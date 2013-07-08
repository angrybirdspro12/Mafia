package Mafia.Hacks;

import Mafia.Mafia;
import net.minecraft.src.GuiIngame;
import org.lwjgl.input.Keyboard;

public class Fly extends BasicHack {
    public Fly(Mafia mafia) {
        super(mafia);
    }

    float speed = 0.15f;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void tick() {
        if (this.isEnabled()) {
            if (!mafia.getMc().thePlayer.capabilities.isCreativeMode) {
                mafia.getMc().thePlayer.capabilities.allowFlying = true;
                mafia.getMc().thePlayer.capabilities.isFlying = true;
                mafia.getMc().thePlayer.capabilities.setFlySpeed(getSpeed());
            } else {
                mafia.getMc().thePlayer.capabilities.isFlying = true;
                mafia.getMc().thePlayer.capabilities.setFlySpeed(getSpeed());
            }
        } else {
            if (!mafia.getMc().thePlayer.capabilities.isCreativeMode) {
                mafia.getMc().thePlayer.capabilities.allowFlying = false;
                mafia.getMc().thePlayer.capabilities.isFlying = false;
            } else {
                mafia.getMc().thePlayer.capabilities.setFlySpeed(0.05F);
            }
        }
    }
}
