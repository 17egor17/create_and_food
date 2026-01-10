package net.egorplaytv.caf.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.BasinCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedBlazeBurner;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.egorplaytv.caf.integration.animations.AnimatedBlender;

public class BlenderCategory extends BasinCategory {
    private final AnimatedBlender blender = new AnimatedBlender();
    private final AnimatedBlazeBurner heater = new AnimatedBlazeBurner();
    BlenderType type;

    enum BlenderType {
        BEATING, CHOPPING
    }

    public static BlenderCategory standard(Info<BasinRecipe> info) {
        return new BlenderCategory(info, BlenderType.CHOPPING);
    }

    public static BlenderCategory beating(Info<BasinRecipe> info) {
        return new BlenderCategory(info, BlenderType.BEATING);
    }

    public BlenderCategory(Info<BasinRecipe> info, BlenderType type) {
        super(info, type != BlenderType.CHOPPING);
        this.type = type;
    }

    @Override
    public void draw(BasinRecipe recipe, IRecipeSlotsView iRecipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
        super.draw(recipe, iRecipeSlotsView, matrixStack, mouseX, mouseY);

        HeatCondition requiredHeat = recipe.getRequiredHeat();
        if (requiredHeat != HeatCondition.NONE)
            heater.withHeat(requiredHeat.visualizeAsBlazeBurner())
                    .draw(matrixStack, getBackground().getWidth() / 2 + 3, 55);
        blender.draw(matrixStack, getBackground().getWidth() / 2 + 3, 34);
    }
}
