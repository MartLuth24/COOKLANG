"""Interprete di InterpLang.

È un visitor ANTLR: la classe `Interpreter` sovrascrive un metodo `visit*` per
ogni produzione della grammatica ed esegue direttamente il programma. Gli errori
a runtime vengono intercettati statement per statement, così il programma non
termina bruscamente.
"""

import math

from antlr4 import InputStream, CommonTokenStream

from InterpLangLexer import InterpLangLexer
from InterpLangParser import InterpLangParser
from InterpLangVisitor import InterpLangVisitor

import typesys as types
from memory import Environment
from errors import InterpRuntimeError, ExitSignal


# funzioni di supporto
def default_value(type):
    """Valore iniziale di una variabile dichiarata senza inizializzatore."""
    if types.is_array(type):
        return []
    if types.is_int_like(type):
        return 0
    if types.is_float_like(type):
        return 0.0
    if type == types.BOOL:
        return False
    return ""   # String


def convert(value, type):
    """Adatta un valore al tipo della variabile in cui viene memorizzato."""
    if types.is_int_like(type) and isinstance(value, (int, float)):
        return int(value)       # troncamento verso zero (float -> intero)
    if types.is_float_like(type) and isinstance(value, (int, float)):
        return float(value)
    return value


def to_text(value):
    """Converte un valore in stringa per la stampa e l'interpolazione."""
    if isinstance(value, bool):
        return "true" if value else "false"
    if isinstance(value, float) and value.is_integer():
        return str(int(value))          # 100.0 -> "100"
    if isinstance(value, list):
        return "[" + ", ".join(to_text(x) for x in value) + "]"
    return str(value)


def unescape(text):
    """Interpreta le sequenze di escape (\\n, \\t, \\", \\\\) in una stringa."""
    escapes = {"n": "\n", "t": "\t", '"': '"', "\\": "\\", "$": "$"}
    result = ""
    i = 0
    while i < len(text):
        if text[i] == "\\" and i + 1 < len(text):
            result += escapes.get(text[i + 1], text[i + 1])
            i += 2
        else:
            result += text[i]
            i += 1
    return result


def interpolation_parts(raw):
    """Divide una stringa i"...${expr}..." in una lista di coppie.

    Ogni coppia è ("text", stringa) oppure ("expr", codice dell'espressione).
    """
    body = raw[2:-1]        # rimuove i" iniziale e " finale
    parts = []
    text = ""
    i = 0
    while i < len(body):
        if body[i] == "\\" and i + 1 < len(body):
            text += unescape(body[i:i + 2])
            i += 2
        elif body[i] == "$" and i + 1 < len(body) and body[i + 1] == "{":
            if text:
                parts.append(("text", text))
                text = ""
            end = body.find("}", i + 2)
            parts.append(("expr", body[i + 2:end]))
            i = end + 1
        else:
            text += body[i]
            i += 1
    if text:
        parts.append(("text", text))
    return parts


def parse_expression(source):
    """Costruisce l'albero di sintassi di una singola espressione."""
    lexer = InterpLangLexer(InputStream(source))
    parser = InterpLangParser(CommonTokenStream(lexer))
    return parser.expr()


def compute(op, a, b, line):
    """Applica un operatore aritmetico, gestendo la divisione per zero.

    La divisione e il modulo tra interi troncano verso zero (come in Java).
    """
    if op == "+":
        return a + b
    if op == "-":
        return a - b
    if op == "*":
        return a * b
    # op è '/' oppure '%': serve controllare la divisione per zero
    if b == 0:
        raise InterpRuntimeError("divisione per zero", line)
    if isinstance(a, int) and isinstance(b, int):
        quotient = abs(a) // abs(b)
        if (a < 0) != (b < 0):
            quotient = -quotient
        return quotient if op == "/" else a - quotient * b
    return a / b if op == "/" else math.fmod(a, b)


# interprete

class Interpreter(InterpLangVisitor):

    def __init__(self):
        self.env = Environment()

    def execute(self, statement):
        """Esegue uno statement; un errore a runtime viene stampato e
        l'esecuzione continua con lo statement successivo."""
        try:
            self.visit(statement)
        except InterpRuntimeError as error:
            print(error)

    # struttura del programma

    def visitProgram(self, ctx):
        try:
            if ctx.ingredientSection() is not None:
                self.visit(ctx.ingredientSection())
            self.visit(ctx.procedureSection())
        except ExitSignal:
            pass    # il comando `exit` termina il programma

    def visitIngredientSection(self, ctx):
        for declaration in ctx.varDecl():
            self.execute(declaration)

    def visitProcedureSection(self, ctx):
        self.visit(ctx.block())

    def visitBlock(self, ctx):
        self.env.push()
        try:
            for statement in ctx.statement():
                self.execute(statement)
        finally:
            self.env.pop()

    def visitBlockStatement(self, ctx):
        self.visit(ctx.block())

    def visitDeclStatement(self, ctx):
        self.visit(ctx.varDecl())

    def visitAssignStatement(self, ctx):
        self.visit(ctx.assign())

    def visitIncDecStatement(self, ctx):
        self.visit(ctx.incDec())

    # dichiarazioni e assegnamenti

    def visitVarDecl(self, ctx):
        type = types.read_type(ctx.varType())
        name = ctx.ID().getText()
        # Dichiara subito con il default: così la variabile esiste anche se la
        # valutazione dell'inizializzatore solleva un errore a runtime.
        self.env.declare(name, type, default_value(type))
        if ctx.expr() is not None:
            self.env.assign(name, convert(self.visit(ctx.expr()), type))

    def visitVarAssign(self, ctx):
        name = ctx.ID().getText()
        type = self.env.type_of(name)
        op = ctx.assignOp().getText()
        value = self.visit(ctx.expr())
        if op != "=":
            value = compute(op[0], self.env.get(name), value, ctx.start.line)
        self.env.assign(name, convert(value, type))

    def visitArrayAssign(self, ctx):
        name = ctx.ID().getText()
        array = self.env.get(name)
        element_type = types.elem_type(self.env.type_of(name))
        index = self.visit(ctx.expr(0))
        if index < 0 or index >= len(array):
            raise InterpRuntimeError(f"indice {index} fuori dai limiti (size={len(array)})", ctx.start.line)
        op = ctx.assignOp().getText()
        value = self.visit(ctx.expr(1))
        if op != "=":
            value = compute(op[0], array[index], value, ctx.start.line)
        array[index] = convert(value, element_type)

    def visitPreIncDec(self, ctx):
        name = ctx.ID().getText()
        type = self.env.type_of(name)
        step = 1 if ctx.op.text == "++" else -1
        new_value = convert(self.env.get(name) + step, type)
        self.env.assign(name, new_value)
        return new_value

    def visitPostIncDec(self, ctx):
        name = ctx.ID().getText()
        type = self.env.type_of(name)
        step = 1 if ctx.op.text == "++" else -1
        old_value = self.env.get(name)
        self.env.assign(name, convert(old_value + step, type))
        return old_value

    # comandi

    def visitPrintStatement(self, ctx):
        print(to_text(self.visit(ctx.expr())))

    def visitIfStatement(self, ctx):
        if self.visit(ctx.expr()):
            self.visit(ctx.block(0))
        elif ctx.block(1) is not None:
            self.visit(ctx.block(1))

    def visitWhileStatement(self, ctx):
        while self.visit(ctx.expr()):
            self.visit(ctx.block())

    def visitForStatement(self, ctx):
        name = ctx.ID().getText()
        start = self.visit(ctx.expr(0))
        end = self.visit(ctx.expr(1))
        self.env.push()                     # scope del ciclo
        self.env.declare(name, types.INT, start)
        current = start
        while current <= end:
            self.env.assign(name, current)
            self.visit(ctx.block())
            current += 1
        self.env.pop()

    def visitExitStatement(self, ctx):
        raise ExitSignal()

    # espressioni

    def visitParenExpr(self, ctx):
        return self.visit(ctx.expr())

    def visitIncDecExpr(self, ctx):
        return self.visit(ctx.incDec())

    def visitIndexExpr(self, ctx):
        array = self.visit(ctx.expr(0))
        index = self.visit(ctx.expr(1))
        if index < 0 or index >= len(array):
            raise InterpRuntimeError(f"indice {index} fuori dai limiti (size={len(array)})", ctx.start.line)
        return array[index]

    def visitUnaryExpr(self, ctx):
        value = self.visit(ctx.expr())
        return (not value) if ctx.op.text == "!" else -value

    def visitMulExpr(self, ctx):
        return compute(ctx.op.text, self.visit(ctx.expr(0)), self.visit(ctx.expr(1)), ctx.start.line)

    def visitAddExpr(self, ctx):
        return compute(ctx.op.text, self.visit(ctx.expr(0)), self.visit(ctx.expr(1)), ctx.start.line)

    def visitRelExpr(self, ctx):
        a = self.visit(ctx.expr(0))
        b = self.visit(ctx.expr(1))
        op = ctx.op.text
        if op == "==":
            return a == b
        if op == "!=":
            return a != b
        if op == "<":
            return a < b
        if op == ">":
            return a > b
        if op == "<=":
            return a <= b
        return a >= b

    def visitLogicExpr(self, ctx):
        # Semantica non short-circuit: entrambi gli operandi sono valutati.
        a = bool(self.visit(ctx.expr(0)))
        b = bool(self.visit(ctx.expr(1)))
        return (a and b) if ctx.op.text == "&&" else (a or b)

    def visitTernaryExpr(self, ctx):
        if self.visit(ctx.expr(0)):
            return self.visit(ctx.expr(1))
        return self.visit(ctx.expr(2))

    def visitNewArrayExpr(self, ctx):
        element_type = ctx.baseType().getText()
        size = self.visit(ctx.expr())
        return [default_value(element_type) for _ in range(size)]

    def visitArrayLiteralExpr(self, ctx):
        return [self.visit(element) for element in ctx.expr()]

    def visitIntExpr(self, ctx):
        return int(ctx.getText())

    def visitFloatExpr(self, ctx):
        return float(ctx.getText())

    def visitBoolExpr(self, ctx):
        return ctx.getText() == "true"

    def visitStringExpr(self, ctx):
        return unescape(ctx.getText()[1:-1])

    def visitInterpExpr(self, ctx):
        result = ""
        for kind, content in interpolation_parts(ctx.getText()):
            if kind == "text":
                result += content
            else:
                result += to_text(self.visit(parse_expression(content)))
        return result

    def visitIdExpr(self, ctx):
        return self.env.get(ctx.getText())
