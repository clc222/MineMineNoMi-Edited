/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.controller.DolphinLookController;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LandFishLookController<T extends MobEntity> extends DolphinLookController {
/*    */   private final T entity;
/*    */   
/*    */   public LandFishLookController(T entity, int maxYRotFromCenter) {
/* 12 */     super((MobEntity)entity, maxYRotFromCenter);
/* 13 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75649_a() {
/* 18 */     if (WyHelper.isAprilFirst()) {
/* 19 */       if (func_220680_b()) {
/* 20 */         this.field_75659_a.field_70125_A = 0.0F;
/*    */       }
/*    */       
/* 23 */       if (this.field_75655_d) {
/* 24 */         this.field_75655_d = false;
/* 25 */         this.field_75659_a.field_70759_as = func_220675_a(this.field_75659_a.field_70759_as, func_220678_h(), this.field_75657_b);
/* 26 */         this.field_75659_a.field_70125_A = func_220675_a(this.field_75659_a.field_70125_A, func_220677_g(), this.field_75658_c);
/*    */       } else {
/*    */         
/* 29 */         this.field_75659_a.field_70759_as = func_220675_a(this.field_75659_a.field_70759_as, this.field_75659_a.field_70761_aq, 10.0F);
/*    */       } 
/*    */       
/* 32 */       if (!this.field_75659_a.func_70661_as().func_75500_f()) {
/* 33 */         this.field_75659_a.field_70759_as = MathHelper.func_219800_b(this.field_75659_a.field_70759_as, this.field_75659_a.field_70761_aq, this.field_75659_a.func_184649_cE());
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     super.func_75649_a();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ai\controllers\LandFishLookController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */