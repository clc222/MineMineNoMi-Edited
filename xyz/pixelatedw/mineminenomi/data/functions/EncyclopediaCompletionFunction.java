/*     */ package xyz.pixelatedw.mineminenomi.data.functions;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootFunction;
/*     */ import net.minecraft.loot.LootFunctionType;
/*     */ import net.minecraft.loot.conditions.ILootCondition;
/*     */ import net.minecraft.loot.functions.ILootFunction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class EncyclopediaCompletionFunction
/*     */   extends LootFunction
/*     */ {
/*     */   protected EncyclopediaCompletionFunction(ILootCondition[] conditionsIn) {
/*  28 */     super(conditionsIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/*  34 */     int attempts = context.func_216032_b().nextInt(5) + 1;
/*  35 */     for (int i = 0; i < attempts; i++) {
/*     */       
/*  37 */       AkumaNoMiItem fruit = WyHelper.shuffle(new ArrayList(ModValues.DEVIL_FRUITS)).stream().findFirst().orElse(null);
/*  38 */       if (fruit != null) {
/*     */ 
/*     */         
/*  41 */         DFEncyclopediaEntry template = fruit.getRandomElements((IWorld)context.func_202879_g());
/*     */         
/*  43 */         Optional<Integer> shape = Optional.empty();
/*  44 */         Optional<Color> baseColor = Optional.empty();
/*  45 */         Optional<Color> stemColor = Optional.empty();
/*     */         
/*  47 */         int rolls = context.func_216032_b().nextInt(2);
/*  48 */         if (context.func_216032_b().nextInt(128) == 0)
/*  49 */           rolls = 3; 
/*  50 */         if (rolls < 3) {
/*     */ 
/*     */           
/*  53 */           for (int j = 0; j < rolls; j++) {
/*     */             
/*  55 */             int rand = context.func_216032_b().nextInt(3);
/*  56 */             if (rand == 0) {
/*  57 */               shape = template.getShape();
/*  58 */             } else if (rand == 1) {
/*  59 */               baseColor = template.getBaseColor();
/*     */             } else {
/*  61 */               stemColor = template.getStemColor();
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/*  66 */           shape = template.getShape();
/*  67 */           baseColor = template.getBaseColor();
/*  68 */           stemColor = template.getStemColor();
/*     */         } 
/*     */         
/*  71 */         if (shape.isPresent() || baseColor.isPresent() || stemColor.isPresent()) {
/*     */ 
/*     */           
/*  74 */           ResourceLocation key = fruit.getRegistryName();
/*  75 */           DFEncyclopediaEntry clue = new DFEncyclopediaEntry(shape, baseColor, stemColor);
/*  76 */           DFEncyclopediaItem.addFruitClues(stack, key, clue);
/*  77 */           stack.func_190920_e(1);
/*     */         } 
/*     */       } 
/*  80 */     }  return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LootFunctionType func_230425_b_() {
/*  86 */     return ModLootTypes.ENCYCLOPEDIA_COMPLETION;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LootFunction.Builder<?> builder() {
/*  91 */     return func_215860_a(condition -> new EncyclopediaCompletionFunction(condition));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Serializer
/*     */     extends LootFunction.Serializer<EncyclopediaCompletionFunction>
/*     */   {
/*     */     public void serialize(JsonObject object, EncyclopediaCompletionFunction functionClazz, JsonSerializationContext serializationContext) {
/* 102 */       super.func_230424_a_(object, functionClazz, serializationContext);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EncyclopediaCompletionFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 108 */       return new EncyclopediaCompletionFunction(conditionsIn);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\EncyclopediaCompletionFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */