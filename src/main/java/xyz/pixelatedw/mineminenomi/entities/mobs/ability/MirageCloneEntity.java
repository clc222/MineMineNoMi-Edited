/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.ArtOfWeatherProjectiles;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MirageCloneEntity
/*    */   extends CloneEntity {
/*    */   public MirageCloneEntity(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MirageCloneEntity(World world) {
/* 22 */     super((EntityType)ArtOfWeatherProjectiles.MIRAGE_CLONE.get(), world);
/* 23 */     this.field_70728_aV = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 28 */     return OPEntity.createAttributes()
/* 29 */       .func_233815_a_(Attributes.field_233819_b_, 35.0D)
/* 30 */       .func_233815_a_(Attributes.field_233821_d_, 0.25D)
/* 31 */       .func_233815_a_(Attributes.field_233818_a_, 5.0D)
/* 32 */       .func_233815_a_(Attributes.field_233820_c_, 10.0D)
/* 33 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70106_y() {
/* 40 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 42 */       for (int i = 0; i < 10; i++) {
/*    */         
/* 44 */         double offsetX = WyHelper.randomDouble();
/* 45 */         double offsetY = WyHelper.randomDouble();
/* 46 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 48 */         if (i % 2 == 0) {
/* 49 */           ((ServerWorld)func_130014_f_()).func_195598_a((IParticleData)ParticleTypes.field_197613_f, func_226277_ct_() + offsetX, func_226278_cu_() + 1.5D + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*    */         } else {
/* 51 */           ((ServerWorld)func_130014_f_()).func_195598_a((IParticleData)ParticleTypes.field_197598_I, func_226277_ct_() + offsetX, func_226278_cu_() + 1.5D + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*    */         } 
/*    */       }  } 
/* 54 */     super.func_70106_y();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\MirageCloneEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */