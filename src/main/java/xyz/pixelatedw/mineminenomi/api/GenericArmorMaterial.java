/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class GenericArmorMaterial
/*    */   implements IArmorMaterial
/*    */ {
/* 14 */   private static final int[] MAX_DAMAGE = new int[] { 13, 15, 16, 11 };
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private final int maxDamageFactor;
/*    */   
/*    */   private final int[] damageReductionAmountArray;
/*    */   
/*    */   private final int enchantability;
/*    */   private final SoundEvent soundEvent;
/*    */   private final float toughness;
/*    */   private final Supplier<Ingredient> repairMaterial;
/*    */   
/*    */   public GenericArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> supplier) {
/* 28 */     this.name = name;
/* 29 */     this.maxDamageFactor = maxDamageFactor;
/* 30 */     this.damageReductionAmountArray = damageReductionAmountArray;
/* 31 */     this.enchantability = enchantability;
/* 32 */     this.soundEvent = soundEvent;
/* 33 */     this.toughness = toughness;
/* 34 */     this.repairMaterial = supplier;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200896_a(EquipmentSlotType slotIn) {
/* 40 */     return MAX_DAMAGE[slotIn.func_188454_b()] * this.maxDamageFactor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200902_b(EquipmentSlotType slotIn) {
/* 46 */     return this.damageReductionAmountArray[slotIn.func_188454_b()];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200900_a() {
/* 52 */     return this.enchantability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SoundEvent func_200899_b() {
/* 58 */     return this.soundEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ingredient func_200898_c() {
/* 64 */     return this.repairMaterial.get();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public String func_200897_d() {
/* 71 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_200901_e() {
/* 77 */     return this.toughness;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_230304_f_() {
/* 83 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\GenericArmorMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */