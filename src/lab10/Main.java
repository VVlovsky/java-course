package lab10;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {


    public static void main(String[] args) {

        Color YELLOW = new Color(255, 221, 0);
        Color ORANGE = new Color(242, 102, 0);
        Color GREEN = new Color(5, 214, 0);
        Color DARKGREEN = new Color(39, 143, 14);
        Color BLUE = new Color(15, 119, 235);
        Color RED = new Color(255, 16, 27);
        Color BROWN = new Color(106, 41, 0);
        Color BLACK = new Color(0, 0, 0);
        Color BACKGROUND_COLOR = new Color(172, 125, 255);

        DrawPanel dp = new DrawPanel();
        dp.setBackground(BACKGROUND_COLOR);

        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(3) + 1;
            int x = rand.nextInt(1000);
            int y = rand.nextInt(1000);
            dp.add(new Star(x, y, size, YELLOW));
        }
        dp.add(new Ground(500, 800, 1, Color.white));
        dp.add(new Wood(500, 550, 1, BROWN));
        dp.add(new Branch(500, 110, 2, DARKGREEN));
        dp.add(new Branch(500, 110, 2, DARKGREEN, true));
        dp.add(new Branch(500, 130, 4, DARKGREEN));
        dp.add(new Branch(500, 130, 4, DARKGREEN, true));
        dp.add(new Branch(500, 160, 6, DARKGREEN));
        dp.add(new Branch(500, 160, 6, DARKGREEN, true));
        dp.add(new Branch(500, 200, 8, DARKGREEN));
        dp.add(new Branch(500, 200, 8, DARKGREEN, true));
        dp.add(new Branch(500, 250, 10, DARKGREEN));
        dp.add(new Branch(500, 250, 10, DARKGREEN, true));


        dp.add(new Star(500, 105, 5, ORANGE));


        int genNum = 40;
        int itsTimeToGenerate = genNum;
        int[] xarr = new int[genNum];
        int[] yarr = new int[genNum];
        while (itsTimeToGenerate != 0) {
            int x = rand.nextInt(600) + 120;
            int y = rand.nextInt(400) + 130;
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);

            boolean notDraw = false;
            if (Math.abs(x - 480) < (y - 50) / 2) {
                int counter = -1;
                for (int ix : xarr) {
                    counter++;
                    for (int insideIX = -35; insideIX < 35; insideIX++) {
                        if (ix + insideIX == x) {

                            for (int insideIY = -50; insideIY < 50; insideIY++) {
                                if (yarr[counter] + insideIY == y) {
                                    notDraw = true;
                                    break;

                                }
                            }
                            break;

                        }
                    }
                }
                if (!notDraw) {
                    if (x % 2 != 0 && y % 2 != 0) {
                        dp.add(new SquareBubble(x, y, 1.8, new Color(r, g, b)));
                    } else {
                        dp.add(new Bubble(x, y, 0.3, BLACK, new Color(r, g, b)));
                    }
                    itsTimeToGenerate--;
                    xarr[genNum - itsTimeToGenerate - 1] = x;
                    yarr[genNum - itsTimeToGenerate - 1] = y;
                }
            }
        }


        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(dp);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);


    }


}
