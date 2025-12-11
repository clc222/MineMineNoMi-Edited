/*     */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BetaCoatingAbility extends Ability {
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "beta_coating", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Covers the user in a thick mucus coat, which makes them immune to almost all attacks, but extremely vulnerable to fire.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 800;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 400;
/*  51 */   public static final AbilityCore<BetaCoatingAbility> INSTANCE = (new AbilityCore.Builder("Beta Coating", AbilityCategory.DEVIL_FRUITS, BetaCoatingAbility::new))
/*  52 */     .addDescriptionLine(DESCRIPTION)
/*  53 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 400.0F), ContinuousComponent.getTooltip(800.0F)
/*  54 */       }).build();
/*     */   
/*  56 */   private static final ArrayList<DamageSource> EXPLOSIVE_SOURCES = new ArrayList<>(Arrays.asList(new DamageSource[] { DamageSource.field_76372_a, DamageSource.field_180137_b, DamageSource.field_76370_b, DamageSource.field_76371_c }));
/*  57 */   private static final ArrayList<SourceElement> EXPLOSIVE_ELEMENTS = new ArrayList<>(Arrays.asList(new SourceElement[] { SourceElement.FIRE, SourceElement.LIGHTNING, SourceElement.EXPLOSION }));
/*     */   
/*  59 */   private static final AbilityAttributeModifier SPEED_MULTIPLIER = new AbilityAttributeModifier(UUID.fromString("efa08cbd-57e5-478f-b15c-6295eb1b375e"), INSTANCE, "Beta Speed Modifier", -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */   
/*  61 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.BETA_COATING).setColor(WyHelper.hexToRGB("#FFFFFFA6")).build();
/*     */   
/*  63 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  64 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*  65 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnHurtEvent(this::damageTakenEvent);
/*  66 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*     */   private boolean exploded = false;
/*     */   
/*     */   public BetaCoatingAbility(AbilityCore<BetaCoatingAbility> core) {
/*  71 */     super(core);
/*     */     
/*  73 */     this.isNew = true;
/*  74 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.changeStatsComponent });
/*     */     
/*  76 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MULTIPLIER);
/*     */     
/*  78 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.continuousComponent.triggerContinuity(entity, 800.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  86 */     this.exploded = false;
/*     */     
/*  88 */     this.skinOverlayComponent.showAll(entity);
/*  89 */     this.changeStatsComponent.applyModifiers(entity);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  93 */     if (this.continuousComponent.getContinueTime() % 10.0F == 0.0F) {
/*  94 */       checkIfOnFire(entity);
/*     */     }
/*     */     
/*  97 */     climbingMovement(entity);
/*     */   }
/*     */   
/*     */   private void climbingMovement(LivingEntity entity) {
/* 101 */     boolean isNearBlock = false;
/* 102 */     AxisAlignedBB bb = entity.func_174813_aQ().func_72314_b(0.25D, 0.25D, 0.25D);
/* 103 */     int mX = MathHelper.func_76128_c(bb.field_72340_a);
/* 104 */     int mY = MathHelper.func_76128_c(bb.field_72338_b);
/* 105 */     int mZ = MathHelper.func_76128_c(bb.field_72339_c);
/* 106 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 107 */     for (int y2 = mY; y2 < bb.field_72337_e; y2++) {
/* 108 */       for (int x2 = mX; x2 < bb.field_72336_d; x2++) {
/* 109 */         for (int z2 = mZ; z2 < bb.field_72334_f; z2++) {
/* 110 */           mutpos.func_181079_c(x2, y2, z2);
/* 111 */           if (entity.field_70170_p.func_180495_p((BlockPos)mutpos).func_185904_a().func_76220_a()) {
/* 112 */             isNearBlock = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 119 */     if ((entity.field_70123_F && !entity.field_70124_G) || (entity.func_213453_ef() && isNearBlock)) {
/* 120 */       double climbSpeed = Math.min(0.1D, (entity.func_70040_Z()).field_72448_b * 0.5D);
/* 121 */       if (entity.func_213453_ef()) {
/* 122 */         AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, 0.0D, (entity.func_213322_ci()).field_72449_c);
/*     */       } else {
/*     */         
/* 125 */         AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, climbSpeed, (entity.func_213322_ci()).field_72449_c);
/*     */       } 
/* 127 */       entity.field_70143_R = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkIfOnFire(LivingEntity entity) {
/* 132 */     boolean hasFireAbilityActive = false;
/* 133 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 135 */     for (IAbility abl : props.getEquippedAbilities()) {
/* 136 */       if (abl.getCore().getSourceElement().equals(SourceElement.FIRE) || abl.getCore().getSourceElement().equals(SourceElement.MAGMA)) {
/* 137 */         boolean isActive = ((Boolean)abl.getComponent(ModAbilityKeys.CONTINUOUS).map(comp -> Boolean.valueOf(comp.isContinuous())).orElse(Boolean.valueOf(false))).booleanValue();
/* 138 */         if (isActive) {
/* 139 */           hasFireAbilityActive = true;
/*     */           
/*     */           break;
/*     */         } 
/* 143 */         isActive = ((Boolean)abl.getComponent(ModAbilityKeys.CHARGE).map(comp -> Boolean.valueOf(comp.isCharging())).orElse(Boolean.valueOf(false))).booleanValue();
/* 144 */         if (isActive) {
/* 145 */           hasFireAbilityActive = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     if (entity.func_70027_ad() || hasFireAbilityActive) {
/* 153 */       this.exploded = true;
/* 154 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 159 */     this.skinOverlayComponent.hideAll(entity);
/* 160 */     this.changeStatsComponent.removeModifiers(entity);
/*     */     
/* 162 */     float bonus = 0.0F;
/* 163 */     if (this.exploded) {
/* 164 */       bonus = 600.0F;
/*     */       
/* 166 */       entity.func_70066_B();
/*     */       
/* 168 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 6.0F);
/*     */       
/* 170 */       explosion.setExplosionSound(true);
/* 171 */       explosion.setDamageOwner(true);
/* 172 */       explosion.setDestroyBlocks(true);
/* 173 */       explosion.setFireAfterExplosion(true);
/* 174 */       explosion.setStaticDamage(100.0F);
/* 175 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/* 176 */       explosion.setDamageEntities(true);
/* 177 */       explosion.doExplosion();
/*     */     } 
/*     */     
/* 180 */     float cooldown = Math.min(100.0F, this.continuousComponent.getContinueTime() / 2.0F) + bonus;
/* 181 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   private float damageTakenEvent(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 185 */     if (!isContinuous()) {
/* 186 */       return damage;
/*     */     }
/*     */     
/* 189 */     if (damageSource instanceof ModDamageSource) {
/* 190 */       ModDamageSource modSource = (ModDamageSource)damageSource;
/*     */       
/* 192 */       if (modSource.isUnavoidable()) {
/* 193 */         return damage;
/*     */       }
/*     */       
/* 196 */       for (SourceElement element : EXPLOSIVE_ELEMENTS) {
/* 197 */         if (modSource.getElement() == element) {
/* 198 */           this.exploded = true;
/* 199 */           this.continuousComponent.stopContinuity(entity);
/* 200 */           return damage;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 205 */     for (DamageSource s : EXPLOSIVE_SOURCES) {
/* 206 */       if (damageSource.func_76355_l().equals(s.func_76355_l())) {
/* 207 */         this.exploded = true;
/* 208 */         this.continuousComponent.stopContinuity(entity);
/* 209 */         return damage;
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     if (damageSource.func_76364_f() instanceof LivingEntity && damageSource.func_76364_f().func_70027_ad()) {
/* 214 */       return damage;
/*     */     }
/*     */     
/* 217 */     if (!damageSource.func_94541_c() && !damageSource.func_76347_k() && !damageSource.func_151517_h()) {
/* 218 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BETA_COATING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 219 */       return 0.0F;
/*     */     } 
/*     */     
/* 222 */     this.exploded = true;
/* 223 */     this.continuousComponent.stopContinuity(entity);
/*     */ 
/*     */     
/* 226 */     return damage;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\BetaCoatingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */