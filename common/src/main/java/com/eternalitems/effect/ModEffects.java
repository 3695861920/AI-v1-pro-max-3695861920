package com.eternalitems.effect;

import com.eternalitems.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * 模组效果定义 (common 层, 仅原版 API)。
 * <p>
 * 具体的注册由各加载器模块 (forge / neoforge) 完成,
 * 这里只提供效果工厂方法与通用工具方法。
 */
public class ModEffects {

    // ==================== 效果注册键 ====================

    /** 永恒: 4 分钟内免疫一切伤害/击杀 */
    public static final ResourceKey<MobEffect> ETERNAL = ResourceKey.create(
            Registries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "eternal"));

    /** 时停: 4 分钟内静止所有其他生物 */
    public static final ResourceKey<MobEffect> TIME_STOP = ResourceKey.create(
            Registries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "time_stop"));

    // ==================== 效果工厂方法 ====================

    /** 创建永恒效果 */
    public static MobEffect createEternal() {
        return new EternalEffect();
    }

    /** 创建时停效果 */
    public static MobEffect createTimeStop() {
        return new TimeStopEffect();
    }

    // ==================== 通用工具方法 ====================

    /** 给实体附加效果 (持续 durationTicks 刻) */
    private static void apply(LivingEntity entity, ResourceKey<MobEffect> effectKey, int durationTicks) {
        Holder<MobEffect> holder = entity.level().registryAccess()
                .registryOrThrow(Registries.MOB_EFFECT)
                .getHolder(effectKey)
                .orElse(null);
        if (holder != null) {
            entity.addEffect(new MobEffectInstance(holder, durationTicks, 0, false, false, true));
        }
    }

    /** 判断实体是否拥有指定效果 */
    private static boolean has(LivingEntity entity, ResourceKey<MobEffect> effectKey) {
        Holder<MobEffect> holder = entity.level().registryAccess()
                .registryOrThrow(Registries.MOB_EFFECT)
                .getHolder(effectKey)
                .orElse(null);
        return holder != null && entity.hasEffect(holder);
    }

    /** 给实体附加永恒效果 */
    public static void applyEternal(LivingEntity entity, int durationTicks) {
        apply(entity, ETERNAL, durationTicks);
    }

    /** 给实体附加时停效果 */
    public static void applyTimeStop(LivingEntity entity, int durationTicks) {
        apply(entity, TIME_STOP, durationTicks);
    }

    /** 判断实体当前是否拥有永恒效果 (供 mixin 调用) */
    public static boolean hasEternal(LivingEntity entity) {
        return has(entity, ETERNAL);
    }

    /** 判断实体当前是否拥有时停效果 */
    public static boolean hasTimeStop(LivingEntity entity) {
        return has(entity, TIME_STOP);
    }

    /** 判断世界中是否有玩家处于时停状态 (供 mixin 调用) */
    public static boolean isTimeStopped(Level level) {
        for (Player player : level.players()) {
            if (hasTimeStop(player)) {
                return true;
            }
        }
        return false;
    }
}
