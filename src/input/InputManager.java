package input;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The InputManager manages input of key and mouse events. Events are mapped to GameActions.
 */
public class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    /**
     * An invisible cursor.
     */
    public static final Cursor INVISIBLE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(0, 0), "invisible");

    //  Mouse Codes
    public static final int MOUSE_MOVE_LEFT = 0;
    public static final int MOUSE_MOVE_RIGHT = 1;
    public static final int MOUSE_MOVE_UP = 2;
    public static final int MOUSE_MOVE_DOWN = 3;
    public static final int MOUSE_WHEEL_UP = 4;
    public static final int MOUSE_WHEEL_DOWN = 5;
    public static final int MOUSE_BUTTON_1 = 6;
    public static final int MOUSE_BUTTON_2 = 7;
    public static final int MOUSE_BUTTON_3 = 8;

    private static final int NUM_MOUSE_CODES = 9;

    //  Most key codes are lest the 600.
    private static final int NUM_KEY_CODES = 600;

    private static GameAction[] keyActions = new GameAction[NUM_KEY_CODES];
    private static GameAction[] mouseActions = new GameAction[NUM_MOUSE_CODES];

    private Point mouseLocation;
    private Point centerLocation;
    private Component comp;

    /**
     * Creates a new InputManager that listens to the input from the specified component.
     */
    public InputManager(Component comp) {
        this.comp = comp;

        //  Register mouse and key listeners.
        comp.addKeyListener(this);
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
        comp.addMouseWheelListener(this);

        //  Allow input of the TAB key and other keys normally used for focus traversal.
        comp.setFocusTraversalKeysEnabled(false);
    }

    /**
     * Sets the cursor on this InputManager's input component.
     *
     * @param cursor The cursor to be used
     */
    public void setCursor(Cursor cursor) {
        comp.setCursor(cursor);
    }

    /**
     * Maps a GameAction to a specific key. If the key already has a GameAction mapped to it, the new GameAction overwrites in.
     *
     * @param gameAction
     * @param keyCode
     */
    public void mapToKey(GameAction gameAction, int keyCode) {
        keyActions[keyCode] = gameAction;
    }

    /**
     * Maps a GameAction to a specific mouse action. If the mouse action already has a GameAction mapped to it, the new GameAction overwrites it.
     *
     * @param gameAction
     * @param mouseCode
     */
    public void mapToMouse(GameAction gameAction, int mouseCode) {
        mouseActions[mouseCode] = gameAction;
    }

    /**
     * Clears all mapped keys and mouse actions to this GameAction.
     *
     * @param gameAction
     */
    public void clearMap(GameAction gameAction) {
        for (int i = 0; i < keyActions.length; i++) {
            if (keyActions[i] == gameAction) {
                keyActions[i] = null;
            }
        }

        for (int i = 0; i < mouseActions.length; i++) {
            if (mouseActions[i] == gameAction) {
                mouseActions[i] = null;
            }
        }
        gameAction.reset();
    }

    /**
     * Gets a List of names of the keys and mouse actions mapped to this GameAction. Each entry in the List is a String.
     *
     * @param gameCode
     * @return
     */
    public ArrayList getMaps(GameAction gameCode) {
        ArrayList list = new ArrayList();

        for (int i = 0; i < keyActions.length; i++) {
            if (keyActions[i] == gameCode) {
                list.add(getKeyName(i));
            }
        }

        for (int i = 0; i < mouseActions.length; i++) {
            if (mouseActions[i] == gameCode) {
                list.add(getMouseName(i));
            }
        }
        return list;
    }

    /**
     * Resets all GameActions so they appear like they haven't been pressed.
     */
    public static void resetAllGameActions() {
        for (int i = 0; i < keyActions.length; i++) {
            if (keyActions[i] != null) {
                keyActions[i].reset();
            }
        }

        for (int i = 0; i < mouseActions.length; i++) {
            if (mouseActions[i] != null) {
                mouseActions[i].reset();
            }
        }
    }

    /**
     * Gets the name of a key code
     *
     * @param keyCode
     * @return
     */
    public static String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }

    /**
     * Gets the name of a mouse code
     *
     * @param mouseCode
     * @return
     */
    public static String getMouseName(int mouseCode) {
        switch (mouseCode) {
            case MOUSE_MOVE_LEFT:
                return "Mouse Left";
            case MOUSE_MOVE_RIGHT:
                return "Mouse Right";
            case MOUSE_MOVE_UP:
                return "Mouse Up";
            case MOUSE_MOVE_DOWN:
                return "Mouse Down";
            case MOUSE_WHEEL_UP:
                return "Mouse Wheel Up";
            case MOUSE_WHEEL_DOWN:
                return "Mouse Wheel Down";
            case MOUSE_BUTTON_1:
                return "Mouse Button 1";
            case MOUSE_BUTTON_2:
                return "Mouse Button 2";
            case MOUSE_BUTTON_3:
                return "Mouse Button 3";
            default:
                return "Unknown mouse code " + mouseCode;
        }
    }

    /**
     * Gets the x position of the mouse.
     *
     * @return mouse x position.
     */
    public int getMouseX() {
        return mouseLocation.x;
    }

    /**
     * Gets the y position of the mouse.
     *
     * @return mouse y position.
     */
    public int getMouseY() {
        return mouseLocation.y;
    }

    private GameAction getKeyAction(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < keyActions.length) {
            return keyActions[keyCode];
        } else {
            return null;
        }
    }

    /**
     * Gets the mouse code for the button specified in this MouseEvent.
     *
     * @param e
     * @return
     */
    public static int getMouseButtonCode(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                return MOUSE_BUTTON_1;
            case MouseEvent.BUTTON2:
                return MOUSE_BUTTON_2;
            case MouseEvent.BUTTON3:
                return MOUSE_BUTTON_3;
            default:
                return -1;
        }
    }

    private GameAction getMouseButtonAction(MouseEvent e) {
        int mouseCode = getMouseButtonCode(e);
        if (mouseCode != -1) {
            return mouseActions[mouseCode];
        }
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if (gameAction != null) {
            gameAction.press();
        }
        //make sure the key isn't processed for anything else
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if (gameAction != null) {
            gameAction.release();
        }
        //make sure the key isn't processed for anything else
        e.consume();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //  Do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        GameAction gameAction = getMouseButtonAction(e);
        if (gameAction != null) {
            gameAction.press();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameAction gameAction = getMouseButtonAction(e);
        if (gameAction != null) {
            gameAction.release();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseHelper(MOUSE_WHEEL_UP, MOUSE_WHEEL_DOWN,
                e.getWheelRotation());
    }

    private void mouseHelper(int codeNeg, int codePos,
                             int amount) {
        GameAction gameAction;
        if (amount < 0) {
            gameAction = mouseActions[codeNeg];
        } else {
            gameAction = mouseActions[codePos];
        }
        if (gameAction != null) {
            gameAction.press(Math.abs(amount));
            gameAction.release();
        }
    }

}
