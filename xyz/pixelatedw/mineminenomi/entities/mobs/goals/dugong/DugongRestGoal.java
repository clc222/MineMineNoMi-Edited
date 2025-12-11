/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong;
/*     */ import java.util.EnumSet;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMemoryModuleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DugongRestGoal extends TickedGoal<AbstractDugongEntity> {
/*  18 */   private Interval canUseInterval = new Interval(40);
/*  19 */   private Interval sleepBubbleTimer = new Interval(50);
/*     */   private int restTime;
/*  21 */   private int nextRestTime = 2;
/*     */   private float startHealth;
/*     */   private float startYaw;
/*     */   
/*     */   public DugongRestGoal(AbstractDugongEntity entity) {
/*  26 */     super((MobEntity)entity);
/*  27 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  33 */     if (!this.canUseInterval.canTick()) {
/*  34 */       return false;
/*     */     }
/*     */     
/*  37 */     if (!((AbstractDugongEntity)this.entity).isIdling()) {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     if (((AbstractDugongEntity)this.entity).hasMemoryValue((MemoryModuleType)ModMemoryModuleTypes.LAST_EXPLOSION_HEARD.get())) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     if (getLastEndTick() <= 0L) {
/*  46 */       setLastEndTick(((AbstractDugongEntity)this.entity).field_70170_p.func_82737_E());
/*     */     }
/*     */     
/*  49 */     if (!hasTimePassedSinceLastEnd(WyHelper.minutesToTicks(this.nextRestTime))) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  54 */       return false;
/*     */     }
/*     */     
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  62 */     if (((AbstractDugongEntity)this.entity).isEnraged()) {
/*  63 */       return false;
/*     */     }
/*     */     
/*  66 */     if (((AbstractDugongEntity)this.entity).isCheering()) {
/*  67 */       return false;
/*     */     }
/*     */     
/*  70 */     if (!((AbstractDugongEntity)this.entity).func_70608_bn()) {
/*  71 */       return false;
/*     */     }
/*     */     
/*  74 */     if (((AbstractDugongEntity)this.entity).hasMemoryValue((MemoryModuleType)ModMemoryModuleTypes.LAST_EXPLOSION_HEARD.get())) {
/*  75 */       return false;
/*     */     }
/*     */     
/*  78 */     if (((AbstractDugongEntity)this.entity).func_110143_aJ() < this.startHealth) {
/*  79 */       return false;
/*     */     }
/*     */     
/*  82 */     if (GoalUtil.shouldMove(this.entity)) {
/*  83 */       return false;
/*     */     }
/*     */     
/*  86 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  87 */       return false;
/*     */     }
/*     */     
/*  90 */     return !hasTimePassedSinceStart(WyHelper.secondsToTicks(this.restTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  95 */     super.func_75249_e();
/*  96 */     ((AbstractDugongEntity)this.entity).setResting(true);
/*  97 */     this.restTime = 20 + ((AbstractDugongEntity)this.entity).func_70681_au().nextInt(20);
/*  98 */     this.nextRestTime = 2 + ((AbstractDugongEntity)this.entity).func_70681_au().nextInt(3);
/*  99 */     ((AbstractDugongEntity)this.entity).func_70661_as().func_75499_g();
/* 100 */     this.startHealth = ((AbstractDugongEntity)this.entity).func_110143_aJ();
/* 101 */     this.startYaw = ((AbstractDugongEntity)this.entity).field_70761_aq;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 106 */     super.func_75246_d();
/* 107 */     if (this.sleepBubbleTimer.canTick()) {
/* 108 */       Vector3d look = ((AbstractDugongEntity)this.entity).func_70040_Z().func_186678_a(0.75D);
/* 109 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.AWA.get());
/* 110 */       part.setLife(20);
/* 111 */       part.setSize(2.0F);
/* 112 */       part.setHasScaleDecay(false);
/* 113 */       WyHelper.spawnParticles((IParticleData)part, (ServerWorld)((AbstractDugongEntity)this.entity).field_70170_p, ((AbstractDugongEntity)this.entity).func_226277_ct_() + look.field_72450_a, ((AbstractDugongEntity)this.entity).func_226280_cw_() + look.field_72448_b - 0.85D, ((AbstractDugongEntity)this.entity).func_226281_cx_() + look.field_72449_c);
/*     */     } 
/* 115 */     this.startYaw = ((AbstractDugongEntity)this.entity).field_70761_aq;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 120 */     super.func_75251_c();
/* 121 */     ((AbstractDugongEntity)this.entity).setResting(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\dugong\DugongRestGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */