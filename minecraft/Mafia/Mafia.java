package Mafia;

import Mafia.Gui.MainGui;
import Mafia.Hacks.*;
import net.minecraft.src.Gui;
import net.minecraft.src.Minecraft;
import org.darkstorm.minecraft.gui.GuiManagerImpl;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class Mafia {
    Minecraft mc;
    private boolean keyStates[] = new boolean[256];
    MafiaUtilities mafiaUtilities;
    public static GuiManagerImpl manager;

    int currentHack = 0;


    public static Fullbright fullbright;
    public static Fly fly;
    public static Nofall nofall;

    public static MainGui mainGui;

    public Mafia(Minecraft mc, MafiaUtilities mafiaUtilities) {
        this.mc = mc;
        this.mafiaUtilities = mafiaUtilities;
        manager = new GuiManagerImpl();
        init();
    }

    public void init() {
        fullbright = new Fullbright(this);
        fly = new Fly(this);
        nofall = new Nofall(this);

        manager.setTheme(new SimpleTheme(mc));
        mainGui = new MainGui(manager);

        new NamesESP();
    }

    public boolean checkKey(int i) {
        if (mc.currentScreen != null) {
            return false;
        }
        if (Keyboard.isKeyDown(i) != keyStates[i]) {
            return keyStates[i] = !keyStates[i];
        } else {
            return false;
        }
    }

    public void checkKeys() {
        if (checkKey(Keyboard.KEY_U)) {
            mc.displayGuiScreen(new MainGui(manager));
        }
        if (checkKey(Keyboard.KEY_F)) {
            fullbright.toggle();
        }
        if (checkKey(Keyboard.KEY_R)) {
            fly.toggle();
        }
        if (checkKey(Keyboard.KEY_H)) {
            nofall.toggle();
        }
    }

    public void tick() {
        checkKeys();
        hackTicks();
        updatePinnedFrames();
        renderPinnedFrames();
    }

    public void hackTicks() {
        fullbright.tick();
        fly.tick();
        nofall.tick();
    }

    public MafiaUtilities getUtils() {
        return mafiaUtilities;
    }

    public Minecraft getMc() {
        return mc;
    }

    public static Mafia getMafia() {
        return new Mafia(Minecraft.getMinecraft(), new MafiaUtilities(Minecraft.getMinecraft()));
    }

    public void updatePinnedFrames() {
        if ((mc.currentScreen == null) || (mc.currentScreen == (Gui) mc.ingameGUI)) {
            for (Frame frame : manager.getFrames()) {
                if (frame.isPinned()) {
                    frame.update();
                }
            }
        }
    }

    public void renderPinnedFrames() {
        if ((mc.currentScreen == null) || (mc.currentScreen == (Gui) mc.ingameGUI)) {
            for (Frame frame : manager.getFrames()) {
                if (frame.isPinned()) {
                    frame.render();
                }
            }
        }
    }
}
