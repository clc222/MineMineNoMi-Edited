/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.electro;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElectricalLunaParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     for (int i = 0; i < 20; i++) {
/* 15 */       SimpleParticleData data; double offsetX = WyHelper.randomDouble();
/* 16 */       double offsetY = WyHelper.randomDouble();
/* 17 */       double offsetZ = WyHelper.randomDouble();
/*    */ 
/*    */ 
/*    */       
/* 21 */       if (i % 2 == 0) {
/* 22 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.GORO2.get());
/*    */       } else {
/* 24 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.GORO.get());
/* 25 */       }  data.setLife(5);
/* 26 */       data.setSize(7.0F);
/* 27 */       data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 28 */       data.setMotion(0.0D, WyHelper.randomDouble() / 3.0D, 0.0D);
/* 29 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\electro\ElectricalLunaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */