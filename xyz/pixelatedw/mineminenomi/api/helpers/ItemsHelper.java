/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ItemParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.capabilities.CapabilityProvider;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import top.theillusivec4.curios.api.CuriosApi;
/*     */ import top.theillusivec4.curios.api.SlotResult;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ItemsHelper {
/*  89 */   private static final String[] WANTED_POSTER_BACKGROUNDS = new String[] { "forest1", "forest2", "jungle1", "jungle2", "hills1", "hills2", "hills3", "plains1", "plains2", "plains3", "taiga1", "taiga2" };
/*     */   
/*     */   public static Optional<ItemStack> findItemInSlot(LivingEntity entity, EquipmentSlotType slot, Item item) {
/*  92 */     ItemStack stack = entity.func_184582_a(slot);
/*  93 */     if (!stack.func_190926_b() && stack.func_77973_b() == item) {
/*  94 */       return Optional.ofNullable(stack);
/*     */     }
/*     */     
/*  97 */     if (ModMain.hasCuriosInstalled()) {
/*  98 */       List<SlotResult> curiosList = CuriosApi.getCuriosHelper().findCurios(entity, item);
/*  99 */       for (SlotResult result : curiosList) {
/* 100 */         if (result.getStack().func_77973_b() == item) {
/* 101 */           return Optional.ofNullable(result.getStack());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   public static boolean hasItemInSlot(LivingEntity entity, EquipmentSlotType slot, Item item) {
/* 110 */     ItemStack stack = entity.func_184582_a(slot);
/* 111 */     if (!stack.func_190926_b() && stack.func_77973_b() == item) {
/* 112 */       return true;
/*     */     }
/*     */     
/* 115 */     if (ModMain.hasCuriosInstalled()) {
/* 116 */       List<SlotResult> curiosList = CuriosApi.getCuriosHelper().findCurios(entity, item);
/* 117 */       for (SlotResult result : curiosList) {
/* 118 */         if (result.getStack().func_77973_b() == item) {
/* 119 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public static void hurtCurrentlyUsedShield(LivingEntity entity, float damage) {
/* 128 */     if (entity.func_184607_cu().isShield(entity)) {
/* 129 */       if (!entity.field_70170_p.field_72995_K && entity instanceof PlayerEntity) {
/* 130 */         ((PlayerEntity)entity).func_71029_a(Stats.field_75929_E.func_199076_b(entity.func_184607_cu().func_77973_b()));
/*     */       }
/*     */       
/* 133 */       int i = 1 + MathHelper.func_76141_d(damage);
/* 134 */       Hand hand = entity.func_184600_cs();
/* 135 */       entity.func_184607_cu().func_222118_a(i, entity, user -> {
/*     */             user.func_213334_d(hand);
/*     */             if (entity instanceof PlayerEntity) {
/*     */               ForgeEventFactory.onPlayerDestroyItem((PlayerEntity)entity, entity.func_184607_cu(), hand);
/*     */             }
/*     */           });
/* 141 */       if (entity.func_184607_cu().func_190926_b()) {
/* 142 */         if (hand == Hand.MAIN_HAND) {
/* 143 */           entity.func_184201_a(EquipmentSlotType.MAINHAND, ItemStack.field_190927_a);
/*     */         } else {
/*     */           
/* 146 */           entity.func_184201_a(EquipmentSlotType.OFFHAND, ItemStack.field_190927_a);
/*     */         } 
/*     */         
/* 149 */         entity.func_184602_cy();
/* 150 */         entity.func_184185_a(SoundEvents.field_187769_eM, 0.8F, 0.8F + entity.field_70170_p.field_73012_v.nextFloat() * 0.4F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void stopShieldAndStartCooldown(LivingEntity entity, int cooldown) {
/* 156 */     if (AbilityHelper.isShieldBlocking(entity)) {
/* 157 */       entity.func_184602_cy();
/* 158 */       if (entity instanceof PlayerEntity) {
/* 159 */         ((PlayerEntity)entity).func_184811_cZ().func_185145_a(Items.field_185159_cQ, cooldown);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean giveItemStackToEntity(LivingEntity entity, ItemStack itemStack, EquipmentSlotType slotType) {
/* 165 */     if (entity instanceof PlayerEntity) {
/* 166 */       ((PlayerEntity)entity).field_71071_by.func_70441_a(itemStack);
/* 167 */       return true;
/*     */     } 
/*     */     
/* 170 */     if (!entity.func_190630_a(slotType)) {
/* 171 */       entity.func_184201_a(slotType, itemStack);
/* 172 */       return true;
/*     */     } 
/*     */     
/* 175 */     return false;
/*     */   }
/*     */   
/*     */   public static void removeItemStackFromInventory(LivingEntity entity, ItemStack searchStack) {
/* 179 */     if (entity instanceof PlayerEntity) {
/* 180 */       ((PlayerEntity)entity).field_71071_by.func_184437_d(searchStack);
/*     */     } else {
/*     */       
/* 183 */       for (ItemStack stack : entity.func_184209_aF()) {
/* 184 */         if (stack.equals(searchStack)) {
/* 185 */           stack.func_190918_g(stack.func_190916_E());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean hasItemStackInInventory(LivingEntity entity, ItemStack stack) {
/* 192 */     return getAllInventoryItems(entity).contains(stack);
/*     */   }
/*     */   
/*     */   public static int countItemInInventory(LivingEntity entity, Item item) {
/* 196 */     return ((Integer)getAllInventoryItems(entity).stream().filter(stack -> stack.func_77973_b().equals(item)).map(ItemStack::func_190916_E).reduce(Integer.valueOf(0), Integer::sum)).intValue();
/*     */   }
/*     */   public static int getFreeOrSameSlot(PlayerEntity player, ItemStack other) {
/*     */     int i;
/* 200 */     for (i = player.field_71071_by.field_70462_a.size() - 1; i > 0; i--) {
/* 201 */       ItemStack stack = (ItemStack)player.field_71071_by.field_70462_a.get(i);
/* 202 */       if (i != player.field_71071_by.field_70461_c && stack.func_77985_e() && stack.func_190916_E() + other.func_190916_E() <= stack.func_77976_d() && matchItemStacks(stack, other)) {
/* 203 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 207 */     for (i = player.field_71071_by.field_70462_a.size() - 1; i > 0; i--) {
/* 208 */       ItemStack stack = (ItemStack)player.field_71071_by.field_70462_a.get(i);
/* 209 */       if (i != player.field_71071_by.field_70461_c && stack.func_190926_b()) {
/* 210 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 214 */     return -1;
/*     */   }
/*     */   
/*     */   private static boolean matchItemStacks(ItemStack stack, ItemStack other) {
/* 218 */     if (stack.func_190926_b() && other.func_190926_b()) {
/* 219 */       return true;
/*     */     }
/* 221 */     if (!stack.func_190926_b() && !other.func_190926_b()) {
/* 222 */       if (stack.func_77973_b() != other.func_77973_b())
/* 223 */         return false; 
/* 224 */       if (stack.func_77978_p() == null && other.func_77978_p() != null) {
/* 225 */         return false;
/*     */       }
/* 227 */       return ((stack.func_77978_p() == null || stack.func_77978_p().equals(other.func_77978_p())) && stack.areCapsCompatible((CapabilityProvider)other));
/*     */     } 
/*     */ 
/*     */     
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFreeSlotReversed(PlayerEntity player) {
/* 236 */     for (int i = player.field_71071_by.field_70462_a.size() - 1; i > 0; i--) {
/* 237 */       if (((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_190926_b()) {
/* 238 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 242 */     return -1;
/*     */   }
/*     */   
/*     */   public static boolean hasInventoryFull(LivingEntity entity) {
/* 246 */     return !getInventoryItems(entity).stream().anyMatch(stack -> stack.func_190926_b());
/*     */   }
/*     */   
/*     */   public static long getFreeSlotsCount(LivingEntity entity) {
/* 250 */     return getInventoryItems(entity).stream().filter(stack -> stack.func_190926_b()).count();
/*     */   }
/*     */   
/*     */   public static List<ItemStack> getInventoryItems(LivingEntity entity) {
/* 254 */     List<ItemStack> inventory = new ArrayList<>();
/*     */     
/* 256 */     if (entity instanceof PlayerEntity) {
/* 257 */       inventory.addAll((Collection<? extends ItemStack>)((PlayerEntity)entity).field_71071_by.field_70462_a);
/*     */     } else {
/* 259 */       entity.func_184214_aD().forEach(inventory::add);
/*     */     } 
/*     */     
/* 262 */     return inventory;
/*     */   }
/*     */   
/*     */   public static List<ItemStack> getAllInventoryItems(LivingEntity entity) {
/* 266 */     List<ItemStack> inventory = new ArrayList<>();
/*     */     
/* 268 */     if (entity instanceof PlayerEntity) {
/* 269 */       inventory.addAll((Collection<? extends ItemStack>)((PlayerEntity)entity).field_71071_by.field_70462_a);
/* 270 */       inventory.addAll((Collection<? extends ItemStack>)((PlayerEntity)entity).field_71071_by.field_184439_c);
/* 271 */       inventory.addAll((Collection<? extends ItemStack>)((PlayerEntity)entity).field_71071_by.field_70460_b);
/*     */     } else {
/* 273 */       entity.func_184209_aF().forEach(inventory::add);
/*     */     } 
/*     */     
/* 276 */     if (ModMain.hasCuriosInstalled()) {
/*     */       
/* 278 */       Collection<SlotResult> curiosList = CuriosApi.getCuriosHelper().findCurios(entity, stack -> true);
/* 279 */       for (SlotResult result : curiosList) {
/* 280 */         inventory.add(result.getStack());
/*     */       }
/*     */     } 
/*     */     
/* 284 */     inventory.removeIf(stack -> (stack == null || stack.func_190926_b()));
/*     */     
/* 286 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void itemBreakParticles(World level, Vector3d pos, int count, ItemStack stack) {
/* 291 */     for (int i = 0; i < count; i++) {
/*     */       
/* 293 */       Vector3d vec3d = new Vector3d((level.func_201674_k().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 294 */       double d0 = -level.func_201674_k().nextFloat() * 0.6D - 0.3D;
/* 295 */       Vector3d vec3d1 = new Vector3d((level.func_201674_k().nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
/* 296 */       if (level instanceof ServerWorld) {
/* 297 */         ((ServerWorld)level).func_195598_a((IParticleData)new ItemParticleData(ParticleTypes.field_197591_B, stack), vec3d1.field_72450_a, vec3d1.field_72448_b, vec3d1.field_72449_c, 1, vec3d.field_72450_a, vec3d.field_72448_b + 0.05D, vec3d.field_72449_c, 0.2D);
/*     */       } else {
/* 299 */         level.func_195594_a((IParticleData)new ItemParticleData(ParticleTypes.field_197591_B, stack), vec3d1.field_72450_a, vec3d1.field_72448_b, vec3d1.field_72449_c, vec3d.field_72450_a, vec3d.field_72448_b + 0.05D, vec3d.field_72449_c);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void itemBreakParticles(LivingEntity entity, int count, ItemStack stack) {
/* 305 */     for (int i = 0; i < count; i++) {
/*     */       
/* 307 */       Vector3d vec3d = new Vector3d((entity.func_70681_au().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 308 */       vec3d = vec3d.func_178789_a(-entity.field_70125_A * 0.017453292F);
/* 309 */       vec3d = vec3d.func_178785_b(-entity.field_70177_z * 0.017453292F);
/* 310 */       double d0 = -entity.func_70681_au().nextFloat() * 0.6D - 0.3D;
/* 311 */       Vector3d vec3d1 = new Vector3d((entity.func_70681_au().nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
/* 312 */       vec3d1 = vec3d1.func_178789_a(-entity.field_70125_A * 0.017453292F);
/* 313 */       vec3d1 = vec3d1.func_178785_b(-entity.field_70177_z * 0.017453292F);
/* 314 */       vec3d1 = vec3d1.func_72441_c(entity.func_226277_ct_(), entity.func_226280_cw_(), entity.func_226281_cx_());
/* 315 */       if (entity.field_70170_p instanceof ServerWorld) {
/* 316 */         ((ServerWorld)entity.field_70170_p).func_195598_a((IParticleData)new ItemParticleData(ParticleTypes.field_197591_B, stack), vec3d1.field_72450_a, vec3d1.field_72448_b, vec3d1.field_72449_c, 1, vec3d.field_72450_a, vec3d.field_72448_b + 0.05D, vec3d.field_72449_c, 0.2D);
/*     */       } else {
/* 318 */         entity.field_70170_p.func_195594_a((IParticleData)new ItemParticleData(ParticleTypes.field_197591_B, stack), vec3d1.field_72450_a, vec3d1.field_72448_b, vec3d1.field_72449_c, vec3d.field_72450_a, vec3d.field_72448_b + 0.05D, vec3d.field_72449_c);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean updateEncyclopediae(PlayerEntity player, ResourceLocation fruit, DFEncyclopediaEntry entry) {
/* 326 */     List<ItemStack> slots = new ArrayList<>();
/* 327 */     slots.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_70462_a);
/* 328 */     slots.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_184439_c);
/*     */ 
/*     */     
/* 331 */     slots = (List<ItemStack>)slots.stream().filter(stack -> (stack.func_77973_b() == ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())).collect(Collectors.toList());
/*     */     
/* 333 */     if (slots.size() <= 0) {
/* 334 */       return false;
/*     */     }
/* 336 */     slots.forEach(stack -> {
/*     */           DFEncyclopediaItem.addFruitClues(stack, fruit, entry);
/*     */           ModAdvancements.ENCYCLOPEDIA_COMPLETION.trigger((ServerPlayerEntity)player, stack);
/*     */         });
/* 340 */     return true;
/*     */   }
/*     */   
/*     */   public static void dropWantedPosters(World world, Vector3d pos) {
/* 344 */     if (world.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 348 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 350 */     Set<Pair<UUID, Long>> playerBountiesInPackage = new HashSet<>();
/*     */     
/* 352 */     if (worldData.getAllBounties().size() > 0) {
/* 353 */       Predicate<PlayerEntity> hasBounty = player -> (player != null && player.func_70089_S() && BountyHelper.canGainBounty((LivingEntity)player) && worldData.getBounty(player.func_110124_au()) != 0L);
/*     */       
/* 355 */       WyHelper.getNearbyPlayers(pos, (IWorld)world, 10.0D, null).stream().filter(hasBounty).limit(5L).forEach(player -> {
/*     */             ImmutablePair immutablePair = ImmutablePair.of(player.func_110124_au(), Long.valueOf(worldData.getBounty(player.func_110124_au())));
/*     */             
/*     */             playerBountiesInPackage.add(immutablePair);
/*     */           });
/* 360 */       if (playerBountiesInPackage.size() < 5) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 366 */         List<Pair<UUID, Long>> playerBounties = (List<Pair<UUID, Long>>)worldData.getAllBounties().entrySet().stream().filter(entry -> !playerBountiesInPackage.contains(entry)).map(entry -> ImmutablePair.of(entry.getKey(), entry.getValue())).collect(Collectors.toList());
/*     */         
/* 368 */         Collections.shuffle(playerBounties);
/* 369 */         int size = Math.min(3, playerBounties.size());
/* 370 */         playerBountiesInPackage.addAll(playerBounties.subList(0, size));
/*     */       } 
/*     */     } 
/*     */     
/* 374 */     if (!playerBountiesInPackage.isEmpty())
/*     */     {
/* 376 */       playerBountiesInPackage.stream().forEach(entry -> {
/*     */             ItemStack stack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER.get());
/*     */             
/*     */             PlayerEntity player = world.func_217371_b((UUID)entry.getKey());
/*     */             if (player != null) {
/*     */               WantedPosterData wantedData = new WantedPosterData((LivingEntity)player, ((Long)entry.getValue()).longValue());
/*     */               CompoundNBT data = wantedData.write();
/*     */               if (!data.isEmpty()) {
/*     */                 stack.func_196082_o().func_218657_a("WPData", (INBT)data);
/*     */                 world.func_217376_c((Entity)new ItemEntity(world, pos.field_72450_a, pos.field_72448_b + 1.0D, pos.field_72449_c, stack));
/*     */               } 
/*     */             } 
/*     */           });
/*     */     }
/* 390 */     int challengePosters = world.func_201674_k().nextInt(3);
/* 391 */     List<ChallengeCore<?>> list = (List<ChallengeCore<?>>)ModRegistries.CHALLENGES.getValues().stream().filter(core -> (core.getDifficulty() == ChallengeDifficulty.STANDARD)).collect(Collectors.toList());
/* 392 */     Collections.shuffle(list);
/* 393 */     for (int i = 0; i < challengePosters; i++) {
/* 394 */       int pick = world.func_201674_k().nextInt(list.size());
/* 395 */       ChallengeCore<?> challenge = list.get(pick);
/*     */       
/* 397 */       ItemStack stack = new ItemStack((IItemProvider)ModItems.CHALLENGE_POSTER.get());
/* 398 */       stack.func_200302_a((ITextComponent)challenge.getLocalizedTitle());
/* 399 */       stack.func_196082_o().func_74778_a("challengeId", challenge.getRegistryName().toString());
/* 400 */       world.func_217376_c((Entity)new ItemEntity(world, pos.field_72450_a, pos.field_72448_b + 1.0D, pos.field_72449_c, stack));
/*     */     } 
/*     */     
/* 403 */     int notoriousPosters = 1 + world.func_201674_k().nextInt(3);
/* 404 */     NPCWorldData npcWorldData = NPCWorldData.get();
/* 405 */     EventsWorldData eventsWorldData = EventsWorldData.get();
/* 406 */     for (int j = 0; j < notoriousPosters; j++) {
/* 407 */       Optional<TrackedNPC> npc = npcWorldData.getRandomTrackedMob(ModValues.PIRATE);
/* 408 */       if (npc.isPresent() && !eventsWorldData.hasNTEventFor(npc.get())) {
/* 409 */         ItemStack stack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER.get());
/* 410 */         NotoriousEntity entity = ((TrackedNPC)npc.get()).createTrackedMob(world);
/*     */         
/* 412 */         Vector3d pos2 = null;
/*     */         
/* 414 */         for (int k = 0; k < 5; ) {
/* 415 */           int extraX = 1000 + world.func_201674_k().nextInt(5000);
/* 416 */           if (world.func_201674_k().nextBoolean()) {
/* 417 */             extraX *= -1;
/*     */           }
/* 419 */           int extraZ = 1000 + world.func_201674_k().nextInt(5000);
/* 420 */           if (world.func_201674_k().nextBoolean()) {
/* 421 */             extraZ *= -1;
/*     */           }
/*     */           
/* 424 */           Vector3d vecPos = pos.func_72441_c(extraX, 1.0D, extraZ);
/* 425 */           BlockPos check = new BlockPos(vecPos.field_72450_a, vecPos.field_72448_b, vecPos.field_72449_c);
/*     */           
/* 427 */           Biome.Category category = world.func_226691_t_(check).func_201856_r();
/* 428 */           if (category == Biome.Category.OCEAN) {
/*     */             k++;
/*     */             continue;
/*     */           } 
/* 432 */           pos2 = vecPos;
/*     */         } 
/*     */ 
/*     */         
/* 436 */         if (pos2 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 441 */           eventsWorldData.addNotoriousTarget((ServerWorld)world, pos2, 72000L, npc.get());
/*     */           
/* 443 */           WantedPosterData wantedData = new WantedPosterData((LivingEntity)entity, ((TrackedNPC)npc.get()).getBounty());
/* 444 */           wantedData.setTrackedPosition(pos2);
/*     */           
/* 446 */           if (BountyHelper.canGainBounty((LivingEntity)entity)) {
/* 447 */             worldData.issueBounty(((TrackedNPC)npc.get()).getUUID(), ((TrackedNPC)npc.get()).getBounty());
/*     */           }
/*     */           
/* 450 */           CompoundNBT nbt = wantedData.write();
/* 451 */           stack.func_196082_o().func_218657_a("WPData", (INBT)nbt);
/* 452 */           world.func_217376_c((Entity)new ItemEntity(world, pos.field_72450_a, pos.field_72448_b + 1.0D, pos.field_72449_c, stack));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static CompoundNBT setPlayerWantedData(IWorld world, UUID id, long bounty) {
/* 460 */     CompoundNBT data = new CompoundNBT();
/*     */     
/* 462 */     PlayerEntity player = world.func_217371_b(id);
/*     */     
/* 464 */     if (player == null || world.func_201670_d()) {
/* 465 */       return data;
/*     */     }
/*     */     
/* 468 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 470 */     data.func_186854_a("UUID", id);
/* 471 */     data.func_74778_a("Name", player.func_200200_C_().getString());
/* 472 */     data.func_74772_a("Bounty", bounty);
/* 473 */     data.func_74778_a("Faction", EntityStatsCapability.get((LivingEntity)player).getFaction().toString());
/*     */     
/* 475 */     long seed = player.func_200200_C_().getString().hashCode() + bounty;
/* 476 */     Random rand = new Random(seed);
/* 477 */     int randomBg = (int)WyHelper.randomWithRange(rand, 0, WANTED_POSTER_BACKGROUNDS.length - 1);
/* 478 */     data.func_74778_a("Background", WANTED_POSTER_BACKGROUNDS[randomBg]);
/*     */     
/* 480 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 481 */     String dateString = format.format(new Date());
/* 482 */     data.func_74778_a("Date", dateString);
/*     */     
/* 484 */     CompoundNBT compoundnbt = new CompoundNBT();
/* 485 */     NBTUtil.func_180708_a(compoundnbt, player.func_146103_bH());
/* 486 */     data.func_218657_a("Owner", (INBT)compoundnbt);
/*     */     
/* 488 */     Crew crew = worldData.getCrewWithMember(id);
/* 489 */     if (crew != null) {
/* 490 */       data.func_218657_a("Crew", (INBT)crew.write());
/*     */     }
/*     */     
/* 493 */     return data;
/*     */   }
/*     */   
/*     */   public static boolean isBow(ItemStack itemStack) {
/* 497 */     if (itemStack.func_190926_b()) {
/* 498 */       return false;
/*     */     }
/*     */     
/* 501 */     if (itemStack.func_77973_b() instanceof net.minecraft.item.ShootableItem) {
/* 502 */       return true;
/*     */     }
/*     */     
/* 505 */     if (itemStack.func_77975_n() == UseAction.BOW) {
/* 506 */       return true;
/*     */     }
/*     */     
/* 509 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlunt(@Nullable ItemStack itemStack) {
/* 513 */     if (itemStack == null || itemStack.func_190926_b()) {
/* 514 */       return false;
/*     */     }
/*     */     
/* 517 */     if (itemStack.func_77973_b() instanceof ModSwordItem && ((ModSwordItem)itemStack.func_77973_b()).isBlunt()) {
/* 518 */       return true;
/*     */     }
/*     */     
/* 521 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isSword(@Nullable ItemStack itemStack) {
/* 525 */     if (itemStack == null || itemStack.func_190926_b()) {
/* 526 */       return false;
/*     */     }
/*     */     
/* 529 */     if (itemStack.func_77973_b() instanceof ModSwordItem && !((ModSwordItem)itemStack.func_77973_b()).isBlunt()) {
/* 530 */       return true;
/*     */     }
/*     */     
/* 533 */     if (itemStack.func_77973_b() instanceof net.minecraft.item.SwordItem) {
/* 534 */       return true;
/*     */     }
/*     */     
/* 537 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isClimaTact(ItemStack itemStack) {
/* 541 */     if (itemStack == null || itemStack.func_190926_b()) {
/* 542 */       return false;
/*     */     }
/*     */     
/* 545 */     if (itemStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
/* 546 */       return true;
/*     */     }
/*     */     
/* 549 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasKairosekiItem(LivingEntity entity) {
/* 553 */     Iterable<ItemStack> iter = entity.func_184209_aF();
/* 554 */     if (entity instanceof PlayerEntity) {
/* 555 */       iter = Iterables.concat((Iterable)((PlayerEntity)entity).field_71071_by.field_70462_a, entity.func_184209_aF());
/*     */     }
/*     */     
/* 558 */     for (ItemStack stack : iter) {
/* 559 */       if (!stack.func_190926_b() && ModTags.Items.KAIROSEKI.func_230235_a_(stack.func_77973_b())) {
/* 560 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 564 */     if (entity.func_70644_a((Effect)ModEffects.CAUGHT_IN_KAIROSEKI_NET.get()) || entity.func_70644_a((Effect)ModEffects.HANDCUFFED_KAIROSEKI.get())) {
/* 565 */       return true;
/*     */     }
/*     */     
/* 568 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isKairosekiWeapon(ItemStack heldItem) {
/* 573 */     if (heldItem != null)
/* 574 */       return ((heldItem.func_77948_v() && EnchantmentHelper.func_77506_a((Enchantment)ModEnchantments.KAIROSEKI.get(), heldItem) > 0) || heldItem.func_77973_b() == ModWeapons.JITTE.get()); 
/* 575 */     return false;
/*     */   }
/*     */   
/*     */   public static float getSniperInaccuracy(float inaccuracy, LivingEntity entity) {
/* 579 */     if (EntityStatsCapability.get(entity).isSniper()) {
/* 580 */       inaccuracy /= 2.0F;
/*     */     }
/*     */     
/* 583 */     if (entity.func_184582_a(EquipmentSlotType.HEAD).func_77973_b() == ModArmors.SNIPER_GOGGLES.get()) {
/* 584 */       IAbilityData aprops = AbilityDataCapability.get(entity);
/* 585 */       ZoomAbility ability = (ZoomAbility)aprops.getEquippedAbility(ZoomAbility.INSTANCE);
/* 586 */       boolean isActive = (ability != null && ability.isContinuous());
/*     */       
/* 588 */       if (isActive) {
/* 589 */         inaccuracy /= 4.0F;
/*     */       }
/*     */     } 
/*     */     
/* 593 */     return inaccuracy;
/*     */   }
/*     */   
/*     */   public static float getItemDamage(ItemStack stack) {
/* 597 */     if (stack == null) {
/* 598 */       return -1.0F;
/*     */     }
/*     */     
/* 601 */     Multimap<Attribute, AttributeModifier> multimap = stack.func_111283_C(EquipmentSlotType.MAINHAND);
/*     */     
/* 603 */     double modifier = EnchantmentHelper.func_152377_a(stack, CreatureAttribute.field_223222_a_);
/*     */     
/* 605 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)multimap.entries()) {
/* 606 */       AttributeModifier attr = entry.getValue();
/*     */       
/* 608 */       if (attr.func_111167_a().equals(AttributeHelper.ATTACK_DAMAGE_MODIFIER)) {
/* 609 */         double damage = attr.func_111164_d() + modifier;
/*     */         
/* 611 */         return (float)damage;
/*     */       } 
/*     */     } 
/*     */     
/* 615 */     return -1.0F;
/*     */   }
/*     */   
/*     */   public static ItemStack getProjectileUsedWeapon(ProjectileEntity entity) {
/* 619 */     return ProjectileExtrasCapability.get((Entity)entity).getWeaponUsed();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\ItemsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */