package level;

import entity.MovableEntity;
import entity.Player;
import entity.UnmovableEntity;
import tile.DefaultTile;
import tile.Tile;
import util.GameLoop;
import input.InputManager;

import java.awt.*;

public class CustomLevel extends Level{

    protected Player player;
    MovableEntity moved, moved1, moved2;
    UnmovableEntity unmoved, unmoved1;

    public CustomLevel(String imageFile, int width, int height, InputManager inputManager) {
        super(imageFile, width, height, inputManager);

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
        this.player = player;player.init(this);
        collision.addEntity(player);
    }


    public void addEntity() {

    }
}
