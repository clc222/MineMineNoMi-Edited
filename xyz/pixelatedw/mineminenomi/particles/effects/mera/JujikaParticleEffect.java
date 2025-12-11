/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class JujikaParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 19 */     Direction dir = Direction.func_176733_a(entity.field_70177_z);
/* 20 */     Random rand = entity.field_70170_p.field_73012_v; float i;
/* 21 */     for (i = 0.0F; i <= 5.0F; i += 0.5F) {
/*    */       SimpleParticleData part;
/*    */ 
/*    */       
/* 25 */       int x = 0;
/* 26 */       int z = 0;
/*    */       
/* 28 */       z = (int)(z + i * dir.func_82601_c());
/* 29 */       x = (int)(x + i * dir.func_82599_e());
/*    */       
/* 31 */       if (rand.nextInt(10) % 2 == 0) {
/* 32 */         part = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */       } else {
/* 34 */         part = new SimpleParticleData((ParticleType)ModParticleTypes.MERA2.get());
/* 35 */       }  part.setLife((int)WyHelper.randomWithRange(1, 3));
/* 36 */       part.setSize((float)WyHelper.randomWithRange(0, 2));
/* 37 */       part.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 38 */       part.setMotion((entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b, (entity.func_213322_ci()).field_72449_c);
/* 39 */       entity.field_70170_p.func_195590_a((IParticleData)part, true, posX - x, posY, posZ - z, 0.0D, 0.0D, 0.0D);
/* 40 */       entity.field_70170_p.func_195590_a((IParticleData)part, true, posX + x, posY, posZ + z, 0.0D, 0.0D, 0.0D);
/* 41 */       entity.field_70170_p.func_195590_a((IParticleData)part, true, posX, posY + i, posZ, 0.0D, 0.0D, 0.0D);
/* 42 */       entity.field_70170_p.func_195590_a((IParticleData)part, true, posX, posY - i, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\JujikaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */