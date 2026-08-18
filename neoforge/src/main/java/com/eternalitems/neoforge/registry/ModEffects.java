package com.eternalitems.neoforge.registry;

import com.eternalitems.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * NeoForge 平台下的效果注册。
 */
public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Constants.MOD_ID);

    // ==================== 效果 ====================

    /** 永恒: 4 分钟内免疫一切伤害/击杀 */
    public static final DeferredHolder<MobEffect, MobEffect> ETERNAL =
            EFFECTS.register("eternal", com.eternalitems.effect.ModEffects::createEternal);

    /** 时停: 4 分钟内静止所有其他生物 */
    public static final DeferredHolder<MobEffect, MobEffect> TIME_STOP =
            EFFECTS.register("time_stop", com.eternalitems.effect.ModEffects::createTimeStop);
}
