package com.eabkystudios.atcrmod.event;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.keymapping.ATCRKeyMappings;
import com.eabkystudios.atcrmod.networking.packet.SummonEntityPacketC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

@EventBusSubscriber(modid = AgainstTheCreepingRot.MODID, value = Dist.CLIENT)
public class ATCRClientEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        //TODO: make a gui that allows to chose which (living) entity to summon. Also how do i call it from anywhere. Lets say I want a weapon to summon an entity.
        //TODO FIX: sometimes would spawn inside a block. used above(2) for temp fix but should find a better solution.

        while (ATCRKeyMappings.PRESS_CREATE_LIFE.get().consumeClick()) {
            var player = Minecraft.getInstance().player;
            if (player == null) return;

            BlockPos targetPos = null;
            var hitResult = Minecraft.getInstance().hitResult;

            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                targetPos = ((BlockHitResult) hitResult).getBlockPos();
            }
            else {
                double distance = 6.0f;
                var lookVector = player.getViewVector(1.0F);
                targetPos = new BlockPos(
                        (int) Math.floor(player.getX() + (lookVector.x * distance)),
                        (int) Math.floor(player.getY() + (lookVector.y * distance)),
                        (int) Math.floor(player.getZ() + (lookVector.z * distance))
                );
            }

            ClientPacketDistributor.sendToServer(new SummonEntityPacketC2S("Summon Entity", EntityType.COW, targetPos.above(2), 1));
        }
    }
}
