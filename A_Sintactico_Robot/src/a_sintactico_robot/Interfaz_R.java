package a_sintactico_robot;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.StringReader;
import java_cup.runtime.Symbol;
import javax.swing.text.*;
import javax.swing.event.*;

public class Interfaz_R extends javax.swing.JFrame {
    private JTextPane entrada;
    private JTextArea salida;
    private JScrollPane scrollEntrada;
    private JPanel lineNumberPanel;
    private JButton btnAnalizar, btnLimpiar, btnEjemplos;
    private JCheckBox chkMostrarTokens;

    public Interfaz_R() {
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        setTitle("Analizador Sintáctico con JCup");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de colores
        Color colorFondo = new Color(245, 248, 250);
        Color colorPanel = Color.WHITE;
        Color colorBoton = new Color(0, 121, 211);
        Color colorBotonHover = new Color(0, 100, 180);
        Color colorTexto = new Color(44, 62, 80);
        Color colorBorde = new Color(200, 215, 227);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(colorFondo);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título
        JLabel tituloLabel = new JLabel("ANALIZADOR SINTÁCTICO CON JCup", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        tituloLabel.setForeground(colorTexto);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 15, 0));
        panelCentral.setBackground(colorFondo);

        // Panel de entrada
        JPanel panelEntrada = crearPanelEntrada(colorPanel, colorBorde, colorTexto);
        panelCentral.add(panelEntrada);

        // Panel de salida
        JPanel panelSalida = crearPanelSalida(colorPanel, colorBorde, colorTexto);
        panelCentral.add(panelSalida);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = crearPanelBotones(colorFondo, colorBoton, colorBotonHover);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearPanelEntrada(Color colorPanel, Color colorBorde, Color colorTexto) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorPanel);
        panel.setBorder(createTitledBorder("Código a Analizar", colorBorde, colorTexto));

        lineNumberPanel = new JPanel();
        lineNumberPanel.setBackground(new Color(240, 240, 240));
        lineNumberPanel.setLayout(new GridBagLayout());

        entrada = new JTextPane();
        entrada.setFont(new Font("Consolas", Font.PLAIN, 14));
        
        // Configurar estilo para mantener alineación
        StyleContext context = new StyleContext();
        Style style = context.addStyle("ConstantWidth", null);
        StyleConstants.setFontFamily(style, "Consolas");
        StyleConstants.setFontSize(style, 14);
        entrada.setDocument(new DefaultStyledDocument(context));

        scrollEntrada = new JScrollPane(entrada);
        scrollEntrada.setRowHeaderView(lineNumberPanel);
        scrollEntrada.setBorder(null);

        entrada.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateLineNumbers(); }
            public void removeUpdate(DocumentEvent e) { updateLineNumbers(); }
            public void insertUpdate(DocumentEvent e) { updateLineNumbers(); }
        });

        panel.add(scrollEntrada, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelSalida(Color colorPanel, Color colorBorde, Color colorTexto) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorPanel);
        panel.setBorder(createTitledBorder("Resultados del Análisis", colorBorde, colorTexto));

        salida = new JTextArea();
        salida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        salida.setEditable(false);
        salida.setLineWrap(true);
        salida.setWrapStyleWord(true);
        salida.setMargin(new Insets(10, 15, 10, 15));

        JScrollPane scrollSalida = new JScrollPane(salida);
        scrollSalida.setBorder(null);

        panel.add(scrollSalida, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelBotones(Color colorFondo, Color colorBoton, Color colorBotonHover) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(colorFondo);

        btnAnalizar = crearBoton("Analizar Sintaxis", colorBoton, colorBotonHover, this::analizarCodigo);
        btnLimpiar = crearBoton("Limpiar Todo", new Color(231, 76, 60), new Color(192, 57, 43), e -> limpiarTodo());

        chkMostrarTokens = new JCheckBox("Mostrar tokens");
        chkMostrarTokens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkMostrarTokens.setBackground(colorFondo);

        panel.add(btnAnalizar);
        panel.add(btnLimpiar);
        panel.add(chkMostrarTokens);

        return panel;
    }

    private JButton crearBoton(String texto, Color normal, Color hover, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(normal);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.addActionListener(accion);

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { boton.setBackground(hover); }
            public void mouseExited(MouseEvent e) { boton.setBackground(normal); }
        });

        return boton;
    }

    private TitledBorder createTitledBorder(String title, Color borderColor, Color textColor) {
        Border border = BorderFactory.createLineBorder(borderColor, 2);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        titledBorder.setTitleColor(textColor);
        return titledBorder;
    }

private void updateLineNumbers() {
    String text = entrada.getText();
    String[] lines = text.split("\n");

    lineNumberPanel.removeAll();
    lineNumberPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.gridx = 0;
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Ajustar el ancho según la cantidad de dígitos de líneas
    int maxDigits = String.valueOf(lines.length).length();
    int width = maxDigits * 10 + 20;
    lineNumberPanel.setPreferredSize(new Dimension(width, 0));

    for (int i = 0; i < lines.length; i++) {
        JLabel label = new JLabel(String.format("%" + maxDigits + "d", i + 1));
        label.setFont(new Font("Consolas", Font.PLAIN, 14));
        label.setForeground(new Color(100, 100, 100));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridy = i;
        lineNumberPanel.add(label, gbc);
    }

    lineNumberPanel.revalidate();
    lineNumberPanel.repaint();
}


    private void analizarCodigo(ActionEvent e) {
        String codigo = entrada.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje("Por favor ingrese código para analizar", Color.ORANGE);
            return;
        }

        try {
            Lexer lexer = new Lexer(new StringReader(codigo));
            Parser parser = new Parser(lexer);
            parser.parse();

            // Validación adicional para comandos incompletos
            String[] lineas = codigo.split("\n");
            StringBuilder erroresAdicionales = new StringBuilder();
            
            for (int i = 0; i < lineas.length; i++) {
                String linea = lineas[i].trim();
                if (linea.matches("[a-zA-Z0-9]+\\.[a-zA-Z]+$") && 
                    !linea.matches(".*(iniciar|finalizar)$")) {
                    erroresAdicionales.append("Error sintáctico en línea ").append(i+1)
                                     .append(": Comando incompleto (falta valor)\n");
                }
                if (linea.matches("[a-zA-Z0-9]+-[a-zA-Z0-9]+.*")) {
                    erroresAdicionales.append("Error sintáctico en línea ").append(i+1)
                                     .append(": Use punto (.) en lugar de guión (-)\n");
                }
            }

            if (parser.getErrores().isEmpty() && erroresAdicionales.length() == 0) {
                mostrarMensaje("Análisis sintáctico CORRECTO", new Color(46, 204, 113));
            } else {
                String todosErrores = parser.getErrores() + erroresAdicionales.toString();
                salida.setText(todosErrores);
                salida.setForeground(new Color(231, 76, 60));
            }

            if (chkMostrarTokens.isSelected()) {
                mostrarTokens(codigo);
            }
        } catch (Exception ex) {
            mostrarMensaje("Error durante el análisis: " + ex.getMessage(), Color.RED);
            ex.printStackTrace();
        }
    }

    private void mostrarTokens(String codigo) throws Exception {
        Lexer lexer = new Lexer(new StringReader(codigo));
        StringBuilder tokens = new StringBuilder("\nTokens reconocidos:\n");
        Symbol token;
        
        while ((token = lexer.next_token()).sym != sym.EOF) {
            tokens.append(String.format("Línea %-3d Col %-3d: %-15s %s\n",
                token.left, token.right,
                sym.terminalNames[token.sym],
                token.value == null ? "" : token.value));
        }
        
        salida.append(tokens.toString());
    }

    private void limpiarTodo() {
        entrada.setText("");
        salida.setText("");
        updateLineNumbers();
        salida.setForeground(Color.BLACK);
    }

    private void mostrarMensaje(String mensaje, Color color) {
        salida.setText(mensaje);
        salida.setForeground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new Interfaz_R().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al iniciar la aplicación: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
