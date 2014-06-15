package collision;

import entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoundingBox {

    List<String> names = new ArrayList<String>();
    Map<String, Entity> entities = new HashMap<String, Entity>();

    public void addEntity(String name, Entity entity) {
        if (!entities.containsKey(name)){
            entities.put(name, entity);
            names.add(name);
        }
    }

    public boolean canMove(String name, int x, int y) {
        return true;
    }
}
