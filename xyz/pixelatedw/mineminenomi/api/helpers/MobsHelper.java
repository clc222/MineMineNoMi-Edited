/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.passive.horse.AbstractHorseEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.pathfinding.GroundPathNavigator;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ExplosionImmunityAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.SunaLogiaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IThreatLevel;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.ITamableEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.GenkotsuMeteorWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SpinningBrawlWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiEmissionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiImbuingWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.ShiganWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.SoruWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.TekkaiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.HiryuKaenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.SanbyakurokujuPoundHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SEquipAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobsHelper
/*     */ {
/* 126 */   public static final Color MARINE_BLUE_COLOR = WyHelper.hexToRGB("#0084BC");
/*     */   
/* 128 */   public static final ArrayList<Supplier<? extends Item>> PIRATE_SWORDS = Lists.newArrayList((Object[])new Supplier[] { (Supplier)ModWeapons.CUTLASS, (Supplier)ModWeapons.KATANA, (Supplier)ModWeapons.AXE, () -> Items.field_151040_l, () -> Items.field_151052_q, () -> Items.field_151049_t, () -> Items.field_151036_c });
/* 129 */   public static final ArrayList<Supplier<? extends Item>> MARINE_SWORDS = Lists.newArrayList((Object[])new Supplier[] { (Supplier)ModWeapons.BROADSWORD, (Supplier)ModWeapons.KATANA, (Supplier)ModWeapons.SPEAR, () -> Items.field_151040_l, () -> Items.field_151052_q });
/* 130 */   public static final ArrayList<Supplier<? extends Item>> MARINE_CAPTAIN_SWORDS = createMarineCaptainWeaponsList();
/* 131 */   public static final ArrayList<Supplier<? extends Item>> BANDIT_SWORDS = Lists.newArrayList((Object[])new Supplier[] { (Supplier)ModWeapons.DAGGER, (Supplier)ModWeapons.AXE, (Supplier)ModWeapons.KATANA, () -> Items.field_151049_t, () -> Items.field_151036_c });
/* 132 */   public static final ArrayList<Supplier<? extends Item>> BRUTE_SWORDS = Lists.newArrayList((Object[])new Supplier[] { (Supplier)ModWeapons.MACE, (Supplier)ModWeapons.DALTONS_SPADE, (Supplier)ModWeapons.CLEAVER, () -> Items.field_151036_c });
/* 133 */   public static final ArrayList<Supplier<? extends Item>> GORILLA_AXES = Lists.newArrayList((Object[])new Supplier[] { () -> Items.field_151036_c, () -> Items.field_151006_E, () -> Items.field_151049_t, () -> Items.field_151056_x });
/*     */ 
/*     */   
/*     */   private static ArrayList<Supplier<? extends Item>> createMarineCaptainWeaponsList() {
/* 137 */     ArrayList<Supplier<? extends Item>> list = Lists.newArrayList((Object[])new Supplier[] { (Supplier)ModWeapons.JITTE, (Supplier)ModWeapons.BISENTO, () -> Items.field_151048_u });
/* 138 */     list.addAll(MARINE_SWORDS);
/* 139 */     return list;
/*     */   }
/*     */   
/* 142 */   public static final ResourceLocation[] PIRATE_TEXTURES = new ResourceLocation[] { entityTexture("pirate6"), entityTexture("pirate7"), entityTexture("pirate8") };
/*     */   
/* 144 */   public static final ResourceLocation[] PIRATE_FISHMEN_TEXTURES = new ResourceLocation[] { entityTexture("fishman_pirate1"), entityTexture("fishman_pirate2"), entityTexture("fishman_pirate3") };
/* 145 */   public static final ResourceLocation[] PIRATE_CAPTAINS_TEXTURES = new ResourceLocation[] { entityTexture("pirate_captain1"), entityTexture("pirate_captain2"), entityTexture("pirate_captain3"), entityTexture("pirate_captain4"), entityTexture("pirate_captain5"), entityTexture("pirate_captain6"), entityTexture("pirate_captain7"), entityTexture("pirate_captain8") };
/* 146 */   public static final ResourceLocation[] PIRATE_TRADERS_TEXTURES = new ResourceLocation[] { entityTexture("pirate_trader1"), entityTexture("pirate_trader2") };
/*     */   
/* 148 */   public static final ResourceLocation[] MARINE_TEXTURES = new ResourceLocation[] { entityTexture("marine1"), entityTexture("marine2"), entityTexture("marine3"), entityTexture("marine4"), entityTexture("marine5") };
/* 149 */   public static final ResourceLocation[] MARINE_CAPTAINS_TEXTURES = new ResourceLocation[] { entityTexture("marine_captain1"), entityTexture("marine_captain2"), entityTexture("marine_captain3"), entityTexture("marine_captain4"), entityTexture("marine_captain5") };
/* 150 */   public static final ResourceLocation[] MARINE_TRADERS_TEXTURES = new ResourceLocation[] { entityTexture("marine_trader1"), entityTexture("marine_trader2") };
/* 151 */   public static final ResourceLocation[] CELESTIAL_DRAGONS_TEXTURES = new ResourceLocation[] { entityTexture("celestial_dragon1"), entityTexture("celestial_dragon2") };
/* 152 */   public static final ResourceLocation[] MARINE_VICE_ADMIRAL_TEXTURES = new ResourceLocation[] { entityTexture("marine_vice_admiral1"), entityTexture("marine_vice_admiral2"), entityTexture("marine_vice_admiral3") };
/*     */   
/* 154 */   public static final ResourceLocation[] BANDIT_TEXTURES = new ResourceLocation[] { entityTexture("bandit1"), entityTexture("bandit2"), entityTexture("bandit3") };
/*     */   
/* 156 */   public static final ResourceLocation[] SKYPEAN_TEXTURES = new ResourceLocation[] { entityTexture("skypiean_civilian1"), entityTexture("skypiean_civilian2"), entityTexture("skypiean_civilian3") };
/* 157 */   public static final ResourceLocation[] SKYPEAN_TRADERS_TEXTURES = new ResourceLocation[] { entityTexture("skypiean_trader1"), entityTexture("skypiean_trader2") };
/* 158 */   public static final ResourceLocation[] BARKEEPER_TEXTURES = new ResourceLocation[] { entityTexture("barkeeper_1") };
/*     */   
/* 160 */   public static final ResourceLocation[] SWORDSMAN_TRAINER_TEXTURES = new ResourceLocation[] { entityTexture("dojosensei1"), entityTexture("dojosensei2"), entityTexture("dojosensei3") };
/* 161 */   public static final ResourceLocation[] SNIPER_TRAINER_TEXTURES = new ResourceLocation[] { entityTexture("bow_master1"), entityTexture("bow_master2") };
/* 162 */   public static final ResourceLocation[] DOCTOR_TRAINER_TEXTURES = new ResourceLocation[] { entityTexture("doctor1"), entityTexture("doctor2") };
/* 163 */   public static final ResourceLocation[] BRAWLER_TRAINER_TEXTURES = new ResourceLocation[] { entityTexture("brawler_trainer1"), entityTexture("brawler_trainer2") };
/* 164 */   public static final ResourceLocation[] BLACK_LEG_TRAINERS_TEXTURES = new ResourceLocation[] { entityTexture("black_leg_trainer1"), entityTexture("black_leg_trainer2") };
/* 165 */   public static final ResourceLocation[] WEATHER_TRAINER_TEXTURES = new ResourceLocation[] { entityTexture("weather_wizard1") };
/*     */   
/* 167 */   public static final ResourceLocation[] DEN_DEN_MUSHI_TEXTURES = new ResourceLocation[] { entityTexture("den_den_mushi1"), entityTexture("den_den_mushi2"), entityTexture("den_den_mushi3") };
/* 168 */   public static final ResourceLocation[] YAGARA_BULL_TEXTURES = new ResourceLocation[] { entityTexture("yagara_bull1"), entityTexture("yagara_bull2"), entityTexture("yagara_bull3") };
/*     */   
/* 170 */   public static final ResourceLocation[] NIGHTMARE_SOLDIER_TEXTURES = (ResourceLocation[])WyHelper.concatAllArrays((Object[])PIRATE_TEXTURES, (Object[][])new ResourceLocation[][] { PIRATE_FISHMEN_TEXTURES, MARINE_TEXTURES, BANDIT_TEXTURES });
/*     */   
/* 172 */   public static final ResourceLocation[] DUGONG_HEAD_SCARS_TEXTURES = new ResourceLocation[] { entityTexture("dugong_scars/head_0"), entityTexture("dugong_scars/head_1"), entityTexture("dugong_scars/head_2"), entityTexture("dugong_scars/head_3"), entityTexture("dugong_scars/head_4") };
/* 173 */   public static final ResourceLocation[] DUGONG_CHEST_SCARS_TEXTURES = new ResourceLocation[] { entityTexture("dugong_scars/chest_0"), entityTexture("dugong_scars/chest_1"), entityTexture("dugong_scars/chest_2") };
/* 174 */   public static final ResourceLocation[] DUGONG_ARMS_SCARS_TEXTURES = new ResourceLocation[] { entityTexture("dugong_scars/arms_0"), entityTexture("dugong_scars/arms_1") };
/* 175 */   public static final ResourceLocation[] DUGONG_TAIL_SCARS_TEXTURES = new ResourceLocation[] { entityTexture("dugong_scars/tail_0"), entityTexture("dugong_scars/tail_1"), entityTexture("dugong_scars/tail_2") };
/*     */   
/* 177 */   private static final Map<ResourceLocation, List<Function<MobEntity, AbilityWrapperGoal>>> FRUIT_ABILITIES = createFruitAbilitiesMap();
/*     */   private static Map<ResourceLocation, List<Function<MobEntity, AbilityWrapperGoal>>> createFruitAbilitiesMap() {
/* 179 */     Map<ResourceLocation, List<Function<MobEntity, AbilityWrapperGoal>>> map = new HashMap<>();
/*     */     
/* 181 */     map.put(ModAbilities.SUKE_SUKE_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suke.SkattingWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suke.ShishaNoTeWrapperGoal::new }));
/* 182 */     map.put(ModAbilities.KILO_KILO_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo.KiloPress1WrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo.KiloPress10000WrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo.KiloPunch5000WrapperGoal::new }));
/* 183 */     map.put(ModAbilities.DORU_DORU_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.CandleChampionWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.CandleLockWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.CandleWallWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruArtsKenWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruArtsMoriWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruBallWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruNoYakataWrapperGoal::new }));
/* 184 */     map.put(ModAbilities.BOMU_BOMU_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.BreezeBreathBombWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.ExplosivePunchWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.KickBombWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.NoseFancyCannonWrapperGoal::new, mob -> new AlwaysActiveAbilityWrapperGoal(mob, ExplosionImmunityAbility.INSTANCE) }));
/* 185 */     map.put(ModAbilities.SUPA_SUPA_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SparClawWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SparklingDaisyWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SpiderWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SpiralHollowWrapperGoal::new }));
/* 186 */     map.put(ModAbilities.SUNA_SUNA_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.BarjanWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.DesertEncierroWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.DesertGrandeEspadaWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.DesertSpadaWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.GroundSeccoWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.SablesGuardWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.SablesPesadoWrapperGoal::new, mob -> new AlwaysActiveAbilityWrapperGoal(mob, SunaLogiaAbility.INSTANCE) }));
/* 187 */     map.put(ModAbilities.SUBE_SUBE_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sube.SubeSubeSpurWrapperGoal::new }));
/* 188 */     map.put(ModAbilities.GOE_GOE_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.goe.TodorokiWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.goe.DragonsRoarWrapperGoal::new }));
/* 189 */     map.put(ModAbilities.KACHI_KACHI_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kachi.VulcanizationWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kachi.HotBoilingSpecialWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kachi.EvaporateWrapperGoal::new }));
/* 190 */     map.put(ModAbilities.AWA_AWA_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.awa.GoldenHourWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.awa.RelaxHourWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.awa.SoapDefenseWrapperGoal::new }));
/* 191 */     map.put(ModAbilities.BANE_BANE_NO_MI.getRegistryName(), Lists.newArrayList((Object[])new Function[] { xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bane.SpringDeathKnockWrapperGoal::new, xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bane.SpringSnipeWrapperGoal::new }));
/*     */     
/* 193 */     return map;
/*     */   }
/*     */   
/* 196 */   public static final Comparator<LivingEntity> ENTITY_THREAT = new Comparator<LivingEntity>()
/*     */     {
/*     */       public int compare(LivingEntity e1, LivingEntity e2) {
/* 199 */         int e1Threat = (int)EntityStatsCapability.get(e1).getDoriki();
/* 200 */         int e2Threat = (int)EntityStatsCapability.get(e2).getDoriki();
/* 201 */         return e1Threat - e2Threat;
/*     */       }
/*     */     };
/*     */   
/*     */   public static float getThreatLevel(LivingEntity entity) {
/* 206 */     if (entity instanceof IThreatLevel) {
/* 207 */       return ((IThreatLevel)entity).getThreatLevel();
/*     */     }
/*     */     
/* 210 */     double doriki = EntityStatsCapability.get(entity).getDoriki() / CommonConfig.INSTANCE.getDorikiLimit();
/* 211 */     doriki = MathHelper.func_151237_a(doriki, 0.0D, 1.0D);
/* 212 */     double haki = (HakiDataCapability.get(entity).getTotalHakiExp() / (CommonConfig.INSTANCE.getHakiExpLimit() * 2));
/* 213 */     haki = MathHelper.func_151237_a(haki, 0.0D, 1.0D);
/* 214 */     float abilities = (AbilityDataCapability.get(entity).getUnlockedAbilities().size() >= 30) ? 1.0F : 0.0F;
/*     */     
/* 216 */     if (entity instanceof PlayerEntity) {
/* 217 */       abilities = 0.0F;
/*     */     }
/*     */     
/* 220 */     doriki *= 0.4000000059604645D;
/* 221 */     haki *= 0.5D;
/* 222 */     abilities *= 0.1F;
/*     */     
/* 224 */     float threatLevel = (float)(doriki + haki + abilities);
/* 225 */     threatLevel = MathHelper.func_76131_a(threatLevel, 0.0F, 1.0F);
/*     */     
/* 227 */     return threatLevel;
/*     */   }
/*     */   
/*     */   public static Predicate<Entity> shouldTarget(LivingEntity entity) {
/* 231 */     return targetEntity -> {
/*     */         if (!(targetEntity instanceof LivingEntity)) {
/*     */           return false;
/*     */         }
/*     */         if (WyHelper.isInChallengeDimension(entity.field_70170_p)) {
/*     */           return true;
/*     */         }
/*     */         LivingEntity target = (LivingEntity)targetEntity;
/*     */         double entityThreatLevel = getThreatLevel(entity);
/*     */         double targetThreatLevel = getThreatLevel(target) * 0.25D;
/*     */         if (entity.field_70170_p.func_175659_aa().func_151525_a() <= 2) {
/*     */           float mod = 0.0F;
/*     */           switch (entity.field_70170_p.func_175659_aa()) {
/*     */             case EASY:
/*     */               mod = 0.8F;
/*     */               break;
/*     */             case NORMAL:
/*     */               mod = 0.5F;
/*     */               break;
/*     */           } 
/*     */           if (entityThreatLevel * mod > targetThreatLevel) {
/*     */             return false;
/*     */           }
/*     */         } 
/*     */         return (entityThreatLevel >= targetThreatLevel);
/*     */       };
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static LivingEntity getTamer(LivingEntity entity) {
/* 277 */     if (!ModEntityPredicates.canBeTamed().test(entity)) {
/* 278 */       return null;
/*     */     }
/*     */     
/* 281 */     if (entity instanceof TameableEntity) {
/* 282 */       return ((TameableEntity)entity).func_70902_q();
/*     */     }
/*     */     
/* 285 */     if (entity instanceof AbstractHorseEntity) {
/* 286 */       UUID ownerUuid = ((AbstractHorseEntity)entity).func_184780_dh();
/* 287 */       if (ownerUuid == null) {
/* 288 */         return null;
/*     */       }
/* 290 */       return (LivingEntity)entity.field_70170_p.func_217371_b(ownerUuid);
/*     */     } 
/*     */     
/* 293 */     if (entity instanceof ITamableEntity) {
/* 294 */       ITamableEntity tamableEntity = (ITamableEntity)entity;
/* 295 */       return tamableEntity.getOwnerIfAlive();
/*     */     } 
/*     */     
/* 298 */     if (entity instanceof ICommandReceiver) {
/* 299 */       return ((ICommandReceiver)entity).getLastCommandSender();
/*     */     }
/*     */     
/* 302 */     return null;
/*     */   }
/*     */   
/*     */   public static <T extends IAbility> T unlockAndEquipAbility(LivingEntity entity, AbilityCore<T> core) {
/* 306 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 307 */     T ability = unlockAbility(entity, core);
/*     */     
/* 309 */     int availableSlot = props.getEquippedAbilities().size();
/* 310 */     props.setEquippedAbility(availableSlot, (IAbility)ability);
/*     */     
/* 312 */     if (!entity.field_70170_p.field_72995_K) {
/* 313 */       WyNetwork.sendToAllAround(new SEquipAbilityPacket(entity.func_145782_y(), availableSlot, core), (Entity)entity);
/*     */     }
/*     */     
/* 316 */     return ability;
/*     */   }
/*     */   
/*     */   public static <T extends IAbility> T unlockAbility(LivingEntity entity, AbilityCore<T> core) {
/* 320 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 321 */     props.addUnlockedAbility(core, AbilityUnlock.COMMAND);
/* 322 */     return (T)core.createAbility();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceLocation entityTexture(String texture) {
/* 327 */     return entityTexture("mineminenomi", texture);
/*     */   }
/*     */   
/*     */   public static ResourceLocation entityTexture(String modId, String texture) {
/* 331 */     return new ResourceLocation(modId, "textures/models/" + texture + ".png");
/*     */   }
/*     */   
/*     */   public static void addBasicNPCGoals(CreatureEntity entity) {
/* 335 */     ((GroundPathNavigator)entity.func_70661_as()).func_179688_b(true);
/*     */ 
/*     */     
/* 338 */     boolean isBoss = entity instanceof xyz.pixelatedw.mineminenomi.api.challenges.OPBossEntity;
/*     */     
/* 340 */     if (!isBoss) {
/* 341 */       entity.func_70661_as().func_212239_d(true);
/* 342 */       entity.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)entity));
/* 343 */       entity.field_70714_bg.func_75776_a(2, (Goal)new WaterAvoidingRandomWalkingGoal(entity, 0.8D));
/*     */     } 
/* 345 */     entity.field_70714_bg.func_75776_a(0, (Goal)new OpenDoorGoal((MobEntity)entity, true));
/* 346 */     entity.field_70714_bg.func_75776_a(3, (Goal)new LookAtGoal((MobEntity)entity, LivingEntity.class, 8.0F));
/* 347 */     entity.field_70714_bg.func_75776_a(3, (Goal)new LookRandomlyGoal((MobEntity)entity));
/*     */     
/* 349 */     Predicate<Entity> hurtByCheck = ModEntityPredicates.getEnemyFactions((LivingEntity)entity).and(ModEntityPredicates.IS_ENTITY_HARMLESS.negate());
/* 350 */     Predicate<Entity> targetMobCheck = hurtByCheck.and(ModEntityPredicates.IS_INVISIBLE.negate()).and(shouldTarget((LivingEntity)entity));
/* 351 */     Predicate<Entity> targetPlayerCheck = targetMobCheck;
/*     */     
/* 353 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*     */     
/* 355 */     FactionHurtByTargetGoal hurtByTargetGoal = new FactionHurtByTargetGoal(entity, hurtByCheck, new Class[0]);
/*     */     
/* 357 */     if (props.isMarine() || props.isBountyHunter()) {
/* 358 */       targetPlayerCheck = targetPlayerCheck.and(ModEntityPredicates.TARGET_HAS_ISSUED_BOUNTY);
/* 359 */       hurtByTargetGoal.func_220794_a(new Class[0]);
/*     */     } 
/*     */     
/* 362 */     entity.field_70715_bh.func_75776_a(1, (Goal)hurtByTargetGoal);
/* 363 */     entity.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)entity, MobEntity.class, 10, true, true, targetMobCheck));
/* 364 */     entity.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)entity, PlayerEntity.class, 10, true, true, targetPlayerCheck));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Set<Goal> getBrawlerAbilities(MobEntity entity, int maxGoals) {
/* 372 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 373 */     goals.addEntry(() -> new HakaiHoWrapperGoal(entity), 3.0F);
/* 374 */     goals.addEntry(() -> new SuplexWrapperGoal(entity), 3.0F);
/* 375 */     goals.addEntry(() -> new GenkotsuMeteorWrapperGoal(entity), 2.0F);
/* 376 */     goals.addEntry(() -> new JishinHoWrapperGoal(entity), 2.0F);
/* 377 */     goals.addEntry(() -> new SpinningBrawlWrapperGoal(entity), 1.0F);
/*     */     
/* 379 */     return getRandomizedGoals(entity, maxGoals, goals);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void addBrawlerAbilities(MobEntity entity, int maxGoals) {
/* 384 */     applyRandomizedGoals(entity, maxGoals, getBrawlerAbilities(entity, maxGoals));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Set<Goal> getSwordsmanAbilities(MobEntity entity, int maxGoals) {
/* 389 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 390 */     goals.addEntry(() -> new HiryuKaenWrapperGoal(entity), 4.0F);
/* 391 */     goals.addEntry(() -> new ShiShishiSonsonWrapperGoal(entity), 4.0F);
/* 392 */     goals.addEntry(() -> new YakkodoriWrapperGoal(entity), 3.0F);
/* 393 */     goals.addEntry(() -> new OTatsumakiWrapperGoal(entity), 2.0F);
/* 394 */     goals.addEntry(() -> new SanbyakurokujuPoundHoWrapperGoal(entity), 2.0F);
/*     */     
/* 396 */     return getRandomizedGoals(entity, maxGoals, goals);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void addSwordsmanAbilities(MobEntity entity, int maxGoals) {
/* 401 */     applyRandomizedGoals(entity, maxGoals, getSwordsmanAbilities(entity, maxGoals));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Set<Goal> getRokushikiAbilities(MobEntity entity, int maxGoals) {
/* 406 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 407 */     goals.addEntry(() -> new SoruWrapperGoal(entity), 10.0F);
/* 408 */     goals.addEntry(() -> new ShiganWrapperGoal(entity), 7.0F);
/* 409 */     goals.addEntry(() -> new TekkaiWrapperGoal(entity), 7.0F);
/* 410 */     goals.addEntry(() -> new RankyakuWrapperGoal(entity), 1.0F);
/*     */     
/* 412 */     return getRandomizedGoals(entity, maxGoals, goals);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void addRokushikiAbilities(MobEntity entity, int maxGoals) {
/* 417 */     applyRandomizedGoals(entity, maxGoals, getRokushikiAbilities(entity, maxGoals));
/*     */   }
/*     */   
/*     */   public static void addBusoshokuHaki(MobEntity entity, int chance) {
/* 421 */     if (chance > WyHelper.randomWithRange(0, 100)) {
/* 422 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/* 423 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)entity);
/* 424 */       if (props.isSniper() || props.isSwordsman()) {
/* 425 */         hakiProps.setBusoshokuHakiExp((30 + entity.func_70681_au().nextInt(20)));
/* 426 */         entity.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiImbuingWrapperGoal(entity));
/* 427 */         if ((chance / 3) > WyHelper.randomWithRange(0, 100)) {
/* 428 */           hakiProps.setBusoshokuHakiExp((50 + entity.func_70681_au().nextInt(20)));
/* 429 */           entity.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal(entity));
/*     */         }
/*     */       
/*     */       }
/* 433 */       else if ((chance / 3) > WyHelper.randomWithRange(0, 100)) {
/* 434 */         hakiProps.setBusoshokuHakiExp((50 + entity.func_70681_au().nextInt(20)));
/* 435 */         entity.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal(entity));
/*     */       } else {
/*     */         
/* 438 */         hakiProps.setBusoshokuHakiExp((30 + entity.func_70681_au().nextInt(20)));
/* 439 */         entity.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiHardeningWrapperGoal(entity));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addAdvancedBusoshokuHaki(MobEntity entity, int chance) {
/* 446 */     if (chance > WyHelper.randomWithRange(0, 100)) {
/* 447 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/* 448 */       if (entity.func_70681_au().nextInt(10) < 6) {
/* 449 */         entity.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiEmissionWrapperGoal(entity));
/*     */       } else {
/*     */         
/* 452 */         entity.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal(entity));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Set<Goal> getRandomizedGoals(MobEntity entity, int maxGoals, WeightedList<Supplier<Goal>> list) {
/* 459 */     int rolls = 5;
/*     */     
/* 461 */     Set<Goal> goals = new HashSet<>();
/*     */     
/* 463 */     if (list.size() <= 0) {
/* 464 */       return goals;
/*     */     }
/*     */     
/* 467 */     while (goals.size() < maxGoals && 
/* 468 */       rolls > 0) {
/*     */ 
/*     */ 
/*     */       
/* 472 */       Goal goal = ((Supplier<Goal>)list.pick(entity.func_70681_au())).get();
/*     */       
/* 474 */       if (goal == null) {
/* 475 */         rolls--;
/*     */         
/*     */         continue;
/*     */       } 
/* 479 */       boolean alreadyInList = goals.stream().map(g -> g.toString()).anyMatch(s -> goal.toString().equalsIgnoreCase(s));
/* 480 */       if (!alreadyInList) {
/* 481 */         goals.add(goal);
/*     */         
/*     */         continue;
/*     */       } 
/* 485 */       rolls--;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     return goals;
/*     */   }
/*     */   
/*     */   private static void applyRandomizedGoals(MobEntity entity, int maxGoals, Set<Goal> goals) {
/* 495 */     goals.forEach(goal -> entity.field_70714_bg.func_75776_a(4, goal));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Optional<List<AbilityWrapperGoal>> getRandomDevilFruitAbilities(MobEntity entity) {
/* 500 */     ResourceLocation fruit = (ResourceLocation)FRUIT_ABILITIES.keySet().toArray()[entity.func_70681_au().nextInt(FRUIT_ABILITIES.size())];
/* 501 */     if (fruit == null) {
/* 502 */       return Optional.empty();
/*     */     }
/*     */     
/* 505 */     return getDevilFruitAbilities(entity, fruit);
/*     */   }
/*     */   
/*     */   public static Optional<List<AbilityWrapperGoal>> getDevilFruitAbilities(MobEntity entity, ResourceLocation fruit) {
/* 509 */     if (!FRUIT_ABILITIES.containsKey(fruit)) {
/* 510 */       return Optional.empty();
/*     */     }
/*     */     
/* 513 */     List<Function<MobEntity, AbilityWrapperGoal>> abilities = FRUIT_ABILITIES.get(fruit);
/* 514 */     if (abilities == null || abilities.isEmpty()) {
/* 515 */       return Optional.empty();
/*     */     }
/*     */     
/* 518 */     IDevilFruit fruitData = DevilFruitCapability.get((LivingEntity)entity);
/* 519 */     if (fruitData != null) {
/* 520 */       fruitData.setDevilFruit(fruit);
/*     */     }
/*     */     
/* 523 */     return Optional.ofNullable((List<AbilityWrapperGoal>)abilities.stream().map(func -> (AbilityWrapperGoal)func.apply(entity)).collect(Collectors.toList()));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\MobsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */