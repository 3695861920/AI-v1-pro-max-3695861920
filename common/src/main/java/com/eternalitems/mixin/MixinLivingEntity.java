package com.eternalitems.mixin;

import com.eternalitems.effect.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 永恒与时停效果的实体级实现:
 * <ul>
 *   <li>永恒: 拦截 {@link LivingEntity#actuallyHurt} 免疫一切伤害 (含虚空、岩浆、摔落等)</li>
 *   <li>永恒: 拦截 {@link LivingEntity#die} 免疫死亡 (防穿透伤害的二次保障)</li>
 *   <li>时停: 拦截 {@link LivingEntity#travel} 静止非玩家实体的移动 (重力/惯性)</li>
 * </ul>
 */
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    /** 永恒庇护: 免疫一切伤害 */
    @Inject(method = "actuallyHurt", at = @At("HEAD"), cancellable = true)
    private void eternalitems$preventDamage(DamageSource source, float amount, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (ModEffects.hasEternal(self)) {
            ci.cancel();
        }
    }

    /** 永恒庇护: 阻止死亡 (兜底, 防止绕过伤害结算的击杀) */
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void eternalitems$preventDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (ModEffects.hasEternal(self)) {
            ci.cancel();
        }
    }

    /** 时停: 静止所有非玩家实体的移动 */
    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void eternalitems$timeStopMovement(Vec3 travelVector, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player) {
            return; // 玩家不受时停影响
        }
        if (ModEffects.isTimeStopped(self.level())) {
            ci.cancel();
        }
    }
}
