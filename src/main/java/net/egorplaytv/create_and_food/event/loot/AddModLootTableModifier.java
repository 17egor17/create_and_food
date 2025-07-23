package net.egorplaytv.create_and_food.event.loot;

import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class AddModLootTableModifier extends LootModifier {
    private final ResourceLocation lootTable;

    protected AddModLootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
        super(conditionsIn);
        this.lootTable = lootTable;
    }

    @Nonnull
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (CreateAndFoodCommonConfigs.GENERATE_CAF_CHEST_LOOT.get()) {
            LootTable extraTable = context.getLootTable(this.lootTable);
            Objects.requireNonNull(generatedLoot);
            extraTable.getRandomItems(context, generatedLoot::add);
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AddModLootTableModifier> {
        public Serializer() {
        }

        public AddModLootTableModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
            ResourceLocation lootTable = new ResourceLocation(GsonHelper.getAsString(object, "lootTable"));
            return new AddModLootTableModifier(conditions, lootTable);
        }

        public JsonObject write(AddModLootTableModifier instance) {
            JsonObject object = this.makeConditions(instance.conditions);
            object.addProperty("lootTable", instance.lootTable.toString());
            return object;
        }
    }
}