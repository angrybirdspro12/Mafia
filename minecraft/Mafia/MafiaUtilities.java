package Mafia;

import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Minecraft;

public class MafiaUtilities {
    Minecraft mc;
    public MafiaUtilities(Minecraft mc) {
        this.mc = mc;
    }

    public void enableMessage(String hack) {
        mc.thePlayer.addChatMessage(EnumChatFormatting.RED + "[Mafia] " + EnumChatFormatting.LIGHT_PURPLE + hack + " enabled");
    }

    public void disableMessage(String hack) {
        mc.thePlayer.addChatMessage(EnumChatFormatting.RED + "[Mafia] " + EnumChatFormatting.LIGHT_PURPLE + hack + " disabled");
    }
}
