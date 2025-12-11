/*     */ package xyz.pixelatedw.mineminenomi.data.functions;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootFunction;
/*     */ import net.minecraft.loot.LootFunctionType;
/*     */ import net.minecraft.loot.LootParameters;
/*     */ import net.minecraft.loot.conditions.ILootCondition;
/*     */ import net.minecraft.loot.functions.ILootFunction;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.nbt.StringNBT;
/*     */ import net.minecraft.util.JSONUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*     */ 
/*     */ public class UnlockChallengesFunction extends LootFunction {
/*     */   protected UnlockChallengesFunction(ILootCondition[] conditions, ChallengeCore<?>[] unlocks) {
/*  31 */     super(conditions);
/*  32 */     this.unlocks = unlocks;
/*     */   }
/*     */   private ChallengeCore<?>[] unlocks;
/*     */   
/*     */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/*  37 */     Entity entity = (Entity)context.func_216031_c(LootParameters.field_216281_a);
/*  38 */     if (entity != null && entity instanceof PlayerEntity) {
/*  39 */       PlayerEntity player = (PlayerEntity)entity;
/*  40 */       IChallengesData props = ChallengesDataCapability.get(player);
/*     */       
/*  42 */       for (ChallengeCore<?> core : this.unlocks) {
/*  43 */         if (core != null)
/*     */         {
/*     */           
/*  46 */           props.addChallenge(core);
/*     */         }
/*     */       } 
/*  49 */       stack.func_200302_a((ITextComponent)new StringTextComponent("_rewards"));
/*  50 */       stack.func_196082_o().func_74768_a("_unlocksAmount", this.unlocks.length);
/*  51 */       ListNBT unlocksNbt = new ListNBT();
/*  52 */       for (ChallengeCore<?> core : this.unlocks) {
/*  53 */         if (core != null) {
/*     */ 
/*     */           
/*  56 */           StringNBT stringNbt = StringNBT.func_229705_a_(core.getLocalizedTitle().getString());
/*  57 */           unlocksNbt.add(stringNbt);
/*     */         } 
/*  59 */       }  stack.func_196082_o().func_218657_a("_unlocks", (INBT)unlocksNbt);
/*     */     } 
/*  61 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootFunctionType func_230425_b_() {
/*  66 */     return ModLootTypes.UNLOCK_CHALLENGES;
/*     */   }
/*     */   
/*     */   public static LootFunction.Builder<?> builder(ChallengeCore<?>... unlocks) {
/*  70 */     return func_215860_a(condition -> new UnlockChallengesFunction(condition, (ChallengeCore<?>[])unlocks));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Serializer
/*     */     extends LootFunction.Serializer<UnlockChallengesFunction>
/*     */   {
/*     */     public void serialize(JsonObject object, UnlockChallengesFunction func, JsonSerializationContext context) {
/*  78 */       super.func_230424_a_(object, func, context);
/*     */       
/*  80 */       JsonArray unlocks = new JsonArray();
/*     */       
/*  82 */       for (ChallengeCore<?> core : func.unlocks) {
/*  83 */         if (core != null)
/*     */         {
/*     */           
/*  86 */           unlocks.add(core.getRegistryName().toString());
/*     */         }
/*     */       } 
/*  89 */       object.add("unlocks", (JsonElement)unlocks);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public UnlockChallengesFunction deserialize(JsonObject object, JsonDeserializationContext context, ILootCondition[] cond) {
/*  95 */       JsonArray unlocksJson = JSONUtils.func_151213_a(object, "unlocks", new JsonArray());
/*  96 */       ChallengeCore[] unlocksId = new ChallengeCore[unlocksJson.size()];
/*     */       
/*  98 */       for (int i = 0; i < unlocksId.length; i++) {
/*  99 */         ResourceLocation id = new ResourceLocation(JSONUtils.func_151206_a(unlocksJson.get(i), "unlocks[" + i + "]"));
/* 100 */         unlocksId[i] = (ChallengeCore)ModRegistries.CHALLENGES.getValue(id);
/*     */       } 
/*     */       
/* 103 */       return new UnlockChallengesFunction(cond, (ChallengeCore<?>[])unlocksId);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\UnlockChallengesFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */