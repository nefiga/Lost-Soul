package util;

import entity.Player;
import input.*;
import level.StartLevel;
import tile.DefaultTile;
import tile.DirtTile;
import tile.GrassTile;
import tile.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainGame extends GameLoop {

    // Levels
    StartLevel startLevel;

    // Inputs
    private static GameInput gameInput;

    Player player;
    private static float xOffset, yOffset;
    private static int mouseOffsetX = 0, mouseOffsetY = 0;
    public static int centerX, centerY;

    @Override
    public void init() {
        tileSize = 64;
        gameInput = new GameInput(inputManager);
        player = new Player("player1", ImageManager.getImage("tiles/temp_tile.png"), screenManager.getWidth() / 2, screenManager.getHeight() / 2, 64, 64);
        PlayerInput playerInput = new PlayerInput(player, gameInput);
        InputUpdater.addInput(PlayerInput.getName(), playerInput);
        InputUpdater.setCurrentInput(PlayerInput.getName());
        centerX = screenManager.getWidth() / 2;
        centerY = screenManager.getHeight() / 2;
    }

    @Override
    public void initInput() {

    }

    @Override
    public void initTile() {
        Tile.addTile(GrassTile.grassTile);
        Tile.addTile(DirtTile.dirtTile);
        Tile.addTile(DefaultTile.defaultTile);
    }

    @Override
    public void initLevels() {
        startLevel = new StartLevel("sweet_map.png", screenManager.getWidth(), screenManager.getHeight(), inputManager);
        startLevel.addPlayer(player);
        levelHolder.addNewLevel(StartLevel.NAME, startLevel);
        levelHolder.setCurrentLevel(StartLevel.NAME);
    }

    @Override
    public void update() {
        xOffset = player.getX() - screenManager.getWidth() / 2;
        yOffset = player.getY() - screenManager.getHeight() / 2;

        inputUpdater.update();
        levelHolder.update();

    }

    @Override
    public void render(Graphics2D g) {
        levelHolder.render(g);
    }

    public static void main(String[] args) {
        MainGame game = new MainGame();
        game.start();
    }

    public static GameInput getGameInput() {
        return gameInput;
    }

    public static float getXOffset() {
        return xOffset;
    }

    public static float getYOffset() {
        return yOffset;
    }

    public static int getMouseOffsetX() {
        return mouseOffsetX;
    }

    public static int getMouseOffsetY() {
        return mouseOffsetY;
    }

    public static void changeMouseOffsetX(int x) {
        mouseOffsetX += x;
    }

    public static void changeMouseOffsetY(int y) {
        mouseOffsetY += y;
    }
}
