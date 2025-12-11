/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MoverType;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SpikeEntity
/*    */   extends Entity {
/*    */   protected boolean inGround;
/* 24 */   protected List<LivingEntity> targets = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SpikeEntity(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpikeEntity(World world) {
/* 33 */     super((EntityType)ModEntities.SPIKE.get(), world);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 39 */     super.func_70071_h_();
/*    */     
/* 41 */     if (!func_233570_aj_()) {
/* 42 */       func_213315_a(MoverType.SELF, new Vector3d(0.0D, -0.3D, 0.0D));
/*    */     }
/* 44 */     if (this.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/* 47 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 0.0D, null);
/*    */     
/* 49 */     for (LivingEntity entity : targets) {
/*    */       
/* 51 */       entity.func_70097_a(DamageSource.field_76377_j, 3.0F);
/* 52 */       if (entity.func_70681_au().nextDouble() < 0.5D)
/*    */       {
/* 54 */         entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 60, 1, false, true));
/*    */       }
/*    */     } 
/*    */     
/* 58 */     if (this.field_70173_aa > 1200.0D + WyHelper.randomWithRange(0, 100)) {
/* 59 */       func_70106_y();
/*    */     }
/*    */   }
/*    */   
/*    */   protected float getGravityVelocity() {
/* 64 */     return 0.03F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_241845_aY() {
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70037_a(CompoundNBT compound) {
/* 81 */     compound.func_74757_a("inGround", this.inGround);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_213281_b(CompoundNBT compound) {
/* 87 */     this.inGround = compound.func_74767_n("inGround");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> func_213297_N() {
/* 93 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\SpikeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */