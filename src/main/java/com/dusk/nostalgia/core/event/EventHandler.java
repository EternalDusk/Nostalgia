package com.dusk.nostalgia.core.event;

import com.dusk.nostalgia.nostalgia;

import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = nostalgia.MOD_ID, bus = Bus.FORGE)
public class EventHandler {
	
	@SubscribeEvent
	public static void onCraftingTableOpen(ScreenOpenEvent event) {
		if (event.isCancelable()) {
            if (event.getScreen() instanceof CraftingScreen) {
                event.setCanceled(true);
                
                //open our own gui
                
            }
        }
	}
	
}
