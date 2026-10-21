# Risultati dei test di InterpLang

Test totali: **100** verificati correttamente: **100/100**.

## Riepilogo per esito atteso

| Esito atteso | Numero | Significato |
|---|---|---|
| OK | 82 | il programma viene eseguito senza errori |
| Errore di tipo | 12 | rifiutato dal type checker prima dell'esecuzione |
| Errore a runtime | 4 | segnalato dall'interprete senza bloccare il programma |
| Errore di sintassi | 2 | rifiutato dal parser |

La colonna **Verifica** vale ✔ quando l'esito reale coincide con quello atteso.

## Tabella completa

| # | File | Categoria | Esito atteso | Esito ottenuto | Verifica | Perché |
|---|---|---|---|---|:---:|---|
| 1 | `test_019.cook` | algoritmo | OK | OK | ✔ | bubble sort di un array di interi |
| 2 | `test_020.cook` | algoritmo | OK | OK | ✔ | massimo comune divisore (algoritmo di Euclide) |
| 3 | `test_021.cook` | algoritmo | OK | OK | ✔ | verifica se un numero è primo |
| 4 | `test_022.cook` | algoritmo | OK | OK | ✔ | potenza base^esponente con un ciclo |
| 5 | `test_023.cook` | algoritmo | OK | OK | ✔ | massimo di un array |
| 6 | `test_024.cook` | algoritmo | OK | OK | ✔ | somma dei primi 100 numeri naturali |
| 7 | `test_025.cook` | dominio | OK | OK | ✔ | Gram sottotipo di Int: farina totale in grammi |
| 8 | `test_026.cook` | dominio | OK | OK | ✔ | Ml usato in un'espressione con Int |
| 9 | `test_027.cook` | dominio | OK | OK | ✔ | Temp sottotipo di Float |
| 10 | `test_028.cook` | dominio | OK | OK | ✔ | conversione implicita Gram -> Int in una somma |
| 11 | `test_029.cook` | dominio | OK | OK | ✔ | ridimensionamento ricetta con assegnamento composto |
| 12 | `test_030.cook` | dominio | OK | OK | ✔ | Temp media tra due temperature (Float) |
| 13 | `test_031.cook` | array | OK | OK | ✔ | creazione e lettura di un array literal |
| 14 | `test_032.cook` | array | OK | OK | ✔ | modifica di un elemento tramite indice |
| 15 | `test_033.cook` | array | OK | OK | ✔ | somma di tutti gli elementi di un array |
| 16 | `test_034.cook` | array | OK | OK | ✔ | inversione di un array sul posto |
| 17 | `test_035.cook` | array | OK | OK | ✔ | array di stringhe |
| 18 | `test_036.cook` | array | OK | OK | ✔ | array allocato con new e riempito in un ciclo |
| 19 | `test_037.cook` | array | OK | OK | ✔ | new Int[] inizializza tutto a zero |
| 20 | `test_038.cook` | array | OK | OK | ✔ | new Bool[] inizializza tutto a false |
| 21 | `test_039.cook` | scoping | OK | OK | ✔ | una variabile locale non è visibile fuori dal blocco |
| 22 | `test_040.cook` | scoping | OK | OK | ✔ | shadowing: la variabile interna nasconde quella esterna |
| 23 | `test_041.cook` | scoping | OK | OK | ✔ | un blocco interno modifica una variabile esterna |
| 24 | `test_042.cook` | for | OK | OK | ✔ | ciclo for che stampa i valori da 1 a 5 |
| 25 | `test_043.cook` | for | OK | OK | ✔ | tabellina del 7 con un for |
| 26 | `test_044.cook` | for | OK | OK | ✔ | prodotto (fattoriale) con un for |
| 27 | `test_045.cook` | for | OK | OK | ✔ | for con estremi calcolati da espressioni |
| 28 | `test_046.cook` | while | OK | OK | ✔ | conteggio con while |
| 29 | `test_047.cook` | while | OK | OK | ✔ | somma delle cifre? no: dimezzamento fino a 1 |
| 30 | `test_048.cook` | while | OK | OK | ✔ | while con condizione composta (&&) |
| 31 | `test_049.cook` | while | OK | OK | ✔ | somma dei numeri pari fino a 20 |
| 32 | `test_050.cook` | if | OK | OK | ✔ | if con ramo else |
| 33 | `test_051.cook` | if | OK | OK | ✔ | if senza else |
| 34 | `test_052.cook` | if | OK | OK | ✔ | if annidati (classificazione di un voto) |
| 35 | `test_053.cook` | if | OK | OK | ✔ | if con condizione booleana negata |
| 36 | `test_054.cook` | ternario | OK | OK | ✔ | ternario per scegliere il massimo tra due numeri |
| 37 | `test_055.cook` | ternario | OK | OK | ✔ | ternario che restituisce una String |
| 38 | `test_056.cook` | ternario | OK | OK | ✔ | ternario annidato |
| 39 | `test_057.cook` | ternario | OK | OK | ✔ | ternario con rami numerici (Int e Float -> Float) |
| 40 | `test_058.cook` | logica | OK | OK | ✔ | AND logico |
| 41 | `test_059.cook` | logica | OK | OK | ✔ | OR logico |
| 42 | `test_060.cook` | logica | OK | OK | ✔ | negazione logica |
| 43 | `test_061.cook` | logica | OK | OK | ✔ | espressione logica combinata |
| 44 | `test_062.cook` | confronto | OK | OK | ✔ | confronto minore |
| 45 | `test_063.cook` | confronto | OK | OK | ✔ | confronto maggiore-uguale |
| 46 | `test_064.cook` | confronto | OK | OK | ✔ | uguaglianza tra interi |
| 47 | `test_065.cook` | confronto | OK | OK | ✔ | diverso tra interi |
| 48 | `test_066.cook` | confronto | OK | OK | ✔ | uguaglianza tra stringhe |
| 49 | `test_067.cook` | confronto | OK | OK | ✔ | confronto tra float |
| 50 | `test_068.cook` | stringa | OK | OK | ✔ | stampa di una stringa semplice |
| 51 | `test_069.cook` | stringa | OK | OK | ✔ | interpolazione di un'espressione dentro la stringa |
| 52 | `test_070.cook` | stringa | OK | OK | ✔ | caratteri di escape \n e \t |
| 53 | `test_071.cook` | stringa | OK | OK | ✔ | interpolazione con accesso a un array |
| 54 | `test_072.cook` | stringa | OK | OK | ✔ | più interpolazioni nella stessa stringa |
| 55 | `test_073.cook` | incdec | OK | OK | ✔ | pre-incremento |
| 56 | `test_074.cook` | incdec | OK | OK | ✔ | post-decremento |
| 57 | `test_075.cook` | incdec | OK | OK | ✔ | incremento dentro un'espressione (pre) |
| 58 | `test_076.cook` | incdec | OK | OK | ✔ | post-incremento dentro un'espressione |
| 59 | `test_077.cook` | composto | OK | OK | ✔ | somma composta += |
| 60 | `test_078.cook` | composto | OK | OK | ✔ | sottrazione composta -= |
| 61 | `test_079.cook` | composto | OK | OK | ✔ | moltiplicazione composta *= |
| 62 | `test_080.cook` | composto | OK | OK | ✔ | divisione composta /= |
| 63 | `test_081.cook` | exit | OK | OK | ✔ | exit interrompe subito il programma |
| 64 | `test_082.cook` | exit | OK | OK | ✔ | exit dentro un ciclo |
| 65 | `test_083.cook` | precedenza | OK | OK | ✔ | la moltiplicazione ha priorità sulla somma |
| 66 | `test_084.cook` | precedenza | OK | OK | ✔ | le parentesi cambiano l'ordine di valutazione |
| 67 | `test_085.cook` | precedenza | OK | OK | ✔ | sottrazione associativa a sinistra |
| 68 | `test_086.cook` | precedenza | OK | OK | ✔ | somma di due prodotti |
| 69 | `test_087.cook` | aritmetica | OK | OK | ✔ | divisione intera tronca verso zero |
| 70 | `test_088.cook` | aritmetica | OK | OK | ✔ | resto della divisione (modulo) |
| 71 | `test_089.cook` | aritmetica | OK | OK | ✔ | divisione con resto |
| 72 | `test_090.cook` | aritmetica | OK | OK | ✔ | modulo con numeri più grandi |
| 73 | `test_091.cook` | aritmetica | OK | OK | ✔ | somma tra float |
| 74 | `test_092.cook` | aritmetica | OK | OK | ✔ | divisione tra float |
| 75 | `test_093.cook` | aritmetica | OK | OK | ✔ | media di tre float |
| 76 | `test_094.cook` | aritmetica | OK | OK | ✔ | Int diviso Float dà un Float |
| 77 | `test_095.cook` | aritmetica | OK | OK | ✔ | numero negativo (negazione unaria) |
| 78 | `test_096.cook` | aritmetica | OK | OK | ✔ | 3 + 4 = 7 |
| 79 | `test_097.cook` | aritmetica | OK | OK | ✔ | 7 + 2 = 9 |
| 80 | `test_098.cook` | aritmetica | OK | OK | ✔ | 9 + 5 = 14 |
| 81 | `test_099.cook` | aritmetica | OK | OK | ✔ | 12 + 8 = 20 |
| 82 | `test_100.cook` | aritmetica | OK | OK | ✔ | 6 + 6 = 12 |
| 83 | `test_001.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | la variabile 'x' non è mai stata dichiarata |
| 84 | `test_002.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | assegno una String a una variabile Int |
| 85 | `test_003.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | assegno un Float a un Int (restringimento vietato) |
| 86 | `test_004.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | sommo un Int e un Bool: operazione non valida |
| 87 | `test_005.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | la condizione dell'if non è booleana (è un Int) |
| 88 | `test_006.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | la condizione del while non è booleana (è un Int) |
| 89 | `test_007.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | uso && tra due interi invece che tra booleani |
| 90 | `test_008.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | confronto < tra una String e un Int |
| 91 | `test_009.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | array con elementi di tipi diversi (Int e Bool) |
| 92 | `test_010.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | indice dell'array non intero (è un Bool) |
| 93 | `test_011.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | gli estremi del for devono essere interi, non Float |
| 94 | `test_012.cook` | errore-tipo | Errore di tipo | Errore di tipo | ✔ | negazione logica ! applicata a un numero |
| 95 | `test_013.cook` | errore-runtime | Errore a runtime | Errore a runtime | ✔ | divisione per zero durante l'esecuzione |
| 96 | `test_014.cook` | errore-runtime | Errore a runtime | Errore a runtime | ✔ | modulo per zero durante l'esecuzione |
| 97 | `test_015.cook` | errore-runtime | Errore a runtime | Errore a runtime | ✔ | accesso a un indice oltre la fine dell'array |
| 98 | `test_016.cook` | errore-runtime | Errore a runtime | Errore a runtime | ✔ | accesso a un indice negativo dell'array |
| 99 | `test_017.cook` | errore-sintassi | Errore di sintassi | Errore di sintassi | ✔ | manca il punto e virgola dopo la dichiarazione |
| 100 | `test_018.cook` | errore-sintassi | Errore di sintassi | Errore di sintassi | ✔ | manca la graffa di chiusura del blocco |
