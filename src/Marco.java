import javax.swing.JFrame;

public class Marco extends JFrame{
    
    LaminaPrincipal miLaminaPrincipal = new LaminaPrincipal();
    
    public Marco() {
        this.setLocation(1200, 200);
        this.setSize(300, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.add(miLaminaPrincipal);
        
    }
}
