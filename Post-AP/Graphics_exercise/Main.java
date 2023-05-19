import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class Main extends JFrame implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private boolean running;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Random random;

    private int x, y;
    private double dirX, dirY;
    private Color color;

    private static final int SQUARE_SIZE = 10;
    private static final int GROUND = HEIGHT - 100; // The position of the ground
    private static final double GRAVITY = 0.07; // The strength of the gravity

    private ArrayList<Rectangle> platforms; // List of platforms

    public Main() {
        super("Colorful Trails");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(false);
        add(canvas);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        dirY = -2; break;
                    case KeyEvent.VK_DOWN:
                        dirY = 2; break;
                    case KeyEvent.VK_LEFT:
                        dirX = -2; break;
                    case KeyEvent.VK_RIGHT:
                        dirX = 2; break;
                }
                color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            }
            @Override
            public void keyReleased(KeyEvent e) {
                dirX = 0;
                if (y < GROUND - SQUARE_SIZE) dirY = 0;
            }
        });

        x = WIDTH / 2;
        y = GROUND - SQUARE_SIZE;
        dirX = 0;
        dirY = 0;
        color = Color.BLACK;

        // Initialize platforms
        platforms = new ArrayList<>();
        platforms.add(new Rectangle(200, 300, 100, 10));
        platforms.add(new Rectangle(500, 200, 100, 10));

        random = new Random();
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            render();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void render() {
        bufferStrategy = canvas.getBufferStrategy();
        if (bufferStrategy == null) {
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
        }
        Graphics g = bufferStrategy.getDrawGraphics();

        // Draw over old trails
        g.setColor(new Color(255, 255, 255, 15));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the ground
        g.setColor(Color.GRAY);
        g.fillRect(0, GROUND, WIDTH, HEIGHT - GROUND);

        // Draw the platforms
        for (Rectangle platform : platforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }

        // Draw the square
        g.setColor(color);
        g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

        bufferStrategy.show();
        g.dispose();

        // Movement
        x += dirX;
        y += dirY;

        // Implement gravity
        if (!onPlatform() && y < GROUND - SQUARE_SIZE) {
            dirY += GRAVITY;
        } else if (!onPlatform()) {
            y = GROUND - SQUARE_SIZE;
            dirY = 0;
        }

        if (x < 0) x = WIDTH;
        if (x > WIDTH) x = 0;
        if (y < 0) y = HEIGHT;
        if (y > HEIGHT) y = 0;
    }

    // Check if the square is on a platform
    private boolean onPlatform() {
        Rectangle square = new Rectangle(x, y, SQUARE_SIZE, SQUARE_SIZE);
        for (Rectangle platform : platforms) {
            if (platform.intersects(square)) {
                y = platform.y - SQUARE_SIZE;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);
        game.start();
    }
}
