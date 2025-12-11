/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.item.minecart.HopperMinecartEntity;
/*     */ import net.minecraft.entity.passive.FoxEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.Foods;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFifthAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuGomuNoDawnWhipAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuGomuNoGigantAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.IFruitColor;
/*     */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.FruitType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.EatDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.DFItemEntity;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProgressionEvents;
/*     */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class AkumaNoMiItem
/*     */   extends Item implements IFruitColor {
/*     */   private String name;
/*  74 */   private AbilityCore<?>[] abilities = (AbilityCore<?>[])new AbilityCore[0];
/*     */   private int tier;
/*     */   public FruitType type;
/*     */   private static final int GENERIC_FRUIT_VARIATIONS = 10;
/*  78 */   private static final Color[] STEM_COLORS = new Color[] { WyHelper.hexToRGB("#ccc774"), WyHelper.hexToRGB("#8a5216"), WyHelper.hexToRGB("#025f00"), 
/*  79 */       WyHelper.hexToRGB("#aecd6d"), WyHelper.hexToRGB("#f56fe3"), WyHelper.hexToRGB("#f93434") };
/*     */   
/*     */   public AkumaNoMiItem(String name, int tier, FruitType type, AbilityCore<?>... abilitiesArray) {
/*  82 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.DEVIL_FRUITS).func_200917_a(1).func_221540_a(Foods.field_221425_a));
/*  83 */     this.name = name;
/*  84 */     this.type = type;
/*  85 */     this.abilities = abilitiesArray;
/*     */     
/*  87 */     this.tier = tier;
/*     */     
/*  89 */     if (name.equals("Mochi Mochi no Mi") && !WyHelper.isAprilFirst()) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     if (this.type == FruitType.LOGIA) {
/*  94 */       ModValues.LOGIA_FRUITS.add(this);
/*     */     }
/*     */     
/*  97 */     ModValues.DEVIL_FRUITS.add(this);
/*     */   }
/*     */   
/*     */   public void setAbilities(AbilityCore<?>[] abilities) {
/* 101 */     this.abilities = abilities;
/* 102 */     AbilityCommandGroup.create(this.name.toUpperCase().replaceAll("[\\'\\:\\-\\,\\#]", "").replaceAll(" ", "_"), () -> this.abilities);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 108 */     ItemStack itemstack = player.func_184586_b(hand);
/* 109 */     player.func_184598_c(hand);
/* 110 */     return ActionResult.func_226248_a_(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity livingEntity) {
/* 116 */     if (!(livingEntity instanceof PlayerEntity)) {
/* 117 */       return itemStack;
/*     */     }
/*     */     
/* 120 */     PlayerEntity player = (PlayerEntity)livingEntity;
/*     */     
/* 122 */     EatDevilFruitEvent.Pre preEvent = new EatDevilFruitEvent.Pre(player, itemStack);
/* 123 */     if (MinecraftForge.EVENT_BUS.post((Event)preEvent)) {
/* 124 */       return itemStack;
/*     */     }
/*     */     
/* 127 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 129 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 130 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/* 131 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/* 132 */       OFPWWorldData worldData = OFPWWorldData.get();
/*     */       
/* 134 */       AkumaNoMiItem eatenItem = (AkumaNoMiItem)itemStack.func_77973_b();
/* 135 */       ResourceLocation eatenFruit = eatenItem.getRegistryName();
/*     */       
/* 137 */       boolean hasFruit = devilFruitProps.hasAnyDevilFruit();
/* 138 */       boolean hasYami = devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI);
/*     */       
/* 140 */       if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/*     */         
/* 142 */         eatenItem = eatenItem.getShiftedFruit(world);
/* 143 */         eatenFruit = eatenItem.getRegistryName();
/*     */       } 
/*     */       
/* 146 */       boolean flag = worldData.isFruitInUse(eatenFruit);
/* 147 */       int i = flag | (!worldData.updateOneFruit(eatenFruit, player.func_110124_au(), OneFruitEntry.Status.IN_USE) ? 1 : 0);
/* 148 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && i != 0) {
/*     */         
/* 150 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_FRUIT_ALREADY_USED), Util.field_240973_b_);
/* 151 */         itemStack.func_190918_g(1);
/* 152 */         return itemStack;
/*     */       } 
/*     */       
/* 155 */       if (!CommonConfig.INSTANCE.isYamiPowerEnabled() && hasFruit) {
/*     */         
/* 157 */         applyCurseDeath(player);
/* 158 */         itemStack.func_190918_g(1);
/* 159 */         worldData.lostOneFruit(eatenFruit, (LivingEntity)player, "Devil Fruits Curse");
/* 160 */         return itemStack;
/*     */       } 
/*     */       
/* 163 */       if (CommonConfig.INSTANCE.isYamiPowerEnabled()) {
/*     */ 
/*     */ 
/*     */         
/* 167 */         if (hasFruit && eatenItem != ModAbilities.YAMI_YAMI_NO_MI && !hasYami) {
/*     */           
/* 169 */           applyCurseDeath(player);
/* 170 */           itemStack.func_190918_g(1);
/* 171 */           worldData.lostOneFruit(eatenFruit, (LivingEntity)player, "Devil Fruits Curse");
/* 172 */           return itemStack;
/*     */         } 
/*     */ 
/*     */         
/* 176 */         if (eatenItem == ModAbilities.YAMI_YAMI_NO_MI && hasYami) {
/*     */           
/* 178 */           applyCurseDeath(player);
/* 179 */           itemStack.func_190918_g(1);
/* 180 */           worldData.lostOneFruit(eatenFruit, (LivingEntity)player, "Devil Fruits Curse");
/* 181 */           return itemStack;
/*     */         } 
/*     */ 
/*     */         
/* 185 */         if (hasFruit && !devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI) && devilFruitProps.hasYamiPower()) {
/*     */           
/* 187 */           applyCurseDeath(player);
/* 188 */           itemStack.func_190918_g(1);
/* 189 */           worldData.lostOneFruit(eatenFruit, (LivingEntity)player, "Devil Fruits Curse");
/* 190 */           return itemStack;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 195 */       if (eatenItem == ModAbilities.YAMI_YAMI_NO_MI && 
/* 196 */         CommonConfig.INSTANCE.isYamiPowerEnabled()) {
/* 197 */         devilFruitProps.setYamiPower(true);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 203 */       if (!hasFruit || (hasYami && CommonConfig.INSTANCE.isYamiPowerEnabled())) {
/* 204 */         DFEncyclopediaEntry elements = eatenItem.getRandomElements((IWorld)world);
/* 205 */         ItemsHelper.updateEncyclopediae(player, eatenFruit, elements);
/*     */         
/* 207 */         devilFruitProps.setDevilFruit(eatenItem);
/* 208 */         WyNetwork.sendTo(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), player);
/*     */       } 
/*     */       
/* 211 */       if (player instanceof ServerPlayerEntity) {
/* 212 */         ModAdvancements.CONSUME_DEVIL_FRUIT.trigger((ServerPlayerEntity)player, getRegistryName());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 217 */       if (eatenItem != ModAbilities.YOMI_YOMI_NO_MI) {
/* 218 */         for (AbilityCore<?> core : eatenItem.abilities) {
/* 219 */           if (!AbilityHelper.verifyIfAbilityIsBanned(core) && !abilityDataProps.hasUnlockedAbility(core)) {
/* 220 */             AbilityHelper.checkAndUnlockAbility(livingEntity, core);
/*     */           }
/*     */         } 
/*     */         
/* 224 */         if (eatenItem == ModAbilities.KAGE_KAGE_NO_MI || eatenItem == ModAbilities.ITO_ITO_NO_MI) {
/* 225 */           AbilityHelper.checkAndUnlockAbility(livingEntity, CommandAbility.INSTANCE);
/*     */         }
/*     */         
/* 228 */         WyNetwork.sendTo(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), player);
/* 229 */         WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityDataProps), player);
/*     */       } 
/*     */ 
/*     */       
/* 233 */       if (eatenItem == ModAbilities.HITO_HITO_NO_MI) {
/* 234 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_GAINED_ENLIGHTENMENT), Util.field_240973_b_);
/* 235 */         AbilityProgressionEvents.checkForRacialUnlocks(player);
/*     */       } 
/*     */       
/* 238 */       player.func_213357_a(world, itemStack);
/*     */     } 
/*     */     
/* 241 */     EatDevilFruitEvent.Post postEvent = new EatDevilFruitEvent.Post(player, itemStack);
/* 242 */     MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */     
/* 244 */     itemStack.func_190918_g(1);
/*     */     
/* 246 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 251 */     if (!RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       for (int i = 0; i < this.abilities.length; i++) {
/* 265 */         if (this.abilities[i] != null && !AbilityHelper.verifyIfAbilityIsBanned(this.abilities[i]) && !this.abilities[i].isHidden() && 
/* 266 */           this.abilities[i] != GearFifthAbility.INSTANCE && this.abilities[i] != GomuGomuNoDawnWhipAbility.INSTANCE && this.abilities[i] != GomuGomuNoGigantAbility.INSTANCE)
/*     */         {
/*     */           
/* 269 */           list.add(this.abilities[i].getLocalizedName().func_230530_a_(Style.field_240709_b_.func_240721_b_(TextFormatting.GRAY)));
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 274 */       list.add(new StringTextComponent(" "));
/* 275 */       list.add(new StringTextComponent(this.type.getColor() + this.type.getName()));
/*     */     } else {
/*     */       
/* 278 */       list.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomEntity(ItemStack stack) {
/* 285 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity createEntity(World world, Entity entity, ItemStack itemStack) {
/* 291 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 292 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 298 */     applyRandomness(world, itemStack);
/* 299 */     DFItemEntity newEntity = new DFItemEntity(world, (entity.func_213303_ch()).field_72450_a, (entity.func_213303_ch()).field_72448_b, (entity.func_213303_ch()).field_72449_c, itemStack);
/* 300 */     newEntity.func_200216_c(((ItemEntity)entity).func_200214_m());
/* 301 */     newEntity.func_174867_a(40);
/* 302 */     AbilityHelper.setDeltaMovement((Entity)newEntity, entity.func_213322_ci());
/* 303 */     return (Entity)newEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entity) {
/* 309 */     if (entity.field_70170_p.field_72995_K) {
/* 310 */       return false;
/*     */     }
/*     */     
/* 313 */     OFPWWorldData worldData = OFPWWorldData.get();
/* 314 */     ResourceLocation fruitKey = getRegistryName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     boolean shouldRemove = false;
/* 328 */     String removeReason = null;
/*     */     
/* 330 */     List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities(entity.func_233580_cy_(), entity.field_70170_p, 2);
/*     */     
/* 332 */     for (BlockPos pos : blockPosList) {
/*     */       
/* 334 */       TileEntity te = entity.field_70170_p.func_175625_s(pos);
/*     */       
/* 336 */       if (te instanceof net.minecraft.tileentity.HopperTileEntity) {
/*     */         
/* 338 */         shouldRemove = true;
/* 339 */         removeReason = "Thrown into hopper";
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 344 */     List<Entity> hopperMinecarts = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 0.5D, null, new Class[] { HopperMinecartEntity.class });
/*     */     
/* 346 */     if (hopperMinecarts.size() > 0) {
/* 347 */       shouldRemove = true;
/* 348 */       removeReason = "Thrown into Hopper Minecart";
/*     */     } 
/*     */     
/* 351 */     List<Entity> foxes = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 1.5D, null, new Class[] { FoxEntity.class });
/*     */     
/* 353 */     if (foxes.size() > 0) {
/* 354 */       shouldRemove = true;
/* 355 */       removeReason = "Fox took fruit";
/*     */     } 
/*     */     
/* 358 */     if (shouldRemove) {
/*     */       
/* 360 */       entity.func_70106_y();
/*     */       
/* 362 */       if (entity.func_200214_m() != null) {
/*     */         
/* 364 */         PlayerEntity thrower = entity.field_70170_p.func_217371_b(entity.func_200214_m());
/* 365 */         if (thrower != null) {
/* 366 */           thrower.field_71071_by.func_70441_a(itemStack);
/*     */         } else {
/*     */           
/* 369 */           worldData.lostOneFruit(fruitKey, null, removeReason);
/*     */         } 
/*     */       } else {
/*     */         
/* 373 */         worldData.lostOneFruit(fruitKey, null, removeReason);
/*     */       } 
/*     */     } 
/*     */     
/* 377 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyRandomness(@Nullable World world, ItemStack itemStack) {
/* 382 */     if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*     */       
/* 384 */       RandomFruitEvents.Client.DIRTY = true;
/* 385 */       if (!hasBaseColor(itemStack) || !hasStemColor(itemStack) || RandomFruitEvents.Client.DIRTY) {
/*     */         
/* 387 */         removeBaseColor(itemStack);
/* 388 */         removeStemColor(itemStack);
/* 389 */         AkumaNoMiItem randomFruit = getShiftedFruit(world);
/* 390 */         DFEncyclopediaEntry randomElements = randomFruit.getRandomElements((IWorld)world);
/* 391 */         if (randomElements == null) {
/*     */           return;
/*     */         }
/* 394 */         setBaseColor(itemStack, ((Color)randomElements.getBaseColor().get()).getRGB());
/* 395 */         setStemColor(itemStack, ((Color)randomElements.getStemColor().get()).getRGB());
/* 396 */         int type = ((Integer)randomElements.getShape().get()).intValue();
/* 397 */         itemStack.func_196082_o().func_74768_a("type", type);
/* 398 */         itemStack.func_200302_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_GENERIC_FRUIT));
/* 399 */         RandomFruitEvents.Client.DIRTY = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DFEncyclopediaEntry getRandomElements(@Nullable IWorld world) {
/* 406 */     if (getRegistryName() == null)
/*     */     {
/* 408 */       return null;
/*     */     }
/*     */     
/* 411 */     long seed = RandomFruitEvents.Common.SEED + getRegistryName().hashCode();
/* 412 */     if (world == null || world.func_201670_d()) {
/* 413 */       seed = ((Long)RandomFruitEvents.Client.FRUIT_SEEDS.get(Integer.valueOf(getRegistryName().hashCode()))).longValue();
/*     */     }
/* 415 */     Random rand = new Random(seed);
/*     */     
/* 417 */     float red = rand.nextFloat();
/* 418 */     float green = rand.nextFloat();
/* 419 */     float blue = rand.nextFloat();
/*     */     
/* 421 */     Optional<Integer> type = Optional.of(Integer.valueOf(rand.nextInt(10) + 1));
/* 422 */     Optional<Color> baseColor = Optional.of(new Color(red, green, blue));
/* 423 */     Optional<Color> stemColor = Optional.of(STEM_COLORS[rand.nextInt(STEM_COLORS.length)]);
/*     */     
/* 425 */     return DFEncyclopediaEntry.of(type, baseColor, stemColor);
/*     */   }
/*     */   
/*     */   public AkumaNoMiItem getShiftedFruit(World world) {
/* 429 */     int seed = Math.max(1, (int)(RandomFruitEvents.Common.SEED % 10L));
/* 430 */     int pos = ModValues.DEVIL_FRUITS.indexOf(this);
/* 431 */     int shifted = (pos + seed) % ModValues.DEVIL_FRUITS.size();
/* 432 */     AkumaNoMiItem randomFruit = ModValues.DEVIL_FRUITS.get(shifted);
/*     */ 
/*     */ 
/*     */     
/* 436 */     return randomFruit;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AkumaNoMiItem getReverseShiftedFruit(World world) {
/* 441 */     for (AkumaNoMiItem fruit : ModValues.DEVIL_FRUITS) {
/* 442 */       AkumaNoMiItem shiftedFruit = fruit.getShiftedFruit(world);
/* 443 */       if (shiftedFruit == this) {
/* 444 */         return fruit;
/*     */       }
/*     */     } 
/*     */     
/* 448 */     return null;
/*     */   }
/*     */   
/*     */   private void applyCurseDeath(PlayerEntity player) {
/* 452 */     player.func_70097_a((DamageSource)ModDamageSource.DEVILS_CURSE, Float.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public String getFruitKey() {
/*     */     try {
/* 457 */       return getRegistryName().toString();
/*     */     }
/* 459 */     catch (Exception e) {
/* 460 */       LogManager.getLogger(this).warn("Registry name for " + func_77658_a() + " could not be found! This could mean a faulty or non-existent registry entry for this fruit.");
/* 461 */       e.printStackTrace();
/* 462 */       return "unknown";
/*     */     } 
/*     */   }
/*     */   
/*     */   public AbilityCore<?>[] getAbilities() {
/* 467 */     return this.abilities;
/*     */   }
/*     */   
/*     */   public int getTier() {
/* 471 */     return this.tier;
/*     */   }
/*     */   
/*     */   public FruitType getType() {
/* 475 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getDevilFruitName() {
/* 479 */     return this.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\AkumaNoMiItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */