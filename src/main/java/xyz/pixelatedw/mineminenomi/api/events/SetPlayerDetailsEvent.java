/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class SetPlayerDetailsEvent
/*    */   extends PlayerEvent {
/*    */   private IEntityStats props;
/*    */   
/*    */   public SetPlayerDetailsEvent(PlayerEntity player) {
/* 14 */     super(player);
/* 15 */     this.props = EntityStatsCapability.get((LivingEntity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   public IEntityStats getEntityStats() {
/* 20 */     return this.props;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\SetPlayerDetailsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */