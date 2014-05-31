package util;

import com.lust_gaming.engine.game.Game;
import com.lust_gaming.engine.input.GameAction;
import com.lust_gaming.engine.level.LevelHolder;
import level.StartLevel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainGame extends Game{

    // Levels
    StartLevel startLevel;

    // Inputs
    GameAction exit;

    @Override
    public void init() {
    }

    @Override
    public void initInput() {
        exit = new GameAction("exit");
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    }

    @Override
    public void initTile() {
    }

    @Override
    public void initLevels() {
        startLevel = new StartLevel("StartGame", screenManager.getWidth(), screenManager.getHeight(), inputManager);
        levelHolder.addNewLevel(startLevel);
        LevelHolder.setState(LevelHolder.getState("StartGame"));
    }

    @Override
    public void update() {
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
}
