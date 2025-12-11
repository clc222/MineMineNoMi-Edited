/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WhiteOutProjectile extends AbilityProjectileEntity {
/*    */   public WhiteOutProjectile(EntityType<Entity> type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public WhiteOutProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)MokuProjectiles.WHITE_OUT.get(), world, player, ability.getCore());
/*    */     
/* 22 */     setDamage(0.1F);
/* 23 */     setMaxLife(30);
/* 24 */     setEntityCollisionSize(1.0D);
/*    */     
/* 26 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 30 */     if (!this.field_70170_p.field_72995_K)
/* 31 */       for (int i = 0; i < 10; i++) {
/* 32 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 33 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 34 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 36 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/*    */         
/* 38 */         data.setLife(10);
/* 39 */         data.setSize(1.3F);
/*    */         
/* 41 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteOutProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */