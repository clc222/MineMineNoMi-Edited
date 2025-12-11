/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KingPunchProjectile extends AbilityProjectileEntity {
/*    */   public KingPunchProjectile(EntityType<Entity> type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */   
/*    */   public KingPunchProjectile(World world, LivingEntity player, Ability ability) {
/* 19 */     super((EntityType)BrawlerProjectiles.KING_PUNCH.get(), world, player, ability.getCore());
/*    */     
/* 21 */     setEntityCollisionSize(4.0D);
/* 22 */     setMaxLife(30);
/* 23 */     setPassThroughEntities();
/* 24 */     setCanGetStuckInGround();
/* 25 */     setArmorPiercing(0.5F);
/*    */     
/* 27 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 31 */     if (!this.field_70170_p.field_72995_K)
/* 32 */       for (int i = 0; i < 20; i++) {
/* 33 */         double offsetX = WyHelper.randomDouble() / 1.25D;
/* 34 */         double offsetY = WyHelper.randomDouble() / 1.25D;
/* 35 */         double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */         
/* 37 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197598_I, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 38 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197626_s, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\brawler\KingPunchProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */