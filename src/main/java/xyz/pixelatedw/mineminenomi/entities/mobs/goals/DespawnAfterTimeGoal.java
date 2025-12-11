/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ 
/*    */ public class DespawnAfterTimeGoal
/*    */   extends TickedGoal<MobEntity> {
/*    */   private final int ticksTilDespawn;
/*    */   
/*    */   public DespawnAfterTimeGoal(MobEntity entity, int ticks) {
/* 11 */     super(entity);
/* 12 */     this.ticksTilDespawn = ticks;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 17 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 22 */     if (hasTimePassedSinceStart(this.ticksTilDespawn)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 31 */     super.func_75251_c();
/* 32 */     this.entity.func_70106_y();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\DespawnAfterTimeGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */