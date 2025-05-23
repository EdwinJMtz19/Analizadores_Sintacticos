package a_sintactico_robot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class GeneradorAnalizadores {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("Directorio actual: " + projectPath);
        
        String flexPath = "src/a_sintactico_robot/flex/lexer.flex";
        String cupPath = "src/a_sintactico_robot/cup/parser.cup";
        String srcPath = "src/a_sintactico_robot/";
        
        if (!verificarArchivo(flexPath)) {
            System.err.println("ERROR: No se encuentra " + flexPath);
            return;
        }
        
        if (!verificarArchivo(cupPath)) {
            System.err.println("ERROR: No se encuentra " + cupPath);
            return;
        }
        
        generarLexer(projectPath + "/" + flexPath);
        generarParser(projectPath + "/" + cupPath, projectPath + "/" + srcPath);
        
        System.out.println("Analizadores generados exitosamente!");
    }

    private static boolean verificarArchivo(String ruta) {
        File f = new File(ruta);
        if (!f.exists()) {
            System.err.println("Buscando en: " + f.getAbsolutePath());
            return false;
        }
        return true;
    }

    public static void generarLexer(String path) {
        try {
            System.out.println("\nGenerando analizador léxico desde: " + path);
            File archivoFlex = new File(path);
            
            if (!archivoFlex.exists()) {
                System.err.println("Archivo .flex no encontrado en: " + archivoFlex.getAbsolutePath());
                return;
            }
            jflex.Main.generate(new String[]{archivoFlex.getAbsolutePath()});
            Path lexerJava = Paths.get("Lexer.java");
            if (Files.exists(lexerJava)) {
                Path destino = Paths.get(archivoFlex.getParentFile().getParent(), "Lexer.java");
                Files.move(lexerJava, destino, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Lexer.java movido a: " + destino);
            }
        } catch (Exception e) {
            System.err.println("Error al generar lexer:");
            e.printStackTrace();
        }
    }

    public static void generarParser(String cupPath, String destPath) {
        try {
            System.out.println("\nGenerando analizador sintáctico desde: " + cupPath);
            File archivoCup = new File(cupPath);
            
            if (!archivoCup.exists()) {
                System.err.println("Archivo .cup no encontrado en: " + archivoCup.getAbsolutePath());
                return;
            }
            
            String[] opciones = {
                "-package", "a_sintactico_robot",
                "-parser", "Parser",
                "-symbols", "sym",
                archivoCup.getAbsolutePath()
            };
            java_cup.Main.main(opciones);
            moverArchivo("Parser.java", destPath);
            moverArchivo("sym.java", destPath);
            
        } catch (Exception e) {
            System.err.println("Error al generar parser:");
            e.printStackTrace();
        }
    }

    private static void moverArchivo(String fileName, String destPath) throws IOException {
        Path origen = Paths.get(fileName);
        if (Files.exists(origen)) {
            Path destino = Paths.get(destPath, fileName);
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(fileName + " movido a: " + destino);
        } else {
            System.err.println("No se encontró el archivo generado: " + fileName);
        }
    }
}