package net.egorplaytv.create_and_food.screen.procedure;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class closeInventory {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player _player)
            _player.closeContainer();
    }
}
