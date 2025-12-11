/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.block.PortalInfo;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.common.util.ITeleporter;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ public class ChallengeTeleporter
/*    */   implements ITeleporter {
/*    */   private InProgressChallenge challenge;
/* 16 */   private int spawnId = 0;
/*    */   private boolean toChallenge = true;
/*    */   
/*    */   public ChallengeTeleporter(InProgressChallenge challenge) {
/* 20 */     this.challenge = challenge;
/*    */   }
/*    */   
/*    */   public void teleportToChallengeWorld(Entity entity) {
/* 24 */     this.toChallenge = true;
/* 25 */     entity.changeDimension(this.challenge.getShard(), this);
/*    */   }
/*    */   
/*    */   public void teleportToHomeWorld(Entity entity) {
/* 29 */     this.toChallenge = false;
/* 30 */     entity.changeDimension(getTargetWorld(entity), this);
/*    */   }
/*    */ 
/*    */   
/*    */   public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
/* 35 */     if (this.toChallenge) {
/* 36 */       ChallengeArena.SpawnPosition pos = this.challenge.getChallengerPosition().getChallengerSpawnPos(this.spawnId, this.challenge);
/* 37 */       this.spawnId++;
/*    */       
/* 39 */       Vector3d vector3d = new Vector3d(pos.getPos().func_177958_n(), pos.getPos().func_177956_o(), pos.getPos().func_177952_p());
/* 40 */       PortalInfo portalInfo = new PortalInfo(vector3d, Vector3d.field_186680_a, pos.getYaw(), pos.getPitch());
/*    */       
/* 42 */       return portalInfo;
/*    */     } 
/*    */     
/* 45 */     ServerWorld spawnWorld = getTargetWorld(entity);
/* 46 */     BlockPos spawnPos = null;
/*    */     
/* 48 */     if (CommonConfig.INSTANCE.isReturnToSafetyEnabled()) {
/* 49 */       if (entity instanceof ServerPlayerEntity) {
/* 50 */         spawnPos = ((ServerPlayerEntity)entity).func_241140_K_();
/*    */       }
/*    */       
/* 53 */       if (spawnPos == null) {
/* 54 */         spawnPos = spawnWorld.func_241135_u_();
/*    */       }
/*    */     } else {
/*    */       
/* 58 */       spawnPos = this.challenge.getReturnPosition();
/*    */     } 
/*    */     
/* 61 */     Vector3d vecPos = new Vector3d(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
/* 62 */     PortalInfo info = new PortalInfo(vecPos, Vector3d.field_186680_a, entity.field_70177_z, entity.field_70125_A);
/*    */     
/* 64 */     return info;
/*    */   }
/*    */   
/*    */   private ServerWorld getTargetWorld(Entity entity) {
/*    */     ServerWorld spawnWorld;
/* 69 */     if (this.toChallenge) {
/* 70 */       return this.challenge.getShard();
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 75 */     if (CommonConfig.INSTANCE.isReturnToSafetyEnabled()) {
/* 76 */       spawnWorld = entity.func_184102_h().func_241755_D_();
/*    */     } else {
/*    */       
/* 79 */       spawnWorld = entity.func_184102_h().func_71218_a(this.challenge.getReturnDimension());
/*    */     } 
/*    */     
/* 82 */     return spawnWorld;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeTeleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */