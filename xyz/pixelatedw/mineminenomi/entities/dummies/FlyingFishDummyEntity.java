/*    */ package xyz.pixelatedw.mineminenomi.entities.dummies;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FlyingFishEntity;
/*    */ 
/*    */ public class FlyingFishDummyEntity extends FlyingFishEntity {
/*    */   public FlyingFishDummyEntity(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 16 */     this.field_70145_X = true;
/* 17 */     super.func_70071_h_();
/* 18 */     this.field_70145_X = false;
/* 19 */     func_189654_d(true);
/* 20 */     AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/* 21 */     func_70050_g(300);
/*    */   }
/*    */   
/*    */   protected void func_184651_r() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\dummies\FlyingFishDummyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */