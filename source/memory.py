"""Memoria e scoping di CookLang.

L'ambiente è una pila di frame. Ogni frame è una mappa da nome a `Cell` (tipo +
valore). Entrare in un blocco fa push di un frame, uscirne fa pop. La ricerca di
una variabile risale la pila, dal frame più interno a quello più esterno:
questo realizza lo scoping lessicale con shadowing.
"""


class Cell:
    """Una cella di memoria: tipo dichiarato e valore corrente."""

    def __init__(self, type, value):
        self.type = type
        self.value = value


class Environment:
    def __init__(self):
        # stack[0] è il frame globale (ingredienti); stack[-1] è quello corrente.
        self.stack = [{}]

    def push(self):
        self.stack.append({})

    def pop(self):
        self.stack.pop()

    def declare(self, name, type, value):
        """Dichiara una nuova variabile nel frame corrente."""
        self.stack[-1][name] = Cell(type, value)

    def find(self, name):
        """Restituisce la cella della variabile, o None se non dichiarata."""
        for frame in reversed(self.stack):
            if name in frame:
                return frame[name]
        return None

    def get(self, name):
        return self.find(name).value

    def type_of(self, name):
        return self.find(name).type

    def assign(self, name, value):
        """Aggiorna la variabile nel frame più interno che la dichiara."""
        self.find(name).value = value
