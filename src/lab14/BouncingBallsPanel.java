package lab14;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static lab14.BouncingBallsFrame.screenWidth;
import static lab14.BouncingBallsFrame.screenHeight;

public class BouncingBallsPanel extends JPanel {

    Image bufferImage = null;
    long lastPaint = 0;
    static final int frameRate = 40;
    int sleepTime = 10;
    double speed = .66;

    static class Ball {
        double x;
        double y;
        double vx;
        double vy;
        Color color;
        double m;
        double r;

        public Ball(double x, double y, double vx, double vy, Color color, double r) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
            this.r = r;
            this.m = Math.PI * Math.pow(r, 2) / 314;
        }
    }

    List<Ball> balls = new ArrayList<>();
    Random random = new Random();
    Dimension d = getSize();
    AnimationThread at = new AnimationThread();
    boolean suspend = false;


    class AnimationThread extends Thread {

        synchronized void wakeup() {
            suspend = false;
            notify();
        }

        void safeSuspend() {
            suspend = true;
        }

        synchronized void addBalls(int n) {
            for (int i = 0; i < n; i++) {
                onPlus();
            }
        }


        public void run() {
            while (true) {

                synchronized (this) {
                    try {
                        if (suspend) {
                            System.out.println("suspending");
                            wait();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }

                try {
                    sleep(sleepTime);
                } catch (InterruptedException ignored) {
                }

                elastic_collision();

                balls.forEach(ball -> {


                    if (ball.x < ball.r) {
                        ball.x = ball.r;
                        ball.vx *= -1;
                    }
                    if (ball.x > screenWidth - ball.r) {
                        ball.x = screenWidth - ball.r;
                        ball.vx *= -1;
                    }
                    if (ball.y < ball.r) {
                        ball.y = ball.r;
                        ball.vy *= -1;
                    }
                    if (ball.y > screenHeight - 90 - ball.r) {
                        ball.y = screenHeight - 90 - ball.r;
                        ball.vy *= -1;
                    }


                    ball.x = (ball.x + ball.vx);
                    ball.y = (ball.y + ball.vy);
                });


                repaint();
            }
        }
    }

    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        at.start();
        at.safeSuspend();
    }

    void onStart() {
        System.out.println("Start or resume animation thread");
        at.wakeup();
    }

    void onStop() {
        System.out.println("Suspend animation thread");
        at.safeSuspend();
    }

    void onPlus() {
        System.out.println("Add a ball");
        balls.add(new Ball(random.nextInt(screenWidth), random.nextInt(screenHeight), random.nextDouble() * 5 - 2.5,
                random.nextDouble() * 5 - 2.5, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()), random.nextInt(20) + 10));
    }

    void onPlus100() {
        at.addBalls(100);
    }

    void onMinus() {
        System.out.println("Remove a ball");
        balls.remove(0);
    }

    public void update(Graphics g) {
        if (System.currentTimeMillis() - lastPaint < frameRate) return;
        Dimension d = getSize();
        if (bufferImage == null || bufferImage.getHeight(this) != d.height || bufferImage.getWidth(this) != d.width) {
            bufferImage = createImage(d.width, d.height);
        }
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.setColor(Color.white);
        bufferGraphics.fillRect(0, 0, d.width, d.height);
        super.paint(bufferGraphics);
        g.drawImage(bufferImage, 0, 0, this);
        lastPaint = System.currentTimeMillis();
    }

    public void paint(Graphics g) {
        update(g);
        super.paint(g);
        balls.forEach(ball -> {
            g.setColor(ball.color);
            g.fillOval((int) (ball.x - ball.r), (int) (ball.y - ball.r), (int) (2 * ball.r), (int) (2 * ball.r));
            g.setColor(Color.black);
            g.drawOval((int) (ball.x - ball.r), (int) (ball.y - ball.r), (int) (2 * ball.r), (int) (2 * ball.r));

        });

    }


    //
    // Realizacja algorytmu poniższego dokumentu
    // http://www.vobarian.com/collisions/2dcollisions2.pdf
    //

    public void elastic_collision() {
        balls.forEach(b1 -> {
            balls.forEach(b2 -> {
                if (!b1.equals(b2)) {
                    int interCheck = circle_inter(b1.x, b1.y, b2.x, b2.y, b1.r, b2.r);
                    if (interCheck >= 0) {
                        boolean markAsInter = false;
                        if (interCheck == 0) {
                            double xDis = b2.x - b1.x;
                            double yDis = b2.y - b1.y;
                            double move = (b1.r + b2.r - Math.sqrt(xDis * xDis + yDis * yDis)) / 2;
                            double scale = move / Math.sqrt(xDis * xDis + yDis * yDis);
                            b1.x += xDis * -1 * scale;
                            b1.y += yDis * -1 * scale;
                            b2.x += xDis * scale;
                            b2.y += yDis * scale;
                            markAsInter = true;
                        }


                        double nvX = b2.x - b1.x;
                        double nvY = b2.y - b1.y;
                        double unvX = nvX / Math.sqrt(nvX * nvX + nvY * nvY);
                        double unvY = nvY / Math.sqrt(nvX * nvX + nvY * nvY);
                        double utvX = unvY * -1;
                        double utvY = unvX;

                        double v1n = unvX * b1.vx + unvY * b1.vy; // Dot product
                        double v1t = utvX * b1.vx + utvY * b1.vy;
                        double v2n = unvY * b2.vy + unvX * b2.vx;
                        double v2t = utvX * b2.vx + utvY * b2.vy;

                        double v1nPrime = (v1n * (b1.m - b2.m) + 2. * b2.m * v2n) / (b1.m + b2.m);
                        double v2nPrime = (v2n * (b2.m - b1.m) + 2. * b1.m * v1n) / (b1.m + b2.m);

                        double v1tPrime = v1t;
                        double v2tPrime = v2t;

                        double finalXVel = v1nPrime * unvX + v1tPrime * utvX;
                        double finalYVel = v1nPrime * unvY + v1tPrime * utvY;

                        b1.vx = finalXVel;
                        b1.vy = finalYVel;
                        if (markAsInter) {
                            b2.vx = v2nPrime * unvX + v2tPrime * utvX;
                            b2.vy = v2nPrime * unvY + v2tPrime * utvY;
                        }


                    }
                }
            });
        });
    }

    public int circle_inter(double x1, double y1, double x2,
                            double y2, double r1, double r2) {
        double distSq = (x1 - x2) * (x1 - x2) +
                (y1 - y2) * (y1 - y2);
        double radSumSq = (r1 + r2) * (r1 + r2);
        if (distSq == radSumSq)
            return 1;
        else if (distSq > radSumSq)
            return -1;
        else
            return 0;
    }


}