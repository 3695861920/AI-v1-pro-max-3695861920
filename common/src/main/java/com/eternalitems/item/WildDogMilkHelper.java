package com.eternalitems.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 挤狗奶通用逻辑 (由各平台的事件监听器调用)。
 * <p>
 * 用法: 玩家手持玻璃瓶右键狼 → 消耗玻璃瓶 → 获得野生狗奶。
 */
public final class WildDogMilkHelper {

    private WildDogMilkHelper() {
    }

    /**
     * 用玻璃瓶挤狗奶。
     *
     * @param player      挤奶玩家
     * @param level       所在世界
     * @param wolf        被挤的狼
     * @param bottleStack 玩家手中的玻璃瓶 (会被消耗 1 个)
     * @param milkItem    野生狗奶物品 (由平台注册后传入)
     */
    public static void milk(Player player, Level level, Wolf wolf,
                            ItemStack bottleStack, Item milkItem) {
        if (level.isClientSide()) {
            return;
        }

        // 1. 消耗玻璃瓶
        bottleStack.shrink(1);

        // 2. 给予野生狗奶 (背包满则掉落)
        ItemStack milk = new ItemStack(milkItem);
        if (!player.getInventory().add(milk)) {
            player.drop(milk, false);
        }

        // 3. 挤奶音效 + 狼的委屈叫声
        level.playSound(null, wolf.blockPosition(), SoundEvents.COW_MILK,
                SoundSource.PLAYERS, 1.0F, 1.0F);
        wolf.playSound(SoundEvents.WOLF_WHINE, 0.8F, 1.2F);

        // 4. 爱心粒子
        for (int i = 0; i < 6; i++) {
            level.addParticle(ParticleTypes.HEART,
                    wolf.getX() + level.random.nextDouble() * 0.6 - 0.3,
                    wolf.getY() + wolf.getBbHeight() * level.random.nextDouble(),
                    wolf.getZ() + level.random.nextDouble() * 0.6 - 0.3,
                    0.0, 0.1, 0.0);
        }
    }
}
