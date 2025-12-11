/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiEmissionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiInternalDestructionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiInfusionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HakiHelper {
/*     */   public static final String IMBUING_TAG = "imbuingHakiActive";
/*     */   
/*     */   public enum HakiRank {
/*  50 */     BEGINNER,
/*  51 */     INITIATE,
/*  52 */     ADEPT,
/*  53 */     PROFICIENT,
/*  54 */     MASTER;
/*     */     
/*     */     public static boolean isAbove(HakiRank rank, HakiType type, PlayerEntity player) {
/*  57 */       return (HakiHelper.getHakiRank(type, (LivingEntity)player).compareTo(rank) <= 0);
/*     */     }
/*     */     
/*     */     public static boolean isBelow(HakiRank rank, HakiType type, PlayerEntity player) {
/*  61 */       return (HakiHelper.getHakiRank(type, (LivingEntity)player).compareTo(rank) > 0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  67 */   private static final UUID ADVANCED_BUSOSHOKU_HAKI_REACH_BONUS_UUID = UUID.fromString("9e7fec38-e291-49e0-9ba7-10b18c87a6a4");
/*  68 */   public static final UUID ADVANCED_BUSOSHOKU_HAKI_STRENGTH_BONUS_UUID = UUID.fromString("cf88c7dd-fa1d-4b36-a4de-4268efae6378");
/*  69 */   public static final UUID BASIC_BUSOSHOKU_HAKI_STRENGTH_BONUS_UUID = UUID.fromString("0150e464-35c2-4cf0-a510-fd572c55d1d7");
/*     */   
/*     */   public static int getHaoshokuColour(LivingEntity player) {
/*  72 */     if (player == null) {
/*  73 */       return 16711680;
/*     */     }
/*  75 */     return HakiDataCapability.get(player).getHaoshokuHakiColour();
/*     */   }
/*     */   
/*     */   public static double getBasicBusoshokuHakiDamageBoost(LivingEntity entity, float originalAmount) {
/*  79 */     IEntityStats props = EntityStatsCapability.get(entity);
/*  80 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/*  82 */     double dorikiMultiplier = props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit();
/*  83 */     float hakiMultiplier = hakiProps.getBusoshokuHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit();
/*     */     
/*  85 */     return 5.0D + originalAmount / 100.0D * 35.0D * (0.2D * dorikiMultiplier + 0.8D * hakiMultiplier);
/*     */   }
/*     */   
/*     */   public static double getAdvancedBusoshokuHakiDamageBoost(LivingEntity entity) {
/*  89 */     IEntityStats props = EntityStatsCapability.get(entity);
/*  90 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/*  92 */     double dorikiMultiplier = props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit();
/*  93 */     float hakiMultiplier = hakiProps.getBusoshokuHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit();
/*     */     
/*  95 */     return dorikiMultiplier * 2.0D + hakiMultiplier * 8.0D;
/*     */   }
/*     */   
/*     */   public static AbilityAttributeModifier getAdvancedBusoshokuHakiAttackReachBonus(AbilityCore core, double amount) {
/*  99 */     return new AbilityAttributeModifier(ADVANCED_BUSOSHOKU_HAKI_REACH_BONUS_UUID, core, "Advanced Busoshoku Haki Attack Reach Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   public static AbilityAttributeModifier getAdvancedBusoshokuHakiAttackDamageBonus(AbilityCore core, double amount) {
/* 103 */     return new AbilityAttributeModifier(ADVANCED_BUSOSHOKU_HAKI_STRENGTH_BONUS_UUID, core, "Advanced Busoshoku Haki Strength Attack Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   public static AbilityAttributeModifier getBasicBusoshokuHakiAttackDamageBonus(AbilityCore core, double amount) {
/* 107 */     return new AbilityAttributeModifier(BASIC_BUSOSHOKU_HAKI_STRENGTH_BONUS_UUID, core, "Basic Busoshoku Haki Strength Attack Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isHaoshokuBorn(PlayerEntity player) {
/* 112 */     if (!CommonConfig.INSTANCE.isHaoshokuUnlockLogicChanceBased()) {
/* 113 */       return false;
/*     */     }
/* 115 */     boolean isKing = false;
/*     */     
/* 117 */     String[] bits = ("" + player.func_110124_au().getMostSignificantBits()).split("");
/* 118 */     int playerBitsSum = 0;
/* 119 */     for (String bit : bits) {
/*     */       
/* 121 */       if (!bit.equalsIgnoreCase("-"))
/*     */       {
/* 123 */         playerBitsSum += Integer.parseInt(bit); } 
/*     */     } 
/* 125 */     playerBitsSum = MathHelper.func_76125_a(playerBitsSum & 0xA, 0, 10);
/*     */     
/* 127 */     if (CommonConfig.INSTANCE.getHaoshokuUnlockLogic() == CommonConfig.HaoshokuUnlockLogic.TRUE_RANDOM) {
/*     */       
/* 129 */       String[] seedBits = String.valueOf(((ServerWorld)player.field_70170_p).func_72905_C()).split("");
/* 130 */       int worldBitsSum = 0;
/* 131 */       for (String bit : seedBits) {
/*     */         
/* 133 */         if (!bit.equalsIgnoreCase("-"))
/*     */         {
/* 135 */           worldBitsSum += Integer.parseInt(bit); } 
/*     */       } 
/* 137 */       worldBitsSum = MathHelper.func_76125_a(worldBitsSum & 0xA, 0, 10);
/* 138 */       isKing = (playerBitsSum == worldBitsSum);
/*     */     }
/* 140 */     else if (CommonConfig.INSTANCE.getHaoshokuUnlockLogic() == CommonConfig.HaoshokuUnlockLogic.RANDOM || CommonConfig.INSTANCE.getHaoshokuUnlockLogic() == CommonConfig.HaoshokuUnlockLogic.COMBINED) {
/*     */       
/* 142 */       isKing = (playerBitsSum <= 1);
/*     */     } 
/*     */     
/* 145 */     return isKing;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isItemAffectedByImbuing(@Nullable ItemStack itemStack) {
/* 150 */     if (itemStack == null || itemStack.func_190926_b()) {
/* 151 */       return false;
/*     */     }
/* 153 */     if (ItemsHelper.isSword(itemStack)) {
/* 154 */       return true;
/*     */     }
/* 156 */     boolean hasDamageAttribute = (itemStack.func_77973_b().getAttributeModifiers(EquipmentSlotType.MAINHAND, itemStack).get(Attributes.field_233823_f_).size() > 0);
/*     */     
/* 158 */     return hasDamageAttribute;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuHardeningExpNeeded(LivingEntity user) {
/* 163 */     Random rand = user.func_70681_au();
/* 164 */     rand.setSeed(user.func_110124_au().getMostSignificantBits());
/* 165 */     return 30.0D + WyHelper.randomWithRange(rand, -2, 25);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuFullBodyExpNeeded(LivingEntity user) {
/* 170 */     Random rand = user.func_70681_au();
/* 171 */     rand.setSeed(user.func_110124_au().getMostSignificantBits());
/* 172 */     return 50.0D + WyHelper.randomWithRange(rand, 0, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuEmissionExpNeeded(LivingEntity user) {
/* 177 */     Random rand = user.func_70681_au();
/* 178 */     rand.setSeed(user.func_110124_au().getMostSignificantBits());
/* 179 */     return 70.0D + WyHelper.randomWithRange(rand, 0, 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuInternalDestructionExpNeeded(LivingEntity user) {
/* 184 */     Random rand = user.func_70681_au();
/* 185 */     rand.setSeed(user.func_110124_au().getMostSignificantBits());
/* 186 */     return 80.0D + WyHelper.randomWithRange(rand, 0, 15);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getKenbunshokuAuraExpNeeded(LivingEntity user) {
/* 191 */     Random rand = user.func_70681_au();
/* 192 */     rand.setSeed(user.func_110124_au().getMostSignificantBits());
/* 193 */     return 30.0D + WyHelper.randomWithRange(rand, -5, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getKenbunshokuFutureSightExpNeeded(LivingEntity user) {
/* 198 */     Random rand = user.func_70681_au();
/* 199 */     rand.setSeed(user.func_110124_au().getMostSignificantBits());
/* 200 */     return 50.0D + WyHelper.randomWithRange(rand, 0, 30);
/*     */   }
/*     */ 
/*     */   
/*     */   public static HakiRank getHakiRank(HakiType type, LivingEntity entity) {
/* 205 */     IAbilityData abilityData = AbilityDataCapability.get(entity);
/* 206 */     IHakiData hakiData = HakiDataCapability.get(entity);
/* 207 */     HakiRank rank = HakiRank.values()[0];
/*     */     
/* 209 */     float exp = 0.0F;
/* 210 */     boolean check = false;
/*     */     
/* 212 */     if (type == HakiType.BUSOSHOKU) {
/*     */       
/* 214 */       exp = hakiData.getBusoshokuHakiExp();
/* 215 */       check = (abilityData.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE) || abilityData.hasUnlockedAbility(BusoshokuHakiImbuingAbility.INSTANCE));
/*     */     }
/* 217 */     else if (type == HakiType.KENBUNSHOKU) {
/*     */       
/* 219 */       exp = hakiData.getKenbunshokuHakiExp();
/* 220 */       check = abilityData.hasUnlockedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
/*     */     } 
/*     */     
/* 223 */     float per = exp / CommonConfig.INSTANCE.getHakiExpLimit() * 100.0F;
/*     */     
/* 225 */     if (per >= 0.0F && per < 15.0F) {
/* 226 */       rank = HakiRank.BEGINNER;
/* 227 */     } else if (per >= 15.0F && check) {
/*     */       
/* 229 */       if (per >= 15.0F && per < 30.0F) {
/* 230 */         rank = HakiRank.INITIATE;
/* 231 */       } else if (per >= 30.0F && per < 50.0F) {
/* 232 */         rank = HakiRank.ADEPT;
/* 233 */       } else if (per >= 50.0F && per < 90.0F) {
/* 234 */         rank = HakiRank.PROFICIENT;
/* 235 */       } else if (per >= 90.0F) {
/* 236 */         rank = HakiRank.MASTER;
/*     */       } 
/*     */     } 
/* 239 */     return rank;
/*     */   }
/*     */   
/*     */   public static boolean hasAnyHakiEnabled(LivingEntity entity) {
/* 243 */     if (entity == null) {
/* 244 */       return false;
/*     */     }
/*     */     
/* 247 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 249 */     boolean hasAnyHaki = false;
/*     */     
/* 251 */     for (IAbility abl : props.getEquippedAbilities(AbilityCategory.HAKI.isAbilityPartofCategory())) {
/* 252 */       if (abl == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 256 */       Optional<ContinuousComponent> comp = abl.getComponent(ModAbilityKeys.CONTINUOUS);
/* 257 */       if (comp.isPresent() && ((ContinuousComponent)comp.get()).isContinuous()) {
/* 258 */         hasAnyHaki = true;
/*     */       }
/*     */     } 
/*     */     
/* 262 */     return hasAnyHaki;
/*     */   }
/*     */   
/*     */   public static boolean hasAdvancedBusoActive(LivingEntity entity) {
/* 266 */     return (hasBusoEmissionActive(entity) || hasBusoInternalDestructionActive(entity));
/*     */   }
/*     */   
/*     */   public static boolean hasBusoFullbodyActive(LivingEntity entity) {
/* 270 */     if (entity == null) {
/* 271 */       return false;
/*     */     }
/*     */     
/* 274 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 276 */     BusoshokuHakiFullBodyHardeningAbility fullbodyHaki = (BusoshokuHakiFullBodyHardeningAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/*     */     
/* 278 */     boolean isFullbodyActive = (fullbodyHaki != null && fullbodyHaki.isContinuous());
/*     */     
/* 280 */     return isFullbodyActive;
/*     */   }
/*     */   
/*     */   public static boolean hasBusoEmissionActive(LivingEntity entity) {
/* 284 */     if (entity == null) {
/* 285 */       return false;
/*     */     }
/*     */     
/* 288 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 290 */     BusoshokuHakiEmissionAbility emissionHaki = (BusoshokuHakiEmissionAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiEmissionAbility.INSTANCE);
/*     */     
/* 292 */     boolean isEmissionActive = (emissionHaki != null && emissionHaki.isContinuous());
/*     */     
/* 294 */     return isEmissionActive;
/*     */   }
/*     */   
/*     */   public static boolean hasBusoInternalDestructionActive(LivingEntity entity) {
/* 298 */     if (entity == null) {
/* 299 */       return false;
/*     */     }
/*     */     
/* 302 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 304 */     BusoshokuHakiInternalDestructionAbility internalDestructionHaki = (BusoshokuHakiInternalDestructionAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiInternalDestructionAbility.INSTANCE);
/*     */     
/* 306 */     boolean isInternalDestructionHaki = (internalDestructionHaki != null && internalDestructionHaki.isContinuous());
/*     */     
/* 308 */     return isInternalDestructionHaki;
/*     */   }
/*     */   
/*     */   public static boolean hasInfusionActive(LivingEntity entity) {
/* 312 */     if (entity == null) {
/* 313 */       return false;
/*     */     }
/*     */     
/* 316 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 318 */     if (props == null) {
/* 319 */       return false;
/*     */     }
/*     */     
/* 322 */     Ability abl = (Ability)props.getEquippedAbility(HaoshokuHakiInfusionAbility.INSTANCE);
/*     */     
/* 324 */     return (abl != null && abl.isContinuous());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasHardeningActive(LivingEntity entity) {
/* 329 */     return hasHardeningActive(entity, true, true);
/*     */   }
/*     */   
/*     */   public static boolean hasHardeningActive(LivingEntity entity, boolean includeInfusion, boolean includeAdvancedBuso) {
/* 333 */     if (entity == null) {
/* 334 */       return false;
/*     */     }
/*     */     
/* 337 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/* 338 */     BusoshokuHakiHardeningAbility hardeningHaki = (BusoshokuHakiHardeningAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
/* 339 */     boolean hasHardeningBusoHakiActive = (hardeningHaki != null && hardeningHaki.isContinuous());
/* 340 */     if (hasHardeningBusoHakiActive) {
/* 341 */       return true;
/*     */     }
/*     */     
/* 344 */     BusoshokuHakiFullBodyHardeningAbility fullbodyHaki = (BusoshokuHakiFullBodyHardeningAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/* 345 */     boolean hasFullbodyBusoHakiActive = (fullbodyHaki != null && fullbodyHaki.isContinuous());
/* 346 */     if (hasFullbodyBusoHakiActive) {
/* 347 */       return true;
/*     */     }
/*     */     
/* 350 */     if (includeInfusion) {
/* 351 */       HaoshokuHakiInfusionAbility infusionHaki = (HaoshokuHakiInfusionAbility)attackerAbilityProps.getEquippedAbility(HaoshokuHakiInfusionAbility.INSTANCE);
/* 352 */       boolean hasInfusionActive = (infusionHaki != null && infusionHaki.isContinuous());
/* 353 */       if (hasInfusionActive) {
/* 354 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 358 */     if (includeAdvancedBuso) {
/* 359 */       BusoshokuHakiEmissionAbility emissionHaki = (BusoshokuHakiEmissionAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiEmissionAbility.INSTANCE);
/* 360 */       boolean hasEmissionHakiActive = (emissionHaki != null && emissionHaki.isContinuous());
/* 361 */       if (hasEmissionHakiActive) {
/* 362 */         return true;
/*     */       }
/*     */       
/* 365 */       BusoshokuHakiInternalDestructionAbility idHaki = (BusoshokuHakiInternalDestructionAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiInternalDestructionAbility.INSTANCE);
/* 366 */       boolean hasIDHakiActive = (idHaki != null && idHaki.isContinuous());
/* 367 */       if (hasIDHakiActive) {
/* 368 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 372 */     if (!(entity instanceof PlayerEntity) && hasNativeHaki(entity)) {
/* 373 */       return true;
/*     */     }
/*     */     
/* 376 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasImbuingActive(LivingEntity entity) {
/* 381 */     return hasImbuingActive(entity, true, true);
/*     */   }
/*     */   
/*     */   public static boolean hasImbuingActive(LivingEntity entity, boolean includeInfusion, boolean includeAdvancedBuso) {
/* 385 */     if (entity == null) {
/* 386 */       return false;
/*     */     }
/*     */     
/* 389 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 391 */     if (attackerAbilityProps == null) {
/* 392 */       return false;
/*     */     }
/*     */     
/* 395 */     BusoshokuHakiImbuingAbility imbuingHaki = (BusoshokuHakiImbuingAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
/* 396 */     boolean hasImbuingBusoHakiActive = (imbuingHaki != null && imbuingHaki.isContinuous());
/* 397 */     if (hasImbuingBusoHakiActive) {
/* 398 */       return true;
/*     */     }
/*     */     
/* 401 */     if (includeInfusion) {
/* 402 */       HaoshokuHakiInfusionAbility infusionHaki = (HaoshokuHakiInfusionAbility)attackerAbilityProps.getEquippedAbility(HaoshokuHakiInfusionAbility.INSTANCE);
/* 403 */       boolean hasInfusionActive = (infusionHaki != null && infusionHaki.isContinuous());
/* 404 */       if (hasInfusionActive) {
/* 405 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 409 */     if (includeAdvancedBuso) {
/* 410 */       BusoshokuHakiEmissionAbility emissionHaki = (BusoshokuHakiEmissionAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiEmissionAbility.INSTANCE);
/* 411 */       boolean hasEmissionHakiActive = (emissionHaki != null && emissionHaki.isContinuous());
/* 412 */       if (hasEmissionHakiActive) {
/* 413 */         return true;
/*     */       }
/*     */       
/* 416 */       BusoshokuHakiInternalDestructionAbility idHaki = (BusoshokuHakiInternalDestructionAbility)attackerAbilityProps.getEquippedAbility(BusoshokuHakiInternalDestructionAbility.INSTANCE);
/* 417 */       boolean hasIDHakiActive = (idHaki != null && idHaki.isContinuous());
/* 418 */       if (hasIDHakiActive) {
/* 419 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 423 */     if (!(entity instanceof PlayerEntity)) {
/* 424 */       if (entity.func_184614_ca() == null || entity.func_184614_ca().func_190926_b() || !isItemAffectedByImbuing(entity.func_184614_ca())) {
/* 425 */         return false;
/*     */       }
/*     */       
/* 428 */       if (hasNativeHaki(entity)) {
/* 429 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasNativeHaki(LivingEntity entity) {
/* 438 */     ModifiableAttributeInstance attrAtk = entity.func_233645_dx_().func_233779_a_(Attributes.field_233823_f_);
/* 439 */     double atk = (attrAtk != null) ? attrAtk.func_111125_b() : 0.0D;
/*     */     
/* 441 */     boolean hasDoriki = (EntityStatsCapability.get(entity).getDoriki() >= 5000.0D);
/* 442 */     boolean hasPseudoHaki = (atk >= ((entity.field_70170_p.func_175659_aa() == Difficulty.HARD) ? 5 : 7) && CommonConfig.INSTANCE.isNativeHakiEnabled());
/*     */     
/* 444 */     return (hasPseudoHaki || hasDoriki);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkForHakiOveruse(LivingEntity entity, int overuseBonus) {
/* 449 */     if (entity.field_70170_p.field_72995_K) {
/* 450 */       return false;
/*     */     }
/* 452 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/* 454 */     if (overuseBonus != -1) {
/* 455 */       hakiProps.alterHakiOveruse(1 + overuseBonus);
/*     */     }
/* 457 */     return !canEnableHaki(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canEnableHaki(LivingEntity entity) {
/* 462 */     IHakiData props = HakiDataCapability.get(entity);
/* 463 */     return (props.getHakiOveruse() < props.getMaxOveruse());
/*     */   }
/*     */   
/*     */   public static AbilityUseResult canEnableHaki(LivingEntity entity, IAbility ability) {
/* 467 */     IHakiData props = HakiDataCapability.get(entity);
/*     */     
/* 469 */     if (props.getHakiOveruse() < props.getMaxOveruse()) {
/* 470 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 473 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_HAKI));
/*     */   }
/*     */   
/*     */   public static boolean isProjectileBusoshokuImbued(ProjectileEntity entity) {
/* 477 */     return ProjectileExtrasCapability.get((Entity)entity).isProjectileBusoshokuImbued();
/*     */   }
/*     */   
/*     */   public static boolean isProjectileBusoshokuShrouded(ProjectileEntity entity) {
/* 481 */     return ProjectileExtrasCapability.get((Entity)entity).isProjectileBusoshokuShrouded();
/*     */   }
/*     */   
/*     */   public static boolean isProjectileHaoshokuInfused(ProjectileEntity entity) {
/* 485 */     return ProjectileExtrasCapability.get((Entity)entity).isProjectileHaoshokuInfused();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\HakiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */