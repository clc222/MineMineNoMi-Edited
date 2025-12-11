/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ 
/*    */ public class EntityCloud
/*    */   extends Entity implements IEntityAdditionalSpawnData {
/* 18 */   private int life = 100;
/*    */   private LivingEntity thrower;
/* 20 */   private EntitySize size = null;
/*    */   
/*    */   public EntityCloud(World world) {
/* 23 */     super((EntityType)ExtraProjectiles.CLOUD.get(), world);
/*    */   }
/*    */   
/*    */   public EntityCloud(EntityType type, World world) {
/* 27 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 32 */     super.func_70071_h_();
/* 33 */     if (!this.field_70170_p.field_72995_K) {
/* 34 */       if (this.life <= 0) {
/* 35 */         func_70106_y();
/*    */       }
/* 37 */       this.life--;
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getThrower() {
/* 43 */     return this.thrower;
/*    */   }
/*    */   
/*    */   public void setThrower(LivingEntity player) {
/* 47 */     this.thrower = player;
/*    */   }
/*    */   
/*    */   public int getLife() {
/* 51 */     return this.life;
/*    */   }
/*    */   
/*    */   public void setLife(int life) {
/* 55 */     this.life = life;
/*    */   }
/*    */   
/*    */   public void setSize(EntitySize size) {
/* 59 */     this.size = size;
/* 60 */     func_213323_x_();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySize func_213305_a(Pose pose) {
/* 65 */     if (this.size == null) {
/* 66 */       return super.func_213305_a(pose);
/*    */     }
/* 68 */     return this.size;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70037_a(CompoundNBT compound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_213281_b(CompoundNBT compound) {}
/*    */ 
/*    */   
/*    */   public IPacket<?> func_213297_N() {
/* 85 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeSpawnData(PacketBuffer buffer) {
/* 90 */     buffer.writeFloat((this.size != null) ? this.size.field_220315_a : (func_200600_R().func_220334_j()).field_220315_a);
/* 91 */     buffer.writeFloat((this.size != null) ? this.size.field_220316_b : (func_200600_R().func_220334_j()).field_220316_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readSpawnData(PacketBuffer buffer) {
/* 96 */     float width = buffer.readFloat();
/* 97 */     float height = buffer.readFloat();
/* 98 */     EntitySize size = EntitySize.func_220311_c(width, height);
/* 99 */     setSize(size);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\EntityCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */