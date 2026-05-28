package net.egorplaytv.caf.util.degree;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;

public class DegreeValue {
    private float degree;

    public DegreeValue() {
        this.degree = 0F;
    }

    public DegreeValue(float degree) {
        this.degree = degree;
    }

    public float getDegree() {
        return this.degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }


    public static DegreeValue fromJson(JsonObject json) {
        return new DegreeValue(GsonHelper.getAsFloat(json, "degree", 100));
    }

    public static DegreeValue fromNetwork(FriendlyByteBuf buf) {
        return new DegreeValue(buf.readFloat());
    }

    public static void toNetwork(FriendlyByteBuf buf, DegreeValue value) {
        buf.writeFloat(value.getDegree());
    }
}
