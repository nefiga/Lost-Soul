package util;

import com.lust_gaming.engine.game.Game;
import com.lust_gaming.engine.input.GameAction;
import com.lust_gaming.engine.level.LevelHolder;
import com.lust_gaming.engine.tile.Tile;
import entity.Player;
import level.StartLevel;
import tile.DirtTile;
import tile.GrassTile;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainGame extends Game {

    // Levels
    StartLevel startLevel;

    // Inputs
    GameAction exit;

    Player player;
    private static float xOffset, yOffset;

    @Override
    public void init() {
        tileSize = 64;
        player = new Player("tiles/temp_tile.png", screenManager.getWidth() / 2, screenManager.getHeight() / 2, inputManager);
    }

    @Override
    public void initInput() {
        exit = new GameAction("exit");
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    }

    @Override
    public void initTile() {
        Tile.addTile(GrassTile.grassTile);
        Tile.addTile(DirtTile.dirtTile);
    }

    @Override
    public void initLevels() {
        startLevel = new StartLevel("StartGame", "sweet_map.png", screenManager.getWidth(), screenManager.getHeight(), inputManager);
        startLevel.addPlayer(player);
        levelHolder.addNewLevel(startLevel);
        LevelHolder.setState(LevelHolder.getState("StartGame"));
    }

    @Override
    public void update() {
        xOffset = player.getX() - screenManager.getWidth() / 2;
        yOffset = player.getY() - screenManager.getHeight() / 2 ;

        levelHolder.update();

        // Exit game
        if (exit.isPressed()) stop();
    }

    @Override
    public void render(Graphics2D g) {
        levelHolder.render(g);
    }

    public static void main(String[] args) {
        MainGame game = new MainGame();
        game.start();
    }

    public static float getXOffset() {
        return xOffset;
    }

    public static float getYOffset() {
        return yOffset;
    }
}
