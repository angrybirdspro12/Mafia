package Mafia.Hacks;

import Mafia.Mafia;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class NamesESP {

    public static RenderManager renderManager;

    public NamesESP() {
        renderManager = new RenderManager();
    }

    public static void renderBigName(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int useless) {
        double distanceSqToEntity = par1EntityLivingBase.getDistanceSqToEntity(renderManager.livingPlayer);
        if (distanceSqToEntity <= (260D * 260D)) {
            FontRenderer var12 = Minecraft.getMinecraft().fontRenderer;
            float fontMultiplier;
            if (distanceSqToEntity >= 260 * 260) {
                fontMultiplier = 27.6F;
            } else if (distanceSqToEntity >= Math.pow(250D, 2D)) {
                fontMultiplier = 26.6F;
            } else if (distanceSqToEntity >= Math.pow(240D, 2D)) {
                fontMultiplier = 25.6F;
            } else if (distanceSqToEntity >= Math.pow(230D, 2D)) {
                fontMultiplier = 24.6F;
            } else if (distanceSqToEntity >= Math.pow(220D, 2D)) {
                fontMultiplier = 23.6F;
            } else if (distanceSqToEntity >= Math.pow(210D, 2D)) {
                fontMultiplier = 22.6F;
            } else if (distanceSqToEntity >= Math.pow(200D, 2D)) {
                fontMultiplier = 21.6F;
            } else if (distanceSqToEntity >= Math.pow(190D, 2D)) {
                fontMultiplier = 20.6F;
            } else if (distanceSqToEntity >= Math.pow(180D, 2D)) {
                fontMultiplier = 19.6F;
            } else if (distanceSqToEntity >= Math.pow(170D, 2D)) {
                fontMultiplier = 18.6F;
            } else if (distanceSqToEntity >= Math.pow(160D, 2D)) {
                fontMultiplier = 17.6F;
            } else if (distanceSqToEntity >= Math.pow(150D, 2D)) {
                fontMultiplier = 16.6F;
            } else if (distanceSqToEntity >= Math.pow(140D, 2D)) {
                fontMultiplier = 15.6F;
            } else if (distanceSqToEntity >= Math.pow(130D, 2D)) {
                fontMultiplier = 14.6F;
            } else if (distanceSqToEntity >= Math.pow(120D, 2D)) {
                fontMultiplier = 13.6F;
            } else if (distanceSqToEntity >= Math.pow(110D, 2D)) {
                fontMultiplier = 12.6F;
            } else if (distanceSqToEntity >= Math.pow(100D, 2D)) {
                fontMultiplier = 11.6F;
            } else if (distanceSqToEntity >= Math.pow(90D, 2D)) {
                fontMultiplier = 10.6F;
            } else if (distanceSqToEntity >= Math.pow(80D, 2D)) {
                fontMultiplier = 9.6F;
            } else if (distanceSqToEntity >= Math.pow(70D, 2D)) {
                fontMultiplier = 8.6F;
            } else if (distanceSqToEntity >= Math.pow(60D, 2D)) {
                fontMultiplier = 7.6F;//////////
            } else if (distanceSqToEntity >= Math.pow(50D, 2D)) {
                fontMultiplier = 6.6F;
            } else if (distanceSqToEntity >= Math.pow(40D, 2D)) {
                fontMultiplier = 5.6F;
            } else if (distanceSqToEntity >= Math.pow(30D, 2D)) {
                fontMultiplier = 4.6F;
            } else if (distanceSqToEntity >= Math.pow(20D, 2D)) {
                fontMultiplier = 3.6F;
            } else if (distanceSqToEntity >= Math.pow(10D, 2D)) {
                fontMultiplier = 2.6F;
            } else {
                fontMultiplier = 1.6F;
            }
            float var14 = 0.016666668F * fontMultiplier;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) par3 + 0.0F, (float) par5 + par1EntityLivingBase.height + 0.5F, (float) par7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var14, -var14, var14);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator var15 = Tessellator.instance;
            byte var16 = 0;

            if (par2Str.equals("wowlover687"))
            {
                var16 = -10;
            }

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            var15.startDrawingQuads();
            int var17 = var12.getStringWidth(par2Str) / 2;
            var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            var15.addVertex((double) (-var17 - 1), (double) (-1 + var16), 0.0D);
            var15.addVertex((double)(-var17 - 1), (double)(8 + var16), 0.0D);
            var15.addVertex((double)(var17 + 1), (double)(8 + var16), 0.0D);
            var15.addVertex((double)(var17 + 1), (double)(-1 + var16), 0.0D);
            var15.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }

}
