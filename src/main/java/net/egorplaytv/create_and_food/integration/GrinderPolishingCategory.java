package net.egorplaytv.create_and_food.integration;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.gui.AllGuiTextures;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.egorplaytv.create_and_food.config.CAFConfigs;
import net.egorplaytv.create_and_food.integration.animations.AnimatedGrinder;
import net.egorplaytv.create_and_food.recipe.PolishingRecipe;
import net.egorplaytv.create_and_food.screen.gui.CAFGuiTextures;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

@ParametersAreNonnullByDefault
public class GrinderPolishingCategory extends CreateRecipeCategory<PolishingRecipe> {

    private final AnimatedGrinder grinder = new AnimatedGrinder();

    public GrinderPolishingCategory(Info<PolishingRecipe> info) {
        super(info);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PolishingRecipe recipe, IFocusGroup focuses) {
        builder
                .addSlot(RecipeIngredientRole.INPUT, 44, 5)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(recipe.getIngredients().get(0));

        List<ProcessingOutput> results = recipe.getRollableResults();
        int i = 0;
        for (ProcessingOutput output : results) {
            int xOffset = i % 2 == 0 ? 0 : 19;
            int yOffset = (i / 2) * -19;
            builder
                    .addSlot(RecipeIngredientRole.OUTPUT, 118 + xOffset, 48 + yOffset)
                    .setBackground(getRenderedSlot(output), -1, -1)
                    .addItemStack(output.getStack())
                    .addTooltipCallback(addStochasticTooltip(output));
            i++;
        }
    }

    @Override
    public void draw(PolishingRecipe recipe, IRecipeSlotsView iRecipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
        AllGuiTextures.JEI_DOWN_ARROW.render(matrixStack, 70, 6);
        AllGuiTextures.JEI_SHADOW.render(matrixStack, 72 - 17, 42 + 13);

        grinder.draw(matrixStack, 72, 42);

        int speedLimits = recipe.getSpeedLimits();

        String speedText = (speedLimits == 1)? "low":
                (speedLimits == 2)? "medium":
                        (speedLimits == 3)? "high":
                                "any";
        ChatFormatting style = 	(speedLimits == 1)? ChatFormatting.GREEN:
                (speedLimits == 2)? ChatFormatting.YELLOW:
                        (speedLimits == 3)? ChatFormatting.RED:
                                ChatFormatting.WHITE;

        MutableComponent text = TextUtils.getJeiTranslation("text.required_speed")
                .append(TextUtils.getJeiTranslation("text." + speedText).withStyle(style));

        int width = Minecraft.getInstance().font.width(text);

        Minecraft.getInstance().font.draw(matrixStack,  text, (177 - width)/2.0f, 75, 0xFFFFFF);

        if (recipe.isFragile()) {
            CAFGuiTextures.JEI_FRAGILE.render(matrixStack, 120, 5);
            if (mouseX >= 120 && mouseX <= 133 && mouseY >= 5 && mouseY <= 28)
                Minecraft.getInstance().screen.renderComponentTooltip(matrixStack, addToTooltip(new LinkedList<>()), (int)mouseX,  (int)mouseY);
        }
    }

    private List<Component> addToTooltip(List<Component> list) {
        if (CAFConfigs.server().recipes.destroyOnWrongGrinderSpeed.get()) {
            list.add(TextUtils.getJeiTranslation("text.fragile.destroy")
                    .withStyle(ChatFormatting.GOLD));
        } else {
            list.add(TextUtils.getJeiTranslation("text.fragile")
                    .withStyle(ChatFormatting.GOLD));
        }
        return list;
    }

}