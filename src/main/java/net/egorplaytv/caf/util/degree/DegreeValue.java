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
        return ((Math.round(this.degree * 100)) / 100F);
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

    public static class DegreeValueInteger {
        private int degree;

        public DegreeValueInteger() {
            this.degree = 0;
        }

        public DegreeValueInteger(int degree) {
            this.degree = degree;
        }

        public DegreeValueInteger(float degree) {
            this.degree = Math.round(degree);
        }

        public DegreeValueInteger(DegreeValue degree) {
            this.degree = Math.round(degree.getDegree());
        }

        public int getDegree() {
            return this.degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public static DegreeValueInteger fromJson(JsonObject json) {
            return new DegreeValueInteger(GsonHelper.getAsFloat(json, "degree", 100));
        }

        public static DegreeValueInteger fromNetwork(FriendlyByteBuf buf) {
            return new DegreeValueInteger(buf.readFloat());
        }

        public static void toNetwork(FriendlyByteBuf buf, DegreeValueInteger value) {
            buf.writeFloat(value.getDegree());
        }
    }
}
