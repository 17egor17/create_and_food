package net.egorplaytv.caf.util.degree;

public class SimpleDegreeValueData implements DegreeValueData {
    private final DegreeValue[] degreeValues;

    public SimpleDegreeValueData(int pSize) {
        this.degreeValues = new DegreeValue[pSize];
    }

    @Override
    public DegreeValue get(int pIndex) {
        return this.degreeValues[pIndex];
    }

    @Override
    public void set(int pIndex, DegreeValue pValue) {
        this.degreeValues[pIndex] = pValue;
    }

    @Override
    public int getCount() {
        return this.degreeValues.length;
    }
}
