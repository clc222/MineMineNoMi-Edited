/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ import com.google.common.base.Predicates;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityPredicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ 
/*    */ 
/*    */ public class TargetsPredicate
/*    */ {
/* 15 */   public static final TargetsPredicate EVERYTHING = (new TargetsPredicate()).selector((Predicate<LivingEntity>)Predicates.alwaysTrue());
/* 16 */   public static final TargetsPredicate DEFAULT_AREA_CHECK = (new TargetsPredicate()).testEnemyFaction();
/* 17 */   public static final TargetsPredicate DEFAULT_LINE_CHECK = (new TargetsPredicate()).testEnemyFaction().testAdvancedInView();
/*    */   
/* 19 */   private EntityPredicate vanillaPredicate = EntityPredicate.field_221016_a;
/*    */   
/*    */   private boolean testSimpleInView;
/*    */   
/*    */   private boolean testAdvancedInView;
/*    */   
/*    */   private float fov;
/*    */   private boolean testEnemy;
/*    */   private boolean testFriendly;
/*    */   private Predicate<LivingEntity> selector;
/*    */   
/*    */   public TargetsPredicate testVanilla(EntityPredicate entityPredicate) {
/* 31 */     this.vanillaPredicate = entityPredicate;
/* 32 */     return this;
/*    */   }
/*    */   
/*    */   public TargetsPredicate testSimpleInView() {
/* 36 */     this.testSimpleInView = true;
/* 37 */     return this;
/*    */   }
/*    */   
/*    */   public TargetsPredicate testAdvancedInView() {
/* 41 */     this.testAdvancedInView = true;
/* 42 */     this.fov = 60.0F;
/* 43 */     return this;
/*    */   }
/*    */   
/*    */   public TargetsPredicate testAdvancedInView(float fov) {
/* 47 */     this.testAdvancedInView = true;
/* 48 */     this.fov = fov;
/* 49 */     return this;
/*    */   }
/*    */   
/*    */   public TargetsPredicate testEnemyFaction() {
/* 53 */     this.testEnemy = true;
/* 54 */     return this;
/*    */   }
/*    */   
/*    */   public TargetsPredicate testFriendlyFaction() {
/* 58 */     this.testFriendly = true;
/* 59 */     return this;
/*    */   }
/*    */   
/*    */   public TargetsPredicate selector(@Nullable Predicate<LivingEntity> customPredicate) {
/* 63 */     this.selector = customPredicate;
/* 64 */     return this;
/*    */   }
/*    */   
/*    */   public boolean test(@Nullable LivingEntity entity, LivingEntity target) {
/* 68 */     if (!this.vanillaPredicate.func_221015_a(entity, target)) {
/* 69 */       return false;
/*    */     }
/*    */     
/* 72 */     if (this.selector != null && !this.selector.test(target)) {
/* 73 */       return false;
/*    */     }
/*    */     
/* 76 */     if (this.testEnemy && !ModEntityPredicates.getEnemyFactions(entity).test(target)) {
/* 77 */       return false;
/*    */     }
/*    */     
/* 80 */     if (this.testFriendly && !ModEntityPredicates.getFriendlyFactions(entity).test(target)) {
/* 81 */       return false;
/*    */     }
/*    */     
/* 84 */     if (this.testSimpleInView && !entity.func_70685_l((Entity)target)) {
/* 85 */       return false;
/*    */     }
/*    */     
/* 88 */     if (this.testAdvancedInView && (!entity.func_70685_l((Entity)target) || !TargetHelper.isEntityInView(entity, (Entity)target, this.fov))) {
/* 89 */       return false;
/*    */     }
/*    */     
/* 92 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\TargetsPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */