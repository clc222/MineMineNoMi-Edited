/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
/*    */ 
/*    */ public class GomuGomuNoJetPistolProjectile extends AbilityProjectileEntity {
/* 14 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetPistolProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetPistolProjectile(World world, LivingEntity player, Ability ability) {
/* 23 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL.get(), world, player, ability.getCore());
/*    */     
/* 25 */     setDamage(10.0F);
/* 26 */     setMaxLife(9);
/* 27 */     setFist();
/* 28 */     setHurtTime(10);
/*    */     
/* 30 */     setEntityCollisionSize(1.0D);
/*    */     
/* 32 */     setDamageSource(getDamageSource().setSourceElement(SourceElement.RUBBER));
/*    */     
/* 34 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 39 */     if (this.field_70173_aa % 2 == 0)
/* 40 */       PARTICLES.spawn(this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 0.0D, 0.0D, 0.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoJetPistolProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */