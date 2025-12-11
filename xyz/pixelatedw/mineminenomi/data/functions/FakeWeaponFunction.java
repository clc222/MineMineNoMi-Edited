/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.LootFunction;
/*    */ import net.minecraft.loot.LootFunctionType;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ 
/*    */ public class FakeWeaponFunction
/*    */   extends LootFunction
/*    */ {
/*    */   protected FakeWeaponFunction(ILootCondition[] conditionsIn) {
/* 18 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 24 */     stack.func_196082_o().func_74757_a("isClone", true);
/* 25 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 31 */     return ModLootTypes.FAKE_WEAPON;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder() {
/* 36 */     return func_215860_a(condition -> new FakeWeaponFunction(condition));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<FakeWeaponFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, FakeWeaponFunction functionClazz, JsonSerializationContext serializationContext) {
/* 47 */       super.func_230424_a_(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public FakeWeaponFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 53 */       return new FakeWeaponFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\FakeWeaponFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */