"""Visualizza l'albero di sintassi (parse tree) di un programma CookLang.

Usa la libreria `rich` per disegnare un albero colorato nel terminale: ogni
nodo interno è una regola della grammatica (program, statement, expr, ...) in
azzurro, ogni foglia è un token in giallo.

Uso:
    python source/tree.py programs/hello.cook
"""

import sys

from antlr4 import FileStream, CommonTokenStream
from antlr4.tree.Tree import TerminalNode
from rich.console import Console
from rich.text import Text
from rich.tree import Tree

from CookLangLexer import CookLangLexer
from CookLangParser import CookLangParser


def label(node, parser):
    if isinstance(node, TerminalNode):
        return Text(repr(node.getText()), style="yellow")            # token
    return Text(parser.ruleNames[node.getRuleIndex()], style="bold cyan")  # regola


def build(node, parser, branch):
    for i in range(node.getChildCount()):
        child = node.getChild(i)
        build(child, parser, branch.add(label(child, parser)))


def main(argv):
    if len(argv) != 2:
        print("Uso: python source/tree.py <programma.cook>")
        return 1
    lexer = CookLangLexer(FileStream(argv[1], encoding="utf-8"))
    parser = CookLangParser(CommonTokenStream(lexer))

    parse_tree = parser.program()
    tree = Tree(label(parse_tree, parser))
    build(parse_tree, parser, tree)
    Console().print(tree)
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
