/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PoeleAFrireProjectile extends AbilityProjectileEntity {
/*    */   public PoeleAFrireProjectile(EntityType<Entity> type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */   
/*    */   public PoeleAFrireProjectile(World world, LivingEntity player, Ability abl) {
/* 25 */     super((EntityType)BlackLegProjectiles.POELE_A_FRIRE.get(), world, player, abl.getCore(), SourceElement.FIRE, SourceHakiNature.IMBUING, new ArrayList(Arrays.asList((Object[])new SourceType[] { SourceType.PROJECTILE })));
/*    */     
/* 27 */     setDamage(10.0F);
/* 28 */     setMaxLife(10);
/*    */     
/* 30 */     this.onTickEvent = this::onTickEvent;
/* 31 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 35 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.EXTRA_HACHI_DIABLE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 39 */     AbilityHelper.setSecondsOnFireBy((Entity)target, 2, getThrower());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\blackleg\PoeleAFrireProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */