/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ @Cancelable
/*    */ public class AbilityCanUseEvent extends AbilityEvent {
/*    */   public AbilityCanUseEvent(PlayerEntity player, Ability ability) {
/* 12 */     super((LivingEntity)player, (IAbility)ability);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\AbilityCanUseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */