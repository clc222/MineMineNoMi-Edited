/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WhiteBlowProjectile extends AbilityProjectileEntity {
/*    */   public WhiteBlowProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteBlowProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)MokuProjectiles.WHITE_OUT.get(), world, player, ability);
/*    */     
/* 26 */     setDamage(10.0F);
/* 27 */     setMaxLife(128);
/*    */     
/* 29 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance((Effect)ModEffects.SMOKE.get(), 500, 0) });
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 38 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 40 */       for (int i = 0; i < 10; i++) {
/*    */         
/* 42 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 43 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 44 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 46 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 47 */         data.setLife(10);
/* 48 */         data.setSize(1.3F);
/* 49 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteBlowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */