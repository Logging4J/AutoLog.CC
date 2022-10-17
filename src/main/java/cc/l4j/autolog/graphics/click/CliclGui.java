package cc.l4j.autolog.graphics.click;

import cc.l4j.autolog.hack.Hack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CliclGui extends Screen{

    private List<Frame> frames;
    private CliclGui() {
        super(Text.literal("ClickGui"));
        frames = new ArrayList<>();
        int offset = 20;
        for(Hack.Category category: Hack.Category.values()){
            frames.add(new Frame(category,offset,30,100,30));
            offset += 120;
        }
    }

    public static CliclGui getInstance = new CliclGui();

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for(Frame frame : frames){
            frame.render(matrices, mouseX, mouseY, delta);
            frame.updatePos(mouseX, mouseY);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(Frame frame: frames){
            frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for(Frame frame: frames){
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
