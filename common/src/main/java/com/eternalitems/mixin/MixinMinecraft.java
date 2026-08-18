package com.eternalitems.mixin;

import com.eternalitems.Constants;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 示例 mixin: 在客户端 Minecraft 初始化时打印信息。
 * <p>
 * 此 mixin 位于 common 模块, 会同时被 Forge 与 NeoForge 加载。
 * 由于引用的是客户端类, 需要在 mixins.json 的 "client" 数组中声明。
 */
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(at = @At("TAIL"), method = "<init>")
    private void eternalitems$init(CallbackInfo info) {

        Constants.LOG.info("永恒物品 common mixin 生效! MC 版本: {}", Minecraft.getInstance().getVersionType());
    }
}
