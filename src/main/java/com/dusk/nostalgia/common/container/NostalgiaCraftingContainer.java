package com.dusk.nostalgia.common.container;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import com.dusk.nostalgia.core.init.ContainerInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class NostalgiaCraftingContainer extends AbstractContainerMenu{
	private final ContainerLevelAccess containerAccess;
	public final ContainerData data;
	
	//Client Constructor
	public NostalgiaCraftingContainer(int id, Inventory playerInv) {
		this(id, playerInv, new ItemStackHandler(27), BlockPos.ZERO, new SimpleContainerData(0));
	}
	
	//Server Constructor
	public NostalgiaCraftingContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
		super(ContainerInit.NOSTALGIA_CRAFTING_TABLE.get(), id);
		this.containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
		this.data = data;
		
		
		
	}

	@Override
	public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(containerAccess, player, <CraftingTableBlock>);
	}
	
}
