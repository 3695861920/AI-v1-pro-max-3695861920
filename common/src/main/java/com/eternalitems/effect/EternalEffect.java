package com.eternalitems.effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.ParticleTypes;

/**
 * 永恒效果: 持有者获得神级庇护。
 * <p>
 * 无敌逻辑由 {@code MixinLivingEntity} / {@code MixinEntity} 实现
 * (免疫伤害、免疫死亡、免疫 /kill 与强制抹除)。
 * 本类负责效果粒子表现: 持续飘散青色光辉粒子。
 */
public class EternalEffect extends MobEffect {

    /** 永恒青 (电光蓝) */
    public static final int ETERNAL_COLOR = 0x00E5FF;

    public EternalEffect() {
        super(MobEffectCategory.BENEFICIAL, ETERNAL_COLOR, null);
    }

    @Override
    public boolean shouldSpawnParticles() {
        return true; // 启用粒子
    }

    @Override
    public void spawnParticles(LivingEntity livingEntity, int amplifier,
                               RandomSource random, double x, double y, double z) {
        double angle = random.nextDouble() * Math.PI * 2.0;
        double radius = livingEntity.getBbWidth() * 0.6 + 0.2;
        double speed = 0.02 + random.nextDouble() * 0.04;
        livingEntity.level().addParticle(
                ParticleTypes.END_ROD,
                x + Math.cos(angle) * radius,
                y + random.nextDouble() * (livingEntity.getBbHeight() + 0.5),
                z + Math.sin(angle) * radius,
                Math.cos(angle) * speed, 0.01, Math.sin(angle) * speed);
    }
}
