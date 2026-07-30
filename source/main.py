"""Entry point dell'interprete CookLang.

Uso:
    python source/main.py programs/hello.cook

Fasi: analisi sintattica -> controllo dei tipi (type system) -> esecuzione.
Se ci sono errori di sintassi o di tipo, il programma non viene eseguito.
"""

import sys

from antlr4 import FileStream, CommonTokenStream
from antlr4.error.ErrorListener import ErrorListener

from CookLangLexer import CookLangLexer
from CookLangParser import CookLangParser
from ts import TypeChecker
from interpreter import Interpreter


class SyntaxErrorCollector(ErrorListener):
    """Raccoglie gli errori di sintassi invece di stamparli su stderr."""

    def __init__(self):
        super().__init__()
        self.errors = []

    def syntaxError(self, recognizer, offendingSymbol, line, column, message, e):
        self.errors.append(f"Errore di sintassi alla riga {line}:{column} - {message}")


def run_file(path):
    lexer = CookLangLexer(FileStream(path, encoding="utf-8"))
    parser = CookLangParser(CommonTokenStream(lexer))

    collector = SyntaxErrorCollector()
    for component in (lexer, parser):
        component.removeErrorListeners()
        component.addErrorListener(collector)

    tree = parser.program()

    errors = collector.errors
    if not errors:
        checker = TypeChecker()
        checker.visit(tree)
        errors = checker.errors

    if errors:
        for error in errors:
            print(error)
        return

    Interpreter().visit(tree)


def main(argv):
    if len(argv) != 2:
        print("Uso: python source/main.py <programma.cook>")
        return 1
    run_file(argv[1])
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
