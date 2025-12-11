/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WhiteSnakeProjectile extends AbilityProjectileEntity {
/*    */   public WhiteSnakeProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteSnakeProjectile(World world, LivingEntity player, Ability ability) {
/* 33 */     super((EntityType)MokuProjectiles.WHITE_SNAKE.get(), world, player, ability);
/*    */     
/* 35 */     setDamage(25.0F);
/* 36 */     setMaxLife(40);
/*    */     
/* 38 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76436_u, 300, 0) });
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   private Optional<LivingEntity> target;
/*    */   
/*    */   private void onTickEvent() {
/* 47 */     if (this.target == null || !this.target.isPresent() || !((LivingEntity)this.target.get()).func_70089_S() || ((LivingEntity)this.target.get()).func_213303_ch().func_72436_e(func_213303_ch()) > 15.0D) {
/*    */       
/* 49 */       List<LivingEntity> list = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 5.0D, ModEntityPredicates.getEnemyFactions(getThrower()));
/* 50 */       list.remove(getThrower());
/* 51 */       list.sort(MobsHelper.ENTITY_THREAT);
/* 52 */       if (list.size() > 0) {
/* 53 */         this.target = list.stream().findAny();
/*    */       }
/*    */     } else {
/*    */       
/* 57 */       Vector3d dist = func_213303_ch().func_178788_d(((LivingEntity)this.target.get()).func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D);
/* 58 */       double speedReduction = 12.0D;
/* 59 */       double speed = 0.6D;
/* 60 */       double xSpeed = Math.min(speed, -dist.field_72450_a / speedReduction);
/* 61 */       double ySpeed = Math.min(speed, -dist.field_72448_b / speedReduction);
/* 62 */       double zSpeed = Math.min(speed, -dist.field_72449_c / speedReduction);
/* 63 */       AbilityHelper.setDeltaMovement((Entity)this, xSpeed, ySpeed, zSpeed);
/*    */     } 
/*    */     
/* 66 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 68 */       for (int i = 0; i < 40; i++) {
/*    */         
/* 70 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 71 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 72 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 74 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU2.get());
/* 75 */         data.setLife(10);
/* 76 */         data.setSize((float)(WyHelper.randomDouble() * 4.0D));
/* 77 */         data.setColor(0.8F, 0.8F, 0.8F);
/* 78 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteSnakeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */