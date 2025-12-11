/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TokuyoAburaBoshiProjectile extends AbilityProjectileEntity {
/*    */   public TokuyoAburaBoshiProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */   
/*    */   public TokuyoAburaBoshiProjectile(World world, LivingEntity player) {
/* 22 */     super((EntityType)SniperProjectiles.TOKUYO_ABURA_BOSHI.get(), world, player, TokuyoAburaBoshiAbility.INSTANCE);
/*    */     
/* 24 */     setDamage(4.0F);
/*    */     
/* 26 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 27 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 31 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.OIL_COVERED.get(), 300, 0));
/*    */     
/* 33 */     onBlockImpactEvent(target.func_233580_cy_());
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 37 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 38 */     for (int heightSpread = -3; heightSpread < 3; heightSpread++) {
/* 39 */       for (int i = 0; i < 20; i++) {
/* 40 */         double offsetX = WyHelper.randomWithRange(-3, 3);
/* 41 */         double offsetZ = WyHelper.randomWithRange(-3, 3);
/*    */         
/* 43 */         mutpos.func_189532_c(hit.func_177958_n() + offsetX, (hit.func_177956_o() + heightSpread), hit.func_177952_p() + offsetZ);
/* 44 */         BlockPos belowPos = mutpos.func_177977_b();
/*    */         
/* 46 */         if (this.field_70170_p.func_180495_p(belowPos).func_185904_a().func_76220_a() && this.field_70170_p.func_180495_p(belowPos).func_177230_c() != ModBlocks.OIL_SPILL.get())
/* 47 */           AbilityHelper.placeBlockIfAllowed(getThrower(), mutpos.func_185334_h(), ((Block)ModBlocks.OIL_SPILL.get()).func_176223_P(), DefaultProtectionRules.AIR_FOLIAGE); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\TokuyoAburaBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */