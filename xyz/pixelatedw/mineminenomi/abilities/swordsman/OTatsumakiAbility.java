/*     */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class OTatsumakiAbility extends Ability {
/*  46 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "o_tatsumaki", new Pair[] {
/*  47 */         (Pair)ImmutablePair.of("By spinning, the user creates a small tornado, which slashes and weakens nearby opponents", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 60;
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float DAMAGE = 30.0F;
/*     */   private static final float RANGE = 5.5F;
/*  54 */   private final Interval damageInterval = new Interval(15);
/*     */   
/*  56 */   public static final AbilityCore<OTatsumakiAbility> INSTANCE = (new AbilityCore.Builder("O Tatsumaki", AbilityCategory.STYLE, OTatsumakiAbility::new))
/*  57 */     .addDescriptionLine(DESCRIPTION)
/*  58 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(60.0F), DealDamageComponent.getTooltip(30.0F), RangeComponent.getTooltip(5.5F, RangeComponent.RangeType.AOE)
/*  59 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  60 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  61 */       }).setUnlockCheck(OTatsumakiAbility::canUnlock)
/*  62 */     .build();
/*     */   
/*  64 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::onStartContinuousEvent).addTickEvent(100, this::onTickContinuousEvent).addEndEvent(100, this::onEndContinuousEvent);
/*  65 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  66 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  67 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public OTatsumakiAbility(AbilityCore<OTatsumakiAbility> core) {
/*  70 */     super(core);
/*     */     
/*  72 */     this.isNew = true;
/*     */     
/*  74 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  76 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*  77 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  81 */     this.continuousComponent.triggerContinuity(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void onStartContinuousEvent(LivingEntity entity, IAbility ability) {
/*  85 */     ItemStack stack = entity.func_184614_ca();
/*  86 */     stack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */     
/*  88 */     this.damageInterval.restartIntervalToZero();
/*  89 */     this.animationComponent.start(entity, ModAnimations.BODY_ROTATION_WIDE_ARMS);
/*  90 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.SPIN.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*     */   }
/*     */   
/*     */   private void onEndContinuousEvent(LivingEntity entity, IAbility ability) {
/*  94 */     this.animationComponent.stop(entity);
/*  95 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private void onTickContinuousEvent(LivingEntity entity, IAbility ability) {
/*  99 */     if (this.damageInterval.canTick()) {
/* 100 */       List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, 5.5F);
/*     */       
/* 102 */       AbilityDamageSource source = (AbilityDamageSource)((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).setSlash();
/*     */       
/* 104 */       for (LivingEntity target : list) {
/* 105 */         this.dealDamageComponent.hurtTarget(entity, target, 30.0F, (DamageSource)source);
/*     */         
/* 107 */         if (!entity.field_70170_p.field_72995_K) {
/* 108 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_() + target.func_70047_e(), target.func_226281_cx_());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 5, 1, false, false));
/*     */     
/* 115 */     if (!entity.field_70170_p.field_72995_K) {
/* 116 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.O_TATSUMAKI.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 117 */       if (this.continuousComponent.getContinueTime() % 5.0F == 0.0F) {
/* 118 */         entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.SPIN.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 124 */     if (!(entity instanceof PlayerEntity)) {
/* 125 */       return false;
/*     */     }
/*     */     
/* 128 */     PlayerEntity player = (PlayerEntity)entity;
/* 129 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 130 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 132 */     return (props.isSwordsman() && questProps.hasFinishedQuest(ModQuests.SWORDSMAN_TRIAL_04));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\OTatsumakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */