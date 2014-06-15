package level;

import com.lust_gaming.engine.game.Game;
import com.lust_gaming.engine.input.InputManager;
import com.lust_gaming.engine.level.Level;
import com.lust_gaming.engine.tile.DefaultTile;
import com.lust_gaming.engine.tile.Tile;
import entity.Player;
import util.MainGame;

import java.awt.*;

public class CustomLevel extends Level{

    protected Player player;

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

    @Override
    public Tile getTile(float xa, float ya, boolean tilePrecision) {
        int x, y;
        if (tilePrecision) {
            x = (int) xa;
            y = (int) ya;
        }
        else {
            x = Game.pixelToTile(xa);
            y = Game.pixelToTile(ya);
        }

        if (x > imageWidth - 1 || x < 0 || y > imageHeight - 1 || y < 0) return DefaultTile.defaultTile;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (tile.getId() == map[x + y * imageWidth]) return tile;
        }
        return DefaultTile.defaultTile;
    }

    public void addPlayer(Player player) {
        this.player = player;
        player.init(this);
    }
}
