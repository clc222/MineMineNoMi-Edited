/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import net.minecraft.client.world.ClientWorld;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemModelsProperties;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gasu.BlueSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModItemModelProps
/*     */ {
/*     */   private static final IItemPropertyGetter BLUE_GORO = (itemStack, world, livingEntity) -> CommonConfig.INSTANCE.getRandomizedFruits() ? 0.0F : (ClientConfig.INSTANCE.isGoroBlue() ? 1.0F : 0.0F);
/*     */   private static final IItemPropertyGetter BELLY_POUCH_SIZE;
/*     */   private static final IItemPropertyGetter EXTOL_POUCH_SIZE;
/*     */   private static final IItemPropertyGetter DEVIL_FRUITS_RANDOMIZER;
/*     */   private static final IItemPropertyGetter SAKE_CUP_FILLED;
/*     */   
/*     */   static {
/*  35 */     BELLY_POUCH_SIZE = ((itemStack, world, livingEntity) -> {
/*     */         if (livingEntity == null) {
/*     */           return 0.0F;
/*     */         }
/*     */         
/*     */         long belly = itemStack.func_196082_o().func_74763_f("belly");
/*     */         
/*     */         int size = 0;
/*     */         
/*     */         if (belly > 1000L && belly < 5000L) {
/*     */           size = 1;
/*     */         } else if (belly >= 5000L) {
/*     */           size = 2;
/*     */         } 
/*     */         
/*     */         return size;
/*     */       });
/*     */     
/*  53 */     EXTOL_POUCH_SIZE = ((itemStack, world, livingEntity) -> {
/*     */         if (livingEntity == null) {
/*     */           return 0.0F;
/*     */         }
/*     */         
/*     */         long extol = itemStack.func_196082_o().func_74763_f("extol");
/*     */         
/*     */         long belly = CurrencyHelper.getBellyFromExtol(extol);
/*     */         
/*     */         int size = 0;
/*     */         
/*     */         if (belly > 1000L && belly < 5000L) {
/*     */           size = 1;
/*     */         } else if (belly >= 5000L) {
/*     */           size = 2;
/*     */         } 
/*     */         
/*     */         return size;
/*     */       });
/*  72 */     DEVIL_FRUITS_RANDOMIZER = ((itemStack, world, livingEntity) -> {
/*     */         if (itemStack == null || itemStack.func_190926_b() || !(itemStack.func_77973_b() instanceof AkumaNoMiItem)) {
/*     */           return 1.0F;
/*     */         }
/*     */         
/*     */         if (!RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*     */           AkumaNoMiItem fruit = (AkumaNoMiItem)itemStack.func_77973_b();
/*     */           
/*     */           fruit.removeBaseColor(itemStack);
/*     */           
/*     */           fruit.removeStemColor(itemStack);
/*     */           return -1.0F;
/*     */         } 
/*     */         if (world == null && livingEntity != null) {
/*     */           world = (ClientWorld)livingEntity.field_70170_p;
/*     */         }
/*     */         ((AkumaNoMiItem)itemStack.func_77973_b()).applyRandomness((World)world, itemStack);
/*     */         return itemStack.func_196082_o().func_74762_e("type");
/*     */       });
/*  91 */     SAKE_CUP_FILLED = ((itemStack, world, livingEntity) -> 
/*     */       
/*  93 */       (itemStack.func_77978_p() != null && !Strings.isNullOrEmpty(itemStack.func_77978_p().func_74779_i("leader"))) ? 1.0F : 0.0F);
/*     */ 
/*     */     
/*  96 */     SHEATHED_WEAPON = ((itemStack, world, livingEntity) -> {
/*     */         if (livingEntity == null) {
/*     */           return 1.0F;
/*     */         }
/*     */         
/*     */         boolean mainHandFlag = ItemStack.func_179545_c(livingEntity.func_184614_ca(), itemStack);
/*     */         
/*     */         boolean offHandFlag = ItemStack.func_179545_c(livingEntity.func_184592_cb(), itemStack);
/* 104 */         return (mainHandFlag || offHandFlag) ? 0.0F : 1.0F;
/*     */       });
/*     */     
/* 107 */     HAKI_WEAPON = ((itemStack, world, livingEntity) -> {
/*     */         if (livingEntity == null) {
/*     */           return 0.0F;
/*     */         }
/*     */         
/*     */         float hasHakiActive = 0.0F;
/*     */         
/*     */         if (livingEntity instanceof net.minecraft.entity.player.PlayerEntity) {
/*     */           boolean mainHandFlag = (livingEntity.func_184614_ca() == itemStack);
/*     */           boolean offHandFlag = (livingEntity.func_184592_cb() == itemStack);
/*     */           boolean hakiActiveFlag = HakiHelper.hasImbuingActive(livingEntity);
/* 118 */           hasHakiActive = ((mainHandFlag || offHandFlag) && hakiActiveFlag) ? 1.0F : 0.0F;
/*     */         } else if (livingEntity instanceof OPEntity) {
/*     */           hasHakiActive = ((OPEntity)livingEntity).hasBusoHaki() ? 1.0F : 0.0F;
/*     */         } 
/*     */ 
/*     */         
/*     */         return hasHakiActive;
/*     */       });
/*     */     
/* 127 */     UMBRELLA_OPEN = ((itemStack, world, livingEntity) -> {
/*     */         if (livingEntity == null) {
/*     */           return 0.0F;
/*     */         }
/*     */         
/*     */         boolean mainHandFlag = (livingEntity.func_184614_ca() == itemStack);
/*     */         boolean offHandFlag = (livingEntity.func_184592_cb() == itemStack);
/* 134 */         boolean isInAir = (!livingEntity.func_233570_aj_() && (livingEntity.func_213322_ci()).field_72448_b < 0.0D);
/*     */         
/*     */         BlockPos blockpos = livingEntity.func_233580_cy_();
/* 137 */         boolean isRaining = (livingEntity.field_70170_p.func_175727_C(blockpos) || livingEntity.field_70170_p.func_175727_C(new BlockPos(blockpos.func_177958_n(), (livingEntity.func_174813_aQ()).field_72337_e, blockpos.func_177952_p())));
/* 138 */         boolean checkMainHand = (!livingEntity.func_184614_ca().func_190926_b() && livingEntity.func_184614_ca().func_77973_b() == ModWeapons.UMBRELLA.get());
/* 139 */         boolean checkOffHand = (!livingEntity.func_184592_cb().func_190926_b() && livingEntity.func_184592_cb().func_77973_b() == ModWeapons.UMBRELLA.get());
/* 140 */         boolean holdsUmbrella = (checkMainHand || checkOffHand);
/*     */         
/* 142 */         return (((mainHandFlag || offHandFlag) && isInAir) || (isRaining && holdsUmbrella)) ? 1.0F : 0.0F;
/*     */       });
/*     */     
/* 145 */     BLUE_SWORD_WEAPON = ((itemStack, world, livingEntity) -> {
/*     */         if (livingEntity == null) {
/*     */           return 1.0F;
/*     */         }
/*     */         
/*     */         boolean mainHandFlag = ItemStack.func_179545_c(livingEntity.func_184614_ca(), itemStack);
/*     */         
/*     */         boolean offHandFlag = ItemStack.func_179545_c(livingEntity.func_184592_cb(), itemStack);
/*     */         
/*     */         boolean hasAbl = AbilityDataCapability.get(livingEntity).hasUnlockedAbility(BlueSwordAbility.INSTANCE);
/* 155 */         return ((mainHandFlag || offHandFlag) && hasAbl) ? 0.0F : 1.0F;
/*     */       });
/*     */     
/* 158 */     PULL = ((itemStack, world, livingEntity) -> (livingEntity == null) ? 0.0F : ((livingEntity.func_184607_cu() != itemStack) ? 0.0F : ((itemStack.func_77988_m() - livingEntity.func_184605_cv()) / 20.0F)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     PULLING = ((itemStack, world, livingEntity) -> 
/* 167 */       (livingEntity != null && livingEntity.func_184587_cr() && livingEntity.func_184607_cu() == itemStack) ? 1.0F : 0.0F);
/*     */   }
/*     */   private static final IItemPropertyGetter SHEATHED_WEAPON; private static final IItemPropertyGetter HAKI_WEAPON; private static final IItemPropertyGetter UMBRELLA_OPEN;
/*     */   private static final IItemPropertyGetter BLUE_SWORD_WEAPON;
/*     */   private static final IItemPropertyGetter PULL;
/*     */   private static final IItemPropertyGetter PULLING;
/*     */   private static final IItemPropertyGetter LOLLIPOP = (itemStack, world, livingEntity) -> WyHelper.isAprilFirst() ? 1.0F : 0.0F;
/*     */   
/*     */   public static void register() {
/* 176 */     for (AkumaNoMiItem df : ModValues.DEVIL_FRUITS) {
/* 177 */       ItemModelsProperties.func_239418_a_((Item)df, new ResourceLocation("type"), DEVIL_FRUITS_RANDOMIZER);
/*     */     }
/*     */     
/* 180 */     ItemModelsProperties.func_239418_a_((Item)ModAbilities.GORO_GORO_NO_MI, new ResourceLocation("alt_texture"), BLUE_GORO);
/*     */ 
/*     */     
/* 183 */     ItemModelsProperties.func_239418_a_((Item)ModItems.BELLY_POUCH.get(), new ResourceLocation("size"), BELLY_POUCH_SIZE);
/* 184 */     ItemModelsProperties.func_239418_a_((Item)ModItems.EXTOL_POUCH.get(), new ResourceLocation("size"), EXTOL_POUCH_SIZE);
/* 185 */     ItemModelsProperties.func_239418_a_((Item)ModItems.SAKE_CUP.get(), new ResourceLocation("filled"), SAKE_CUP_FILLED);
/*     */ 
/*     */ 
/*     */     
/* 189 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.UMBRELLA.get(), new ResourceLocation("open"), UMBRELLA_OPEN);
/*     */     
/* 191 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SORCERY_CLIMA_TACT.get(), new ResourceLocation("open"), SHEATHED_WEAPON);
/*     */     
/* 193 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.GREEN_KUJA_BOW.get(), new ResourceLocation("pull"), PULL);
/* 194 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.GREEN_KUJA_BOW.get(), new ResourceLocation("pulling"), PULLING);
/* 195 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.KABUTO.get(), new ResourceLocation("pull"), PULL);
/* 196 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.KABUTO.get(), new ResourceLocation("pulling"), PULLING);
/* 197 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.BLACK_KABUTO.get(), new ResourceLocation("pull"), PULL);
/* 198 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.BLACK_KABUTO.get(), new ResourceLocation("pulling"), PULLING);
/* 199 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.GINGA_PACHINKO.get(), new ResourceLocation("pull"), PULL);
/* 200 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.GINGA_PACHINKO.get(), new ResourceLocation("pulling"), PULLING);
/*     */     
/* 202 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MARINE_SWORD.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 203 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MARINE_SWORD.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 204 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.PIRATE_CUTLASS.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 205 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.PIRATE_CUTLASS.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 206 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.PIPE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 207 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SCISSORS.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 208 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.KIKOKU.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 209 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.KIKOKU.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 210 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.KIRIBACHI.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 211 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.YORU.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 212 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MURAKUMOGIRI.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 213 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.HOOK.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 214 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.HOOK.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 215 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.JITTE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 216 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.NONOSAMA_STAFF.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 217 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.NONOSAMA_TRIDENT.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 218 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.HAMMER_5T.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 219 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.TONFA.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 220 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.BANDIT_KNIFE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 221 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.ACES_KNIFE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 222 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MIHAWKS_KNIFE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 223 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MIHAWKS_KNIFE.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 224 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SANDAI_KITETSU.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 225 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SANDAI_KITETSU.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 226 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.WADO_ICHIMONJI.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 227 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.WADO_ICHIMONJI.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 228 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.NIDAI_KITETSU.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 229 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.NIDAI_KITETSU.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 230 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SHUSUI.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 231 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SHUSUI.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 232 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.ENMA.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 233 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.ENMA.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 234 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.AME_NO_HABAKIRI.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 235 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.AME_NO_HABAKIRI.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 236 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SOUL_SOLID.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 237 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SOUL_SOLID.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 238 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.DURANDAL.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 239 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.DURANDAL.get(), new ResourceLocation("sheathed"), SHEATHED_WEAPON);
/* 240 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MACE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 241 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.DAISENSO.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 242 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.MOGURA.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 243 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.DALTONS_SPADE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 244 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.ACE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 245 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.BLUE_SWORD.get(), new ResourceLocation("sheathed"), BLUE_SWORD_WEAPON);
/* 246 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.AXE.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 247 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SPEAR.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 248 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.KATANA.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 249 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.DAGGER.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 250 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.CUTLASS.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 251 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.BROADSWORD.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 252 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.BISENTO.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 253 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.CLEAVER.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 254 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.HASSAIKAI.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 255 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.GRYPHON.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 256 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.SAMEKIRI_BOCHO.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/* 257 */     ItemModelsProperties.func_239418_a_((Item)ModWeapons.CHAKRAM.get(), new ResourceLocation("haki"), HAKI_WEAPON);
/*     */     
/* 259 */     if (WyHelper.isAprilFirst()) {
/* 260 */       ItemModelsProperties.func_239418_a_((Item)ModItems.CIGAR.get(), new ResourceLocation("april"), LOLLIPOP);
/* 261 */       ItemModelsProperties.func_239418_a_((Item)ModItems.CIGARETTE.get(), new ResourceLocation("april"), LOLLIPOP);
/* 262 */       ItemModelsProperties.func_239418_a_((Item)ModItems.SMOKING_PIPE.get(), new ResourceLocation("april"), LOLLIPOP);
/* 263 */       ItemModelsProperties.func_239418_a_((Item)ModItems.THREE_CIGARS.get(), new ResourceLocation("april"), LOLLIPOP);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModItemModelProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */