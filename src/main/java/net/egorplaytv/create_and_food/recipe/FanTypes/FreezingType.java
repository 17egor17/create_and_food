package net.egorplaytv.create_and_food.recipe.FanTypes;

import com.mojang.math.Vector3f;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import com.simibubi.create.foundation.utility.Color;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import net.egorplaytv.create_and_food.recipe.AllRecipeTypes;
import net.egorplaytv.create_and_food.recipe.FreezingRecipe;
import net.egorplaytv.create_and_food.util.ModTags.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FreezingType implements FanProcessingType {
    private static final FreezingRecipe.FreezingWrapper FREEZING_WRAPPER = new FreezingRecipe.FreezingWrapper();
    private static final DamageSource FREEZING_DAMAGE_SOURCE = DamageSource.FREEZE;

    public boolean isValidAt(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        if (blockState.is(Blocks.FAN_PROCESSING_CATALYSTS_FREEZING)){
            return true;
        }
        return false;
    }

    public int getPriority() {
        return 500;
    }

    public boolean canProcess(ItemStack stack, Level level) {
        FREEZING_WRAPPER.setItem(0, stack);
        Optional<FreezingRecipe> recipe = AllRecipeTypes.FREEZING.find(FREEZING_WRAPPER, level);
        return recipe.isPresent();
    }

    public @Nullable List<ItemStack> process(ItemStack stack, Level level) {
        FREEZING_WRAPPER.setItem(0, stack);
        Optional<FreezingRecipe> recipe = AllRecipeTypes.FREEZING.find(FREEZING_WRAPPER, level);
        if (recipe.isPresent()){
            return RecipeApplier.applyRecipeOn(stack, recipe.get());
        }
        return null;
    }

    public void spawnProcessingParticles(Level level, Vec3 pos) {
        if (level.random.nextInt(8) != 0)
            return;
        Vector3f color = new Color(0xd8e9e9).asVectorF();
        level.addParticle(new DustParticleOptions(color, 1), pos.x + (level.random.nextFloat() - .5f) * .5f,
                pos.y + .5f, pos.z + (level.random.nextFloat() - .5f) * .5f, 0, 1 / 8f, 0);
        level.addParticle(ParticleTypes.SPIT, pos.x + (level.random.nextFloat() - .5f) * .5f, pos.y + .5f,
                pos.z + (level.random.nextFloat() - .5f) * .5f, 0, 1 / 8f, 0);
    }

    public void morphAirFlow(FanProcessingType.AirFlowParticleAccess particleAccess, Random random) {
        particleAccess.setColor(Color.mixColors(0xffffff, 0xd8e9e9, random.nextFloat()));
        particleAccess.setAlpha(1.0F);
        if (random.nextFloat() < 1 / 32f) {
            particleAccess.spawnExtraParticle(ParticleTypes.SNOWFLAKE, 0.125F);
        }

    }

    public void affectEntity(Entity entity, Level level) {
        if (level.isClientSide)
            return;
        if (entity.isOnFire()) {
            entity.clearFire();
            level.playSound(null, entity.blockPosition(), SoundEvents.GENERIC_EXTINGUISH_FIRE,
                    SoundSource.NEUTRAL, 0.7F, 1.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
        }

        int i = entity.getTicksFrozen();
        if (entity.getTicksFrozen() <= 180) {
            entity.setTicksFrozen(i + 5);
        }
        int t = 0;
        if (entity.isFullyFrozen()) {
            ++t;
            if (t >= 15) {
                entity.hurt(FREEZING_DAMAGE_SOURCE, 1.0F);
                t = 0;
            }
        }
    }
}
