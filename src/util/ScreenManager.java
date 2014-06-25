package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class ScreenManager {

    private GraphicsDevice graphicsDevice;

    /**
     * Creates a new ScreenManager Object.
     */
    public ScreenManager() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = environment.getDefaultScreenDevice();
    }

    /**
     * Returns a list of compatible display modes for the default device on the system.
     */
    public DisplayMode[] getCompatibleDisplayModes() {
        return graphicsDevice.getDisplayModes();
    }

    /**
     * Returns the first compatible mode in a list of modes.
     * Returns null if no modes are compatible.
     */
    public DisplayMode findFirstCompatibleMod(DisplayMode modes[]) {
        DisplayMode goodModes[] = graphicsDevice.getDisplayModes();
        for (int i = 0; i < modes.length; i++) {
            for (int j = 0; j < goodModes.length; j++) {
                if (displayModesMatch(modes[i], goodModes[j])) {
                    return modes[i];
                }
            }
        }
        return null;
    }

    /**
     * Returns the current display mode.
     */
    public DisplayMode getCurrentDisplayMode() {
        return graphicsDevice.getDisplayMode();
    }

    /**
     * Determines if two display modes "match". Two display modes match if they have the same resolution,
     * bit depth, and refresh rate. The bit depth is ignored if one of the modes has a bit depth of DisplayMode.BIT_DEPTH_MULTI.
     * Likewise, the refresh rate is ignored if one of the modes has a refresh rate of DisplayMode.REFRESH_RATE_UNKNOWN.
     */
    public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
        if (mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight()) return false;
        if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && mode1.getBitDepth() != mode2.getBitDepth()) return false;
        if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && mode1.getRefreshRate() != mode2.getRefreshRate()) return false;
        return true;
    }

    /**
     * Enters full screenManager mode and changes the display mode. If the specified display mode is null or not compatible
     * with this device, or if the display mode cannot be changed on this system, the current display mode is used.
     * The display uses a BufferStrategy with 3 buffers.
     */
    public void setFullScreen(DisplayMode displayMode) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);

        graphicsDevice.setFullScreenWindow(frame);
        if (displayMode != null && graphicsDevice.isDisplayChangeSupported()) {
            try {
                graphicsDevice.setDisplayMode(displayMode);
            }
            catch (IllegalArgumentException ex) {
            }
            frame.createBufferStrategy(3);
        }
    }

    /**
     * Gets the graphics context for the display. The ScreenManager uses double buffering, so applications must call show()
     * to show any graphics drawn.
     * The application must dispose of the graphics object.
     */
    public Graphics2D getGraphics() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            BufferStrategy bs = window.getBufferStrategy();
            return (Graphics2D)bs.getDrawGraphics();
        }
        return null;
    }

    /**
     * updates the display.
     */
    public void show() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            BufferStrategy bs = window.getBufferStrategy();
            if (!bs.contentsLost()) {
                bs.show();
            }
        }
        //Sync the display on some systems
        //(on Linux, this fixes event queue problems)
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Returns the window currently used in full screenManager mode.
     * Returns null if the device is not in full screenManager mode.
     */
    public Window getFullScreenWindow() {
        return  graphicsDevice.getFullScreenWindow();
    }

    /**
     * Returns the imageWidth of the window currently used in full screenManager mode. Returns 0 if the device is not in full screenManager mode.
     */
    public int getWidth() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            return window.getWidth();
        }
        return 0;
    }

    /**
     * Returns the imageHeight of the window currently used in full screenManager mode. Returns 0 if the device is not in full screenManager mode.
     */
    public int getHeight() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            return window.getHeight();
        }
        return  0;
    }

    /**
     * Restores the screenManager's display mode.
     */
    public void restoreScreen() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            window.dispose();
        }
        graphicsDevice.setFullScreenWindow(null);
    }

    /**
     * Creates an image compatible with the current display.
     */
    public BufferedImage createCompatibleImage(int w, int h, int transparency) {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            GraphicsConfiguration gc = window.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, transparency);
        }
        return null;
    }

}
