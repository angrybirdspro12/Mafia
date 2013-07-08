package net.minecraft.src;

import org.lwjgl.opengl.GL11;

class GuiSlotOnlineServerList extends GuiScreenSelectLocation
{
    final GuiScreenOnlineServers field_96294_a;

    public GuiSlotOnlineServerList(GuiScreenOnlineServers par1GuiScreenOnlineServers)
    {
        super(GuiScreenOnlineServers.func_140037_f(par1GuiScreenOnlineServers), par1GuiScreenOnlineServers.width, par1GuiScreenOnlineServers.height, 32, par1GuiScreenOnlineServers.height - 64, 36);
        this.field_96294_a = par1GuiScreenOnlineServers;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size() + 1;
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
        if (par1 < GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size())
        {
            McoServer var3 = (McoServer)GuiScreenOnlineServers.func_140013_c(this.field_96294_a).get(par1);
            GuiScreenOnlineServers.func_140036_b(this.field_96294_a, var3.field_96408_a);

            if (!GuiScreenOnlineServers.func_140015_g(this.field_96294_a).func_110432_I().func_111285_a().equals(var3.field_96405_e))
            {
                GuiScreenOnlineServers.func_140038_h(this.field_96294_a).displayString = StatCollector.translateToLocal("mco.selectServer.leave");
            }
            else
            {
                GuiScreenOnlineServers.func_140038_h(this.field_96294_a).displayString = StatCollector.translateToLocal("mco.selectServer.configure");
            }

            GuiScreenOnlineServers.func_140033_i(this.field_96294_a).enabled = var3.field_96404_d.equals("OPEN") && !var3.field_98166_h;

            if (par2 && GuiScreenOnlineServers.func_140033_i(this.field_96294_a).enabled)
            {
                GuiScreenOnlineServers.func_140008_c(this.field_96294_a, GuiScreenOnlineServers.func_140041_a(this.field_96294_a));
            }
        }
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return par1 == GuiScreenOnlineServers.func_140027_d(this.field_96294_a, GuiScreenOnlineServers.func_140041_a(this.field_96294_a));
    }

    protected boolean func_104086_b(int par1)
    {
        return par1 < GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size() && ((McoServer)GuiScreenOnlineServers.func_140013_c(this.field_96294_a).get(par1)).field_96405_e.toLowerCase().equals(GuiScreenOnlineServers.func_104032_j(this.field_96294_a).func_110432_I().func_111285_a());
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return this.getSize() * 36;
    }

    protected void drawBackground()
    {
        this.field_96294_a.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        if (par1 < GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size())
        {
            this.func_96292_b(par1, par2, par3, par4, par5Tessellator);
        }
    }

    private void func_96292_b(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        McoServer var6 = (McoServer)GuiScreenOnlineServers.func_140013_c(this.field_96294_a).get(par1);
        this.field_96294_a.drawString(GuiScreenOnlineServers.func_140023_k(this.field_96294_a), var6.func_96398_b(), par2 + 2, par3 + 1, 16777215);
        short var7 = 207;
        byte var8 = 1;

        if (var6.field_98166_h)
        {
            GuiScreenOnlineServers.func_104031_c(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e);
        }
        else if (var6.field_96404_d.equals("CLOSED"))
        {
            GuiScreenOnlineServers.func_140035_b(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e);
        }
        else if (var6.field_96405_e.equals(GuiScreenOnlineServers.func_140014_l(this.field_96294_a).func_110432_I().func_111285_a()) && var6.field_104063_i < 7)
        {
            this.func_96293_a(par1, par2 - 14, par3, var6);
            GuiScreenOnlineServers.func_140031_a(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e, var6.field_104063_i);
        }
        else if (var6.field_96404_d.equals("OPEN"))
        {
            GuiScreenOnlineServers.func_140020_c(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e);
            this.func_96293_a(par1, par2 - 14, par3, var6);
        }

        this.field_96294_a.drawString(GuiScreenOnlineServers.func_140039_m(this.field_96294_a), var6.func_96397_a(), par2 + 2, par3 + 12, 7105644);
        this.field_96294_a.drawString(GuiScreenOnlineServers.func_98079_k(this.field_96294_a), var6.field_96405_e, par2 + 2, par3 + 12 + 11, 5000268);
    }

    private void func_96293_a(int par1, int par2, int par3, McoServer par4McoServer)
    {
        if (par4McoServer.field_96403_g != null)
        {
            synchronized (GuiScreenOnlineServers.func_140029_i())
            {
                if (GuiScreenOnlineServers.func_140018_j() < 5 && (!par4McoServer.field_96411_l || par4McoServer.field_102022_m))
                {
                    (new ThreadConnectToOnlineServer(this, par4McoServer)).start();
                }
            }

            boolean var5 = par4McoServer.field_96415_h > 73;
            boolean var6 = par4McoServer.field_96415_h < 73;
            boolean var7 = var5 || var6;

            if (par4McoServer.field_96414_k != null)
            {
                this.field_96294_a.drawString(GuiScreenOnlineServers.func_110402_q(this.field_96294_a), par4McoServer.field_96414_k, par2 + 215 - GuiScreenOnlineServers.func_140010_p(this.field_96294_a).getStringWidth(par4McoServer.field_96414_k), par3 + 1, 8421504);
            }

            String var8 = null;

            if (var7)
            {
                String var9 = EnumChatFormatting.DARK_RED + par4McoServer.field_96413_j;
                int var10 = par2 + 230 - GuiScreenOnlineServers.func_140025_q(this.field_96294_a).getStringWidth(var9);
                int var11 = var10 + GuiScreenOnlineServers.func_140034_r(this.field_96294_a).getStringWidth(var9);
                int var12 = par3 + 12;
                int var13 = var12 + 10;
                this.field_96294_a.drawString(GuiScreenOnlineServers.func_140028_s(this.field_96294_a), var9, var10, par3 + 12, 8421504);
                var8 = var5 ? "Client out of date!" : "Server out of date!";
                byte var14 = 1;

                if (this.field_104094_d >= var10 - var14 && this.field_104095_e >= var12 - var14 && this.field_104094_d <= var11 + var14 && this.field_104095_e <= var13 + var14)
                {
                    GuiScreenOnlineServers.func_140026_a(this.field_96294_a, var8);
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GuiScreenOnlineServers.func_140022_t(this.field_96294_a).func_110434_K().func_110577_a(Gui.field_110324_m);
        }
    }
}
