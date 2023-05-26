import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class ColorLoop extends JFrame implements Runnable {

    // Window size
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // Game loop
    private boolean running;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Random random;

    // Square location/ color variables
    private double x, y;
    private int dirX;
    private double dirY;
    private Color color;

    // Ground, gravity, and square size variables
    private static final int SQUARE_SIZE = 10;
    private static final int GROUND = HEIGHT - 100; // The position of the ground
    private static final double GRAVITY = 0.07; // The strength of the gravity

    private ArrayList<Rectangle> platforms; // List of platforms

    public ColorLoop() {
        // Open the window
        super("Colorful Trails");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(false);
        add(canvas);

        // Add key listener
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
                
                // Change color
                color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            }
            // Stop moving when key is released
            @Override
            public void keyReleased(KeyEvent e) {
                dirX = 0;
                if (!onGroundOrPlatform()) dirY = 0;
            }
        });

        x = WIDTH / 2;
        y = GROUND - SQUARE_SIZE;
        dirX = 0;
        dirY = 0;
        color = Color.BLACK;

        // Create platforms
        platforms = new ArrayList<>();
        platforms.add(new Rectangle(200, 300, 100, 10));
        platforms.add(new Rectangle(500, 200, 100, 10));

        random = new Random();
    }

    // Start the game loop
    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    // Call the rendering
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

    // Draw the game
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
        g.fillRect((int)x, (int)y, SQUARE_SIZE, SQUARE_SIZE);

        bufferStrategy.show();
        g.dispose();

        // Update position and check collisions
        double nextX = x + dirX;
        double nextY = y + dirY;

        // Check X-axis collision
        if (!collisionWithPlatform(nextX, y)) {
            x = nextX;
        } else {
            dirX = 0;
        }

        // Implement gravity
        if (!onGroundOrPlatform() && nextY < GROUND - SQUARE_SIZE) {
            dirY += GRAVITY;
        } else if (!onGroundOrPlatform()) {
            nextY = GROUND - SQUARE_SIZE;
            dirY = 0;
        }

        // Check Y-axis collision
        if (!collisionWithPlatform(x, nextY)) {
            y = nextY;
        } else {
            dirY = 0;
        }

        // Handle edge of screen
        if (x < 0) x = WIDTH;
        if (x > WIDTH) x = 0;
        if (y < 0) y = HEIGHT;
        if (y > HEIGHT) y = 0;
    }

    // Check if the square will collide with a platform
    private boolean collisionWithPlatform(double nextX, double nextY) {
        Rectangle nextPos = new Rectangle((int)nextX, (int)nextY, SQUARE_SIZE, SQUARE_SIZE);
        for (Rectangle platform : platforms) {
            if (platform.intersects(nextPos)) {
                return true;
            }
        }
        return false;
    }

    // Check if the square is on the ground or a platform
    private boolean onGroundOrPlatform() {
        Rectangle nextStep = new Rectangle((int)x, (int)(y + 1), SQUARE_SIZE, SQUARE_SIZE);
        for (Rectangle platform : platforms) {
            if (platform.intersects(nextStep)) {
                return true;
            }
        }
        return y >= GROUND - SQUARE_SIZE;
    }

    // Start the game
    public static void main(String[] args) {
        ColorLoop game = new ColorLoop();
        game.setVisible(true);
        game.start();
    }
}
