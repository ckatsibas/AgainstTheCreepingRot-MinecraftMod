package com.eabkystudios.atcrmod.networking;

import com.eabkystudios.atcrmod.networking.packet.SummonEntityPacketC2S;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.neoforged.neoforge.network.handling.IPayloadContext;

// Handle packets from Client to Server
public class ClientPayloadHandler {
    //HERE: on server

    public static void handleSummonEntityPacket(SummonEntityPacketC2S summonEntityPacketC2S, IPayloadContext context) {
        summonEntityPacketC2S.entity().spawn((ServerLevel) context.player().level(), summonEntityPacketC2S.pos(), EntitySpawnReason.TRIGGERED);
    }
}
