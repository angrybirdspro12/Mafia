package org.darkstorm.minecraft.gui.theme.simple;

import java.awt.Font;

import Mafia.Mafia;
import net.minecraft.src.FontRenderer;

import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import org.darkstorm.minecraft.gui.theme.AbstractTheme;

public class SimpleTheme extends AbstractTheme {
	private final FontRenderer fontRenderer;

    Minecraft mc;
	public SimpleTheme(Minecraft mc) {
        this.mc = mc;
		fontRenderer = new FontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);

		installUI(new SimpleFrameUI(this));
		installUI(new SimplePanelUI(this));
		installUI(new SimpleLabelUI(this));
		installUI(new SimpleButtonUI(this));
		installUI(new SimpleCheckButtonUI(this));
		installUI(new SimpleComboBoxUI(this));
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
}
