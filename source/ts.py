"""Type system di CookLang.

È un visitor ANTLR distinto dall'interprete: prima dell'esecuzione, `TypeChecker`
percorre l'albero, calcola il tipo di ogni espressione e verifica dichiarazioni,
assegnamenti e operazioni. Gli errori vengono raccolti in `self.errors`; se la
lista non è vuota, il programma non viene eseguito.

Ogni metodo `visit*` di un'espressione restituisce il suo tipo (o None se il
tipo non è determinabile a causa di un errore precedente).
"""

from CookLangVisitor import CookLangVisitor

import cooktypes as types
from interpreter import interpolation_parts, parse_expression


class TypeChecker(CookLangVisitor):

    def __init__(self):
        self.scopes = [{}]      # pila di ambienti: nome -> tipo
        self.errors = []

    def enter_scope(self):
        self.scopes.append({})

    def exit_scope(self):
        self.scopes.pop()

    def declare(self, name, type):
        self.scopes[-1][name] = type

    def lookup(self, name):
        """Cerca il tipo di una variabile risalendo gli scope, o None."""
        for scope in reversed(self.scopes):
            if name in scope:
                return scope[name]
        return None

    def add_error(self, message):
        self.errors.append("Errore di tipo: " + message)

    # --- struttura del programma ---

    def visitProgram(self, ctx):
        if ctx.ingredientSection() is not None:
            self.visit(ctx.ingredientSection())
        self.visit(ctx.procedureSection())

    def visitIngredientSection(self, ctx):
        for declaration in ctx.varDecl():
            self.visit(declaration)

    def visitProcedureSection(self, ctx):
        self.visit(ctx.block())

    def visitBlock(self, ctx):
        self.enter_scope()
        for statement in ctx.statement():
            self.visit(statement)
        self.exit_scope()

    def visitBlockStatement(self, ctx):
        self.visit(ctx.block())

    def visitDeclStatement(self, ctx):
        self.visit(ctx.varDecl())

    def visitAssignStatement(self, ctx):
        self.visit(ctx.assign())

    def visitIncDecStatement(self, ctx):
        self.visit(ctx.incDec())

    # --- dichiarazioni e assegnamenti ---

    def visitVarDecl(self, ctx):
        declared = types.read_type(ctx.varType())
        name = ctx.ID().getText()
        if ctx.expr() is not None:
            found = self.visit(ctx.expr())
            if not types.assignable(declared, found):
                self.add_error(f"atteso {types.type_name(declared)}, trovato {types.type_name(found)}")
        self.declare(name, declared)

    def visitVarAssign(self, ctx):
        name = ctx.ID().getText()
        declared = self.lookup(name)
        if declared is None:
            self.add_error(f"variabile '{name}' usata ma non dichiarata")
            return
        found = self.visit(ctx.expr())
        if found is None:
            return
        if ctx.assignOp().getText() == "=":
            if not types.assignable(declared, found):
                self.add_error(f"atteso {types.type_name(declared)}, trovato {types.type_name(found)}")
        elif not (types.is_numeric(declared) and types.is_numeric(found)):
            self.add_error(f"operazione non valida tra {types.type_name(declared)} e {types.type_name(found)}")

    def visitArrayAssign(self, ctx):
        name = ctx.ID().getText()
        declared = self.lookup(name)
        if declared is None:
            self.add_error(f"variabile '{name}' usata ma non dichiarata")
            return
        if not types.is_array(declared):
            self.add_error(f"'{name}' non è un array")
            return
        index = self.visit(ctx.expr(0))
        if index is not None and not types.is_int_like(index):
            self.add_error(f"indice di array non intero: {types.type_name(index)}")
        element = types.elem_type(declared)
        found = self.visit(ctx.expr(1))
        if found is None:
            return
        if ctx.assignOp().getText() == "=":
            if not types.assignable(element, found):
                self.add_error(f"atteso {types.type_name(element)}, trovato {types.type_name(found)}")
        elif not (types.is_numeric(element) and types.is_numeric(found)):
            self.add_error(f"operazione non valida tra {types.type_name(element)} e {types.type_name(found)}")

    def check_numeric_variable(self, ctx):
        """Verifica che la variabile di un ++/-- esista e sia numerica."""
        name = ctx.ID().getText()
        declared = self.lookup(name)
        if declared is None:
            self.add_error(f"variabile '{name}' usata ma non dichiarata")
            return None
        if not types.is_numeric(declared):
            self.add_error(f"operazione non valida su {types.type_name(declared)}")
        return declared

    def visitPreIncDec(self, ctx):
        return self.check_numeric_variable(ctx)

    def visitPostIncDec(self, ctx):
        return self.check_numeric_variable(ctx)

    # --- comandi ---

    def visitPrintStatement(self, ctx):
        self.visit(ctx.expr())

    def visitIfStatement(self, ctx):
        condition = self.visit(ctx.expr())
        if condition is not None and condition != types.BOOL:
            self.add_error(f"condizione dell'if non booleana: {types.type_name(condition)}")
        self.visit(ctx.block(0))
        if ctx.block(1) is not None:
            self.visit(ctx.block(1))

    def visitWhileStatement(self, ctx):
        condition = self.visit(ctx.expr())
        if condition is not None and condition != types.BOOL:
            self.add_error(f"condizione del while non booleana: {types.type_name(condition)}")
        self.visit(ctx.block())

    def visitForStatement(self, ctx):
        for bound in (self.visit(ctx.expr(0)), self.visit(ctx.expr(1))):
            if bound is not None and not types.is_int_like(bound):
                self.add_error(f"gli estremi del for devono essere interi, trovato {types.type_name(bound)}")
        self.enter_scope()
        self.declare(ctx.ID().getText(), types.INT)     # variabile del ciclo
        self.visit(ctx.block())
        self.exit_scope()

    def visitExitStatement(self, ctx):
        return None

    # --- espressioni (restituiscono il tipo) ---

    def visitParenExpr(self, ctx):
        return self.visit(ctx.expr())

    def visitIncDecExpr(self, ctx):
        return self.visit(ctx.incDec())

    def visitIndexExpr(self, ctx):
        array = self.visit(ctx.expr(0))
        index = self.visit(ctx.expr(1))
        if index is not None and not types.is_int_like(index):
            self.add_error(f"indice di array non intero: {types.type_name(index)}")
        if array is None:
            return None
        if not types.is_array(array):
            self.add_error(f"accesso con indice su un tipo non array: {types.type_name(array)}")
            return None
        return types.elem_type(array)

    def visitUnaryExpr(self, ctx):
        operand = self.visit(ctx.expr())
        if operand is None:
            return None
        if ctx.op.text == "!":
            if operand != types.BOOL:
                self.add_error(f"operazione non valida su {types.type_name(operand)}")
            return types.BOOL
        if not types.is_numeric(operand):
            self.add_error(f"operazione non valida su {types.type_name(operand)}")
            return None
        return types.INT if types.is_int_like(operand) else types.FLOAT

    def numeric_operation(self, ctx, int_only=False):
        """Verifica un'operazione binaria numerica e ne restituisce il tipo."""
        a = self.visit(ctx.expr(0))
        b = self.visit(ctx.expr(1))
        if a is None or b is None:
            return None
        both_numeric = types.is_numeric(a) and types.is_numeric(b)
        both_integer = types.is_int_like(a) and types.is_int_like(b)
        if not both_numeric or (int_only and not both_integer):
            self.add_error(f"operazione non valida tra {types.type_name(a)} e {types.type_name(b)}")
            return types.INT if int_only else None
        return types.numeric_result(a, b)

    def visitMulExpr(self, ctx):
        return self.numeric_operation(ctx, int_only=(ctx.op.text == "%"))

    def visitAddExpr(self, ctx):
        return self.numeric_operation(ctx)

    def visitRelExpr(self, ctx):
        a = self.visit(ctx.expr(0))
        b = self.visit(ctx.expr(1))
        if a is not None and b is not None:
            both_numeric = types.is_numeric(a) and types.is_numeric(b)
            if ctx.op.text in ("==", "!="):
                valid = both_numeric or a == b
            else:
                valid = both_numeric
            if not valid:
                self.add_error(f"operazione non valida tra {types.type_name(a)} e {types.type_name(b)}")
        return types.BOOL

    def visitLogicExpr(self, ctx):
        a = self.visit(ctx.expr(0))
        b = self.visit(ctx.expr(1))
        if a is not None and b is not None and not (a == types.BOOL and b == types.BOOL):
            self.add_error(f"operazione non valida tra {types.type_name(a)} e {types.type_name(b)}")
        return types.BOOL

    def visitTernaryExpr(self, ctx):
        condition = self.visit(ctx.expr(0))
        if condition is not None and condition != types.BOOL:
            self.add_error(f"condizione del ternario non booleana: {types.type_name(condition)}")
        a = self.visit(ctx.expr(1))
        b = self.visit(ctx.expr(2))
        if a is None or b is None:
            return a or b
        if a == b:
            return a
        if types.is_numeric(a) and types.is_numeric(b):
            return types.numeric_result(a, b)
        self.add_error(f"rami del ternario incompatibili: {types.type_name(a)} e {types.type_name(b)}")
        return a

    def visitNewArrayExpr(self, ctx):
        size = self.visit(ctx.expr())
        if size is not None and not types.is_int_like(size):
            self.add_error(f"dimensione dell'array non intera: {types.type_name(size)}")
        return types.array_of(ctx.baseType().getText())

    def visitArrayLiteralExpr(self, ctx):
        element_type = None
        for expr in ctx.expr():
            current = self.visit(expr)
            if element_type is None:
                element_type = current
            elif current is not None and current != element_type:
                if types.is_numeric(current) and types.is_numeric(element_type):
                    element_type = types.numeric_result(element_type, current)
                else:
                    self.add_error("elementi dell'array di tipi incompatibili")
        return types.array_of(element_type)

    def visitIntExpr(self, ctx):
        return types.INT

    def visitFloatExpr(self, ctx):
        return types.FLOAT

    def visitBoolExpr(self, ctx):
        return types.BOOL

    def visitStringExpr(self, ctx):
        return types.STRING

    def visitInterpExpr(self, ctx):
        # Verifica staticamente le espressioni annidate ${...}.
        for kind, content in interpolation_parts(ctx.getText()):
            if kind == "expr":
                self.visit(parse_expression(content))
        return types.STRING

    def visitIdExpr(self, ctx):
        name = ctx.getText()
        declared = self.lookup(name)
        if declared is None:
            self.add_error(f"variabile '{name}' usata ma non dichiarata")
        return declared
