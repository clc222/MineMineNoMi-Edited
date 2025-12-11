/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IAnimatedEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.animations.SSetNPCTimeAnimationPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ @Deprecated
/*    */ public interface IAnimatedGoal {
/*    */   IAnimation getAnimation();
/*    */   
/*    */   boolean isAnimationActive(LivingEntity paramLivingEntity);
/*    */   
/*    */   default void startAnimation(OPEntity entity) {
/* 21 */     updateAnimation(entity);
/* 22 */     if (getAnimation() instanceof TimeAnimation) {
/* 23 */       ((TimeAnimation)getAnimation()).start((LivingEntity)entity);
/* 24 */       if (!entity.field_70170_p.field_72995_K)
/* 25 */         WyNetwork.sendToAllTracking(new SSetNPCTimeAnimationPacket(entity.func_145782_y(), entity.getAnimation(), TimeAnimation.State.PLAY), (Entity)entity); 
/*    */     } 
/*    */   }
/*    */   
/*    */   default void stopAnimation(OPEntity entity) {
/* 30 */     if (getAnimation() instanceof TimeAnimation) {
/* 31 */       ((TimeAnimation)getAnimation()).stop((LivingEntity)entity);
/* 32 */       if (!entity.field_70170_p.field_72995_K)
/* 33 */         WyNetwork.sendToAllTracking(new SSetNPCTimeAnimationPacket(entity.func_145782_y(), entity.getAnimation(), TimeAnimation.State.STOP), (Entity)entity); 
/*    */     } 
/* 35 */     updateAnimation(entity);
/*    */   }
/*    */   
/*    */   default void updateAnimation(OPEntity entity) {
/* 39 */     if (isAnimationActive((LivingEntity)entity)) {
/* 40 */       if (entity instanceof IAnimatedEntity) {
/* 41 */         int i = getAnimationIndex(entity);
/* 42 */         if (i == -1) {
/* 43 */           WyDebug.debug("Invalid animation index for entity " + entity.func_145748_c_().getString() + " and animation " + getAnimation());
/*    */           return;
/*    */         } 
/* 46 */         entity.setAnimation(i + 1);
/*    */       } 
/*    */     } else {
/*    */       
/* 50 */       entity.setAnimation(0);
/*    */     } 
/*    */   }
/*    */   default int getAnimationIndex(OPEntity entity) {
/* 54 */     if (entity instanceof IAnimatedEntity) {
/* 55 */       return ArrayUtils.indexOf((Object[])((IAnimatedEntity)entity).getAnimations(), getAnimation());
/*    */     }
/*    */     
/* 58 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean isCurrentAnimation(OPEntity entity) {
/* 63 */     if (entity instanceof IAnimatedEntity) {
/* 64 */       return (entity.getAnimation() == getAnimationIndex(entity) + 1);
/*    */     }
/* 66 */     if (!(entity instanceof IAnimatedEntity)) {
/* 67 */       return true;
/*    */     }
/*    */     
/* 70 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\IAnimatedGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */