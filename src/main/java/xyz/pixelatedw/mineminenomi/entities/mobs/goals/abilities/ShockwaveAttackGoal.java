/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.Difficulty;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ShockwaveAttackGoal
/*    */   extends CooldownGoal {
/*    */   private int hitCount;
/*    */   private int maxCount;
/*    */   private int duration;
/*    */   private final int maxDuration;
/*    */   private float prevHealth;
/*    */   private int animationId;
/*    */   private boolean canBreakBlocks;
/*    */   
/*    */   public ShockwaveAttackGoal(OPEntity entity, int hitCount) {
/* 20 */     this(entity, hitCount, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public ShockwaveAttackGoal(OPEntity entity, int hitCount, boolean canBreakBlocks) {
/* 25 */     super(entity);
/* 26 */     setMaxCooldown(5.0D);
/* 27 */     this.duration = 40;
/* 28 */     this.maxCount = hitCount;
/* 29 */     this.prevHealth = this.entity.func_110143_aJ();
/* 30 */     this.canBreakBlocks = canBreakBlocks;
/* 31 */     this.maxDuration = 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public ShockwaveAttackGoal setAnimationId(int id) {
/* 36 */     this.animationId = id;
/* 37 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 43 */     if (this.entity.func_110143_aJ() < this.prevHealth) {
/*    */       
/* 45 */       this.hitCount++;
/* 46 */       this.prevHealth = this.entity.func_110143_aJ();
/*    */     } 
/*    */     
/* 49 */     boolean shouldExecute = (super.func_75250_a() || this.hitCount >= this.maxCount);
/* 50 */     boolean hasTarget = (this.entity.func_70638_az() != null);
/* 51 */     boolean hasEnemyInSight = (hasTarget && this.entity.func_70685_l((Entity)this.entity.func_70638_az()));
/*    */     
/* 53 */     if (shouldExecute && hasTarget && hasEnemyInSight) {
/* 54 */       return true;
/*    */     }
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 62 */     this.duration++;
/* 63 */     boolean continueExecution = false;
/*    */     
/* 65 */     if (this.duration < this.maxDuration) {
/* 66 */       continueExecution = true;
/*    */     }
/* 68 */     if (!continueExecution) {
/*    */       
/* 70 */       this.entity.setAnimation(0);
/* 71 */       this.hitCount = 0;
/* 72 */       this.maxCount = (int)Math.abs(WyHelper.randomWithRange(this.maxCount - 2, this.maxCount + 2));
/*    */       
/* 74 */       setOnCooldown(true);
/*    */     } 
/*    */     
/* 77 */     return continueExecution;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 85 */     this.duration = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringCooldown() {}
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 94 */     this.entity.setAnimation(this.animationId);
/*    */     
/* 96 */     double x = this.entity.func_70638_az().func_226277_ct_() - this.entity.func_226277_ct_() + ((this.entity.field_70170_p.func_175659_aa().func_151525_a() >= Difficulty.HARD.func_151525_a()) ? this.entity.func_70681_au().nextGaussian() : 0.0D);
/* 97 */     double y = (this.entity.func_70638_az().func_174813_aQ()).field_72338_b + (this.entity.func_70638_az().func_213302_cg() / 2.0F) - this.entity.func_226278_cu_() + (this.entity.func_213302_cg() / 2.0F);
/* 98 */     double z = this.entity.func_70638_az().func_226281_cx_() - this.entity.func_226281_cx_() + ((this.entity.field_70170_p.func_175659_aa().func_151525_a() >= Difficulty.HARD.func_151525_a()) ? this.entity.func_70681_au().nextGaussian() : 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ShockwaveAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */