package net.egorplaytv.caf.util.degree;

public interface DegreeValueData {
    DegreeValue get(int pIndex);

    void set(int pIndex, DegreeValue pValue);

    int getCount();
}
