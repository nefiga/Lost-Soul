package level;

import entity.MovableEntity;
import entity.UnmovableEntity;
import util.ImageManager;
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
        moved = new MovableEntity("moved", ImageManager.getImage("tiles/temp_tile.png"), 323, 403, 64, 64);
        moved.init(this);
        addEntity(moved);
        collision.addEntity(moved);


        moved1 = new MovableEntity("moved1", ImageManager.getImage("tiles/temp_tile.png"), 400, 403, 64, 64);
        moved1.init(this);
        addEntity(moved1);
        collision.addEntity(moved1);

        unmoved = new UnmovableEntity("unmoved", ImageManager.getImage("tiles/temp_tile.png"), 237, 149, 64, 64);
        unmoved.init(this);
        addEntity(unmoved);
        collision.addEntity(unmoved);

        unmoved1 = new UnmovableEntity("unmoved1", ImageManager.getImage("tiles/temp_tile.png"), 239, 213, 64, 64);
        unmoved1.init(this);
        addEntity(unmoved1);
        collision.addEntity(unmoved1);

        moved2 = new MovableEntity("moved2", ImageManager.getImage("tiles/temp_tile.png"), 479, 403, 64, 64);
        moved2.init(this);
        addEntity(moved2);
        collision.addEntity(moved2);
    }

    @Override
    public void initInput() {
    }

    @Override
    public void update() {

        player.update();

        updateStringEntities();
        updateEntities();
    }

    @Override
    public void render(Graphics2D g) {
        renderMap(g, (int) MainGame.getXOffset(), (int) MainGame.getYOffset());

        renderEntities(g);

        player.render(g);

        renderStringEntities(g);
    }
}
