import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class Gameframe extends JFrame {

    Gameframe(){
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Connect 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
