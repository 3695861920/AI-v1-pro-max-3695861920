package com.eternalitems.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * 永恒效果: 持有者获得神级庇护。
 * <p>
 * 无敌逻辑由 {@code MixinLivingEntity} / {@code MixinEntity} 实现
 * (免疫伤害、免疫死亡、免疫 /kill 与强制抹除)。
 * 效果粒子由客户端渲染器按效果颜色自动生成。
 */
public class EternalEffect extends MobEffect {

    /** 永恒青 (电光蓝) */
    public static final int ETERNAL_COLOR = 0x00E5FF;

    public EternalEffect() {
        super(MobEffectCategory.BENEFICIAL, ETERNAL_COLOR, null);
    }
}
