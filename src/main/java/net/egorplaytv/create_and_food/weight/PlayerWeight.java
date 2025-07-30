package net.egorplaytv.create_and_food.weight;

import net.egorplaytv.create_and_food.block.entity.custom.ScalesBlockEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class PlayerWeight {
    Player player;
    Map<Item, Integer> weight;
    float defaultWeight = 62000;
    private static int defaultWeightForItems = 100;
    private static int defaultWeightForArmorHead = 750;
    private static int defaultWeightForArmorChest = 750;
    private static int defaultWeightForArmorLegs = 750;
    private static int defaultWeightForArmorFeet = 750;
    private static int error = 0;


    public PlayerWeight(Player player){
        this.player = player;
        this.weight = ScalesBlockEntity.getWeight();
    }

    public float getDefaultWeight() {
        return defaultWeight;
    }

    public void setDefaultWeight(float defaultWeight){
        this.defaultWeight = defaultWeight;
    }

    public float AllInventoryWeight(){
        Inventory inventory = player.getInventory();
        float weightSlotHand;
        if (!player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.OFFHAND).getItem()) != null){
            ItemStack Hand = player.getItemBySlot(EquipmentSlot.OFFHAND);
            weightSlotHand = (weight.get(Hand.getItem()) + weight.get(Hand.getItem()) > 40 ? error : 0) * Hand.getCount();
        } else if (!player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.OFFHAND).getItem()) == null)
            weightSlotHand = defaultWeightForItems * player.getItemBySlot(EquipmentSlot.OFFHAND).getCount();
        else
            weightSlotHand = 0;
        float weightSlot0;
        if (!inventory.getItem(0).isEmpty()
                && weight.get(inventory.getItem(0).getItem()) != null){
            ItemStack stack = inventory.getItem(0);
            weightSlot0 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(0).isEmpty()
                && weight.get(inventory.getItem(0).getItem()) == null)
            weightSlot0 = defaultWeightForItems * inventory.getItem(0).getCount();
        else
            weightSlot0 = 0;
        float weightSlot1;
        if (!inventory.getItem(1).isEmpty()
                && weight.get(inventory.getItem(1).getItem()) != null){
            ItemStack stack = inventory.getItem(1);
            weightSlot1 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(1).isEmpty()
                && weight.get(inventory.getItem(1).getItem()) == null)
            weightSlot1 = defaultWeightForItems * inventory.getItem(1).getCount();
        else
            weightSlot1 = 0;
        float weightSlot2;
        if (!inventory.getItem(2).isEmpty()
                && weight.get(inventory.getItem(2).getItem()) != null){
            ItemStack stack = inventory.getItem(2);
            weightSlot2 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(2).isEmpty()
                && weight.get(inventory.getItem(2).getItem()) == null)
            weightSlot2 = defaultWeightForItems * inventory.getItem(2).getCount();
        else
            weightSlot2 = 0;
        float weightSlot3;
        if (!inventory.getItem(3).isEmpty()
                && weight.get(inventory.getItem(3).getItem()) != null){
            ItemStack stack = inventory.getItem(3);
            weightSlot3 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(3).isEmpty()
                && weight.get(inventory.getItem(3).getItem()) == null)
            weightSlot3 = defaultWeightForItems * inventory.getItem(3).getCount();
        else
            weightSlot3 = 0;
        float weightSlot4;
        if (!inventory.getItem(4).isEmpty()
                && weight.get(inventory.getItem(4).getItem()) != null){
            ItemStack stack = inventory.getItem(4);
            weightSlot4 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(4).isEmpty()
                && weight.get(inventory.getItem(4).getItem()) == null)
            weightSlot4 = defaultWeightForItems * inventory.getItem(4).getCount();
        else
            weightSlot4 = 0;
        float weightSlot5;
        if (!inventory.getItem(5).isEmpty()
                && weight.get(inventory.getItem(5).getItem()) != null){
            ItemStack stack = inventory.getItem(5);
            weightSlot5 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(5).isEmpty()
                && weight.get(inventory.getItem(5).getItem()) == null)
            weightSlot5 = defaultWeightForItems * inventory.getItem(5).getCount();
        else
            weightSlot5 = 0;
        float weightSlot6;
        if (!inventory.getItem(6).isEmpty()
                && weight.get(inventory.getItem(6).getItem()) != null){
            ItemStack stack = inventory.getItem(6);
            weightSlot6 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(6).isEmpty()
                && weight.get(inventory.getItem(6).getItem()) == null)
            weightSlot6 = defaultWeightForItems * inventory.getItem(6).getCount();
        else
            weightSlot6 = 0;
        float weightSlot7;
        if (!inventory.getItem(7).isEmpty()
                && weight.get(inventory.getItem(7).getItem()) != null){
            ItemStack stack = inventory.getItem(7);
            weightSlot7 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(7).isEmpty()
                && weight.get(inventory.getItem(7).getItem()) == null)
            weightSlot7 = defaultWeightForItems * inventory.getItem(7).getCount();
        else
            weightSlot7 = 0;
        float weightSlot8;
        if (!inventory.getItem(8).isEmpty()
                && weight.get(inventory.getItem(8).getItem()) != null){
            ItemStack stack = inventory.getItem(8);
            weightSlot8 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(8).isEmpty()
                && weight.get(inventory.getItem(8).getItem()) == null)
            weightSlot8 = defaultWeightForItems * inventory.getItem(8).getCount();
        else
            weightSlot8 = 0;

        float weightSlot9;
        if (!inventory.getItem(9).isEmpty()
                && weight.get(inventory.getItem(9).getItem()) != null){
            ItemStack stack = inventory.getItem(9);
            weightSlot9 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(9).isEmpty()
                && weight.get(inventory.getItem(9).getItem()) == null)
            weightSlot9 = defaultWeightForItems * inventory.getItem(9).getCount();
        else
            weightSlot9 = 0;
        float weightSlot10;
        if (!inventory.getItem(10).isEmpty()
                && weight.get(inventory.getItem(10).getItem()) != null){
            ItemStack stack = inventory.getItem(10);
            weightSlot10 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(10).isEmpty()
                && weight.get(inventory.getItem(10).getItem()) == null)
            weightSlot10 = defaultWeightForItems * inventory.getItem(10).getCount();
        else
            weightSlot10 = 0;
        float weightSlot11;
        if (!inventory.getItem(11).isEmpty()
                && weight.get(inventory.getItem(11).getItem()) != null){
            ItemStack stack = inventory.getItem(11);
            weightSlot11 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(11).isEmpty()
                && weight.get(inventory.getItem(11).getItem()) == null)
            weightSlot11 = defaultWeightForItems * inventory.getItem(11).getCount();
        else
            weightSlot11 = 0;
        float weightSlot12;
        if (!inventory.getItem(12).isEmpty()
                && weight.get(inventory.getItem(12).getItem()) != null){
            ItemStack stack = inventory.getItem(12);
            weightSlot12 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(12).isEmpty()
                && weight.get(inventory.getItem(12).getItem()) == null)
            weightSlot12 = defaultWeightForItems * inventory.getItem(12).getCount();
        else
            weightSlot12 = 0;
        float weightSlot13;
        if (!inventory.getItem(13).isEmpty()
                && weight.get(inventory.getItem(13).getItem()) != null){
            ItemStack stack = inventory.getItem(13);
            weightSlot13 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(13).isEmpty()
                && weight.get(inventory.getItem(13).getItem()) == null)
            weightSlot13 = defaultWeightForItems * inventory.getItem(13).getCount();
        else
            weightSlot13 = 0;
        float weightSlot14;
        if (!inventory.getItem(14).isEmpty()
                && weight.get(inventory.getItem(14).getItem()) != null){
            ItemStack stack = inventory.getItem(14);
            weightSlot14 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(14).isEmpty()
                && weight.get(inventory.getItem(14).getItem()) == null)
            weightSlot14 = defaultWeightForItems * inventory.getItem(14).getCount();
        else
            weightSlot14 = 0;
        float weightSlot15;
        if (!inventory.getItem(15).isEmpty()
                && weight.get(inventory.getItem(15).getItem()) != null){
            ItemStack stack = inventory.getItem(15);
            weightSlot15 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(15).isEmpty()
                && weight.get(inventory.getItem(15).getItem()) == null)
            weightSlot15 = defaultWeightForItems * inventory.getItem(15).getCount();
        else
            weightSlot15 = 0;
        float weightSlot16;
        if (!inventory.getItem(16).isEmpty()
                && weight.get(inventory.getItem(16).getItem()) != null){
            ItemStack stack = inventory.getItem(16);
            weightSlot16 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(16).isEmpty()
                && weight.get(inventory.getItem(16).getItem()) == null)
            weightSlot16 = defaultWeightForItems * inventory.getItem(16).getCount();
        else
            weightSlot16 = 0;
        float weightSlot17;
        if (!inventory.getItem(17).isEmpty()
                && weight.get(inventory.getItem(17).getItem()) != null){
            ItemStack stack = inventory.getItem(17);
            weightSlot17 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(17).isEmpty()
                && weight.get(inventory.getItem(17).getItem()) == null)
            weightSlot17 = defaultWeightForItems * inventory.getItem(17).getCount();
        else
            weightSlot17 = 0;
        float weightSlot18;
        if (!inventory.getItem(18).isEmpty()
                && weight.get(inventory.getItem(18).getItem()) != null){
            ItemStack stack = inventory.getItem(18);
            weightSlot18 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(18).isEmpty()
                && weight.get(inventory.getItem(18).getItem()) == null)
            weightSlot18 = defaultWeightForItems * inventory.getItem(18).getCount();
        else
            weightSlot18 = 0;
        float weightSlot19;
        if (!inventory.getItem(19).isEmpty()
                && weight.get(inventory.getItem(19).getItem()) != null){
            ItemStack stack = inventory.getItem(19);
            weightSlot19 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(19).isEmpty()
                && weight.get(inventory.getItem(19).getItem()) == null)
            weightSlot19 = defaultWeightForItems * inventory.getItem(19).getCount();
        else
            weightSlot19 = 0;
        float weightSlot20;
        if (!inventory.getItem(20).isEmpty()
                && weight.get(inventory.getItem(20).getItem()) != null){
            ItemStack stack = inventory.getItem(20);
            weightSlot20 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(20).isEmpty()
                && weight.get(inventory.getItem(20).getItem()) == null)
            weightSlot20 = defaultWeightForItems * inventory.getItem(20).getCount();
        else
            weightSlot20 = 0;
        float weightSlot21;
        if (!inventory.getItem(21).isEmpty()
                && weight.get(inventory.getItem(21).getItem()) != null){
            ItemStack stack = inventory.getItem(21);
            weightSlot21 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(21).isEmpty()
                && weight.get(inventory.getItem(21).getItem()) == null)
            weightSlot21 = defaultWeightForItems * inventory.getItem(21).getCount();
        else
            weightSlot21 = 0;
        float weightSlot22;
        if (!inventory.getItem(22).isEmpty()
                && weight.get(inventory.getItem(22).getItem()) != null){
            ItemStack stack = inventory.getItem(22);
            weightSlot22 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(22).isEmpty()
                && weight.get(inventory.getItem(22).getItem()) == null)
            weightSlot22 = defaultWeightForItems * inventory.getItem(22).getCount();
        else
            weightSlot22 = 0;
        float weightSlot23;
        if (!inventory.getItem(23).isEmpty()
                && weight.get(inventory.getItem(23).getItem()) != null){
            ItemStack stack = inventory.getItem(23);
            weightSlot23 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(23).isEmpty()
                && weight.get(inventory.getItem(23).getItem()) == null)
            weightSlot23 = defaultWeightForItems * inventory.getItem(23).getCount();
        else
            weightSlot23 = 0;
        float weightSlot24;
        if (!inventory.getItem(24).isEmpty()
                && weight.get(inventory.getItem(24).getItem()) != null){
            ItemStack stack = inventory.getItem(24);
            weightSlot24 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(24).isEmpty()
                && weight.get(inventory.getItem(24).getItem()) == null)
            weightSlot24 = defaultWeightForItems * inventory.getItem(24).getCount();
        else
            weightSlot24 = 0;
        float weightSlot25;
        if (!inventory.getItem(25).isEmpty()
                && weight.get(inventory.getItem(25).getItem()) != null){
            ItemStack stack = inventory.getItem(25);
            weightSlot25 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(25).isEmpty()
                && weight.get(inventory.getItem(25).getItem()) == null)
            weightSlot25 = defaultWeightForItems * inventory.getItem(25).getCount();
        else
            weightSlot25 = 0;
        float weightSlot26;
        if (!inventory.getItem(26).isEmpty()
                && weight.get(inventory.getItem(26).getItem()) != null){
            ItemStack stack = inventory.getItem(26);
            weightSlot26 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(26).isEmpty()
                && weight.get(inventory.getItem(26).getItem()) == null)
            weightSlot26 = defaultWeightForItems * inventory.getItem(26).getCount();
        else
            weightSlot26 = 0;
        float weightSlot27;
        if (!inventory.getItem(27).isEmpty()
                && weight.get(inventory.getItem(27).getItem()) != null){
            ItemStack stack = inventory.getItem(27);
            weightSlot27 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(27).isEmpty()
                && weight.get(inventory.getItem(27).getItem()) == null)
            weightSlot27 = defaultWeightForItems * inventory.getItem(27).getCount();
        else
            weightSlot27 = 0;
        float weightSlot28;
        if (!inventory.getItem(28).isEmpty()
                && weight.get(inventory.getItem(28).getItem()) != null){
            ItemStack stack = inventory.getItem(28);
            weightSlot28 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(28).isEmpty()
                && weight.get(inventory.getItem(28).getItem()) == null)
            weightSlot28 = defaultWeightForItems * inventory.getItem(28).getCount();
        else
            weightSlot28 = 0;
        float weightSlot29;
        if (!inventory.getItem(29).isEmpty()
                && weight.get(inventory.getItem(29).getItem()) != null){
            ItemStack stack = inventory.getItem(29);
            weightSlot29 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(29).isEmpty()
                && weight.get(inventory.getItem(29).getItem()) == null)
            weightSlot29 = defaultWeightForItems * inventory.getItem(29).getCount();
        else
            weightSlot29 = 0;
        float weightSlot30;
        if (!inventory.getItem(30).isEmpty()
                && weight.get(inventory.getItem(30).getItem()) != null){
            ItemStack stack = inventory.getItem(30);
            weightSlot30 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(30).isEmpty()
                && weight.get(inventory.getItem(30).getItem()) == null)
            weightSlot30 = defaultWeightForItems * inventory.getItem(30).getCount();
        else
            weightSlot30 = 0;
        float weightSlot31;
        if (!inventory.getItem(31).isEmpty()
                && weight.get(inventory.getItem(31).getItem()) != null){
            ItemStack stack = inventory.getItem(31);
            weightSlot31 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(31).isEmpty()
                && weight.get(inventory.getItem(31).getItem()) == null)
            weightSlot31 = defaultWeightForItems * inventory.getItem(31).getCount();
        else
            weightSlot31 = 0;
        float weightSlot32;
        if (!inventory.getItem(32).isEmpty()
                && weight.get(inventory.getItem(32).getItem()) != null){
            ItemStack stack = inventory.getItem(32);
            weightSlot32 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(32).isEmpty()
                && weight.get(inventory.getItem(32).getItem()) == null)
            weightSlot32 = defaultWeightForItems * inventory.getItem(32).getCount();
        else
            weightSlot32 = 0;
        float weightSlot33;
        if (!inventory.getItem(33).isEmpty()
                && weight.get(inventory.getItem(33).getItem()) != null){
            ItemStack stack = inventory.getItem(33);
            weightSlot33 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(33).isEmpty()
                && weight.get(inventory.getItem(33).getItem()) == null)
            weightSlot33 = defaultWeightForItems * inventory.getItem(33).getCount();
        else
            weightSlot33 = 0;
        float weightSlot34;
        if (!inventory.getItem(34).isEmpty()
                && weight.get(inventory.getItem(34).getItem()) != null){
            ItemStack stack = inventory.getItem(34);
            weightSlot34 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(34).isEmpty()
                && weight.get(inventory.getItem(34).getItem()) == null)
            weightSlot34 = defaultWeightForItems * inventory.getItem(34).getCount();
        else
            weightSlot34 = 0;
        float weightSlot35;
        if (!inventory.getItem(35).isEmpty()
                && weight.get(inventory.getItem(35).getItem()) != null){
            ItemStack stack = inventory.getItem(35);
            weightSlot35 = (weight.get(stack.getItem()) + weight.get(stack.getItem()) > 40 ? error : 0) * stack.getCount();
        } else if (!inventory.getItem(35).isEmpty()
                && weight.get(inventory.getItem(35).getItem()) == null)
            weightSlot35 = defaultWeightForItems * inventory.getItem(35).getCount();
        else
            weightSlot35 = 0;

        return weightSlotHand + weightSlot0 + weightSlot1 + weightSlot2 + weightSlot3
                + weightSlot4 + weightSlot5 + weightSlot6 + weightSlot7 + weightSlot8
                + weightSlot9 + weightSlot10 + weightSlot11 + weightSlot12 + weightSlot13
                + weightSlot14 + weightSlot15 + weightSlot16 + weightSlot17 + weightSlot18
                + weightSlot19 + weightSlot20 + weightSlot21 + weightSlot22 + weightSlot23
                + weightSlot24 + weightSlot25 + weightSlot26 + weightSlot27 + weightSlot28
                + weightSlot29 + weightSlot30 + weightSlot31 + weightSlot32 + weightSlot33
                + weightSlot34 + weightSlot35;
    }

    public float AllArmorWeight(){
        float weightSlotArmorHead;
        if (!player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.HEAD).getItem()) != null){
            ItemStack armorHead = player.getItemBySlot(EquipmentSlot.HEAD);
            weightSlotArmorHead = weight.get(armorHead.getItem()) + weight.get(armorHead.getItem()) > 40 ? error : 0;
        } else if (!player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.HEAD).getItem()) == null)
            weightSlotArmorHead = defaultWeightForArmorHead;
        else
            weightSlotArmorHead = 0;
        float weightSlotArmorChest;
        if (!player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.CHEST).getItem()) != null){
            ItemStack armorChest = player.getItemBySlot(EquipmentSlot.CHEST);
            weightSlotArmorChest = weight.get(armorChest.getItem()) + weight.get(armorChest.getItem()) > 40 ? error : 0;
        } else if (!player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.CHEST).getItem()) == null)
            weightSlotArmorChest = defaultWeightForArmorChest;
        else
            weightSlotArmorChest = 0;
        float weightSlotArmorLegs;
        if (!player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.LEGS).getItem()) != null){
            ItemStack armorLegs = player.getItemBySlot(EquipmentSlot.LEGS);
            weightSlotArmorLegs = weight.get(armorLegs.getItem()) + weight.get(armorLegs.getItem()) > 40 ? error : 0;
        } else if (!player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.LEGS).getItem()) == null)
            weightSlotArmorLegs = defaultWeightForArmorLegs;
        else
            weightSlotArmorLegs = 0;
        float weightSlotArmorFeet;
        if (!player.getItemBySlot(EquipmentSlot.FEET).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.FEET).getItem()) != null){
            ItemStack armorFeet = player.getItemBySlot(EquipmentSlot.FEET);
            weightSlotArmorFeet = weight.get(armorFeet.getItem()) + weight.get(armorFeet.getItem()) > 40 ? error : 0;
        } else if (!player.getItemBySlot(EquipmentSlot.FEET).isEmpty()
                && weight.get(player.getItemBySlot(EquipmentSlot.FEET).getItem()) == null)
            weightSlotArmorFeet = defaultWeightForArmorFeet;
        else
            weightSlotArmorFeet = 0;

        return weightSlotArmorHead + weightSlotArmorChest + weightSlotArmorLegs +   weightSlotArmorFeet;
    }

    public float AllPlayerWeight(){
        return AllInventoryWeight() + AllArmorWeight();
    }
}
