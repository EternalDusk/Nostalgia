package com.dusk.nostalgia.common.block;

import com.dusk.nostalgia.common.block.entity.NostalgiaCraftingBlockEntity;
import com.dusk.nostalgia.common.container.NostalgiaCraftingMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class NostalgiaCraftingTableBlock extends CraftingTableBlock implements EntityBlock{

	public NostalgiaCraftingTableBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
      if (level.isClientSide) {
         return InteractionResult.SUCCESS;
      } else {
    	  player.openMenu(state.getMenuProvider(level, pos));
    	  player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
         return InteractionResult.CONSUME;
      }
	}

   public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
      return new SimpleMenuProvider((id, inventory, player) -> {
         return new CraftingMenu(id, inventory, ContainerLevelAccess.create(level, pos));
      }, NostalgiaCraftingBlockEntity.TITLE);
   	}
   
   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
       super.createBlockStateDefinition(builder);
   }

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NostalgiaCraftingBlockEntity(pos, state);
	}

}
