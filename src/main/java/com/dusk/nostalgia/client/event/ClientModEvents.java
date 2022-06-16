package com.dusk.nostalgia.client.event;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.client.screen.NostalgiaCraftingScreen;
import com.dusk.nostalgia.core.init.ContainerInit;
import com.dusk.nostalgia.core.init.BlockInit;
import com.dusk.nostalgia.core.init.BlockEntityInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = nostalgia.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
	private ClientModEvents() {
	}
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(BlockInit.NOSTALGIA_CRAFTING_TABLE.get(), RenderType.cutout());
		MenuScreens.register(ContainerInit.NOSTALGIA_CRAFTING_MENU().get(), NostalgiaCraftingScreen::new);
	}
}
