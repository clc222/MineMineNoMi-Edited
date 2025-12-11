/*     */ package xyz.pixelatedw.mineminenomi.datagen;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.advancements.ICriterionInstance;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.data.CookingRecipeBuilder;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.IFinishedRecipe;
/*     */ import net.minecraft.data.RecipeProvider;
/*     */ import net.minecraft.data.ShapedRecipeBuilder;
/*     */ import net.minecraft.data.ShapelessRecipeBuilder;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.IRecipeSerializer;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.tags.ItemTags;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class ModRecipesDataGen extends RecipeProvider {
/*     */   public ModRecipesDataGen(DataGenerator generator) {
/*  26 */     super(generator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_200404_a(Consumer<IFinishedRecipe> consumer) {
/*  35 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.BULLET.get(), 8)
/*  36 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/*  37 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Blocks.field_150347_e)
/*  38 */       .func_200472_a("ic ")
/*  39 */       .func_200472_a("ci ")
/*  40 */       .func_200472_a("   ")
/*  41 */       .func_200465_a("has_iron_ingot", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/*  42 */       .func_200464_a(consumer);
/*     */     
/*  44 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.CANNON_BALL.get(), 4)
/*  45 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Blocks.field_150347_e)
/*  46 */       .func_200472_a("cc ")
/*  47 */       .func_200472_a("cc ")
/*  48 */       .func_200472_a("   ")
/*  49 */       .func_200465_a("has_cobblestone", (ICriterionInstance)func_200403_a((IItemProvider)Blocks.field_150347_e.func_199767_j()))
/*  50 */       .func_200464_a(consumer);
/*     */     
/*  52 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get(), 8)
/*  53 */       .func_200462_a(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI.get())
/*  54 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Blocks.field_150347_e)
/*  55 */       .func_200472_a("kc ")
/*  56 */       .func_200472_a("ck ")
/*  57 */       .func_200472_a("   ")
/*  58 */       .func_200465_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get()))
/*  59 */       .func_200464_a(consumer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.DENSE_KAIROSEKI.get(), 1)
/*  66 */       .func_200462_a(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI.get())
/*  67 */       .func_200472_a("kk ")
/*  68 */       .func_200472_a("kk ")
/*  69 */       .func_200472_a("   ")
/*  70 */       .func_200465_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.KAIROSEKI.get()))
/*  71 */       .func_200464_a(consumer);
/*     */     
/*  73 */     ShapelessRecipeBuilder.func_200488_a((IItemProvider)ModItems.KAIROSEKI.get(), 9)
/*  74 */       .func_200487_b((IItemProvider)ModBlocks.KAIROSEKI.get())
/*  75 */       .func_200483_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.KAIROSEKI.get()))
/*  76 */       .func_200482_a(consumer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.KAIROSEKI_BARS.get(), 3)
/*  83 */       .func_200462_a(Character.valueOf('k'), (IItemProvider)ModItems.DENSE_KAIROSEKI.get())
/*  84 */       .func_200472_a("kkk")
/*  85 */       .func_200472_a("kkk")
/*  86 */       .func_200472_a("   ")
/*  87 */       .func_200465_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.DENSE_KAIROSEKI.get()))
/*  88 */       .func_200464_a(consumer);
/*     */     
/*  90 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.KAIROSEKI.get(), 1)
/*  91 */       .func_200462_a(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI.get())
/*  92 */       .func_200472_a("kkk")
/*  93 */       .func_200472_a("kkk")
/*  94 */       .func_200472_a("kkk")
/*  95 */       .func_200465_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.KAIROSEKI.get()))
/*  96 */       .func_200464_a(consumer);
/*     */     
/*  98 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.WANTED_POSTER.get(), 1)
/*  99 */       .func_200462_a(Character.valueOf('f'), (IItemProvider)ModBlocks.WANTED_POSTER.get())
/* 100 */       .func_200462_a(Character.valueOf('w'), (IItemProvider)Items.field_151121_aF)
/* 101 */       .func_200472_a("www")
/* 102 */       .func_200472_a("wfw")
/* 103 */       .func_200472_a("www")
/* 104 */       .func_200465_a("has_poster", (ICriterionInstance)func_200403_a((IItemProvider)ModBlocks.WANTED_POSTER.get()))
/* 105 */       .func_200467_a(consumer, new ResourceLocation("mineminenomi", "wanted_poster_upgrade"));
/*     */     
/* 107 */     ShapelessRecipeBuilder.func_200488_a((IItemProvider)ModBlocks.MANGROVE_PLANKS.get(), 4)
/* 108 */       .func_203221_a((ITag)ModTags.Items.MANGROVE_LOGS)
/* 109 */       .func_200490_a("planks")
/* 110 */       .func_200483_a("has_log", (ICriterionInstance)func_200409_a((ITag)ModTags.Items.MANGROVE_LOGS))
/* 111 */       .func_200482_a(consumer);
/*     */     
/* 113 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.MANGROVE_WOOD.get(), 3)
/* 114 */       .func_200462_a(Character.valueOf('#'), (IItemProvider)ModBlocks.MANGROVE_LOG.get())
/* 115 */       .func_200472_a("##")
/* 116 */       .func_200472_a("##")
/* 117 */       .func_200473_b("bark")
/* 118 */       .func_200465_a("has_log", (ICriterionInstance)func_200403_a((IItemProvider)ModBlocks.MANGROVE_LOG.get()))
/* 119 */       .func_200464_a(consumer);
/*     */     
/* 121 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.STRIPPED_MANGROVE_WOOD.get(), 3)
/* 122 */       .func_200462_a(Character.valueOf('#'), (IItemProvider)ModBlocks.STRIPPED_MANGROVE_LOG.get())
/* 123 */       .func_200472_a("##")
/* 124 */       .func_200472_a("##")
/* 125 */       .func_200473_b("bark")
/* 126 */       .func_200465_a("has_log", (ICriterionInstance)func_200403_a((IItemProvider)ModBlocks.STRIPPED_MANGROVE_LOG.get()))
/* 127 */       .func_200464_a(consumer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     CookingRecipeBuilder.func_218629_c(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)ModItems.SEA_KING_MEAT.get() }, ), (IItemProvider)ModItems.COOKED_SEA_KING_MEAT.get(), 0.5F, 500)
/* 134 */       .func_218628_a("has_seaking_meat", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.SEA_KING_MEAT.get()))
/* 135 */       .func_218635_a(consumer, new ResourceLocation("mineminenomi", "cooked_sea_king_meat"));
/*     */     
/* 137 */     CookingRecipeBuilder.func_218631_a(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)ModItems.SEA_KING_MEAT.get() }, ), (IItemProvider)ModItems.COOKED_SEA_KING_MEAT.get(), 0.5F, 1000, IRecipeSerializer.field_222174_r)
/* 138 */       .func_218628_a("has_seaking_meat", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.SEA_KING_MEAT.get()))
/* 139 */       .func_218635_a(consumer, new ResourceLocation("mineminenomi", "cooked_sea_king_meat_from_campfire_cooking"));
/*     */     
/* 141 */     CookingRecipeBuilder.func_218631_a(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)ModItems.SEA_KING_MEAT.get() }, ), (IItemProvider)ModItems.COOKED_SEA_KING_MEAT.get(), 0.5F, 200, IRecipeSerializer.field_222173_q)
/* 142 */       .func_218628_a("has_seaking_meat", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.SEA_KING_MEAT.get()))
/* 143 */       .func_218635_a(consumer, new ResourceLocation("mineminenomi", "cooked_sea_king_meat_from_smoking"));
/*     */     
/* 145 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.COLA.get(), 1)
/* 146 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151102_aT)
/* 147 */       .func_200462_a(Character.valueOf('b'), (IItemProvider)Items.field_151069_bo)
/* 148 */       .func_200472_a(" s ")
/* 149 */       .func_200472_a(" s ")
/* 150 */       .func_200472_a(" b ")
/* 151 */       .func_200465_a("has_sugar", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151102_aT))
/* 152 */       .func_200465_a("has_bottle", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151069_bo))
/* 153 */       .func_200464_a(consumer);
/*     */     
/* 155 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.ULTRA_COLA.get(), 1)
/* 156 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151102_aT)
/* 157 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)ModItems.COLA.get())
/* 158 */       .func_200472_a("sss")
/* 159 */       .func_200472_a("sss")
/* 160 */       .func_200472_a("scs")
/* 161 */       .func_200465_a("has_cola", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.COLA.get()))
/* 162 */       .func_200464_a(consumer);
/*     */     
/* 164 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.SAKE_CUP.get(), 1)
/* 165 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Items.field_151119_aD)
/* 166 */       .func_200472_a("   ")
/* 167 */       .func_200472_a("c c")
/* 168 */       .func_200472_a(" c ")
/* 169 */       .func_200465_a("has_clay", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151119_aD))
/* 170 */       .func_200464_a(consumer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.CANNON.get(), 1)
/* 177 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151007_F)
/* 178 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_221698_bk)
/* 179 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Blocks.field_150347_e)
/* 180 */       .func_200472_a("s  ")
/* 181 */       .func_200472_a("iii")
/* 182 */       .func_200472_a("ccc")
/* 183 */       .func_200465_a("has_iron_ingot", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/* 184 */       .func_200464_a(consumer);
/*     */     
/* 186 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModArmors.COLA_BACKPACK.get(), 1)
/* 187 */       .func_200462_a(Character.valueOf('u'), (IItemProvider)ModItems.ULTRA_COLA.get())
/* 188 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/* 189 */       .func_200472_a("u u")
/* 190 */       .func_200472_a("uiu")
/* 191 */       .func_200472_a("u u")
/* 192 */       .func_200465_a("has_iron", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/* 193 */       .func_200465_a("has_ultra_cola", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.ULTRA_COLA.get()))
/* 194 */       .func_200464_a(consumer);
/*     */     
/* 196 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModArmors.MEDIC_BAG.get(), 1)
/* 197 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151007_F)
/* 198 */       .func_200462_a(Character.valueOf('l'), (IItemProvider)Items.field_151116_aA)
/* 199 */       .func_200462_a(Character.valueOf('b'), (IItemProvider)Items.field_196128_bn)
/* 200 */       .func_200472_a("s s")
/* 201 */       .func_200472_a("lbl")
/* 202 */       .func_200472_a("lll")
/* 203 */       .func_200465_a("has_lapis", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_196128_bn))
/* 204 */       .func_200465_a("has_leather", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151116_aA))
/* 205 */       .func_200464_a(consumer);
/*     */     
/* 207 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get(), 1)
/* 208 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/* 209 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Items.field_234729_dO_)
/* 210 */       .func_200472_a("   ")
/* 211 */       .func_200472_a("ici")
/* 212 */       .func_200472_a("   ")
/* 213 */       .func_200465_a("has_iron", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/* 214 */       .func_200464_a(consumer);
/*     */     
/* 216 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.KAIROSEKI_HANDCUFFS.get(), 1)
/* 217 */       .func_200462_a(Character.valueOf('k'), (IItemProvider)ModItems.DENSE_KAIROSEKI.get())
/* 218 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Items.field_234729_dO_)
/* 219 */       .func_200472_a("   ")
/* 220 */       .func_200472_a("kck")
/* 221 */       .func_200472_a("   ")
/* 222 */       .func_200465_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.DENSE_KAIROSEKI.get()))
/* 223 */       .func_200464_a(consumer);
/*     */     
/* 225 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.JITTE.get(), 1)
/* 226 */       .func_200462_a(Character.valueOf('k'), (IItemProvider)ModItems.DENSE_KAIROSEKI.get())
/* 227 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/* 228 */       .func_200472_a(" k ")
/* 229 */       .func_200472_a(" i ")
/* 230 */       .func_200472_a(" i ")
/* 231 */       .func_200465_a("has_kairoseki", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.DENSE_KAIROSEKI.get()))
/* 232 */       .func_200464_a(consumer);
/*     */     
/* 234 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.SCISSORS.get(), 1)
/* 235 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)Items.field_221585_m)
/* 236 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/* 237 */       .func_200472_a(" ii")
/* 238 */       .func_200472_a("cii")
/* 239 */       .func_200472_a("cc ")
/* 240 */       .func_200465_a("has_iron", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/* 241 */       .func_200464_a(consumer);
/*     */     
/* 243 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.PIPE.get(), 1)
/* 244 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/* 245 */       .func_200472_a(" i ")
/* 246 */       .func_200472_a(" i ")
/* 247 */       .func_200472_a(" i ")
/* 248 */       .func_200465_a("has_iron", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/* 249 */       .func_200464_a(consumer);
/*     */     
/* 251 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.MACE.get(), 1)
/* 252 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151055_y)
/* 253 */       .func_200462_a(Character.valueOf('i'), (IItemProvider)Items.field_151042_j)
/* 254 */       .func_200462_a(Character.valueOf('b'), (IItemProvider)Items.field_221698_bk)
/* 255 */       .func_200472_a(" b ")
/* 256 */       .func_200472_a(" i ")
/* 257 */       .func_200472_a(" s ")
/* 258 */       .func_200465_a("has_iron", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151042_j))
/* 259 */       .func_200464_a(consumer);
/*     */     
/* 261 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.UMBRELLA.get(), 1)
/* 262 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151055_y)
/* 263 */       .func_200469_a(Character.valueOf('w'), (ITag)ItemTags.field_199904_a)
/* 264 */       .func_200472_a("www")
/* 265 */       .func_200472_a(" s ")
/* 266 */       .func_200472_a(" s ")
/* 267 */       .func_200465_a("has_stick", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151055_y))
/* 268 */       .func_200465_a("has_wool", (ICriterionInstance)func_200409_a((ITag)ItemTags.field_199904_a))
/* 269 */       .func_200464_a(consumer);
/*     */     
/* 271 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.CLIMA_TACT.get(), 1)
/* 272 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151055_y)
/* 273 */       .func_200462_a(Character.valueOf('b'), (IItemProvider)Items.field_196128_bn)
/* 274 */       .func_200472_a("bsb")
/* 275 */       .func_200472_a("bsb")
/* 276 */       .func_200472_a("bsb")
/* 277 */       .func_200465_a("has_lapis", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_196128_bn))
/* 278 */       .func_200464_a(consumer);
/*     */     
/* 280 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.PERFECT_CLIMA_TACT.get(), 1)
/* 281 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)ModWeapons.CLIMA_TACT.get())
/* 282 */       .func_200462_a(Character.valueOf('b'), (IItemProvider)ModBlocks.BREATH_DIAL.get())
/* 283 */       .func_200462_a(Character.valueOf('f'), (IItemProvider)ModBlocks.FLAME_DIAL.get())
/* 284 */       .func_200462_a(Character.valueOf('e'), (IItemProvider)ModBlocks.EISEN_DIAL.get())
/* 285 */       .func_200462_a(Character.valueOf('l'), (IItemProvider)ModBlocks.FLASH_DIAL.get())
/* 286 */       .func_200462_a(Character.valueOf('m'), (IItemProvider)ModBlocks.MILKY_DIAL.get())
/* 287 */       .func_200472_a("eme")
/* 288 */       .func_200472_a("bcf")
/* 289 */       .func_200472_a("ele")
/* 290 */       .func_200465_a("has_clima_tact", (ICriterionInstance)func_200403_a((IItemProvider)ModWeapons.CLIMA_TACT.get()))
/* 291 */       .func_200464_a(consumer);
/*     */     
/* 293 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT.get(), 1)
/* 294 */       .func_200462_a(Character.valueOf('c'), (IItemProvider)ModWeapons.PERFECT_CLIMA_TACT.get())
/* 295 */       .func_200462_a(Character.valueOf('g'), (IItemProvider)Items.field_151043_k)
/* 296 */       .func_200472_a("ggg")
/* 297 */       .func_200472_a("gcg")
/* 298 */       .func_200472_a("ggg")
/* 299 */       .func_200465_a("has_perfect_clima_tact", (ICriterionInstance)func_200403_a((IItemProvider)ModWeapons.PERFECT_CLIMA_TACT.get()))
/* 300 */       .func_200464_a(consumer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     ShapelessRecipeBuilder.func_200488_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get(), 1)
/* 307 */       .func_200487_b((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 308 */       .func_200487_b((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 309 */       .func_200483_a("has_encyclopedia", (ICriterionInstance)func_200403_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get()))
/* 310 */       .func_200482_a(consumer);
/*     */     
/* 312 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.FLAG.get(), 1)
/* 313 */       .func_200462_a(Character.valueOf('s'), (IItemProvider)Items.field_151055_y)
/* 314 */       .func_200469_a(Character.valueOf('w'), (ITag)ItemTags.field_199904_a)
/* 315 */       .func_200472_a("sww")
/* 316 */       .func_200472_a("sww")
/* 317 */       .func_200472_a("s  ")
/* 318 */       .func_200465_a("has_wool", (ICriterionInstance)func_200409_a((ITag)ItemTags.field_199904_a))
/* 319 */       .func_200464_a(consumer);
/*     */     
/* 321 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModBlocks.FLAG.get(), 1)
/* 322 */       .func_200462_a(Character.valueOf('f'), (IItemProvider)ModBlocks.FLAG.get())
/* 323 */       .func_200469_a(Character.valueOf('w'), (ITag)ItemTags.field_199904_a)
/* 324 */       .func_200472_a("www")
/* 325 */       .func_200472_a("wfw")
/* 326 */       .func_200472_a("www")
/* 327 */       .func_200465_a("has_flag", (ICriterionInstance)func_200403_a((IItemProvider)ModBlocks.FLAG.get()))
/* 328 */       .func_200467_a(consumer, new ResourceLocation("mineminenomi", "flag_upgrade"));
/*     */     
/* 330 */     ShapedRecipeBuilder.func_200468_a((IItemProvider)ModItems.KEY.get(), 1)
/* 331 */       .func_200462_a(Character.valueOf('g'), (IItemProvider)Items.field_151043_k)
/* 332 */       .func_200472_a(" g ")
/* 333 */       .func_200472_a(" g ")
/* 334 */       .func_200472_a(" g ")
/* 335 */       .func_200465_a("has_gold", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151043_k))
/* 336 */       .func_200464_a(consumer);
/*     */     
/* 338 */     ShapelessRecipeBuilder.func_200488_a((IItemProvider)ModItems.VIVRE_CARD.get(), 1)
/* 339 */       .func_200487_b((IItemProvider)Items.field_151121_aF)
/* 340 */       .func_200483_a("has_paper", (ICriterionInstance)func_200403_a((IItemProvider)Items.field_151121_aF))
/* 341 */       .func_200482_a(consumer);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\ModRecipesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */