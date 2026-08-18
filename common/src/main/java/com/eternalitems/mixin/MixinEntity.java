package com.eternalitems.mixin;

import com.eternalitems.effect.ModEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 永恒效果的击杀防护:
 * <ul>
 *   <li>拦截 {@link Entity#kill()}: 免疫 {@code /kill} 命令</li>
 *   <li>拦截 {@link Entity#remove(Entity.RemovalReason)} (KILLED): 免疫其他模组的强制抹除</li>
 * </ul>
 */
@Mixin(Entity.class)
public abstract class MixinEntity {

    /** 永恒庇护: 免疫 /kill 命令 */
    @Inject(method = "kill", at = @At("HEAD"), cancellable = true)
    private void eternalitems$preventKillCommand(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (self instanceof LivingEntity living && ModEffects.hasEternal(living)) {
            ci.cancel();
        }
    }

    /** 永恒庇护: 免疫以 KILLED 原因被强制移除 (其他模组的击杀/抹除) */
    @Inject(method = "remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V",
            at = @At("HEAD"), cancellable = true)
    private void eternalitems$preventForcedRemoval(Entity.RemovalReason reason, CallbackInfo ci) {
        if (reason != Entity.RemovalReason.KILLED) {
            return;
        }
        Entity self = (Entity) (Object) this;
        if (self instanceof LivingEntity living && ModEffects.hasEternal(living)) {
            ci.cancel();
        }
    }
}
