/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertGrandeEspadaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DesertGrandeEspadaAbility extends Ability {
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "desert_grande_espada", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Spawns a large sand blade cutting through multiple enemies wherever the user is looking.", null), 
/*  35 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s and the damage is increased by %s.", new Object[] {
/*  36 */             "§a" + Math.round(19.999998F) + "%§r", "§a" + 
/*  37 */             Math.round(Math.abs(-0.14999998F) * 100.0F) + "%§r" }) });
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*     */   private static final float DAMAGE_BONUS = 1.15F;
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final int CHARGE_TIME = 10;
/*  42 */   public static final AbilityCore<DesertGrandeEspadaAbility> INSTANCE = (new AbilityCore.Builder("Desert Grande Espada", AbilityCategory.DEVIL_FRUITS, DesertGrandeEspadaAbility::new))
/*  43 */     .addDescriptionLine(DESCRIPTION)
/*  44 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ChargeComponent.getTooltip(10.0F)
/*     */       
/*  46 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  47 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  48 */       }).build();
/*     */   
/*  50 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  51 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   private Vector3d targetPos;
/*     */   
/*     */   public DesertGrandeEspadaAbility(AbilityCore<DesertGrandeEspadaAbility> core) {
/*  56 */     super(core);
/*     */     
/*  58 */     this.isNew = true;
/*     */     
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.chargeComponent });
/*     */     
/*  62 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  63 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*     */     
/*  65 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  69 */     this.chargeComponent.startCharging(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  73 */     if (this.targetPos == null) {
/*  74 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 64.0D);
/*  75 */       double i = (mop.func_216347_e()).field_72450_a;
/*  76 */       double k = (mop.func_216347_e()).field_72449_c;
/*  77 */       int y = entity.field_70170_p.func_201676_a(Heightmap.Type.WORLD_SURFACE, (int)i, (int)k);
/*     */       
/*  79 */       this.targetPos = new Vector3d(i, y, k);
/*     */     } 
/*     */     
/*  82 */     if (!entity.field_70170_p.field_72995_K) {
/*  83 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SAND_BLADE_IDLE.get(), (Entity)entity, this.targetPos.field_72450_a, this.targetPos.field_72448_b, this.targetPos.field_72449_c);
/*     */     }
/*     */     
/*  86 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  90 */     if (!entity.field_70170_p.field_72995_K) {
/*  91 */       DesertGrandeEspadaProjectile pillar = (DesertGrandeEspadaProjectile)this.projectileComponent.getNewProjectile(entity);
/*  92 */       pillar.func_70012_b(this.targetPos.field_72450_a, this.targetPos.field_72448_b - 6.0D, this.targetPos.field_72449_c, 0.0F, 0.0F);
/*  93 */       pillar.func_70186_c(0.0D, 0.7D, 0.0D, 1.4F, 0.0F);
/*  94 */       entity.field_70170_p.func_217376_c((Entity)pillar);
/*     */     } 
/*     */     
/*  97 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/*  98 */     if (SunaHelper.isFruitBoosted(entity)) {
/*  99 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 102 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */     
/* 104 */     this.targetPos = null;
/*     */   }
/*     */   
/*     */   private DesertGrandeEspadaProjectile createProjectile(LivingEntity entity) {
/* 108 */     boolean fruitBoosted = SunaHelper.isFruitBoosted(entity);
/*     */     
/* 110 */     this.projectileComponent.getDamageBonusManager().removeBonus(SunaHelper.DESERT_DAMAGE_BONUS);
/* 111 */     if (fruitBoosted) {
/* 112 */       this.projectileComponent.getDamageBonusManager().addBonus(SunaHelper.DESERT_DAMAGE_BONUS, "Desert Damage Bonus", BonusOperation.MUL, 1.15F);
/*     */     }
/*     */     
/* 115 */     DesertGrandeEspadaProjectile proj = new DesertGrandeEspadaProjectile(entity.field_70170_p, entity);
/* 116 */     return proj;
/*     */   }
/*     */   
/*     */   public void setTargetPos(Vector3d vec) {
/* 120 */     this.targetPos = vec;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertGrandeEspadaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */