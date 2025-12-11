/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yuki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class YukiRabiProjectile extends AbilityProjectileEntity {
/*    */   public YukiRabiProjectile(EntityType<Entity> type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */   
/*    */   public YukiRabiProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)YukiProjectiles.YUKI_RABI.get(), world, player, ability.getCore());
/*    */     
/* 26 */     setDamage(3.5F);
/* 27 */     addBlockToIgnore(new Block[] { Blocks.field_150433_aE, Blocks.field_196604_cC, (Block)ModBlocks.HARDENED_SNOW.get() });
/*    */     
/* 29 */     this.onTickEvent = this::onTickEvent;
/* 30 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 35 */     AbilityHelper.addFrostbiteStacks(entity, getThrower(), 1);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 40 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 42 */       for (int i = 0; i < 2; i++) {
/*    */         ParticleType<SimpleParticleData> particle;
/* 44 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 45 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 46 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */ 
/*    */         
/* 49 */         if (i % 2 == 0) {
/* 50 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI2.get();
/*    */         } else {
/* 52 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI.get();
/*    */         } 
/* 54 */         SimpleParticleData data = new SimpleParticleData(particle);
/* 55 */         data.setLife(20);
/* 56 */         data.setSize(1.3F);
/* 57 */         data.setMotion(0.0D, -0.02D, 0.0D);
/* 58 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + 0.25D + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yuki\YukiRabiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */