/*    */ package xyz.pixelatedw.mineminenomi.api.entities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public interface IAnimatedEntity
/*    */ {
/*    */   IAnimation[] getAnimations();
/*    */   
/*    */   default IAnimation getAnimationById(int id) {
/* 17 */     return getAnimations()[id];
/*    */   }
/*    */ 
/*    */   
/*    */   default int getAnimationId(IAnimation animation) {
/* 22 */     int i = ArrayUtils.indexOf((Object[])getAnimations(), animation);
/* 23 */     return i + 1;
/*    */   }
/*    */ 
/*    */   
/*    */   default void setActiveAnimation(OPEntity entity, @Nullable IAnimation animation) {
/* 28 */     if (animation == null) {
/*    */       
/* 30 */       entity.setAnimation(0);
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     if (entity instanceof IAnimatedEntity) {
/*    */       
/* 36 */       int i = ArrayUtils.indexOf((Object[])((IAnimatedEntity)entity).getAnimations(), animation);
/* 37 */       if (i == -1) {
/*    */         
/* 39 */         WyDebug.debug("Invalid animation index for entity " + entity.func_145748_c_().getString() + " and animation " + animation);
/*    */         return;
/*    */       } 
/* 42 */       entity.setAnimation(i + 1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\IAnimatedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */