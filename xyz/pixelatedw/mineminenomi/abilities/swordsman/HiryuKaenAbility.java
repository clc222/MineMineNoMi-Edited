/*     */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HiryuKaenAbility extends DropHitAbility {
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final float RANGE = 4.5F;
/*     */   private static final float DAMAGE = 20.0F;
/*  43 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hiryu_kaen", new Pair[] {
/*  44 */         (Pair)ImmutablePair.of("The user leaps into the air and releases a big flaming shockwave slash when landing", null)
/*     */       });
/*  46 */   public static final AbilityCore<HiryuKaenAbility> INSTANCE = (new AbilityCore.Builder("Hiryu: Kaen", AbilityCategory.STYLE, HiryuKaenAbility::new))
/*  47 */     .addDescriptionLine(DESCRIPTION)
/*  48 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), DealDamageComponent.getTooltip(20.0F), RangeComponent.getTooltip(4.5F, RangeComponent.RangeType.AOE)
/*  49 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  50 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  51 */       }).setSourceElement(SourceElement.FIRE)
/*  52 */     .setUnlockCheck(HiryuKaenAbility::canUnlock)
/*  53 */     .build();
/*     */   
/*  55 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  56 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   public HiryuKaenAbility(AbilityCore<HiryuKaenAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent });
/*  62 */     this.continuousComponent.addStartEvent(100, this::onStartContinuityEvent);
/*  63 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*     */     
/*  65 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/*  70 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 4.5F);
/*     */     
/*  72 */     targets.remove(entity);
/*     */     
/*  74 */     AbilityDamageSource source = (AbilityDamageSource)ModDamageSource.causeAbilityDamage(entity, getCore()).setSlash();
/*     */     
/*  76 */     for (LivingEntity target : targets) {
/*  77 */       if (this.hitTrackerComponent.canHit((Entity)target) && entity.func_70685_l((Entity)target) && 
/*  78 */         this.dealDamageComponent.hurtTarget(entity, target, 20.0F, (DamageSource)source)) {
/*  79 */         target.func_70015_d(4);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  84 */     if (!entity.field_70170_p.field_72995_K) {
/*  85 */       if (targets.size() > 0) {
/*  86 */         ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */       }
/*  88 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HIRYU_KAEN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  93 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private void onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/*  97 */     Vector3d speed = WyHelper.propulsion(entity, 1.0D, 1.0D);
/*  98 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 1.3D, speed.field_72449_c);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 102 */     if (!(entity instanceof PlayerEntity)) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     PlayerEntity player = (PlayerEntity)entity;
/* 107 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 108 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 110 */     return (props.isSwordsman() && questProps.hasFinishedQuest(ModQuests.SWORDSMAN_TRIAL_05));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\HiryuKaenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */