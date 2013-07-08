package Mafia.Hacks;

import Mafia.Mafia;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet13PlayerLookMove;

public class Nofall extends BasicHack {
    public Nofall(Mafia mafia) {
        super(mafia);
    }

    @Override
    public void onEnable() {
        mafia.getUtils().enableMessage("Nofall");
        Packet11PlayerPosition.isNoFall = true;
        Packet12PlayerLook.isNoFall = true;
        Packet13PlayerLookMove.isNoFall = true;

    }

    @Override
    public void onDisable() {
        mafia.getUtils().disableMessage("Nofall");
        Packet11PlayerPosition.isNoFall = false;
        Packet12PlayerLook.isNoFall = false;
        Packet13PlayerLookMove.isNoFall = false;
    }

    @Override
    public void tick() {}
}
