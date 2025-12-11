/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.OutOfBodyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket2;
/*     */ 
/*     */ public class BaraSplitAbility extends OutOfBodyAbility<BottomHalfBodyEntity> {
/*  25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bara_split", new Pair[] {
/*  26 */         (Pair)ImmutablePair.of("Allows the user to split its upper part of the body from the lower.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 1000;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 1000;
/*  32 */   public static final AbilityCore<BaraSplitAbility> INSTANCE = (new AbilityCore.Builder("Bara Split", AbilityCategory.DEVIL_FRUITS, BaraSplitAbility::new))
/*  33 */     .addDescriptionLine(DESCRIPTION)
/*  34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 1000.0F), ContinuousComponent.getTooltip(1000.0F)
/*  35 */       }).build();
/*     */   
/*  37 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*  38 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.BARA_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   private BottomHalfBodyEntity legs;
/*     */   
/*     */   public BaraSplitAbility(AbilityCore<BaraSplitAbility> core) {
/*  43 */     super(core);
/*     */     
/*  45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.morphComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  47 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*  48 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*  49 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  53 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.BARA_SPLIT.get());
/*     */     
/*  55 */     AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 2.0D, 0.0D);
/*     */     
/*  57 */     this.legs = new BottomHalfBodyEntity(entity.field_70170_p);
/*  58 */     this.legs.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*  59 */     this.legs.setOwner(entity);
/*  60 */     entity.field_70170_p.func_217376_c((Entity)this.legs);
/*  61 */     this.legs.setParentAbility((Ability)this);
/*  62 */     setOriginalBody((LivingEntity)this.legs);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  66 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     float time = this.continuousComponent.getContinueTime();
/*     */     
/*  72 */     if (this.legs != null) {
/*  73 */       setPivotPoint(this.legs.func_233580_cy_());
/*     */     }
/*     */     
/*  76 */     if (time > 5.0F && entity.func_233570_aj_()) {
/*  77 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     if (time % 20.0F == 0.0F) {
/*  82 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket2(entity, (IAbility)this), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.morphComponent.stopMorph(entity);
/*     */     
/*  89 */     float cooldown = Math.max(100.0F, this.continuousComponent.getContinueTime());
/*  90 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHoldTime() {
/*  95 */     return 1000;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxRange() {
/* 100 */     return 40.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 105 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraSplitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */