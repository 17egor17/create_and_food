package net.egorplaytv.create_and_food.util;

import com.simibubi.create.foundation.fluid.SmartFluidTank;
import com.simibubi.create.foundation.utility.NBTHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.function.Consumer;

public class CAFFluidTank extends FluidTank {
    private final BehaviourType type;
    public static final BehaviourType
            TYPE = new BehaviourType(), INPUT = new BehaviourType("Input"), OUTPUT = new BehaviourType("Output");
    protected TankSegment[] tanks;
    public CAFFluidTank(BehaviourType type, int tanks, int capacity) {
        super(capacity);
        this.type = type;
        this.tanks = new TankSegment[tanks];
        IFluidHandler[] handlers = new IFluidHandler[tanks];
        for (int i = 0; i < tanks; i++) {
            TankSegment tankSegment = new TankSegment(capacity);
            this.tanks[i] = tankSegment;
            handlers[i] = tankSegment.tank;
        }
    }

    public BehaviourType getType() {
        return type;
    }

    public void forEach(Consumer<TankSegment> action) {
        for (TankSegment tankSegment : tanks)
            action.accept(tankSegment);
    }

    @Override
    public CompoundTag writeToNBT(CompoundTag nbt) {
        ListTag tanksNBT = new ListTag();
        forEach(ts -> tanksNBT.add(ts.writeNBT()));
        nbt.put(getType().getName() + "Tanks", tanksNBT);
        return super.writeToNBT(nbt);
    }

    @Override
    public FluidTank readFromNBT(CompoundTag nbt) {
        MutableInt index = new MutableInt(0);
        NBTHelper.iterateCompoundList(nbt.getList(getType().getName() + "Tanks", Tag.TAG_COMPOUND), c -> {
            if (index.intValue() >= tanks.length)
                return;
            tanks[index.intValue()].readNBT(c);
            index.increment();
        });
        return super.readFromNBT(nbt);
    }


    public class TankSegment {
        protected SmartFluidTank tank;
        protected FluidStack renderedFluid;

        public TankSegment(int capacity) {
            tank = new SmartFluidTank(capacity, f -> onFluidStackChanged());
        }


        public void onFluidStackChanged() {
            if (!tank.getFluid().isEmpty()){
                renderedFluid = tank.getFluid();
            }
        }

        public FluidStack getRenderedFluid() {
            return renderedFluid;
        }

        public CompoundTag writeNBT() {
            CompoundTag compound = new CompoundTag();
            compound.put("TankContent", tank.writeToNBT(new CompoundTag()));
            return compound;
        }

        public void readNBT(CompoundTag compound) {
            tank.readFromNBT(compound.getCompound("TankContent"));
            if (!tank.getFluid()
                    .isEmpty())
                renderedFluid = tank.getFluid();
        }

        public boolean isEmpty() {
            FluidStack renderedFluid = getRenderedFluid();
            if (renderedFluid.isEmpty())
                return true;
            return false;
        }
    }

    public static class BehaviourType{
        private String name;

        public BehaviourType(String name) {
            this.name = name;
        }

        public BehaviourType() {
            this("");
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            return super.hashCode() * 31 * 493286711; // Better hash table distribution
        }
    }
}
