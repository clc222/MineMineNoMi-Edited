/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.ITamableEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GuardCommandGoal<E extends MobEntity & ICommandReceiver>
/*     */   extends TickedGoal<E> {
/*     */   private final Predicate<LivingEntity> entityPredicate;
/*     */   private final TargetsPredicate targetPredicate;
/*     */   private Vector3d guardPosition;
/*     */   private long lastManualStart;
/*     */   
/*     */   public GuardCommandGoal(E entity) {
/*  25 */     super((MobEntity)entity);
/*  26 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.TARGET));
/*  27 */     this.entityPredicate = (target -> {
/*     */         if (entity instanceof ITamableEntity) {
/*     */           LivingEntity owner = ((ITamableEntity)entity).getOwner();
/*     */           
/*     */           if (owner != null && owner.equals(target)) {
/*     */             return false;
/*     */           }
/*     */         } 
/*  35 */         return !(((ICommandReceiver)entity).getLastCommandSender() != null && ((ICommandReceiver)entity).getLastCommandSender().equals(target));
/*     */       });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.targetPredicate = (new TargetsPredicate()).testEnemyFaction().testSimpleInView().selector(this.entityPredicate);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  47 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.GUARD)) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     if (!hasTimePassedSinceLastEnd(WyHelper.secondsToTicks(5.0F))) {
/*  52 */       return false;
/*     */     }
/*     */     
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  60 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.GUARD)) {
/*  61 */       return false;
/*     */     }
/*     */     
/*  64 */     if (this.entity.func_70638_az() != null && this.entity.func_70638_az().func_70089_S()) {
/*  65 */       return false;
/*     */     }
/*     */     
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  73 */     super.func_75249_e();
/*  74 */     this.entity.func_70624_b(null);
/*  75 */     this.entity.func_70604_c(null);
/*  76 */     this.entity.func_70661_as().func_75499_g();
/*  77 */     if (this.guardPosition == null || this.lastManualStart != ((ICommandReceiver)this.entity).getLastCommandTime()) {
/*  78 */       this.guardPosition = this.entity.func_213303_ch();
/*     */     }
/*  80 */     this.lastManualStart = ((ICommandReceiver)this.entity).getLastCommandTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  85 */     super.func_75246_d();
/*     */     
/*  87 */     if (getTickCount() % 20L != 0L) {
/*     */       return;
/*     */     }
/*     */     
/*  91 */     if (this.entity.func_213303_ch().func_72438_d(this.guardPosition) > 6.0D) {
/*  92 */       this.entity.func_70661_as().func_75492_a(this.guardPosition.field_72450_a, this.guardPosition.field_72448_b, this.guardPosition.field_72449_c, 1.25D);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     LivingEntity possibleTarget = TargetHelper.getEntitiesInArea((LivingEntity)this.entity, 10.0D, this.targetPredicate, new Class[] { LivingEntity.class }).stream().sorted(TargetHelper.closestComparator(this.guardPosition)).findFirst().orElse(null);
/* 100 */     this.entity.func_70624_b(possibleTarget);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 105 */     super.func_75251_c();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\commands\GuardCommandGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */