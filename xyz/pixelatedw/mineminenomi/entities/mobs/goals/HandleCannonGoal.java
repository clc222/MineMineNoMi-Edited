/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.CannonEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HandleCannonGoal
/*     */   extends TickedGoal<MobEntity> {
/*     */   private CannonEntity cannon;
/*     */   
/*     */   public HandleCannonGoal(MobEntity entity) {
/*  23 */     super(entity);
/*     */   }
/*     */   private LivingEntity target; private long lastShot;
/*     */   
/*     */   public boolean func_75250_a() {
/*  28 */     if (this.entity.field_70173_aa % 100 != 0) {
/*  29 */       return false;
/*     */     }
/*     */     
/*  32 */     if (this.entity.func_184218_aH()) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  37 */       return false;
/*     */     }
/*     */     
/*  40 */     this.target = this.entity.func_70638_az();
/*     */     
/*  42 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     StructureStart<?> structA = StructuresHelper.getStructureAt((ServerWorld)this.entity.field_70170_p, this.entity.func_233580_cy_());
/*  47 */     StructureStart<?> structB = StructuresHelper.getStructureAt((ServerWorld)this.entity.field_70170_p, this.target.func_233580_cy_());
/*     */     
/*  49 */     if (structA != null && structA == structB) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     List<CannonEntity> nearbyCannons = WyHelper.getNearbyEntities(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 5.0D, null, new Class[] { CannonEntity.class });
/*  54 */     Optional<CannonEntity> targetCannon = nearbyCannons.stream().filter(cannon -> !cannon.func_184207_aI()).findFirst();
/*     */     
/*  56 */     if (!targetCannon.isPresent()) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     this.cannon = targetCannon.get();
/*     */     
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  67 */     if (this.cannon == null || !this.cannon.func_70089_S()) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  72 */       return false;
/*     */     }
/*     */     
/*  75 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     StructureStart<?> structA = StructuresHelper.getStructureAt((ServerWorld)this.entity.field_70170_p, this.entity.func_233580_cy_());
/*  80 */     StructureStart<?> structB = StructuresHelper.getStructureAt((ServerWorld)this.entity.field_70170_p, this.target.func_233580_cy_());
/*     */     
/*  82 */     if (structA != null && structA == structB) {
/*  83 */       return false;
/*     */     }
/*     */     
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  91 */     super.func_75249_e();
/*     */     
/*  93 */     this.entity.func_184205_a((Entity)this.cannon, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  98 */     super.func_75246_d();
/*     */     
/* 100 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 101 */     this.cannon.field_70125_A = this.entity.field_70125_A - 8.0F;
/* 102 */     this.cannon.field_70177_z = this.entity.field_70177_z;
/*     */     
/* 104 */     if (this.lastShot == 0L) {
/* 105 */       this.lastShot = this.entity.field_70170_p.func_82737_E();
/*     */     }
/*     */     
/* 108 */     if (this.entity.field_70170_p.func_82737_E() >= this.lastShot + 100L) {
/* 109 */       this.entity.func_184609_a(Hand.MAIN_HAND);
/* 110 */       this.lastShot = this.entity.field_70170_p.func_82737_E();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 116 */     super.func_75251_c();
/*     */     
/* 118 */     this.entity.func_184210_p();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\HandleCannonGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */