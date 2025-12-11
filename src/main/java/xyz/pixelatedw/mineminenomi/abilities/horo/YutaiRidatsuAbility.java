/*    */ package xyz.pixelatedw.mineminenomi.abilities.horo;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.OutOfBodyAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*    */ 
/*    */ public class YutaiRidatsuAbility extends OutOfBodyAbility<PhysicalBodyEntity> {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yutai_ridatsu", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("The user's spirit leaves their body, allowing them to freely explore the nearby areas.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 1200;
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final int MAX_COOLDOWN = 600;
/* 29 */   public static final AbilityCore<YutaiRidatsuAbility> INSTANCE = (new AbilityCore.Builder("Yutai Ridatsu", AbilityCategory.DEVIL_FRUITS, YutaiRidatsuAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 600.0F), ContinuousComponent.getTooltip(1200.0F)
/* 32 */       }).build();
/*    */   
/* 34 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*    */   
/*    */   public YutaiRidatsuAbility(AbilityCore<YutaiRidatsuAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */     
/* 41 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/* 42 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 46 */     AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 5.0D, 0.0D);
/*    */     
/* 48 */     PhysicalBodyEntity body = new PhysicalBodyEntity(entity.field_70170_p);
/* 49 */     body.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/* 50 */     body.setOwner(entity);
/* 51 */     entity.field_70170_p.func_217376_c((Entity)body);
/* 52 */     body.setParentAbility((Ability)this);
/* 53 */     setOriginalBody((LivingEntity)body);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 57 */     float cooldown = Math.max(100.0F, this.continuousComponent.getContinueTime());
/* 58 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   public float damageTakenEvent(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 62 */     if (isActive() && 
/* 63 */       damageSource != DamageSource.field_76376_m && !damageSource.func_151517_h()) {
/* 64 */       return 0.0F;
/*    */     }
/*    */ 
/*    */     
/* 68 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHoldTime() {
/* 73 */     return 1200;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMaxRange() {
/* 78 */     return 80.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPhysical() {
/* 83 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\horo\YutaiRidatsuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */