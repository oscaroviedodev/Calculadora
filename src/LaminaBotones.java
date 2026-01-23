import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.List;
import javax.swing.BorderFactory;

public class LaminaBotones extends JPanel{
    
    JButton uno = new JButton("1");
    JButton dos = new JButton("2");
    JButton tres = new JButton("3");
    JButton suma = new JButton("+");
    JButton cuatro = new JButton("4");
    JButton cinco = new JButton("5");
    JButton seis = new JButton("6");
    JButton resta = new JButton("-");
    JButton siete = new JButton("7");
    JButton ocho = new JButton("8");
    JButton nueve = new JButton("9");
    JButton multiplicacion = new JButton("x");
    JButton punto = new JButton(".");
    JButton cero = new JButton("0");
    JButton igual = new JButton("=");
    JButton division = new JButton("/");
    JButton limpiar = new JButton("C");
    JButton eliminarCaracter = new JButton("←");
    
    List<JButton> listaBotones = List.of(
        uno, dos, tres, suma, cuatro, cinco, seis, resta, siete, ocho,
        nueve, multiplicacion, punto, cero, igual, division, limpiar, eliminarCaracter            
    );
    
    LaminaPantalla laminaPantalla = new LaminaPantalla();
    LogicaNumeros logicaNumeros = new LogicaNumeros();
    logicaOperdador logicaOperdador = new logicaOperdador();
    double numeroEscuchado;
    double numeroCapturado;
    int operacion = 0;
    double resultado = 0;
    boolean estadoIgual = false;    
    
    public LaminaBotones() {
        
        this.setLayout(new GridLayout(5, 4, 5, 5));
        this.setBackground(new Color(44, 45, 45));
        this.setBorder(BorderFactory.createLineBorder(new Color(44, 45, 45), 5));
        
        establecerCaracteristicasBotones(listaBotones);
        
        uno.addActionListener(logicaNumeros);
        dos.addActionListener(logicaNumeros);
        tres.addActionListener(logicaNumeros);
        cuatro.addActionListener(logicaNumeros);
        cinco.addActionListener(logicaNumeros);
        seis.addActionListener(logicaNumeros);
        siete.addActionListener(logicaNumeros);
        ocho.addActionListener(logicaNumeros);
        nueve.addActionListener(logicaNumeros);
        cero.addActionListener(logicaNumeros);
        
        suma.addActionListener(logicaOperdador);
        resta.addActionListener(logicaOperdador);
        multiplicacion.addActionListener(logicaOperdador);
        division.addActionListener(logicaOperdador);
        
        // Operacion con boton igual
        igual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Formatea resultado si es entero o decimal
                if ((resultado % 2) == 0) {
                    int resultadoEntero = (int)resultado;
                    laminaPantalla.pantalla.setText(String.valueOf(resultadoEntero));
                    
                } else {
                    laminaPantalla.pantalla.setText(String.valueOf(resultado));
                }
                  
                estadoIgual = true;
                operacion = 0;
            }
        });
        
        // Operacion con boton limpiar
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                laminaPantalla.pantalla.setText("0");
                numeroEscuchado = 0;
                numeroCapturado = 0;
                operacion = 0;
                resultado = 0;                
            }
        });
        
        // Operacion con boton punto
        punto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Quita el cero de la pantalla
                if (laminaPantalla.pantalla.getText().equals("0") || laminaPantalla.pantalla.getText().equals("Error")) {
                    laminaPantalla.pantalla.setText("");                    
                }
                
                boolean estado = false;
                String numeroPantalla = laminaPantalla.pantalla.getText();
                
                // Verifica si el . se encuentra en el numero de la pantalla
                for(int i = 0; i < numeroPantalla.length(); i++) {
                    if (numeroPantalla.charAt(i) == '.') {
                        estado = true;
                    }
                }
                
                if (estado) {
                    laminaPantalla.pantalla.setText(laminaPantalla.pantalla.getText() + "");
                } else {
                    laminaPantalla.pantalla.setText(laminaPantalla.pantalla.getText() + ".");
                }
            }
        });
        
        // operacion con boton eliminarCaracter        
        eliminarCaracter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoCapturado = laminaPantalla.pantalla.getText();
                double numeroOptenido = 0;
                
                if (! laminaPantalla.pantalla.getText().equals("0")) { 
                    
                    if(textoCapturado.length() == 1 || estadoIgual == true) {
                       laminaPantalla.pantalla.setText("0");
                       numeroEscuchado = 0;
                       System.out.println("Numero despues de eliminar caracter: " + numeroOptenido);
                       
                    } else {
                        laminaPantalla.pantalla.setText(textoCapturado.substring(0, textoCapturado.length() - 1));
                        numeroOptenido = Double.parseDouble(laminaPantalla.pantalla.getText());
                        numeroEscuchado = numeroOptenido;
                        obtenerResultado(operacion);
                        System.out.println("Numero despues de eliminar caracter: " + numeroOptenido);
                    }
                }
            }
        });        
    }   
    
    // Operacion con botones numericos
    public class LogicaNumeros implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Quitar el 0 de la pantalla
            if (laminaPantalla.pantalla.getText().equals("0") || laminaPantalla.pantalla.getText().equals("Error") || estadoIgual == true) {
                laminaPantalla.pantalla.setText("");
                
                // para poder realizar operaciones seguidas ej: 5+5=10 4+4=8 y no moleste a las operaciones consequtivas 5+5=10+5=15
                if (estadoIgual == true) {                    
                    numeroCapturado = 0;
                    estadoIgual = false; 
                }               
            }
            
            // Establecer numero en pantalla
            laminaPantalla.pantalla.setText(laminaPantalla.pantalla.getText() + e.getActionCommand());
            
            // Capturar numero pantalla
            numeroEscuchado = Double.parseDouble(laminaPantalla.pantalla.getText());
            System.out.println("numero escuchado: " + numeroEscuchado);
 
            obtenerResultado(operacion);
        }
    }
    
    // Metodo operaciones en segundo plano
    public void obtenerResultado(int op) {
        switch (operacion) {
                
            case 1:
                resultado = numeroCapturado + numeroEscuchado;
                System.out.println("Resultado suma: " + resultado);
                break;

            case 2:
                resultado = numeroCapturado - numeroEscuchado;
                System.out.println("resultado resta: " + resultado);
                break;

            case 3:
                resultado = numeroCapturado * numeroEscuchado;
                System.out.println("resultado multiplicacion: " + resultado);
                break;

            case 4:
                if (numeroEscuchado != 0) {

                    // Formateado el resultado para que aparezcan 8 decimales
                    double resultadoSinFormatear = numeroCapturado / numeroEscuchado;
                    String resultadoFormateado = String.format(Locale.US, "%.8f", resultadoSinFormatear);

                    resultado = Double.parseDouble(resultadoFormateado);
                    
                } else {
                    laminaPantalla.pantalla.setText("Error");
                    operacion = 0;
                    numeroCapturado = 0;
                    resultado = 0;
                }
                System.out.println("resultado division: " + resultado);
                break;  
        }            
    }
    
    // Operacion con botones de operaciones
    public class logicaOperdador implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            estadoIgual = false;
            
            // Al oprimir en un operador guarda numero y limpia pantalla
            laminaPantalla.pantalla.setText("0");
            
            if (numeroCapturado == 0) {
                numeroCapturado = numeroEscuchado;
                
            } else {
                numeroCapturado = resultado;
            }                       
            System.out.println("numero capturado: " + numeroCapturado);
            
            // Selecciona la opearacion que se ejecutara en segundo plano
            if (e.getSource() == suma) {
                operacion = 1;
                
            } if (e.getSource() == resta) {
                operacion = 2;   
                
            } if (e.getSource() == multiplicacion) {
                operacion = 3;
                
            }if (e.getSource() == division) {
                operacion = 4;
            }            
        }
    }
    
    private void establecerCaracteristicasBotones(List<JButton> botones) {
        
        for (JButton boton : botones) {
            
            boton.setFocusPainted(false);
            boton.setRolloverEnabled(false);
            boton.setBorder(null);
            boton.setContentAreaFilled(false);
            boton.setOpaque(true);
            
            if (boton == suma || boton == resta || boton == multiplicacion || boton == division || boton == punto || boton == limpiar || boton == eliminarCaracter) {
                boton.setFont(new Font("", Font.PLAIN, 18));
                boton.setBackground(new Color(64, 64, 64));
                boton.setForeground(Color.WHITE);
                
                boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        boton.setBackground(new Color(70, 70, 70));
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        boton.setBackground(new Color(64, 64, 64));
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        boton.setBackground(new Color(85, 85, 85));
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        boton.setBackground(new Color(64, 64, 64));
                    } 
                });
                
            } else if (boton == igual) {
                boton.setBackground(new Color(235, 101, 54));
                boton.setFont(new Font("", Font.PLAIN, 18));
                boton.setForeground(Color.WHITE);
                
                boton.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        boton.setBackground(new Color(240, 116, 72));
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        boton.setBackground(new Color(235, 101, 54));
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        boton.setBackground(new Color(244, 131, 89));
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        boton.setBackground(new Color(235, 101, 54));
                    } 
                });
                
            } else {
                boton.setFont(new Font("", Font.BOLD, 18));
                boton.setBackground(new Color(84, 84, 85));
                boton.setForeground(Color.WHITE);

                boton.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        boton.setBackground(new Color(95, 94, 94));
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        boton.setBackground(new Color(84, 84, 85));
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        boton.setBackground(new Color(111, 111, 111));
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        boton.setBackground(new Color(84, 84, 84));
                    }                    
                });   
            }
            
            this.add(boton);
        }
    }
}
