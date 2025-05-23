
package analizador_lexico;
import analizador_lexico.Token.Tipos;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador_sintactico {
    int index = 0;
    public Analizador_sintactico(){
        
        }
    
   public boolean parseAsignacion(ArrayList<Token> contenido) {
    if (match(Token.Tipos.IDENTIFICADOR,contenido)) {
       if (match(Token.Tipos.SIMBOLO_PUNTO,contenido)) {
          if (match(Token.Tipos.METODO,contenido)) {
            while(match(Token.Tipos.ESPACIO,contenido)){}
                if (match(Token.Tipos.SIMBOLO_IGUAL,contenido)) {
                    while(match(Token.Tipos.ESPACIO,contenido)){}
                    if (match(Token.Tipos.NUMERO,contenido)) {
                         while(match(Token.Tipos.ESPACIO,contenido)){}
                        if (index >= contenido.size()) return true;
                        }
                }
        }else if(match(Token.Tipos.ACCION,contenido)){
             while(match(Token.Tipos.ESPACIO,contenido)){}
                    if (index >= contenido.size()) return true;
        }
       }
     }else if(match(Token.Tipos.CLASE,contenido)){
         if(match(Token.Tipos.ESPACIO,contenido)){
                    while(match(Token.Tipos.ESPACIO,contenido)){}
         if (match(Token.Tipos.IDENTIFICADOR,contenido)) {
                 while(match(Token.Tipos.ESPACIO,contenido)){}
                if (index >= contenido.size()) return true;
                   }
         }
     }
       return false;    }
   
   public boolean match(Token.Tipos tipoEsperado,ArrayList<Token> contenido) {
          if(tipoEsperado==Token.Tipos.METODO){
               
           if (index< contenido.size() && contenido.get(index).getTipo()== tipoEsperado) {
            if(contenido.get(index).getValor().equals("velocidad")){
                int tempIndex = index + 1;   
                while (tempIndex < contenido.size() && contenido.get(tempIndex).getTipo() == Token.Tipos.ESPACIO) {
                            tempIndex++;
                        }
                if(tempIndex < contenido.size() && contenido.get(tempIndex).getTipo()==Token.Tipos.SIMBOLO_IGUAL){
                    tempIndex++;
                 while (tempIndex < contenido.size() && contenido.get(tempIndex).getTipo() == Token.Tipos.ESPACIO) {
                            tempIndex++;
                        }
                        if(tempIndex < contenido.size() && contenido.get(tempIndex).getTipo()==Token.Tipos.NUMERO){

                                Pattern patron = Pattern.compile("[0-9]");
                                Matcher m = patron.matcher(contenido.get(tempIndex).getValor());
                                        if(m.matches()) 
                                        {
                                        index++;
                                        return true;
                                        }else return false;
                        }else return false;
                 }else return false;

            }   else if (index < contenido.size() && contenido.get(index).getTipo()== tipoEsperado) {
                index++;
                 return true;
                 }    

   }   
}else if (index < contenido.size() && contenido.get(index).getTipo()== tipoEsperado) {
   index++;
    return true;
    }
return false;

   }
    
}
