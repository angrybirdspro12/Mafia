package Mafia.Gui;

import Mafia.Mafia;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Minecraft;
import org.darkstorm.minecraft.gui.GuiManager;
import org.darkstorm.minecraft.gui.component.*;
import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.*;
import org.darkstorm.minecraft.gui.listener.ButtonListener;
import org.darkstorm.minecraft.gui.listener.ComboBoxListener;
import org.darkstorm.minecraft.gui.listener.SelectableComponentListener;
import org.darkstorm.minecraft.gui.theme.Theme;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import java.awt.*;

public class MainGui extends GuiScreen {
    private final GuiManager guiManager;

    private final Theme theme;

    public boolean setup = false;

    public MainGui(GuiManager guiManager) {
        this.guiManager = guiManager;
        theme = new SimpleTheme(Minecraft.getMinecraft());
        this.setup();
    }

    public void setup() {
        if (setup) {
            return;
        }
        setup = true;

        if (guiManager.getFrames().length == 0) {
            createPlayerFrame();
            createWorldFrame();
            createKeybindList();
            createMiscFrame();
        }

    }


    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        for (Frame frame : guiManager.getFrames()) {
            if (!frame.isVisible()) {
                continue;
            }
            if (!frame.isMinimized() && !frame.getArea().contains(x, y)) {
                for (Component component : frame.getChildren()) {
                    for (Rectangle area : component.getTheme().getUIForComponent(component).getInteractableRegions(component)) {
                        if (area.contains(x - frame.getX() - component.getX(), y - frame.getY() - component.getY())) {
                            frame.onMousePress(x - frame.getX(), y - frame.getY(), button);
                            guiManager.bringForward(frame);
                            return;
                        }
                    }
                }
            }
        }
        for (Frame frame : guiManager.getFrames()) {
            if (!frame.isVisible()) {
                continue;
            }
            if (!frame.isMinimized() && frame.getArea().contains(x, y)) {
                frame.onMousePress(x - frame.getX(), y - frame.getY(), button);
                guiManager.bringForward(frame);
                break;
            } else if (frame.isMinimized()) {
                for (Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame)) {
                    if (area.contains(x - frame.getX(), y - frame.getY())) {
                        frame.onMousePress(x - frame.getX(), y - frame.getY(), button);
                        guiManager.bringForward(frame);
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void mouseMovedOrUp(int x, int y, int button) {
        super.mouseMovedOrUp(x, y, button);
        for (Frame frame : guiManager.getFrames()) {
            if (!frame.isVisible()) {
                continue;
            }
            if (!frame.isMinimized() && !frame.getArea().contains(x, y)) {
                for (Component component : frame.getChildren()) {
                    for (Rectangle area : component.getTheme().getUIForComponent(component).getInteractableRegions(component)) {
                        if (area.contains(x - frame.getX() - component.getX(), y - frame.getY() - component.getY())) {
                            frame.onMouseRelease(x - frame.getX(), y - frame.getY(), button);
                            guiManager.bringForward(frame);
                            return;
                        }
                    }
                }
            }
        }
        for (Frame frame : guiManager.getFrames()) {
            if (!frame.isVisible()) {
                continue;
            }
            if (!frame.isMinimized() && frame.getArea().contains(x, y)) {
                frame.onMouseRelease(x - frame.getX(), y - frame.getY(), button);
                guiManager.bringForward(frame);
                break;
            } else if (frame.isMinimized()) {
                for (Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame)) {
                    if (area.contains(x - frame.getX(), y - frame.getY())) {
                        frame.onMouseRelease(x - frame.getX(), y - frame.getY(), button);
                        guiManager.bringForward(frame);
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void drawScreen(int par2, int par3, float par4) {
        Frame[] frames = guiManager.getFrames();
        for (int i = frames.length - 1; i >= 0; i--) {
            frames[i].render();
        }
        super.drawScreen(par2, par3, par4);
    }

    private void createPlayerFrame() {
        final Frame playerFrame = new BasicFrame("Player");
        playerFrame.setTheme(theme);
        BasicButton nofallButton = new BasicButton("NoFall");
        nofallButton.addButtonListener(new ButtonListener() {

            @Override
            public void onButtonPress(Button button) {
                Mafia.nofall.toggle();
            }
        });

        BasicButton flyButton = new BasicButton("Fly");
        flyButton.addButtonListener(new ButtonListener() {

            @Override
            public void onButtonPress(Button button) {
                Mafia.fly.toggle();
            }
        });

        playerFrame.add(flyButton);
        playerFrame.add(nofallButton);

        playerFrame.setX(10);
        playerFrame.setY(10);

        playerFrame.setWidth(100);
        playerFrame.setHeight(80);
        playerFrame.layoutChildren();
        playerFrame.setPinnable(false);
        playerFrame.setClosable(false);
        playerFrame.setVisible(true);
        playerFrame.setMinimized(true);
        Mafia.manager.addFrame(playerFrame);
    }

    private void createMiscFrame() {
        final Frame miscFrame = new BasicFrame("Misc");
        miscFrame.setTheme(theme);

        final BasicButton enabled = new BasicButton("Gui: Disable");

        enabled.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPress(Button button) {
                NormalGui.isEnabled = !NormalGui.isEnabled;
                if (NormalGui.isEnabled) {
                    enabled.setText("Gui: Disable");
                } else {
                    enabled.setText("Gui: Enable");
                }
            }
        });

        miscFrame.add(enabled);

        miscFrame.setX(310);
        miscFrame.setY(10);

        miscFrame.setWidth(100);
        miscFrame.setHeight(80);
        miscFrame.layoutChildren();
        miscFrame.setPinnable(false);
        miscFrame.setClosable(false);
        miscFrame.setVisible(true);
        miscFrame.setMinimized(true);
        Mafia.manager.addFrame(miscFrame);
    }

    private void createWorldFrame() {
        final Frame worldFrame = new BasicFrame("World");
        worldFrame.setTheme(theme);


        BasicButton fullbrightButton = new BasicButton("Fullbright");
        fullbrightButton.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPress(Button button) {
                Mafia.fullbright.toggle();
            }
        });

        worldFrame.add(fullbrightButton);

        worldFrame.setX(110);
        worldFrame.setY(10);

        worldFrame.setWidth(100);
        worldFrame.setHeight(80);
        worldFrame.layoutChildren();
        worldFrame.setPinnable(false);
        worldFrame.setClosable(false);
        worldFrame.setVisible(true);
        worldFrame.setMinimized(true);
        Mafia.manager.addFrame(worldFrame);
    }

    private void createKeybindList() {
        final Frame keybindsFrame = new BasicFrame("Keybinds");
        keybindsFrame.setTheme(theme);
        keybindsFrame.add(new BasicLabel("F - Fullbright"));
        keybindsFrame.add(new BasicLabel("R - Fly"));
        keybindsFrame.add(new BasicLabel("H - Nofall"));

        keybindsFrame.setX(210);
        keybindsFrame.setY(10);

        keybindsFrame.setWidth(100);
        keybindsFrame.setHeight(80);

        keybindsFrame.layoutChildren();
        keybindsFrame.setPinnable(true);
        keybindsFrame.setClosable(false);
        keybindsFrame.setVisible(true);
        keybindsFrame.setMinimized(true);
        Mafia.manager.addFrame(keybindsFrame);
    }
/*
    private void createThemeFrame() {
        Frame themeFrame = new BasicFrame("Theme");
        themeFrame.setTheme(theme);
        ComboBox comboBox = new BasicComboBox("Evil Theme", "Sexy Theme", "Mafia Theme");
        comboBox.addComboBoxListener(new ComboBoxListener() {


            @Override

            public void onComboBoxSelectionChanged(ComboBox comboBox) {
                Theme theme;
                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        theme = new SimpleTheme(Minecraft.getMinecraft());
                        break;
                    case 1:
                        theme = new SimpleTheme(Minecraft.getMinecraft());
                        break;
                    case 2:
                        theme  = new
                    default:
                        return;
                }
                Mafia.manager.setTheme(theme);
            }
        });
        themeFrame.add(comboBox);
        themeFrame.setX(310);
        themeFrame.setY(10);
        Dimension defaultDimension = theme.getUIForComponent(themeFrame).getDefaultSize(themeFrame);
        themeFrame.setWidth(defaultDimension.width);
        themeFrame.setHeight(defaultDimension.height);
        themeFrame.layoutChildren();
        themeFrame.setVisible(true);
        themeFrame.setMinimized(true);
        themeFrame.setClosable(false);
        themeFrame.setPinnable(false);
        Mafia.manager.addFrame(themeFrame);
    }
    */
}
