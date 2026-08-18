package com.eternalitems.mixin;

import com.eternalitems.effect.ModEffects;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 时停效果的 AI 静止:
 * 当世界中有玩家处于「时停」状态时, 取消所有生物的 AI 更新
 * (寻路、目标、行为等), 玩家自身不受影响。
 */
@Mixin(Mob.class)
public abstract class MixinMob {

    @Inject(method = "serverAiStep", at = @At("HEAD"), cancellable = true)
    private void eternalitems$timeStopAi(CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        if (ModEffects.isTimeStopped(self.level())) {
            ci.cancel();
        }
    }
}
