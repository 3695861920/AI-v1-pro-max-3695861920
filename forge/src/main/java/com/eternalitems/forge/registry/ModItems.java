package com.eternalitems.forge.registry;

import com.eternalitems.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Forge 平台下的物品与创造模式物品栏注册。
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    // ==================== 物品 ====================

    /** 永恒钻石 */
    public static final RegistryObject<Item> ETERNAL_DIAMOND =
            ITEMS.register("eternal_diamond", com.eternalitems.item.ModItems::createEternalDiamond);

    /** 野生狗奶 */
    public static final RegistryObject<Item> WILD_DOG_MILK =
            ITEMS.register("wild_dog_milk", com.eternalitems.item.ModItems::createWildDogMilk);

    /** 春秋肠 */
    public static final RegistryObject<Item> SPRING_AUTUMN_SAUSAGE =
            ITEMS.register("spring_autumn_sausage", com.eternalitems.item.ModItems::createSpringAutumnSausage);

    // ==================== 创造模式物品栏 ====================

    /** 永恒物品 创造模式物品栏 */
    public static final RegistryObject<CreativeModeTab> ETERNAL_TAB = CREATIVE_TABS.register("eternal_items_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.eternalitems"))
                    .icon(() -> new ItemStack(ETERNAL_DIAMOND.get()))
                    .displayItems((params, output) -> {
                        output.accept(ETERNAL_DIAMOND.get());
                        output.accept(WILD_DOG_MILK.get());
                        output.accept(SPRING_AUTUMN_SAUSAGE.get());
                    })
                    .build());
}
