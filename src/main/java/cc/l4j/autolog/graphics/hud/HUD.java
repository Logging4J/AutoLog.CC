package cc.l4j.autolog.graphics.hud;

import cc.l4j.autolog.AutoLog;
import cc.l4j.autolog.hack.Hack;
import cc.l4j.autolog.hack.HackManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class HUD {
    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static void draw(MatrixStack matrixStack, float tickDelta){
        int index = 0;
        int sWidth = mc.getWindow().getWidth();
        int sHeight = mc.getWindow().getHeight();
        //WaterMark
        mc.textRenderer.drawWithShadow(matrixStack, "["+AutoLog.MOD_NAME+"]" + " ["+ mc.fpsDebugString+"]", 2, 2, -1);

        for(Hack hack : HackManager.getInstance.getHacks()){
            if(hack.isEnabled()) {
                mc.textRenderer.drawWithShadow(matrixStack, "-"+hack.getDisplayName(), 2 , 100 + index * mc.textRenderer.fontHeight, -1);
                index++;
            }
        }
    }
}
