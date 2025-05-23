package a_sintactico_robot;

import java_cup.runtime.*;
import a_sintactico_robot.sym;

%%

%class Lexer
%cup
%line
%column
%unicode

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
Numero = [0-9]+
Identificador = [a-zA-Z][a-zA-Z0-9]*

%%

<YYINITIAL> {
    "Robot"         { return symbol(sym.ROBOT); }
    "base"|"cuerpo"|"garra"|"velocidad" { return symbol(sym.METODO, yytext()); }
"iniciar"|"finalizar"|"cerrarGarra"|"abrirGarra"|"repetir" { 
    return symbol(sym.ACCION, yytext()); 
}
    {Identificador} { return symbol(sym.IDENTIFICADOR, yytext()); }
    {Numero}        { return symbol(sym.NUMERO, Integer.valueOf(yytext())); }
    "="             { return symbol(sym.IGUAL); }
    "."             { return symbol(sym.PUNTO); }
    {WhiteSpace}    { /* ignore */ }
    [^]             { return symbol(sym.ERROR, "Símbolo no válido: " + yytext()); }
}