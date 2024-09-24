import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Connectfour extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 650;
    static final int SCREEN_HEIGHT = 500;
    private static final int columns = 7;
    private static final int rows = 6;
    static final int UNIT_SIZE = 80;
    int turn = 1;
    Color[][] grid = new Color[rows][columns];
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();

    JPanel game_panel = new JPanel();
    JButton[] buttons = new JButton[42];

    Connectfour(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setBackground(Color.white);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);

        textfield.setBackground(Color.black);
        textfield.setForeground(Color.green);
        textfield.setFont(new Font("Ink Free", Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Connect 4");
        textfield.setOpaque(true);
        title_panel.setLayout(new BorderLayout());

        button_panel.setLayout(new GridLayout(1,7));
        button_panel.setBounds(0,0,SCREEN_WIDTH,100);
        button_panel.setOpaque(false);

        for(int i = 0; i < 7; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setOpaque(false);
        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.blue);
        g2D.fillRect(0,0,650,550);
        g2D.setColor(Color.white);
        int startX = 0;
        int startY = 0;
        //2) draw grid here
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                g2D.setColor(grid[row][col]);
                g2D.fillOval(startX, startY, UNIT_SIZE, UNIT_SIZE);
                startX = startX + UNIT_SIZE + 10;
            }
            startX = 0;
            startY += UNIT_SIZE + 10;
        }
    }
    public void move(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void firstTurn(){

    }
    public void check(){

    }
    public void RedWins(){

    }

/*
    public class MyKeyAdapter extends KeyAdapter {

        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            int xSpot = x/UNIT_SIZE;
            int ySpot = y/UNIT_SIZE;

            if(turn % 2 == 0) {
                grid[ySpot][xSpot] = new Color(Color.RED);
            }else{
                grid[ySpot][xSpot] = new Color(255,255,0);
            }
            turn++;
        }


    }
  */

}


