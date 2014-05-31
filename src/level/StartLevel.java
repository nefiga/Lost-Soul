package level;

import com.lust_gaming.engine.input.InputManager;
import com.lust_gaming.engine.level.Level;

import java.awt.*;

public class StartLevel extends Level{

    public StartLevel(String name, int width, int height, InputManager inputManager) {
        super(name, width, height, inputManager);
    }

    @Override
    public void init() {

    }

    @Override
    public void initInput() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Testing", 50, 50);
    }
}
