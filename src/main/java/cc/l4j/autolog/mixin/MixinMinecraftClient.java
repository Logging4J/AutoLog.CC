package cc.l4j.autolog.mixin;

import cc.l4j.autolog.AutoLog;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void onTick(CallbackInfo info){
        AutoLog.getInstance.onTick();
    }

    @Inject(method = "close", at = @At("HEAD"), cancellable = true)
    public void close(CallbackInfo info){
        AutoLog.getInstance.onClose();
    }
}
