package level;

import com.lust_gaming.engine.input.InputManager;
import util.MainGame;

import java.awt.*;

public class StartLevel extends CustomLevel{

    public static final String NAME = "StartGame";

    public StartLevel(String map, int width, int height, InputManager inputManager) {
        super(map, width, height, inputManager);
    }

    @Override
    public void init() {

    }

    @Override
    public void initInput() {

    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(Graphics2D g) {
        renderMap(g, (int) MainGame.getXOffset(), (int) MainGame.getYOffset());
        player.render(g);
    }
}
