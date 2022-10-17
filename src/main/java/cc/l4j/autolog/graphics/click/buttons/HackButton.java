package cc.l4j.autolog.graphics.click.buttons;

import cc.l4j.autolog.graphics.click.Frame;
import cc.l4j.autolog.hack.Hack;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class HackButton {

    public Hack hack;
    public Frame frame;
    public int offset;

    public HackButton(Hack hack, Frame frame, int offset) {
        this.hack = hack;
        this.frame = frame;
        this.offset = offset;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrixStack, frame.x, frame.y + offset, frame.x + frame.width, frame.y + offset - 15+ frame.height, new Color(0,0,0,160).getRGB());
        if(isHovered(mouseX,mouseY)){
            DrawableHelper.fill(matrixStack, frame.x, frame.y + offset, frame.x + frame.width, frame.y + offset - 15 + frame.height, new Color(0,0,0,160).getRGB());
        }
        frame.mc.textRenderer.drawWithShadow(matrixStack, hack.getName(), frame.x +2, frame.y +offset+2, -1);

    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY)){
            if(button == 0){
                hack.toggle();;
            }else{}
        }
    }

    public boolean isHovered(double mouseX, double mouseY){
        return  mouseX > frame.x && mouseX < frame.x + frame.width && mouseY > frame.y + offset && mouseY < frame.y + offset + frame.height;
    }

}
