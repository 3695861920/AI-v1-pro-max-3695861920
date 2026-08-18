package com.eternalitems.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 永恒物品 —— 拥有"永恒光辉"的传奇物品基类。
 * <p>
 * 永恒光辉: 无论是否附魔, 物品始终像附魔物品一样闪烁。
 */
public class EternalItem extends Item {

    public EternalItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        // 永恒光辉: 物品永远闪烁着附魔的光芒
        return true;
    }
}
