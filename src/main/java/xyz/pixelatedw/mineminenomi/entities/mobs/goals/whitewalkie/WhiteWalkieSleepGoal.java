/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.whitewalkie;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMemoryModuleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WhiteWalkieSleepGoal extends TickedGoal<WhiteWalkieEntity> {
/*     */   private static final int COOLDOWN = 40;
/*  15 */   private int nextRestTime = 2; private int restTime;
/*     */   private float startHealth;
/*     */   private float startYaw;
/*     */   
/*     */   public WhiteWalkieSleepGoal(WhiteWalkieEntity entity) {
/*  20 */     super((MobEntity)entity);
/*  21 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  26 */     if (!hasTimePassedSinceLastEnd(40.0F)) {
/*  27 */       return false;
/*     */     }
/*     */     
/*  30 */     if (!((WhiteWalkieEntity)this.entity).isIdling()) {
/*  31 */       return false;
/*     */     }
/*     */     
/*  34 */     if (!((WhiteWalkieEntity)this.entity).field_70170_p.func_226690_K_()) {
/*  35 */       return false;
/*     */     }
/*     */     
/*  38 */     if (((WhiteWalkieEntity)this.entity).hasMemoryValue((MemoryModuleType)ModMemoryModuleTypes.LAST_EXPLOSION_HEARD.get())) {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     if (getLastEndTick() <= 0L) {
/*  43 */       setLastEndTick(((WhiteWalkieEntity)this.entity).field_70170_p.func_82737_E());
/*     */     }
/*     */     
/*  46 */     if (!hasTimePassedSinceLastEnd(WyHelper.minutesToTicks(this.nextRestTime))) {
/*  47 */       return false;
/*     */     }
/*     */     
/*  50 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  51 */       return false;
/*     */     }
/*     */     
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  59 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  60 */       return false;
/*     */     }
/*     */     
/*  63 */     if (!((WhiteWalkieEntity)this.entity).func_70608_bn()) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     if (((WhiteWalkieEntity)this.entity).hasMemoryValue((MemoryModuleType)ModMemoryModuleTypes.LAST_EXPLOSION_HEARD.get())) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     if (((WhiteWalkieEntity)this.entity).func_110143_aJ() < this.startHealth) {
/*  72 */       return false;
/*     */     }
/*     */     
/*  75 */     if (GoalUtil.shouldMove(this.entity)) {
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     return !hasTimePassedSinceStart(WyHelper.secondsToTicks(this.restTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  84 */     super.func_75249_e();
/*  85 */     ((WhiteWalkieEntity)this.entity).setSleeping(true);
/*  86 */     this.restTime = 60 + ((WhiteWalkieEntity)this.entity).func_70681_au().nextInt(60);
/*  87 */     this.nextRestTime = 2 + ((WhiteWalkieEntity)this.entity).func_70681_au().nextInt(3);
/*  88 */     ((WhiteWalkieEntity)this.entity).func_70661_as().func_75499_g();
/*  89 */     this.startHealth = ((WhiteWalkieEntity)this.entity).func_110143_aJ();
/*  90 */     this.startYaw = ((WhiteWalkieEntity)this.entity).field_70761_aq;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  95 */     super.func_75246_d();
/*  96 */     ((WhiteWalkieEntity)this.entity).field_70761_aq = this.startYaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 101 */     super.func_75251_c();
/* 102 */     ((WhiteWalkieEntity)this.entity).setSleeping(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\whitewalkie\WhiteWalkieSleepGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */