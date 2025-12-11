/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class SniperTargetEntity
/*    */   extends MobEntity {
/*    */   public SniperTargetEntity(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SniperTargetEntity(World world) {
/* 20 */     super((EntityType)ModEntities.SNIPER_TARGET.get(), world);
/* 21 */     this.field_70728_aV = 0;
/* 22 */     func_70659_e(0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 27 */     return MobEntity.func_233666_p_()
/* 28 */       .func_233815_a_(Attributes.field_233818_a_, 1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 33 */     AbilityHelper.setDeltaMovement((Entity)this, 0.0D, -0.1D, 0.0D);
/*    */     
/* 35 */     this.field_70143_R = 0.0F;
/*    */     
/* 37 */     if ((func_233570_aj_() || func_70090_H() || func_180799_ab()) && !this.field_70170_p.field_72995_K) {
/* 38 */       func_70106_y();
/*    */     }
/*    */     
/* 41 */     super.func_70071_h_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\objectives\SniperTargetEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */