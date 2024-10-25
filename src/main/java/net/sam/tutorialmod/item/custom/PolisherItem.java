package net.sam.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class PolisherItem extends Item {
    private static final Map<Block, Block> POLISHER_MAP =
            Map.of(
                    Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
                    Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE,
                    Blocks.BASALT, Blocks.POLISHED_BASALT,
                    Blocks.DEEPSLATE, Blocks.POLISHED_DEEPSLATE,
                    Blocks.DIORITE, Blocks.POLISHED_DIORITE,
                    Blocks.GRANITE, Blocks.POLISHED_GRANITE,
                    Blocks.TUFF, Blocks.POLISHED_TUFF
            );

    public PolisherItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (POLISHER_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()){
                world.setBlockState(context.getBlockPos(), POLISHER_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundCategory.BLOCKS);
            }
        }

        return super.useOnBlock(context);
    }
}
