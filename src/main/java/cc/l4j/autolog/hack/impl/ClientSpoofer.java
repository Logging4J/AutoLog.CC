package cc.l4j.autolog.hack.impl;

import cc.l4j.autolog.hack.Hack;
import cc.l4j.autolog.hack.HackManager;
import cc.l4j.autolog.hack.options.Option;
import cc.l4j.autolog.hack.options.impl.OptionMode;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.mixin.networking.accessor.CustomPayloadC2SPacketAccessor;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;

import java.nio.charset.StandardCharsets;

//WIP Not Done Yet
public class ClientSpoofer extends Hack {
    public ClientSpoofer() {
        super("ClientSpoofer", "Spoof your Client to get into more servers", Category.EXPLOITS);
        addSetting(mode);
    }

    Option mode = new OptionMode("Mode", "FML", "Vanilla");

    @Override
    public void sendPacket(Packet<?> packet) {
        if(packet instanceof CustomPayloadC2SPacket){
            if(((CustomPayloadC2SPacket) packet).getChannel() == CustomPayloadC2SPacket.BRAND){
                if(mode.getName().equalsIgnoreCase("FML")) {
                    CustomPayloadC2SPacket newPacket = new CustomPayloadC2SPacket(CustomPayloadC2SPacket.BRAND, new PacketByteBuf(Unpooled.buffer()).writeString("FML"));
                    sendPacket(newPacket);
                } else if(mode.getName().equalsIgnoreCase("Vanilla")) {
                    CustomPayloadC2SPacket newPacket = new CustomPayloadC2SPacket(CustomPayloadC2SPacket.BRAND, new PacketByteBuf(Unpooled.buffer()).writeString("vanilla"));
                    sendPacket(newPacket);
                }
            }
        }
        super.sendPacket(packet);
    }
}
