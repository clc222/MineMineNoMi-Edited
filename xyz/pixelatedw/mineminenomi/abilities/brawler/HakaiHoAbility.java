/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class HakaiHoAbility extends PunchAbility2 {
/*  40 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hakai_ho", new Pair[] {
/*  41 */         (Pair)ImmutablePair.of("The user punches with enough force to create a small explosion damaging all nearby enemies.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int RANGE = 2;
/*     */   private static final int DAMAGE = 30;
/*  47 */   public static final AbilityCore<HakaiHoAbility> INSTANCE = (new AbilityCore.Builder("Hakai Ho", AbilityCategory.STYLE, HakaiHoAbility::new))
/*  48 */     .addDescriptionLine(DESCRIPTION)
/*  49 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip(), RangeComponent.getTooltip(2.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(30.0F)
/*  50 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  51 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  52 */       }).setUnlockCheck(HakaiHoAbility::canUnlock)
/*  53 */     .build();
/*     */   
/*  55 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  56 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public HakaiHoAbility(AbilityCore<HakaiHoAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  63 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  68 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.0F);
/*     */     
/*  70 */     targets.remove(entity);
/*  71 */     targets.remove(target);
/*     */     
/*  73 */     Vector3d dir = entity.func_70040_Z().func_216372_d(5.0D, 0.0D, 5.0D);
/*     */     
/*  75 */     ModDamageSource newSource = ((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).ignore();
/*  76 */     for (LivingEntity aoeTarget : targets) {
/*  77 */       if (this.dealDamageComponent.hurtTarget(entity, aoeTarget, 30.0F, (DamageSource)newSource)) {
/*  78 */         aoeTarget.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 60, 0, false, false));
/*     */         
/*  80 */         AbilityHelper.setDeltaMovement((Entity)aoeTarget, aoeTarget.func_213322_ci().func_72441_c(dir.field_72450_a, 0.2D, dir.field_72449_c));
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 2.0F);
/*     */     
/*  86 */     explosion.setExplosionSound(true);
/*  87 */     explosion.setDamageOwner(false);
/*  88 */     explosion.setDestroyBlocks(false);
/*  89 */     explosion.setFireAfterExplosion(false);
/*  90 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*  91 */     explosion.setDamageEntities(false);
/*  92 */     explosion.doExplosion();
/*     */     
/*  94 */     AbilityHelper.disableAbilities(target, 100, abl -> (abl.hasComponent(ModAbilityKeys.POOL) && ((PoolComponent)abl.getComponent(ModAbilityKeys.POOL).get()).containsPool(ModAbilityPools.TEKKAI_LIKE)));
/*     */     
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/* 106 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 112 */     return 240.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchDamage() {
/* 117 */     return 30.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 122 */     return 1;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 126 */     if (!(entity instanceof PlayerEntity)) {
/* 127 */       return false;
/*     */     }
/*     */     
/* 130 */     PlayerEntity player = (PlayerEntity)entity;
/* 131 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 132 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 134 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_04));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\HakaiHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */