package level;

import entity.MovableEntity;
import entity.UnmovableEntity;
import util.InputManager;
import util.MainGame;

import java.awt.*;

public class StartLevel extends CustomLevel{

    public static final String NAME = "StartGame";

    public StartLevel(String map, int width, int height, InputManager inputManager) {
        super(map, width, height, inputManager);
    }

    @Override
    public void init() {
        moved = new MovableEntity("moved", "tiles/temp_tile.png", 323, 403, 64, 64);
        moved.init(this);
        collision.addEntity(moved.getName(), moved);


        moved1 = new MovableEntity("moved1", "tiles/temp_tile.png", 400, 403, 64, 64);
        moved1.init(this);
        collision.addEntity(moved1.getName(), moved1);

        unmoved = new UnmovableEntity("unmoved", "tiles/temp_tile.png", 237, 149, 64, 64);
        unmoved.init(this);
        collision.addEntity(unmoved.getName(), unmoved);

        unmoved1 = new UnmovableEntity("unmoved1", "tiles/temp_tile.png", 239, 213, 64, 64);
        unmoved1.init(this);
        collision.addEntity(unmoved1.getName(), unmoved1);

        moved2 = new MovableEntity("moved2", "tiles/temp_tile.png", 479, 403, 64, 64);
        moved2.init(this);
        collision.addEntity(moved2.getName(), moved2);
    }

    @Override
    public void initInput() {
    }

    @Override
    public void update() {

        player.update();

        updateStringEntities();
    }

    @Override
    public void render(Graphics2D g) {
        renderMap(g, (int) MainGame.getXOffset(), (int) MainGame.getYOffset());

        moved.render(g);
        moved1.render(g);
        moved2.render(g);
        unmoved.render(g);
        unmoved1.render(g);
        player.render(g);

        renderStringEntities(g);
    }
}
