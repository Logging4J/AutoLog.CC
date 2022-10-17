package cc.l4j.autolog.hack.impl;

import cc.l4j.autolog.command.Command;
import cc.l4j.autolog.hack.Hack;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;

public class Dupe extends Hack {

    public static Dupe getInstance = new Dupe();
    public boolean shouldDupe;

    public Dupe() {
        super("ShulkerDupe", "Dupe patched after 1.19.1", Category.EXPLOITS);
        setDisplayName("Dupe[OldShulkerDupe]");
        setKey(GLFW.GLFW_KEY_V);
    }

    @Override
    public void onEnable() {
        shouldDupe = true;
        Command.sendMessage("[WARNING] This dupe has been patched By Mojang after Minecraft Release 1.19.1");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        shouldDupe = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        if(nullCheck()){return;}
        if(shouldDupe){
            HitResult hit = mc.crosshairTarget;
            if(hit instanceof BlockHitResult){
                BlockHitResult block = (BlockHitResult) hit;
                if(mc.world.getBlockState(block.getBlockPos()).getBlock() instanceof ShulkerBoxBlock && mc.player.currentScreenHandler instanceof ShulkerBoxScreenHandler){
                    mc.interactionManager.updateBlockBreakingProgress(block.getBlockPos(), Direction.DOWN);
                }else{
                    Command.sendMessage("Shulker Box Screen Not Found");
                    setEnabled(false);
                    mc.player.closeHandledScreen();
                    shouldDupe = false;
                }
            }
        }
        super.onTick();
    }

    @Override
    public void onPacketSend(Packet<?> packet) {
        if (nullCheck()){return;}
        if(packet instanceof PlayerActionC2SPacket){
            if(((PlayerActionC2SPacket) packet).getAction() == PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK){
                if(Dupe.getInstance.shouldDupe){
                    Dupe.getInstance.moveItems();
                    Dupe.getInstance.shouldDupe = false;
                }
            }
        }
        super.onPacketSend(packet);
    }

    public void moveItems(){
        for(int i = 0; i < 27; i++){
            if(mc.player.currentScreenHandler instanceof ShulkerBoxScreenHandler){
                ShulkerBoxScreenHandler screenHandler = (ShulkerBoxScreenHandler) mc.player.currentScreenHandler;
                Int2ObjectArrayMap<ItemStack> stack = new Int2ObjectArrayMap<>();
                stack.put(i, screenHandler.getSlot(i).getStack());
                sendPacket(new ClickSlotC2SPacket(screenHandler.syncId, 0, i, 0, SlotActionType.QUICK_MOVE,screenHandler.getSlot(0).getStack(), (Int2ObjectMap)stack));
            }
        }
    }
}
