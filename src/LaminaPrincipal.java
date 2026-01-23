import java.awt.BorderLayout;
import javax.swing.JPanel;

public class LaminaPrincipal extends JPanel{
    
    LaminaPantalla miLaminaPantalla = new LaminaPantalla();
    LaminaBotones miLaminaBotones = new LaminaBotones();
    
    public LaminaPrincipal() {
        this.setLayout(new BorderLayout());
        this.add(miLaminaPantalla, BorderLayout.NORTH);
        this.add(miLaminaBotones, BorderLayout.CENTER);
        
        this.miLaminaBotones.laminaPantalla = miLaminaPantalla;
    }
}