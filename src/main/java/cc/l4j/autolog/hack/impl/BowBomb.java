package cc.l4j.autolog.hack.impl;

import cc.l4j.autolog.hack.Hack;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class BowBomb extends Hack {

    public static BowBomb getInstance = new BowBomb();
    public BowBomb() {
        super("BowBomb", "Droppin Bombs fr", Category.COMBAT);
    }

    public void onBowRelease(){
        sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_SPRINTING));
        for(int i = 0; i < 100; i++){
            sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() - 0.000000001, mc.player.getZ(), true));
            sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() + 0.000000001, mc.player.getZ(), false));
        }
    }
}
