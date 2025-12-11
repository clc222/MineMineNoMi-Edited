/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MokoProjectile extends AbilityProjectileEntity {
/*    */   public MokoProjectile(EntityType<Entity> type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */   
/*    */   public MokoProjectile(World world, LivingEntity player, Ability ability) {
/* 27 */     super((EntityType)ZushiProjectiles.MOKO.get(), world, player, ability.getCore());
/*    */     
/* 29 */     setDamage(10.0F);
/* 30 */     setPassThroughEntities();
/* 31 */     setArmorPiercing(1.0F);
/* 32 */     setUnavoidable();
/*    */     
/* 34 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 35 */     this.onBlockImpactEvent = this::onBlockImpact;
/* 36 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpact(BlockPos hit) {
/* 40 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 41 */     explosion.setStaticDamage(15.0F);
/* 42 */     explosion.setExplosionSound(true);
/* 43 */     explosion.setDamageOwner(false);
/* 44 */     explosion.setDestroyBlocks(true);
/* 45 */     explosion.setFireAfterExplosion(false);
/* 46 */     explosion.setExplosionSound(false);
/* 47 */     explosion.setSmokeParticles(null);
/* 48 */     explosion.setDamageEntities(true);
/* 49 */     explosion.doExplosion();
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 53 */     for (int x = -1; x < 1; x++) {
/* 54 */       for (int z = -1; z < 1; z++) {
/* 55 */         if (AbilityHelper.placeBlockIfAllowed(getThrower(), func_233580_cy_(), Blocks.field_150350_a.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 56 */           AbilityHelper.setDeltaMovement((Entity)target, 0.0D, -5.0D, 0.0D);
/* 57 */           target.func_195064_c(new EffectInstance(Effects.field_76421_d, 100, 10));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 64 */     if (!this.field_70170_p.field_72995_K)
/* 65 */       for (int i = 0; i < 25; i++) {
/* 66 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 67 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 68 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 70 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.GASU.get());
/* 71 */         data.setLife(10);
/* 72 */         data.setSize(1.0F);
/*    */         
/* 74 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zushi\MokoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */