/*     */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KasuriutaFubukiGiriAbility extends Ability {
/*  47 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kasuriuta_fubuki_giri", new Pair[] {
/*  48 */         (Pair)ImmutablePair.of("A quick slash at the enemy that also freezes them.", null)
/*     */       });
/*     */   
/*     */   private static final float MAX_TELEPORT_DISTANCE = 30.0F;
/*     */   private static final int COOLDOWN = 160;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int RANGE = 3;
/*     */   private static final int DAMAGE = 20;
/*  56 */   public static final AbilityCore<KasuriutaFubukiGiriAbility> INSTANCE = (new AbilityCore.Builder("Kasuriuta: Fubuki Giri", AbilityCategory.DEVIL_FRUITS, KasuriutaFubukiGiriAbility::new))
/*  57 */     .addDescriptionLine(DESCRIPTION)
/*  58 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ChargeComponent.getTooltip(20.0F)
/*  59 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  60 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  61 */       }).setSourceElement(SourceElement.ICE)
/*  62 */     .setUnlockCheck(KasuriutaFubukiGiriAbility::canUnlock)
/*  63 */     .build();
/*     */   
/*  65 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargingEvent).addTickEvent(this::duringChargingEvent).addEndEvent(this::endChargingEvent);
/*  66 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  67 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  68 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  69 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public KasuriutaFubukiGiriAbility(AbilityCore<KasuriutaFubukiGiriAbility> core) {
/*  72 */     super(core);
/*     */     
/*  74 */     this.isNew = true;
/*  75 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  77 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  78 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  79 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*     */     
/*  81 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  85 */     this.chargeComponent.startCharging(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void startChargingEvent(LivingEntity entity, IAbility ability) {
/*  89 */     this.hitTrackerComponent.clearHits();
/*  90 */     this.animationComponent.start(entity, ModAnimations.ITTORYU_CHARGE);
/*     */   }
/*     */   
/*     */   private void duringChargingEvent(LivingEntity entity, IAbility ability) {
/*  94 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*  95 */     AbilityHelper.slowEntityFall(entity, 15);
/*     */   }
/*     */   
/*     */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/*  99 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 103 */     ItemStack stack = entity.func_184614_ca();
/*     */     
/* 105 */     stack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */     
/* 107 */     BlockPos blockpos = WyHelper.rayTraceBlockSafe(entity, 30.0F);
/*     */     
/* 109 */     AbilityDamageSource source = (AbilityDamageSource)((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).setSlash();
/*     */     
/* 111 */     Vector3d startPos = entity.func_213303_ch();
/*     */     
/* 113 */     float actualTeleportDistance = 30.0F;
/*     */     double f;
/* 115 */     for (f = 0.0D; f < 1.0D; f += 0.13D) {
/* 116 */       double x = MathHelper.func_219803_d(f, startPos.func_82615_a(), blockpos.func_177958_n());
/* 117 */       double y = MathHelper.func_219803_d(f, startPos.func_82617_b(), blockpos.func_177956_o());
/* 118 */       double z = MathHelper.func_219803_d(f, startPos.func_82616_c(), blockpos.func_177952_p());
/*     */       
/* 120 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KASURIUTA_FUBUKI_GIRI.get(), (Entity)entity, x, y - 1.0D, z);
/*     */       
/* 122 */       Vector3d pos = new Vector3d(x, y, z);
/*     */       
/* 124 */       List<ProjectileEntity> projectiles = WyHelper.getNearbyEntities(pos, (IWorld)entity.field_70170_p, entity.func_213311_cf(), entity.func_213302_cg(), entity.func_213311_cf(), null, new Class[] { ProjectileEntity.class });
/*     */       
/* 126 */       if (!projectiles.isEmpty()) {
/* 127 */         projectiles.sort(TargetHelper.closestComparator(startPos));
/*     */         
/* 129 */         actualTeleportDistance = MathHelper.func_76133_a(((ProjectileEntity)projectiles.get(0)).func_195048_a(startPos));
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     blockpos = WyHelper.rayTraceBlockSafe(entity, actualTeleportDistance);
/*     */     
/* 137 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, actualTeleportDistance, 3.0F);
/*     */     
/* 139 */     for (LivingEntity target : targets) {
/* 140 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 141 */         boolean flag = this.dealDamageComponent.hurtTarget(entity, target, 20.0F, (DamageSource)source);
/*     */         
/* 143 */         if (flag) {
/* 144 */           EffectInstance instance = new EffectInstance((Effect)ModEffects.FROZEN.get(), 70, 0);
/*     */           
/* 146 */           target.func_195064_c(instance);
/*     */           
/* 148 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_() + target.func_70047_e(), target.func_226281_cx_());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     entity.func_184210_p();
/* 154 */     entity.func_223102_j(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/*     */     
/* 156 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*     */     
/* 158 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     
/* 160 */     this.animationComponent.stop(entity);
/* 161 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 165 */     return ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\KasuriutaFubukiGiriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */