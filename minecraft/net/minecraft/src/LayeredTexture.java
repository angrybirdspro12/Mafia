package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LayeredTexture extends AbstractTexture
{
    private final String[] field_110567_b;

    public LayeredTexture(String ... par1ArrayOfStr)
    {
        this.field_110567_b = par1ArrayOfStr;
    }

    public void func_110551_a(ResourceManager par1ResourceManager) throws IOException
    {
        BufferedImage var2 = null;

        try
        {
            String[] var3 = this.field_110567_b;
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5)
            {
                String var6 = var3[var5];

                if (var6 != null)
                {
                    InputStream var7 = par1ResourceManager.func_110536_a(new ResourceLocation(var6)).func_110527_b();
                    BufferedImage var8 = ImageIO.read(var7);

                    if (var2 == null)
                    {
                        var2 = new BufferedImage(var8.getWidth(), var8.getHeight(), 2);
                        var2.createGraphics();
                    }

                    var2.getGraphics().drawImage(var8, 0, 0, (ImageObserver)null);
                }
            }
        }
        catch (IOException var9)
        {
            var9.printStackTrace();
            return;
        }

        TextureUtil.func_110987_a(this.func_110552_b(), var2);
    }
}
