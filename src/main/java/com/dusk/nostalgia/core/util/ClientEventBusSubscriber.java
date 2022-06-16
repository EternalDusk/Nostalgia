package com.dusk.nostalgia.core.util;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.client.screen.NostalgiaCraftingScreen;
import com.dusk.nostalgia.core.init.ContainerInit;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = nostalgia.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(ContainerInit.NOSTALGIA_CRAFTING_MENU.get(), NostalgiaCraftingScreen::new);
	}
	
}
