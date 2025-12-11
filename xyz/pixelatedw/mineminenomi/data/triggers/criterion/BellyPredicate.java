/*    */ package xyz.pixelatedw.mineminenomi.data.triggers.criterion;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.advancements.criterion.MinMaxBounds;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.JSONUtils;
/*    */ 
/*    */ 
/*    */ public class BellyPredicate
/*    */ {
/* 14 */   public static final BellyPredicate ANY = new BellyPredicate(MinMaxBounds.IntBound.field_211347_e, Boolean.FALSE);
/*    */   private final MinMaxBounds.IntBound amount;
/*    */   private final Boolean isBountyReward;
/*    */   
/*    */   public BellyPredicate(MinMaxBounds.IntBound amount, Boolean isBountyReward) {
/* 19 */     this.amount = amount;
/* 20 */     this.isBountyReward = isBountyReward;
/*    */   }
/*    */   
/*    */   public boolean matches(ServerPlayerEntity player, int amount, boolean isBountyReward) {
/* 24 */     if (this == ANY) {
/* 25 */       return true;
/*    */     }
/* 27 */     if (!this.amount.func_211339_d(amount)) {
/* 28 */       return false;
/*    */     }
/* 30 */     return true;
/*    */   }
/*    */   
/*    */   public JsonElement serializeToJson() {
/* 34 */     if (this == ANY) {
/* 35 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 38 */     JsonObject jsonobject = new JsonObject();
/* 39 */     jsonobject.add("amount", this.amount.func_200321_c());
/* 40 */     if (this.isBountyReward != null) {
/* 41 */       jsonobject.addProperty("isBountyReward", this.isBountyReward);
/*    */     }
/* 43 */     return (JsonElement)jsonobject;
/*    */   }
/*    */ 
/*    */   
/*    */   public static BellyPredicate fromJson(@Nullable JsonElement element) {
/* 48 */     if (element != null && !element.isJsonNull()) {
/* 49 */       JsonObject jsonobject = JSONUtils.func_151210_l(element, "belly");
/* 50 */       MinMaxBounds.IntBound amount = MinMaxBounds.IntBound.func_211344_a(jsonobject.get("amount"));
/* 51 */       Boolean isBountyReward = jsonobject.has("isBountyReward") ? Boolean.valueOf(JSONUtils.func_151212_i(jsonobject, "isBountyReward")) : null;
/* 52 */       return new BellyPredicate(amount, isBountyReward);
/*    */     } 
/*    */     
/* 55 */     return ANY;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\criterion\BellyPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */