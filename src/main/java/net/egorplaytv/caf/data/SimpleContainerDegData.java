package net.egorplaytv.caf.data;

public class SimpleContainerDegData implements ContainerDegData {
    private final float[] floats;

    public SimpleContainerDegData(int size) {
        this.floats = new float[size];
    }

    @Override
    public float get(int pIndex) {
        return floats[pIndex];
    }

    @Override
    public void set(int pIndex, float pValue) {
        this.floats[pIndex] = pValue;
    }

    @Override
    public int getCount() {
        return this.floats.length;
    }
}
