package cc.l4j.autolog.mixin;

import cc.l4j.autolog.AutoLog;
import cc.l4j.autolog.hack.HackManager;
import cc.l4j.autolog.hack.impl.BowBomb;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager {

    @Inject(method = "stopUsingItem", at = @At("HEAD"))
    public void stopUsingItem(PlayerEntity player, CallbackInfo info) {
        if(HackManager.getInstance.getHack("BowBomb").isEnabled()){
            if(player.getInventory().getMainHandStack().getItem().equals(Items.BOW)){
                BowBomb.getInstance.onBowRelease();
            }
        }
    }
}
