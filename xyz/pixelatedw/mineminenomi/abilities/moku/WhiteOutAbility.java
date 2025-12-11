/*     */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteOutProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SPinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class WhiteOutAbility extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "white_out", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("Fires both fists at an enemy and lifts them up, moving them around according to the user's movements", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int HOLD_TIME = 60;
/*  35 */   public static final AbilityCore<WhiteOutAbility> INSTANCE = (new AbilityCore.Builder("White Out", AbilityCategory.DEVIL_FRUITS, WhiteOutAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(60.0F)
/*  38 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  39 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  40 */       }).setSourceElement(SourceElement.SMOKE)
/*  41 */     .build();
/*     */   
/*  43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  44 */     .addStartEvent(this::onContinuityStart)
/*  45 */     .addTickEvent(this::onContinuityTick)
/*  46 */     .addEndEvent(this::onContinuityEnd);
/*  47 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*  49 */   private LivingEntity grabbedEntity = null;
/*     */   private boolean pinnedCamera = false;
/*  51 */   private WhiteOutProjectile proj = null;
/*     */   
/*     */   public WhiteOutAbility(AbilityCore<WhiteOutAbility> core) {
/*  54 */     super(core);
/*     */     
/*  56 */     this.isNew = true;
/*     */     
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  60 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.continuousComponent.triggerContinuity(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  68 */     this.proj = (WhiteOutProjectile)this.projectileComponent.getNewProjectile(entity);
/*     */     
/*  70 */     this.proj.onEntityImpactEvent = (hit -> this.grabbedEntity = hit);
/*     */ 
/*     */ 
/*     */     
/*  74 */     entity.field_70170_p.func_217376_c((Entity)this.proj);
/*     */     
/*  76 */     this.proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 3.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  80 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  84 */     if ((this.proj == null || !this.proj.func_70089_S()) && this.grabbedEntity == null) {
/*  85 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  89 */     if (this.grabbedEntity != null && (!this.grabbedEntity.func_70089_S() || AbilityHelper.isGuardBlocking(this.grabbedEntity))) {
/*  90 */       this.continuousComponent.stopContinuity(entity);
/*  91 */     } else if (this.grabbedEntity != null) {
/*  92 */       this.grabbedEntity.field_70177_z = this.grabbedEntity.field_70126_B;
/*  93 */       this.grabbedEntity.field_70125_A = this.grabbedEntity.field_70127_C;
/*     */       
/*  95 */       this.grabbedEntity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*  96 */       this.grabbedEntity.func_195064_c(new EffectInstance((Effect)ModEffects.SMOKE.get(), 60, 0));
/*     */       
/*  98 */       double distance = 7.0D;
/*     */       
/* 100 */       Vector3d lookVec = entity.func_70040_Z();
/* 101 */       Vector3d pos = new Vector3d(lookVec.field_72450_a * distance, entity.func_70047_e() / 2.0D + lookVec.field_72448_b * distance, lookVec.field_72449_c * distance);
/*     */       
/* 103 */       AbilityHelper.setDeltaMovement((Entity)this.grabbedEntity, entity.func_213303_ch().func_178787_e(pos).func_178788_d(this.grabbedEntity.func_213303_ch()));
/*     */       
/* 105 */       if (!this.pinnedCamera && this.grabbedEntity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 106 */         if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 107 */           WyNetwork.sendTo(SPinCameraPacket.pinFixed(), (PlayerEntity)this.grabbedEntity);
/*     */         }
/* 109 */         this.pinnedCamera = true;
/*     */       } 
/*     */       
/* 112 */       this.grabbedEntity.field_70143_R = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 117 */     this.proj = null;
/*     */     
/* 119 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 120 */       WyNetwork.sendTo(new SUnpinCameraPacket(), (PlayerEntity)this.grabbedEntity);
/*     */     }
/*     */     
/* 123 */     this.grabbedEntity = null;
/*     */     
/* 125 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private WhiteOutProjectile createProjectile(LivingEntity entity) {
/* 129 */     WhiteOutProjectile proj = new WhiteOutProjectile(entity.field_70170_p, entity, this);
/* 130 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteOutAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */