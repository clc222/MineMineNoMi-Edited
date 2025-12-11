/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ 
/*    */ public class EntityCarryEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   @Nullable
/*    */   private final LivingEntity carryTarget;
/*    */   private final boolean isCarrying;
/*    */   
/*    */   public EntityCarryEvent(LivingEntity entity, @Nullable LivingEntity carryTarget, boolean isCarrying) {
/* 15 */     super(entity);
/* 16 */     this.carryTarget = carryTarget;
/* 17 */     this.isCarrying = isCarrying;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getCarryTarget() {
/* 22 */     return this.carryTarget;
/*    */   }
/*    */   
/*    */   public boolean isCarrying() {
/* 26 */     return this.isCarrying;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\EntityCarryEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */