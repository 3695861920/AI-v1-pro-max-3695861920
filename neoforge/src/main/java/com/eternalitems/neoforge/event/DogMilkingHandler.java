package com.eternalitems.neoforge.event;

import com.eternalitems.Constants;
import com.eternalitems.item.WildDogMilkHelper;
import com.eternalitems.neoforge.registry.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * 挤狗奶事件: 手持玻璃瓶右键狼 → 消耗玻璃瓶 → 获得野生狗奶。
 */
@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class DogMilkingHandler {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        // 只处理主手, 且目标必须是狼
        if (!(event.getTarget() instanceof Wolf wolf) || event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }

        Player player = event.getEntity();
        Level level = event.getLevel();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);

        // 必须手持玻璃瓶
        if (!stack.is(Items.GLASS_BOTTLE)) {
            return;
        }

        // 阻止狼的默认交互 (驯服/喂食等)
        event.setCanceled(true);

        // 执行挤奶逻辑 (服务端)
        WildDogMilkHelper.milk(player, level, wolf, stack,
                ModItems.WILD_DOG_MILK.get());
    }
}
