#!/usr/bin/env bash
# Rigenera il parser ANTLR di CookLang nella cartella source/.
# Richiede il virtual environment (.venv) con antlr4-tools installato e Java 11+.
set -e
cd "$(dirname "$0")"
export ANTLR4_TOOLS_ANTLR_VERSION=4.13.2
.venv/bin/antlr4 -Dlanguage=Python3 -visitor -no-listener CookLang.g4 -o source
echo "Parser generato in source/ (CookLangLexer.py, CookLangParser.py, CookLangVisitor.py)."
