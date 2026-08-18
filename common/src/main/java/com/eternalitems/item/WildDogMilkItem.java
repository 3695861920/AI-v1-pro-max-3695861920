package com.eternalitems.item;

import com.eternalitems.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

/**
 * 野生狗奶: 喝下后返还玻璃瓶,
 * 补充饱食度 100 格 / 饱和度 1000 格,
 * 并附加 4 分钟「永恒」效果 (期间免疫一切伤害/击杀)。
 */
public class WildDogMilkItem extends Item {

    /** 永恒效果持续时长: 4 分钟 = 4800 tick */
    public static final int ETERNAL_DURATION_TICKS = 4 * 60 * 20;

    public WildDogMilkItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            // 1. 补充饱食度 100 格 / 饱和度 1000 格
            player.getFoodData().setFoodLevel(100);
            player.getFoodData().setSaturation(1000.0F);

            // 2. 附加 4 分钟永恒效果
            ModEffects.applyEternal(player, ETERNAL_DURATION_TICKS);

            // 3. 喝奶音效
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);

            // 4. 返还玻璃瓶 (优先入背包, 背包满则掉落)
            ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.getInventory().add(bottle)) {
                player.drop(bottle, false);
            }

            // 5. 消耗手中的奶
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
