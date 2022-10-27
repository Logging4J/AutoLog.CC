package cc.l4j.autolog.mixin;

import cc.l4j.autolog.AutoLog;
import cc.l4j.autolog.hack.HackManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.charset.StandardCharsets;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo callback){
        if(HackManager.getInstance.getHack("ClientSpoofer").isEnabled()) {
            if(packet instanceof CustomPayloadC2SPacket) {
                if (((CustomPayloadC2SPacket) packet).getData().toString(StandardCharsets.UTF_8).toLowerCase().contains("fabric")) {return;}
            }
        }
        AutoLog.getInstance.onPacketSend(packet);
    }
}
