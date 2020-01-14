package lab10;

import javax.swing.*;
import java.awt.*;

public class main {



    public static void main(String[] args) {
        Color YELLOW = new Color(255, 221, 0);
        Color GREEN = new Color(5, 214, 0);
        Color DARKGREEN = new Color(39, 143, 14);
        Color BLUE = new Color(15, 119, 235);
        Color RED = new Color(255, 16, 27);
        Color BROWN = new Color(106, 41, 0);
        DrawPanel dp = new DrawPanel();

        dp.add(new Wood(500, 330, 1, BROWN));
        dp.add(new Branch(500, 100, 2, DARKGREEN));
        dp.add(new Branch(500, 100, 2, DARKGREEN, true));
        dp.add(new Branch(500, 130, 4, DARKGREEN));
        dp.add(new Branch(500, 130, 4, DARKGREEN, true));
        dp.add(new Branch(500, 160, 6, DARKGREEN));
        dp.add(new Branch(500, 160, 6, DARKGREEN, true));

        dp.add(new Bubble(410, 200, 0.3, RED, BLUE));
        dp.add(new Bubble(390, 240, 0.3, RED, RED));
        dp.add(new Bubble(470, 210, 0.3, RED, YELLOW));
        dp.add(new Bubble(360, 300, 0.3, RED, RED));



        JFrame frame = new JFrame("Choinka");
        frame.setBackground(new Color(0, 194, 194));
        frame.setContentPane(dp);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);



    }


}
