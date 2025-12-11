/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBomberEntity;
/*    */ 
/*    */ public class HumanoidBigBomberModel extends HumanoidModel<PirateBomberEntity> {
/*    */   public void setupAnim(PirateBomberEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 10 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 12 */     if (entity.hasBomb()) {
/*    */       
/* 14 */       this.field_78116_c.field_78797_d += 2.0F;
/* 15 */       this.field_178723_h.field_78795_f = (float)Math.toRadians(180.0D);
/* 16 */       this.field_178723_h.field_78808_h = (float)Math.toRadians(-10.0D);
/* 17 */       this.field_178724_i.field_78795_f = (float)Math.toRadians(180.0D);
/* 18 */       this.field_178724_i.field_78808_h = (float)Math.toRadians(10.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidBigBomberModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */