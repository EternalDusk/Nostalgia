package com.dusk.nostalgia.common.container;

import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.Optional;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class NostalgiaCraftingMenu extends AbstractContainerMenu{
	
	private final ContainerLevelAccess access;
	public static final int RESULT_SLOT = 0;
	private static final int CRAFT_SLOT_START = 1;
	private static final int CRAFT_SLOT_END = 10;
	private static final int INV_SLOT_START = 10;
	private static final int INV_SLOT_END = 37;
	private static final int USE_ROW_SLOT_START = 37;
	private static final int USE_ROW_SLOT_END = 46;
	private final CraftingContainer craftSlots = new CraftingContainer(this, 3, 3);
	private final ResultContainer resultSlots = new ResultContainer();
	private final Player player;
	
	//Client Constructor
	public NostalgiaCraftingMenu(int id, Inventory playerInv) {
		this(id, playerInv, ContainerLevelAccess.NULL);
	}
	
	//Server Constructor
	public NostalgiaCraftingMenu(int id, Inventory playerInv, ContainerLevelAccess access) {
		super(MenuType.CRAFTING, id);
		   this.access = access;
		   this.player = playerInv.player;
		   this.addSlot(new ResultSlot(playerInv.player, this.craftSlots, this.resultSlots, 0, 124, 35));
		
		   for(int i = 0; i < 3; ++i) {
		      for(int j = 0; j < 3; ++j) {
		         this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
		      }
		   }
		
		   for(int k = 0; k < 3; ++k) {
		      for(int i1 = 0; i1 < 9; ++i1) {
		         this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
		      }
		   }
		
		   for(int l = 0; l < 9; ++l) {
		      this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
		   }
	}
	
	protected static void slotChangedCraftingGrid(AbstractContainerMenu menu, Level world, Player player, CraftingContainer craftContainer, ResultContainer resultContainer) {
	   if (!world.isClientSide) {
	      ServerPlayer serverplayer = (ServerPlayer) player;
	      ItemStack itemstack = ItemStack.EMPTY;
	      Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftContainer, world);
	      if (optional.isPresent()) {
	         CraftingRecipe craftingrecipe = optional.get();
	         if (resultContainer.setRecipeUsed(world, serverplayer, craftingrecipe)) {
	            itemstack = craftingrecipe.assemble(craftContainer);
	         }
	      }
	
	      resultContainer.setItem(0, itemstack);
	      menu.setRemoteSlot(0, itemstack);
	      serverplayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, itemstack));
	   }
	}
	
	public void slotsChanged(Container p_39366_) {
	   this.access.execute((level, blockPos) -> {
	      slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots);
	   });
	}
	
	public void fillCraftSlotsStackedContents(StackedContents contents) {
	   this.craftSlots.fillStackedContents(contents);
	}
	
	public void clearCraftingContent() {
	   this.craftSlots.clearContent();
	   this.resultSlots.clearContent();
	}
	
	public boolean recipeMatches(Recipe<? super CraftingContainer> recipeMatches) {
	   return recipeMatches.matches(this.craftSlots, this.player.level);
	}
	
	public void removed(Player player) {
	   super.removed(player);
	   this.access.execute((level, blockPos) -> {
	      this.clearContainer(player, this.craftSlots);
	   });
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
	
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
	}
	
	public int getResultSlotIndex() {
		return 0;
	}
	
	public int getGridWidth() {
		return this.craftSlots.getWidth();
	}
	
	public int getGridHeight() {
		return this.craftSlots.getHeight();
	}
	
	public int getSize() {
		return 10;
	}
	
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.CRAFTING;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, Blocks.CRAFTING_TABLE);
	}
	
}
