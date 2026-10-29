package com.eabkystudios.atcrmod.entity;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.custom.LostTravelerEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ATCREntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.createEntities(AgainstTheCreepingRot.MODID);

    public static final ResourceKey<EntityType<?>> LOST_TRAVELER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "lost_traveler"));
    public static final Supplier<EntityType<LostTravelerEntity>> LOST_TRAVELER = ENTITY_TYPES.register("lost_traveler", ()-> EntityType.Builder.of(LostTravelerEntity::new, MobCategory.MONSTER).sized(1f, 2f).build(LOST_TRAVELER_KEY));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
