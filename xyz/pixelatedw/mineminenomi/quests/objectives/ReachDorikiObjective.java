/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IReachDorikiObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class ReachDorikiObjective
/*    */   extends Objective
/*    */   implements IReachDorikiObjective {
/*    */   private int doriki;
/*    */   
/*    */   public ReachDorikiObjective(String title, int doriki) {
/* 17 */     super(title);
/* 18 */     setMaxProgress(1.0D);
/* 19 */     this.doriki = doriki;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkDoriki(PlayerEntity player) {
/* 25 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 26 */     return (props.getDoriki() >= this.doriki);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 32 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective.mineminenomi.%s", new Object[] { getId() }))).func_150268_i();
/* 33 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf(this.doriki) })).getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\ReachDorikiObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */