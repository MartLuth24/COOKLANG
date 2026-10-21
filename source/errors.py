class InterpRuntimeError(Exception):
    """Errore a tempo d'esecuzione (divisione per zero, indice fuori dai limiti).

    Viene intercettato dall'interprete, stampato con un messaggio descrittivo,
    e l'esecuzione prosegue con lo statement successivo.
    """

    def __init__(self, message, line):
        self.message = message
        self.line = line

    def __str__(self):
        return f"Errore runtime: {self.message} alla riga {self.line}"


class ExitSignal(Exception):
    """Segnale interno usato dal comando `exit` per terminare il programma."""
    pass
