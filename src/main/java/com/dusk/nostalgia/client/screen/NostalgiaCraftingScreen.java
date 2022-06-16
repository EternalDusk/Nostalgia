package com.dusk.nostalgia.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;


import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.common.container.NostalgiaCraftingMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class NostalgiaCraftingScreen extends AbstractContainerScreen<NostalgiaCraftingMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(nostalgia.MOD_ID, "textures/gui/crafting.png");
	public NostalgiaCraftingScreen(NostalgiaCraftingMenu container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 512;
		this.imageHeight = 332;
	}

	@Override
	protected void renderBg(PoseStack stack, float mouseX, int mouseY, int partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}
	
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		drawString(stack, font, title, this.leftPos + 8, this.topPos + 3, 0x404040); //hexadecimal
		drawString(stack, font, playerInventoryTitle, this.leftPos + 8, this.topPos + 80, 0x404040);
	}
	
	@SuppressWarnings("resource")
	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new ExtendedButton(leftPos, topPos, 16, 16, (Component) Component.EMPTY, btn -> {
			Minecraft.getInstance().player.displayClientMessage(Component.literal("beans"), false);
		}));
	}
	
}
