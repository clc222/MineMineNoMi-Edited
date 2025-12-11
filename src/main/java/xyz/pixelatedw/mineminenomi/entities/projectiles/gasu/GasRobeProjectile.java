/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GasRobeProjectile extends AbilityProjectileEntity {
/*    */   private boolean hasShinokuniEnabled = false;
/*    */   
/*    */   public GasRobeProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */   private ShinokuniAbility shinokuniAbility;
/*    */   public GasRobeProjectile(World world, LivingEntity player, Ability ability) {
/* 25 */     super((EntityType)GasuProjectiles.GAS_ROBE.get(), world, player, ability);
/*    */     
/* 27 */     setDamage(0.1F);
/* 28 */     setMaxLife(30);
/* 29 */     setPassThroughEntities();
/* 30 */     setArmorPiercing(0.5F);
/*    */     
/* 32 */     this.hasShinokuniEnabled = ((MorphInfo)ModMorphs.SHINOKUNI.get()).isActive(player);
/* 33 */     if (this.hasShinokuniEnabled) {
/* 34 */       this.shinokuniAbility = (ShinokuniAbility)AbilityDataCapability.get(player).getEquippedAbility(ShinokuniAbility.INSTANCE);
/*    */     } else {
/*    */       
/* 37 */       this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76437_t, 200, 2), new EffectInstance(Effects.field_76440_q, 40, 0), new EffectInstance(Effects.field_76436_u, 200, 6) });
/*    */     } 
/*    */ 
/*    */     
/* 41 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 46 */     if (this.hasShinokuniEnabled)
/* 47 */       this.shinokuniAbility.applyEffects(getThrower(), entity); 
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     if (!this.field_70170_p.field_72995_K)
/* 52 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GAS_ROBE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\GasRobeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */