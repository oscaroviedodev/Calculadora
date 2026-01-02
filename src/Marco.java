import javax.swing.JFrame;

public class Marco extends JFrame{
    
    LaminaPrincipal miLaminaPrincipal = new LaminaPrincipal();
    
    public Marco() {
        this.setLocation(1200, 200);
        this.setSize(300, 450);
        this.setTitle("Calculadora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        this.add(miLaminaPrincipal);
        
    }
}
