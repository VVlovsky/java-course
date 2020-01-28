package lab14;

import lab10.XmasShape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class BouncingBallsPanel extends JPanel {

    private int sleepTime = (int) 1000 / 60;

    static class Ball {
        double x;
        double y;
        double vx;
        double vy;
        Color color;
        double m = 1;

        public Ball(double x, double y, double vx, double vy, Color color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
        }
    }

    List<Ball> balls = new ArrayList<>();
    Random random = new Random();
    int BALL_SIZE = 10;
    Dimension d = getSize();


    class AnimationThread extends Thread {


        public void run() {
            for (; ; ) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException ignored) {
                }

                elastic_collision();

                balls.stream().forEach(ball -> {


                    if (ball.x < BALL_SIZE) {
                        ball.x = BALL_SIZE;
                        ball.vx *= -1;
                    }
                    if (ball.x > 700 - BALL_SIZE) {
                        ball.x = 700 - BALL_SIZE;
                        ball.vx *= -1;
                    }
                    if (ball.y < BALL_SIZE) {
                        ball.y = BALL_SIZE;
                        ball.vy *= -1;
                    }
                    if (ball.y > 700 - BALL_SIZE) {
                        ball.y = 700 - BALL_SIZE;
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
    }

    void onStart() {
        System.out.println("Start or resume animation thread");
        new AnimationThread().start();
    }

    void onStop() {
        System.out.println("Suspend animation thread");
    }

    void onPlus() {
        System.out.println("Add a ball");
        balls.add(new Ball(random.nextInt(700), random.nextInt(700), random.nextDouble() * 5 - 2.5,
                random.nextDouble() * 5 - 2.5, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat())));
    }

    void onMinus() {
        System.out.println("Remove a ball");
    }

    public void paint(Graphics g) {
        super.paint(g);
        balls.forEach(ball -> {
            g.setColor(ball.color);
            g.fillOval((int) ball.x - BALL_SIZE, (int) ball.y - BALL_SIZE, 2 * BALL_SIZE, 2 * BALL_SIZE);
            g.setColor(Color.black);
            g.drawOval((int) ball.x - BALL_SIZE, (int) ball.y - BALL_SIZE, 2 * BALL_SIZE, 2 * BALL_SIZE);

        });

    }

    public void elastic_collision() {
        balls.forEach(b1 -> {
            balls.forEach(b2 -> {
                if (!b1.equals(b2) && Math.abs(b1.x - b2.x) < 2*BALL_SIZE && Math.abs(b1.y - b2.y) < 2*BALL_SIZE) {
//                    double phi, theta1, theta2;         // collision angle, a's movement angle, b's movement angle
//                    double v1, v2;                      // scalar values of velocity for balls a and b
//                    double m1, m2;                      // mass for b1's a and b
//                    double v1xf, v1yf, v2xf, v2yf;      // x and y components of final velocities after collision
//
//                    // calculate angles
//                    phi = Math.atan2(b1.y - b2.y, b1.x - b2.x);
//                    theta1 = Math.atan2(b1.vy, b1.vx);
//                    theta2 = Math.atan2(b2.vy, b2.vx);
//
//                    // get mass
//                    m1 = 1;
//                    m2 = 1;
//
//                    // calculate scalar values of velocities
//                    v1 = Math.sqrt(Math.pow(b1.vx, 2) + Math.pow(b1.vy, 2));
//                    v2 = Math.sqrt(Math.pow(b2.vx, 2) + Math.pow(b2.vy, 2));
//
////                    v1xf = ((((v1 * Math.cos(Math.toRadians(theta1 - phi)) * (m1 - m2)) + (2 * m2 * v2 * Math.cos(Math.toRadians(theta2 - phi)))) / (m1 + m2)) * Math.cos(Math.toRadians(phi))) + (v1 * Math.sin(Math.toRadians(theta1 - phi)) * Math.cos(Math.toRadians(phi + (Math.PI / 2))));
////                    v2xf = ((((v2 * Math.cos(Math.toRadians(theta2 - phi)) * (m2 - m1)) + (2 * m1 * v1 * Math.cos(Math.toRadians(theta1 - phi)))) / (m1 + m2)) * Math.cos(Math.toRadians(phi))) + (v2 * Math.sin(Math.toRadians(theta2 - phi)) * Math.cos(Math.toRadians(phi + (Math.PI / 2))));
////                    v1yf = ((((v1 * Math.cos(Math.toRadians(theta1 - phi)) * (m1 - m2)) + (2 * m2 * v2 * Math.cos(Math.toRadians(theta2 - phi)))) / (m1 + m2)) * Math.sin(Math.toRadians(phi))) + (v1 * Math.sin(Math.toRadians(theta1 - phi)) * Math.sin(Math.toRadians(phi + (Math.PI / 2))));
////                    v2yf = ((((v2 * Math.cos(Math.toRadians(theta2 - phi)) * (m2 - m1)) + (2 * m1 * v1 * Math.cos(Math.toRadians(theta1 - phi)))) / (m1 + m2)) * Math.sin(Math.toRadians(phi))) + (v2 * Math.sin(Math.toRadians(theta2 - phi)) * Math.sin(Math.toRadians(phi + (Math.PI / 2))));
//
//                    v1xf = b1.vx - ((Math.abs(b1.vx - b2.vx)*Math.abs(b1.x - b2.x)*Math.atan2(b1.vx - b2.vx, b1.x - b2.x))/(b1.vx - b2.vx));
//                    v1yf = b1.vy - ((Math.abs(b1.vy - b2.vy)*Math.abs(b1.y - b2.y)*Math.atan2(b1.vy - b2.vy, b1.y - b2.y))/(b1.vy - b2.vy));
//
//                    b1.vx = v1xf;
//                    b1.vy = v1yf;
////                    b2.vx = v2xf;
////                    b2.vy = v2yf;
//
//
                    // Compute unit normal and unit tangent vectors
//                    Vector<Double> b1 = new Vector<>();
//                    b1.add(b1.x);
//                    b1.add(b1.y);
//                    Vector<Double> b2 = new Vector<>();
//                    b2.add(b2.x);
//                    b2.add(b2.y);

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

                    double v1tPrime = v1t; // Note: in reality, the tangential velocities do not change after the collision
                    double v2tPrime = v2t;

                    double finalXVel = v1nPrime * unvX + v1tPrime * utvX;
                    double finalYVel = v1nPrime * unvY + v1tPrime * utvY;

                    b1.vx = finalXVel;
                    b1.vy = finalYVel;

//                    Vector v_n = b2.pos() - b1.pos(); // v_n = normal vec. - a vector normal to the collision surface
//                    Vector2D v_un = v_n.unitVector(); // unit normal vector
//                    Vector2D v_ut (-v_un.y(), v_un.x()); // unit tangent vector
//
//                    // Compute scalar projections of velocities onto v_un and v_ut
////                    double v1n = v_un * b1.v(); // Dot product
////                    double v1t = v_ut * b1.v();
////                    double v2n = v_un * b2.v();
////                    double v2t = v_ut * b2.v();
//
//                    // Compute new tangential velocities
////                    double v1tPrime = v1t; // Note: in reality, the tangential velocities do not change after the collision
////                    double v2tPrime = v2t;
//
//                    // Compute new normal velocities using one-dimensional elastic collision equations in the normal direction
//                    // Division by zero avoided. See early return above.
////                    double v1nPrime = (v1n * (b1.m - b2.m) + 2. * b2.m * v2n) / (b1.m + b2.m);
////                    double v2nPrime = (v2n * (b2.m - b1.m) + 2. * b1.m * v1n) / (b1.m + b2.m);
//
//                    // Compute new normal and tangential velocity vectors
//                    Vector2D v_v1nPrime = v1nPrime * v_un; // Multiplication by a scalar
//                    Vector2D v_v1tPrime = v1tPrime * v_ut;
//                    Vector2D v_v2nPrime = v2nPrime * v_un;
//                    Vector2D v_v2tPrime = v2tPrime * v_ut;
//
//                    // Set new velocities in x and y coordinates
//                    b1.setVX(v_v1nPrime.x() + v_v1tPrime.x());
//                    b1.setVY(v_v1nPrime.y() + v_v1tPrime.y());
//                    b2.setVX(v_v2nPrime.x() + v_v2tPrime.x());
//                    b2.setVY(v_v2nPrime.y() + v_v2tPrime.y());
                }
            });
        });
    }


}