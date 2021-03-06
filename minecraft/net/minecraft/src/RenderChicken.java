package net.minecraft.src;

public class RenderChicken extends RenderLiving
{
    private static final ResourceLocation field_110920_a = new ResourceLocation("textures/entity/chicken.png");

    public RenderChicken(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderChicken(EntityChicken par1EntityChicken, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityChicken, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110919_a(EntityChicken par1EntityChicken)
    {
        return field_110920_a;
    }

    protected float getWingRotation(EntityChicken par1EntityChicken, float par2)
    {
        float var3 = par1EntityChicken.field_70888_h + (par1EntityChicken.field_70886_e - par1EntityChicken.field_70888_h) * par2;
        float var4 = par1EntityChicken.field_70884_g + (par1EntityChicken.destPos - par1EntityChicken.field_70884_g) * par2;
        return (MathHelper.sin(var3) + 1.0F) * var4;
    }

    public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityChicken)par1, par2, par4, par6, par8, par9);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getWingRotation((EntityChicken)par1EntityLivingBase, par2);
    }

    public void renderPlayer(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityChicken)par1, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110919_a((EntityChicken)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityChicken)par1Entity, par2, par4, par6, par8, par9);
    }
}
