package com.eternalitems.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

/**
 * 永恒之镐: 挖掘速度 14 (钻石为 8), 攻击 1 + 等级加成, 攻速 -2.8。
 * 永恒特性: 永不损坏, 永恒光辉。
 */
public class EternalPickaxeItem extends PickaxeItem {

    public EternalPickaxeItem(Tier tier, Item.Properties properties) {
        super(tier, 1.0F, -2.8F, properties);
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
