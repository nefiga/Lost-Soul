package input;

import java.util.HashMap;
import java.util.Map;

public class InputUpdater {

    private static String currentInput = null;
    private static Map<String, Input> inputs = new HashMap<String, Input>();

    public InputUpdater() {
    }

    public void update() {
        inputs.get(currentInput).update();
    }

    public static void addInput(String name, Input input) {
        if (!inputs.containsKey(name)) inputs.put(name, input);
    }

    public static void removeInput(String name) {
        inputs.remove(name);
    }

    public static void setCurrentInput(String name) {
        InputManager.resetAllGameActions();
        currentInput = name;
    }

}
