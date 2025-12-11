/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ 
/*     */ public class TakedownKickAbility extends DropHitAbility2 {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "takedown_kick", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Jumps high in the sky and kicks down any enemies it reaches temporarily stunning them too.", null)
/*     */       });
/*  41 */   private static final UUID COOLDOWN_BONUS_UUID = UUID.fromString("11164ab3-f50d-4f6e-b2a1-3a2ac0c1c895");
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   
/*     */   private static final float RANGE = 2.5F;
/*     */   private static final float DAMAGE = 10.0F;
/*  47 */   public static final AbilityCore<TakedownKickAbility> INSTANCE = (new AbilityCore.Builder("Takedown Kick", AbilityCategory.STYLE, TakedownKickAbility::new))
/*  48 */     .addDescriptionLine(DESCRIPTION)
/*  49 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), DealDamageComponent.getTooltip(10.0F), RangeComponent.getTooltip(2.5F, RangeComponent.RangeType.AOE)
/*  50 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  51 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  52 */       }).setUnlockCheck(TakedownKickAbility::canUnlock)
/*  53 */     .build();
/*     */   
/*  55 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  56 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  57 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   private boolean isFalling;
/*     */   
/*     */   @Nullable
/*     */   private Entity target;
/*     */   
/*     */   public TakedownKickAbility(AbilityCore<TakedownKickAbility> core) {
/*  65 */     super(core);
/*     */     
/*  67 */     this.isNew = true;
/*  68 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  70 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  71 */     this.continuousComponent.addEndEvent(100, this::onContinuityEnd);
/*  72 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*     */     
/*  74 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  78 */     this.hitTrackerComponent.clearHits();
/*  79 */     this.isFalling = false;
/*  80 */     if (this.target != null) {
/*  81 */       Vector3d speed = this.target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_216372_d(7.0D, 2.5D, 7.0D);
/*  82 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/*     */     } else {
/*     */       
/*  85 */       Vector3d speed = entity.func_70040_Z().func_216372_d(4.0D, 1.0D, 4.0D);
/*  86 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 2.25D, speed.field_72449_c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  91 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)entity) > 5.0D) {
/*  92 */       boolean targetHurt = false;
/*     */       
/*  94 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.5F);
/*     */       
/*  96 */       for (LivingEntity target : targets) {
/*  97 */         if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/*  98 */           target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 20, 0, false, false));
/*  99 */           AbilityHelper.setDeltaMovement((Entity)target, (entity.func_213322_ci()).field_72450_a, -5.0D, (entity.func_213322_ci()).field_72449_c);
/* 100 */           targetHurt = true;
/*     */         } 
/*     */       } 
/*     */       
/* 104 */       if (targetHurt) {
/* 105 */         if (!this.isFalling) {
/* 106 */           AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 0.0D, 0.0D);
/* 107 */           this.isFalling = true;
/*     */         } 
/* 109 */         this.animationComponent.start(entity, ModAnimations.TAKEDOWN_KICK, 8);
/* 110 */         if (!entity.field_70170_p.field_72995_K) {
/* 111 */           ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 118 */     this.target = null;
/* 119 */     this.animationComponent.stop(entity);
/*     */     
/* 121 */     if (entity instanceof PlayerEntity) {
/* 122 */       this.cooldownComponent.getBonusManager().addBonus(COOLDOWN_BONUS_UUID, "Cooldown Bonus", BonusOperation.ADD, 360.0F);
/*     */     }
/*     */     
/* 125 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/* 130 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   public void setTarget(Entity target) {
/* 134 */     this.target = target;
/*     */   }
/*     */   
/*     */   public Entity getTarget() {
/* 138 */     return this.target;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 142 */     if (!(entity instanceof PlayerEntity)) {
/* 143 */       return false;
/*     */     }
/*     */     
/* 146 */     PlayerEntity player = (PlayerEntity)entity;
/* 147 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 149 */     return questProps.hasFinishedQuest(ModQuests.TAKEDOWN_TRIAL);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\TakedownKickAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */