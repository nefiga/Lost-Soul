package input;

import inventory.Inventory;

public class InventoryInput extends Input{

    Inventory inventory;

    private static String name = "InventoryInput";

    public InventoryInput(Inventory inventory, GameInput gameInput) {
        super(gameInput);
        this.inventory = inventory;
    }

    public void update() {
        if (gameInput.leftClick.isPressed()) inventory.clickItemSlot(InputManager.getMouseX(), InputManager.getMouseY());
        if (gameInput.exit.isPressed()) inventory.closeInventory();
        if (gameInput.openInventory.isPressed()) inventory.closeInventory();
    }

    public static String getName() {
        return name;
    }
}
