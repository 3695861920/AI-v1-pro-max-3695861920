package com.eternalitems.mixin;

import com.eternalitems.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 示例 mixin: 在标题界面初始化时打印信息 (Forge 平台)。
 */
@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void eternalitems$init(CallbackInfo info) {

        Constants.LOG.info("永恒物品 Forge mixin 生效! MC 版本: {}", Minecraft.getInstance().getVersionType());
    }
}
