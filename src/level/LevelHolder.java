package level;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LevelHolder {

    private static String currentLevel = null;

    private Map<String, Level> levels = new HashMap<String, Level>();

    /**
     * Renders the Level at the {@link #currentLevel}
     */
    public void render(Graphics2D g) {
        if (currentLevel != null) levels.get(currentLevel).render(g);
    }

    /**
     * Updates the Level at the {@link #currentLevel}
     */
    public void update() {
        if (currentLevel != null) levels.get(currentLevel).update();
    }

    /**
     * Adds a new Level to {@link #levels}
     * @param name String key for the {@link #levels}
     * @param level Level to be add to the {@link #levels}
     */
    public void addNewLevel(String name, Level level) {
        levels.put(name, level);
    }

    /**
     * Removes the specified Level.
     * @param name String the name of the Level to be removed.
     */
    public void removeLevel(String name) {
        levels.remove(name);
        if (levels.size() < 1) currentLevel = null;
    }

    /**
     * Sets the current Level to be updated and rendered.
     * @param name String name of the Level to be updated.
     */
    public void setCurrentLevel(String name) {
        currentLevel = name;
    }
}
