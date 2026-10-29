package com.eabkystudios.atcrmod;

import com.eabkystudios.atcrmod.entity.ATCREntities;
import com.eabkystudios.atcrmod.entity.client.ATCRModelLayerLocations;
import com.eabkystudios.atcrmod.entity.client.LostTravelerModel;
import com.eabkystudios.atcrmod.entity.client.LostTravelerRenderer;
import com.eabkystudios.atcrmod.keymapping.ATCRKeyMappings;
import com.eabkystudios.atcrmod.menu.ATCRMenuTypes;
import com.eabkystudios.atcrmod.menu.custom.PoweredFurnaceScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = AgainstTheCreepingRot.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = AgainstTheCreepingRot.MODID, value = Dist.CLIENT)
public class AgainstTheCreepingRotClient {
    public static final Logger LOGGER = LogUtils.getLogger();

    public AgainstTheCreepingRotClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        ATCRKeyMappings.register();
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ATCREntities.LOST_TRAVELER.get(), LostTravelerRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ATCRModelLayerLocations.LOST_TRAVELER, LostTravelerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registeredKeyMappings(RegisterKeyMappingsEvent event){
        event.register(ATCRKeyMappings.PRESS_CREATE_LIFE.get());
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ATCRMenuTypes.POWERED_FURNACE_MENU.get(), PoweredFurnaceScreen::new);
    }
}
