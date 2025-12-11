/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class CandyWaveProjectile extends AbilityProjectileEntity {
/* 21 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE })).build();
/*    */ 
/*    */   
/*    */   public CandyWaveProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyWaveProjectile(World world, LivingEntity player, Ability ability) {
/* 30 */     super((EntityType)PeroProjectiles.CANDY_WAVE.get(), world, player, ability);
/*    */     
/* 32 */     setDamage(24.0F);
/* 33 */     setMaxLife(12);
/* 34 */     setPassThroughEntities();
/* 35 */     setPassThroughBlocks();
/*    */     
/* 37 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance((Effect)ModEffects.CANDY_STUCK.get(), 100, 0, false, false, false) });
/*    */ 
/*    */ 
/*    */     
/* 41 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 46 */     BlockPos pos = null;
/* 47 */     int j = 1;
/*    */     
/* 49 */     while (pos == null) {
/*    */       
/* 51 */       BlockState state = this.field_70170_p.func_180495_p(func_233580_cy_().func_177979_c(j));
/*    */       
/* 53 */       if (state.func_200132_m()) {
/*    */         
/* 55 */         pos = func_233580_cy_().func_177979_c(j);
/*    */         
/*    */         break;
/*    */       } 
/* 59 */       if (j > 3) {
/*    */         break;
/*    */       }
/* 62 */       j++;
/*    */     } 
/*    */     
/* 65 */     if (pos == null) {
/*    */       return;
/*    */     }
/* 68 */     int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
/*    */     
/* 70 */     AbilityHelper.createFilledSphere(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), size, (Block)ModBlocks.CANDY.get(), GRIEF_RULE);
/*    */     
/* 72 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CANDY_WAVE.get(), (Entity)this, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\CandyWaveProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */