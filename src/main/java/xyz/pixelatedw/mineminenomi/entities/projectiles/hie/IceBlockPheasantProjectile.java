/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IceBlockPheasantProjectile extends AbilityProjectileEntity {
/* 24 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).build();
/*    */ 
/*    */   
/*    */   public IceBlockPheasantProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPheasantProjectile(World world, LivingEntity player, Ability ability) {
/* 33 */     super((EntityType)HieProjectiles.ICE_BLOCK_PHEASANT.get(), world, player, ability);
/*    */     
/* 35 */     setDamage(45.0F);
/* 36 */     setArmorPiercing(1.0F);
/* 37 */     setPassThroughEntities();
/* 38 */     setMaxLife(60);
/* 39 */     setPhysical();
/*    */     
/* 41 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 46 */     AbilityHelper.addFrostbiteStacks(entity, getThrower(), 6);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     BlockPos pos = null;
/* 52 */     int j = 1;
/*    */     
/* 54 */     while (pos == null) {
/*    */       
/* 56 */       BlockState state = this.field_70170_p.func_180495_p(func_233580_cy_().func_177979_c(j));
/*    */       
/* 58 */       if (state.func_200132_m()) {
/*    */         
/* 60 */         pos = func_233580_cy_().func_177979_c(j);
/*    */         
/*    */         break;
/*    */       } 
/* 64 */       if (j > 2) {
/*    */         break;
/*    */       }
/* 67 */       j++;
/*    */     } 
/*    */     
/* 70 */     if (pos != null) {
/* 71 */       int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
/* 72 */       AbilityHelper.createFilledSphere(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), size, Blocks.field_205164_gk, GRIEF_RULE);
/*    */     } 
/*    */     
/* 75 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 77 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 79 */         double offsetX = WyHelper.randomDouble();
/* 80 */         double offsetY = WyHelper.randomDouble();
/* 81 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 83 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.HIE.get());
/* 84 */         part.setLife(14);
/* 85 */         part.setAnimationSpeed(2);
/* 86 */         part.setRotation(Vector3f.field_229183_f_);
/* 87 */         part.setRotationSpeed((i % 2 == 0) ? 0.07F : -0.07F);
/* 88 */         part.setSize(1.5F);
/* 89 */         WyHelper.spawnParticles((IParticleData)part, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBlockPheasantProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */