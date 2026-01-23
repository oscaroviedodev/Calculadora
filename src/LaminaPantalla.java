import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LaminaPantalla extends JPanel{
    
    JTextField pantalla = new JTextField("0");
        
    public LaminaPantalla() {
        this.add(pantalla);        
        pantalla.setHorizontalAlignment(JTextField.RIGHT); 
        pantalla.setPreferredSize(new Dimension(285, 50));
        pantalla.setFont(new Font("", Font.PLAIN, 40));
        pantalla.setFocusable(false);
        
        this.setBackground(new Color(70, 70, 70));
        pantalla.setBackground(new Color(70, 70, 70));
        pantalla.setForeground(Color.WHITE);
        pantalla.setBorder(null);
        //this.setBorder(BorderFactory.createLineBorder(new Color(44, 45, 45), 5));
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, new Color(44, 45, 45)));
    }
}
