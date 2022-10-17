package cc.l4j.autolog.graphics.click;

import cc.l4j.autolog.graphics.click.buttons.HackButton;
import cc.l4j.autolog.hack.Hack;
import cc.l4j.autolog.hack.HackManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    public MinecraftClient mc = MinecraftClient.getInstance();
    private List<HackButton> buttons;
    public int x, y, width, height, dragX, dragY;
    public Hack.Category category;
    public boolean dragging;

    public Frame( Hack.Category category, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;
        dragging = false;
        buttons = new ArrayList<>();
        int offset = height;
        for(Hack hack : HackManager.getInstance.getHacksInCategory(category)){
            buttons.add(new HackButton(hack, this, offset));
            offset += height;
        }
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta){
        DrawableHelper.fill(matrixStack ,x, y, x+width, y+height - 15, new Color(0, 175, 239, 255).getRGB());
        mc.textRenderer.drawWithShadow(matrixStack, category.name, x+2, y+2, -1);
        for(HackButton button : buttons){
            button.render(matrixStack, mouseX, mouseY, delta);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button){
        if(isHovered(mouseX, mouseY) && button == 0){
            dragging = true;
            dragX = (int) (mouseX - x);
            dragY = (int) (mouseY - y);
        }
        for(HackButton mb : buttons){
            mb.mouseClicked(mouseX,mouseY,button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button){
        if(button == 0 && dragging == true){
            dragging = false;
        }
    }

    public boolean isHovered(double mouseX, double mouseY){
        return  mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void updatePos(double mouseX, double mouseY){
        if(dragging){
            x = (int) (mouseX - dragX);
            y = (int) (mouseY - dragY);
        }
    }

}
