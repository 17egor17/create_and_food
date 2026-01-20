package net.egorplaytv.caf.mixin;

import net.egorplaytv.caf.damage.CAFDamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(at = @At("HEAD"), method = "getHurtSound", cancellable = true)
    protected void getHurtSound(DamageSource pDamageSource, CallbackInfoReturnable<SoundEvent> info) {
        info.cancel();

        if (pDamageSource == DamageSource.ON_FIRE) {
            info.setReturnValue(SoundEvents.PLAYER_HURT_ON_FIRE);
        } else if (pDamageSource == DamageSource.DROWN) {
            info.setReturnValue(SoundEvents.PLAYER_HURT_DROWN);
        } else if (pDamageSource == DamageSource.SWEET_BERRY_BUSH || pDamageSource == CAFDamageSource.RASPBERRY_BUSH) {
            info.setReturnValue(SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH);
        } else {
            info.setReturnValue(pDamageSource == DamageSource.FREEZE ? SoundEvents.PLAYER_HURT_FREEZE : SoundEvents.PLAYER_HURT);
        }
    }
}
