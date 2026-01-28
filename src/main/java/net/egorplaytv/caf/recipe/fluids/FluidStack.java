package net.egorplaytv.caf.recipe.fluids;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;

import java.util.Optional;

public class FluidStack extends net.minecraftforge.fluids.FluidStack {
    public static final Codec<FluidStack> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Registry.FLUID.byNameCodec().fieldOf("fluid").forGetter(FluidStack::getFluid),
                    Codec.INT.fieldOf("amount").forGetter(FluidStack::getAmount),
                    CompoundTag.CODEC.optionalFieldOf("fluidTag").forGetter(stack -> Optional.ofNullable(stack.getTag()))
            ).apply(instance, (fluid, amount, tag) -> {
                FluidStack stack = new FluidStack(fluid, amount);
                tag.ifPresent(stack::setTag);
                return stack;
            })
    );

    private CompoundTag tag;

    public FluidStack(Fluid fluid, int amount) {
        super(fluid, amount);
    }

    public FluidStack(Fluid fluid, int amount, CompoundTag nbt) {
        this(fluid, amount);

        if (nbt != null)
        {
            tag = nbt.copy();
        }
    }

    public FluidStack(FluidStack stack, int amount) {
        this(stack.getFluid(), amount, stack.tag);
    }

    @Override
    public void setTag(CompoundTag tag) {
        this.tag = tag;
    }

    @Override
    public CompoundTag getTag() {
        return tag;
    }
}
