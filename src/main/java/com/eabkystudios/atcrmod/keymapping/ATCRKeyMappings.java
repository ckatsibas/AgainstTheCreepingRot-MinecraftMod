package com.eabkystudios.atcrmod.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ATCRKeyMappings {
    private static final KeyMapping KEY_MAPPING_CREATE_LIFE = new KeyMapping("key.atcr.create_life",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, KeyMapping.Category.GAMEPLAY);
    public static final Lazy<KeyMapping> PRESS_CREATE_LIFE = Lazy.of(()->KEY_MAPPING_CREATE_LIFE);

    public static void register(){
    }
}
