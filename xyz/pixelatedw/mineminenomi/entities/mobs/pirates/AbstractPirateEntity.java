/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class AbstractPirateEntity
/*    */   extends OPEntity
/*    */ {
/*    */   protected AbstractPirateEntity(EntityType<? extends MobEntity> type, World world) {
/* 27 */     this(type, world, (ResourceLocation[])null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractPirateEntity(EntityType<? extends MobEntity> type, World world, ResourceLocation[] textures) {
/* 32 */     super(type, world, textures);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_184651_r() {
/* 38 */     getEntityStats().setFaction(ModValues.PIRATE);
/* 39 */     getEntityStats().setRace(ModValues.HUMAN);
/*    */ 
/*    */     
/* 42 */     if (getEntityStats().isPirate() && this.field_70170_p.func_201674_k().nextInt(10) < 3) {
/*    */       
/* 44 */       getEntityStats().setRace(ModValues.FISHMAN);
/* 45 */       queueEntityDataUpdate();
/*    */     } 
/*    */     
/* 48 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 55 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 57 */     return spawnData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {
/* 63 */     super.func_70088_a();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean func_225511_J_() {
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_213337_cE() {
/* 75 */     super.func_213337_cE();
/* 76 */     if (func_184582_a(EquipmentSlotType.HEAD).func_77973_b() == ModArmors.MH5_GAS_MASK.get() && WyHelper.randomDouble() < 0.2D) {
/* 77 */       func_199703_a((IItemProvider)ModArmors.MH5_GAS_MASK.get());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_213397_c(double distance) {
/* 83 */     if (isSpawnedViaSpawner() && distance < 40000.0D) {
/* 84 */       return false;
/*    */     }
/* 86 */     if (distance > 1024.0D) {
/* 87 */       return true;
/*    */     }
/* 89 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\AbstractPirateEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */