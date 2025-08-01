package net.egorplaytv.create_and_food.block.block_item;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockItem;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlazeFreezeBurnerBlockItem extends BlockItem {
    private final boolean capturedBlaze;

    public static BlazeFreezeBurnerBlockItem empty(Properties properties) {
        return new BlazeFreezeBurnerBlockItem(AllBlocks.BLAZE_BURNER.get(), properties, false);
    }

    public static BlazeFreezeBurnerBlockItem withBlaze(Block block, Properties properties) {
        return new BlazeFreezeBurnerBlockItem(block, properties, true);
    }

    @Override
    public void registerBlocks(Map<Block, Item> p_195946_1_, Item p_195946_2_) {
        if (!hasCapturedBlaze())
            return;
        super.registerBlocks(p_195946_1_, p_195946_2_);
    }

    private BlazeFreezeBurnerBlockItem(Block block, Properties properties, boolean capturedBlaze) {
        super(block, properties);
        this.capturedBlaze = capturedBlaze;
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_150895_1_, NonNullList<ItemStack> p_150895_2_) {
        if (!hasCapturedBlaze())
            return;
        super.fillItemCategory(p_150895_1_, p_150895_2_);
    }

    @Override
    public String getDescriptionId() {
        return hasCapturedBlaze() ? super.getDescriptionId() : "item.create." + RegisteredObjects.getKeyOrThrow(this).getPath();
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack heldItem, Player player, LivingEntity entity,
                                                  InteractionHand hand) {
        if (hasCapturedBlaze())
            return InteractionResult.PASS;
        if (!(entity instanceof Blaze))
            return InteractionResult.PASS;

        Level world = player.level;
        spawnCaptureEffects(world, entity.position());
        if (world.isClientSide)
            return InteractionResult.FAIL;

        giveBurnerItemTo(player, heldItem, hand);
        entity.discard();
        return InteractionResult.FAIL;
    }

    protected void giveBurnerItemTo(Player player, ItemStack heldItem, InteractionHand hand) {
        ItemStack filled = AllBlocks.BLAZE_BURNER.asStack();
        if (!player.isCreative())
            heldItem.shrink(1);
        if (heldItem.isEmpty()) {
            player.setItemInHand(hand, filled);
            return;
        }
        player.getInventory()
                .placeItemBackInInventory(filled);
    }

    private void spawnCaptureEffects(Level world, Vec3 vec) {
        if (world.isClientSide) {
            for (int i = 0; i < 40; i++) {
                Vec3 motion = VecHelper.offsetRandomly(Vec3.ZERO, world.random, .125f);
                world.addParticle(ParticleTypes.FLAME, vec.x, vec.y, vec.z, motion.x, motion.y, motion.z);
                Vec3 circle = motion.multiply(1, 0, 1)
                        .normalize()
                        .scale(.5f);
                world.addParticle(ParticleTypes.SMOKE, circle.x, vec.y, circle.z, 0, -0.125, 0);
            }
            return;
        }

        BlockPos soundPos = new BlockPos(vec);
        world.playSound(null, soundPos, SoundEvents.BLAZE_HURT, SoundSource.HOSTILE, .25f, .75f);
        world.playSound(null, soundPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.HOSTILE, .5f, .75f);
    }

    public boolean hasCapturedBlaze() {
        return capturedBlaze;
    }
}
