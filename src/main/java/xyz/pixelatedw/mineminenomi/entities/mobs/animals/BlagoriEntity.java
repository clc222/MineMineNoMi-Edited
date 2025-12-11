/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class BlagoriEntity extends AbstractGorillaEntity {
/*    */   public BlagoriEntity(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */     
/* 15 */     if (world != null && !world.field_72995_K) {
/* 16 */       func_110148_a((Attribute)ModAttributes.FALL_RESISTANCE.get()).func_111128_a(2.0D);
/* 17 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(9.0D);
/* 18 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 23 */     return MobEntity.func_233666_p_()
/* 24 */       .func_233815_a_(Attributes.field_233821_d_, 0.3D)
/* 25 */       .func_233815_a_(Attributes.field_233820_c_, 0.4000000059604645D)
/* 26 */       .func_233815_a_(Attributes.field_233818_a_, 80.0D)
/* 27 */       .func_233815_a_(Attributes.field_233823_f_, 7.0D)
/* 28 */       .func_233815_a_(Attributes.field_233819_b_, 80.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\BlagoriEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */