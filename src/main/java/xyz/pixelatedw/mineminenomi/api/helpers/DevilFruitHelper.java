/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.ChestBlock;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.DroppedDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.InventoryDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DevilFruitHelper {
/*     */   @Deprecated
/*  52 */   public static final HashMap<String, String> ZOAN_MODELS = new HashMap<>(); private static final double REINCARNATION_RANGE = 60.0D;
/*     */   
/*     */   static {
/*  55 */     ZOAN_MODELS.put("ushi_ushi_bison", "bison");
/*  56 */     ZOAN_MODELS.put("tori_tori_phoenix", "phoenix");
/*  57 */     ZOAN_MODELS.put("ushi_ushi_giraffe", "giraffe");
/*  58 */     ZOAN_MODELS.put("zou_zou_mammoth", "mammoth");
/*  59 */     ZOAN_MODELS.put("hito_hito_daibutsu", "daibutsu");
/*  60 */     ZOAN_MODELS.put("neko_neko_leopard", "leopard");
/*  61 */     ZOAN_MODELS.put("ryu_ryu_pteranodon", "pteranodon");
/*  62 */     ZOAN_MODELS.put("ryu_ryu_brachiosaurus", "brachiosaurus");
/*  63 */     ZOAN_MODELS.put("sara_sara_axolotl", "axolotl");
/*  64 */     ZOAN_MODELS.put("ryu_ryu_allosaurus", "allosaurus");
/*     */   }
/*     */   
/*     */   public static boolean canDevilFruitRespawn() {
/*  68 */     return !((Boolean)GeneralConfig.DEVIL_FRUIT_KEEP.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean respawnDevilFruit(LivingEntity entity, IDevilFruit props) {
/*  72 */     if (!canDevilFruitRespawn()) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     ChallengesWorldData challengeWorldData = ChallengesWorldData.get();
/*  77 */     InProgressChallenge challenge = challengeWorldData.getInProgressChallengeFor(entity);
/*  78 */     if (challenge != null) {
/*  79 */       return false;
/*     */     }
/*     */     
/*  82 */     if (!props.hasAnyDevilFruit()) {
/*  83 */       return false;
/*     */     }
/*     */     
/*  86 */     boolean isYomi = (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity));
/*     */     
/*  88 */     if (isYomi) {
/*  89 */       return false;
/*     */     }
/*     */     
/*  92 */     double chance = WyHelper.randomWithRange(1, 100);
/*     */     
/*  94 */     OFPWWorldData worldData = OFPWWorldData.get();
/*     */     
/*  96 */     List<ItemEntity> droppedItems = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 60.0D, null, new Class[] { ItemEntity.class });
/*     */     
/*  98 */     droppedItems.removeIf(entry -> !entry.func_92059_d().func_77973_b().func_206844_a((ITag)ModTags.Items.FRUIT_REINCARNATION));
/*     */     
/* 100 */     if (!droppedItems.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForDroppedAppleReincarnation()) {
/* 101 */       AkumaNoMiItem fruit = (AkumaNoMiItem)props.getDevilFruitItem();
/*     */       
/* 103 */       if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/* 104 */         fruit = fruit.getReverseShiftedFruit(entity.field_70170_p);
/*     */       }
/*     */       
/* 107 */       ((ItemEntity)droppedItems.get(0)).func_92058_a(new ItemStack((IItemProvider)fruit));
/*     */       
/* 109 */       worldData.updateOneFruit(fruit.getRegistryName(), null, OneFruitEntry.Status.DROPPED, "Reincarnated when " + entity.func_145748_c_().getString() + " died", true);
/*     */       
/* 111 */       DroppedDevilFruitEvent droppedEvent = new DroppedDevilFruitEvent(entity, (Item)fruit, "Reincarnated when " + entity.func_145748_c_().getString() + " died");
/* 112 */       MinecraftForge.EVENT_BUS.post((Event)droppedEvent);
/*     */       
/* 114 */       entity.field_70170_p.func_184133_a(null, ((ItemEntity)droppedItems.get(0)).func_233580_cy_(), (SoundEvent)ModSounds.FRUIT_REINCARNATION.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*     */       
/* 116 */       return true;
/*     */     } 
/*     */     
/* 119 */     List<PlayerEntity> players = WyHelper.getNearbyPlayers(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 60.0D, null);
/*     */     
/* 121 */     players.removeIf(entry -> !hasFruitReincarnationInInventory((LivingEntity)entry));
/*     */     
/* 123 */     if (!players.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForInventoryAppleReincarnation()) {
/* 124 */       boolean flag = setFruitInInv((IInventory)((PlayerEntity)players.get(0)).field_71071_by, (LivingEntity)players.get(0), entity, entity.field_70170_p, props.getDevilFruit().get());
/*     */       
/* 126 */       if (flag) {
/* 127 */         entity.field_70170_p.func_184133_a(null, ((PlayerEntity)players.get(0)).func_233580_cy_(), (SoundEvent)ModSounds.FRUIT_REINCARNATION.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*     */         
/* 129 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 134 */       List<VillagerEntity> villagers = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 60.0D, null, new Class[] { VillagerEntity.class });
/*     */       
/* 136 */       villagers.removeIf(entry -> !hasFruitReincarnationInInventory((LivingEntity)entry));
/*     */       
/* 138 */       if (!villagers.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForInventoryAppleReincarnation()) {
/* 139 */         boolean flag = setFruitInInv((IInventory)((VillagerEntity)villagers.get(0)).func_213715_ed(), (LivingEntity)villagers.get(0), entity, entity.field_70170_p, props.getDevilFruit().get());
/*     */         
/* 141 */         if (flag) {
/* 142 */           entity.field_70170_p.func_184133_a(null, ((VillagerEntity)villagers.get(0)).func_233580_cy_(), (SoundEvent)ModSounds.FRUIT_REINCARNATION.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*     */           
/* 144 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       List<BlockPos> blockPosList = WyHelper.getNearbyBlocks((Entity)entity, 60);
/*     */       
/* 150 */       if (!blockPosList.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForChestAppleReincarnation()) {
/* 151 */         for (BlockPos blockPos : blockPosList) {
/* 152 */           BlockState state = entity.field_70170_p.func_180495_p(blockPos);
/*     */           
/* 154 */           if (state.func_177230_c() instanceof ChestBlock) {
/* 155 */             IInventory inv = ChestBlock.func_226916_a_((ChestBlock)state.func_177230_c(), state, entity.field_70170_p, blockPos, false);
/*     */             
/* 157 */             boolean flag = setFruitInInv(inv, null, entity, entity.field_70170_p, props.getDevilFruit().get());
/*     */             
/* 159 */             if (flag) {
/* 160 */               entity.field_70170_p.func_184133_a(null, blockPos, (SoundEvent)ModSounds.FRUIT_REINCARNATION.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*     */               
/* 162 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean hasFruitReincarnationInInventory(LivingEntity entity) {
/* 173 */     for (ItemStack stack : ItemsHelper.getAllInventoryItems(entity)) {
/* 174 */       if (stack.func_77973_b().func_206844_a((ITag)ModTags.Items.FRUIT_REINCARNATION)) {
/* 175 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 179 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean setFruitInInv(@Nullable IInventory inv, @Nullable LivingEntity invOwner, LivingEntity entity, World level, ResourceLocation df) {
/* 183 */     if (inv == null) {
/* 184 */       return false;
/*     */     }
/*     */     
/* 187 */     int stackIndex = -1;
/* 188 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 189 */       if (inv.func_70301_a(i).func_77973_b().func_206844_a((ITag)ModTags.Items.FRUIT_REINCARNATION)) {
/* 190 */         stackIndex = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 195 */     if (stackIndex != -1) {
/* 196 */       AkumaNoMiItem fruit = (AkumaNoMiItem)getDevilFruitItem(df);
/* 197 */       if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/* 198 */         fruit = fruit.getReverseShiftedFruit(level);
/*     */       }
/* 200 */       inv.func_70299_a(stackIndex, new ItemStack((IItemProvider)fruit));
/* 201 */       UUID invOwnerUUID = (invOwner != null) ? invOwner.func_110124_au() : null;
/* 202 */       OFPWWorldData.get().updateOneFruit(fruit.getRegistryName(), invOwnerUUID, OneFruitEntry.Status.INVENTORY, "Reincarnated in " + entity.func_145748_c_().getString() + "'s inventory", true);
/*     */       
/* 204 */       InventoryDevilFruitEvent postEvent = new InventoryDevilFruitEvent(invOwner, getDevilFruitItem(df), "Reincarnated in " + entity.func_145748_c_().getString() + "'s inventory");
/* 205 */       MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */       
/* 207 */       return true;
/*     */     } 
/*     */     
/* 210 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static AkumaNoMiItem findAvailableOneFruit(World world, @Nullable Item devilFruitItem) {
/* 215 */     if (devilFruitItem == null || !(devilFruitItem instanceof AkumaNoMiItem)) {
/* 216 */       return null;
/*     */     }
/*     */     
/* 219 */     AkumaNoMiItem fruit = (AkumaNoMiItem)devilFruitItem;
/*     */     
/* 221 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 222 */       return fruit;
/*     */     }
/*     */     
/* 225 */     OFPWWorldData worldProps = OFPWWorldData.get();
/* 226 */     int tier = fruit.getTier();
/*     */     
/* 228 */     ResourceLocation key = fruit.getRegistryName();
/* 229 */     boolean isAvailable = !worldProps.isFruitInWorld(key);
/*     */     
/* 231 */     if (isAvailable) {
/* 232 */       return fruit;
/*     */     }
/*     */ 
/*     */     
/* 236 */     List<AkumaNoMiItem> fruitsLeft = (List<AkumaNoMiItem>)WyHelper.shuffle(ModValues.DEVIL_FRUITS).stream().filter(f -> (f.getTier() == tier && !worldProps.isFruitInWorld(f.getRegistryName()))).collect(Collectors.toList());
/*     */     
/* 238 */     if (fruitsLeft.size() > 0) {
/* 239 */       fruit = fruitsLeft.get(0);
/* 240 */       return fruit;
/*     */     } 
/*     */ 
/*     */     
/* 244 */     return null;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static Item getDevilFruitItem(String key) {
/* 250 */     ResourceLocation res = new ResourceLocation(key);
/* 251 */     if (key.equals("yamidummy")) {
/* 252 */       res = ModAbilities.YAMI_YAMI_NO_MI.getRegistryName();
/*     */     }
/* 254 */     return (Item)ForgeRegistries.ITEMS.getValue(res);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Item getDevilFruitItem(ResourceLocation key) {
/* 259 */     return (Item)ForgeRegistries.ITEMS.getValue(key);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static Item getLegacyDevilFruitItem(String key) {
/* 265 */     String model = "";
/* 266 */     String fullModel = "";
/*     */     
/* 268 */     for (Map.Entry<String, String> entry : ZOAN_MODELS.entrySet()) {
/* 269 */       if (key.equals(entry.getKey())) {
/* 270 */         model = entry.getValue();
/* 271 */         fullModel = "_model_" + model;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 276 */     if (key.equals("yamidummy")) {
/* 277 */       key = "yami_yami";
/*     */     }
/*     */     
/* 280 */     String finalName = (!Strings.isNullOrEmpty(model) ? key.replace("_" + model, "") : key) + "_no_mi" + fullModel;
/*     */     
/* 282 */     return (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("mineminenomi", finalName));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static ItemStack getDevilFruitItemStack(String key) {
/* 288 */     return new ItemStack((IItemProvider)getDevilFruitItem(key));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static ItemStack getDevilFruitItemStack(ResourceLocation key) {
/* 294 */     return new ItemStack((IItemProvider)getDevilFruitItem(key));
/*     */   }
/*     */   
/*     */   public static boolean hasDFLimitInInventory(PlayerEntity player) {
/* 298 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 299 */       return false;
/*     */     }
/*     */     
/* 302 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 307 */     int fruitsFound = ((List)ItemsHelper.getAllInventoryItems((LivingEntity)player).stream().filter(itemStack -> itemStack.func_77973_b() instanceof AkumaNoMiItem).collect(Collectors.toList())).size();
/*     */     
/* 309 */     boolean canYamiPickupFruit = (devilFruitProps.getDevilFruit().isPresent() && ((ResourceLocation)devilFruitProps.getDevilFruit().get()).equals(ModAbilities.YAMI_YAMI_NO_MI.getRegistryName()) && CommonConfig.INSTANCE.isYamiPowerEnabled() && fruitsFound == 0);
/*     */     
/* 311 */     if (DevilFruitCapability.get((LivingEntity)player).hasAnyDevilFruit() && CommonConfig.INSTANCE.getUnableToPickDFAsUser() && !canYamiPickupFruit) {
/* 312 */       return true;
/*     */     }
/*     */     
/* 315 */     return (fruitsFound >= CommonConfig.INSTANCE.getInventoryLimitForFruits());
/*     */   }
/*     */   
/*     */   public static void vanillaFlightThreshold(LivingEntity entity, int height) {
/* 319 */     if (entity.func_226278_cu_() > height) {
/* 320 */       AbilityHelper.setDeltaMovement((Entity)entity, entity.func_213322_ci().func_72441_c(0.0D, 0.25D * (height / 5.0F), 0.0D).func_216372_d(1.0D, -0.15D, 1.0D));
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean flyingAtMaxHeight(LivingEntity player, double maxDifference) {
/* 325 */     return (maxDifference > getDifferenceToFloor((Entity)player));
/*     */   }
/*     */   
/*     */   public static double getDifferenceToFloor(Entity entity) {
/* 329 */     return (entity.func_213303_ch().func_178788_d(getFloorLevel(entity))).field_72448_b;
/*     */   }
/*     */   
/*     */   public static Vector3d getFloorLevel(Entity entity) {
/* 333 */     Vector3d startVec = entity.func_213303_ch();
/* 334 */     Vector3d endVec = startVec.func_72441_c(0.0D, -256.0D, 0.0D);
/* 335 */     BlockRayTraceResult blockRayTraceResult = entity.field_70170_p.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity));
/* 336 */     return blockRayTraceResult.func_216347_e();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\DevilFruitHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */