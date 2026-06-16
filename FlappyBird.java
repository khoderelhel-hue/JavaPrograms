import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class flappybird {
    static int birdX = 100;
    static int birdY = 250;
    static int birdWidth = 30;
    static int birdHeight = 30;

    static int pipeX = 360;
    static int pipeWidth = 60;
    static int pipeHeight = 300;
    static int pipeGap = 150;
    static int pipeSpeed = -4;

    static int velocityY = 0;
    static int gravity = 1;

    static int score = 0;

    static class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(new Color(135, 206, 235));
            g.fillRect(0, 0, 360, 640);

            g.setColor(Color.YELLOW);
            g.fillRect(birdX, birdY, birdWidth, birdHeight);
            g.setColor(Color.GREEN);
            g.fillRect(pipeX, 0, pipeWidth, pipeHeight);
            int downPipe = pipeHeight + pipeGap;
            int downPipeHeight = 640 - downPipe;
            g.fillRect(pipeX, downPipe, pipeWidth, downPipeHeight);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("Score: " + score, 20, 40);

        }
    }

    public static void main(String[] args) {
        JFrame gameScreen = new JFrame("Flappy bird");
        gameScreen.setSize(360, 640);

        gameScreen.setLocationRelativeTo(null);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameScreen.setResizable(false);

        GamePanel panel = new GamePanel();
        gameScreen.add(panel);
        Timer gameLoop = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                velocityY += gravity;
                birdY += velocityY;
                pipeX += pipeSpeed;

                if (pipeX + pipeWidth < 0) {
                    pipeX = 360;
                    pipeHeight = 100 + (int) (Math.random() * 200);
                    score++;
                    if (score % 3 == 0 && pipeSpeed > -10){
                        pipeSpeed--;
                        System.out.println("Hız arttı! Yeni Hız: " + pipeSpeed );
                    }
                }

                if (birdY > 640 - birdHeight - 40 || birdY < 0) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Game Over");
                    System.out.println(score);
                }
                if (birdX + birdWidth > pipeX && birdX < pipeX + pipeWidth && birdY < pipeHeight) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Game Over");
                    System.out.println(score);
                }
                if (birdX + birdWidth > pipeX && birdX < pipeX + pipeWidth && birdY + birdHeight > pipeHeight + pipeGap) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Game over");
                    System.out.println(score);
                }
                panel.repaint();
            }
        });


        gameLoop.start();
        gameScreen.setFocusable(true);
        gameScreen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    velocityY = -10; // Kuşu yukarı fırlat
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    birdY = 250;
                    velocityY = 0;
                    pipeX = 360;
                    pipeHeight = 300;
                    pipeSpeed = -4;

                    score = 0;


                    gameLoop.start();
                    System.out.println("game restarted");
                }

            }

        });

        gameScreen.setVisible(true);
    }
}
