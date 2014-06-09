package level;

import com.lust_gaming.engine.input.InputManager;
import com.lust_gaming.engine.level.Level;
import entity.Player;

import java.awt.*;

public class CustomLevel extends Level{

    protected Player player;

    public CustomLevel(String name, String imageFile, int width, int height, InputManager inputManager) {
        super(name, imageFile, width, height, inputManager);
    }

    public CustomLevel(String name, int width, int height, InputManager inputManager) {
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
    }

    public void addPlayer(Player player) {
        this.player = player;
        player.init(this);
    }
}
