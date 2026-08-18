package com.eternalitems.platform;

import com.eternalitems.Constants;
import com.eternalitems.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

/**
 * 服务定位器: 利用 Java 自带的 ServiceLoader 机制, 在 common 代码中访问
 * 由各加载器提供的平台实现。例如 Forge 提供 ForgePlatformHelper,
 * NeoForge 提供 NeoForgePlatformHelper。
 */
public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    /**
     * 加载当前环境下的服务实现。
     * 实现类需要通过在 META-INF/services 中放置以接口全限定名为文件名、
     * 内容为实现类全限定名的文本文件来声明。
     */
    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("无法加载服务: " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
