/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ShockwaveParticleEffect;
/*    */ 
/*    */ public class ShockwaveProjectile extends AbilityProjectileEntity {
/* 19 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE })).build();
/*    */   
/* 21 */   private ShockwaveParticleEffect.Details particleDetails = new ShockwaveParticleEffect.Details(2);
/*    */   private boolean canBreakBlocks = false;
/* 23 */   private int size = 2;
/*    */   
/*    */   public ShockwaveProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */   
/*    */   public ShockwaveProjectile(World world, LivingEntity entity, AbilityCore<?> parent) {
/* 30 */     this(world, entity, parent, false);
/*    */   }
/*    */   
/*    */   public ShockwaveProjectile(World world, LivingEntity entity, AbilityCore<?> parent, boolean canBreakBlocks) {
/* 34 */     super((EntityType)ExtraProjectiles.SHOCKWAVE.get(), world, entity, parent);
/*    */     
/* 36 */     setDamage(8.0F);
/* 37 */     setSize(this.size);
/* 38 */     setMaxLife(20);
/* 39 */     setPassThroughEntities();
/* 40 */     this.onTickEvent = this::onTickEvent;
/* 41 */     this.canBreakBlocks = canBreakBlocks;
/*    */   }
/*    */   
/*    */   public void setSize(int size) {
/* 45 */     this.size = size;
/* 46 */     setEntityCollisionSize(size);
/* 47 */     this.particleDetails = new ShockwaveParticleEffect.Details(size);
/*    */   }
/*    */   
/*    */   public void setBreakBlocks(boolean flag) {
/* 51 */     this.canBreakBlocks = flag;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 55 */     BlockPos pos = null;
/* 56 */     int j = 1;
/*    */     
/* 58 */     while (pos == null) {
/* 59 */       BlockState state = this.field_70170_p.func_180495_p(func_233580_cy_().func_177979_c(j));
/*    */       
/* 61 */       if (state.func_200132_m()) {
/* 62 */         pos = func_233580_cy_().func_177979_c(j);
/*    */         
/*    */         break;
/*    */       } 
/* 66 */       if (j > 5) {
/*    */         break;
/*    */       }
/*    */       
/* 70 */       j++;
/*    */     } 
/*    */     
/* 73 */     if (pos == null) {
/*    */       return;
/*    */     }
/*    */     
/* 77 */     if (this.canBreakBlocks) {
/* 78 */       AbilityHelper.createSphere(this.field_70170_p, new BlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), this.size, false, Blocks.field_150350_a, 258, GRIEF_RULE);
/*    */     }
/*    */     
/* 81 */     if (this.field_70173_aa % 2 == 0)
/* 82 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SHOCKWAVE.get(), (Entity)this, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), (ParticleEffect.Details)this.particleDetails); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\ShockwaveProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */