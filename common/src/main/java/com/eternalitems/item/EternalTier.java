package com.eternalitems.item;

import net.minecraft.tags.BlocksTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

/**
 * 永恒工具等级。
 * <p>
 * 比钻石更强的性能: 挖掘速度 14, 攻击加成 +4, 附魔能力 22。
 * 注意: 本类面向 1.21.1 (Tier 接口包含 getIncorrectBlocksForDrops);
 * 移植 1.20.1 时该接口方法为 getLevel(), 需要相应调整。
 */
public enum EternalTier implements Tier {

    /** 永恒 (史诗级) */
    ETERNAL(4096, 14.0F, 4.0F, 22,
            Ingredient.of(Items.DIAMOND),
            BlocksTags.INCORRECT_FOR_DIAMOND_TOOL);

    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;
    private final TagKey<Block> incorrectBlocksForDrops;

    EternalTier(int uses, float speed, float attackDamageBonus, int enchantmentValue,
                Ingredient repairIngredient, TagKey<Block> incorrectBlocksForDrops) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
