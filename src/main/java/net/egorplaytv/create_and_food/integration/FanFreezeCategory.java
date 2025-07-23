package net.egorplaytv.create_and_food.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.ProcessingViaFanCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import net.egorplaytv.create_and_food.recipe.FreezingRecipe;
import net.minecraft.world.level.block.Blocks;

public class FanFreezeCategory extends ProcessingViaFanCategory.MultiOutput<FreezingRecipe> {
    public FanFreezeCategory(CreateRecipeCategory.Info<FreezingRecipe> info) {
        super(info);
    }

    protected void renderAttachedBlock(PoseStack matrixStack) {
        GuiGameElement.of(Blocks.POWDER_SNOW.defaultBlockState())
                .scale((double)24.0F)
                .atLocal((double)0.0F, (double)0.0F, (double)2.0F)
                .lighting(AnimatedKinetics.DEFAULT_LIGHTING)
                .render(matrixStack);
    }
}