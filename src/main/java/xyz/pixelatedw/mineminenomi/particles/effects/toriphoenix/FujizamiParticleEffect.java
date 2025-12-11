/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
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
/*    */ public class FujizamiParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     ClientPlayerEntity clientPlayer = (Minecraft.func_71410_x()).field_71439_g;
/* 17 */     float visibility = (entity == clientPlayer) ? 0.15F : 0.5F;
/*    */     
/* 19 */     for (int i = 0; i < 64; i++) {
/* 20 */       double offsetX = WyHelper.randomDouble() / 1.2D;
/* 21 */       double offsetY = WyHelper.randomDouble();
/* 22 */       double offsetZ = WyHelper.randomDouble() / 1.2D;
/*    */       
/* 24 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BLUE_FLAME.get());
/* 25 */       data.setLife(20);
/* 26 */       data.setColor(1.0F, 1.0F, 1.0F, visibility);
/* 27 */       data.setSize(2.0F);
/* 28 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\FujizamiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */