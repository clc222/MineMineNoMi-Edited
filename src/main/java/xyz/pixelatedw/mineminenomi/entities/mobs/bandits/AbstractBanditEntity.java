/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class AbstractBanditEntity
/*    */   extends OPEntity {
/*    */   protected AbstractBanditEntity(EntityType<? extends MobEntity> type, World world) {
/* 19 */     this(type, world, null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractBanditEntity(EntityType<? extends MobEntity> type, World world, ResourceLocation[] textures) {
/* 24 */     super(type, world, textures);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_184651_r() {
/* 30 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 31 */     props.setFaction(ModValues.BANDIT);
/*    */     
/* 33 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean func_225511_J_() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_213397_c(double distance) {
/* 45 */     if (isSpawnedViaSpawner() && distance < 40000.0D) {
/* 46 */       return false;
/*    */     }
/* 48 */     if (distance > 1024.0D) {
/* 49 */       return true;
/*    */     }
/* 51 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\bandits\AbstractBanditEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */