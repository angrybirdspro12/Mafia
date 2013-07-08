package Mafia.Hacks;

import net.minecraft.src.Minecraft;
import Mafia.Mafia;

public abstract class BasicHack {
    boolean enabled;
    Mafia mafia;

    public BasicHack(Mafia mafia) {
        this.mafia = mafia;
    }

    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void tick();

    public void enable() {
        enabled = true;
        onEnable();
    }

    public void disable() {
        enabled = false;
        onDisable();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
}
