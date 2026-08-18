package com.eternalitems;

import com.eternalitems.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

/**
 * 通用入口类, 位于 common 模块, 由 Forge / NeoForge 的加载器入口共同调用。
 * <p>
 * 注意: common 模块中的代码只能使用原版 API, 不能直接使用 Forge / NeoForge
 * 特有的类(如事件总线、DeferredRegister 等)。如需平台差异功能, 请通过
 * {@link com.eternalitems.platform.Services} 提供的服务接口访问。
 */
public class CommonClass {

    /**
     * 模组初始化入口, 由各平台的主类在加载时调用。
     */
    public static void init() {

        Constants.LOG.info("{} 模组初始化完成! 当前平台: {} / 环境: {}",
                Constants.MOD_NAME,
                Services.PLATFORM.getPlatformName(),
                Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("钻石的注册 ID 为 {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("{} 模组加载成功!", Constants.MOD_NAME);
        }
    }
}
