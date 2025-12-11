/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AvoidAoEGoal
/*    */   extends Goal {
/*    */   private OPEntity entity;
/*    */   private double speed;
/*    */   private double responseTime;
/*    */   
/*    */   public AvoidAoEGoal(OPEntity entity, double speed, double responseTime) {
/* 22 */     this.entity = entity;
/* 23 */     this.speed = speed;
/* 24 */     this.responseTime = responseTime;
/* 25 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 31 */     List<PlayerEntity> targets = WyHelper.getNearbyPlayers(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 20.0D, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     boolean hasTargetNearby = (((List)targets.stream().filter(player -> { IAbilityData props = AbilityDataCapability.get((LivingEntity)player); return false; }).collect(Collectors.toList())).size() > 0);
/*    */     
/* 50 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\AvoidAoEGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */