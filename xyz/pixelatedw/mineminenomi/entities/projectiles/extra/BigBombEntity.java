/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class BigBombEntity
/*    */   extends AbilityProjectileEntity {
/*    */   public BigBombEntity(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigBombEntity(World world, LivingEntity entity) {
/* 24 */     this(world, entity, (AbilityCore)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigBombEntity(World world, LivingEntity entity, @Nullable AbilityCore ability) {
/* 29 */     super((EntityType)ExtraProjectiles.BIG_BOMB.get(), world, entity, ability);
/* 30 */     setDamage(5.0F);
/* 31 */     setMaxLife(60);
/* 32 */     setGravity(0.1F);
/*    */     
/* 34 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 39 */     if (this.field_70173_aa < 0) {
/*    */       return;
/*    */     }
/* 42 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 5.0F);
/* 43 */     explosion.setStaticDamage(20.0F);
/* 44 */     explosion.setDestroyBlocks(true);
/* 45 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 46 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\BigBombEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */