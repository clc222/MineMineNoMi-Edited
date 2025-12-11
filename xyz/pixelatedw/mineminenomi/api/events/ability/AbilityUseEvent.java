/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ public class AbilityUseEvent extends AbilityEvent {
/*    */   public AbilityUseEvent(LivingEntity living, IAbility ability) {
/*  9 */     super(living, ability);
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre extends AbilityUseEvent {
/*    */     public Pre(LivingEntity living, IAbility ability) {
/* 15 */       super(living, ability);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post extends AbilityUseEvent {
/*    */     public Post(LivingEntity living, IAbility ability) {
/* 21 */       super(living, ability);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\AbilityUseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */