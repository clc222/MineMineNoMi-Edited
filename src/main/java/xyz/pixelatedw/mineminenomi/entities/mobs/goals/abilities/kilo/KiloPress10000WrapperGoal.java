/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress10000Ability;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ 
/*    */ public class KiloPress10000WrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, KiloPress10000Ability>
/*    */ {
/*    */   private static final int DROP_CHECK_BOUND = 4;
/*    */   private LivingEntity target;
/*    */   
/*    */   public KiloPress10000WrapperGoal(MobEntity entity) {
/* 24 */     super(entity, KiloPress10000Ability.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 29 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     this.target = this.entity.func_70638_az();
/*    */     
/* 35 */     KiloPress1Ability kiloPress = (KiloPress1Ability)getProps().getEquippedAbility(KiloPress1Ability.INSTANCE);
/* 36 */     if (kiloPress != null) {
/* 37 */       Optional<ContinuousComponent> comp = kiloPress.getComponent(ModAbilityKeys.CONTINUOUS);
/* 38 */       if (comp.isPresent() && ((ContinuousComponent)comp.get()).getContinueTime() <= 100.0F) {
/* 39 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 43 */     if (this.entity.func_233570_aj_()) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     boolean isXInBound = (this.target.func_226277_ct_() - 4.0D <= this.entity.func_226277_ct_() && this.entity.func_226277_ct_() <= this.target.func_226277_ct_() + 4.0D);
/* 52 */     boolean isZInBound = (this.target.func_226281_cx_() - 4.0D <= this.entity.func_226281_cx_() && this.entity.func_226281_cx_() <= this.target.func_226281_cx_() + 4.0D);
/* 53 */     if (!isXInBound || !isZInBound) {
/* 54 */       return false;
/*    */     }
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 62 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 63 */       return false;
/*    */     }
/*    */     
/* 66 */     if (!hasUmbrellaInHand()) {
/* 67 */       return false;
/*    */     }
/*    */     
/* 70 */     if (this.entity.func_233570_aj_()) {
/* 71 */       return false;
/*    */     }
/*    */     
/* 74 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasUmbrellaInHand() {
/* 78 */     ItemStack itemStack = this.entity.func_184582_a(EquipmentSlotType.MAINHAND);
/* 79 */     return (!itemStack.func_190926_b() && itemStack.func_77973_b().equals(ModWeapons.UMBRELLA.get()));
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\kilo\KiloPress10000WrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */