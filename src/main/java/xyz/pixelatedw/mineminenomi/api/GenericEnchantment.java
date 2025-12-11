/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentType;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ 
/*    */ public class GenericEnchantment
/*    */   extends Enchantment
/*    */ {
/*    */   private final boolean isTreasure;
/*    */   
/*    */   public GenericEnchantment(Enchantment.Rarity rarityIn, boolean isTreasure, EquipmentSlotType... slots) {
/* 13 */     super(rarityIn, EnchantmentType.WEAPON, slots);
/* 14 */     this.isTreasure = isTreasure;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_77325_b() {
/* 20 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_185261_e() {
/* 25 */     return this.isTreasure;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\GenericEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */