/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.CombatRules;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class KenbunshokuHakiFutureSightAbility extends Ability {
/*  51 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kenbunshoku_haki_future_sight", new Pair[] {
/*  52 */         (Pair)ImmutablePair.of("Using Observation Haki allows the user to see a short period into the future to avoid attacks.", null)
/*     */       });
/*     */   
/*     */   private static final int MIN_COOLDOWN = 100;
/*  56 */   public static final AbilityCore<KenbunshokuHakiFutureSightAbility> INSTANCE = (new AbilityCore.Builder("Kenbunshoku Haki: Future Sight", AbilityCategory.HAKI, KenbunshokuHakiFutureSightAbility::new))
/*  57 */     .addDescriptionLine(DESCRIPTION)
/*  58 */     .setUnlockCheck(KenbunshokuHakiFutureSightAbility::canUnlock)
/*  59 */     .build();
/*     */   
/*  61 */   public static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setColor(new Color(255, 100, 200, 100)).build();
/*     */   
/*  63 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  64 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*  65 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*  66 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.DODGE_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  67 */   private final StackComponent stackComponent = (new StackComponent((IAbility)this)).addStackChangeEvent(this::changeStackEvent);
/*     */   
/*     */   private boolean hasDodged;
/*     */   
/*     */   private int protTimer;
/*     */   
/*  73 */   private int invulnerabilityTimer = 0;
/*     */   
/*     */   public KenbunshokuHakiFutureSightAbility(AbilityCore<KenbunshokuHakiFutureSightAbility> core) {
/*  76 */     super(core);
/*     */     
/*  78 */     this.isNew = true;
/*  79 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.poolComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  81 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*  82 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  83 */     addCanUseCheck(AbilityHelper::requiresFocus);
/*  84 */     addUseEvent(this::useEvent);
/*  85 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   private void equipEvent(LivingEntity entity, Ability ability) {
/*  89 */     this.stackComponent.setDefaultStacks(calculateMaxProtection(entity));
/*  90 */     this.stackComponent.revertStacksToDefault(entity, (IAbility)ability);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  94 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void changeStackEvent(LivingEntity entity, IAbility ability, int stacks) {
/*  98 */     if (stacks <= 0) {
/*  99 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HAKI_GUARD.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 100 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 105 */     this.stackComponent.setDefaultStacks(calculateMaxProtection(entity));
/* 106 */     this.stackComponent.revertStacksToDefault(entity, ability);
/* 107 */     this.invulnerabilityTimer = 0;
/* 108 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.KENBUNSHOKU_HAKI_ON_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 112 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 2);
/* 113 */     if (isOnMaxOveruse) {
/* 114 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HAKI_GUARD.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 115 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */     
/* 118 */     if (this.protTimer > 0) {
/* 119 */       this.protTimer--;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 124 */     else if (this.protTimer <= 0 && this.hasDodged) {
/* 125 */       this.skinOverlayComponent.hideAll(entity);
/* 126 */       this.hasDodged = false;
/*     */     } 
/*     */     
/* 129 */     if (this.invulnerabilityTimer > 0) {
/* 130 */       this.invulnerabilityTimer--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 141 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.KENBUNSHOKU_HAKI_OFF.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     
/* 143 */     this.skinOverlayComponent.hideAll(entity);
/*     */     
/* 145 */     float protLost = ((this.stackComponent.getDefaultStacks() - this.stackComponent.getStacks()) * 6);
/*     */     
/* 147 */     float cooldown = 100.0F + WyHelper.secondsToTicks(protLost);
/* 148 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */     
/* 150 */     this.stackComponent.revertStacksToDefault(entity, (IAbility)this);
/*     */   }
/*     */   
/*     */   private int calculateMaxProtection(LivingEntity entity) {
/* 154 */     IEntityStats sprops = EntityStatsCapability.get(entity);
/* 155 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/* 157 */     double dorikiPower = sprops.getDoriki() / 5000.0D;
/* 158 */     double hakiPower = (hakiProps.getKenbunshokuHakiExp() / 12.0F);
/*     */     
/* 160 */     return (int)Math.max(1L, Math.round(dorikiPower + hakiPower));
/*     */   }
/*     */   
/*     */   public void reduceProtection(LivingEntity entity, float damage) {
/* 164 */     if (this.invulnerabilityTimer == 0) {
/* 165 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.FUTURE_SIGHT_HIT.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 2.0F);
/*     */       
/* 167 */       int stacks = Math.max(1, Math.round(damage / 15.0F));
/*     */       
/* 169 */       this.stackComponent.addStacks(entity, (IAbility)this, -stacks);
/* 170 */       this.protTimer = 4;
/* 171 */       this.invulnerabilityTimer = 10;
/* 172 */       this.hasDodged = true;
/*     */     } 
/*     */     
/* 175 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(entity, this), (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 180 */     nbt = super.save(nbt);
/* 181 */     nbt.func_74768_a("protectionTimer", this.protTimer);
/* 182 */     nbt.func_74768_a("invulnerabilityTimer", this.invulnerabilityTimer);
/* 183 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 188 */     super.load(nbt);
/* 189 */     this.protTimer = nbt.func_74762_e("protectionTimer");
/* 190 */     this.invulnerabilityTimer = nbt.func_74762_e("invulnerabilityTimer");
/*     */   }
/*     */   
/*     */   public float damageTakenEvent(LivingEntity entity, IAbility ability, DamageSource source, float damage) {
/* 194 */     if (!isContinuous() || !AbilityHelper.canUseMomentumAbilities(entity) || AbilityHelper.isGrabbing(entity)) {
/* 195 */       return damage;
/*     */     }
/*     */     
/* 198 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/* 200 */     int hakiOveruse = 10 + hakiProps.getMaxOveruse() / 1180;
/*     */     
/* 202 */     boolean isLogia = DevilFruitCapability.get(entity).isLogia();
/*     */     
/* 204 */     LogiaInvulnerabilityAbility invulnerabilityInstance = null;
/*     */     
/* 206 */     if (isLogia) {
/* 207 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */       
/* 209 */       Set<IAbility> unlockedAbilities = abilityProps.getPassiveAbilities(AbilityCategory.DEVIL_FRUITS.isAbilityPartofCategory());
/*     */       
/* 211 */       for (IAbility otherAbility : unlockedAbilities) {
/* 212 */         if (otherAbility == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 216 */         if (otherAbility instanceof LogiaInvulnerabilityAbility) {
/* 217 */           invulnerabilityInstance = (LogiaInvulnerabilityAbility)otherAbility;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       hakiOveruse /= 3;
/* 228 */     } else if (source.func_94541_c()) {
/* 229 */       return damage;
/*     */     } 
/*     */     
/* 232 */     ArrayList<DamageSource> damageableSources = new ArrayList<>(Arrays.asList(new DamageSource[] { DamageSource.field_76371_c, DamageSource.field_76368_d, DamageSource.field_76367_g, DamageSource.field_220302_v, DamageSource.field_76366_f, DamageSource.field_82728_o, DamageSource.field_188406_j, DamageSource.field_76379_h, DamageSource.field_82729_p, DamageSource.field_76380_i, DamageSource.field_82727_n, DamageSource.field_76376_m, DamageSource.field_76372_a, DamageSource.field_76370_b, DamageSource.field_180137_b }));
/*     */     
/* 234 */     if (getInvulnerabilityTimer() > 0 && !damageableSources.contains(source) && !source.func_76355_l().equals("special") && !isLogia) {
/* 235 */       return 0.0F;
/*     */     }
/*     */     
/* 238 */     if (!source.func_76363_c()) {
/* 239 */       damage = CombatRules.func_189427_a(damage, entity.func_70658_aO(), (float)entity.func_110148_a(Attributes.field_233827_j_).func_111126_e());
/*     */     }
/*     */     
/* 242 */     int absorbed = EnchantmentHelper.func_77508_a(entity.func_184193_aE(), source);
/*     */     
/* 244 */     if (absorbed > 0) {
/* 245 */       damage = CombatRules.func_188401_b(damage, absorbed);
/*     */     }
/*     */     
/* 248 */     if (damage < 0.0F) {
/* 249 */       return 0.0F;
/*     */     }
/*     */     
/* 252 */     boolean baseCondition = damageableSources.stream().noneMatch(s -> source.func_76355_l().equals(s.func_76355_l()));
/* 253 */     boolean isUnavoidable = ((source instanceof ModDamageSource && ((ModDamageSource)source).isUnavoidable()) || (source instanceof EntityDamageSource && ((EntityDamageSource)source).func_180139_w()));
/*     */     
/* 255 */     if (getInvulnerabilityTimer() > 0) {
/* 256 */       return 0.0F;
/*     */     }
/*     */     
/* 259 */     if (baseCondition && !isUnavoidable && getInvulnerabilityTimer() == 0) {
/* 260 */       this.skinOverlayComponent.showAll(entity);
/*     */       
/* 262 */       reduceProtection(entity, damage);
/*     */       
/* 264 */       hakiProps.alterHakiOveruse(hakiOveruse * 4 + (int)(damage * hakiOveruse));
/*     */       
/* 266 */       return 0.0F;
/*     */     } 
/*     */     
/* 269 */     return damage;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 273 */     IAbilityData abilityProps = AbilityDataCapability.get(user);
/* 274 */     IHakiData props = HakiDataCapability.get(user);
/* 275 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*     */     
/* 277 */     boolean hasAuraUnlocked = abilityProps.hasUnlockedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
/*     */     
/* 279 */     return (hasAuraUnlocked && statsProps.getDoriki() > 6000.0D && props.getKenbunshokuHakiExp() > HakiHelper.getKenbunshokuFutureSightExpNeeded(user));
/*     */   }
/*     */   
/*     */   public int getInvulnerabilityTimer() {
/* 283 */     return this.invulnerabilityTimer;
/*     */   }
/*     */   
/*     */   public int getProtectionStacks() {
/* 287 */     return this.stackComponent.getStacks();
/*     */   }
/*     */   
/*     */   public int getMaxProtectionStacks() {
/* 291 */     return this.stackComponent.getDefaultStacks();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\KenbunshokuHakiFutureSightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */