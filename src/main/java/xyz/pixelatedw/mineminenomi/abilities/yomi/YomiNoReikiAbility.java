/*    */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class YomiNoReikiAbility extends OutOfBodyAbility<PhysicalBodyEntity> {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yomi_no_reiki", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user's spirit leaves their body, allowing them to freely explore the nearby areas.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 4000;
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final int MAX_COOLDOWN = 2000;
/* 30 */   public static final AbilityCore<YomiNoReikiAbility> INSTANCE = (new AbilityCore.Builder("Yomi no Reiki", AbilityCategory.DEVIL_FRUITS, YomiNoReikiAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 2000.0F), ContinuousComponent.getTooltip(4000.0F)
/* 33 */       }).setUnlockCheck(YomiNoReikiAbility::canUnlock)
/* 34 */     .build();
/*    */   
/* 36 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*    */   
/*    */   public YomiNoReikiAbility(AbilityCore<YomiNoReikiAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */     
/* 43 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/* 44 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 48 */     AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 5.0D, 0.0D);
/*    */     
/* 50 */     PhysicalBodyEntity body = new PhysicalBodyEntity(entity.field_70170_p);
/* 51 */     body.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/* 52 */     body.setOwner(entity);
/* 53 */     entity.field_70170_p.func_217376_c((Entity)body);
/* 54 */     body.setParentAbility((Ability)this);
/* 55 */     setOriginalBody((LivingEntity)body);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     float cooldown = Math.max(100.0F, this.continuousComponent.getContinueTime() / 2.0F);
/* 60 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   public float damageTakenEvent(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 64 */     if (isActive() && 
/* 65 */       damageSource != DamageSource.field_76376_m && !damageSource.func_151517_h()) {
/* 66 */       return 0.0F;
/*    */     }
/*    */ 
/*    */     
/* 70 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHoldTime() {
/* 75 */     return 4000;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMaxRange() {
/* 80 */     return 100.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPhysical() {
/* 85 */     return false;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 89 */     return ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\YomiNoReikiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */