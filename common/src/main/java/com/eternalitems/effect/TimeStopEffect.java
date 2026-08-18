package com.eternalitems.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * 时停效果: 时间凝固的灰烬。
 * <p>
 * 静止逻辑由 {@code MixinMob} / {@code MixinLivingEntity} 实现
 * (所有其他生物停止 AI 与移动, 玩家不受影响)。
 * 本类负责效果粒子表现: 灰色烟雾缓缓飘落。
 */
public class TimeStopEffect extends MobEffect {

    /** 时停灰 */
    public static final int TIME_STOP_COLOR = 0x9E9E9E;

    public TimeStopEffect() {
        super(MobEffectCategory.BENEFICIAL, TIME_STOP_COLOR, null);
    }

    @Override
    public boolean shouldSpawnParticles() {
        return true; // 启用粒子
    }

    @Override
    public void spawnParticles(LivingEntity livingEntity, int amplifier,
                               RandomSource random, double x, double y, double z) {
        double angle = random.nextDouble() * Math.PI * 2.0;
        double radius = livingEntity.getBbWidth() * 0.5 + 0.1;
        livingEntity.level().addParticle(
                ParticleTypes.SMOKE,
                x + Math.cos(angle) * radius,
                y + random.nextDouble() * livingEntity.getBbHeight(),
                z + Math.sin(angle) * radius,
                0.0, -0.02, 0.0);
    }
}
