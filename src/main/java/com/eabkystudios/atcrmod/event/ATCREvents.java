package com.eabkystudios.atcrmod.event;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.ATCREntities;
import com.eabkystudios.atcrmod.entity.custom.DeerEntity;
import com.eabkystudios.atcrmod.entity.custom.LostTravelerEntity;
import com.eabkystudios.atcrmod.item.ATCRCurios;
import com.eabkystudios.atcrmod.item.ATCRItems;
import com.eabkystudios.atcrmod.item.custom.AmuletOfTheDeathlessItem;
import com.eabkystudios.atcrmod.networking.ClientPayloadHandler;
import com.eabkystudios.atcrmod.networking.packet.SummonEntityPacketC2S;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(modid = AgainstTheCreepingRot.MODID)
public class ATCREvents {

    private static final Logger log = LoggerFactory.getLogger(ATCREvents.class);

    @SubscribeEvent
    public static void registerPayload(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.MAIN);

        registrar.playToServer(SummonEntityPacketC2S.TYPE, SummonEntityPacketC2S.STREAM_CODEC, ClientPayloadHandler::handleSummonEntityPacket);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ATCREntities.LOST_TRAVELER.get(), LostTravelerEntity.createAttributes().build());
        event.put(ATCREntities.DEER.get(), DeerEntity.createAttributes().build());
    }

    //TODO: Remove this. This is called when entity dies so I wont be able to prevent death here.
    @SubscribeEvent
    public static void amuletOfTheDeathlessEvent(LivingDeathEvent event){
        CuriosApi.getCuriosInventory(event.getEntity()).flatMap(curiosInventory ->{
            if (curiosInventory.isEquipped(ATCRItems.AMULET_OF_THE_DEATHLESS.get())){
                if(AmuletOfTheDeathlessItem.isShouldPreventDeath()){
                    ItemStack protectionItem = ATCRItems.AMULET_OF_THE_DEATHLESS.asItem().getDefaultInstance();
                    DeathProtection protection  = protectionItem.get(DataComponents.DEATH_PROTECTION);

                    event.getEntity().setHealth(1.0F);
                    protection.applyEffects(protectionItem, event.getEntity());
                    event.getEntity().level().broadcastEntityEvent(event.getEntity(), (byte)35);

                    AmuletOfTheDeathlessItem.setShouldPreventDeath(false);
                }
            }

            return java.util.Optional.empty();
        });
    }
}
