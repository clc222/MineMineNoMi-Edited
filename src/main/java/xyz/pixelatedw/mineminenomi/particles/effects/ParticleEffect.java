/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.ParticleStatus;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*    */ 
/*    */ public abstract class ParticleEffect<A extends ParticleEffect.Details>
/*    */   extends ForgeRegistryEntry<ParticleEffect<?>>
/*    */ {
/* 15 */   public static final NoDetails EMPTY_DETAILS = new NoDetails();
/*    */   private IFactory<A> factory;
/*    */   
/*    */   public ParticleEffect() {}
/*    */   
/*    */   public ParticleEffect(IFactory<A> factory) {
/* 21 */     this.factory = factory;
/*    */   }
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, A details) {
/* 25 */     spawn(entity.field_70170_p, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean canParticlesSpawn(World world) {
/* 30 */     ParticleStatus particles = (Minecraft.func_71410_x()).field_71474_y.field_74362_aa;
/* 31 */     if ((particles == ParticleStatus.DECREASED && world.func_201674_k().nextInt(10) < 2) || (particles == ParticleStatus.MINIMAL && world.func_201674_k().nextInt(10) < 8)) {
/* 32 */       return false;
/*    */     }
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public A createDetails() {
/* 38 */     if (this.factory != null) {
/* 39 */       return this.factory.create();
/*    */     }
/* 41 */     return (A)EMPTY_DETAILS;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {}
/*    */   
/*    */   public static abstract class Details {
/*    */     public abstract void save(CompoundNBT param1CompoundNBT);
/*    */     
/*    */     public abstract void load(CompoundNBT param1CompoundNBT);
/*    */   }
/*    */   
/*    */   public static class NoDetails extends Details {
/*    */     public void save(CompoundNBT nbt) {}
/*    */     
/*    */     public void load(CompoundNBT nbt) {}
/*    */   }
/*    */   
/*    */   public static interface IFactory<A extends Details> {
/*    */     A create();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */