/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.datasync.DataParameter;
/*    */ import net.minecraft.network.datasync.DataSerializers;
/*    */ import net.minecraft.network.datasync.EntityDataManager;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ 
/*    */ public class ChargingUrsusShockEntity extends Entity {
/* 15 */   protected static final DataParameter<Float> CHARGE = EntityDataManager.func_187226_a(ChargingUrsusShockEntity.class, DataSerializers.field_187193_c);
/*    */   
/*    */   private LivingEntity owner;
/*    */   
/*    */   public ChargingUrsusShockEntity(World worldIn) {
/* 20 */     super((EntityType)NikyuProjectiles.CHARGING_URSUS_SHOCK.get(), worldIn);
/*    */   }
/*    */   
/*    */   public ChargingUrsusShockEntity(EntityType<?> entityTypeIn, World worldIn) {
/* 24 */     super(entityTypeIn, worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 29 */     super.func_70071_h_();
/*    */     
/* 31 */     if (!this.field_70170_p.field_72995_K) {
/* 32 */       if (this.owner == null) {
/* 33 */         func_70106_y();
/*    */         
/*    */         return;
/*    */       } 
/* 37 */       if (this.field_70173_aa > 600) {
/* 38 */         func_70106_y();
/*    */         
/*    */         return;
/*    */       } 
/* 42 */       func_70012_b(this.owner.func_226277_ct_(), this.owner.func_226280_cw_() + (0.2F * getCharge()), this.owner.func_226281_cx_(), this.owner.field_70177_z, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {
/* 48 */     this.field_70180_af.func_187214_a(CHARGE, Float.valueOf(0.0F));
/*    */   }
/*    */   
/*    */   public void setCharge(float value) {
/* 52 */     this.field_70180_af.func_187227_b(CHARGE, Float.valueOf(value));
/*    */   }
/*    */   
/*    */   public float getCharge() {
/* 56 */     return ((Float)this.field_70180_af.func_187225_a(CHARGE)).floatValue();
/*    */   }
/*    */   
/*    */   public void setOwner(LivingEntity owner) {
/* 60 */     this.owner = owner;
/*    */   }
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
/* 73 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\ChargingUrsusShockEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */