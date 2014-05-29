package util;

import input.GameAction;
import input.InputManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable{

    private InputManager inputManager;
    private ScreenManager screenManager;
    private Thread thread;

    private boolean running;

    public static final int TILE_SIZE = 64;

    // GameActions
    GameAction exit;

    private static final DisplayMode POSSIBLE_MODES[] = {
            new DisplayMode(1920, 1080, 32, 0),
            new DisplayMode(1920, 1080, 24, 0),
            new DisplayMode(1920, 1080, 16, 0),
            new DisplayMode(1024, 768, 32, 0),
            new DisplayMode(1024, 768, 24, 0),
            new DisplayMode(1024, 768, 16, 0),
            new DisplayMode(800, 600, 16, 0),
            new DisplayMode(800, 600, 32, 0),
            new DisplayMode(800, 600, 24, 0),
            new DisplayMode(640, 480, 16, 0),
            new DisplayMode(640, 480, 32, 0),
            new DisplayMode(640, 480, 24, 0),
    };

    public Game() {
        screenManager = new ScreenManager();
        DisplayMode displayMode = screenManager.findFirstCompatibleMode(POSSIBLE_MODES);
        screenManager.setFullScreen(displayMode);

        Window window = screenManager.getFullScreenWindow();
        window.setBackground(Color.green);
        window.setForeground(Color.blue);

        inputManager = new InputManager(screenManager.getFullScreenWindow());
    }

    private void init() {

    }

    private void initInput() {
        exit = new GameAction("exit");

        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    }

    private void update() {
        updateInput();
    }

    private void render(Graphics2D g) {

    }

    private void updateInput() {
        if (exit.isPressed()) stop();
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000 / 60.0;
        double updateTimer = 0;
        int frames = 0;
        int updates = 0;

        while (running) {
            long currentTime = System.nanoTime();
            updateTimer += (currentTime - startTime) / ns;
            startTime = currentTime;

            // Update
            if (updateTimer > 1) {
                update();
                updates++;
            }

            //Render
            Graphics2D g = screenManager.getGraphics();
            g.setBackground(Color.black);
            g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
            render(g);
            g.dispose();
            screenManager.show();
            frames++;

            // Tracking fps
            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println("UPS " + updates + "   FPS " + frames);
                updates = 0;
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

    public void start() {
        init();
        initInput();
        running = true;
        thread = new Thread(this, "Main Game");
        thread.start();
    }

    public void stop() {
        running = false;
        screenManager.restoreScreen();
        System.exit(0);
        try {
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int pixelToTile(int pixel) {
        int tile = pixel << 6;
        return tile;
    }

    public static int tileToPixel(int tile) {
        int pixel = tile >> 6;
        return pixel;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
