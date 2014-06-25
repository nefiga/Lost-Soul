package level;

import tile.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MapGenerator {


    public static int[] generateMap(BufferedImage image, int width, int height) {
        int[] tiles = new int[width * height];
        image.getRGB(0, 0, width, height, tiles, 0, width);
        return tiles;
    }

    public static List<Tile> getTiles(Level level, BufferedImage image, int width, int height) {
        int[] map = new int[width * height];
        image.getRGB(0, 0, width, height, map, 0, width);

        List<Tile> tiles = new ArrayList<Tile>();
        List<Tile> tileList = Tile.getTileList();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < tileList.size(); j++) {
                Tile tile = tileList.get(j);
                if (tile.getId() == map[i]) {
                    tiles.add(tile);
                    level.setTileDurability(i, tile.getDurability());
                }
            }
        }
        return tiles;
    }
}
