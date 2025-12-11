/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class WantedPosterPackageEntity extends MobEntity {
/*    */   public WantedPosterPackageEntity(EntityType type, World world) {
/* 14 */     super(type, world);
/* 15 */     func_70606_j(1.0F);
/*    */   }
/*    */   
/*    */   public WantedPosterPackageEntity(World world) {
/* 19 */     this((EntityType)ModEntities.WANTED_POSTER_PACKAGE.get(), world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_213345_d(DamageSource cause) {
/* 24 */     if (!func_233570_aj_()) {
/* 25 */       ItemsHelper.dropWantedPosters(this.field_70170_p, func_213303_ch());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 31 */     func_213293_j(func_213322_ci().func_82615_a(), func_213322_ci().func_82617_b() / (1.5D + this.field_70170_p.field_73012_v.nextDouble()), func_213322_ci().func_82616_c());
/* 32 */     this.field_70143_R = 0.0F;
/* 33 */     func_230245_c_(false);
/*    */     
/* 35 */     if (!this.field_70170_p.field_72995_K) {
/* 36 */       BlockPos pos = func_233580_cy_().func_177977_b();
/* 37 */       if (!this.field_70170_p.func_175623_d(pos)) {
/* 38 */         this.field_70170_p.func_175656_a(pos.func_177984_a(), ((Block)ModBlocks.WANTED_POSTER_PACKAGE.get()).func_176223_P());
/* 39 */         func_70106_y();
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     if (func_70090_H() || func_180799_ab()) {
/* 44 */       func_174812_G();
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     super.func_70071_h_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\WantedPosterPackageEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */