/*      */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*      */ 
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityType;
/*      */ import net.minecraft.entity.LivingEntity;
/*      */ import net.minecraft.entity.MoverType;
/*      */ import net.minecraft.entity.Pose;
/*      */ import net.minecraft.entity.ai.attributes.Attribute;
/*      */ import net.minecraft.entity.ai.attributes.Attributes;
/*      */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*      */ import net.minecraft.entity.player.PlayerEntity;
/*      */ import net.minecraft.entity.player.ServerPlayerEntity;
/*      */ import net.minecraft.fluid.FluidState;
/*      */ import net.minecraft.inventory.EquipmentSlotType;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.network.IPacket;
/*      */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*      */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*      */ import net.minecraft.potion.Effect;
/*      */ import net.minecraft.potion.EffectInstance;
/*      */ import net.minecraft.tags.FluidTags;
/*      */ import net.minecraft.tags.ITag;
/*      */ import net.minecraft.util.EntityPredicates;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.SoundCategory;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.util.SoundEvents;
/*      */ import net.minecraft.util.Util;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.MutableBoundingBox;
/*      */ import net.minecraft.util.math.vector.Vector3d;
/*      */ import net.minecraft.util.math.vector.Vector3i;
/*      */ import net.minecraft.util.text.IFormattableTextComponent;
/*      */ import net.minecraft.util.text.ITextComponent;
/*      */ import net.minecraft.util.text.StringTextComponent;
/*      */ import net.minecraft.util.text.Style;
/*      */ import net.minecraft.util.text.TextFormatting;
/*      */ import net.minecraft.util.text.TranslationTextComponent;
/*      */ import net.minecraft.world.IWorld;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.server.ServerWorld;
/*      */ import net.minecraftforge.api.distmarker.Dist;
/*      */ import net.minecraftforge.api.distmarker.OnlyIn;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.common.util.BlockSnapshot;
/*      */ import net.minecraftforge.event.ForgeEventFactory;
/*      */ import net.minecraftforge.eventbus.api.Event;
/*      */ import net.minecraftforge.fml.RegistryObject;
/*      */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*      */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*      */ import org.apache.commons.lang3.tuple.Pair;
/*      */ import xyz.pixelatedw.mineminenomi.abilities.LogiaBlockBypassingAbility;
/*      */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*      */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
/*      */ import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.ReferenceTextComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.OutOfBodyAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireAbilityComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*      */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.SetOnFireEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.ability.UnlockAbilityEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*      */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*      */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*      */ import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
/*      */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*      */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*      */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*      */ import xyz.pixelatedw.mineminenomi.events.abilities.OutOfBodyAbilitiesEvents;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*      */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SDisableAbilityPacket;
/*      */ import xyz.pixelatedw.mineminenomi.particles.effects.GuardParticleEffect;
/*      */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AbilityHelper
/*      */ {
/*      */   public static final int CLOUD_HEIGHT = 128;
/*  151 */   public static final ArrayList<SourceType> NO_SOURCE_TYPE = new ArrayList<>(Arrays.asList(new SourceType[] { SourceType.UNKNOWN })); public static final Function<PlayerEntity, ResourceLocation> DF_CATEGORY_ICON; public static final Function<PlayerEntity, ResourceLocation> RACE_CATEGORY_ICON;
/*  152 */   private static final Object[] EMPTY_ARGS = new Object[0]; public static final Function<PlayerEntity, ResourceLocation> HAKI_CATEGORY_ICON; public static final Predicate<Entity> HAS_LOGIA_INVULNERABILITY;
/*      */   static {
/*  154 */     DF_CATEGORY_ICON = (player -> {
/*      */         IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*      */ 
/*      */         
/*      */         ResourceLocation icon = null;
/*      */ 
/*      */         
/*      */         if (props.hasAnyDevilFruit()) {
/*      */           ResourceLocation res;
/*      */ 
/*      */           
/*      */           if (props.hasYamiPower()) {
/*      */             res = ModAbilities.YAMI_YAMI_NO_MI.getRegistryName();
/*      */           } else {
/*      */             res = props.getDevilFruit().orElse(ModValues.NIL_LOCATION);
/*      */           } 
/*      */ 
/*      */           
/*      */           if (ClientConfig.INSTANCE.isGoroBlue() && res.toString().equals("mineminenomi:goro_goro_no_mi")) {
/*      */             res = new ResourceLocation("mineminenomi", "goro_goro_no_mi_blue");
/*      */           }
/*      */           
/*      */           icon = new ResourceLocation(res.func_110624_b(), "textures/items/" + res.func_110623_a() + ".png");
/*      */         } 
/*      */         
/*      */         return icon;
/*      */       });
/*      */     
/*  182 */     RACE_CATEGORY_ICON = (player -> {
/*      */         String iconName = null;
/*      */ 
/*      */ 
/*      */         
/*      */         IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*      */ 
/*      */ 
/*      */         
/*      */         if (props.isHuman()) {
/*      */           iconName = "human-rokushiki";
/*      */         } else if (props.isFishman()) {
/*      */           iconName = "fishman-karate";
/*      */         } else if (props.isCyborg()) {
/*      */           iconName = "cyborg-abilities";
/*      */         } else if (props.isMink()) {
/*      */           iconName = "mink-electro";
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         return (iconName != null) ? new ResourceLocation("mineminenomi", "textures/gui/icons/" + iconName + ".png") : null;
/*      */       });
/*      */ 
/*      */ 
/*      */     
/*  208 */     HAKI_CATEGORY_ICON = (player -> {
/*      */         float haoLevel = HakiDataCapability.get((LivingEntity)player).getTotalHakiExp() / 100.0F;
/*      */         
/*      */         String id = "haoshoku_haki";
/*      */         
/*      */         String level = "0";
/*      */         
/*      */         if (haoLevel > 1.0F && haoLevel <= 1.75D) {
/*      */           level = "1";
/*      */         } else if (haoLevel > 1.75D) {
/*      */           level = "2";
/*      */         } 
/*      */         
/*      */         return new ResourceLocation("mineminenomi", "textures/abilities/" + id + "_" + level + ".png");
/*      */       });
/*  223 */     HAS_LOGIA_INVULNERABILITY = (entity -> {
/*      */         boolean isNotSpectating = EntityPredicates.field_180132_d.test(entity);
/*      */         
/*      */         boolean isLogia = false;
/*      */         
/*      */         if (entity instanceof LivingEntity) {
/*      */           IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
/*      */           
/*  231 */           isLogia = (props.isLogia() && !props.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI));
/*      */         } 
/*      */         
/*  234 */         return (isNotSpectating && isLogia);
/*      */       });
/*      */   }
/*      */   public static AbilityDescriptionLine.IDescriptionLine createShortLongCooldownStat(float shortCooldown, float longCooldown) {
/*  238 */     return (entity, ability) -> {
/*      */         TranslationTextComponent secondsStackUnit = new TranslationTextComponent("%s %s", new Object[] { ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_SECONDS.getString(), ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_PER_STACK.getString() });
/*      */         float shortCooldownSeconds = shortCooldown / 20.0F;
/*      */         float longCooldownSeconds = longCooldown / 20.0F;
/*      */         AbilityStat.Builder shortStatBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_SHORT_COOLDOWN, shortCooldownSeconds)).withUnit((ITextComponent)secondsStackUnit);
/*      */         AbilityStat.Builder longStatBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_LONG_COOLDOWN, longCooldownSeconds)).withUnit((ITextComponent)secondsStackUnit);
/*      */         ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(());
/*      */         ITextComponent shortStat = shortStatBuilder.build().getStatDescription();
/*      */         ITextComponent longStat = longStatBuilder.build().getStatDescription();
/*      */         StringBuilder sb = new StringBuilder();
/*      */         sb.append(shortStat.getString() + "\n");
/*      */         sb.append(longStat.getString());
/*      */         return (ITextComponent)new StringTextComponent(sb.toString());
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  266 */   public static final Style MENTION_STYLE = Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN);
/*      */   
/*      */   @Nullable
/*      */   public static IFormattableTextComponent tryParseAndMention(RegistryObject<? extends IForgeRegistryEntry<?>> obj) {
/*  270 */     if (!obj.isPresent()) {
/*  271 */       return null;
/*      */     }
/*      */     
/*  274 */     return mentionEntry(obj.get());
/*      */   }
/*      */   
/*      */   private static IFormattableTextComponent mentionEntry(IForgeRegistryEntry<?> entry) {
/*  278 */     if (entry instanceof AbilityCore) {
/*  279 */       return mentionAbility((AbilityCore)entry);
/*      */     }
/*  281 */     if (entry instanceof Item) {
/*  282 */       return mentionItem((Item)entry);
/*      */     }
/*  284 */     if (entry instanceof Effect) {
/*  285 */       return mentionEffect((Effect)entry);
/*      */     }
/*  287 */     if (entry instanceof MorphInfo) {
/*  288 */       return mentionMorph((MorphInfo)entry);
/*      */     }
/*      */     
/*  291 */     return null;
/*      */   }
/*      */   
/*      */   public static IFormattableTextComponent mentionAbility(AbilityCore<?> core) {
/*  295 */     return core.getLocalizedName().func_230532_e_().func_230530_a_(MENTION_STYLE);
/*      */   }
/*      */   
/*      */   public static IFormattableTextComponent mentionMorph(MorphInfo info) {
/*  299 */     return (new StringTextComponent(info.getDisplayName())).func_230532_e_().func_230530_a_(MENTION_STYLE);
/*      */   }
/*      */   
/*      */   public static IFormattableTextComponent mentionItem(Item item) {
/*  303 */     TranslationTextComponent translationTextComponent = new TranslationTextComponent(item.func_77658_a());
/*  304 */     return translationTextComponent.func_230530_a_(MENTION_STYLE);
/*      */   }
/*      */   
/*      */   public static IFormattableTextComponent mentionEffect(Effect effect) {
/*  308 */     return effect.func_199286_c().func_230532_e_().func_230530_a_(MENTION_STYLE);
/*      */   }
/*      */   
/*      */   public static IFormattableTextComponent mentionText(Object text) {
/*  312 */     if (text instanceof ITextComponent) {
/*  313 */       return ((ITextComponent)text).func_230532_e_().func_230530_a_(MENTION_STYLE);
/*      */     }
/*      */     
/*  316 */     return (new StringTextComponent(text.toString())).func_230530_a_(MENTION_STYLE);
/*      */   }
/*      */ 
/*      */   
/*      */   @SafeVarargs
/*      */   public static IFormattableTextComponent[] registerDescriptionText(String modid, String abilityName, Pair<String, Object[]>... pairs) {
/*  322 */     IFormattableTextComponent[] components = new IFormattableTextComponent[pairs.length];
/*  323 */     for (int i = 0; i < pairs.length; i++) {
/*  324 */       String key = String.format("ability.%s.%s.description.%s", new Object[] { modid, abilityName, Integer.valueOf(i) });
/*  325 */       key = WyRegistry.registerName(key, (String)pairs[i].getKey());
/*  326 */       Object[] args = (Object[])pairs[i].getValue();
/*  327 */       if (args != null) {
/*  328 */         for (int j = 0; j < args.length; j++) {
/*  329 */           Object o = args[j];
/*  330 */           if (o instanceof RegistryObject) {
/*  331 */             args[j] = new ReferenceTextComponent((RegistryObject)o);
/*      */           }
/*  333 */           else if (o instanceof IForgeRegistryEntry) {
/*  334 */             args[j] = mentionEntry((IForgeRegistryEntry)o);
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  339 */         args = EMPTY_ARGS;
/*      */       } 
/*  341 */       TranslationTextComponent comp = new TranslationTextComponent(key, args);
/*  342 */       components[i] = (IFormattableTextComponent)comp;
/*      */     } 
/*  344 */     return components;
/*      */   }
/*      */   
/*      */   public static void setSecondsOnFireBy(Entity entity, int seconds, @Nullable LivingEntity attacker) {
/*  348 */     SetOnFireEvent event = new SetOnFireEvent(attacker, entity, seconds);
/*  349 */     if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/*  350 */       entity.func_70015_d(event.getFireTime());
/*      */     }
/*      */   }
/*      */   
/*      */   public static void emergencyStopAbility(LivingEntity entity, IAbility ability) {
/*  355 */     ability.getComponent(ModAbilityKeys.GRAB).ifPresent(comp -> comp.release(entity));
/*      */ 
/*      */ 
/*      */     
/*  359 */     ability.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> {
/*      */           if (comp.isContinuous()) {
/*      */             comp.stopContinuity(entity);
/*      */           }
/*      */         });
/*      */     
/*  365 */     ability.getComponent(ModAbilityKeys.CHARGE).ifPresent(comp -> {
/*      */           if (comp.isCharging()) {
/*      */             comp.forceStopCharging(entity);
/*      */             
/*      */             ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(());
/*      */           } 
/*      */         });
/*  372 */     ability.getComponent(ModAbilityKeys.ANIMATION).ifPresent(comp -> comp.stop(entity));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @OnlyIn(Dist.CLIENT)
/*      */   public static boolean showAbilityAdvancedTooltips() {
/*  379 */     Minecraft mc = Minecraft.func_71410_x();
/*      */     
/*  381 */     return mc.field_71474_y.field_82882_x;
/*      */   }
/*      */   
/*      */   public static boolean checkValidAbility(LivingEntity entity, AbilityCore<?> core) {
/*  385 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  386 */     boolean isAbilityUnlocked = props.hasUnlockedAbility(core);
/*  387 */     boolean isAbilityBanned = (CommonConfig.INSTANCE.isAbilityFraudChecksEnabled() && verifyIfAbilityIsBanned(core));
/*  388 */     boolean canUnlock = core.canUnlock(entity);
/*      */     
/*  390 */     UnlockAbilityEvent event = new UnlockAbilityEvent(entity, core);
/*  391 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*      */     
/*  393 */     if (event.getResult() == Event.Result.ALLOW) {
/*  394 */       canUnlock = true;
/*      */     }
/*  396 */     else if (event.getResult() == Event.Result.DENY) {
/*  397 */       canUnlock = false;
/*      */     } 
/*      */     
/*  400 */     if (isAbilityUnlocked && props.getUnlockTypeForAbility(core) == AbilityUnlock.PROGRESSION && !canUnlock) {
/*  401 */       return false;
/*      */     }
/*      */     
/*  404 */     if (isAbilityBanned) {
/*  405 */       return false;
/*      */     }
/*      */     
/*  408 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean checkAndUnlockAbility(LivingEntity entity, AbilityCore<?> core) {
/*  415 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  416 */     boolean hasAbilityUnlocked = props.hasUnlockedAbility(core);
/*  417 */     boolean isAbilityBanned = (CommonConfig.INSTANCE.isAbilityFraudChecksEnabled() && verifyIfAbilityIsBanned(core));
/*  418 */     boolean canUnlock = core.canUnlock(entity);
/*      */     
/*  420 */     UnlockAbilityEvent event = new UnlockAbilityEvent(entity, core);
/*  421 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*      */     
/*  423 */     if (event.getResult() == Event.Result.ALLOW) {
/*  424 */       canUnlock = true;
/*      */     }
/*  426 */     else if (event.getResult() == Event.Result.DENY) {
/*  427 */       canUnlock = false;
/*      */     } 
/*      */     
/*  430 */     if (hasAbilityUnlocked) {
/*  431 */       if (isAbilityBanned) {
/*  432 */         props.removeUnlockedAbility(core);
/*  433 */         return true;
/*      */       } 
/*      */       
/*  436 */       if (!canUnlock) {
/*  437 */         AbilityUnlock unlockType = props.getUnlockTypeForAbility(core);
/*  438 */         if (props.hasUnlockedAbility(core) && unlockType == AbilityUnlock.PROGRESSION) {
/*  439 */           props.removeUnlockedAbility(core);
/*  440 */           return true;
/*      */         } 
/*      */       } 
/*      */       
/*  444 */       return false;
/*      */     } 
/*      */     
/*  447 */     if (!isAbilityBanned && canUnlock) {
/*  448 */       props.addUnlockedAbility(core, AbilityUnlock.PROGRESSION);
/*  449 */       return true;
/*      */     } 
/*      */     
/*  452 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Nullable
/*      */   public static AnimationComponent getActiveAnimation(LivingEntity entity) {
/*  462 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*      */     
/*  464 */     if (abilityProps == null) {
/*  465 */       return null;
/*      */     }
/*      */     
/*  468 */     List<IAbility> abilities = new ArrayList<>();
/*  469 */     abilities.addAll((Collection<? extends IAbility>)ImmutableList.copyOf(abilityProps.getEquippedAbilities()));
/*  470 */     abilities.addAll((Collection<? extends IAbility>)ImmutableList.copyOf(abilityProps.getPassiveAbilities()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  483 */     Optional<AnimationComponent> animComp = abilities.stream().filter(ability -> { if (!ability.hasComponent(ModAbilityKeys.ANIMATION)) return false;  AnimationComponent comp = ability.getComponent(ModAbilityKeys.ANIMATION).get(); return (comp == null) ? false : comp.isPlaying(); }).map(a -> (AnimationComponent)a.getComponent(ModAbilityKeys.ANIMATION).get()).findFirst();
/*  484 */     return animComp.orElse(null);
/*      */   }
/*      */   
/*      */   public static void enableAbilities(LivingEntity entity, Predicate<IAbility> check) {
/*  488 */     IAbilityData abilityData = AbilityDataCapability.get(entity);
/*      */ 
/*      */ 
/*      */     
/*  492 */     List<IAbility> abilities = (List<IAbility>)abilityData.getEquippedAndPassiveAbilities().stream().filter(ability -> check.test(ability)).collect(Collectors.toList());
/*      */     
/*  494 */     Set<IAbility> markedAbilities = new HashSet<>();
/*      */     
/*  496 */     for (IAbility ability : abilities) {
/*  497 */       ability.getComponent(ModAbilityKeys.DISABLE).ifPresent(component -> {
/*      */             component.stopDisable(entity);
/*      */             
/*      */             markedAbilities.add(ability);
/*      */           });
/*      */     } 
/*  503 */     if (!entity.field_70170_p.field_72995_K) {
/*  504 */       WyNetwork.sendToAllTrackingAndSelf(new SDisableAbilityPacket(entity.func_145782_y(), markedAbilities, 0, false), (Entity)entity);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void disableAbilities(LivingEntity entity, int duration, Predicate<IAbility> check) {
/*  509 */     IAbilityData abilityData = AbilityDataCapability.get(entity);
/*      */ 
/*      */ 
/*      */     
/*  513 */     List<IAbility> abilities = (List<IAbility>)abilityData.getEquippedAndPassiveAbilities().stream().filter(ability -> check.test(ability)).collect(Collectors.toList());
/*      */     
/*  515 */     Set<IAbility> markedAbilities = new HashSet<>();
/*      */     
/*  517 */     for (IAbility ability : abilities) {
/*  518 */       ability.getComponent(ModAbilityKeys.DISABLE).ifPresent(component -> {
/*      */             emergencyStopAbility(entity, ability);
/*      */             
/*      */             component.stopDisable(entity);
/*      */             component.startDisable(entity, duration);
/*      */             markedAbilities.add(ability);
/*      */           });
/*      */     } 
/*  526 */     if (!entity.field_70170_p.field_72995_K) {
/*  527 */       WyNetwork.sendToAllTrackingAndSelf(new SDisableAbilityPacket(entity.func_145782_y(), markedAbilities, duration, true), (Entity)entity);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean canUseMomentumAbilities(LivingEntity entity) {
/*  532 */     boolean canJump = (entity.func_110148_a((Attribute)ModAttributes.JUMP_HEIGHT.get()).func_111126_e() > 0.0D);
/*  533 */     boolean canMove = (entity.func_110148_a(Attributes.field_233821_d_).func_111126_e() > 0.0D);
/*      */     
/*  535 */     return (canJump && canMove);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static AbilityOverlay getCurrentOverlay(PlayerEntity player) {
/*  541 */     AbilityOverlay overlay = null;
/*  542 */     Set<Ability> list = AbilityDataCapability.get((LivingEntity)player).getEquippedAbilities();
/*  543 */     for (Ability ability : list) {
/*      */       
/*  545 */       if (ability == null || (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility && !ability.isContinuous())) {
/*      */         continue;
/*      */       }
/*      */       
/*  549 */       if (ability instanceof IPunchOverlayAbility) {
/*  550 */         overlay = ((IPunchOverlayAbility)ability).getPunchOverlay((LivingEntity)player); continue;
/*      */       } 
/*  552 */       if (ability instanceof IBodyOverlayAbility) {
/*  553 */         overlay = ((IBodyOverlayAbility)ability).getBodyOverlay((LivingEntity)player);
/*      */       }
/*      */     } 
/*      */     
/*  557 */     return overlay;
/*      */   }
/*      */   
/*      */   public static boolean canPlaceBlock(World world, double posX, double posY, double posZ, BlockState toPlace, int flag, BlockProtectionRule rule) {
/*  561 */     BlockPos pos = new BlockPos(posX, posY, posZ);
/*      */     
/*  563 */     if (World.func_189509_E(pos) || !isWithinChallengeArenaBounds(world, pos)) {
/*  564 */       return false;
/*      */     }
/*      */     
/*  567 */     BlockState currentBlockState = world.func_180495_p(pos);
/*      */     
/*  569 */     ProtectedAreasData worldData = ProtectedAreasData.get(world);
/*  570 */     ProtectedArea area = worldData.getProtectedArea((int)posX, (int)posY, (int)posZ);
/*      */     
/*  572 */     boolean isGriefDisabled = !CommonConfig.INSTANCE.isAbilityGriefingEnabled();
/*  573 */     boolean isGriefBypass = true;
/*  574 */     if (rule != null) {
/*  575 */       isGriefBypass = rule.getBypassGriefRule();
/*      */     }
/*      */     
/*  578 */     if (!isGriefBypass && 
/*  579 */       isGriefDisabled) {
/*  580 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  590 */     if (rule == null || rule.check(world, pos, currentBlockState)) {
/*  591 */       return true;
/*      */     }
/*      */     
/*  594 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean canMobGrief(@Nullable Entity entity) {
/*  598 */     if (entity == null) {
/*  599 */       return true;
/*      */     }
/*      */     
/*  602 */     if (entity instanceof net.minecraft.entity.MobEntity) {
/*  603 */       if (WyHelper.isInChallengeDimension(entity.field_70170_p)) {
/*  604 */         return true;
/*      */       }
/*      */       
/*  607 */       if (ForgeEventFactory.getMobGriefingEvent(entity.field_70170_p, entity)) {
/*  608 */         return true;
/*      */       }
/*      */       
/*  611 */       return false;
/*      */     } 
/*  613 */     if (!(entity instanceof PlayerEntity)) {
/*  614 */       return ForgeEventFactory.getMobGriefingEvent(entity.field_70170_p, entity);
/*      */     }
/*      */     
/*  617 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean placeBlockIfAllowed(LivingEntity entity, BlockPos pos, BlockState toPlace, @Nullable BlockProtectionRule rule) {
/*  622 */     return placeBlockIfAllowed(entity, pos, toPlace, 2, rule);
/*      */   }
/*      */   
/*      */   public static boolean placeBlockIfAllowed(LivingEntity entity, BlockPos pos, BlockState toPlace, int flag, @Nullable BlockProtectionRule rule) {
/*  626 */     if (entity == null) {
/*  627 */       return false;
/*      */     }
/*      */     
/*  630 */     if (!canMobGrief((Entity)entity)) {
/*  631 */       return false;
/*      */     }
/*      */     
/*  634 */     return placeBlockIfAllowed(entity.field_70170_p, pos, toPlace, flag, rule);
/*      */   }
/*      */   
/*      */   public static boolean placeBlockIfAllowed(World world, BlockPos pos, Block toPlace, int flag, BlockProtectionRule rule) {
/*  638 */     return placeBlockIfAllowed(world, pos, toPlace.func_176223_P(), flag, rule);
/*      */   }
/*      */   
/*      */   public static boolean placeBlockIfAllowed(World world, BlockPos pos, Block toPlace, BlockProtectionRule rule) {
/*  642 */     return placeBlockIfAllowed(world, pos, toPlace.func_176223_P(), 2, rule);
/*      */   }
/*      */   
/*      */   public static boolean placeBlockIfAllowed(World world, BlockPos pos, BlockState toPlace, int flag, @Nullable BlockProtectionRule rule) {
/*  646 */     if (World.func_189509_E(pos)) {
/*  647 */       return false;
/*      */     }
/*      */     
/*  650 */     if (!toPlace.func_196958_f() && 
/*  651 */       !isWithinChallengeArenaBounds(world, pos)) {
/*  652 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  656 */     BlockState currentBlockState = world.func_180495_p(pos);
/*      */     
/*  658 */     ProtectedAreasData worldData = ProtectedAreasData.get(world);
/*      */     
/*  660 */     if (worldData == null) {
/*  661 */       return false;
/*      */     }
/*      */     
/*  664 */     ProtectedArea area = worldData.getProtectedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*      */     
/*  666 */     boolean isGriefDisabled = !CommonConfig.INSTANCE.isAbilityGriefingEnabled();
/*  667 */     boolean isGriefBypass = false;
/*  668 */     boolean canPlace = true;
/*  669 */     boolean canActuallyPlace = true;
/*  670 */     if (rule != null) {
/*  671 */       isGriefBypass = rule.getBypassGriefRule();
/*  672 */       canPlace = rule.check(world, pos, currentBlockState);
/*  673 */       canActuallyPlace = !rule.getCoordsOnly();
/*      */     } else {
/*      */       
/*  676 */       canPlace = !RestrictedBlockProtectionRule.INSTANCE.check(world, pos, currentBlockState);
/*      */     } 
/*      */     
/*  679 */     if (WyHelper.isInChallengeDimension(world)) {
/*  680 */       isGriefBypass = true;
/*  681 */       isGriefDisabled = false;
/*      */     } 
/*      */     
/*  684 */     if (!isGriefBypass) {
/*  685 */       if (isGriefDisabled) {
/*  686 */         return false;
/*      */       }
/*      */       
/*  689 */       if (area != null) {
/*  690 */         if (!area.canDestroyBlocks())
/*  691 */           return false; 
/*  692 */         if (area.canDestroyBlocks() && area.canRestoreBlocks()) {
/*  693 */           BlockPlacingHelper.DistanceBlockPos pos2 = new BlockPlacingHelper.DistanceBlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*  694 */           BlockSnapshot snapshot = BlockSnapshot.create(world.func_234923_W_(), (IWorld)world, pos, 2);
/*      */           
/*  696 */           area.queueForRestoration(pos2, new ProtectedArea.RestorationEntry(world.func_82737_E(), snapshot));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  701 */     if (canPlace) {
/*  702 */       if (canActuallyPlace)
/*      */       {
/*  704 */         WyHelper.setBlockStateInChunk(world, pos, toPlace, flag);
/*      */       }
/*  706 */       return true;
/*      */     } 
/*      */     
/*  709 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWithinChallengeArenaBounds(World world, BlockPos pos) {
/*  714 */     if (!WyHelper.isInChallengeDimension(world)) {
/*  715 */       return true;
/*      */     }
/*      */     
/*  718 */     if (world.field_72995_K) {
/*  719 */       return false;
/*      */     }
/*      */     
/*  722 */     ServerWorld sworld = (ServerWorld)world;
/*  723 */     InProgressChallenge ch = ChallengesWorldData.get().getInProgressChallengeFor(sworld);
/*      */ 
/*      */     
/*  726 */     if (ch == null) {
/*  727 */       return true;
/*      */     }
/*      */     
/*  730 */     MutableBoundingBox mbb = ch.getArena().getArenaLimits();
/*  731 */     mbb.field_78897_a -= 4;
/*  732 */     mbb.field_78895_b -= 4;
/*  733 */     mbb.field_78896_c -= 4;
/*  734 */     mbb.field_78893_d += 3;
/*  735 */     mbb.field_78894_e += 3;
/*  736 */     mbb.field_78892_f += 3;
/*  737 */     mbb.func_236989_a_((Vector3i)ch.getArenaPos());
/*      */     
/*  739 */     if (mbb.func_175898_b((Vector3i)pos)) {
/*  740 */       return true;
/*      */     }
/*      */     
/*  743 */     return false;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createEmptyCube(Entity entity, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
/*  747 */     return createEmptyCube(entity, posX, posY, posZ, sizeX, sizeY, sizeZ, 2, blockToPlace, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createEmptyCube(Entity entity, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int flags, Block blockToPlace, BlockProtectionRule rule) {
/*  751 */     if (!canMobGrief(entity)) {
/*  752 */       return new ArrayList<>();
/*      */     }
/*      */     
/*  755 */     return createEmptyCube(entity.field_70170_p, posX, posY, posZ, sizeX, sizeY, sizeZ, flags, blockToPlace, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
/*  759 */     return createEmptyCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, 2, blockToPlace, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int flags, Block blockToPlace, BlockProtectionRule rule) {
/*  763 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  764 */     List<BlockPos> blockPositions = new ArrayList<>();
/*  765 */     for (int x = -sizeX; x <= sizeX; x++) {
/*  766 */       for (int y = -sizeY; y <= sizeY; y++) {
/*  767 */         for (int z = -sizeZ; z <= sizeZ; z++) {
/*  768 */           if (x == -sizeX || x == sizeX || y == -sizeY || y == sizeY || z == -sizeZ || z == sizeZ) {
/*  769 */             mutpos.func_189532_c(posX + x, posY + y, posZ + z);
/*  770 */             if (placeBlockIfAllowed(world, (BlockPos)mutpos, blockToPlace, flags, rule)) {
/*  771 */               blockPositions.add(mutpos.func_185334_h());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  777 */     return blockPositions;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createFilledCube(Entity entity, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
/*  781 */     return createFilledCube(entity, posX, posY, posZ, sizeX, sizeY, sizeZ, blockToPlace, 2, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createFilledCube(Entity entity, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, int flags, BlockProtectionRule rule) {
/*  785 */     if (!canMobGrief(entity)) {
/*  786 */       return new ArrayList<>();
/*      */     }
/*      */     
/*  789 */     return createFilledCube(entity.field_70170_p, posX, posY, posZ, sizeX, sizeY, sizeZ, blockToPlace, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
/*  793 */     return createFilledCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, blockToPlace, 2, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, int flag, BlockProtectionRule rule) {
/*  797 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  798 */     List<BlockPos> blockPositions = new ArrayList<>();
/*  799 */     for (int x = -sizeX; x <= sizeX; x++) {
/*  800 */       for (int y = -sizeY; y <= sizeY; y++) {
/*  801 */         for (int z = -sizeZ; z <= sizeZ; z++) {
/*  802 */           mutpos.func_189532_c(posX + x, posY + y, posZ + z);
/*  803 */           if (placeBlockIfAllowed(world, (BlockPos)mutpos, blockToPlace, flag, rule)) {
/*  804 */             blockPositions.add(mutpos.func_185334_h());
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  810 */     return blockPositions;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphereWithProtection(Entity entity, BlockPos center, int radiusXZ, int radiusY, Block block, int flags, BlockProtectionRule rule) {
/*  814 */     if (!canMobGrief(entity)) {
/*  815 */       return new ArrayList<>();
/*      */     }
/*      */     
/*  818 */     return createSphereWithProtection(entity.field_70170_p, center, radiusXZ, radiusY, block, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphereWithProtection(World world, BlockPos center, int radiusXZ, int radiusY, Block block, int flags, BlockProtectionRule rule) {
/*  822 */     int x0 = center.func_177958_n();
/*  823 */     int y0 = center.func_177956_o();
/*  824 */     int z0 = center.func_177952_p();
/*      */     
/*  826 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  827 */     List<BlockPos> blockPositions = new ArrayList<>();
/*  828 */     for (int y = y0 - radiusY; y <= y0 + radiusY; y++) {
/*  829 */       for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++) {
/*  830 */         for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++) {
/*  831 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*      */           
/*  833 */           if (distance < (radiusXZ * radiusY)) {
/*  834 */             mutpos.func_181079_c(x, y, z);
/*  835 */             int posDifference = center.func_177956_o() - mutpos.func_177956_o();
/*  836 */             boolean fallingProtection = (Math.sqrt(mutpos.func_177951_i((Vector3i)center.func_177979_c(posDifference))) > 2.5D);
/*  837 */             if (fallingProtection && placeBlockIfAllowed(world, (BlockPos)mutpos, block, flags, rule)) {
/*  838 */               blockPositions.add(mutpos.func_185334_h());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  845 */     return blockPositions;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphere(Entity entity, BlockPos center, int radiusXZ, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
/*  849 */     return createSphere(entity, center, radiusXZ, radiusXZ, hollow, block, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphere(Entity entity, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
/*  853 */     return createSphere(entity, center, radiusXZ, radiusY, hollow, block, (BlockProtectionRule.IReplaceBlockRule)null, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphere(Entity entity, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, @Nullable BlockProtectionRule.IReplaceBlockRule replaceTest, int flags, BlockProtectionRule rule) {
/*  857 */     if (!canMobGrief(entity)) {
/*  858 */       return new ArrayList<>();
/*      */     }
/*      */     
/*  861 */     return createSphere(entity.field_70170_p, center, radiusXZ, radiusY, hollow, block, replaceTest, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
/*  865 */     return createSphere(world, center, radiusXZ, radiusXZ, hollow, block, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
/*  869 */     return createSphere(world, center, radiusXZ, radiusY, hollow, block, (BlockProtectionRule.IReplaceBlockRule)null, flags, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, @Nullable BlockProtectionRule.IReplaceBlockRule replaceTest, int flags, BlockProtectionRule rule) {
/*  873 */     int x0 = center.func_177958_n();
/*  874 */     int y0 = center.func_177956_o();
/*  875 */     int z0 = center.func_177952_p();
/*      */     
/*  877 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  878 */     List<BlockPos> blockPositions = new ArrayList<>();
/*  879 */     for (int y = y0 - radiusY; y <= y0 + radiusY; y++) {
/*  880 */       for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++) {
/*  881 */         for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++) {
/*  882 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*      */           
/*  884 */           if (distance < (radiusXZ * radiusY) && (!hollow || distance >= ((radiusXZ - 1) * (radiusXZ - 1)))) {
/*  885 */             mutpos.func_181079_c(x, y, z);
/*  886 */             BlockState state = world.func_180495_p((BlockPos)mutpos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  895 */             if (replaceTest == null || replaceTest.replace(world, (BlockPos)mutpos, state))
/*      */             {
/*      */ 
/*      */               
/*  899 */               if (placeBlockIfAllowed(world, (BlockPos)mutpos, block, flags, rule)) {
/*  900 */                 blockPositions.add(mutpos.func_185334_h());
/*      */               }
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  907 */     return blockPositions;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static List<BlockPos> createEmptySphere(World world, int posX, int posY, int posZ, int size, Block block, BlockProtectionRule rule) {
/*  913 */     return createSphere(world, new BlockPos(posX, posY, posZ), size, true, block, 2, rule);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static List<BlockPos> createFilledSphere(World world, int posX, int posY, int posZ, int size, Block block, BlockProtectionRule rule) {
/*  919 */     return createSphere(world, new BlockPos(posX, posY, posZ), size, false, block, 2, rule);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> getFilledSpherePositions(World world, double posX, double posY, double posZ, int radiusXZ, int radiusY, BlockState state, int flag, BlockProtectionRule rule) {
/*  923 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  924 */     List<BlockPos> blockPositions = new ArrayList<>(); double y;
/*  925 */     for (y = posY - radiusY; y <= posY + radiusY; y++) {
/*  926 */       double x; for (x = posX - radiusXZ; x <= posX + radiusXZ; x++) {
/*  927 */         double z; for (z = posZ - radiusXZ; z <= posZ + radiusXZ; z++) {
/*  928 */           double distance = (posX - x) * (posX - x) + (posZ - z) * (posZ - z) + (posY - y) * (posY - y);
/*  929 */           if (distance < (radiusXZ * radiusY)) {
/*  930 */             mutpos.func_189532_c(x, y, z);
/*  931 */             if (canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, flag, rule)) {
/*  932 */               blockPositions.add(mutpos.func_185334_h());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  939 */     return blockPositions;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> getFilledCubePositions(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, BlockState state, int flag, BlockProtectionRule rule) {
/*  943 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  944 */     List<BlockPos> blockPositions = new ArrayList<>();
/*  945 */     for (int y = -sizeY; y <= sizeY; y++) {
/*  946 */       for (int x = -sizeX; x <= sizeX; x++) {
/*  947 */         for (int z = -sizeZ; z <= sizeZ; z++) {
/*  948 */           mutpos.func_189532_c(posX + x, posY + y, posZ + z);
/*  949 */           if (canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, flag, rule)) {
/*  950 */             blockPositions.add(mutpos.func_185334_h());
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  956 */     return blockPositions;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ExplosionAbility newExplosion(Entity entity, World world, double posX, double posY, double posZ, float size) {
/*  961 */     return new ExplosionAbility(entity, world, posX, posY, posZ, size);
/*      */   }
/*      */   
/*      */   public static boolean canUseBrawlerAbilities(LivingEntity entity) {
/*  965 */     return entity.func_184614_ca().func_190926_b();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canUseSwordsmanAbilities(LivingEntity entity) {
/*  970 */     boolean hasSwordInHand = ItemsHelper.isSword(entity.func_184614_ca());
/*  971 */     if (entity instanceof PlayerEntity) {
/*      */       
/*  973 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*  974 */       Ability sparClawAbility = (Ability)abilityProps.getEquippedAbility(SparClawAbility.INSTANCE);
/*      */       
/*  976 */       boolean hasSparClaw = (sparClawAbility != null && sparClawAbility.isContinuous());
/*      */       
/*  978 */       return (hasSwordInHand || hasSparClaw);
/*      */     } 
/*      */ 
/*      */     
/*  982 */     return hasSwordInHand;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAffectedByWater(LivingEntity entity) {
/*  987 */     return isAffectedByWater(entity, 0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAffectedByWater(LivingEntity entity, float waterLevel) {
/*  994 */     if (entity.func_175149_v()) {
/*  995 */       return false;
/*      */     }
/*      */     
/*  998 */     if (entity.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/*  999 */       return false;
/*      */     }
/*      */     
/* 1002 */     boolean isUnderDaSea = entity.func_208600_a((ITag)FluidTags.field_206959_a);
/* 1003 */     if (isUnderDaSea) {
/* 1004 */       return true;
/*      */     }
/*      */     
/* 1007 */     if (entity.func_184218_aH()) {
/* 1008 */       return false;
/*      */     }
/*      */     
/* 1011 */     FluidState fluidState = entity.field_70170_p.func_204610_c(entity.func_233580_cy_());
/*      */     
/* 1013 */     float entityHeight = entity.func_213302_cg();
/* 1014 */     int waterLevelCheck = Math.max(1, Math.round(entityHeight * waterLevel));
/*      */     
/* 1016 */     boolean isInWater = fluidState.func_206884_a((ITag)FluidTags.field_206959_a);
/* 1017 */     if (isInWater) {
/* 1018 */       boolean hasWaterUnder = false;
/* 1019 */       BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 1020 */       for (int i = 0; i <= waterLevelCheck; i++) {
/* 1021 */         mutpos.func_189533_g((Vector3i)entity.func_233580_cy_().func_177979_c(i));
/* 1022 */         boolean isWater = entity.field_70170_p.func_204610_c((BlockPos)mutpos).func_206884_a((ITag)FluidTags.field_206959_a);
/* 1023 */         if (!isWater) {
/* 1024 */           hasWaterUnder = false;
/*      */           break;
/*      */         } 
/* 1027 */         hasWaterUnder = true;
/*      */       } 
/*      */       
/* 1030 */       if (hasWaterUnder) {
/* 1031 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1035 */     if (fluidState.func_223408_f() < 0.8F && !fluidState.func_206889_d()) {
/* 1036 */       return false;
/*      */     }
/*      */     
/* 1039 */     float waterLevelHeight = entityHeight * waterLevel;
/*      */     
/* 1041 */     if (entity.func_233571_b_((ITag)FluidTags.field_206959_a) >= waterLevelHeight) {
/* 1042 */       return true;
/*      */     }
/*      */     
/* 1045 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isKairosekiNearby(LivingEntity entity) {
/* 1049 */     if (entity.func_175149_v()) {
/* 1050 */       return false;
/*      */     }
/*      */     
/* 1053 */     if (entity.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/* 1054 */       return false;
/*      */     }
/*      */     
/* 1057 */     return (WyHelper.isBlockNearby((Entity)entity, 1, state -> state.func_177230_c().func_203417_a((ITag)ModTags.Blocks.KAIROSEKI)) || ItemsHelper.hasKairosekiItem(entity));
/*      */   }
/*      */   
/*      */   public static boolean isWeakenedByKairosekiOrWater(LivingEntity entity) {
/* 1061 */     return (isKairosekiNearby(entity) || isAffectedByWater(entity));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean verifyIfAbilityIsBanned(AbilityCore core) {
/* 1066 */     return CommonConfig.INSTANCE.getBannedAbilities().stream().anyMatch(abl -> abl.equals(core));
/*      */   }
/*      */ 
/*      */   
/*      */   public static Predicate<IAbility> getAbilityFromCategoryPredicate(LivingEntity entity, AbilityCategory category) {
/* 1071 */     return ability -> AbilityDataCapability.get(entity).getUnlockTypeForAbility(ability.getCore()).equals(AbilityUnlock.COMMAND) ? false : (ability.getCore().getCategory().equals(category));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Predicate<AbilityCore> getAbilityCoreFromCategoryPredicate(LivingEntity entity, AbilityCategory category) {
/* 1086 */     return core -> AbilityDataCapability.get(entity).getUnlockTypeForAbility(core).equals(AbilityUnlock.COMMAND) ? false : (core.getCategory().equals(category));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isInCreativeOrSpectator(@Nullable LivingEntity entity) {
/* 1100 */     if (entity == null) {
/* 1101 */       return false;
/*      */     }
/* 1103 */     if (entity.func_175149_v()) {
/* 1104 */       return true;
/*      */     }
/* 1106 */     if (entity instanceof PlayerEntity && ((PlayerEntity)entity).func_184812_l_()) {
/* 1107 */       return true;
/*      */     }
/* 1109 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static boolean isTargetBlocking(LivingEntity attacker, LivingEntity target) {
/* 1117 */     if (isLogiaBlocking((Entity)attacker, target)) {
/* 1118 */       return true;
/*      */     }
/*      */     
/* 1121 */     if (isShieldBlocking(target)) {
/* 1122 */       return true;
/*      */     }
/*      */     
/* 1125 */     if (isGuardBlocking(target)) {
/* 1126 */       return true;
/*      */     }
/*      */     
/* 1129 */     if (isDodging(target)) {
/* 1130 */       return true;
/*      */     }
/*      */     
/* 1133 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isHakiBlocking(LivingEntity attacker, LivingEntity target) {
/* 1137 */     float attackerHakiExp = HakiDataCapability.get(attacker).getTotalHakiExp();
/* 1138 */     float targetHakiExp = HakiDataCapability.get(target).getTotalHakiExp();
/*      */     
/* 1140 */     if (targetHakiExp < 5.0F) {
/* 1141 */       return false;
/*      */     }
/* 1143 */     float diff = attackerHakiExp - targetHakiExp;
/* 1144 */     if (diff < -5.0F) {
/* 1145 */       return true;
/*      */     }
/* 1147 */     if (diff > 5.0F) {
/* 1148 */       return false;
/*      */     }
/* 1150 */     float clampedDiff = MathHelper.func_76131_a(diff, 0.0F, 5.0F);
/* 1151 */     float mod = clampedDiff / 400.0F * -1.0F;
/* 1152 */     float chance = 0.95F + mod;
/* 1153 */     float rand = target.func_70681_au().nextFloat();
/* 1154 */     if (rand < chance) {
/* 1155 */       attacker.field_70170_p.func_184133_a(null, attacker.func_233580_cy_(), (SoundEvent)ModSounds.HAKI_GUARD.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 1156 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HAKI_GUARD.get(), (Entity)target, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/* 1157 */       return true;
/*      */     } 
/* 1159 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isLogiaBlocking(Entity attacker, LivingEntity target) {
/* 1163 */     EffectInstance inst = target.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/* 1164 */     if (inst != null && inst.func_76458_c() > 0) {
/* 1165 */       return false;
/*      */     }
/*      */     
/* 1168 */     if (target.func_70644_a((Effect)ModEffects.KAIROSEKI_WEAKNESS.get()) || target.func_70644_a((Effect)ModEffects.WATER_WEAKNESS.get())) {
/* 1169 */       return false;
/*      */     }
/*      */     
/* 1172 */     boolean hasHardeningActive = false;
/* 1173 */     boolean hasKairosekiEffect = false;
/*      */     
/* 1175 */     if (attacker instanceof LivingEntity) {
/* 1176 */       LivingEntity livingAttacker = (LivingEntity)attacker;
/*      */       
/* 1178 */       hasHardeningActive = HakiHelper.hasHardeningActive(livingAttacker);
/*      */       
/* 1180 */       for (ItemStack stack : livingAttacker.func_184214_aD()) {
/* 1181 */         if (stack.func_190926_b()) {
/*      */           continue;
/*      */         }
/*      */         
/* 1185 */         if (ItemsHelper.isKairosekiWeapon(stack)) {
/* 1186 */           hasKairosekiEffect = true;
/*      */           
/*      */           break;
/*      */         } 
/* 1190 */         if (ModTags.Items.KAIROSEKI.func_230235_a_(stack.func_77973_b())) {
/* 1191 */           hasKairosekiEffect = true;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1197 */     } else if (ModTags.Entities.KAIROSEKI.func_230235_a_(attacker.func_200600_R())) {
/* 1198 */       hasKairosekiEffect = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1210 */     Optional<LogiaInvulnerabilityAbility> logiaAbility = AbilityDataCapability.get(target).getPassiveAbilities(AbilityCategory.DEVIL_FRUITS.isAbilityPartofCategory()).stream().filter(LogiaInvulnerabilityAbility.class::isInstance).limit(1L).map(LogiaInvulnerabilityAbility.class::cast).filter(a -> !a.isPaused()).findFirst();
/* 1211 */     boolean hasLogiaDefence = (logiaAbility.isPresent() && !hasHardeningActive && !hasKairosekiEffect);
/* 1212 */     if (hasLogiaDefence) {
/* 1213 */       ((LogiaInvulnerabilityAbility)logiaAbility.get()).spawnParticles(target);
/* 1214 */       return true;
/*      */     } 
/*      */     
/* 1217 */     return false;
/*      */   }
/*      */   
/*      */   public static void setDeltaMovement(Entity entity, double x, double y, double z) {
/* 1221 */     setDeltaMovement(entity, new Vector3d(x, y, z), false);
/*      */   }
/*      */   
/*      */   public static void setDeltaMovement(Entity entity, Vector3d velocity) {
/* 1225 */     setDeltaMovement(entity, velocity, false);
/*      */   }
/*      */   
/*      */   public static void setDeltaMovement(Entity entity, double x, double y, double z, boolean isPrecise) {
/* 1229 */     setDeltaMovement(entity, new Vector3d(x, y, z), isPrecise);
/*      */   }
/*      */   
/*      */   public static void setDeltaMovement(Entity entity, Vector3d velocity, boolean isPrecise) {
/* 1233 */     entity.func_213317_d(velocity);
/*      */     
/* 1235 */     if (velocity.func_82617_b() > 0.0D) {
/* 1236 */       entity.func_230245_c_(false);
/*      */     }
/*      */     
/* 1239 */     if (entity instanceof ServerPlayerEntity) {
/* 1240 */       ServerPlayerEntity player = (ServerPlayerEntity)entity;
/*      */       
/* 1242 */       Vector3d position = player.func_213303_ch();
/*      */       
/* 1244 */       player.field_70169_q = position.field_72450_a;
/* 1245 */       player.field_70167_r = position.field_72448_b;
/* 1246 */       player.field_70166_s = position.field_72449_c;
/* 1247 */       player.field_70142_S = position.field_72450_a;
/* 1248 */       player.field_70137_T = position.field_72448_b;
/* 1249 */       player.field_70136_U = position.field_72449_c;
/*      */       
/* 1251 */       if (isPrecise) {
/* 1252 */         entity.func_213315_a(MoverType.SELF, velocity);
/*      */       }
/*      */       
/* 1255 */       player.field_71135_a.func_147359_a((IPacket)new SEntityVelocityPacket((Entity)player));
/* 1256 */     } else if (!entity.func_184207_aI()) {
/* 1257 */       entity.field_70133_I = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isShieldBlocking(LivingEntity target) {
/* 1262 */     EffectInstance inst = target.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/* 1263 */     if (inst != null && inst.func_76458_c() > 0) {
/* 1264 */       return false;
/*      */     }
/* 1266 */     boolean hasShield = target.func_184585_cz();
/* 1267 */     if (hasShield) {
/* 1268 */       target.func_184185_a(SoundEvents.field_187767_eL, 1.0F, 0.8F + target.field_70170_p.field_73012_v.nextFloat() * 0.4F);
/* 1269 */       return true;
/*      */     } 
/* 1271 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isGuardBlocking(LivingEntity target) {
/* 1275 */     EffectInstance inst = target.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/* 1276 */     if (inst != null && inst.func_76458_c() > 0) {
/* 1277 */       return false;
/*      */     }
/*      */     
/* 1280 */     boolean isGuarding = target.func_70651_bq().stream().anyMatch(instance -> instance.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect);
/* 1281 */     if (isGuarding) {
/* 1282 */       target.field_70170_p.func_184133_a(null, target.func_233580_cy_(), (SoundEvent)ModSounds.GUARD.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 1283 */       (new GuardParticleEffect()).spawn(target.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/* 1284 */       return true;
/*      */     } 
/* 1286 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isGrabbing(LivingEntity entity) {
/* 1290 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*      */     
/* 1292 */     if (abilityDataProps == null) {
/* 1293 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1299 */     boolean isGrabbingOrPulling = (abilityDataProps.getEquippedAndPassiveAbilities(abl -> abl.hasComponent(ModAbilityKeys.GRAB)).stream().map(abl -> (GrabEntityComponent)abl.getComponent(ModAbilityKeys.GRAB).get()).filter(comp -> (comp.getState() == GrabEntityComponent.GrabState.PULLING || comp.getState() == GrabEntityComponent.GrabState.GRABBED)).count() > 0L);
/*      */     
/* 1301 */     return isGrabbingOrPulling;
/*      */   }
/*      */   
/*      */   public static boolean isDodging(LivingEntity entity) {
/* 1305 */     EffectInstance inst = entity.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/*      */     
/* 1307 */     if (inst != null && inst.func_76458_c() > 0) {
/* 1308 */       return false;
/*      */     }
/*      */     
/* 1311 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*      */     
/* 1313 */     if (abilityDataProps == null) {
/* 1314 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1318 */     boolean isDodgingAbilityActive = (getEquippedAbilitiesPool(entity, AbilityPool.DODGE_ABILITY).stream().anyMatch(ability -> ability.isContinuous()) || getEquippedAbilitiesPool(entity, ModAbilityPools.DODGE_ABILITY).stream().anyMatch(ability -> ability.isContinuous()));
/*      */     
/* 1320 */     return isDodgingAbilityActive;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static List<Ability> getEquippedAbilitiesPool(LivingEntity entity, AbilityPool pool) {
/* 1325 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*      */     
/* 1327 */     return (List<Ability>)abilityProps.getEquippedAndPassiveAbilities().stream()
/* 1328 */       .filter(ability -> (ability instanceof Ability && ((Ability)ability).isInPool(pool)))
/* 1329 */       .map(ability -> (Ability)ability)
/* 1330 */       .collect(Collectors.toList());
/*      */   }
/*      */   
/*      */   public static List<Ability> getEquippedAbilitiesPool(LivingEntity entity, AbilityPool2 pool) {
/* 1334 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*      */     
/* 1336 */     return (List<Ability>)abilityProps.getEquippedAndPassiveAbilities().stream()
/* 1337 */       .filter(ability -> (ability.hasComponent(ModAbilityKeys.POOL) && ((PoolComponent)ability.getComponent(ModAbilityKeys.POOL).get()).containsPool(pool)))
/* 1338 */       .map(ability -> (Ability)ability)
/* 1339 */       .collect(Collectors.toList());
/*      */   }
/*      */   
/*      */   public static void addFrostbiteStacks(LivingEntity target, @Nullable LivingEntity attacker, int stacks) {
/* 1343 */     if (target == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1347 */     if (stacks <= 0) {
/* 1348 */       stacks = 1;
/*      */     }
/*      */     
/* 1351 */     EffectInstance frostbite = target.func_70660_b((Effect)ModEffects.FROSTBITE.get());
/*      */     
/* 1353 */     if (frostbite == null) {
/*      */       
/* 1355 */       frostbite = new EffectInstance((Effect)ModEffects.FROSTBITE.get(), 20 * stacks, stacks);
/* 1356 */       target.func_195064_c(frostbite);
/*      */     } else {
/*      */       
/* 1359 */       ((EffectInstanceMixin)frostbite).setDuration(frostbite.func_76459_b() + 20 * stacks);
/* 1360 */       ((EffectInstanceMixin)frostbite).setAmplifier(frostbite.func_76458_c() + stacks);
/*      */     } 
/*      */     
/* 1363 */     if (target.func_184102_h() != null) {
/* 1364 */       target.func_184102_h().func_184103_al().func_148543_a(null, (target.func_213303_ch()).field_72450_a, (target.func_213303_ch()).field_72448_b, (target.func_213303_ch()).field_72449_c, 100.0D, target.func_130014_f_().func_234923_W_(), (IPacket)new SPlayEntityEffectPacket(target.func_145782_y(), frostbite));
/*      */     }
/*      */   }
/*      */   
/*      */   public static void reduceEffect(@Nullable EffectInstance effect, double reduction) {
/* 1369 */     if (effect == null) {
/*      */       return;
/*      */     }
/*      */     
/*      */     try {
/* 1374 */       double duration = effect.func_76459_b() / reduction;
/*      */       
/* 1376 */       ((EffectInstanceMixin)effect).setDuration((int)duration);
/* 1377 */     } catch (Exception e) {
/* 1378 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void slowEntityFall(LivingEntity player) {
/* 1384 */     slowEntityFall(player, 5);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void slowEntityFall(LivingEntity player, int duration) {
/* 1389 */     player.func_195064_c(new EffectInstance((Effect)ModEffects.REDUCED_FALL.get(), duration, 0));
/*      */   }
/*      */   
/*      */   public static void resetSpawnInvulnerability(ServerPlayerEntity player) {
/* 1393 */     Field f = ObfuscationReflectionHelper.findField(ServerPlayerEntity.class, "field_147101_bU");
/*      */     try {
/* 1395 */       f.setInt(player, 0);
/*      */     }
/* 1397 */     catch (IllegalAccessException e) {
/* 1398 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isJumping(LivingEntity entity) {
/* 1404 */     Field f = ObfuscationReflectionHelper.findField(LivingEntity.class, "field_70703_bu");
/*      */     
/*      */     try {
/* 1407 */       return f.getBoolean(entity);
/*      */     }
/* 1409 */     catch (IllegalAccessException e) {
/*      */       
/* 1411 */       e.printStackTrace();
/*      */       
/* 1413 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setPose(LivingEntity entity, Pose pose) {
/* 1418 */     Method method = ObfuscationReflectionHelper.findMethod(Entity.class, "func_213301_b", new Class[] { Pose.class });
/*      */     
/*      */     try {
/* 1421 */       method.setAccessible(true);
/* 1422 */       method.invoke(entity, new Object[] { pose });
/*      */     }
/* 1424 */     catch (Exception e) {
/*      */       
/* 1426 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static LivingEntity canGrab(LivingEntity entity, boolean sendMessage) {
/* 1432 */     double reach = Math.sqrt(AttributeHelper.getSquaredAttackRangeDistance(entity, 3.0D));
/*      */     
/* 1434 */     return canGrab(entity, (float)reach, 0.4F, sendMessage);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static LivingEntity canGrab(LivingEntity entity, float distance, float size, boolean sendMessage) {
/* 1439 */     Optional<LivingEntity> opt = TargetHelper.<LivingEntity>getEntitiesInLine(entity, distance, size, null, (Class<? extends LivingEntity>[])new Class[] { LivingEntity.class }).stream().findFirst();
/*      */     
/* 1441 */     if (opt.isPresent()) {
/* 1442 */       LivingEntity target = opt.get();
/*      */       
/* 1444 */       KenbunshokuHakiFutureSightAbility futureSightAbility = (KenbunshokuHakiFutureSightAbility)AbilityDataCapability.get(target).getEquippedAbility(KenbunshokuHakiFutureSightAbility.INSTANCE);
/*      */       
/* 1446 */       if (target instanceof PlayerEntity && futureSightAbility != null && futureSightAbility.isContinuous()) {
/* 1447 */         futureSightAbility.reduceProtection(target, 1.0F);
/*      */         
/* 1449 */         return null;
/*      */       } 
/*      */       
/* 1452 */       if (target != null && target.func_70089_S() && !isTargetBlocking(entity, target)) {
/* 1453 */         return target;
/*      */       }
/*      */     }
/* 1456 */     else if (sendMessage && !entity.field_70170_p.field_72995_K) {
/* 1457 */       entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET), Util.field_240973_b_);
/*      */     } 
/*      */     
/* 1460 */     return null;
/*      */   }
/*      */   
/*      */   public static boolean canCarry(LivingEntity entity, LivingEntity target) {
/* 1464 */     if (entity == null || target == null) {
/* 1465 */       return false;
/*      */     }
/*      */     
/* 1468 */     for (EffectInstance effectInstance : entity.func_70651_bq()) {
/* 1469 */       Effect effect = effectInstance.func_188419_a();
/*      */       
/* 1471 */       if (effect instanceof IBindHandsEffect && ((IBindHandsEffect)effect).isBlockingSwings()) {
/* 1472 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1476 */     Optional<OutOfBodyAbility> oobAbility = OutOfBodyAbilitiesEvents.getOOBAbility(entity);
/* 1477 */     if (oobAbility.isPresent()) {
/* 1478 */       return false;
/*      */     }
/*      */     
/* 1481 */     MorphInfo morphInfo = MorphHelper.getZoanInfo(target);
/* 1482 */     if (morphInfo != null && !morphInfo.canMount()) {
/* 1483 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1487 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 1488 */     if (props.isCarrying() && props.getCarry().equals(target)) {
/* 1489 */       if (entity.func_70644_a((Effect)ModEffects.DOA_INVISIBILITY.get())) {
/* 1490 */         return true;
/*      */       
/*      */       }
/*      */     }
/* 1494 */     else if (!target.func_70644_a((Effect)ModEffects.DOA_INVISIBILITY.get()) && entity.func_70644_a((Effect)ModEffects.DOA_INVISIBILITY.get())) {
/* 1495 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1499 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(target);
/* 1500 */     IEntityStats entityStatsProps = EntityStatsCapability.get(target);
/*      */     
/* 1502 */     for (EffectInstance effectInstance : target.func_70651_bq()) {
/* 1503 */       Effect effect = effectInstance.func_188419_a();
/*      */ 
/*      */       
/* 1506 */       if ((ModEntityPredicates.getFriendlyFactions(entity).test(target) || entityStatsProps.isRogue()) && devilFruitProps.hasAnyDevilFruit() && effect == ModEffects.WATER_WEAKNESS.get()) {
/* 1507 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 1511 */       if (effect instanceof xyz.pixelatedw.mineminenomi.effects.CaughtInNetEffect) {
/* 1512 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1516 */     EffectInstance inst = target.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/*      */     
/* 1518 */     if (inst != null && inst.func_76458_c() > 0) {
/* 1519 */       return true;
/*      */     }
/*      */     
/* 1522 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isCarrying(LivingEntity entity, LivingEntity target) {
/* 1526 */     IEntityStats props = EntityStatsCapability.get(entity);
/*      */     
/* 1528 */     if (!props.isCarrying()) {
/* 1529 */       return false;
/*      */     }
/*      */     
/* 1532 */     if (props.getCarry() == null || props.getCarry() != target) {
/* 1533 */       return false;
/*      */     }
/*      */     
/* 1536 */     if (!canCarry(entity, target)) {
/* 1537 */       return false;
/*      */     }
/*      */     
/* 1540 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static AbilityUseResult canUseNPCAbilities(LivingEntity entity, EntityType<?> type) {
/* 1546 */     if (entity.func_200600_R().equals(type)) {
/* 1547 */       return AbilityUseResult.success();
/*      */     }
/* 1549 */     return AbilityUseResult.fail(null);
/*      */   }
/*      */   
/*      */   public static AbilityUseResult canUseWeaponAbilities(LivingEntity entity, IAbility ability) {
/* 1553 */     if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity) {
/* 1554 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1557 */     if (entity.func_184614_ca().func_190926_b()) {
/* 1558 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MELEE_WEAPON));
/*      */     }
/*      */     
/* 1561 */     ItemStack itemStack = entity.func_184614_ca();
/*      */     
/* 1563 */     boolean hasSwordInHand = ItemsHelper.isSword(itemStack);
/* 1564 */     if (hasSwordInHand) {
/* 1565 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1568 */     if (itemStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem) {
/* 1569 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1572 */     if (itemStack.func_77973_b() instanceof net.minecraft.item.TieredItem) {
/* 1573 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1576 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MELEE_WEAPON));
/*      */   }
/*      */   public static AbilityUseResult canUseMorphAbility(LivingEntity entity, IAbility ability, MorphInfo... morphs) {
/*      */     TranslationTextComponent translationTextComponent;
/* 1580 */     for (MorphInfo morph : morphs) {
/* 1581 */       if (morph.isActive(entity)) {
/* 1582 */         return AbilityUseResult.success();
/*      */       }
/*      */     } 
/*      */     
/* 1586 */     ITextComponent failMessage = null;
/* 1587 */     if (morphs.length == 1) {
/* 1588 */       translationTextComponent = new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { ability.getCore().getLocalizedName(), morphs[0].getDisplayName() });
/*      */     }
/* 1590 */     else if (morphs.length >= 2) {
/* 1591 */       translationTextComponent = new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_DOUBLE, new Object[] { ability.getCore().getLocalizedName(), morphs[0].getDisplayName(), morphs[1].getDisplayName() });
/*      */     } 
/*      */     
/* 1594 */     return AbilityUseResult.fail((ITextComponent)translationTextComponent);
/*      */   }
/*      */   public static AbilityUseResult requireAbilityCheck(LivingEntity entity, IAbility ability, RequireAbilityComponent.CheckData<IAbility>... checks) {
/*      */     TranslationTextComponent translationTextComponent;
/* 1598 */     for (RequireAbilityComponent.CheckData<IAbility> checkData : checks) {
/* 1599 */       if (checkData.checkAbility(entity)) {
/* 1600 */         return AbilityUseResult.success();
/*      */       }
/*      */     } 
/*      */     
/* 1604 */     ITextComponent failMessage = null;
/* 1605 */     if (checks.length == 1) {
/* 1606 */       translationTextComponent = new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { ability.getCore().getLocalizedName(), checks[0].getAbility().getLocalizedName().getString() });
/*      */     }
/* 1608 */     else if (checks.length >= 2) {
/* 1609 */       translationTextComponent = new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_DOUBLE, new Object[] { ability.getCore().getLocalizedName(), checks[0].getAbility().getLocalizedName().getString(), checks[1].getAbility().getLocalizedName().getString() });
/*      */     } 
/*      */     
/* 1612 */     return AbilityUseResult.fail((ITextComponent)translationTextComponent);
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresFocus(LivingEntity entity, IAbility ability) {
/* 1616 */     if (isGrabbing(entity)) {
/* 1617 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_FOCUS));
/*      */     }
/*      */     
/* 1620 */     return AbilityUseResult.success();
/*      */   }
/*      */   
/*      */   public static AbilityUseResult canUseMomentumAbilities(LivingEntity entity, IAbility ability) {
/* 1624 */     ModifiableAttributeInstance attr = entity.func_110148_a((Attribute)ModAttributes.JUMP_HEIGHT.get());
/* 1625 */     if (attr != null && attr.func_111126_e() <= 0.0D) {
/* 1626 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANT_MOVE, new Object[] { ability.getCore().getLocalizedName() }));
/*      */     }
/* 1628 */     attr = entity.func_110148_a(Attributes.field_233821_d_);
/* 1629 */     if (attr != null && attr.func_111126_e() <= 0.0D) {
/* 1630 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANT_MOVE, new Object[] { ability.getCore().getLocalizedName() }));
/*      */     }
/* 1632 */     return AbilityUseResult.success();
/*      */   }
/*      */   
/*      */   public static AbilityUseResult canUseBrawlerAbilities(LivingEntity entity, IAbility ability) {
/* 1636 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 1637 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST));
/*      */     }
/* 1639 */     return AbilityUseResult.success();
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresClimaTact(LivingEntity entity, IAbility ability) {
/* 1643 */     ItemStack stack = entity.func_184614_ca();
/*      */     
/* 1645 */     if (stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
/* 1646 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1649 */     return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT);
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresBluntWeapon(LivingEntity entity, IAbility ability) {
/* 1653 */     boolean hasBluntInHand = ItemsHelper.isBlunt(entity.func_184614_ca());
/*      */     
/* 1655 */     if (hasBluntInHand) {
/* 1656 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1659 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_BLUNT));
/*      */   }
/*      */   
/*      */   public static AbilityUseResult canUseSwordsmanAbilities(LivingEntity entity, IAbility ability) {
/* 1663 */     if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity) {
/* 1664 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1667 */     boolean hasSwordInHand = ItemsHelper.isSword(entity.func_184614_ca());
/*      */     
/* 1669 */     if (hasSwordInHand) {
/* 1670 */       return AbilityUseResult.success();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1678 */     Optional<ContinuousComponent> sparClaw = AbilityDataCapability.getLazy(entity).filter(Objects::nonNull).map(data -> (Ability)data.getEquippedAbility(SparClawAbility.INSTANCE)).map(abl -> abl.getComponent(ModAbilityKeys.CONTINUOUS)).filter(Optional::isPresent).map(Optional::get);
/*      */     
/* 1680 */     boolean hasSparClawActive = (sparClaw.isPresent() && ((ContinuousComponent)sparClaw.get()).isContinuous());
/*      */     
/* 1682 */     if (hasSparClawActive && entity.func_184614_ca().func_190926_b()) {
/* 1683 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1686 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD));
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresMedicBag(LivingEntity entity, IAbility ability) {
/* 1690 */     boolean hasMedicBag = ItemsHelper.hasItemInSlot(entity, EquipmentSlotType.CHEST, (Item)ModArmors.MEDIC_BAG.get());
/*      */     
/* 1692 */     if (hasMedicBag) {
/* 1693 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1696 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { ability.getDisplayName().getString() }));
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresOnGround(LivingEntity entity, IAbility ability) {
/* 1700 */     if (entity.func_233570_aj_()) {
/* 1701 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1704 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { ability.getDisplayName().getString() }));
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresInAir(LivingEntity entity, IAbility ability) {
/* 1708 */     if (!entity.func_233570_aj_()) {
/* 1709 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1712 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { ability.getDisplayName().getString() }));
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresDryUser(LivingEntity entity, IAbility ability) {
/* 1716 */     if (!entity.func_70026_G()) {
/* 1717 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1720 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_WHEN_DRY, new Object[] { ability.getDisplayName().getString() }));
/*      */   }
/*      */   
/*      */   public static AbilityUseResult requiresTonfaWeapon(LivingEntity entity, IAbility ability) {
/* 1724 */     boolean hasTonfaInHand = (!entity.func_184614_ca().func_190926_b() && entity.func_184614_ca().func_77973_b() == ModWeapons.TONFA.get());
/*      */     
/* 1726 */     if (hasTonfaInHand) {
/* 1727 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1730 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_TONFA));
/*      */   }
/*      */   
/*      */   public static boolean isAllowedToGoThrough(LivingEntity player, BlockState state) {
/* 1734 */     if (player == null) {
/* 1735 */       return false;
/*      */     }
/* 1737 */     IAbilityData props = AbilityDataCapability.get(player);
/* 1738 */     Set<IAbility> bypass = props.getPassiveAbilities(ability -> ability instanceof LogiaBlockBypassingAbility);
/* 1739 */     return bypass.stream().anyMatch(ability -> (((LogiaBlockBypassingAbility)ability).canGoThrough(state) && !((PassiveAbility2)ability).isPaused()));
/*      */   }
/*      */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\AbilityHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */