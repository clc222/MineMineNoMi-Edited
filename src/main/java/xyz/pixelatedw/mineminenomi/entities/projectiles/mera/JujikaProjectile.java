/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class JujikaProjectile extends AbilityProjectileEntity {
/*    */   public JujikaProjectile(EntityType<Entity> type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */   
/*    */   public JujikaProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)MeraProjectiles.JUJIKA.get(), world, player, ability.getCore());
/*    */     
/* 26 */     setDamage(25.0F);
/* 27 */     setArmorPiercing(0.75F);
/*    */     
/* 29 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 30 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 34 */     if (func_208600_a((ITag)FluidTags.field_206959_a)) {
/* 35 */       func_70106_y();
/*    */       
/* 37 */       func_130014_f_().func_195594_a((IParticleData)ParticleTypes.field_197601_L, func_226277_ct_(), func_226278_cu_() + 1.1D, func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 40 */     if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 0) {
/* 41 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.JUJIKA.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 46 */     if (CommonConfig.INSTANCE.isAbilityGriefingEnabled())
/* 47 */       for (int j = -2; j <= 2; j++) {
/* 48 */         for (int i = -5; i <= 5; i++) {
/* 49 */           if (this.field_70170_p.func_175623_d(new BlockPos(hit.func_177958_n() + i, hit.func_177956_o() + j, hit.func_177952_p()))) {
/* 50 */             AbilityHelper.placeBlockIfAllowed(getThrower(), new BlockPos(hit.func_177958_n() + i, hit.func_177956_o() + j, hit.func_177952_p() - i), Blocks.field_150480_ab.func_176223_P(), null);
/*    */           }
/*    */           
/* 53 */           if (this.field_70170_p.func_175623_d(new BlockPos(hit.func_177958_n(), hit.func_177956_o() + j, hit.func_177952_p() + i)))
/* 54 */             AbilityHelper.placeBlockIfAllowed(getThrower(), new BlockPos(hit.func_177958_n(), hit.func_177956_o() + j, hit.func_177952_p() + i), Blocks.field_150480_ab.func_176223_P(), null); 
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\JujikaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */