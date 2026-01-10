package net.egorplaytv.caf.recipe.fluids;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;

import java.util.Optional;

public class FluidStack extends net.minecraftforge.fluids.FluidStack {
    public static final Codec<net.minecraftforge.fluids.FluidStack> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Registry.FLUID.byNameCodec().fieldOf("fluid").forGetter(net.minecraftforge.fluids.FluidStack::getFluid),
                    Codec.INT.fieldOf("amount").forGetter(net.minecraftforge.fluids.FluidStack::getAmount),
                    CompoundTag.CODEC.optionalFieldOf("fluidTag").forGetter(stack -> Optional.ofNullable(stack.getTag()))
            ).apply(instance, (fluid, amount, tag) -> {
                net.minecraftforge.fluids.FluidStack stack = new net.minecraftforge.fluids.FluidStack(fluid, amount);
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

    public FluidStack(net.minecraftforge.fluids.FluidStack stack, int amount) {
        this(stack.getFluid(), amount, stack.getTag());
    }
}
