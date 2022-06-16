package com.dusk.nostalgia.core.event;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.client.screen.NostalgiaCraftingScreen;
import com.dusk.nostalgia.common.container.NostalgiaCraftingMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(modid = nostalgia.MOD_ID, bus = Bus.FORGE)
public class EventHandler {
	
	@SubscribeEvent
	public static void onInteract(PlayerInteractEvent.RightClickBlock event) {
		Player player = event.getPlayer();
		InteractionHand hand = event.getHand();
		BlockPos pos = event.getPos();
		Level level = event.getWorld();
		//BlockHitResult hitVec = event.getHitVec();
		
		BlockState block = event.getWorld().getBlockState(pos);
		
        if (block.getBlock() == Blocks.CRAFTING_TABLE) {
            System.out.println(player.getName() + " tried to open a crafting table.");
            NetworkHooks.openGui(null, null);
        }
		
	}
}