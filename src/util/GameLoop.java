package util;

import input.InputManager;
import input.InputUpdater;
import level.LevelHolder;

import java.awt.*;

public class GameLoop implements Runnable{

    protected InputManager inputManager;
    protected static ScreenManager screenManager;
    protected LevelHolder levelHolder;
    protected InputUpdater inputUpdater;
    protected static Thread thread;

    private static boolean running;
    protected static int tileSize;

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

    public GameLoop() {
        levelHolder = new LevelHolder();
        screenManager = new ScreenManager();
        DisplayMode displayMode = screenManager.findFirstCompatibleMod(POSSIBLE_MODES);
        screenManager.setFullScreen(displayMode);

        Window window = screenManager.getFullScreenWindow();
        window.setFont(new Font("Dialog", Font.PLAIN, 24));
        window.setBackground(Color.green);
        window.setForeground(Color.blue);
        inputManager = new InputManager(screenManager.getFullScreenWindow());
        inputUpdater = new InputUpdater();
    }

    /**
     * General initialize method. Called when the game is started.
     */
    public void init() {
    }

    /**
     * This is where all the general inputs should be initialized.
     * You can still initialize specific input for each level when it is created.
     */
    public void initInput() {
    }

    /**
     * This is where all your tiles should be added to your {@code Tile} {@code allTiles} arrayList.
     * You must add all tiles to this list.
     */
    public void initTile() {

    }

    /**
     * This is where all your {@code Level} classes should be instantiated and added to the LevelHolder class.
     */
    public void initLevels() {

    }

    /**
     * Update is called 60 times a second. This is where you should put anything that needs to be update (Players, Monsters, ect...).
     */
    public void update() {
    }

    public void render(Graphics2D g) {
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000.0 / 60.0;
        double updateTimer = 0;
        int frames = 0;
        int updates = 0;

        while (running) {
            long currentTime = System.nanoTime();
            updateTimer += (currentTime - startTime) / ns;
            startTime = currentTime;

            //  Update
            if (updateTimer > 1) {
                update();
                updates++;
            }

            //  Render
            Graphics2D g = screenManager.getGraphics();
            g.setColor(Color.blue);
            g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
            render(g);
            g.dispose();
            screenManager.show();
            frames++;

            // Tracking fps
            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println("UPS " + updates + "    FPS " + frames);
                updates = 0;
                frames = 0;
                timer += 1000;
            }

        }

        stop();
    }

    /**
     * Starts the main game loop and creates and starts the main Thread.
     * Call this in your main method after creating the game instance.
     */
    public void start() {
        initTile();
        init();
        initInput();
        initLevels();
        running = true;
        thread = new Thread(this, "Main Game");
        thread.start();
    }

    /**
     * Stops the main game loop. Also disposes of the window and stops the game Thread.
     */
    public static void stop() {
        running = false;
        screenManager.restoreScreen();
        System.exit(0);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts an in into pixel precision.
     * @param t The int you want converted.
     * @return int converted into pixel precision.
     */
    public static int tileToPixel(float t) {
        int tile = (int) t;
        int pixel = tile * tileSize;
        return pixel;
    }

    /**
     * Converts an int into tile precision.
     * @param p The int you want converted.
     * @return int converted into tile precision.
     */
    public static int pixelToTile(float p) {
        int pixel = (int) p;
        int tile = pixel / tileSize;
        return tile;
    }

    /**
     *
     * @return The tile size set for this game.
     */
    public static int getTileSize() {
        return tileSize;
    }

}
