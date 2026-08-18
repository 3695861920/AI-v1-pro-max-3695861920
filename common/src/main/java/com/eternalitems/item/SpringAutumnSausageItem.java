package com.eternalitems.item;

import com.eternalitems.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 春秋肠: 猪排与纸合成的时间美食。
 * 吃下后补充饱食度 100 格 / 饱和度 1000 格 (由 FoodProperties 处理),
 * 并附加 4 分钟「时停」效果 (期间静止所有其他生物, 玩家不受影响)。
 */
public class SpringAutumnSausageItem extends Item {

    /** 时停效果持续时长: 4 分钟 = 4800 tick */
    public static final int TIME_STOP_DURATION_TICKS = 4 * 60 * 20;

    public SpringAutumnSausageItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        // 吃下后附加时停效果 (服务端)
        if (!level.isClientSide() && entity instanceof Player player) {
            ModEffects.applyTimeStop(player, TIME_STOP_DURATION_TICKS);
        }
        return result;
    }
}
