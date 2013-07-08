package Mafia.Hacks;

import Mafia.Mafia;
import Mafia.MafiaUtilities;
import net.minecraft.src.Minecraft;

public class Fullbright extends BasicHack {
    public float oldGamma;

    public Fullbright(Mafia mafia) {
        super(mafia);
    }

    @Override
    public void onEnable() {
        mafia.getUtils().enableMessage("Fullbright");
        oldGamma = mafia.getMc().gameSettings.gammaSetting;
        mafia.getMc().gameSettings.gammaSetting = 900;
    }

    @Override
    public void onDisable() {
        mafia.getUtils().disableMessage("Fullbright");
        mafia.getMc().gameSettings.gammaSetting = oldGamma;
    }

    @Override
    public void tick() {
        if (this.isEnabled()) {
            if (mafia.getMc().gameSettings.gammaSetting != 900) {
                mafia.getMc().gameSettings.gammaSetting = 900;
            }
        } else {
            mafia.getMc().gameSettings.gammaSetting = 1;
        }
    }
}
