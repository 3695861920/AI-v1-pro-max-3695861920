# 永恒物品 (Eternal Items)

让物品永恒不朽的 Minecraft 模组。当前包含拥有**永恒光辉**（始终闪烁附魔光泽）的传奇物品——**永恒钻石**。

基于 [MultiLoader-Template](https://github.com/jaredlll08/MultiLoader-Template) 构建，采用 **common + forge + neoforge** 三模块结构，一套代码同时支持 Forge 与 NeoForge。

## 支持版本

| 加载器 | 1.21.1（默认分支） | 1.20.1 |
| --- | --- | --- |
| Forge | ✅ `52.1.x` | ✅ `47.4.x` |
| NeoForge | ✅ `21.1.x` | ✅ `47.1.x` |

> ⚠️ **多版本支持策略**：Minecraft 模组社区的标准做法是**每个 Minecraft 版本一个 git 分支**（代码 API 与构建工具在不同版本间差异较大）。本仓库默认配置为 **1.21.1**；切换 1.20.1 请参考下文《版本切换指南》。

## 项目结构

```
├── build.gradle / settings.gradle / gradle.properties   # 构建配置（版本号集中管理）
├── gradlew / gradlew.bat / gradle/wrapper/              # Gradle 8.10 Wrapper
├── buildSrc/
│   └── src/main/groovy/
│       ├── multiloader-common.gradle                    # common 模块约定配置
│       └── multiloader-loader.gradle                    # 平台模块约定配置
├── common/                                              # 共享代码（仅原版 API）
│   └── src/main/java/com/eternalitems/
│       ├── Constants.java                               # 模组常量
│       ├── CommonClass.java                             # 通用初始化入口
│       ├── item/                                        # 物品类与定义
│       ├── mixin/                                       # 共享 mixin
│       └── platform/                                    # 平台服务抽象 (ServiceLoader)
├── forge/                                               # Forge 模块
│   └── src/main/java/com/eternalitems/forge/
│       ├── EternalItemsForge.java                       # Forge 入口
│       ├── registry/ModItems.java                       # Forge 物品注册
│       └── platform/ForgePlatformHelper.java
└── neoforge/                                            # NeoForge 模块
    └── src/main/java/com/eternalitems/neoforge/
        ├── EternalItemsNeoForge.java                    # NeoForge 入口
        ├── registry/ModItems.java                       # NeoForge 物品注册
        └── platform/NeoForgePlatformHelper.java
```

**代码分层原则**：
- `common` 只能使用**原版 API**（不能 import `net.minecraftforge` / `net.neoforged` 的类）
- 平台差异功能（注册表、事件、API 差异）通过 `Services.PLATFORM` 接口或平台模块自行实现
- 每个平台模块的入口类在启动时调用 `CommonClass.init()` 完成通用初始化

## 环境要求

- **JDK 21**（1.21.1）；切换 1.20.1 需 JDK 17（Gradle 可通过 foojay 自动下载）
- **Gradle 8.10**（已内置 wrapper，无需单独安装）
- 无需 IDE，命令行即可构建

## 快速开始

```bash
# 构建 Forge 版本
./gradlew :forge:build

# 构建 NeoForge 版本
./gradlew :neoforge:build

# 同时构建两个版本
./gradlew build

# 运行游戏（客户端）
./gradlew :forge:runClient
./gradlew :neoforge:runClient

# 运行服务端
./gradlew :forge:runServer
./gradlew :neoforge:runServer

# 数据生成器
./gradlew :forge:runData
./gradlew :neoforge:runData
```

构建产物位于 `forge/build/libs/` 与 `neoforge/build/libs/`，将 jar 放入 `.minecraft/mods/` 即可使用。

> 首次构建需要下载 Minecraft 反编译产物与依赖，耗时较长属正常现象。

## 添加新物品

以添加"永恒之剑"为例：

**1. common 模块**（`common/.../item/ModItems.java`）定义物品属性与工厂方法：

```java
// common/src/main/java/com/eternalitems/item/ModItems.java
/** 永恒之剑: 快速攻击、不惧火焰 */
public static final Item.Properties ETERNAL_SWORD_PROPERTIES = new Item.Properties()
        .fireResistant()
        .rarity(Rarity.EPIC);

public static Item createEternalSword() {
    return new EternalItem(ETERNAL_SWORD_PROPERTIES);
}
```

**2. 两个平台的注册类**中注册：

```java
// forge: forge/.../registry/ModItems.java
public static final RegistryObject<Item> ETERNAL_SWORD =
        ITEMS.register("eternal_sword", com.eternalitems.item.ModItems::createEternalSword);

// neoforge: neoforge/.../registry/ModItems.java
public static final DeferredHolder<Item, Item> ETERNAL_SWORD =
        ITEMS.register("eternal_sword", com.eternalitems.item.ModItems::createEternalSword);
```

**3. 加入创造模式物品栏**：在 `ModItems` 的 `displayItems` 中 `output.accept(ETERNAL_SWORD.get());`

**4. 添加语言文件与贴图**：
- `common/src/main/resources/assets/eternalitems/lang/zh_cn.json`：`"item.eternalitems.eternal_sword": "永恒之剑"`
- `common/src/main/resources/assets/eternalitems/lang/en_us.json`：英文名
- `common/src/main/resources/assets/eternalitems/textures/item/eternal_sword.png`：16×16 贴图

## 版本切换指南

> **推荐方式**：使用 git 分支管理版本。每个版本独立分支，互不影响。

```bash
git checkout -b 1.20.1
# ...修改下述配置与代码后提交...
```

### 从 1.21.1 切换到 1.20.1

#### 1. `gradle.properties`

```properties
java_version=17

minecraft_version=1.20.1
minecraft_version_range=[1.20.1, 1.21)
neo_form_version=            # 1.20.1 无 NeoForm, 留空即可 (common 改用 MCP)
parchment_minecraft=1.20.1
parchment_version=2023.09.03

forge_version=47.4.22        # Forge 1.20.1
forge_loader_version_range=[47,)

neoforge_version=1.20.1-47.1.106   # 注意: 1.20.1 需要带 MC 前缀的完整坐标
neoforge_loader_version_range=[47,)

moddev_gradle_version=2.0.141      # 保持 2.x, 使用 legacyforge 变体
```

#### 2. `build.gradle`（根）

1.20.1 的 Forge 与 NeoForge 都改用 ModDevGradle 的 **legacyforge** 变体，移除 ForgeGradle：

```groovy
plugins {
    id 'net.neoforged.moddev.legacyforge' version "${moddev_gradle_version}" apply false
}
```

#### 3. `common/build.gradle`

将 `net.neoforged.moddev` 换为 `net.neoforged.moddev.legacyforge`，`neoFormVersion` 换为 MCP：

```groovy
plugins {
    id 'multiloader-common'
    id 'net.neoforged.moddev.legacyforge'
}

legacyForge {
    mcpVersion = minecraft_version
    parchment {
        minecraftVersion = parchment_minecraft
        mappingsVersion = parchment_version
    }
}
```

#### 4. `forge/build.gradle`

换用 legacyforge 变体（runs / mods 语法与 neoforge 模块一致），并恢复 `reobfJar`：

```groovy
plugins {
    id 'multiloader-loader'
    id 'net.neoforged.moddev.legacyforge'
}

mixin {
    add(sourceSets.main, "${mod_id}.refmap.json")
    config("${mod_id}.mixins.json")
    config("${mod_id}.forge.mixins.json")
}

legacyForge {
    version = "${minecraft_version}-${forge_version}"   // -> 1.20.1-47.4.22
    runs {
        client { client() }
        server { server() }
        data {
            data()
            programArguments.addAll '--mod', project.mod_id, '--all', '--output', file('src/generated/resources/').getAbsolutePath(), '--existing', file('src/main/resources/').getAbsolutePath()
        }
    }
    mods {
        "${mod_id}" { sourceSet sourceSets.main }
    }
}

sourceSets.main.resources.srcDir 'src/generated/resources'

dependencies {
    compileOnly project(":common")
    annotationProcessor("org.spongepowered:mixin:0.8.5-SNAPSHOT:processor")
}

jar {
    finalizedBy('reobfJar')
    manifest.attributes([
            "MixinConfigs": "${mod_id}.mixins.json,${mod_id}.forge.mixins.json"
    ])
}
```

#### 5. `neoforge/build.gradle`

换用 legacyforge 变体（NeoForge 1.20.1 亦由 legacyforge 构建）：

```groovy
plugins {
    id 'multiloader-loader'
    id 'net.neoforged.moddev.legacyforge'
}

legacyForge {
    version = neoforge_version      // -> 1.20.1-47.1.106
    runs {
        client { client() }
        server { server() }
        data {
            data()
            programArguments.addAll '--mod', project.mod_id, '--all', '--output', file('src/generated/resources/').getAbsolutePath(), '--existing', file('src/main/resources/').getAbsolutePath()
        }
    }
    mods {
        "${mod_id}" { sourceSet sourceSets.main }
    }
}

sourceSets.main.resources.srcDir 'src/generated/resources'
```

#### 6. 代码与资源差异

| 文件 | 1.21.1 | 1.20.1 |
| --- | --- | --- |
| `common/.../pack.mcmeta` | `pack_format: 34` | `pack_format: 15` |
| `common/.../eternalitems.mixins.json` | `compatibilityLevel: "JAVA_21"` | `"JAVA_17"` |
| `forge/.../eternalitems.forge.mixins.json` | 同上 | 同上 |
| `neoforge/.../eternalitems.neoforge.mixins.json` | 同上 | 同上 |
| `forge/.../EternalItemsForge.java` | 构造参数注入 `IEventBus` | 改用 `FMLJavaModLoadingContext.get().getModEventBus()` |
| `neoforge/.../EternalItemsNeoForge.java` | 构造参数注入 `IEventBus` | 改用 `FMLJavaModLoadingContext.get().getModEventBus()` |

Forge 1.20.1 主类示例：

```java
public EternalItemsForge() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ModItems.ITEMS.register(modEventBus);
    ModItems.CREATIVE_TABS.register(modEventBus);
    modEventBus.addListener(this::commonSetup);
    CommonClass.init();
}
```

NeoForge 1.20.1 主类同理（`net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext`）。

#### 7. 验证

```bash
./gradlew :forge:build :neoforge:build
```

## 常见问题

**Q: 构建时下载依赖失败 / 网络超时？**
国内网络环境下可配置镜像，例如在 `settings.gradle` 的 `pluginManagement` 与各模块 `repositories` 中加入阿里云镜像：
```groovy
maven { url = 'https://maven.aliyun.com/repository/public' }
maven { url = 'https://maven.aliyun.com/repository/gradle-plugin' }
```

**Q: `./gradlew` 提示找不到 Java 17/21？**
Gradle 已配置 foojay 工具链插件，会自动下载缺失的 JDK；若网络受限，请手动安装对应版本 JDK。

**Q: 修改 `gradle.properties` 后需要做什么？**
若新增了属性，请同步添加到 `buildSrc/src/main/groovy/multiloader-common.gradle` 的 `expandProps` 映射中（否则资源文件中的 `${xxx}` 不会被替换）。

**Q: 如何添加平台特定功能（如事件监听）？**
在对应平台模块的入口类或独立类中注册事件，并通过 `Services` 接口暴露给 common 使用。

## License

本项目基于 [MIT License](LICENSE) 开源。
