import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BouncingBallAnimation extends JFrame implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BALL_SIZE = 50;
    private static final int BALL_SPEED = 5;

    private int ballX;
    private int ballY;
    private int ballDirectionX;
    private int ballDirectionY;

    private Canvas canvas;

    public BouncingBallAnimation() {
        setTitle("Bouncing Ball Animation");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(canvas);
        addKeyListener(this);

        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballDirectionX = 1;
        ballDirectionY = 1;
    }

    public void startAnimation() {
        while (true) {
            updateBallPosition();
            canvas.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBallPosition() {
        ballX += BALL_SPEED * ballDirectionX;
        ballY += BALL_SPEED * ballDirectionY;

        if (ballX <= 0 || ballX >= WIDTH - BALL_SIZE) {
            ballDirectionX *= -1;
        }

        if (ballY <= 0 || ballY >= HEIGHT - BALL_SIZE) {
            ballDirectionY *= -1;
        }
    }

    public class Canvas extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ballDirectionX *= -1;
            ballDirectionY *= -1;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BouncingBallAnimation animation = new BouncingBallAnimation();
            animation.setVisible(true);
            animation.startAnimation();
        });
    }
}
