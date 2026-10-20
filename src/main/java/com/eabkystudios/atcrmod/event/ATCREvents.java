package com.eabkystudios.atcrmod.event;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.networking.ClientPayloadHandler;
import com.eabkystudios.atcrmod.networking.packet.SummonEntityPacketC2S;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = AgainstTheCreepingRot.MODID)
public class ATCREvents {

    @SubscribeEvent
    public static void registerPayload(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.MAIN);
        registrar.playToServer(SummonEntityPacketC2S.TYPE, SummonEntityPacketC2S.STREAM_CODEC, ClientPayloadHandler::handleSummonEntityPacket);
    }
}
