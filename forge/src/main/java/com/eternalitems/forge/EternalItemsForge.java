package com.eternalitems.forge;

import com.eternalitems.CommonClass;
import com.eternalitems.Constants;
import com.eternalitems.forge.registry.ModEffects;
import com.eternalitems.forge.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Forge 加载器入口。
 */
@Mod(Constants.MOD_ID)
public class EternalItemsForge {

    public EternalItemsForge(IEventBus modEventBus) {

        // 注册物品与创造模式物品栏
        ModItems.ITEMS.register(modEventBus);
        ModItems.CREATIVE_TABS.register(modEventBus);

        // 注册效果
        ModEffects.EFFECTS.register(modEventBus);

        // 调用 common 模块的初始化
        modEventBus.addListener(this::commonSetup);
        CommonClass.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        Constants.LOG.info("Forge common setup 完成!");
    }
}
