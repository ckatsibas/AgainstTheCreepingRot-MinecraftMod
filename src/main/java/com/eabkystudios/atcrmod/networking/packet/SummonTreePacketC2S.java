package com.eabkystudios.atcrmod.networking.packet;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SummonTreePacketC2S(String name, BlockPos pos) implements CustomPacketPayload {
    public static final Type<SummonEntityPacketC2S> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "summon_tree_packet"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
