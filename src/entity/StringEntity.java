package entity;

import java.awt.*;

public class StringEntity extends Entity {

    Font font;
    Color color;
    Movement movement;

    int startArch = 0;
    float archAngleX = 0;
    int second = 0;
    int life;

    /**
     * Creates a String Entity
     * @param name The String of the Entity
     * @param x The x position
     * @param y The y position
     * @param size Size of the text
     * @param movement The kind of movement if any
     * @param life The life of the string
     * @param color The color of the String
     */
    public StringEntity(String name, int x, int y, int size, Movement movement, int life, Color color) {
        super(name, x, y);
        this.color = color;
        this.movement = movement;
        this.life = life;
        font = new Font(null, Font.PLAIN, size);
    }

    public void update() {
        move();

        second++;
        if (second >= 60) {
            life--;
            second = 0;
            if (life <= 0) remove();
        }
    }

    public void render(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(name, x, y);
    }

    public void move() {
        switch (movement) {
            case NONE:
                break;
            case UP:
                y -= 1.5;
                break;
            case DOWN:
                y += 1.5;
                break;
            case RIGHT:
                x += 1.5;
                break;
            case LEFT:
                x -= 1.5;
                break;
            case ARCH_LEFT:
                startArch++;
                y -= 1.5;
                if (startArch > 25) {
                    archAngleX -= .02;
                    x += archAngleX;
                }
                break;
            case ARCH_RIGHT:
                startArch++;
                y -= 1.5;
                if (startArch > 25) {
                    archAngleX += .02;
                    x += archAngleX;
                }
                break;
        }
    }

    public static enum Movement {
        NONE, UP, DOWN, RIGHT, LEFT, ARCH_LEFT, ARCH_RIGHT
    }
}

