import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

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
    
    LaminaPantalla laminaPantalla = new LaminaPantalla();
    LogicaNumeros logicaNumeros = new LogicaNumeros();
    logicaOperdador logicaOperdador = new logicaOperdador();
    double numeroEscuchado;
    double numeroCapturado;
    int operacion = 0;
    double resultado = 0;
    boolean estadoIgual = false;
    
    
    public LaminaBotones() {
        this.setLayout(new GridLayout(5, 4));
        
        this.add(uno);
        this.add(dos);
        this.add(tres);
        this.add(suma);
        this.add(cuatro);
        this.add(cinco);
        this.add(seis);
        this.add(resta);
        this.add(siete);
        this.add(ocho);
        this.add(nueve);
        this.add(multiplicacion);
        this.add(punto);
        this.add(cero);
        this.add(igual);
        this.add(division);
        this.add(limpiar);  
        
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
                laminaPantalla.pantalla.setText(String.valueOf(resultado));  
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
 
            // Operaciones en segundo plano
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
                        resultado = numeroCapturado / numeroEscuchado;
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
}
