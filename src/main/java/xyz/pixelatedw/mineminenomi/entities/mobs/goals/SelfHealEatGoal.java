/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.Hand;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ 
/*    */ public class SelfHealEatGoal
/*    */   extends TickedGoal {
/*    */   private static final int COOLDOWN = 1200;
/* 17 */   private static final Item[] FOODS = new Item[] { Items.field_151083_be, Items.field_151077_bg, Items.field_196102_ba, Items.field_179557_bn, Items.field_151157_am, Items.field_179559_bp, Items.field_196104_bb, Items.field_151025_P };
/*    */ 
/*    */   
/* 20 */   private ItemStack prevItem = ItemStack.field_190927_a;
/* 21 */   private int eatTicks = 0;
/*    */   
/*    */   public SelfHealEatGoal(MobEntity entity) {
/* 24 */     super(entity);
/* 25 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 30 */     if (this.entity.func_110143_aJ() >= this.entity.func_110138_aP()) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     if (this.entity.func_70643_av() != null && this.entity.func_70643_av().func_70089_S()) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     if (!hasTimePassedSinceLastEnd(1200.0F)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 51 */     if (this.eatTicks > 60) {
/* 52 */       return false;
/*    */     }
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 60 */     super.func_75249_e();
/* 61 */     this.prevItem = this.entity.func_184614_ca();
/* 62 */     ItemStack foodItem = FOODS[this.entity.func_70681_au().nextInt(FOODS.length)].func_190903_i();
/* 63 */     this.entity.func_184201_a(EquipmentSlotType.MAINHAND, foodItem);
/* 64 */     this.entity.func_184598_c(Hand.MAIN_HAND);
/* 65 */     this.eatTicks = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 70 */     this.eatTicks++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 75 */     super.func_75251_c();
/* 76 */     this.entity.func_184602_cy();
/* 77 */     this.entity.func_184201_a(EquipmentSlotType.MAINHAND, this.prevItem);
/* 78 */     this.entity.func_70691_i(10.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\SelfHealEatGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */