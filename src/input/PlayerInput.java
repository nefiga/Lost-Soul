package input;

import entity.Player;
import util.GameAction;
import util.MainGame;

import java.awt.event.KeyEvent;

public class PlayerInput extends Input {

    Player player;

    private static String name = "PlayerInput";

    public PlayerInput(Player player, GameInput gameInput) {
        super(gameInput);
        this.player = player;
    }

    public void update() {
        updateInput();
    }

    private void updateInput() {
        movement();
        actionBar();
        mouseEvents();
        if (gameInput.openInventory.isPressed()) player.openInventory();
        if (gameInput.exit.isPressed()) MainGame.stop();
    }

    private void mouseEvents() {
        if (gameInput.leftClick.isPressed()) {
            int clickX = gameInput.getMouseX() + MainGame.getMouseOffsetX();
            int clickY = gameInput.getMouseY() + MainGame.getMouseOffsetY();
            player.interact(clickX, clickY);
        }
    }

    private void actionBar() {
        if (gameInput.action1.isPressed()) player.setActionbarSlot(0);
        else if (gameInput.action2.isPressed()) player.setActionbarSlot(1);
        else if (gameInput.action3.isPressed()) player.setActionbarSlot(2);
        else if (gameInput.action4.isPressed()) player.setActionbarSlot(3);
    }

    private void movement() {
        float velocityX = 0, velocityY = 0;
        int direction = 0;
        if (gameInput.left.isPressed()) {
            velocityX = -5;
            direction = 3;
        }
        if (gameInput.right.isPressed()) {
            velocityX = 5;
            direction = 1;
        }
        if (gameInput.up.isPressed()) {
            velocityY = -5;
            direction = 0;
        }
        if (gameInput.down.isPressed()) {
            velocityY = 5;
            direction = 2;
        }
        if (velocityX != 0) player.moveX(velocityX);
        if (velocityY != 0) player.moveY(velocityY);
        player.changeDirection(direction);
    }

    public static String getName() {
        return name;
    }
}
