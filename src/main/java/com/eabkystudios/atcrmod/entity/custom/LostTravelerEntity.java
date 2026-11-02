package com.eabkystudios.atcrmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: write block functionality

public class LostTravelerEntity extends PathfinderMob {
    private static final Logger log = LoggerFactory.getLogger(LostTravelerEntity.class);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public LostTravelerEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, (double)1.0F, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 18d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.ATTACK_DAMAGE, 4d)
                .add(Attributes.FOLLOW_RANGE, 24d);
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

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        boolean hit = super.doHurtTarget(level, target);

        if (hit) {
            this.level().broadcastEntityEvent(this, (byte) 4);
        }

        return hit;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackAnimationState.start(this.tickCount);
        }
        else {
            super.handleEntityEvent(id);
        }


    }
}
