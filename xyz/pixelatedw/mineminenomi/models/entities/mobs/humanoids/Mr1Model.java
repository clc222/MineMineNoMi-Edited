/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr1Entity;
/*    */ 
/*    */ public class Mr1Model extends HumanoidModel<Mr1Entity> {
/*    */   public void setupAttackAnimation(Mr1Entity entity, float ageInTicks) {
/*  9 */     if (this.field_217112_c > 0.0F) {
/* 10 */       float f = this.field_217112_c;
/* 11 */       f = 1.0F - this.field_217112_c;
/* 12 */       f *= f;
/* 13 */       f *= f;
/* 14 */       f = 1.0F - f;
/* 15 */       float f1 = MathHelper.func_76126_a(f * 3.1415927F);
/* 16 */       float f2 = MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/*    */       
/* 18 */       this.field_178723_h.field_78795_f = (float)(this.field_178723_h.field_78795_f - f1 * 1.6D + f2);
/* 19 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g * 4.0F;
/* 20 */       this.field_178723_h.field_78808_h += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.8F;
/*    */       
/* 22 */       this.field_178724_i.field_78795_f = (float)(this.field_178724_i.field_78795_f - f1 * 1.6D + f2);
/* 23 */       this.field_178724_i.field_78796_g -= this.field_78115_e.field_78796_g * 4.0F;
/* 24 */       this.field_178724_i.field_78808_h -= MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * -0.8F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\Mr1Model.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */