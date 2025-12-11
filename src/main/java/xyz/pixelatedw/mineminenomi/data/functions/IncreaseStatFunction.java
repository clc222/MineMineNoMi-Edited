/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.LootFunction;
/*    */ import net.minecraft.loot.RandomRanges;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public abstract class IncreaseStatFunction
/*    */   extends LootFunction {
/*    */   protected IRandomRange amount;
/*    */   protected StatChangeSource source;
/*    */   protected boolean scaleDownChallengeCompletion = false;
/*    */   
/*    */   public IncreaseStatFunction(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/* 21 */     super(conditions);
/* 22 */     this.amount = amount;
/* 23 */     this.source = source;
/*    */   }
/*    */   
/*    */   public IRandomRange getAmount() {
/* 27 */     return this.amount;
/*    */   }
/*    */   
/*    */   public StatChangeSource getSource() {
/* 31 */     return this.source;
/*    */   }
/*    */   
/*    */   public boolean scalesDownChallengeCompletion() {
/* 35 */     return this.scaleDownChallengeCompletion;
/*    */   } @FunctionalInterface
/*    */   public static interface IFunctionFactory<F extends IncreaseStatFunction> {
/*    */     F create(ILootCondition[] param1ArrayOfILootCondition, IRandomRange param1IRandomRange, StatChangeSource param1StatChangeSource); } public int scaleValueFromCompletions(Challenge challenge, int amount) {
/* 39 */     int completions = challenge.getCompletions();
/* 40 */     completions = Math.min(completions, 10);
/* 41 */     if (completions > 0) {
/* 42 */       float d = completions * 1.5F;
/* 43 */       amount = (int)(amount / d);
/*    */     } 
/* 45 */     amount = Math.max(amount, 1);
/* 46 */     if (amount % 10 != 0 && amount > 10) {
/* 47 */       amount = WyHelper.roundToNiceNumber(amount);
/*    */     }
/* 49 */     return amount;
/*    */   }
/*    */   
/*    */   public static class Serializer<F extends IncreaseStatFunction>
/*    */     extends LootFunction.Serializer<F>
/*    */   {
/*    */     public Serializer(IncreaseStatFunction.IFunctionFactory<F> factory) {
/* 56 */       this.factory = factory;
/*    */     }
/*    */     private IncreaseStatFunction.IFunctionFactory<F> factory;
/*    */     
/*    */     public void serialize(JsonObject object, F func, JsonSerializationContext context) {
/* 61 */       super.func_230424_a_(object, (LootFunction)func, context);
/* 62 */       object.add("amount", context.serialize(func.getAmount()));
/* 63 */       object.add("source", context.serialize(func.getSource()));
/* 64 */       object.add("scaleDownChallengeCompletion", context.serialize(Boolean.valueOf(func.scalesDownChallengeCompletion())));
/*    */     }
/*    */ 
/*    */     
/*    */     public F deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] cond) {
/* 69 */       IRandomRange amount = RandomRanges.func_216130_a(object.get("amount"), context);
/* 70 */       StatChangeSource source = (StatChangeSource)context.deserialize(object.get("source"), StatChangeSource.class);
/* 71 */       F func = this.factory.create(cond, amount, source);
/* 72 */       if (object.has("scaleDownChallengeCompletion")) {
/* 73 */         ((IncreaseStatFunction)func).scaleDownChallengeCompletion = ((Boolean)context.deserialize(object.get("scaleDownChallengeCompletion"), Boolean.class)).booleanValue();
/*    */       } else {
/* 75 */         ((IncreaseStatFunction)func).scaleDownChallengeCompletion = false;
/*    */       } 
/* 77 */       return func;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\IncreaseStatFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */