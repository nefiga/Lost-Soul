package input;

import util.GameAction;

import java.awt.event.KeyEvent;

public class GameInput {
    protected InputManager inputManager;

    // Keyboard inputs
    protected GameAction left, right, up, down, interact, openInventory, action1, action2, action3, action4, exit;

    // Mouse inputs
    protected GameAction leftClick, rightClick;

    public GameInput(InputManager inputManager) {
        this.inputManager = inputManager;
        initInput();
    }

    public void update() {

    }

    public void initInput() {
        left = new GameAction("left");
        right = new GameAction("right");
        up = new GameAction("up");
        down = new GameAction("down");
        interact = new GameAction("interact", GameAction.DETECT_INITIAL_PRESS_ONLY);
        openInventory = new GameAction("openInventory", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action1 = new GameAction("action1", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action2 = new GameAction("action2", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action3 = new GameAction("action3", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action4 = new GameAction("action4", GameAction.DETECT_INITIAL_PRESS_ONLY);
        exit = new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY);

        leftClick = new GameAction("leftClick", GameAction.DETECT_INITIAL_PRESS_ONLY);
        rightClick = new GameAction("rightClick", GameAction.DETECT_INITIAL_PRESS_ONLY);

        inputManager.mapToKey(left, KeyEvent.VK_A);
        inputManager.mapToKey(right, KeyEvent.VK_D);
        inputManager.mapToKey(up, KeyEvent.VK_W);
        inputManager.mapToKey(down, KeyEvent.VK_S);
        inputManager.mapToKey(interact, KeyEvent.VK_SPACE);
        inputManager.mapToKey(openInventory, KeyEvent.VK_M);
        inputManager.mapToKey(action1, KeyEvent.VK_1);
        inputManager.mapToKey(action2, KeyEvent.VK_2);
        inputManager.mapToKey(action3, KeyEvent.VK_3);
        inputManager.mapToKey(action4, KeyEvent.VK_4);
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);

        inputManager.mapToMouse(leftClick, InputManager.MOUSE_BUTTON_1);
        inputManager.mapToMouse(rightClick, InputManager.MOUSE_BUTTON_3);
    }

    public int getMouseX() {
        return inputManager.getMouseX();
    }

    public int getMouseY() {
        return inputManager.getMouseY();
    }
}
