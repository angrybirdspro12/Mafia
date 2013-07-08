package Mafia.Gui;

import Mafia.Mafia;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

import java.util.concurrent.atomic.AtomicInteger;

public class NormalGui extends GuiIngame {
    public static boolean isEnabled = true;
    Mafia mafia;

    public NormalGui(Minecraft par1Minecraft, Mafia mafia) {
        super(par1Minecraft);
        this.mafia = mafia;
    }

    @Override
    public void renderGameOverlay(float par1, boolean par2, int par3, int par4) {
        super.renderGameOverlay(par1, par2, par3, par4);

        if (isEnabled) {
            ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            int var6 = var5.getScaledWidth();
            int var7 = var5.getScaledHeight();
            int var8 = mc.debugFPS;
            FontRenderer var9 = this.mc.fontRenderer;

            var9.drawString("Mafia 1.6.1", var6 - var6 / 2, 2, 0xff0000);
            var9.drawString("fps: " + var8, 2, 2, 0xffcc00);
            var9.drawString(String.format("cords: %s, %s, %s",
                    String.valueOf(mc.thePlayer.posX),
                    String.valueOf(mc.thePlayer.posY),
                    String.valueOf(mc.thePlayer.posZ)),
                    2, 16, 0xffcc00);
        }
        mafia.tick();
    }
}
