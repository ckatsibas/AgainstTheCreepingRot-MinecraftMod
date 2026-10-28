package com.eabkystudios.atcrmod.item;

import com.eabkystudios.atcrmod.tags.ATCRTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class ATCRToolTiers {
    // speed has to do with mining, swords don't use this
    // attackDamageBonus + 4 is how much damage a sword item does.

    public static final ToolMaterial SILVER = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 180, 6.0f, 1f, 28, ATCRTags.Items.SILVER_REPAIRABLE);
    public static final ToolMaterial STEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 425, 5.0f, 2f, 28, ATCRTags.Items.STEEL_REPAIRABLE);
}
