/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.beta;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanamizuShinkenShirahadoriParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     ClientPlayerEntity clientPlayer = (Minecraft.func_71410_x()).field_71439_g;
/* 17 */     float visibility = (entity == clientPlayer) ? 0.15F : 0.5F;
/*    */     
/* 19 */     for (int i = 0; i < 64; i++) {
/* 20 */       double offsetX = WyHelper.randomDouble() / 1.2D;
/* 21 */       double offsetY = WyHelper.randomDouble();
/* 22 */       double offsetZ = WyHelper.randomDouble() / 1.2D;
/*    */       
/* 24 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BETA.get());
/* 25 */       data.setLife(50);
/* 26 */       data.setSize((1 + world.field_73012_v.nextInt(2)) + world.field_73012_v.nextFloat());
/* 27 */       data.setColor(1.0F, 1.0F, 1.0F, visibility);
/* 28 */       data.setMotion(0.0D, -0.01D, 0.0D);
/* 29 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\beta\HanamizuShinkenShirahadoriParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */