package com.eternalitems.item;

import com.eternalitems.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 永恒盔甲材料。
 * <p>
 * 防御 4/8/7/4 (总计 23), 韧性 4, 击退抗性 0.2 —— 超越下界合金的史诗护甲。
 * 注意: 本类面向 1.21.1 (ArmorMaterial 使用 Holder&lt;SoundEvent&gt; 与 ArmorType);
 * 移植 1.20.1 时需要调整构造参数 (SoundEvent 与 ArmorItem.Type)。
 */
public final class EternalArmorMaterials {

    private EternalArmorMaterials() {
    }

    /** 永恒护甲材料 */
    public static final ArmorMaterial ETERNAL = new ArmorMaterial(
            createDefense(4, 8, 7, 4),
            22,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(Items.DIAMOND),
            List.of(new ArmorMaterial.Layer(
                    ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "eternal"), false)),
            4.0F,
            0.2F
    );

    /** 构建按部位划分的防御值表 */
    private static Map<ArmorType, Integer> createDefense(int helmet, int chestplate, int leggings, int boots) {
        EnumMap<ArmorType, Integer> defense = new EnumMap<>(ArmorType.class);
        defense.put(ArmorType.HELMET, helmet);
        defense.put(ArmorType.CHESTPLATE, chestplate);
        defense.put(ArmorType.LEGGINGS, leggings);
        defense.put(ArmorType.BOOTS, boots);
        return defense;
    }
}
