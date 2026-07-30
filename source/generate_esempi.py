"""Genera programmi CookLang (.cook) CASUALI, mai uguali, di dimensione variabile.

Uso:
    python source/generate_examples.py --count 5 --depth 3 --length 6

Parametri:
    --count    quanti file generare
    --depth    profondità massima di annidamento (quanto è "alto" l'albero:
               quanti if/for/while si possono annidare uno dentro l'altro)
    --length   lunghezza massima (quanti comandi nel blocco principale)
    --seed     seme casuale, per ottenere sempre gli stessi programmi

I file vengono creati in  programs/generati/ , con nomi random_01.cook, ...
Ogni programma generato è valido: usa solo variabili dichiarate, i tipi sono
corretti e i cicli terminano sempre.
"""

import argparse
import os
import random


FRASI = ["Passo", "Ingrediente", "Cottura", "Fase", "Quantità", "Porzione"]


class Generatore:
    def __init__(self, depth, length):
        self.depth = depth              # profondità massima di annidamento
        self.length = length            # numero di comandi nel blocco principale
        self.scopes = [{}]              # pila di ambienti: nome -> {"type", "size"}
        self.contatore = 0              # per generare nomi unici (v1, v2, ...)
        self.riservate = set()          # nomi da non riassegnare (contatori dei while)

    # --- nomi e ambienti ---

    def nuovo_nome(self):
        self.contatore += 1
        return "v" + str(self.contatore)

    def entra_scope(self):
        self.scopes.append({})

    def esci_scope(self):
        self.scopes.pop()

    def dichiara(self, nome, tipo, size=0):
        self.scopes[-1][nome] = {"type": tipo, "size": size}

    def variabili(self, tipo):
        risultato = []
        for scope in self.scopes:
            for nome, info in scope.items():
                if info["type"] == tipo:
                    risultato.append((nome, info))
        return risultato

    def assegnabili(self):
        # variabili semplici che si possono riassegnare (non i contatori riservati)
        risultato = []
        for scope in self.scopes:
            for nome, info in scope.items():
                if info["type"] in ("Int", "Bool", "String") and nome not in self.riservate:
                    risultato.append((nome, info))
        return risultato

    def indent(self, righe):
        return ["    " + r for r in righe]

    # --- espressioni ---

    def espr_int(self, profondita):
        variabili = self.variabili("Int")
        array = self.variabili("array")
        opzioni = ["numero"]
        if variabili:
            opzioni.append("variabile")
        if array:
            opzioni.append("elemento")
        if profondita > 0:
            opzioni.append("operazione")
        scelta = random.choice(opzioni)

        if scelta == "numero":
            return str(random.randint(0, 9))
        if scelta == "variabile":
            return random.choice(variabili)[0]
        if scelta == "elemento":
            nome, info = random.choice(array)
            return nome + "[" + str(random.randint(0, info["size"] - 1)) + "]"
        operatore = random.choice(["+", "-", "*"])
        return "(" + self.espr_int(profondita - 1) + " " + operatore + " " + self.espr_int(profondita - 1) + ")"

    def espr_bool(self, profondita):
        variabili = self.variabili("Bool")
        opzioni = ["costante", "confronto"]
        if variabili:
            opzioni.append("variabile")
        if profondita > 0:
            opzioni += ["logica", "negazione"]
        scelta = random.choice(opzioni)

        if scelta == "costante":
            return random.choice(["true", "false"])
        if scelta == "variabile":
            return random.choice(variabili)[0]
        if scelta == "confronto":
            operatore = random.choice(["<", ">", "<=", ">=", "==", "!="])
            return "(" + self.espr_int(1) + " " + operatore + " " + self.espr_int(1) + ")"
        if scelta == "logica":
            operatore = random.choice(["&&", "||"])
            return "(" + self.espr_bool(profondita - 1) + " " + operatore + " " + self.espr_bool(profondita - 1) + ")"
        return "(!" + self.espr_bool(profondita - 1) + ")"

    def espr_string(self):
        frase = random.choice(FRASI)
        if random.random() < 0.5:
            return '"' + frase + '"'
        return 'i"' + frase + ': ${' + self.espr_int(1) + '}"'

    # --- statement semplici ---

    def statement_dichiara(self):
        # Nota: il valore iniziale va generato PRIMA di dichiarare il nome, così
        # non può riferirsi alla variabile che si sta ancora dichiarando.
        tipo = random.choice(["Int", "Bool", "String", "array"])
        nome = self.nuovo_nome()
        if tipo == "Int":
            valore = self.espr_int(2)
            self.dichiara(nome, "Int")
            return ["Int " + nome + " = " + valore + ";"]
        if tipo == "Bool":
            valore = self.espr_bool(1)
            self.dichiara(nome, "Bool")
            return ["Bool " + nome + " = " + valore + ";"]
        if tipo == "String":
            valore = self.espr_string()
            self.dichiara(nome, "String")
            return ["String " + nome + " = " + valore + ";"]
        # array di interi (il valore iniziale non usa variabili)
        dimensione = random.randint(2, 5)
        if random.random() < 0.5:
            riga = "Int[] " + nome + " = new Int[" + str(dimensione) + "];"
        else:
            elementi = ", ".join(str(random.randint(0, 9)) for _ in range(dimensione))
            riga = "Int[] " + nome + " = [" + elementi + "];"
        self.dichiara(nome, "array", dimensione)
        return [riga]

    def statement_assegna(self):
        candidati = self.assegnabili()
        if not candidati:
            return self.statement_dichiara()
        nome, info = random.choice(candidati)
        if info["type"] == "Int":
            scelta = random.random()
            if scelta < 0.3:
                return [nome + " " + random.choice(["+=", "-=", "*="]) + " " + self.espr_int(1) + ";"]
            if scelta < 0.45:
                return [nome + random.choice(["++", "--"]) + ";"]
            return [nome + " = " + self.espr_int(2) + ";"]
        if info["type"] == "Bool":
            return [nome + " = " + self.espr_bool(1) + ";"]
        return [nome + " = " + self.espr_string() + ";"]

    def statement_array_assegna(self):
        nome, info = random.choice(self.variabili("array"))
        indice = random.randint(0, info["size"] - 1)
        return [nome + "[" + str(indice) + "] = " + self.espr_int(1) + ";"]

    def statement_stampa(self):
        if random.random() < 0.5:
            return ["print " + self.espr_string() + ";"]
        return ['print i"Valore = ${' + self.espr_int(2) + '}";']

    # --- statement composti (annidati) ---

    def corpo(self, profondita, variabile_extra=None):
        """Genera 1-2 statement in un nuovo scope; restituisce le righe indentate."""
        self.entra_scope()
        if variabile_extra is not None:
            self.dichiara(variabile_extra, "Int")
        righe = []
        for _ in range(random.randint(1, 2)):
            righe += self.statement(profondita)
        self.esci_scope()
        return self.indent(righe)

    def statement_if(self, profondita):
        righe = ["if (" + self.espr_bool(1) + ") {"] + self.corpo(profondita - 1)
        if random.random() < 0.5:
            righe.append("} else {")
            righe += self.corpo(profondita - 1)
        righe.append("}")
        return righe

    def statement_for(self, profondita):
        nome = self.nuovo_nome()
        inizio = random.randint(0, 2)
        fine = inizio + random.randint(1, 3)
        intestazione = "for " + nome + " from " + str(inizio) + " to " + str(fine) + " {"
        return [intestazione] + self.corpo(profondita - 1, variabile_extra=nome) + ["}"]

    def statement_while(self, profondita):
        nome = self.nuovo_nome()
        limite = random.randint(1, 4)
        self.dichiara(nome, "Int")
        self.riservate.add(nome)             # il corpo non deve riassegnare il contatore
        corpo = self.corpo(profondita - 1)
        self.riservate.discard(nome)
        corpo.append("    " + nome + "++;")  # garantisce la terminazione del ciclo
        return ["Int " + nome + " = 0;", "while (" + nome + " < " + str(limite) + ") {"] + corpo + ["}"]

    def statement(self, profondita):
        opzioni = ["dichiara", "assegna", "stampa"]
        if self.variabili("array"):
            opzioni.append("array_assegna")
        if profondita > 0:
            opzioni += ["if", "for", "while"]
        scelta = random.choice(opzioni)
        if scelta == "dichiara":
            return self.statement_dichiara()
        if scelta == "assegna":
            return self.statement_assegna()
        if scelta == "stampa":
            return self.statement_stampa()
        if scelta == "array_assegna":
            return self.statement_array_assegna()
        if scelta == "if":
            return self.statement_if(profondita)
        if scelta == "for":
            return self.statement_for(profondita)
        return self.statement_while(profondita)

    # --- programma completo ---

    def genera_programma(self):
        corpo = self.statement_dichiara() + self.statement_dichiara()   # due variabili iniziali
        for _ in range(self.length):
            corpo += self.statement(self.depth)
        intestazione = [
            "// Programma CookLang generato casualmente",
            "// depth (profondità) = " + str(self.depth) + ", length (lunghezza) = " + str(self.length),
            "",
            "procedure: {",
        ]
        return "\n".join(intestazione + self.indent(corpo) + ["}"]) + "\n"


def main():
    parser = argparse.ArgumentParser(description="Genera programmi CookLang casuali.")
    parser.add_argument("--count", type=int, default=5, help="quanti file generare")
    parser.add_argument("--depth", type=int, default=3, help="profondità massima di annidamento")
    parser.add_argument("--length", type=int, default=6, help="numero massimo di comandi nel blocco principale")
    parser.add_argument("--seed", type=int, default=None, help="seme casuale per risultati riproducibili")
    args = parser.parse_args()

    if args.seed is not None:
        random.seed(args.seed)

    project_dir = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    folder = os.path.join(project_dir, "programs", "generati")
    os.makedirs(folder, exist_ok=True)

    # Rimuove i programmi casuali generati in precedenza.
    for vecchio in os.listdir(folder):
        if vecchio.startswith("random_") and vecchio.endswith(".cook"):
            os.remove(os.path.join(folder, vecchio))

    for i in range(1, args.count + 1):
        depth = random.randint(1, max(1, args.depth))       # profondità diversa per ogni file
        length = random.randint(3, max(3, args.length))     # lunghezza diversa per ogni file
        codice = Generatore(depth, length).genera_programma()
        nome = "random_%02d.cook" % i
        with open(os.path.join(folder, nome), "w", encoding="utf-8") as file:
            file.write(codice)
        print("Creato programs/generati/%s  (depth=%d, length=%d)" % (nome, depth, length))

    print("\n%d programmi casuali generati in programs/generati/" % args.count)


if __name__ == "__main__":
    main()
