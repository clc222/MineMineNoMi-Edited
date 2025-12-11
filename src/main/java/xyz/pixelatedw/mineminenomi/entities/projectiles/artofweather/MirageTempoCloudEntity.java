/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MirageTempoCloudEntity extends EntityCloud {
/*    */   public MirageTempoCloudEntity(World world) {
/* 12 */     super(world);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 18 */     super.func_70071_h_();
/* 19 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 21 */       if (this.field_70173_aa % 2 == 0)
/*    */       {
/* 23 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KEMURI_BOSHI.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\MirageTempoCloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */