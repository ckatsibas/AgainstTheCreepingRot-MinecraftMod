package com.eabkystudios.atcrmod.item.custom;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingVitalityItem extends Item implements ICurioItem {
    private static final Logger log = LoggerFactory.getLogger(RingVitalityItem.class);
    private Identifier id = Identifier.fromNamespaceAndPath("atcrmod", "ring_of_vitality_modifier");
    private AttributeModifier modifier = new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private AttributeInstance instance;
    private float tickCount;
    private int effectDuration = 300;
    private int amplifier = 1;

    public RingVitalityItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()){
            instance = slotContext.entity().asLivingEntity().getAttribute(Attributes.MAX_HEALTH);
            if (instance!=null){
                instance.addOrUpdateTransientModifier(modifier);
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()){
            instance = slotContext.entity().asLivingEntity().getAttribute(Attributes.MAX_HEALTH);
            if (instance!=null){
                instance.removeModifier(modifier);
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()){
            tickCount++;

            if (tickCount % effectDuration == 0){
                slotContext.entity().asLivingEntity().addEffect(new MobEffectInstance(MobEffects.REGENERATION, effectDuration, amplifier));
                tickCount = 0;
            }

            if(slotContext.entity().asLivingEntity().getHealth() <= slotContext.entity().asLivingEntity().getAttribute(Attributes.MAX_HEALTH).getValue() / 2){
                slotContext.entity().asLivingEntity().addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 100, 1));
            }
        }
    }
}
