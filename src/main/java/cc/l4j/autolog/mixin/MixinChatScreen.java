package cc.l4j.autolog.mixin;

import cc.l4j.autolog.AutoLog;
import cc.l4j.autolog.command.Command;
import cc.l4j.autolog.command.CommandManager;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(ChatScreen.class)
public abstract class MixinChatScreen {

    @Shadow public abstract boolean sendMessage(String chatText, boolean addToHistory);

    @Redirect(method = "sendMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendChatMessage(Ljava/lang/String;Lnet/minecraft/text/Text;)V"))
    public void sendChatMessage(ClientPlayerEntity instance, String message, Text preview){
        if(message.startsWith(Command.PREFIX)){
            String trimmedMessage = message.substring(Command.PREFIX.length());
            if(!(trimmedMessage.isEmpty() || trimmedMessage.isBlank())){
                String[] messageSplit = trimmedMessage.split(" +");
                String command = messageSplit[0];
                String[] args = Arrays.copyOfRange(messageSplit, 1, messageSplit.length);
                Command run = CommandManager.getByAlias(command);
                if(run == null){
                    Command.sendMessage("Command Not Found");
                }else{
                    run.exc(args);
                }
            }
            return;
        }else{
            instance.sendChatMessage(message, preview);
        }
    }
}
