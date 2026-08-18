package com.eternalitems.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

/**
 * 模组物品定义。
 * <p>
 * common 模块中只能定义物品的属性和工厂方法(仅使用原版 API),
 * 具体的注册工作由各加载器模块(forge / neoforge)中的 DeferredRegister 完成。
 */
public class ModItems {

    // ==================== 物品属性 ====================

    /** 永恒钻石: 不会因火焰/岩浆销毁, 史诗稀有度 */
    public static final Item.Properties ETERNAL_DIAMOND_PROPERTIES = new Item.Properties()
            .fireResistant()      // 永恒不灭: 不会在火焰/岩浆中被烧毁
            .rarity(Rarity.EPIC); // 史诗稀有度(紫色名称)

    /** 野生狗奶: 食物标签, 喝下后返还玻璃瓶 */
    public static final Item.Properties WILD_DOG_MILK_PROPERTIES = new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(100)            // 饱食度 100 格
                    .saturationModifier(1000.0F) // 饱和度 1000 格
                    .alwaysEdible()            // 饱食度满时也可饮用
                    .build());

    /** 春秋肠: 食物标签, 猪排+纸合成, 吃下后获得时停效果 */
    public static final Item.Properties SPRING_AUTUMN_SAUSAGE_PROPERTIES = new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(100)            // 饱食度 100 格
                    .saturationModifier(1000.0F) // 饱和度 1000 格
                    .alwaysEdible()            // 饱食度满时也可食用
                    .build());

    // ==================== 物品工厂方法 ====================

    /** 创建永恒钻石 */
    public static Item createEternalDiamond() {
        return new EternalItem(ETERNAL_DIAMOND_PROPERTIES);
    }

    /** 创建野生狗奶 */
    public static Item createWildDogMilk() {
        return new WildDogMilkItem(WILD_DOG_MILK_PROPERTIES);
    }

    /** 创建春秋肠 */
    public static Item createSpringAutumnSausage() {
        return new SpringAutumnSausageItem(SPRING_AUTUMN_SAUSAGE_PROPERTIES);
    }
}
