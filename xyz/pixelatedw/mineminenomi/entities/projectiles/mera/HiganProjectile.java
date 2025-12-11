/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HiganProjectile extends AbilityProjectileEntity {
/*    */   public HiganProjectile(EntityType<Entity> type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */   
/*    */   public HiganProjectile(World world, LivingEntity thrower, Ability ability) {
/* 28 */     super((EntityType)MeraProjectiles.HIGAN.get(), world, thrower, ability.getCore());
/*    */     
/* 30 */     setDamage(10.0F);
/* 31 */     setArmorPiercing(0.75F);
/*    */     
/* 33 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 34 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 35 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 39 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 4, getThrower());
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 43 */     BlockState state = this.field_70170_p.func_180495_p(hit);
/*    */     
/* 45 */     if (state.func_177230_c() == Blocks.field_222433_lV) {
/* 46 */       this.field_70170_p.func_180501_a(hit, (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208190_q, Boolean.valueOf(true)), 11);
/*    */     } else {
/* 48 */       AbilityHelper.placeBlockIfAllowed(getThrower(), hit.func_177982_a(0, 1, 0), Blocks.field_150480_ab.func_176223_P(), AirBlockProtectionRule.INSTANCE);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 53 */     if (func_208600_a((ITag)FluidTags.field_206959_a)) {
/* 54 */       func_70106_y();
/*    */       
/* 56 */       func_130014_f_().func_195594_a((IParticleData)ParticleTypes.field_197601_L, func_226277_ct_(), func_226278_cu_() + 1.1D, func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 59 */     if (!this.field_70170_p.field_72995_K)
/* 60 */       for (int i = 0; i < 2; i++) {
/* 61 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 62 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 63 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */         
/* 65 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */         
/* 67 */         data.setLife(10);
/* 68 */         data.setSize(1.3F);
/*    */         
/* 70 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + 0.25D + offsetY, func_226281_cx_() + offsetZ);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\HiganProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */