/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.PotionUtils;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IBrewPotionObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class BrewPotionObjective extends Objective implements IBrewPotionObjective {
/*    */   private Effect[] effects;
/* 15 */   private Item[] types = null;
/*    */ 
/*    */   
/*    */   public BrewPotionObjective(String title, int count) {
/* 19 */     this(title, count, new Item[] { Items.field_151068_bn, Items.field_185156_bI, Items.field_185155_bH }, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrewPotionObjective(String title, int count, Effect[] effects) {
/* 24 */     this(title, count, new Item[] { Items.field_151068_bn, Items.field_185156_bI, Items.field_185155_bH }, effects);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrewPotionObjective(String title, int count, Item[] types, Effect[] effects) {
/* 29 */     super(title);
/* 30 */     setMaxProgress(count);
/* 31 */     this.effects = effects;
/* 32 */     this.types = types;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkPotion(PlayerEntity player, ItemStack stack) {
/* 38 */     if (this.types == null) {
/* 39 */       return false;
/*    */     }
/* 41 */     if (stack.func_196082_o().func_74767_n("questMark")) {
/* 42 */       return false;
/*    */     }
/* 44 */     boolean isPotion = false;
/* 45 */     boolean isCorrectEffect = true;
/*    */     
/* 47 */     for (Item item : this.types) {
/*    */       
/* 49 */       if (stack.func_77973_b() == item) {
/*    */         
/* 51 */         isPotion = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 56 */     if (this.effects != null && isPotion) {
/*    */       
/* 58 */       Effect[] arrayOfEffect = this.effects; int i = arrayOfEffect.length; byte b = 0; if (b < i) { Effect effect = arrayOfEffect[b];
/*    */         
/* 60 */         isCorrectEffect = PotionUtils.func_185189_a(stack).stream().anyMatch(instance -> (instance.func_188419_a() == effect)); }
/*    */ 
/*    */     
/*    */     }
/* 64 */     else if (this.effects == null && isPotion) {
/*    */       
/* 66 */       isCorrectEffect = PotionUtils.func_185189_a(stack).stream().findAny().isPresent();
/*    */     } 
/*    */     
/* 69 */     if (stack.func_190926_b() && isCorrectEffect)
/*    */     {
/* 71 */       isPotion = true;
/*    */     }
/*    */     
/* 74 */     if (isPotion && isCorrectEffect) {
/* 75 */       stack.func_196082_o().func_74757_a("questMark", true);
/*    */     }
/* 77 */     return (isPotion && isCorrectEffect);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\BrewPotionObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */