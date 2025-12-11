/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.HookGrabAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ExtraProjectiles;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HookEntity extends AbilityProjectileEntity {
/*    */   public HookEntity(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */   private IDevilFruit props;
/*    */   public HookEntity(World world, LivingEntity thrower) {
/* 25 */     super((EntityType)ExtraProjectiles.HOOK.get(), world, thrower, HookGrabAbility.INSTANCE);
/*    */     
/* 27 */     setMaxLife(50);
/* 28 */     setEntityCollisionSize(1.0D);
/*    */     
/* 30 */     this.props = DevilFruitCapability.get(thrower);
/*    */     
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 36 */     if (!this.field_70170_p.field_72995_K)
/* 37 */       for (int i = 0; i < 10; i++) {
/* 38 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 39 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 40 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 42 */         SimpleParticleData part = null;
/*    */         
/* 44 */         if (this.props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI)) {
/* 45 */           part = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 46 */           part.setSize(1.5F);
/*    */         }
/* 48 */         else if (this.props.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI)) {
/* 49 */           part = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 50 */           part.setSize(1.3F);
/*    */         } 
/*    */         
/* 53 */         if (part != null) {
/* 54 */           part.setLife(20);
/* 55 */           part.setRotation(0.0F, 0.0F, 1.0F);
/*    */           
/* 57 */           WyHelper.spawnParticles((IParticleData)part, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\HookEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */