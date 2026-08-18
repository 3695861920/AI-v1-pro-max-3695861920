package com.eternalitems.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * 时停效果: 时间凝固的灰烬。
 * <p>
 * 静止逻辑由 {@code MixinMob} / {@code MixinLivingEntity} 实现
 * (所有其他生物停止 AI 与移动, 玩家不受影响)。
 * 效果粒子由客户端渲染器按效果颜色自动生成。
 */
public class TimeStopEffect extends MobEffect {

    /** 时停灰 */
    public static final int TIME_STOP_COLOR = 0x9E9E9E;

    public TimeStopEffect() {
        super(MobEffectCategory.BENEFICIAL, TIME_STOP_COLOR, null);
    }
}
