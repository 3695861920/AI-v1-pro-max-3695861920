package com.eternalitems.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

/**
 * 永恒之剑: 攻击伤害 3 + 等级加成, 攻速 -2.4。
 * 永恒特性: 永不损坏 (isDamageable 恒为 false), 永恒光辉 (isFoil 恒为 true)。
 */
public class EternalSwordItem extends SwordItem {

    public EternalSwordItem(Tier tier, Item.Properties properties) {
        super(tier, 3.0F, -2.4F, properties);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false; // 永不损坏
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // 永恒光辉
    }
}
