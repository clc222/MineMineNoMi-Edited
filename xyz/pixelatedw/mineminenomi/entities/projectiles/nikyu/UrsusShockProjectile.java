/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.nikyu.UrsusShockAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class UrsusShockProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*  36 */   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(UrsusShockProjectile.class, DataSerializers.field_187193_c);
/*  37 */   private static final DataParameter<Boolean> FINISHED = EntityDataManager.func_187226_a(UrsusShockProjectile.class, DataSerializers.field_187198_h);
/*     */   
/*  39 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE })).build();
/*     */   
/*  41 */   public float multiplier = 0.0F;
/*     */   
/*     */   public UrsusShockProjectile(EntityType type, World world) {
/*  44 */     super(type, world);
/*     */   }
/*     */   
/*     */   public UrsusShockProjectile(World world, LivingEntity player) {
/*  48 */     super((EntityType)NikyuProjectiles.URSUS_SHOCK.get(), world, player, UrsusShockAbility.INSTANCE);
/*     */     
/*  50 */     setDamage(15.0F);
/*  51 */     setMaxLife(400);
/*  52 */     setArmorPiercing(1.0F);
/*  53 */     setCanGetStuckInGround();
/*     */     
/*  55 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  60 */     super.func_70071_h_();
/*  61 */     this.field_70158_ak = true;
/*  62 */     if (isFinished()) {
/*  63 */       setSize(Math.min(getSize() + 0.5F, 50.0F));
/*  64 */       AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*  65 */       func_70634_a((func_213303_ch()).field_72450_a, (func_213303_ch()).field_72448_b, (func_213303_ch()).field_72449_c);
/*  66 */       func_70101_b(90.0F, 0.0F);
/*  67 */       this.field_70127_C = this.field_70125_A;
/*  68 */       this.field_70126_B = this.field_70177_z;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  73 */     if (isFinished()) {
/*     */       return;
/*     */     }
/*  76 */     this.field_70170_p.func_184133_a(null, func_233580_cy_(), (SoundEvent)ModSounds.PAD_HO_SFX.get(), SoundCategory.PLAYERS, 10.0F, 0.25F);
/*     */     
/*  78 */     AbilityHelper.createSphere(this.field_70170_p, func_233580_cy_(), 55, 5, false, Blocks.field_150350_a, 2, GRIEF_RULE);
/*     */     
/*  80 */     List<LivingEntity> damageList = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 13.75D, ModEntityPredicates.getEnemyFactions(getThrower()));
/*  81 */     List<LivingEntity> knockbackList = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 22.0D, ModEntityPredicates.getEnemyFactions(getThrower()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     ModDamageSource shockwaveSource = (new ModIndirectEntityDamageSource((getDamageSource()).field_76373_n, (Entity)this, (Entity)getThrower())).setSourceElement(SourceElement.SHOCKWAVE).setHakiNature(SourceHakiNature.IMBUING).setSourceTypes(new ArrayList(Arrays.asList((Object[])new SourceType[] { SourceType.INTERNAL }))).setUnavoidable().setPiercing(1.0F);
/*     */     
/*  90 */     for (LivingEntity target : damageList) {
/*  91 */       target.field_70737_aN = target.field_70172_ad = 0;
/*     */       
/*  93 */       target.func_70097_a((DamageSource)shockwaveSource, 85.0F);
/*     */     } 
/*     */     
/*  96 */     for (LivingEntity target : knockbackList) {
/*  97 */       Vector3d speed = target.func_70040_Z().func_216372_d(-1.0D, -1.0D, -1.0D).func_216372_d(1.0D, 0.0D, 1.0D);
/*     */       
/*  99 */       AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 0.25D, speed.field_72449_c);
/*     */     } 
/*     */     
/* 102 */     setFinished();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70088_a() {
/* 107 */     super.func_70088_a();
/* 108 */     this.field_70180_af.func_187214_a(SIZE, Float.valueOf(0.0F));
/* 109 */     this.field_70180_af.func_187214_a(FINISHED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 114 */     this.field_70180_af.func_187227_b(SIZE, Float.valueOf(size));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 119 */     return ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue();
/*     */   }
/*     */   
/*     */   public boolean isFinished() {
/* 123 */     return ((Boolean)this.field_70180_af.func_187225_a(FINISHED)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setFinished() {
/* 127 */     this.field_70180_af.func_187227_b(FINISHED, Boolean.valueOf(true));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\UrsusShockProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */