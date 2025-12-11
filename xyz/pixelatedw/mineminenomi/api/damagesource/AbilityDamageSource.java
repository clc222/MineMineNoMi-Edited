/*     */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiInfusionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ public class AbilityDamageSource
/*     */   extends ModEntityDamageSource
/*     */ {
/*     */   private AbilityCore<? extends IAbility> ability;
/*     */   private boolean isProjectileSource = false;
/*     */   private LivingEntity thrower;
/*     */   
/*     */   public AbilityDamageSource(String damageType, Entity source, AbilityCore<? extends IAbility> ability) {
/*  30 */     super(damageType, source);
/*     */     
/*  32 */     this.ability = ability;
/*     */     
/*  34 */     setSourceTypes(ability.getSourceTypes());
/*  35 */     setHakiNature(ability.getSourceHakiNature());
/*  36 */     setSourceElement(ability.getSourceElement());
/*     */   }
/*     */   
/*     */   public AbilityDamageSource(String damageType, AbilityProjectileEntity source, AbilityCore<? extends IAbility> ability) {
/*  40 */     super(damageType, (Entity)source);
/*     */     
/*  42 */     this.ability = ability;
/*     */     
/*  44 */     setSourceTypes(ability.getSourceTypes());
/*  45 */     setHakiNature(ability.getSourceHakiNature());
/*  46 */     setSourceElement(ability.getSourceElement());
/*     */     
/*  48 */     this.isProjectileSource = true;
/*  49 */     this.thrower = source.getThrower();
/*     */   }
/*     */   
/*     */   public AbilityCore<? extends IAbility> getAbilitySource() {
/*  53 */     return this.ability;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_76364_f() {
/*  59 */     return this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_76346_g() {
/*  69 */     if (this.isProjectileSource && this.thrower != null) {
/*  70 */       return (Entity)this.thrower;
/*     */     }
/*     */     
/*  73 */     return super.func_76346_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public ITextComponent func_151519_b(LivingEntity entityLivingBaseIn) {
/*  78 */     String s = "death.attack." + this.field_76373_n;
/*     */     
/*  80 */     return (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.func_145748_c_(), func_76346_g().func_145748_c_(), this.ability.getUnlocalizedName() });
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  85 */     return "AbilityDamageSource (" + this.field_76373_n + ") from ability: " + this.ability.getUnlocalizedName();
/*     */   }
/*     */   
/*     */   public float getBonusDamage(float damage) {
/*  89 */     float originalAmount = damage;
/*     */     
/*  91 */     if (func_76346_g() == null || !(func_76346_g() instanceof LivingEntity)) {
/*  92 */       return damage;
/*     */     }
/*     */     
/*  95 */     LivingEntity attacker = (LivingEntity)func_76346_g();
/*     */     
/*  97 */     if (attacker == null || damage <= 0.0F) {
/*  98 */       return 0.0F;
/*     */     }
/*     */     
/* 101 */     boolean hasSlashBonus = ((isSlash() || isBlunt()) && !attacker.func_184614_ca().func_190926_b() && ItemsHelper.isSword(attacker.func_184614_ca()));
/* 102 */     boolean hasFistBonus = (isFistDamage() && attacker.func_184614_ca().func_190926_b());
/*     */ 
/*     */     
/* 105 */     if (EntityStatsCapability.get(attacker).isBrawler() && attacker.func_184614_ca().func_77973_b() == ModItems.CANNON_BALL.get() && getAbilitySource().equals(GenkotsuMeteorAbility.INSTANCE)) {
/* 106 */       hasFistBonus = true;
/*     */     }
/*     */ 
/*     */     
/* 110 */     boolean hasSniperBonus = (func_76352_a() && !attacker.func_184614_ca().func_190926_b() && ItemsHelper.isBow(attacker.func_184614_ca()));
/*     */     
/* 112 */     if (hasSlashBonus) {
/* 113 */       damage += ItemsHelper.getItemDamage(attacker.func_184614_ca());
/* 114 */     } else if (hasFistBonus) {
/* 115 */       ModifiableAttributeInstance punchAttr = attacker.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get());
/* 116 */       ModifiableAttributeInstance attackDamageAttr = attacker.func_110148_a(Attributes.field_233823_f_);
/*     */       
/* 118 */       if (punchAttr != null) {
/* 119 */         damage = (float)(damage + punchAttr.func_111126_e());
/*     */       }
/*     */       
/* 122 */       if (attackDamageAttr != null) {
/* 123 */         damage = (float)(damage + attackDamageAttr.func_111126_e());
/*     */       }
/*     */     } 
/*     */     
/* 127 */     if (!hasFistBonus) {
/* 128 */       boolean hasHardeningActive = HakiHelper.hasHardeningActive(attacker, false, false);
/* 129 */       boolean hasImbuingActive = HakiHelper.hasImbuingActive(attacker, false, false);
/* 130 */       boolean hasAdvancedBusoActive = HakiHelper.hasAdvancedBusoActive(attacker);
/* 131 */       boolean hasHardeningBonus = (getHakiNature() == SourceHakiNature.HARDENING && hasHardeningActive);
/* 132 */       boolean hasImbuingBonus = (getHakiNature() == SourceHakiNature.IMBUING && hasImbuingActive);
/* 133 */       boolean hasAdvancedBusoBonus = ((getHakiNature() == SourceHakiNature.HARDENING || getHakiNature() == SourceHakiNature.IMBUING) && hasAdvancedBusoActive);
/* 134 */       boolean hasSpecialBonus = (getHakiNature() == SourceHakiNature.SPECIAL && (hasHardeningActive || hasImbuingActive || hasAdvancedBusoActive));
/* 135 */       boolean hasInfusion = (getHakiNature() != SourceHakiNature.UNKNOWN && HakiHelper.hasInfusionActive(attacker));
/*     */       
/* 137 */       if (hasSpecialBonus) {
/* 138 */         if (hasHardeningActive || hasImbuingActive) {
/* 139 */           damage = (float)(damage + HakiHelper.getBasicBusoshokuHakiDamageBoost(attacker, originalAmount));
/*     */         }
/*     */         
/* 142 */         if (hasAdvancedBusoActive) {
/* 143 */           damage = (float)(damage + HakiHelper.getAdvancedBusoshokuHakiDamageBoost(attacker));
/*     */         }
/*     */       } 
/*     */       
/* 147 */       if (hasHardeningBonus || hasImbuingBonus) {
/* 148 */         damage = (float)(damage + HakiHelper.getBasicBusoshokuHakiDamageBoost(attacker, originalAmount));
/*     */       }
/*     */       
/* 151 */       if (hasAdvancedBusoBonus) {
/* 152 */         damage = (float)(damage + HakiHelper.getAdvancedBusoshokuHakiDamageBoost(attacker));
/*     */       }
/*     */       
/* 155 */       if (hasInfusion) {
/* 156 */         damage = (float)(damage + HaoshokuHakiInfusionAbility.getDamageBoost(attacker, originalAmount));
/*     */       }
/*     */     } 
/*     */     
/* 160 */     return damage;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\AbilityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */