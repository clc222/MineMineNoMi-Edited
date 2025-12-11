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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ 
/*    */ public class SetMarineColorFunction
/*    */   extends LootFunction {
/*    */   protected SetMarineColorFunction(ILootCondition[] conditionsIn) {
/* 18 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 23 */     if (stack.func_77973_b() instanceof net.minecraft.item.IDyeableArmorItem) {
/* 24 */       stack.func_190925_c("display").func_74768_a("color", MobsHelper.MARINE_BLUE_COLOR.getRGB());
/*    */     }
/* 26 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 31 */     return ModLootTypes.SET_MARINE_COLOR;
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder() {
/* 35 */     return func_215860_a(condition -> new SetMarineColorFunction(condition));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetMarineColorFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, SetMarineColorFunction functionClazz, JsonSerializationContext serializationContext) {
/* 43 */       super.func_230424_a_(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */     
/*    */     public SetMarineColorFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 48 */       return new SetMarineColorFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetMarineColorFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */