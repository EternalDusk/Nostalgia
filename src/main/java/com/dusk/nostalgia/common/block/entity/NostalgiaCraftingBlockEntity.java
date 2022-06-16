package com.dusk.nostalgia.common.block.entity;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.core.init.BlockEntityInit;
import com.dusk.nostalgia.common.block.NostalgiaCraftingTableBlock;
import com.dusk.nostalgia.common.block.entity.util.InventoryBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class NostalgiaCraftingBlockEntity extends CraftingTableBlock{
	
	public static final Component TITLE = Component.translatable("container." + nostalgia.MOD_ID + ".nostalgia_crafting_table");
	
	public NostalgiaCraftingBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.NOSTALGIA_CRAFTING_TABLE.get(), pos, state, 27);
	}
	
}
