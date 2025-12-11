/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class HakiGainEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  35 */     if (event.getEntityLiving() instanceof PlayerEntity && !(event.getEntityLiving()).field_70170_p.field_72995_K) {
/*     */       
/*  37 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  38 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  39 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  40 */       float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
/*     */       
/*  42 */       boolean isKnockedDown = false;
/*  43 */       if (player.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get())) {
/*  44 */         isKnockedDown = true;
/*     */       }
/*     */       
/*  47 */       if (isKnockedDown) {
/*     */         return;
/*     */       }
/*     */       
/*  51 */       KenbunshokuHakiFutureSightAbility ability = (KenbunshokuHakiFutureSightAbility)props.getEquippedAbility(KenbunshokuHakiFutureSightAbility.INSTANCE);
/*  52 */       if (ability != null && ability.isContinuous() && hakiProps.getKenbunshokuHakiExp() >= 60.0F && player.field_70173_aa % 600 == 0) {
/*     */         
/*  54 */         float finalHakiExp = 0.025F * hakiMultiplier;
/*  55 */         hakiProps.alterKenbunshokuHakiExp(finalHakiExp, StatChangeSource.NATURAL);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttack(WyLivingHurtEvent event) {
/*  63 */     if (event.getAmount() < 1.0F || !(event.getEntityLiving() instanceof PlayerEntity) || !(event.getSource().func_76346_g() instanceof LivingEntity) || (event.getEntityLiving()).field_70170_p.field_72995_K || event.getSource().func_94541_c()) {
/*     */       return;
/*     */     }
/*  66 */     if (event.getSource().func_76364_f() instanceof AbilityProjectileEntity) {
/*     */       
/*  68 */       AbilityProjectileEntity entity = (AbilityProjectileEntity)event.getSource().func_76364_f();
/*  69 */       if (!entity.isPhysical()) {
/*     */         return;
/*     */       }
/*     */     } 
/*  73 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  74 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  75 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  77 */     boolean isKnockedDown = false;
/*  78 */     if (player.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get())) {
/*  79 */       isKnockedDown = true;
/*     */     }
/*     */     
/*  82 */     if (isKnockedDown) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     KenbunshokuHakiAuraAbility ability = (KenbunshokuHakiAuraAbility)abilityProps.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
/*  87 */     if ((ability != null && ability.isContinuous()) || hakiProps.getKenbunshokuHakiExp() <= 30.0D + HakiHelper.getKenbunshokuAuraExpNeeded((LivingEntity)player)) {
/*  88 */       float exp = event.getAmount() / (20.0F + 100.0F * hakiProps.getKenbunshokuHakiExp() / 100.0F);
/*     */       
/*  90 */       if (exp <= 0.0F) {
/*  91 */         exp = 1.0E-5F;
/*     */       }
/*     */       
/*  94 */       float finalHakiExp = exp * (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
/*     */       
/*  96 */       hakiProps.alterKenbunshokuHakiExp(finalHakiExp, StatChangeSource.NATURAL);
/*     */     } 
/*     */   }
/*     */   @SubscribeEvent
/*     */   public static void onEntityDeath(LivingDeathEvent event) {
/*     */     int i, j;
/* 102 */     if ((event.getEntity()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 106 */     PlayerEntity player = null;
/*     */     
/* 108 */     boolean isHardeningDamage = false;
/* 109 */     boolean isImbuingDamage = false;
/*     */     
/* 111 */     if (event.getSource().func_76346_g() instanceof PlayerEntity) {
/* 112 */       player = (PlayerEntity)event.getSource().func_76346_g();
/*     */       
/* 114 */       ItemStack heldStack = player.func_184614_ca();
/*     */       
/* 116 */       isHardeningDamage = heldStack.func_190926_b();
/* 117 */       isImbuingDamage = HakiHelper.isItemAffectedByImbuing(heldStack);
/* 118 */     } else if (event.getSource().func_76346_g() instanceof AbilityProjectileEntity) {
/* 119 */       AbilityProjectileEntity entity = (AbilityProjectileEntity)event.getSource().func_76346_g();
/*     */       
/* 121 */       if (entity.getThrower() instanceof PlayerEntity && entity.isPhysical()) {
/* 122 */         player = (PlayerEntity)entity.getThrower();
/*     */         
/* 124 */         isHardeningDamage = entity.isAffectedByHaki();
/* 125 */         isImbuingDamage = entity.isAffectedByImbuing();
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */     
/* 133 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*     */     
/* 135 */     ModifiableAttributeInstance attrAtk = event.getEntityLiving().func_233645_dx_().func_233779_a_(Attributes.field_233823_f_);
/* 136 */     ModifiableAttributeInstance attrHP = event.getEntityLiving().func_233645_dx_().func_233779_a_(Attributes.field_233818_a_);
/*     */     
/* 138 */     double atk = (attrAtk != null) ? attrAtk.func_111125_b() : 0.0D;
/* 139 */     double hp = (attrHP != null) ? attrHP.func_111125_b() : 0.0D;
/*     */     
/* 141 */     float expValue = (float)(atk + hp);
/* 142 */     int mult = 2;
/* 143 */     boolean flag = false;
/*     */     
/* 145 */     if (event.getSource() instanceof AbilityDamageSource) {
/* 146 */       AbilityDamageSource ablSource = (AbilityDamageSource)event.getSource();
/*     */       
/* 148 */       boolean isSpecial = (ablSource.getHakiNature() == SourceHakiNature.SPECIAL);
/*     */       
/* 150 */       i = isHardeningDamage & ((ablSource.getHakiNature() == SourceHakiNature.HARDENING || isSpecial) ? 1 : 0);
/* 151 */       j = isImbuingDamage & ((ablSource.getHakiNature() == SourceHakiNature.IMBUING || isSpecial) ? 1 : 0);
/*     */     } 
/*     */     
/* 154 */     if (i != 0 || j != 0) {
/* 155 */       if (expValue < hakiProps.getBusoshokuHakiExp()) {
/* 156 */         mult = 10;
/*     */       }
/*     */       
/* 159 */       boolean hasHardeningActive = HakiHelper.hasHardeningActive((LivingEntity)player);
/* 160 */       boolean hasImbuingActive = HakiHelper.hasImbuingActive((LivingEntity)player);
/*     */       
/* 162 */       if (hasHardeningActive || hasImbuingActive || hakiProps.getBusoshokuHakiExp() <= 30.0D + HakiHelper.getBusoshokuHardeningExpNeeded((LivingEntity)player)) {
/* 163 */         flag = true;
/*     */       }
/*     */     } 
/*     */     
/* 167 */     if (flag) {
/* 168 */       StatChangeSource source = (event.getEntityLiving() instanceof PlayerEntity) ? StatChangeSource.KILL_PLAYER : StatChangeSource.KILL_NPC;
/*     */       
/* 170 */       float exp = getFinalHakiExp(expValue, mult, hakiProps);
/*     */       
/* 172 */       if (event.getEntityLiving() instanceof xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity && 
/* 173 */         exp < 1.0F) {
/* 174 */         exp = 1.0F;
/*     */       }
/*     */ 
/*     */       
/* 178 */       float finalHakiExp = (float)(exp * CommonConfig.INSTANCE.getHakiExpMultiplier());
/*     */       
/* 180 */       hakiProps.alterBusoshokuHakiExp(finalHakiExp, source);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final float getFinalHakiExp(float val, int multiplier, IHakiData data) {
/* 185 */     float currentHaki = data.getBusoshokuHakiExp();
/* 186 */     float finalExp = val * (CommonConfig.INSTANCE.getHakiExpLimit() - currentHaki) / multiplier / 10000.0F;
/*     */     
/* 188 */     return Math.max(0.001F, finalExp);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\HakiGainEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */