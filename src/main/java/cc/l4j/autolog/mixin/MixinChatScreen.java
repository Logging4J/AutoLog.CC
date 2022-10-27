package cc.l4j.autolog.mixin;

import cc.l4j.autolog.AutoLog;
import cc.l4j.autolog.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ChatScreen.class})
public class MixinChatScreen {

    protected MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(method = "sendMessage(Ljava/lang/String;Z)Z", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String message, boolean addToHistory, CallbackInfoReturnable<Boolean> cir){
        if(message.startsWith(Command.PREFIX)) {
            AutoLog.getInstance.onChatCommand(message);
            cir.setReturnValue(true);
        }
    }
}
