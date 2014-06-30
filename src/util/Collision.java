package util;

import entity.Entity;
import entity.LivingEntity;
import level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Collision {

    Level level;

    List<Entity> entities = new ArrayList<Entity>();

    public Collision(Level level) {
        this.level = level;
    }

    public void addEntity(Entity entity) {
        if (entity.isMovable()) {
            entities.add(entity);
        } else entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public boolean interactWithEntity(Entity entity, int x, int y) {
        Rectangle rect = new Rectangle(x, y, 1, 1);
        for (int i = 0; i < entities.size(); i++) {
            Rectangle rect1 = entities.get(i).getRect();
            if (rect.intersects(rect1)) {
                entities.get(i).interact(level, entity);
                return true;
            }
        }
        return false;
    }

    public int getMaxMoveX(Entity entity, int moveX, Entity excludeEntity) {
        int distance = moveX;

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

        for (int i = 0; i < entities.size(); i++) {
            // Skips checks on its self and the exclude entity.
            if (entities.get(i) == entity || entities.get(i) == excludeEntity) continue;
            Entity entity1 = entities.get(i);
            Rectangle rect1 = entity1.getRect();

            if (rect.intersects(rect1)) {
                if (entity.canCollect() && entity1.collectable()) {
                    entity.collectItem(entity1.getItem());
                    level.removeEntity(entity1);
                    removeEntity(entity1);
                    continue;
                }

                while (rect.intersects(rect1)) {
                    distance = (int) Math.nextAfter(distance, 0);
                    rect.setLocation((int) entity.getX() + distance, (int) entity.getY());
                }

                if (entity1.isMovable()) {
                    distance = distance + entity1.pushX(entity, moveX - distance);
                }

                rect.setLocation((int) entity.getX() + distance, (int) entity.getY());
            }
        }

        return distance;
    }

    public int getMaxMoveY(Entity entity, int moveY, Entity excludeEntity) {
        int distance = moveY;

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

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == entity || entities.get(i) == excludeEntity) continue;
            Entity entity1 = entities.get(i);
            Rectangle rect1 = entity1.getRect();

            if (rect.intersects(rect1)) {
                if (entity.canCollect() && entity1.collectable()) {
                    entity.collectItem(entity1.getItem());
                    level.removeEntity(entity1);
                    removeEntity(entity1);
                    continue;
                }
                while (rect.intersects(rect1)) {
                    distance = (int) Math.nextAfter(distance, 0);
                    rect.setLocation((int) entity.getX(), (int) entity.getY() + distance);
                }

                if (entity1.isMovable()) {
                    distance = distance + entity1.pushY(entity, moveY - distance);
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

