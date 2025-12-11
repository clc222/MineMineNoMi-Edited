/*     */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class YataNoKagamiAbility extends Ability {
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yata_no_kagami", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("The user forms light between his hands, and reflects it off any surface he wishes, instantly teleporting it towards where the light hit.", null) }); private static final int COOLDOWN = 60;
/*     */   private static final float MAX_TELEPORT_DISTANCE = 200.0F;
/*     */   private static final float SAFE_TELEPORT_DISTANCE = 64.0F;
/*     */   private static final AbilityDescriptionLine.IDescriptionLine TELEPORT_TOOLTIP;
/*     */   
/*     */   static {
/*  48 */     TELEPORT_TOOLTIP = ((entity, ability) -> {
/*     */         AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_TELEPORT_DISTANCE, 64.0F)).withUnit((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_BLOCKS);
/*     */         return statBuilder.build().getStatDescription();
/*     */       });
/*     */   }
/*  53 */   public static final AbilityCore<YataNoKagamiAbility> INSTANCE = (new AbilityCore.Builder("Yata no Kagami", AbilityCategory.DEVIL_FRUITS, YataNoKagamiAbility::new))
/*  54 */     .addDescriptionLine(DESCRIPTION)
/*  55 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F), TELEPORT_TOOLTIP
/*  56 */       }).build();
/*     */   
/*  58 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  59 */     .addStartEvent(this::onChargeStart)
/*  60 */     .addTickEvent(this::onChargeTick)
/*  61 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  63 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*     */   
/*  65 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   private LightningEntity bolt;
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   private boolean isCancelled = false;
/*     */   
/*     */   public YataNoKagamiAbility(AbilityCore<YataNoKagamiAbility> core) {
/*  74 */     super(core);
/*     */     
/*  76 */     this.isNew = true;
/*     */     
/*  78 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  80 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  84 */     if (!this.chargeComponent.isCharging()) {
/*  85 */       if (AbilityHelper.canUseMomentumAbilities(entity)) {
/*  86 */         this.chargeComponent.startCharging(entity, 30.0F);
/*     */       }
/*     */     } else {
/*  89 */       this.chargeComponent.stopCharging(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/*  94 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     this.hasFallDamage = false;
/*  99 */     this.isCancelled = false;
/*     */     
/* 101 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 105 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)entity, 1.0D);
/*     */     
/* 111 */     if (this.bolt == null) {
/* 112 */       Direction dir = Direction.func_176733_a(entity.field_70177_z);
/*     */       
/* 114 */       Vector3d hitVec = trace.func_216347_e().func_72441_c(dir.func_82601_c(), dir.func_96559_d(), dir.func_82599_e());
/*     */       
/* 116 */       this.bolt = new LightningEntity((Entity)entity, hitVec.field_72450_a, hitVec.field_72448_b, hitVec.field_72449_c, entity.field_70177_z, entity.field_70125_A, 200.0F, 30.0F, getCore());
/* 117 */       this.bolt.setColor(new Color(252, 255, 22, 77));
/* 118 */       this.bolt.setMaxLife((int)(this.chargeComponent.getMaxChargeTime() * 1.2F));
/* 119 */       this.bolt.setDamage(0.0F);
/* 120 */       this.bolt.setSize(0.1F);
/* 121 */       this.bolt.setBranches(8);
/* 122 */       this.bolt.setSegments(1);
/* 123 */       this.bolt.setLightningMimic(false);
/*     */       
/* 125 */       entity.field_70170_p.func_217376_c((Entity)this.bolt);
/*     */     } else {
/* 127 */       Direction dir = Direction.func_176733_a(entity.field_70177_z);
/*     */       
/* 129 */       Vector3d hitVec = trace.func_216347_e().func_72441_c(dir.func_82601_c(), dir.func_96559_d(), dir.func_82599_e());
/*     */       
/* 131 */       this.bolt.func_70012_b(hitVec.field_72450_a, hitVec.field_72448_b, hitVec.field_72449_c, entity.field_70177_z, entity.field_70125_A);
/*     */     } 
/*     */     
/* 134 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */     
/* 136 */     AbilityHelper.slowEntityFall(entity, 2);
/*     */     
/* 138 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PIKA_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 142 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 146 */     if (this.bolt != null) {
/* 147 */       this.bolt.func_70106_y();
/*     */       
/* 149 */       this.bolt = null;
/*     */     } 
/*     */     
/* 152 */     if (!this.isCancelled) {
/* 153 */       BlockPos blockpos; float maxDistance = Math.max(50.0F, 200.0F - 200.0F * (1.0F - entity.func_110143_aJ() / entity.func_110138_aP()) * 2.0F);
/* 154 */       float safeDistance = Math.max(16.0F, 64.0F - 64.0F * (1.0F - entity.func_110143_aJ() / entity.func_110138_aP()) * 2.0F);
/*     */       
/* 156 */       BlockRayTraceResult mop = WyHelper.rayTraceBlocks((Entity)entity, maxDistance);
/*     */ 
/*     */ 
/*     */       
/* 160 */       if (mop == null || mop.func_216346_c() == RayTraceResult.Type.MISS) {
/* 161 */         blockpos = WyHelper.rayTraceBlockSafe(entity, safeDistance);
/*     */       } else {
/* 163 */         blockpos = WyHelper.getClearPositionForPlayer(entity, mop.func_216350_a());
/*     */       } 
/*     */       
/* 166 */       if (blockpos == null) {
/* 167 */         blockpos = WyHelper.rayTraceBlockSafe(entity, safeDistance);
/*     */       }
/*     */       
/* 170 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.YATA_NO_KAGAMI.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 171 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FLASH.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 172 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.YATA_NO_KAGAMI.get(), (Entity)entity, blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/*     */       
/* 174 */       entity.func_184210_p();
/* 175 */       entity.func_223102_j(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/*     */       
/* 177 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PIKA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 178 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     } 
/*     */     
/* 181 */     this.animationComponent.stop(entity);
/* 182 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 186 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 187 */       this.hasFallDamage = true;
/*     */       
/* 189 */       return 0.0F;
/*     */     } 
/*     */     
/* 192 */     if (damage >= 5.0F) {
/* 193 */       if (this.chargeComponent.isCharging()) {
/* 194 */         this.isCancelled = true;
/*     */         
/* 196 */         this.chargeComponent.stopCharging(entity);
/*     */       } else {
/* 198 */         this.disableComponent.startDisable(entity, 40.0F);
/*     */       } 
/*     */     }
/*     */     
/* 202 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 207 */     nbt = super.save(nbt);
/* 208 */     nbt.func_74757_a("hasFallDamage", this.hasFallDamage);
/*     */     
/* 210 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 215 */     super.load(nbt);
/*     */     
/* 217 */     this.hasFallDamage = nbt.func_74767_n("hasFallDamage");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\YataNoKagamiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */