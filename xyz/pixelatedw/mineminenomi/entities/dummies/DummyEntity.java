/*    */ package xyz.pixelatedw.mineminenomi.entities.dummies;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ 
/*    */ public class DummyEntity extends OPEntity {
/*    */   public DummyEntity(EntityType<? extends DummyEntity> type, World world) {
/* 18 */     super(type, world);
/* 19 */     EntityStatsCapability.get((LivingEntity)this).setFaction(ModValues.CIVILIAN);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 24 */     this.field_70145_X = true;
/* 25 */     super.func_70071_h_();
/* 26 */     this.field_70145_X = false;
/* 27 */     func_189654_d(true);
/* 28 */     AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 35 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     return spawnData;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\dummies\DummyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */