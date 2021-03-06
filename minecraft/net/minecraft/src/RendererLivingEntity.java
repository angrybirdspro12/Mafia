package net.minecraft.src;

import java.util.Random;

import Mafia.Hacks.NamesESP;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public abstract class RendererLivingEntity extends Render
{
    private static final ResourceLocation field_110814_a = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    protected ModelBase mainModel;

    /** The model to be used during the render passes. */
    protected ModelBase renderPassModel;

    public RendererLivingEntity(ModelBase par1ModelBase, float par2)
    {
        this.mainModel = par1ModelBase;
        this.shadowSize = par2;
    }

    /**
     * Sets the model to be used in the current render pass (the first render pass is done after the primary model is
     * rendered) Args: model
     */
    public void setRenderPassModel(ModelBase par1ModelBase)
    {
        this.renderPassModel = par1ModelBase;
    }

    /**
     * Returns a rotation angle that is inbetween two other rotation angles. par1 and par2 are the angles between which
     * to interpolate, par3 is probably a float between 0.0 and 1.0 that tells us where "between" the two angles we are.
     * Example: par1 = 30, par2 = 50, par3 = 0.5, then return = 40
     */
    private float interpolateRotation(float par1, float par2, float par3)
    {
        float var4;

        for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F)
        {
            ;
        }

        while (var4 >= 180.0F)
        {
            var4 -= 360.0F;
        }

        return par1 + par3 * var4;
    }

    public void doRenderLiving(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        this.mainModel.onGround = this.renderSwingProgress(par1, par9);

        if (this.renderPassModel != null)
        {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }

        this.mainModel.isRiding = par1.isRiding();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }

        this.mainModel.isChild = par1.isChild();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }

        try
        {
            float var10 = this.interpolateRotation(par1.prevRenderYawOffset, par1.renderYawOffset, par9);
            float var11 = this.interpolateRotation(par1.prevRotationYawHead, par1.rotationYawHead, par9);
            float var13;

            if (par1.isRiding() && par1.ridingEntity instanceof EntityLivingBase)
            {
                EntityLivingBase var12 = (EntityLivingBase)par1.ridingEntity;
                var10 = this.interpolateRotation(var12.prevRenderYawOffset, var12.renderYawOffset, par9);
                var13 = MathHelper.wrapAngleTo180_float(var11 - var10);

                if (var13 < -85.0F)
                {
                    var13 = -85.0F;
                }

                if (var13 >= 85.0F)
                {
                    var13 = 85.0F;
                }

                var10 = var11 - var13;

                if (var13 * var13 > 2500.0F)
                {
                    var10 += var13 * 0.2F;
                }
            }

            float var26 = par1.prevRotationPitch + (par1.rotationPitch - par1.prevRotationPitch) * par9;
            this.renderLivingAt(par1, par2, par4, par6);
            var13 = this.handleRotationFloat(par1, par9);
            this.rotateCorpse(par1, var13, var10, par9);
            float var14 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(par1, par9);
            GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
            float var15 = par1.prevLimbYaw + (par1.limbYaw - par1.prevLimbYaw) * par9;
            float var16 = par1.limbSwing - par1.limbYaw * (1.0F - par9);

            if (par1.isChild())
            {
                var16 *= 3.0F;
            }

            if (var15 > 1.0F)
            {
                var15 = 1.0F;
            }

            GL11.glEnable(GL11.GL_ALPHA_TEST);
            this.mainModel.setLivingAnimations(par1, var16, var15, par9);
            this.renderModel(par1, var16, var15, var13, var11 - var10, var26, var14);
            float var19;
            int var18;
            float var20;
            float var22;

            for (int var17 = 0; var17 < 4; ++var17)
            {
                var18 = this.shouldRenderPass(par1, var17, par9);

                if (var18 > 0)
                {
                    this.renderPassModel.setLivingAnimations(par1, var16, var15, par9);
                    this.renderPassModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);

                    if ((var18 & 240) == 16)
                    {
                        this.func_82408_c(par1, var17, par9);
                        this.renderPassModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);
                    }

                    if ((var18 & 15) == 15)
                    {
                        var19 = (float)par1.ticksExisted + par9;
                        this.func_110776_a(field_110814_a);
                        GL11.glEnable(GL11.GL_BLEND);
                        var20 = 0.5F;
                        GL11.glColor4f(var20, var20, var20, 1.0F);
                        GL11.glDepthFunc(GL11.GL_EQUAL);
                        GL11.glDepthMask(false);

                        for (int var21 = 0; var21 < 2; ++var21)
                        {
                            GL11.glDisable(GL11.GL_LIGHTING);
                            var22 = 0.76F;
                            GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
                            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                            GL11.glMatrixMode(GL11.GL_TEXTURE);
                            GL11.glLoadIdentity();
                            float var23 = var19 * (0.001F + (float)var21 * 0.003F) * 20.0F;
                            float var24 = 0.33333334F;
                            GL11.glScalef(var24, var24, var24);
                            GL11.glRotatef(30.0F - (float)var21 * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, var23, 0.0F);
                            GL11.glMatrixMode(GL11.GL_MODELVIEW);
                            this.renderPassModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);
                        }

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glDepthFunc(GL11.GL_LEQUAL);
                    }

                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            GL11.glDepthMask(true);
            this.renderEquippedItems(par1, par9);
            float var27 = par1.getBrightness(par9);
            var18 = this.getColorMultiplier(par1, var27, par9);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

            if ((var18 >> 24 & 255) > 0 || par1.hurtTime > 0 || par1.deathTime > 0)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (par1.hurtTime > 0 || par1.deathTime > 0)
                {
                    GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                    this.mainModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);

                    for (int var28 = 0; var28 < 4; ++var28)
                    {
                        if (this.inheritRenderPass(par1, var28, par9) >= 0)
                        {
                            GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);
                        }
                    }
                }

                if ((var18 >> 24 & 255) > 0)
                {
                    var19 = (float)(var18 >> 16 & 255) / 255.0F;
                    var20 = (float)(var18 >> 8 & 255) / 255.0F;
                    float var30 = (float)(var18 & 255) / 255.0F;
                    var22 = (float)(var18 >> 24 & 255) / 255.0F;
                    GL11.glColor4f(var19, var20, var30, var22);
                    this.mainModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);

                    for (int var29 = 0; var29 < 4; ++var29)
                    {
                        if (this.inheritRenderPass(par1, var29, par9) >= 0)
                        {
                            GL11.glColor4f(var19, var20, var30, var22);
                            this.renderPassModel.render(par1, var16, var15, var13, var11 - var10, var26, var14);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
        catch (Exception var25)
        {
            var25.printStackTrace();
        }

        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        this.passSpecialRender(par1, par2, par4, par6);
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.func_110777_b(par1EntityLivingBase);

        if (!par1EntityLivingBase.isInvisible())
        {
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
        }
        else if (!par1EntityLivingBase.func_98034_c(Minecraft.getMinecraft().thePlayer))
        {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        }
        else
        {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
        }
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
    {
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    }

    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);

        if (par1EntityLivingBase.deathTime > 0)
        {
            float var5 = ((float)par1EntityLivingBase.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
            var5 = MathHelper.sqrt_float(var5);

            if (var5 > 1.0F)
            {
                var5 = 1.0F;
            }

            GL11.glRotatef(var5 * this.getDeathMaxRotation(par1EntityLivingBase), 0.0F, 0.0F, 1.0F);
        }
        else if ((par1EntityLivingBase.getEntityName().equals("Dinnerbone") || par1EntityLivingBase.getEntityName().equals("Grumm")) && (!(par1EntityLivingBase instanceof EntityPlayer) || !((EntityPlayer)par1EntityLivingBase).getHideCape()))
        {
            GL11.glTranslatef(0.0F, par1EntityLivingBase.height + 0.1F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        }
    }

    protected float renderSwingProgress(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return par1EntityLivingBase.getSwingProgress(par2);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return (float)par1EntityLivingBase.ticksExisted + par2;
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {}

    /**
     * renders arrows the Entity has been attacked with, attached to it
     */
    protected void renderArrowsStuckInEntity(EntityLivingBase par1EntityLivingBase, float par2)
    {
        int var3 = par1EntityLivingBase.getArrowCountInEntity();

        if (var3 > 0)
        {
            EntityArrow var4 = new EntityArrow(par1EntityLivingBase.worldObj, par1EntityLivingBase.posX, par1EntityLivingBase.posY, par1EntityLivingBase.posZ);
            Random var5 = new Random((long)par1EntityLivingBase.entityId);
            RenderHelper.disableStandardItemLighting();

            for (int var6 = 0; var6 < var3; ++var6)
            {
                GL11.glPushMatrix();
                ModelRenderer var7 = this.mainModel.getRandomModelBox(var5);
                ModelBox var8 = (ModelBox)var7.cubeList.get(var5.nextInt(var7.cubeList.size()));
                var7.postRender(0.0625F);
                float var9 = var5.nextFloat();
                float var10 = var5.nextFloat();
                float var11 = var5.nextFloat();
                float var12 = (var8.posX1 + (var8.posX2 - var8.posX1) * var9) / 16.0F;
                float var13 = (var8.posY1 + (var8.posY2 - var8.posY1) * var10) / 16.0F;
                float var14 = (var8.posZ1 + (var8.posZ2 - var8.posZ1) * var11) / 16.0F;
                GL11.glTranslatef(var12, var13, var14);
                var9 = var9 * 2.0F - 1.0F;
                var10 = var10 * 2.0F - 1.0F;
                var11 = var11 * 2.0F - 1.0F;
                var9 *= -1.0F;
                var10 *= -1.0F;
                var11 *= -1.0F;
                float var15 = MathHelper.sqrt_float(var9 * var9 + var11 * var11);
                var4.prevRotationYaw = var4.rotationYaw = (float)(Math.atan2((double)var9, (double)var11) * 180.0D / Math.PI);
                var4.prevRotationPitch = var4.rotationPitch = (float)(Math.atan2((double)var10, (double)var15) * 180.0D / Math.PI);
                double var16 = 0.0D;
                double var18 = 0.0D;
                double var20 = 0.0D;
                float var22 = 0.0F;
                this.renderManager.renderEntityWithPosYaw(var4, var16, var18, var20, var22, par2);
                GL11.glPopMatrix();
            }

            RenderHelper.enableStandardItemLighting();
        }
    }

    protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.shouldRenderPass(par1EntityLivingBase, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return -1;
    }

    protected void func_82408_c(EntityLivingBase par1, int par2, float par3) {}

    protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase)
    {
        return 90.0F;
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
     */
    protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
    {
        return 0;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {}

    /**
     * Passes the specialRender and renders it
     */
    protected void passSpecialRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
    {
        if (this.func_110813_b(par1EntityLivingBase))
        {
            float var8 = 1.6F;
            float var9 = 0.016666668F * var8;
            double var10 = par1EntityLivingBase.getDistanceSqToEntity(this.renderManager.livingPlayer);

            if (var10 < (double)(260 * 260))
            {
                String var13 = par1EntityLivingBase.getTranslatedEntityName();
                    this.func_96449_a(par1EntityLivingBase, par2, par4, par6, var13, var9, var10);

            }
        }
    }

    protected boolean func_110813_b(EntityLivingBase par1EntityLivingBase)
    {
        return Minecraft.isGuiEnabled() && par1EntityLivingBase != this.renderManager.livingPlayer && !par1EntityLivingBase.func_98034_c(Minecraft.getMinecraft().thePlayer) && par1EntityLivingBase.riddenByEntity == null;
    }

    public void func_96449_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, String par8Str, float par9, double par10)
    {
        if (par1EntityLivingBase.isPlayerSleeping())
        {
            this.renderBigName(par1EntityLivingBase, par8Str, par2, par4 - 1.5D, par6, 64);
        }
        else
        {
            this.renderBigName(par1EntityLivingBase, par8Str, par2, par4, par6, 64);
        }
    }

    /**
     * Draws the debug or playername text above a living
     */
    public void renderBigName(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int useless) {
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
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
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
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, -1);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderLiving((EntityLivingBase)par1Entity, par2, par4, par6, par8, par9);
    }
}
