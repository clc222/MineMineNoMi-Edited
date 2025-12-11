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
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SetFruitClueFunction
/*     */   extends LootFunction {
/*     */   protected SetFruitClueFunction(ILootCondition[] conditionsIn) {
/*  29 */     super(conditionsIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/*  35 */     AkumaNoMiItem fruit = WyHelper.shuffle(new ArrayList(ModValues.DEVIL_FRUITS)).stream().findFirst().orElse(null);
/*  36 */     if (fruit != null) {
/*     */       
/*  38 */       DFEncyclopediaEntry template = fruit.getRandomElements((IWorld)context.func_202879_g());
/*     */       
/*  40 */       Optional<Integer> shape = Optional.empty();
/*  41 */       Optional<Color> baseColor = Optional.empty();
/*  42 */       Optional<Color> stemColor = Optional.empty();
/*     */       
/*  44 */       int maxRolls = 2;
/*  45 */       if (context.func_216032_b().nextInt(100) < 10) {
/*  46 */         maxRolls = 3;
/*     */       }
/*  48 */       int rolls = 1 + context.func_216032_b().nextInt(maxRolls);
/*     */       
/*  50 */       for (int i = 0; i < rolls; i++) {
/*     */         
/*  52 */         int rand = context.func_216032_b().nextInt(3);
/*  53 */         if (rand == 0) {
/*  54 */           shape = template.getShape();
/*  55 */         } else if (rand == 1) {
/*  56 */           baseColor = template.getBaseColor();
/*     */         } else {
/*  58 */           stemColor = template.getStemColor();
/*     */         } 
/*     */       } 
/*  61 */       String key = fruit.getFruitKey();
/*  62 */       CompoundNBT nbt = stack.func_190925_c("fruitClues");
/*  63 */       nbt.func_74778_a("key", key);
/*  64 */       if (shape.isPresent())
/*  65 */         nbt.func_74768_a("shape", ((Integer)shape.get()).intValue()); 
/*  66 */       if (baseColor.isPresent())
/*  67 */         nbt.func_74768_a("baseColor", ((Color)baseColor.get()).getRGB()); 
/*  68 */       if (stemColor.isPresent())
/*  69 */         nbt.func_74768_a("stemColor", ((Color)stemColor.get()).getRGB()); 
/*  70 */       stack.func_200302_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_FRUIT_CLUE));
/*  71 */       stack.func_190920_e(1);
/*     */     } 
/*     */     
/*  74 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LootFunctionType func_230425_b_() {
/*  80 */     return ModLootTypes.FRUIT_CLUE;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LootFunction.Builder<?> builder() {
/*  85 */     return func_215860_a(condition -> new SetFruitClueFunction(condition));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Serializer
/*     */     extends LootFunction.Serializer<SetFruitClueFunction>
/*     */   {
/*     */     public void serialize(JsonObject object, SetFruitClueFunction functionClazz, JsonSerializationContext serializationContext) {
/*  96 */       super.func_230424_a_(object, functionClazz, serializationContext);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public SetFruitClueFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 102 */       return new SetFruitClueFunction(conditionsIn);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetFruitClueFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */