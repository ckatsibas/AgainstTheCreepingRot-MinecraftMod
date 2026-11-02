package com.eabkystudios.atcrmod.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeerEntity extends PathfinderMob {
    private static final Logger log = LoggerFactory.getLogger(DeerEntity.class);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public DeerEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12d)
                .add(Attributes.MOVEMENT_SPEED, 0.5d)
                .add(Attributes.FOLLOW_RANGE, 30d);
    }

    private void setIdleAnimationStates(){
        if (this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 50;
            this.idleAnimationState.animateWhen(!this.isInWater(), idleAnimationTimeout);
        }
        else{
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            this.setIdleAnimationStates();
        }

        super.tick();
    }
}
