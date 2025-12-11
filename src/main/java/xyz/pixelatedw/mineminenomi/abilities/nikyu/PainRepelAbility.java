/*     */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PainEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SPinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class PainRepelAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "pain_repel", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Extracts all the damage their target has suffered condensing it into a small paw-shaped ball.", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 1200.0F;
/*  37 */   public static final AbilityCore<PainRepelAbility> INSTANCE = (new AbilityCore.Builder("Pain Repel", AbilityCategory.DEVIL_FRUITS, PainRepelAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1200.0F)
/*  40 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  41 */     .build();
/*  42 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*  43 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(100, this::tryHitEvent);
/*  44 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(0, this::onSwing);
/*     */   
/*  46 */   private Interval painAddInterval = new Interval(10);
/*     */   private LivingEntity target;
/*  48 */   private State state = State.IDLE;
/*  49 */   private float pain = 0.0F;
/*  50 */   private float initialTargetHealthDiff = 0.0F;
/*     */   private boolean pinnedCamera = false;
/*     */   
/*     */   public PainRepelAbility(AbilityCore<PainRepelAbility> core) {
/*  54 */     super(core);
/*     */     
/*  56 */     this.isNew = true;
/*     */     
/*  58 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*     */     
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.swingTriggerComponent });
/*     */     
/*  62 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  66 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  70 */     if (target.func_110143_aJ() >= target.func_110138_aP()) {
/*  71 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  74 */     if (AbilityHelper.isTargetBlocking(entity, target)) {
/*  75 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  78 */     if (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b() && this.state == State.IDLE) {
/*  79 */       this.painAddInterval.restartIntervalToZero();
/*     */       
/*  81 */       this.state = State.HEALING;
/*     */       
/*  83 */       this.target = target;
/*     */       
/*  85 */       this.initialTargetHealthDiff = target.func_110138_aP() - target.func_110143_aJ();
/*     */       
/*  87 */       return HitTriggerComponent.HitResult.FAIL;
/*     */     } 
/*     */     
/*  90 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */   
/*     */   private void onSwing(LivingEntity entity, IAbility ability) {
/*  94 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     if (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b() && this.state == State.READY) {
/*  99 */       PainEntity proj = new PainEntity(entity.field_70170_p, entity);
/*     */       
/* 101 */       proj.setDamage(this.pain);
/* 102 */       proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/*     */       
/* 104 */       entity.field_70170_p.func_217376_c((Entity)proj);
/*     */       
/* 106 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 111 */     if (this.state == State.IDLE) {
/*     */       return;
/*     */     }
/*     */     
/* 115 */     if (this.target != null && this.state == State.HEALING) {
/* 116 */       if (!this.pinnedCamera && this.target instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 117 */         WyNetwork.sendTo(SPinCameraPacket.pinFixed(), (PlayerEntity)this.target);
/* 118 */         this.pinnedCamera = true;
/*     */       } 
/*     */       
/* 121 */       this.target.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */       
/* 123 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */       
/* 125 */       if (this.painAddInterval.canTick()) {
/* 126 */         float extraPain = this.target.func_110138_aP() / 10.0F;
/*     */         
/* 128 */         this.pain += extraPain;
/*     */         
/* 130 */         this.target.func_70691_i(extraPain);
/*     */         
/* 132 */         entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PAD_HO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */       } 
/*     */       
/* 135 */       float diff = this.target.func_110138_aP() - this.target.func_110143_aJ();
/*     */       
/* 137 */       if ((diff <= 0.0F || this.pain >= this.initialTargetHealthDiff) && !entity.field_82175_bq) {
/* 138 */         this.state = State.READY;
/*     */         
/* 140 */         if (this.target instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 141 */           WyNetwork.sendTo(new SUnpinCameraPacket(), (PlayerEntity)this.target);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 148 */     this.target = null;
/*     */     
/* 150 */     this.state = State.IDLE;
/*     */     
/* 152 */     this.pain = 0.0F;
/* 153 */     this.initialTargetHealthDiff = 0.0F;
/* 154 */     this.pinnedCamera = false;
/*     */     
/* 156 */     this.cooldownComponent.startCooldown(entity, 1200.0F);
/*     */   }
/*     */   
/*     */   private enum State {
/* 160 */     IDLE, HEALING, READY;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\PainRepelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */