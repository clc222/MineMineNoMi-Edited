/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class DemonPoisonBlock extends PoisonBlock {
/*    */   public void func_196262_a(BlockState state, World world, BlockPos pos, Entity entity) {
/* 14 */     if (entity instanceof LivingEntity && 
/* 15 */       !((LivingEntity)entity).func_70644_a((Effect)ModEffects.DOKU_POISON.get()))
/* 16 */       ((LivingEntity)entity).func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 100, 2)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\DemonPoisonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */