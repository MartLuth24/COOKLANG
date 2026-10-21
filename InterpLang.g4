grammar InterpLang;

/*
   InterpLang - grammatica ANTLR4
   Un linguaggio imperativo a tipizzazione statica, general-purpose, con
   tipi di dominio dedicati alle unità di misura (Gram, Ml, Temp).
 */

//  PARSER

// Un programma ha una sezione ingredienti (opzionale) e una sezione procedura.
program
    : ingredientSection? procedureSection EOF
    ;

// Dichiarazioni globali (ingredienti), visibili nell'intero programma.
ingredientSection
    : INGREDIENTS ':' varDecl*
    ;

// Corpo eseguibile del programma.
procedureSection
    : PROCEDURE ':' block
    ;

// Un blocco introduce un nuovo scope lessicale.
block
    : '{' statement* '}'
    ;

statement
    : varDecl                                       # declStatement
    | assign ';'                                    # assignStatement
    | incDec ';'                                    # incDecStatement
    | PRINT expr ';'                                # printStatement
    | IF '(' expr ')' block (ELSE block)?           # ifStatement
    | WHILE '(' expr ')' block                      # whileStatement
    | FOR ID FROM expr TO expr block                # forStatement
    | EXIT ';'                                      # exitStatement
    | block                                         # blockStatement
    ;

varDecl
    : varType ID ('=' expr)? ';'
    ;

assign
    : ID assignOp expr                  # varAssign
    | ID '[' expr ']' assignOp expr     # arrayAssign
    ;

assignOp
    : '=' | '+=' | '-=' | '*=' | '/='
    ;

incDec
    : op=('++'|'--') ID     # preIncDec
    | ID op=('++'|'--')     # postIncDec
    ;

varType
    : baseType ('[' ']')?
    ;

baseType
    : INT_T | FLOAT_T | BOOL_T | STRING_T | GRAM_T | ML_T | TEMP_T
    ;

// Espressioni: gli operatori sono elencati dal piu' alto al piu' basso
// livello di precedenza (ANTLR usa l'ordine delle alternative).
expr
    : '(' expr ')'                                  # parenExpr
    | incDec                                        # incDecExpr
    | expr '[' expr ']'                             # indexExpr
    | op=('!'|'-') expr                             # unaryExpr
    | expr op=('*'|'/'|'%') expr                    # mulExpr
    | expr op=('+'|'-') expr                        # addExpr
    | expr op=('=='|'!='|'<'|'>'|'<='|'>=') expr    # relExpr
    | expr op=('&&'|'||') expr                      # logicExpr
    | <assoc=right> expr '?' expr ':' expr          # ternaryExpr
    | NEW baseType '[' expr ']'                     # newArrayExpr
    | '[' (expr (',' expr)*)? ']'                   # arrayLiteralExpr
    | INT                                           # intExpr
    | FLOAT                                         # floatExpr
    | (TRUE | FALSE)                                # boolExpr
    | STRING                                        # stringExpr
    | ISTRING                                       # interpExpr
    | ID                                            # idExpr
    ;

// LEXER

// Parole chiave del linguaggio
INGREDIENTS : 'ingredients' ;
PROCEDURE   : 'procedure' ;
PRINT       : 'print' ;
IF          : 'if' ;
ELSE        : 'else' ;
WHILE       : 'while' ;
FOR         : 'for' ;
FROM        : 'from' ;
TO          : 'to' ;
EXIT        : 'exit' ;
NEW         : 'new' ;
TRUE        : 'true' ;
FALSE       : 'false' ;

// Parole chiave dei tipi
INT_T    : 'Int' ;
FLOAT_T  : 'Float' ;
BOOL_T   : 'Bool' ;
STRING_T : 'String' ;
GRAM_T   : 'Gram' ;
ML_T     : 'Ml' ;
TEMP_T   : 'Temp' ;

// Letterali
FLOAT   : [0-9]+ '.' [0-9]+ ;
INT     : [0-9]+ ;
STRING  : '"' ( '\\' . | ~["\\\r\n] )* '"' ;
// Stringa interpolata: cattura l'intero i"...${...}..." come singolo token,
// le espressioni ${...} vengono valutate dall'interprete.
ISTRING : 'i"' ( '\\' . | ~["\\] )* '"' ;

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;

// Commenti e spazi bianchi (ignorati)
LINE_COMMENT  : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;
WS            : [ \t\r\n]+ -> skip ;
