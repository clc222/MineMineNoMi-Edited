/*     */ package xyz.pixelatedw.mineminenomi.abilities.buki;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchTriggerAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class BukimorphoseAbility extends PunchTriggerAbility {
/*  25 */   public static final AbilityCore<BukimorphoseAbility> INSTANCE = (new AbilityCore.Builder("Bukimorphose", AbilityCategory.DEVIL_FRUITS, BukimorphoseAbility::new))
/*  26 */     .build();
/*     */   
/*  28 */   private Mode mode = Mode.SWORD;
/*     */   boolean released = false;
/*     */   boolean exploded;
/*     */   
/*     */   public BukimorphoseAbility(AbilityCore<BukimorphoseAbility> core) {
/*  33 */     super(core);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.exploded = false; this.onStartContinuityEvent = this::onStartContinuity; this.duringContinuityEvent = this::duringContinuityEvent;
/*     */     this.beforeContinuityStopEvent = this::beforeContinuityStopEvent;
/*     */     stopAfterUsage(false);
/*     */     this.onSwingEvent = this::onSwingEvent;
/*  48 */     this.duringCooldownEvent = this::duringCooldownEvent; } private void duringCooldownEvent(PlayerEntity player, int i) { if (this.mode == Mode.MISSILE && !this.exploded) {
/*     */       
/*  50 */       float maxSpeed = 4.0F;
/*  51 */       Vector3d vec = player.func_70040_Z();
/*  52 */       AbilityHelper.setDeltaMovement((Entity)player, vec.field_72450_a * maxSpeed, vec.field_72448_b * maxSpeed, vec.field_72449_c * maxSpeed);
/*     */       
/*  54 */       if (player.field_70123_F || player.field_70124_G) {
/*     */         
/*  56 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.field_70170_p, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), 6.0F);
/*  57 */         explosion.setExplosionSound(true);
/*  58 */         explosion.setDamageOwner(false);
/*  59 */         explosion.setDestroyBlocks(true);
/*  60 */         explosion.setFireAfterExplosion(true);
/*  61 */         explosion.setStaticDamage(100.0F);
/*  62 */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/*  63 */         explosion.setDamageEntities(true);
/*  64 */         explosion.doExplosion();
/*  65 */         this.exploded = true;
/*     */       } 
/*     */     }  }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean onStartContinuity(PlayerEntity player) {
/*  72 */     this.released = false;
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onSwingEvent(PlayerEntity player) {
/*  78 */     stopAfterUsage(false);
/*  79 */     if (this.mode == Mode.FIRE) {
/*     */       
/*  81 */       FreshFireProjectile proj = new FreshFireProjectile(player.field_70170_p, (LivingEntity)player, (Ability)this);
/*  82 */       player.field_70170_p.func_217376_c((Entity)proj);
/*  83 */       proj.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 2.0F, 10.0F);
/*     */     }
/*  85 */     else if (this.mode == Mode.GATLING) {
/*     */       
/*  87 */       NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(player.field_70170_p, (LivingEntity)player);
/*  88 */       player.field_70170_p.func_217376_c((Entity)normalBulletProjectile);
/*  89 */       normalBulletProjectile.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 3.0F, 2.0F);
/*     */     }
/*  91 */     else if (this.mode == Mode.CANNON) {
/*     */       
/*  93 */       stopAfterUsage(true);
/*  94 */       CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(player.field_70170_p, (LivingEntity)player);
/*  95 */       setMaxCooldown(2.0D);
/*  96 */       player.field_70170_p.func_217376_c((Entity)cannonBallProjectile);
/*  97 */       cannonBallProjectile.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 3.0F, 2.0F);
/*     */     }
/*  99 */     else if (this.mode == Mode.MISSILE) {
/*     */       
/* 101 */       stopAfterUsage(true);
/* 102 */       setMaxCooldown(8.0D);
/* 103 */       this.exploded = false;
/*     */     } 
/*     */     
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 111 */     if (i % (player.func_226271_bk_() ? 10 : 40) == 0 && !this.released) {
/*     */       
/* 113 */       this.mode = this.mode.getNext();
/* 114 */       player.func_145747_a((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.mode), Util.field_240973_b_);
/*     */       
/* 116 */       WyNetwork.sendTo(new SSyncAbilityPacket(player.func_145782_y(), (IAbility)this), player);
/* 117 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, (Ability)this), (Entity)player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean beforeContinuityStopEvent(PlayerEntity player) {
/* 123 */     if (!this.released) {
/*     */       
/* 125 */       this.released = true;
/* 126 */       return false;
/*     */     } 
/*     */     
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Mode
/*     */   {
/* 135 */     SWORD, CANNON, GATLING, FIRE, MISSILE;
/*     */ 
/*     */     
/*     */     public Mode getNext() {
/* 139 */       return (ordinal() == Arrays.<Mode>stream(values()).count() - 1L) ? SWORD : values()[ordinal() + 1];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\buki\BukimorphoseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */