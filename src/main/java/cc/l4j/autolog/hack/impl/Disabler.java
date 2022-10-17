package cc.l4j.autolog.hack.impl;

import cc.l4j.autolog.hack.Hack;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class Disabler extends Hack {



    public Disabler() {
        super("Disabler", "Disable AntiCheats and Plugins", Category.EXPLOITS);
        setKey(GLFW.GLFW_KEY_B);
        setDisplayName("Disabler[LiveOverFlow]");
    }

    @Override
    public void onPacketSend(Packet<?> packet) {
        if(packet instanceof PlayerMoveC2SPacket.Full){
            PlayerMoveC2SPacket.Full mPacket = (PlayerMoveC2SPacket.Full)packet;
            double x = ((int)(mPacket.getX(mc.player.getX()) * 100)) / 100.0;
            double y = ((int)(mPacket.getY(mc.player.getY()) * 100)) / 100.0;
            double z = ((int)(mPacket.getZ(mc.player.getZ()) * 100)) / 100.0;
            mc.player.networkHandler.getConnection().send(new PlayerMoveC2SPacket.Full(x, y, z, mPacket.getYaw(mc.player.getYaw()), mPacket.getPitch(mc.player.getPitch()), mPacket.isOnGround()));
            mc.player.setPos(x, y, z);
        }
        super.onPacketSend(packet);
    }
}
