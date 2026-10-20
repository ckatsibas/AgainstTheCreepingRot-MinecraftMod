package com.eabkystudios.atcrmod.networking.packet;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

public record SummonEntityPacketC2S(String name, EntityType entity, BlockPos pos, int amount) implements CustomPacketPayload {
    public static final Type<SummonEntityPacketC2S> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "create_life_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SummonEntityPacketC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SummonEntityPacketC2S::name,
            EntityType.STREAM_CODEC,
            SummonEntityPacketC2S::entity,
            BlockPos.STREAM_CODEC,
            SummonEntityPacketC2S::pos,
            ByteBufCodecs.VAR_INT,
            SummonEntityPacketC2S::amount,
            SummonEntityPacketC2S::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
