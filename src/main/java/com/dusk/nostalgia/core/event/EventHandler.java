package com.dusk.nostalgia.core.event;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.common.container.NostalgiaCraftingContainer;

import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(modid = nostalgia.MOD_ID, bus = Bus.FORGE)
public class EventHandler {
	
	@SubscribeEvent
	public static void onCraftingTableOpen(ScreenOpenEvent event) {
		if (event.isCancelable()) {
            if (event.getScreen() instanceof CraftingScreen) {
                event.setCanceled(true);
                
                //open our own gui
                final MenuProvider container = new SimpleMenuProvider(NostalgiaCraftingContainer.getServerContainer(), null);
                NetworkHooks.openGui((Player) Player, container);
                
            }
        }
	}
	
}
