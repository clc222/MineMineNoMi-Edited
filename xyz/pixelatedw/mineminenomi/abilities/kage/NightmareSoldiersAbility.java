/*     */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class NightmareSoldiersAbility
/*     */   extends Ability {
/*     */   private static final int CHARGE_TIME = 200;
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nightmare_soldiers", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Creates Nightmare Soldiers using Shadows from the user's inventory, the longer the ability charges the more soldiers it'll create", null) }); private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 300;
/*  36 */   public static final AbilityCore<NightmareSoldiersAbility> INSTANCE = (new AbilityCore.Builder("Nightmare Soldiers", AbilityCategory.DEVIL_FRUITS, NightmareSoldiersAbility::new))
/*  37 */     .addDescriptionLine(DESCRIPTION)
/*  38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 300.0F), ChargeComponent.getTooltip(200.0F)
/*  39 */       }).build();
/*     */   
/*  41 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this, comp -> (getShadowsUsed() > 0))).addTickEvent(100, this::duringChargingEvent).addEndEvent(100, this::stopChargingEvent);
/*  42 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*     */   
/*     */   private int prevShadowValue;
/*     */   
/*     */   public NightmareSoldiersAbility(AbilityCore<NightmareSoldiersAbility> core) {
/*  47 */     super(core);
/*     */     
/*  49 */     this.isNew = true;
/*  50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  52 */     addCanUseCheck(this::canUseCheck);
/*  53 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUseCheck(LivingEntity entity, IAbility ability) {
/*  57 */     if (ItemsHelper.countItemInInventory(entity, (Item)ModItems.SHADOW.get()) <= 0) {
/*  58 */       return AbilityUseResult.fail(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
/*     */     }
/*  60 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.chargeComponent.startCharging(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void duringChargingEvent(LivingEntity entity, IAbility ability) {
/*  68 */     if (!entity.field_70170_p.field_72995_K && this.chargeComponent.getChargeTime() % 2.0F == 0.0F) {
/*  69 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHARGE_KAGE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/*  72 */     entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 20, 2, false, false));
/*  73 */     int shadowsUsed = getShadowsUsed();
/*  74 */     if (ItemsHelper.countItemInInventory(entity, (Item)ModItems.SHADOW.get()) < shadowsUsed) {
/*  75 */       entity.func_145747_a(KageHelper.NOT_ENOUGH_SHADOWS_WARN, Util.field_240973_b_);
/*  76 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  80 */     if (shadowsUsed != this.prevShadowValue) {
/*  81 */       this.stackComponent.setStacks(entity, (IAbility)this, shadowsUsed);
/*  82 */       this.prevShadowValue = shadowsUsed;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean stopChargingEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.cooldownComponent.startCooldown(entity, this.chargeComponent.getChargeTime() + 100.0F);
/*  88 */     int shadowsUsed = getShadowsUsed();
/*     */     
/*  90 */     KageHelper.removeShadows(entity, shadowsUsed);
/*     */     
/*  92 */     for (int i = 0; i < shadowsUsed; i++) {
/*  93 */       NightmareSoldierEntity soldier = new NightmareSoldierEntity(entity.field_70170_p);
/*  94 */       soldier.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*  95 */       soldier.setOwner(entity);
/*  96 */       entity.field_70170_p.func_217376_c((Entity)soldier);
/*     */     } 
/*     */     
/*  99 */     this.prevShadowValue = 0;
/* 100 */     this.stackComponent.setStacks(entity, (IAbility)this, 0);
/*     */     
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   private int getShadowsUsed() {
/* 106 */     return (int)(this.chargeComponent.getChargeTime() / 40.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\NightmareSoldiersAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */