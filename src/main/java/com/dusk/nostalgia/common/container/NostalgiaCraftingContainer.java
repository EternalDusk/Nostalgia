package com.dusk.nostalgia.common.container;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import com.dusk.nostalgia.core.init.ContainerInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NostalgiaCraftingContainer extends AbstractContainerMenu{
	private final ContainerLevelAccess containerAccess;
	
	//Client Constructor
	public NostalgiaCraftingContainer(int id, Inventory playerInv) {
		this(id, playerInv, new ItemStackHandler(27), BlockPos.ZERO);
	}
	
	//Server Constructor
	public NostalgiaCraftingContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos pos) {
		super(ContainerInit.NOSTALGIA_CRAFTING_TABLE.get(), id);
		this.containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
		
		final int slotSizePlus2 = 18, startX = 8, startY = 84, hotbarY = 142, inventoryY = 10;
		for (int column = 0; column < 9; column++) {
			for (int row = 0; row < 3; row++) {
				addSlot(new Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2, startY + row * slotSizePlus2));
			}
			
			addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
		}
		
		for (int column = 0; column < 9; column++) {
			for (int row = 0; row < 3; row++) {
				addSlot(new SlotItemHandler(slots, row * 9 + column, startX + column * slotSizePlus2, inventoryY + row * slotSizePlus2));
			}
		}
		
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            final ItemStack stack = slot.getItem();
            retStack = stack.copy();
            
            final int size = this.slots.size() - player.getInventory().getContainerSize();
            if (index < size) {
                if (!moveItemStackTo(stack, 0, this.slots.size(), false))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(stack, 0, size, false))
                return ItemStack.EMPTY;
            
            if (stack.isEmpty() || stack.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            
            if (stack.getCount() == retStack.getCount())
                return ItemStack.EMPTY;
            
            slot.onTake(player, stack);
        }
        
        return retStack;
    }

	@Override
	public boolean stillValid(Player player) {
		return stillValid(containerAccess, player, Block.byItem(Items.CRAFTING_TABLE));
	}
	
	public static MenuConstructor getServerContainer() {
        return (id, playerInv, player) -> new NostalgiaCraftingContainer(id, playerInv);
    }
	
}
