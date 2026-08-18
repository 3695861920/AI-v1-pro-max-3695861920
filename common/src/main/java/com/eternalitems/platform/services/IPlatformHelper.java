package com.eternalitems.platform.services;

public interface IPlatformHelper {

    /**
     * 获取当前加载器的名称。
     *
     * @return 当前加载器名称, 如 "Forge" / "NeoForge"
     */
    String getPlatformName();

    /**
     * 检查指定 id 的模组是否已加载。
     *
     * @param modId 要检查的模组 id
     * @return 已加载返回 true, 否则返回 false
     */
    boolean isModLoaded(String modId);

    /**
     * 检查游戏是否运行在开发环境中。
     *
     * @return 开发环境返回 true, 否则返回 false
     */
    boolean isDevelopmentEnvironment();

    /**
     * 获取环境类型名称。
     *
     * @return 环境类型名称
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }
}
