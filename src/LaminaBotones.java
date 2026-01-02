import java.awt.GridLayout;
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
    }
}
