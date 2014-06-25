package util;

import entity.Entity;
import level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collision {

    Level level;

    List<String> names = new ArrayList<String>();
    Map<String, Entity> entities = new HashMap<String, Entity>();

    public Collision(Level level) {
        this.level = level;
    }

    /**
     * Adds an Entity to the HashMap at the {@code name}
     *
     * @param name   Key for the HashMap
     * @param entity The Entity to be added to the HashMap
     */
    public void addEntity(String name, Entity entity) {
        if (!entities.containsKey(name)) {
            entities.put(name, entity);
            if (entity.isMovable()) {
                names.add(name);
            }
            else  names.add(0, name);
        }
    }

    public boolean interactWithEntity(Entity entity, int x, int y) {
        Rectangle rect = new Rectangle(x, y, 1, 1);
        for (int i = 0; i < names.size(); i++) {
            Rectangle rect1 = entities.get(names.get(i)).getRect();
            if (rect.intersects(rect1)) {
                entities.get(names.get(i)).interact(level, entity);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the Rectangle of the Entity at {@code name} is colliding with an other Rectangle and if that Rectangle can be pushed.
     * Also checks if there are any collisions with Tiles.
     *
     * @param name       The {@code name} of the Entity which holds the Rectangle to be checked.
     * @param moveX      The amount the Rectangle is to be moved on the X axis
     * @param moveY      The amount the Rectangle is to be moved on the Y axis
     * @param excludeBox A Rectangle to be excluded from the collision check.
     * @return True if Rectangle of the Entity at {@code name} is not intersecting any other Rectangles or the Rectangles it is intersecting can be pushed.
     */
    public boolean collision(String name, int moveX, int moveY, String excludeBox) {
        Entity entity = entities.get(name);

        // Checking Tile collision
        int xa = (int) entity.getX() + moveX;
        int ya = (int) entity.getY() + moveY;
        if (level.getTile(xa, ya, false).solid()) return true;
        if (level.getTile(xa + 63, ya, false).solid()) return true;
        if (level.getTile(xa, ya + 63, false).solid()) return true;
        if (level.getTile(xa + 63, ya + 63, false).solid()) return true;


        // Checking Rectangle collision
        Rectangle rect = entity.getRect();
        // Moves the Rectangle prior to check if there are any collisions.
        rect.translate(moveX, moveY);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name) || names.get(i).equals(excludeBox)) continue;
            Entity entity1 = entities.get(names.get(i));

            if (rect.intersects(entity1.getRect())) {
                if (entity1.push(name, moveX, moveY)) continue;

                rect.translate(-moveX, -moveY);
                return true;
            }
        }
        return false;
    }

    /**
     * Check for a collision on the X axis.
     * @param name Name of the entity that is checking for collision.
     * @param moveX The desired amount to be moved on the X axis
     * @param excludeBox A Rectangle to be excluded from the collision detection.
     * @return  The max amount that the entity can move.
     */
    public int getMaxMoveX(String name, int moveX, String excludeBox) {
        int distance = moveX;
        Entity entity = entities.get(name);

        // Checking Tile collision
        int xa = (int) entity.getX() + distance;
        int ya = (int) entity.getY();

        while (tileCollision(xa, ya)) {
            distance = (int) Math.nextAfter(distance, 0);
            xa = (int) entity.getX() + distance;
        }


        // Checking Rectangle collision
        Rectangle rect = entity.getRect();
        // Moves the Rectangle prior to check if there are any collisions.
        rect.translate(distance, 0);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name) || names.get(i).equals(excludeBox)) continue;
            Entity entity1 = entities.get(names.get(i));
            Rectangle rect1 = entity1.getRect();

            if (rect.intersects(rect1)) {
                while (rect.intersects(rect1)) {
                    distance = (int) Math.nextAfter(distance, 0);
                    rect.setLocation( (int) entity.getX() + distance, (int) entity.getY());
                }

                if (entity1.isMovable()) {
                    distance = distance + entity1.pushX(name, moveX - distance);
                }

                rect.setLocation((int) entity.getX() + distance, (int) entity.getY());
            }
        }

        return distance;
    }

    /**
     * Check for a collision on the Y axis.
     * @param name Name of the entity that is checking for collision.
     * @param moveY The desired amount to be moved on the Y axis
     * @param excludeBox A Rectangle to be excluded from the collision detection.
     * @return  The max amount that the entity can move.
     */
    public int getMaxMoveY(String name, int moveY, String excludeBox) {
        int distance = moveY;
        Entity entity = entities.get(name);

        // Checking Tile collision
        int xa = (int) entity.getX();
        int ya = (int) entity.getY() + distance;

        while (tileCollision(xa, ya)) {
            distance = (int) Math.nextAfter(distance, 0);
            ya = (int) entity.getY() + distance;
        }


        // Checking Rectangle collision
        Rectangle rect = entity.getRect();
        // Moves the Rectangle prior to check if there are any collisions.
        rect.translate(0, distance);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name) || names.get(i).equals(excludeBox)) continue;
            Entity entity1 = entities.get(names.get(i));
            Rectangle rect1 = entity1.getRect();

            if (rect.intersects(rect1)) {
                while (rect.intersects(rect1)) {
                    distance = (int) Math.nextAfter(distance, 0);
                    rect.setLocation( (int) entity.getX(), (int) entity.getY() + distance);
                }

                if (entity1.isMovable()) {
                    distance = distance + entity1.pushY(name, moveY - distance);
                }

                rect.setLocation((int) entity.getX(), (int) entity.getY() + distance);
            }
        }

        return distance;
    }

    private boolean tileCollision(int xa, int ya) {
        if (level.getTile(xa, ya, false).solid()) return true;
        if (level.getTile(xa + 63, ya, false).solid()) return true;
        if (level.getTile(xa, ya + 63, false).solid()) return true;
        if (level.getTile(xa + 63, ya + 63, false).solid()) return true;
        return false;
    }
}

