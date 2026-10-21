# InterpLang

Un linguaggio di programmazione **imperativo** a **tipizzazione statica**, pensato per eseguire algoritmi e gestire tipi di dato personalizzati.

## Indice

1. [Introduzione](#1-introduzione)
2. [Guida Rapida](#2-guida-rapida)
3. [Sintassi](#3-sintassi)
4. [Semantica](#4-semantica)
5. [Implementazione](#5-implementazione)
6. [Programmi di Esempio](#6-programmi-di-esempio)
7. [Test](#7-test)

## 1. Introduzione

**InterpLang** è un linguaggio imperativo a tipizzazione statica, progettato e implementato da zero. È *general-purpose*: serve a eseguire algoritmi, fare calcoli ed elaborare strutture dati.

Ai tipi primitivi abbiamo aggiunto alcuni **tipi di dominio** pensati per la cucina (grammi, millilitri, temperature) per mostrare come il sistema di tipi possa essere esteso con regole e conversioni proprie di un ambito specifico.

### Scelte progettuali

- **Parte general-purpose** — algoritmi classici (fattoriale, Fibonacci, ordinamento, ricerca del massimo…) usando i tipi primitivi `Int`, `Float`, `Bool`, `String` e la sezione eseguibile `procedure:`.
- **Estensione DSL (dominio)** — la sezione opzionale `ingredients:` e i tipi `Gram`, `Ml`, `Temp` mostrano come estendere i tipi numerici standard con sottotipi e conversioni dedicate.

### Caratteristiche principali

- **Tipizzazione statica** con tipi primitivi (`Int`, `Float`, `Bool`, `String`), array (`T[]`) e tipi di dominio (`Gram`, `Ml`, `Temp`).
- **Due sezioni**: `ingredients:` (opzionale, variabili globali) e `procedure:` (obbligatoria, codice eseguibile).
- **Espressioni aritmetico-logiche** con la normale precedenza degli operatori.
- **Costrutti di controllo**: `if`/`else`, `while`, `for … from … to`.
- **Array** omogenei, con literal (`[1, 2, 3]`) o allocazione (`new T[size]`) e accesso per indice (`arr[i]`).
- **Zucchero sintattico**: `+=` `-=` `*=` `/=`, `++` `--`, operatore ternario `cond ? a : b`, interpolazione nelle stringhe `i"...${expr}..."`.
- **Errori a due livelli**: controllo di tipo *prima* dell'esecuzione e gestione degli errori a runtime *senza* far crashare l'interprete.

## 2. Guida Rapida

### Requisiti

- **Java 11+** (serve solo per *generare* il parser con ANTLR).
- **Python 3.9+** per eseguire l'interprete.
- Dipendenze Python (in `requirements.txt`): `antlr4-python3-runtime`, `antlr4-tools`, `rich`.

### Installazione

```bash
python3 -m venv .venv
source .venv/bin/activate        # su Windows: .venv\Scripts\activate
pip install -r requirements.txt
```

### Comandi

```bash
# 1. generare il parser/lexer (crea InterpLangLexer.py, InterpLangParser.py, InterpLangVisitor.py)
antlr4 -Dlanguage=Python3 -visitor -no-listener InterpLang.g4 -o source

# 2. eseguire un programma
python source/main.py programs/hello.cook

# 3. (facoltativo) stampare l'albero sintattico
python source/tree.py programs/hello.cook

# 4. (facoltativo) eseguire tutti i test nella cartella
$basePath = (Get-Location).Path
Get-ChildItem programs\test -Filter *.cook | ForEach-Object {
    $relativePath = $_.FullName.Substring($basePath.Length + 1)
    Write-Host "Esecuzione $relativePath"
    python source\main.py $relativePath
}
# 4. (facoltativo) eseguire solo i test fuori della grande cartella che si trovano nel test
$basePath = (Get-Location).Path
Get-ChildItem programs -Filter *.cook -Recurse | ForEach-Object {
    $relativePath = $_.FullName.Substring($basePath.Length + 1)
    Write-Host "Esecuzione $relativePath"
    python source\main.py $relativePath
}
```

```bash
# Pulire i file generati
Write-Host "==> 1. Pulizia dei file generati e delle cache..." -ForegroundColor Cyan
Remove-Item -Path "source\InterpLang*.py", "source\InterpLang*.tokens", "source\InterpLang*.interp" -ErrorAction SilentlyContinue
Get-ChildItem -Path . -Recurse -Directory -Filter "__pycache__" | Remove-Item -Recurse -Force -ErrorAction SilentlyContinue
Write-Host "    Pulizia completata." -ForegroundColor Green
```
### Esempio di codice

```
procedure: {
    String messaggio = "Calcolo Media";
    print i"Avvio programma: ${messaggio}";

    Int[] voti = [24, 28, 30];
    Int somma = 0;
    for i from 0 to 2 {
        somma += voti[i];
    }

    Float media = somma / 3.0;   // 3.0 (Float) forza la divisione reale; con 3 (Int) sarebbe intera
    print i"Media finale: ${media}";
}
```
> **Output** <br>
> Avvio programma: Calcolo Media <br>
> Media finale: 27.333333333333332

Esempio con i tipi di dominio:

```
ingredients:
    Gram farina = 500;
    Ml   acqua  = 300;
    Temp forno  = 180;

procedure: {
    Int porzioni = 4;
    Gram farinaPerPorzione = farina / porzioni;
    print i"Farina per porzione: ${farinaPerPorzione}g";
    print i"Temperatura forno: ${forno}°C";
}
```
> **Output** <br>
> Farina per porzione: 125g <br>
> Temperatura forno: 180°C

## 3. Sintassi

### 3.1 Struttura di un programma

```
program : ingredientSection? procedureSection
```

- **`ingredientSection`** (opzionale) — variabili globali, visibili in tutto il programma.
- **`procedureSection`** (obbligatoria) — introdotta da `procedure:`, contiene il blocco eseguibile `{ ... }`.

### 3.2 Tipi di dato

| Tipo     | Descrizione                                | Default | Esempio         |
|----------|--------------------------------------------|---------|-----------------|
| `Int`    | Numero intero con segno                    | `0`     | `42`, `-3`      |
| `Float`  | Numero in virgola mobile                   | `0.0`   | `3.14`, `-0.5`  |
| `Bool`   | Valore booleano                            | `false` | `true`, `false` |
| `String` | Stringa di caratteri                       | `""`    | `"ciao"`        |
| `Gram`   | Peso in grammi (sottotipo di `Int`)        | `0`     | `250`           |
| `Ml`     | Volume in millilitri (sottotipo di `Int`)  | `0`     | `100`           |
| `Temp`   | Temperatura in °C (sottotipo di `Float`)   | `0.0`   | `180`, `24.5`   |
| `T[]`    | Array di elementi di tipo `T`              | `[]`    | `[1, 2, 3]`     |

* **`Gram` e `Ml`** sono sottotipi di `Int`.
* **`Temp`** è un sottotipo di `Float`.
* Nelle espressioni miste, i tipi vengono convertiti **automaticamente** nel loro tipo padre (`Int` o `Float`).
* Questo permette di mescolare le variabili nei calcoli **senza bisogno di fare cast espliciti** (conversioni manuali).

### 3.3 Dichiarazione di variabili

```
Int x = 10;
Float y;                  // inizializzata al default 0.0
String s = "ok";
Int[] arr = [1, 2, 3];    // array literal
Int[] buf = new Int[5];   // array allocato (interi a 0)
```

Valgono le regole classiche dello **scoping lessicale** con **shadowing**: le variabili di `ingredients:` sono globali; quelle dichiarate in un blocco `{ ... }` vivono solo lì dentro.

### 3.4 Assegnamenti

```
x = 42;
x += 5;    x -= 2;    x *= 3;    x /= 4;   // assegnamenti composti
++x;   x++;   --x;   x--;                  // incremento / decremento
```

### 3.5 Espressioni

Precedenza degli operatori (dal più basso al più alto):

| Livello | Operatori                                        |
|---------|--------------------------------------------------|
| 1       | `_ ? _ : _` (ternario)                           |
| 2       | `\|\|`, `&&`                                      |
| 3       | `==`, `!=`, `<`, `>`, `<=`, `>=`                  |
| 4       | `+`, `-`                                          |
| 5       | `*`, `/`, `%`                                     |
| 6       | `!` (not), `-` (negazione)                        |
| 7       | `arr[i]` (accesso array)                          |
| 8       | `++`, `--`                                        |

Esempio di **operatore ternario**:
```
String stato = (porzioni > 1) ? "porzioni" : "porzione";
```
Permette di valutare una condizione al volo: se la condizione tra parentesi è vera, viene restituito il primo valore (in questo caso `"porzioni"`), altrimenti viene restituito il secondo (`"porzione"`).

### 3.6 Costrutti di controllo

```
if (temperatura > 100) { print "Soglia superata"; }
else                   { print "Nella norma"; }

Int i = 0;
while (i < 5) { print i"Passo ${i}"; i++; }

for i from 1 to 10 { print i"Iterazione ${i}"; }
```

Il `for i from a to b` fa variare `i` da `a` a `b` inclusi; `a` e `b` sono interi (o espressioni intere), in poche parole stabilisce un range.

### 3.7 Stampa e interpolazione

```
print "Testo semplice";
print i"Risultato: ${x + y}";
```

Con `print` si stampa a terminale. La sintassi `i"..."` permette di inserire espressioni nel testo tra `${...}`.

### 3.8 Array

```
Int[] numeri = [10, 20, 30];   // literal
Int primo = numeri[0];         // lettura
numeri[1] = 99;                // scrittura
Int[] buffer = new Int[5];     // allocazione (5 interi a 0)
```

### 3.9 Uscita e commenti

```
exit;              // termina subito il programma

// commento su una riga
/* commento
   su più righe */
```

### 3.10 Regole lessicali

- Identificatori:  iniziano con una lettera o `_`, seguiti da lettere, cifre, o `_`.
- Parole chiave: riservate a case-sensitive (es.`if`, `while`, `for`, `true`, `false`, nomi dei tipi).
- Blocchi tra `{` e `}`; ogni statement termina con `;`.
- Spazi, tabulazioni e newline sono ignorati dal lexer.

## 4. Semantica

### 4.1 Tipizzazione

InterpLang è a **tipizzazione statica**: ogni variabile ha un tipo esplicito. Un **type checker** visita l'albero *prima* dell'esecuzione e, se trova anomalie, blocca il programma con un messaggio descrittivo:

| Errore                      | Messaggio                                                 |
|-----------------------------|----------------------------------------------------------|
| Variabile non dichiarata    | *Errore di tipo: variabile 'x' usata ma non dichiarata*  |
| Tipo incompatibile          | *Errore di tipo: operazione non valida tra Int e Bool*   |
| Assegnamento errato         | *Errore di tipo: atteso Gram, trovato String*            |

### 4.2 Visibilità e scoping

La memoria è una **pila di frame**. Ogni blocco `{ ... }` aggiunge un frame (con `push`) e lo rimuove all'uscita (con `pop`). La ricerca di una variabile risale la pila dal frame più interno: se una variabile locale ha lo stesso nome di una esterna, la **nasconde** (*shadowing*) fino a fine blocco. Le variabili di `ingredients:` stanno nel frame globale.

### 4.3 Valutazione delle espressioni

Le espressioni sono valutate in modo standard: tutti gli operandi vengono calcolati prima dell'operatore. Gli operatori logici `&&` e `||` **non** sono short-circuit (valutano sempre entrambi i lati).

### 4.4 Gerarchia dei tipi

```
Bool
String
Float
├── Int
│   ├── Gram
│   └── Ml
└── Temp
```

Catene di sottotipaggio e relative conversioni implicite (solo in espressioni miste):

$$
\text{Gram} \subsetneq \text{Int} \subsetneq \text{Float} \qquad
\text{Ml} \subsetneq \text{Int} \subsetneq \text{Float} \qquad
\text{Temp} \subsetneq \text{Float}
$$

È vietato il restringimento `Float → Int` (assegnare un float a un intero è un errore di tipo). Non esistono conversioni esplicite: per forzare si usa un'espressione (es. `Float x = intVal + 0.0`).

### 4.5 Gestione degli errori a runtime

Gli errori a tempo d'esecuzione vengono intercettati dall'interprete e segnalati a terminale con messaggi esplicativi, senza terminare in modo anomalo l'esecuzione dell'ambiente:

| Errore                    | Messaggio                                              |
|---------------------------|-------------------------------------------------------|
| Divisione per zero        | *Errore runtime: divisione per zero alla riga 8*      |
| Indice fuori dai limiti   | *Errore runtime: indice 7 fuori dai limiti (size=5)*  |

### 4.6 Semantica operazionale (estratto)

Lo stato è una coppia $(\overline{\sigma}, c)$ dove $\overline{\sigma} = \sigma_1 \cdot \sigma_2 \cdot \ldots \cdot \sigma_n$ è una pila di memorie (una memoria $\sigma$ è una mappa da identificatori a valori) e $c$ è il comando (o l'espressione) da valutare. La pila, con ultimo elemento inserito a sinistra, è necessaria per gestire lo scoping delle variabili all'interno di blocchi di codice.

**Blocco**

$$
\text{Block} ~ \frac{-}{(\overline{\sigma},\ \{ c \}) \rightarrow (\sigma \cdot \overline{\sigma},\ \mathtt{block}(c))} ~ \sigma = \varnothing
\quad
\text{BlockP} ~ \frac{(\overline{\sigma},\ c) \rightarrow (\overline{\sigma}',\ c')}{(\overline{\sigma},\ \mathtt{block}(c)) \rightarrow (\overline{\sigma}',\ \mathtt{block}(c'))}
\quad
\text{BlockE} ~ \frac{-}{(\sigma \cdot \overline{\sigma},\ \mathtt{block}(\epsilon)) \rightarrow (\overline{\sigma},\ \epsilon)}
$$

**Ciclo while**

$$
\text{While} ~ \frac{-}{(\overline{\sigma},\ \mathtt{while}\,(e)\,\{ c \}) \rightarrow (\overline{\sigma},\ \mathtt{if}\,(e)\,\{ c \,;\, \mathtt{while}\,(e)\,\{ c \} \})}
$$

**Sequenza**

$$
\text{SeqP} ~ \frac{(\overline{\sigma},\ c_1) \rightarrow (\overline{\sigma}',\ c_1')}{(\overline{\sigma},\ c_1 \,;\, c_2) \rightarrow (\overline{\sigma}',\ c_1' \,;\, c_2)}
\qquad
\text{SeqE} ~ \frac{-}{(\overline{\sigma},\ \epsilon \,;\, c) \rightarrow (\overline{\sigma},\ c)}
$$

## 5. Implementazione

### 5.1 Struttura del progetto

```
InterpLang/
├── InterpLang.g4            # grammatica ANTLR4
├── source/
│   ├── main.py              # entry point (sintassi → tipi → esecuzione)
│   ├── interpreter.py       # visitor principale (esecuzione)
│   ├── ts.py                # visitor del type system (controllo dei tipi)
│   ├── typesys.py           # gerarchia dei tipi e regole
│   ├── memory.py            # memoria e scoping (pila di frame)
│   ├── errors.py            # errori custom (runtime, exit)
│   ├── tree.py              # stampa dell'albero sintattico
├── programs/                # programmi di esempio (.cook)
│   ├── hello.cook  pasta.cook  biscotti.cook  fibonacci.cook  ...
│   └── test/                # 100 test generati + RISULTATI.md
├── run_ubuntu.bash          # avvio automatico (macOS/Linux)
├── run.ps1                  # avvio automatico (Windows)
├── generate.sh             # rigenera solo il parser
└── README.md                # questa relazione
```

> **Nota sul nome dei file.** Il codice usa il nome del linguaggio, **InterpLang**, mentre l'estensione dei programmi resta `.cook` (eredità della parte di dominio dedicata alle ricette).

### 5.2 L'interprete

L'interprete è un **visitor** generato da ANTLR4 (`InterpLangVisitor`). La classe `Interpreter` ne eredita e sovrascrive i metodi `visit*` per ogni produzione della grammatica. L'esecuzione avviene in **due fasi**:

1. **Analisi semantica** (`ts.py`) — una prima visita controlla tipi, dichiarazioni e assegnamenti *senza* eseguire nulla.
2. **Esecuzione** (`interpreter.py`) — se la prima fase non trova errori, una seconda visita valuta il programma.

`visitProgram` crea l'ambiente di memoria, gestisce le dichiarazioni di `ingredients:` e avvia la sezione `procedure:`.

### 5.3 Gestione dello scope

La classe `Environment` (`memory.py`) implementa la memoria come **pila di frame**:

- **push/pop** di un frame all'entrata/uscita di ogni blocco;
- **risoluzione** dei nomi dall'alto verso il basso della pila (realizza lo shadowing);
- **assegnamento** che modifica la variabile nel frame più vicino che la dichiara; se non esiste, è un errore.

### 5.4 Zucchero sintattico

Assegnamenti composti e `++`/`--` sono tradotti dal visitor nell'operazione semplice corrispondente più un aggiornamento della memoria. L'interpolazione `i"...${expr}..."` è catturata come un unico token dal lexer; l'interprete la scompone in parti di testo ed espressioni, valuta ognuna e concatena i risultati.

### 5.5 Difficoltà tecniche

- **Interpolazione** — la gestione dei token nel lexer di ANTLR ha richiesto attenzione per evitare conflitti tra i simboli `{` e `}` (usati sia per delimitare blocchi di codice, sia per delimitare espressioni nelle stringhe). La soluzione adottata è stata quella di utilizzare i token in entrambi i casi, affidandosi alla priorità delle sezioni lessicali per non avere confusione.
- **Tipizzazione statica** — ANTLR non esprime regole di tipo, quindi il controllo è una visita separata (`ts.py`) prima dell'esecuzione.
- **Scoping con shadowing** — Per modificare una variabile già esistente all'esterno, l'interprete deve risalire la pila delle memorie fino a trovarla. Se invece deve creare una nuova variabile locale, la aggiunge direttamente nel livello corrente. Poiché queste due azioni funzionano diversamente, sono state gestite con due logiche distinte.

## 6. Programmi di Esempio

Nella cartella `programs/` ci sono programmi che mostrano i costrutti principali.

### `hello.cook` — Hello world

```
procedure: {
    String nome = "Pasta al Pomodoro";
    print i"Benvenuto in InterpLang! Ricetta: ${nome}";
}
```
> **Output** — `Benvenuto in InterpLang! Ricetta: Pasta al Pomodoro`

### `pasta.cook` — ciclo e operatore ternario

Porta l'acqua a ebollizione con un `while`, poi cuoce la pasta con un `for` usando il ternario per lo stato di cottura.

### `biscotti.cook` — tipi di dominio e assegnamenti composti

Ridimensiona una ricetta (`Gram`) moltiplicando gli ingredienti con `*=`.

### `fibonacci.cook` — array e scoping

Calcola i primi N numeri di Fibonacci in un array e ne somma i valori in un blocco isolato.
> **Output** — `fib[0]=0 … fib[9]=34`, `Somma: 88`

### `sommafattoriale.cook` — algoritmo con array e condizioni

Calcola i primi N fattoriali, la loro somma e quanti sono pari.

### `errori.cook` — gestione degli errori a runtime

```
procedure: {
    Int x = 10;
    Int y = 0;
    Int z = x / y;          // Errore runtime: divisione per zero (l'esecuzione continua)

    Int[] arr = [1, 2, 3];
    print arr[0];           // 1
    print arr[5];           // Errore runtime: indice fuori dai limiti (l'esecuzione continua)

    print "Fine programma";
    exit;                   // termina il programma
    print "Non stampata";
}
```
> **Output** <br>
> Errore runtime: divisione per zero alla riga 8 <br>
> 1 <br>
> Errore runtime: indice 5 fuori dai limiti (size=3) alla riga 20 <br>
> Fine programma

## 7. Test

Qui abbiamo creato 100 tipi di **test** diversi.

Ogni test ha un **esito atteso**. I test con esito `OK` sono quelli che **passano** (il programma gira senza errori); gli altri **falliscono per costruzione** e servono a dimostrare che i controlli del linguaggio funzionano. Un test è *verificato* quando l'esito reale coincide con quello atteso: nell'ultima esecuzione **100/100** sono verificati.

### Riepilogo

| Esito atteso        | Numero | Significato                                                     |
|---------------------|:------:|----------------------------------------------------------------|
| `OK`                |   82   | il programma viene eseguito senza errori                       |
| `Errore di tipo`    |   12   | rifiutato dal type checker *prima* dell'esecuzione             |
| `Errore a runtime`  |    4   | segnalato dall'interprete *senza* bloccare il programma        |
| `Errore di sintassi`|    2   | rifiutato dal parser                                           |

### Test che passano (82) — cosa verificano

| Categoria     | N. | Cosa verifica                                                       |
|---------------|:--:|--------------------------------------------------------------------|
| `aritmetica`  | 14 | somma/sottrazione/prodotto, divisione e modulo interi, float       |
| `array`       |  8 | literal, `new T[]`, lettura/scrittura per indice, inversione       |
| `algoritmo`   |  6 | bubble sort, MCD di Euclide, numero primo, potenza, massimo        |
| `dominio`     |  6 | `Gram`/`Ml`/`Temp` e conversioni implicite verso `Int`/`Float`     |
| `confronto`   |  6 | `<`, `>`, `<=`, `>=`, `==`, `!=` su numeri e stringhe              |
| `stringa`     |  5 | stampa, interpolazione `${...}`, caratteri di escape               |
| `while`       |  4 | conteggi e condizioni composte                                     |
| `for`         |  4 | cicli con estremi costanti o calcolati                             |
| `if`          |  4 | `if`/`else`, condizioni annidate e negate                          |
| `ternario`    |  4 | scelta condizionale, anche annidata                               |
| `logica`      |  4 | `&&`, `\|\|`, `!` e combinazioni                                    |
| `precedenza`  |  4 | priorità degli operatori e parentesi                              |
| `incdec`      |  4 | `++`/`--` come statement e dentro le espressioni                   |
| `composto`    |  4 | `+=`, `-=`, `*=`, `/=`                                             |
| `scoping`     |  3 | visibilità dei blocchi e shadowing                                 |
| `exit`        |  2 | uscita anticipata dal programma                                    |

### Test che falliscono per costruzione (18) — perché

**Errori di tipo** (bloccati dal type checker, il programma non parte):

| File            | Perché fallisce                                          |
|-----------------|---------------------------------------------------------|
| `test_001.cook` | variabile usata ma mai dichiarata                       |
| `test_002.cook` | assegnare una `String` a un `Int`                       |
| `test_003.cook` | assegnare un `Float` a un `Int` (restringimento vietato)|
| `test_004.cook` | sommare un `Int` e un `Bool`                            |
| `test_005.cook` | condizione dell'`if` non booleana                       |
| `test_006.cook` | condizione del `while` non booleana                     |
| `test_007.cook` | `&&` fra due interi invece che booleani                 |
| `test_008.cook` | confronto `<` fra `String` e `Int`                      |
| `test_009.cook` | array con elementi di tipi diversi                      |
| `test_010.cook` | indice dell'array non intero                            |
| `test_011.cook` | estremi del `for` non interi                            |
| `test_012.cook` | `!` applicato a un numero                               |

**Errori a runtime** (il programma parte, l'interprete segnala e continua):

| File            | Perché fallisce                                  |
|-----------------|--------------------------------------------------|
| `test_013.cook` | divisione per zero                               |
| `test_014.cook` | modulo per zero                                  |
| `test_015.cook` | indice oltre la fine dell'array                  |
| `test_016.cook` | indice negativo dell'array                       |

**Errori di sintassi** (bloccati dal parser):

| File            | Perché fallisce                          |
|-----------------|------------------------------------------|
| `test_017.cook` | manca il punto e virgola                  |
| `test_018.cook` | manca la graffa di chiusura del blocco    |

> La tabella riga per riga di tutti i 100 test (con esito atteso, esito ottenuto e verifica) è in [`programs/test/RISULTATI.md`](programs/test/RISULTATI.md), rigenerata a ogni esecuzione dello script.
