package cc.l4j.autolog.mixin;

import cc.l4j.autolog.graphics.hud.HUD;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info){
        HUD.draw(matrixStack, tickDelta);
    }
}
