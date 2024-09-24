import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
    private static final int columns = 7;
    private static final int rows = 6;
    static final int UNIT_SIZE = 80;
    int turn;
    int[][] board = new int[rows][columns];
    int user;
    Color[][] grid = new Color[rows][columns];
    JLabel label;
    boolean win;
    int consecutive;
    int newRow;
    int newColumn;

    GamePanel(){
        this.setPreferredSize(new Dimension(650, 670));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

        label = new JLabel();
        label.setBounds(0,0,100,100);
        label.setBackground(Color.red);
        label.setOpaque(true);

        this.add(label);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        int colCount = 0;
        int measurex = 5;
        for(int col = 0; col < grid[0].length; col++){
            g.setColor(Color.black);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString(getTurn() + "'s Turn",10,35);
            g.setColor(Color.lightGray);
            g.fillRect(measurex, 50, UNIT_SIZE, 50);
            g.setColor(Color.black);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            g.drawString(String.valueOf(colCount), measurex + 30, 85);
            colCount += 1;
            measurex = measurex + UNIT_SIZE + 12;
        }
            g.setColor(Color.blue);
            g.fillRect(0,100,650,570);
            g.setColor(Color.white);
            int startX = 10;
            int startY = 120;

            for(int row = 0; row < grid.length; row++){
                for(int col = 0; col < grid[0].length; col++){
                    g.setColor(Color.black);
                    g.drawLine(startX, startY - 10, startX, startY + 10);
                    g.setColor(grid[row][col]);
                    g.fillOval(startX, startY, UNIT_SIZE, UNIT_SIZE);
                    startX = startX + UNIT_SIZE + 10;
                }
                startX = 10;
                startY += UNIT_SIZE + 10;
            }
            if(win){
                g.setColor(Color.black);
                g.setFont(new Font("Ink Free", Font.BOLD, 80));
                FontMetrics metrics1 = getFontMetrics(g.getFont());

                if(turn == 2) {
                    g.drawString("Red has won",5,350);
                }else{
                    g.drawString("Yellow has won",5,350);
                }
            }


    }
    public String getTurn(){
        if (turn == 1){
            return "Red";
        }else{
            return "Yellow";
        }
    }
    public void startGame(){
        turn = 2;
        user = 0;
        consecutive = 0;
        win = false;
        for (int[] fill : board) {
            Arrays.fill(fill, 0);
        }
        for(int row = 0; row < 6; row ++){
            for(int column = 0; column < 7; column++){
                grid[row][column] = new Color(255,255,255);
            }
        }
    }

    public void gameOver(Graphics g){
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 100));
        FontMetrics metrics1 = getFontMetrics(g.getFont());

        if(turn == 2) {
            g.drawString("Red has won",200,250);
        }else{
            g.drawString("Yellow has won",200,250);
        }
    }
    public void newColor(int row, int column){
        System.out.println("Check Color Change");
        System.out.println("Row2: " + row + "Column2: " + column);
        if(turn == 1) {
            grid[row][column] = new Color(255, 0, 0);
        }else{
            grid[row][column] = new Color(255, 255, 0);
        }
        repaint();
        board();
    }

    public void board() {
        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        for (int row = 0; row < board.length; row++) {
            System.out.print(row + " ");
            for (int column = 0; column < board[0].length; column++) {
                System.out.print(board[row][column] + "|");
            }
            System.out.println("");
        }
    }

    public void move(int input){
        if(!win) {
            System.out.println("Here 1");
            System.out.println("Consecutive: " + consecutive);
            if (input < 8 && consecutive == 1) {
                System.out.println("Here 2");

                for (int row = 0; row < board.length; row++) {
                    System.out.println("Here 3");

                    if (row > 0) {
                        System.out.println("Here 4");

                        if (board[row][input] != 0) {
                            System.out.println("Here 5");
                            board[row - 1][input] = turn;
                            System.out.println("Here 6");
                            newRow = row - 1;
                            newColumn = input;
                            System.out.println("Row3: " + (row - 1) + " Column3: " + input);
                            newColor(newRow, newColumn);
                            consecutive++;
                            win = checkWin(row - 1, input);
                            row += board.length;
                        } else if (row == 5 && board[row][input] == 0) {
                            System.out.println("Here 7");
                            board[row][input] = turn;
                            newRow = row;
                            newColumn = input;
                            System.out.println("Row3: " + row + " Column3: " + input);
                            newColor(newRow, newColumn);
                            win = checkWin(row, input);
                            consecutive++;
                            row += board.length;
                        }
                    } else if (row == 0 && board[row + 1][input] != 0) {
                        if (board[row][input] == 0) {
                            System.out.println("Here 8");
                            board[row][input] = turn;
                            newRow = row;
                            newColumn = input;
                            newColor(newRow, newColumn);
                            consecutive++;
                            row += board.length;
                        }
                    }
                }
            }
            changeTurn();
        }
    }
    public boolean checkWin(int row, int column){
        int consecutive = 1;
        //vertical 4 (same column)
        System.out.println("Check1");
        for (int n = 0; n < board.length - 1; n++) {
            if (board[n][column] != 0 && n < 5) {
                if (board[n][column] == board[n + 1][column]) {
                    System.out.println("vertical Check: " + board[n][column] + " is eq ual to " + board[n + 1][column]);
                    consecutive++;
                    if (consecutive == 4) {
                        System.out.println("You have won!!!");
                        return true;
                    }
                } else {
                    consecutive = 1;
                }
            }
        }

        //horizontal 4 (Same row)
        System.out.println("Check2");
        consecutive = 1;
        for (int n = 0; n < board.length - 1; n++) {
            if (board[row][n] != 0 && n < 5) {
                if (board[row][n] == board[row][n + 1]) {
                    System.out.println("Horizontal Check: " + board[row][n] + " is equal to " + board[row][n + 1]);
                    consecutive++;
                    if (consecutive == 4) {
                        System.out.println("You have won!!!");
                        return true;
                    }
                } else {
                    consecutive = 1;
                }
            }
        }

        //right diagonal

        int newRow = row;
        int newColumn = column;
        System.out.println("row: " + newRow + " column: " + newColumn);
        while (newRow < 5 && newColumn > 0) {
            newRow++;
            newColumn--;
        }
        System.out.println("row: " + newRow + " column: " + newColumn);
        while (newColumn < 6 && newRow > 0) {
            if (board[newRow][newColumn] == board[newRow - 1][newColumn + 1] && board[newRow][newColumn] != 0) {
                System.out.println("Right Diagonal Check: " + board[newRow][newColumn] + " is equal to " + board[newRow - 1][newColumn + 1]);
                consecutive++;
                if (consecutive >= 4) {
                    System.out.println("You have won!!!");
                    return true;
                }
            } else {
                consecutive = 1;
            }


            newColumn++;
            newRow--;
        }

        //left diagonal
        System.out.println("Check4");
        newRow = row;
        newColumn = column;
        System.out.println("row1: " + newRow + " column1: " + newColumn);
        while (newRow > 0 && newColumn > 0) {
            newRow--;
            newColumn--;
        }
        System.out.println("row1: " + newRow + " column1: " + newColumn);
        while (newColumn < 6 && newRow < 5) {
            if (board[newRow][newColumn] == board[newRow + 1][newColumn + 1] && board[newRow][newColumn] != 0) {
                System.out.println("Left Diagonal Check: " + board[newRow][newColumn] + " is equal to " + board[newRow + 1][newColumn + 1]);
                consecutive++;
                if (consecutive == 4) {
                    System.out.println("You have won!!!");
                    return true;
                }
            } else {
                consecutive = 1;
            }

            newColumn++;
            newRow++;
        }
        return false;
    }

    public void changeTurn(){
            consecutive = 0;
            if (turn == 1) {
                turn = 2;
                System.out.println("Yellow's Turn");
            } else if (turn == 2) {
                turn = 1;
                System.out.println("Red's Turn");
            }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        while(!win) {
            move(user);
        }
        System.out.println("You have won");

    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override

        public void keyPressed(KeyEvent e) {

            switch(e.getKeyCode()) {
                case 48:
                    user = 0;
                    System.out.println("0");
                    consecutive += 1;
                    move(0);
                    break;
                case 49:
                    user = 1;
                    System.out.println("1");
                    consecutive += 1;
                    move(1);
                    break;
                case 50:
                    user = 2;
                    System.out.println("2");
                    consecutive += 1;
                    move(2);
                    break;
                case 51:
                    user = 3;
                    System.out.println("3");
                    consecutive += 1;
                    move(3);
                    break;
                case 52:
                    user = 4;
                    System.out.println("4");
                    consecutive += 1;
                    move(4);
                    break;
                case 53:
                    user = 5;
                    System.out.println("5");
                    consecutive += 1;
                    move(5);
                    break;
                case 54:
                    user = 6;
                    System.out.println("6");
                    consecutive += 1;
                    move(6);
                    break;
                default:
                    break;

            }
        }
    }

}
