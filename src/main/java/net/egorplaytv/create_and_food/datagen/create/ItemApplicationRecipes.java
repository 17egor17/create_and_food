package net.egorplaytv.create_and_food.datagen.create;

import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.datagen.custom.ItemApplicationRecipeBuilder;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.egorplaytv.create_and_food.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolAction;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class ItemApplicationRecipes {
    private static void itemApplication(Consumer<FinishedRecipe> pConsumer) {
        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.ALLOY_SOULS_CASING.get())
                .addIngredient(CAFTags.forgeItemTag("stripped_logs"))
                .addIngredient(CAFItems.ALLOY_SOULS.get())
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.ALLOY_SOULS_CASING.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.ALLOY_SOULS_GLASS.get())
                .addIngredient(CAFTags.forgeItemTag("glass/colorless"))
                .addIngredient(CAFItems.ALLOY_SOULS.get())
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.ALLOY_SOULS_GLASS.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.BLACK_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/black"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.BLACK_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.BLUE_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/blue"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.BLUE_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.BROWN_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/brown"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.BROWN_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.CYAN_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/cyan"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.CYAN_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_CALCITE.get())
                .addIngredient(Items.PAPER)
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.GOLDEN_WALL.get())
                .addIngredient(CAFBlocks.RUBY_BLOCK.get())
                .addIngredient(CAFTags.forgeItemTag("plates/gold"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.GOLDEN_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.GREEN_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/green"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.GREEN_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.LUMINOUS_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/luminous"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.LUMINOUS_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.ORANGE_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/orange"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.ORANGE_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.RED_FRAMED_WALL.get())
                .addIngredient(CAFBlocks.FRAMED_WALL.get())
                .addIngredient(CAFTags.forgeItemTag("dyes/red"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.RED_FRAMED_WALL.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.STEEL_CASING.get())
                .addIngredient(CAFTags.forgeItemTag("stripped_logs"))
                .addIngredient(CAFTags.forgeItemTag("ingots/steel"))
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.STEEL_CASING.get())));

        ItemApplicationRecipeBuilder.itemApplicationRecipe(CAFBlocks.UNBAKED_CLAY.get())
                .addIngredient(Items.CLAY)
                .addIngredient(Items.WHEAT)
                .save(pConsumer, getItemApplication(getRecipeId(CAFBlocks.UNBAKED_CLAY.get())));

//        ItemApplicationRecipeBuilder.itemApplicationRecipe()
//                .addIngredient()
//                .addIngredient()
//                .save(pConsumer, getItemApplication(getRecipeId()));
    }

    public static ResourceLocation getItemApplication(String id) {
        return new ResourceLocation(MOD_ID, "create/item_application/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeIdItemFrom(ItemLike result, ItemLike from){
        return result.asItem().getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        itemApplication(pConsumer);
    }
}
