package com.eternalitems.neoforge;

import com.eternalitems.CommonClass;
import com.eternalitems.Constants;
import com.eternalitems.neoforge.registry.ModEffects;
import com.eternalitems.neoforge.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * NeoForge 加载器入口。
 */
@Mod(Constants.MOD_ID)
public class EternalItemsNeoForge {

    public EternalItemsNeoForge(IEventBus modEventBus) {

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

        Constants.LOG.info("NeoForge common setup 完成!");
    }
}
