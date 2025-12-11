/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.DoruProjectiles;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WaxCloneEntity extends CloneEntity {
/*    */   public WaxCloneEntity(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */   
/*    */   public WaxCloneEntity(World world) {
/* 19 */     super((EntityType)DoruProjectiles.WAX_CLONE.get(), world);
/* 20 */     this.field_70728_aV = 0;
/*    */   }
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 24 */     return OPEntity.createAttributes()
/* 25 */       .func_233815_a_(Attributes.field_233819_b_, 35.0D)
/* 26 */       .func_233815_a_(Attributes.field_233821_d_, 0.25D)
/* 27 */       .func_233815_a_(Attributes.field_233818_a_, 10.0D)
/* 28 */       .func_233815_a_(Attributes.field_233820_c_, 10.0D)
/* 29 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70106_y() {
/* 34 */     if (!this.field_70170_p.field_72995_K) {
/* 35 */       for (int i = 0; i < 10; i++) {
/* 36 */         double offsetX = WyHelper.randomDouble();
/* 37 */         double offsetY = WyHelper.randomDouble();
/* 38 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 40 */         if (i % 2 == 0) {
/* 41 */           ((ServerWorld)func_130014_f_()).func_195598_a((IParticleData)ParticleTypes.field_197613_f, func_226277_ct_() + offsetX, func_226278_cu_() + 1.5D + offsetY, 
/* 42 */               func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*    */         } else {
/*    */           
/* 45 */           ((ServerWorld)func_130014_f_()).func_195598_a((IParticleData)ParticleTypes.field_197598_I, func_226277_ct_() + offsetX, func_226278_cu_() + 1.5D + offsetY, 
/* 46 */               func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*    */         } 
/*    */       } 
/*    */     }
/* 50 */     super.func_70106_y();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\WaxCloneEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */