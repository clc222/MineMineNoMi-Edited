/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class AbstractMarineEntity extends OPEntity {
/*    */   protected AbstractMarineEntity(EntityType<? extends MobEntity> type, World world) {
/* 15 */     this(type, world, null);
/*    */   }
/*    */   
/*    */   protected AbstractMarineEntity(EntityType<? extends MobEntity> type, World world, ResourceLocation[] textures) {
/* 19 */     super(type, world, textures);
/*    */     
/* 21 */     if (world != null && !world.field_72995_K) {
/* 22 */       getEntityStats().setFaction(ModValues.MARINE);
/* 23 */       MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\AbstractMarineEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */