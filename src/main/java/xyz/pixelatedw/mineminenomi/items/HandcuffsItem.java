/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class HandcuffsItem
/*    */   extends Item
/*    */ {
/*    */   private static final int HANDCUFF_TIME = 2400;
/*    */   private Supplier<Effect> handcuffEffect;
/*    */   
/*    */   public HandcuffsItem(Supplier<Effect> handcuffed) {
/* 20 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/* 21 */     this.handcuffEffect = handcuffed;
/*    */   }
/*    */   
/*    */   public Supplier<Effect> getEffect() {
/* 25 */     return this.handcuffEffect;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_77644_a(ItemStack stack, LivingEntity target, LivingEntity attacker) {
/* 30 */     EffectInstance inst = target.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/* 31 */     if (inst != null && inst.func_76458_c() > 0) {
/* 32 */       handleHandcuffActivation(stack, target, 2.0F);
/* 33 */       return false;
/*    */     } 
/*    */     
/* 36 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean handleHandcuffActivation(ItemStack stack, LivingEntity target, float damage) {
/* 40 */     if (damage <= 0.0F || target.func_110143_aJ() - damage > 0.0F) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     if (stack.func_190926_b() || !(stack.func_77973_b() instanceof HandcuffsItem)) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     EffectInstance instance = new EffectInstance((Effect)ModEffects.HANDCUFFED.get(), 2400, 0);
/*    */     
/* 50 */     target.func_70606_j(2.0F);
/*    */     
/* 52 */     if (stack.func_77973_b() == ModItems.NORMAL_HANDCUFFS.get()) {
/* 53 */       instance = new EffectInstance((Effect)ModEffects.HANDCUFFED.get(), 2400, 0);
/*    */     }
/* 55 */     else if (stack.func_77973_b() == ModItems.KAIROSEKI_HANDCUFFS.get()) {
/* 56 */       instance = new EffectInstance((Effect)ModEffects.HANDCUFFED_KAIROSEKI.get(), 2400, 0);
/*    */     }
/* 58 */     else if (stack.func_77973_b() == ModItems.EXPLOSIVE_HANDCUFFS.get()) {
/* 59 */       instance = new EffectInstance((Effect)ModEffects.HANDCUFFED_EXPLOSIVE.get(), 2400, 0);
/*    */     } 
/*    */     
/* 62 */     target.func_195064_c(instance);
/*    */     
/* 64 */     target.field_70172_ad = 100;
/*    */     
/* 66 */     stack.func_190918_g(1);
/*    */     
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\HandcuffsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */