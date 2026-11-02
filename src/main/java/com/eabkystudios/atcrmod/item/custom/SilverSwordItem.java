package com.eabkystudios.atcrmod.item.custom;

import com.eabkystudios.atcrmod.entity.ATCREntities;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import java.util.Objects;

public class SilverSwordItem extends Item {
    private static Holder<Attribute> attackerDamage;
    private static final Logger logger = LoggerFactory.getLogger(SilverSwordItem.class);

    public SilverSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker)
    {
        // float checkDamage = (float) attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue();

        if(!attacker.level().isClientSide()){
            logger.debug("Level check: server.");
            if (mob.is(EntityTypeTags.UNDEAD) || mob.is(ATCREntities.LOST_TRAVELER.get()) && mob.isAlive() && !mob.isInvulnerable()){
                float damageMulti = 1.8f;
                float newDamageAmount = (float) attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * damageMulti;
                mob.hurtServer((ServerLevel) attacker.level(), Objects.requireNonNull(mob.getLastDamageSource()), newDamageAmount);

                //checkDamage = newDamageAmount;
            }

            //logger.debug("Damage Source: " + mob.getLastDamageSource().toString());
            //logger.debug("Damage: " + checkDamage);
            //logger.debug("Enemy has: " + mob.getHealth() + " health left.");
        }
        super.hurtEnemy(itemStack, mob, attacker);
    }
}
