/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public abstract class MorphAbility2 extends Ability {
/* 17 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true)).addStartEvent(110, this::startContinuityEvent).addTickEvent(110, this::tickContinuityEvent).addEndEvent(110, this::stopContinuityEvent);
/* 18 */   protected final ChangeStatsComponent statsComponent = new ChangeStatsComponent(this);
/* 19 */   protected final MorphComponent morphComponent = new MorphComponent(this);
/*    */   
/*    */   public MorphAbility2(AbilityCore<? extends IAbility> core) {
/* 22 */     super(core);
/*    */     
/* 24 */     this.isNew = true;
/* 25 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent, (AbilityComponent)this.morphComponent });
/*    */     
/* 27 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 31 */     String currentMorph = DevilFruitCapability.get(entity).getZoanPoint();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     if (!Strings.isNullOrEmpty(currentMorph) && !currentMorph.equals(getTransformation().getForm())) {
/* 37 */       Set<IAbility> abls = AbilityDataCapability.get(entity).getEquippedAbilitiesWith(new AbilityComponentKey[] { ModAbilityKeys.MORPH, ModAbilityKeys.CONTINUOUS });
/* 38 */       for (IAbility abl : abls) {
/* 39 */         if (abl == this) {
/*    */           continue;
/*    */         }
/*    */         
/* 43 */         ((ContinuousComponent)abl.<ContinuousComponent>getComponent(ModAbilityKeys.CONTINUOUS).get()).stopContinuity(entity);
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     this.continuousComponent.triggerContinuity(entity, getContinuityHoldTime());
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.morphComponent.startMorph(entity, getTransformation());
/* 52 */     this.statsComponent.applyModifiers(entity);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 56 */     if (!this.morphComponent.isMorphed()) {
/* 57 */       this.continuousComponent.stopContinuity(entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.morphComponent.stopMorph(entity);
/* 63 */     this.statsComponent.removeModifiers(entity);
/* 64 */     this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/*    */   }
/*    */   
/*    */   public abstract MorphInfo getTransformation();
/*    */   
/*    */   public float getContinuityHoldTime() {
/* 70 */     return -1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCooldownTicks() {
/* 75 */     return 10.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\MorphAbility2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */