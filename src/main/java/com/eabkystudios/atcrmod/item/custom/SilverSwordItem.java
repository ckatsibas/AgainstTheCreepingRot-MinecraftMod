package com.eabkystudios.atcrmod.item.custom;

import com.eabkystudios.atcrmod.effect.ATCREffects;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SilverSwordItem extends Item {
    public static final int EFFECT_DURATION = 100;
    public static final int EFFECT_AMP = 1;
    private static final Logger logger = LoggerFactory.getLogger(SilverSwordItem.class);

    public SilverSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker)
    {
        if(!attacker.level().isClientSide()){
            // log.debug("Level check: server.");
            if (mob.is(EntityTypeTags.UNDEAD) && mob.isAlive() && !mob.isInvulnerable()){
                mob.addEffect(new MobEffectInstance(ATCREffects.MOTHER_REVENGE_EFFECT, EFFECT_DURATION ,EFFECT_AMP));
                // logger.debug("Added effect to undead mob!");
            }
        }
        super.hurtEnemy(itemStack, mob, attacker);
    }
}
