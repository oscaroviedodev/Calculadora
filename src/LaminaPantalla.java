import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LaminaPantalla extends JPanel{
    
    JTextField pantalla = new JTextField("0");
        
    public LaminaPantalla() {
        this.add(pantalla);        
        pantalla.setHorizontalAlignment(JTextField.RIGHT); 
        pantalla.setPreferredSize(new Dimension(285, 50));
        pantalla.setFont(new Font("",Font.BOLD,35));
        pantalla.setFocusable(false);
        
    }
}
