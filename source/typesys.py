"""Tipi di InterpLang e relative regole.

I tipi scalari sono semplici stringhe ("Int", "Float", "Bool", "String",
"Gram", "Ml", "Temp"). Un tipo array è la tupla ("array", tipoElemento).

Gerarchia:
    Bool
    String
    Float
    ├── Int
    │   ├── Gram
    │   └── Ml
    └── Temp

Conversioni implicite (solo in espressioni miste): Int -> Float,
Gram/Ml -> Int, Temp -> Float. È vietato il restringimento Float -> intero.
"""

INT = "Int"
FLOAT = "Float"
BOOL = "Bool"
STRING = "String"
GRAM = "Gram"
ML = "Ml"
TEMP = "Temp"

# Tipi numerici, suddivisi per rappresentazione in Python.
NUMERIC = {INT, FLOAT, GRAM, ML, TEMP}
INT_LIKE = {INT, GRAM, ML}       # rappresentati con int
FLOAT_LIKE = {FLOAT, TEMP}       # rappresentati con float


def array_of(element):
    return ("array", element)


def is_array(t):
    return isinstance(t, tuple) and t[0] == "array"


def elem_type(t):
    return t[1]


def is_numeric(t):
    return t in NUMERIC


def is_int_like(t):
    return t in INT_LIKE


def is_float_like(t):
    return t in FLOAT_LIKE


def numeric_result(a, b):
    """Tipo risultante di un'operazione aritmetica tra due tipi numerici."""
    if is_float_like(a) or is_float_like(b):
        return FLOAT
    return INT


def assignable(target, source):
    """Vero se un valore di tipo `source` è assegnabile a una variabile `target`."""
    if source is None or target is None:
        return True                       # tipo sconosciuto: niente errori a cascata
    if target == source:
        return True
    if is_array(target) and is_array(source):
        if elem_type(source) is None:
            return True                   # letterale array vuoto
        return assignable(elem_type(target), elem_type(source))
    if is_numeric(target) and is_numeric(source):
        # è vietato assegnare un float a una variabile intera (restringimento)
        return not (is_int_like(target) and is_float_like(source))
    return False


def type_name(t):
    """Nome leggibile di un tipo, per i messaggi d'errore."""
    if is_array(t):
        return type_name(elem_type(t)) + "[]"
    return str(t)


def read_type(node):
    """Legge il tipo da un nodo `varType` (baseType con eventuale `[]`)."""
    base = node.baseType().getText()
    if node.getChildCount() > 1:          # baseType '[' ']'  -> array
        return array_of(base)
    return base
