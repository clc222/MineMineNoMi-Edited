/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SparkStepAbility extends Ability {
/*  41 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/spark_step.png");
/*  42 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/spark_step.png");
/*     */   
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spark_step", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Teleport the user to their desired location", null) });
/*     */   private static final int COOLDOWN = 40;
/*     */   private static final float MAX_TELEPORT_DISTANCE = 100.0F;
/*     */   private static final float SAFE_TELEPORT_DISTANCE = 64.0F;
/*     */   private static final AbilityDescriptionLine.IDescriptionLine TELEPORT_TOOLTIP;
/*  50 */   private static final Color COLOR = WyHelper.hexToRGB("#F0EC7155");
/*     */   static {
/*  52 */     TELEPORT_TOOLTIP = ((entity, ability) -> {
/*     */         AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_TELEPORT_DISTANCE, 64.0F)).withUnit((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_BLOCKS);
/*     */         return statBuilder.build().getStatDescription();
/*     */       });
/*     */   }
/*  57 */   public static final AbilityCore<SparkStepAbility> INSTANCE = (new AbilityCore.Builder("Spark Step", AbilityCategory.DEVIL_FRUITS, SparkStepAbility::new))
/*  58 */     .addDescriptionLine(DESCRIPTION)
/*  59 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F), TELEPORT_TOOLTIP
/*  60 */       }).setIcon(DEFAULT_ICON)
/*  61 */     .build();
/*     */   
/*  63 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  64 */     .addStartEvent(this::onChargeStart)
/*  65 */     .addTickEvent(this::onChargeTick)
/*  66 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  68 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*     */   
/*     */   private LightningEntity bolt;
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   private boolean isCancelled = false;
/*     */   
/*     */   public SparkStepAbility(AbilityCore<SparkStepAbility> core) {
/*  77 */     super(core);
/*     */     
/*  79 */     setDisplayIcon(DEFAULT_ICON);
/*  80 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  81 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */     
/*  84 */     this.isNew = true;
/*     */     
/*  86 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.damageTakenComponent });
/*     */     
/*  88 */     addUseEvent(this::onUseEvent);
/*  89 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/*  93 */     setDisplayIcon(DEFAULT_ICON);
/*  94 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  95 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 100 */     if (!this.chargeComponent.isCharging()) {
/* 101 */       if (AbilityHelper.canUseMomentumAbilities(entity)) {
/* 102 */         this.chargeComponent.startCharging(entity, 30.0F);
/*     */       }
/*     */     } else {
/* 105 */       this.chargeComponent.stopCharging(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 110 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     this.hasFallDamage = false;
/* 115 */     this.isCancelled = false;
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 119 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 123 */     BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)entity, 1.0D);
/*     */     
/* 125 */     if (this.bolt == null) {
/* 126 */       Direction dir = Direction.func_176733_a(entity.field_70177_z);
/*     */       
/* 128 */       Vector3d hitVec = trace.func_216347_e().func_72441_c(dir.func_82601_c(), dir.func_96559_d(), dir.func_82599_e());
/*     */       
/* 130 */       this
/* 131 */         .bolt = new LightningEntity((Entity)entity, hitVec.field_72450_a, hitVec.field_72448_b, hitVec.field_72449_c, entity.field_70177_z, entity.field_70125_A, 100.0F, 30.0F, getCore());
/* 132 */       this.bolt.setColor(COLOR);
/* 133 */       this.bolt.setMaxLife((int)(this.chargeComponent.getMaxChargeTime() * 1.2F));
/* 134 */       this.bolt.setDamage(0.0F);
/* 135 */       this.bolt.setSize(0.1F);
/* 136 */       this.bolt.setBranches(8);
/* 137 */       this.bolt.setSegments(1);
/* 138 */       this.bolt.setLightningMimic(false);
/*     */       
/* 140 */       entity.field_70170_p.func_217376_c((Entity)this.bolt);
/*     */     } else {
/* 142 */       Direction dir = Direction.func_176733_a(entity.field_70177_z);
/*     */       
/* 144 */       Vector3d hitVec = trace.func_216347_e().func_72441_c(dir.func_82601_c(), dir.func_96559_d(), dir.func_82599_e());
/*     */       
/* 146 */       this.bolt.func_70012_b(hitVec.field_72450_a, hitVec.field_72448_b, hitVec.field_72449_c, entity.field_70177_z, entity.field_70125_A);
/*     */     } 
/*     */     
/* 149 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */     
/* 151 */     AbilityHelper.slowEntityFall(entity, 2);
/*     */     
/* 153 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GENERIC_YELLOW_LIGHTNING_USE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 157 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     if (this.bolt != null) {
/* 162 */       this.bolt.func_70106_y();
/*     */       
/* 164 */       this.bolt = null;
/*     */     } 
/*     */     
/* 167 */     if (!this.isCancelled) {
/* 168 */       BlockPos blockpos; float maxDistance = Math.max(25.0F, 100.0F - 100.0F * (1.0F - entity.func_110143_aJ() / entity.func_110138_aP()) * 2.0F);
/* 169 */       float safeDistance = Math.max(16.0F, 64.0F - 64.0F * (1.0F - entity.func_110143_aJ() / entity.func_110138_aP()) * 2.0F);
/*     */       
/* 171 */       BlockRayTraceResult mop = WyHelper.rayTraceBlocks((Entity)entity, maxDistance);
/*     */ 
/*     */ 
/*     */       
/* 175 */       if (mop == null || mop.func_216346_c() == RayTraceResult.Type.MISS) {
/* 176 */         blockpos = WyHelper.rayTraceBlockSafe(entity, safeDistance);
/*     */       } else {
/* 178 */         blockpos = WyHelper.getClearPositionForPlayer(entity, mop.func_216350_a());
/*     */       } 
/*     */       
/* 181 */       if (blockpos == null) {
/* 182 */         blockpos = WyHelper.rayTraceBlockSafe(entity, safeDistance);
/*     */       }
/*     */       
/* 185 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GENERIC_YELLOW_LIGHTNING_USE.get(), (Entity)entity, blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/*     */       
/* 187 */       entity.func_184210_p();
/* 188 */       entity.func_223102_j(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/*     */       
/* 190 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.LIGHTNING_TELEPORT.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 191 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     } 
/*     */     
/* 194 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 198 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 199 */       this.hasFallDamage = true;
/*     */       
/* 201 */       return 0.0F;
/*     */     } 
/*     */     
/* 204 */     if (damage >= 5.0F) {
/* 205 */       if (this.chargeComponent.isCharging()) {
/* 206 */         this.isCancelled = true;
/*     */         
/* 208 */         this.chargeComponent.stopCharging(entity);
/*     */       } else {
/* 210 */         this.disableComponent.startDisable(entity, 40.0F);
/*     */       } 
/*     */     }
/*     */     
/* 214 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 219 */     nbt = super.save(nbt);
/* 220 */     nbt.func_74757_a("hasFallDamage", this.hasFallDamage);
/*     */     
/* 222 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 227 */     super.load(nbt);
/*     */     
/* 229 */     this.hasFallDamage = nbt.func_74767_n("hasFallDamage");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\SparkStepAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */