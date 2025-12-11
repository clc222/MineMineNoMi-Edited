/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MeleeRangedStyleSwitchGoal
/*    */   extends TickedGoal<MobEntity>
/*    */ {
/*    */   private static final float SWITCH_RANGE = 5.0F;
/*    */   private static final int COOLDOWN = 20;
/*    */   private boolean isInRangedMode;
/*    */   private boolean forceMeleeInGroupFights;
/*    */   private boolean forceMeleeEmptyHanded;
/* 21 */   private final PriorityEventPool<IStyleSwitchEvent> styleSwitchEvents = new PriorityEventPool();
/*    */   
/*    */   public MeleeRangedStyleSwitchGoal(MobEntity entity) {
/* 24 */     super(entity);
/* 25 */     this.isInRangedMode = true;
/*    */   }
/*    */   
/*    */   public MeleeRangedStyleSwitchGoal addStyleSwitchEvent(int priority, IStyleSwitchEvent event) {
/* 29 */     this.styleSwitchEvents.addEvent(priority, event);
/* 30 */     return this;
/*    */   }
/*    */   
/*    */   public MeleeRangedStyleSwitchGoal forceMeleeInGroupFights() {
/* 34 */     this.forceMeleeInGroupFights = true;
/* 35 */     return this;
/*    */   }
/*    */   
/*    */   public MeleeRangedStyleSwitchGoal forceMeleeEmptyHanded() {
/* 39 */     this.forceMeleeEmptyHanded = true;
/* 40 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 46 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     if (!hasTimePassedSinceLastEnd(20.0F)) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 56 */     if (this.isInRangedMode) {
/* 57 */       if (this.forceMeleeInGroupFights)
/*    */       {
/* 59 */         if (GoalUtil.hasEnoughTargetsAround(this.entity, 2, 5.0F)) {
/* 60 */           return true;
/*    */         }
/*    */       }
/*    */       
/* 64 */       if (this.forceMeleeEmptyHanded) {
/*    */ 
/*    */         
/* 67 */         ItemStack heldItem = this.entity.func_184614_ca();
/* 68 */         if (heldItem.func_190926_b()) {
/* 69 */           return true;
/*    */         }
/*    */       } 
/*    */       
/* 73 */       if (GoalUtil.isWithinDistance((LivingEntity)this.entity, target, 5.0D)) {
/* 74 */         return true;
/*    */       }
/*    */       
/* 77 */       return false;
/*    */     } 
/*    */     
/* 80 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, 5.0D)) {
/* 81 */       return true;
/*    */     }
/*    */     
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 90 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 95 */     super.func_75249_e();
/*    */     
/* 97 */     this.isInRangedMode = !this.isInRangedMode;
/* 98 */     this.styleSwitchEvents.dispatch(event -> event.change(this.entity, this.isInRangedMode));
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IStyleSwitchEvent {
/*    */     void change(MobEntity param1MobEntity, boolean param1Boolean);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\MeleeRangedStyleSwitchGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */