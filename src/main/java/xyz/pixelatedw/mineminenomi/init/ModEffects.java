/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.DamageOverTimeEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HandcuffType;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NetType;
/*     */ import xyz.pixelatedw.mineminenomi.effects.CaughtInNetEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.GuardingEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
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
/*     */ public class ModEffects
/*     */ {
/*  79 */   public static final RegistryObject<Effect> BUBBLY_CORAL = WyRegistry.registerEffect("Bubbly Coral", xyz.pixelatedw.mineminenomi.effects.BubblyCoralEffect::new);
/*  80 */   public static final RegistryObject<Effect> ANIMAL_LOOKOUT = WyRegistry.registerEffect("Animal Lookout", xyz.pixelatedw.mineminenomi.effects.LookoutEffect::new);
/*  81 */   public static final RegistryObject<Effect> CANDLE_LOCK = WyRegistry.registerEffect("Candle Lock", xyz.pixelatedw.mineminenomi.effects.CandleLockEffect::new);
/*  82 */   public static final RegistryObject<Effect> LOVESTRUCK = WyRegistry.registerEffect("Lovestruck", xyz.pixelatedw.mineminenomi.effects.LovestruckEffect::new);
/*  83 */   public static final RegistryObject<Effect> NEGATIVE = WyRegistry.registerEffect("Negative", xyz.pixelatedw.mineminenomi.effects.NegativeEffect::new);
/*  84 */   public static final RegistryObject<Effect> CHIYU_HORMONE = WyRegistry.registerEffect("Chiyu Hormone", xyz.pixelatedw.mineminenomi.effects.ChiyuHormoneEffect::new);
/*  85 */   public static final RegistryObject<Effect> FROSTBITE = WyRegistry.registerEffect("Frostbite", xyz.pixelatedw.mineminenomi.effects.FrostbiteEffect::new);
/*  86 */   public static final RegistryObject<Effect> FROZEN = WyRegistry.registerEffect("Frozen", xyz.pixelatedw.mineminenomi.effects.FrozenEffect::new);
/*  87 */   public static final RegistryObject<Effect> TENSION_HORMONE = WyRegistry.registerEffect("Tension Hormone", xyz.pixelatedw.mineminenomi.effects.TensionHormoneEffect::new);
/*  88 */   public static final RegistryObject<Effect> GANMEN_SEICHO_HORMONE = WyRegistry.registerEffect("Ganmen Seicho Hormone", xyz.pixelatedw.mineminenomi.effects.GanmenSeichoHormoneEffect::new);
/*  89 */   public static final RegistryObject<Effect> DRUNK = WyRegistry.registerEffect("Drunk", xyz.pixelatedw.mineminenomi.effects.DrunkEffect::new);
/*     */   @Deprecated
/*  91 */   public static final RegistryObject<Effect> ABILITY_OFF = WyRegistry.registerEffect("Ability Off", xyz.pixelatedw.mineminenomi.effects.AbilityOffEffect::new);
/*  92 */   public static final RegistryObject<Effect> DOOR_HEAD = WyRegistry.registerEffect("Door Head", xyz.pixelatedw.mineminenomi.effects.DoorHeadEffect::new);
/*  93 */   public static final RegistryObject<Effect> DIZZY = WyRegistry.registerEffect("Dizzy", xyz.pixelatedw.mineminenomi.effects.DizzyEffect::new);
/*  94 */   public static final RegistryObject<Effect> MOVEMENT_BLOCKED = WyRegistry.registerEffect("Movement Blocked", xyz.pixelatedw.mineminenomi.effects.MovementBlockedEffect::new);
/*  95 */   public static final RegistryObject<Effect> UNCONSCIOUS = WyRegistry.registerEffect("Unconscious", xyz.pixelatedw.mineminenomi.effects.UnconsciousEffect::new);
/*  96 */   public static final RegistryObject<Effect> WASHED = WyRegistry.registerEffect("Washed", xyz.pixelatedw.mineminenomi.effects.WashedEffect::new);
/*  97 */   public static final RegistryObject<Effect> STICKY = WyRegistry.registerEffect("Sticky", xyz.pixelatedw.mineminenomi.effects.StickyEffect::new);
/*  98 */   public static final RegistryObject<Effect> BLACK_BOX = WyRegistry.registerEffect("Black Box", xyz.pixelatedw.mineminenomi.effects.BlackBoxEffect::new);
/*     */   @Deprecated
/* 100 */   public static final RegistryObject<Effect> GUARDING = WyRegistry.registerEffect("Guarding", () -> new GuardingEffect(false, true));
/*     */   @Deprecated
/* 102 */   public static final RegistryObject<Effect> PHYSICAL_MOVING_GUARD = WyRegistry.registerEffect("Physical Guarding", () -> (new GuardingEffect(true, true, false, new String[] { "mob", "player", "ability_projectile", "ability", "fall" })).func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "3abb639e-a984-42d1-a4e1-674fcafcbfbc", 0.05D, AttributeModifier.Operation.ADDITION).func_220304_a(Attributes.field_233820_c_, "3abb639e-a984-42d1-a4e1-674fcafcbfbc", 3.0D, AttributeModifier.Operation.ADDITION));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static final RegistryObject<Effect> CANDY_STUCK = WyRegistry.registerEffect("Candy Stuck", xyz.pixelatedw.mineminenomi.effects.CandyStuckEffect::new);
/* 108 */   public static final RegistryObject<Effect> PARALYSIS = WyRegistry.registerEffect("Paralysis", xyz.pixelatedw.mineminenomi.effects.ParalysisEffect::new);
/* 109 */   public static final RegistryObject<Effect> HAKI_OVERUSE = WyRegistry.registerEffect("Haki Overuse", xyz.pixelatedw.mineminenomi.effects.HakiOveruseEffect::new);
/* 110 */   public static final RegistryObject<Effect> SMOKE = WyRegistry.registerEffect("Smoke", xyz.pixelatedw.mineminenomi.effects.SmokeEffect::new);
/* 111 */   public static final RegistryObject<Effect> REDUCED_FALL = WyRegistry.registerEffect("Reduced Fall", xyz.pixelatedw.mineminenomi.effects.ReducedFallEffect::new);
/* 112 */   public static final RegistryObject<Effect> HANDCUFFED = WyRegistry.registerEffect("Handcuffed", () -> new HandcuffedEffect(HandcuffType.NORMAL));
/* 113 */   public static final RegistryObject<Effect> HANDCUFFED_KAIROSEKI = WyRegistry.registerEffect("Handcuffed", "handcuffed_kairoseki", () -> new HandcuffedEffect(HandcuffType.KAIROSEKI));
/* 114 */   public static final RegistryObject<Effect> HANDCUFFED_EXPLOSIVE = WyRegistry.registerEffect("Handcuffed", "handcuffed_explosive", () -> new HandcuffedEffect(HandcuffType.EXPLOSIVE));
/* 115 */   public static final RegistryObject<Effect> BIND = WyRegistry.registerEffect("Bind", xyz.pixelatedw.mineminenomi.effects.BindEffect::new);
/* 116 */   public static final RegistryObject<Effect> NO_HANDS = WyRegistry.registerEffect("No Hands", xyz.pixelatedw.mineminenomi.effects.NoHandsEffect::new);
/* 117 */   public static final RegistryObject<Effect> FATIGUE_EFFECT = WyRegistry.registerEffect("Fatigue", xyz.pixelatedw.mineminenomi.effects.FatigueEffect::new);
/* 118 */   public static final RegistryObject<Effect> NORO_SLOWNESS = WyRegistry.registerEffect("Noro Slowness", xyz.pixelatedw.mineminenomi.effects.NoroSlownessEffect::new);
/*     */   @Deprecated
/* 120 */   public static final RegistryObject<Effect> GUARDING_WITH_MOVEMENT = WyRegistry.registerEffect("Guarding with Movement", () -> new GuardingEffect(true, true));
/* 121 */   public static final RegistryObject<Effect> BLEEDING = WyRegistry.registerEffect("Bleeding", () -> new DamageOverTimeEffect((new ModDamageSource(DamageSource.field_76376_m)).setUnavoidable(), 2.0F, 20));
/* 122 */   public static final RegistryObject<Effect> HANA_HANDS = WyRegistry.registerEffect("Hana", xyz.pixelatedw.mineminenomi.effects.HanaEffect::new);
/* 123 */   public static final RegistryObject<Effect> SILENT = WyRegistry.registerEffect("Silent", xyz.pixelatedw.mineminenomi.effects.SilentEffect::new);
/* 124 */   public static final RegistryObject<Effect> SABOTEN_BOSHI = WyRegistry.registerEffect("Saboten Boshi", () -> new DamageOverTimeEffect(DamageSource.field_76367_g, 2.0F, 40));
/* 125 */   public static final RegistryObject<Effect> OIL_COVERED = WyRegistry.registerEffect("Oil Covered", xyz.pixelatedw.mineminenomi.effects.OilCoveredEffect::new);
/* 126 */   public static final RegistryObject<Effect> DEHYDRATION = WyRegistry.registerEffect("Dehydration", xyz.pixelatedw.mineminenomi.effects.DehydrationEffect::new);
/* 127 */   public static final RegistryObject<Effect> GENOCIDE_RAID = WyRegistry.registerEffect("Genocide Raid", xyz.pixelatedw.mineminenomi.effects.GenocideRaidEffect::new);
/* 128 */   public static final RegistryObject<Effect> DOKU_POISON = WyRegistry.registerEffect("Doku Poison", xyz.pixelatedw.mineminenomi.effects.DokuPoisonEffect::new);
/* 129 */   public static final RegistryObject<Effect> PUNK_CROSS = WyRegistry.registerEffect("Punk Cross", xyz.pixelatedw.mineminenomi.effects.PunkCrossEffect::new);
/* 130 */   public static final RegistryObject<Effect> VANISH = WyRegistry.registerEffect("Vanish", xyz.pixelatedw.mineminenomi.effects.VanishEffect::new);
/* 131 */   public static final RegistryObject<Effect> SUKE_INVISIBILITY = WyRegistry.registerEffect("Suke Invisibility", xyz.pixelatedw.mineminenomi.effects.VanishEffect::new);
/* 132 */   public static final RegistryObject<Effect> DOA_INVISIBILITY = WyRegistry.registerEffect("Doa Invisibility", xyz.pixelatedw.mineminenomi.effects.DoaVanishEffect::new);
/* 133 */   public static final RegistryObject<Effect> SEISMIC_BUBBLE = WyRegistry.registerEffect("Seismic Bubble", xyz.pixelatedw.mineminenomi.effects.SeismicBubbleEffect::new);
/* 134 */   public static final RegistryObject<Effect> IN_EVENT = WyRegistry.registerEffect("In Event", xyz.pixelatedw.mineminenomi.effects.InEventEffect::new);
/* 135 */   public static final RegistryObject<Effect> ANTIDOTE = WyRegistry.registerEffect("Antidote", xyz.pixelatedw.mineminenomi.effects.AntidoteEffect::new);
/* 136 */   public static final RegistryObject<Effect> HUNGER = WyRegistry.registerEffect("Hunger", xyz.pixelatedw.mineminenomi.api.effects.HungerOverTimeEffect::new);
/* 137 */   public static final RegistryObject<Effect> MUMMY_VIRUS = WyRegistry.registerEffect("Mummy", xyz.pixelatedw.mineminenomi.effects.MummyVirusEffect::new);
/* 138 */   public static final RegistryObject<Effect> MUMMY_VIRUS_ANTIBODY = WyRegistry.registerEffect("Mummy Antibody", xyz.pixelatedw.mineminenomi.effects.AntiBodyMummyVirusEffect::new);
/* 139 */   public static final RegistryObject<Effect> ICE_ONI = WyRegistry.registerEffect("Ice Oni", xyz.pixelatedw.mineminenomi.effects.IceOniEffect::new);
/* 140 */   public static final RegistryObject<Effect> ICE_ONI_ANTIBODY = WyRegistry.registerEffect("Ice Oni Antibody", xyz.pixelatedw.mineminenomi.effects.AntiBodyIceOniEffect::new);
/* 141 */   public static final RegistryObject<Effect> SCALING_FROSTBITE = WyRegistry.registerEffect("Scaling Frostbite", xyz.pixelatedw.mineminenomi.effects.ScalingFrostbiteEffect::new);
/* 142 */   public static final RegistryObject<Effect> CUSTOM_LIGHTING = WyRegistry.registerEffect("Custom Lighting", xyz.pixelatedw.mineminenomi.effects.CustomLightingEffect::new);
/* 143 */   public static final RegistryObject<Effect> CHIYUPOPO = WyRegistry.registerEffect("Chiyupopo", xyz.pixelatedw.mineminenomi.effects.ChiyupopoEffect::new);
/* 144 */   public static final RegistryObject<Effect> TOKUDAI_CANDLE_POISON = WyRegistry.registerEffect("Tokudai Candle Poison", xyz.pixelatedw.mineminenomi.effects.TokudaiCandlePoisonEffect::new);
/* 145 */   public static final RegistryObject<Effect> CYCLONE_TEMPO = WyRegistry.registerEffect("Cyclone Tempo", xyz.pixelatedw.mineminenomi.effects.CycloneTempoEffect::new);
/* 146 */   public static final RegistryObject<Effect> HEAT_EGG_TEMPO = WyRegistry.registerEffect("Heat Egg Tempo", xyz.pixelatedw.mineminenomi.effects.HeatEggTempoEffect::new);
/* 147 */   public static final RegistryObject<Effect> KAIROSEKI_WEAKNESS = WyRegistry.registerEffect("Kairoseki Weakness", xyz.pixelatedw.mineminenomi.effects.DFCurseEffect::new);
/* 148 */   public static final RegistryObject<Effect> WATER_WEAKNESS = WyRegistry.registerEffect("Water Weakness", xyz.pixelatedw.mineminenomi.effects.DFCurseEffect::new);
/* 149 */   public static final RegistryObject<Effect> PLAY_DEAD = WyRegistry.registerEffect("Play Dead", xyz.pixelatedw.mineminenomi.effects.PlayDeadEffect::new);
/* 150 */   public static final RegistryObject<Effect> CHALLENGE_FAILED = WyRegistry.registerEffect("Challenge Failed", xyz.pixelatedw.mineminenomi.effects.UnconsciousEffect::new);
/* 151 */   public static final RegistryObject<CaughtInNetEffect> CAUGHT_IN_NET = WyRegistry.registerEffect("Caught in Net", "caught_in_net", () -> new CaughtInNetEffect(NetType.NORMAL));
/* 152 */   public static final RegistryObject<CaughtInNetEffect> CAUGHT_IN_KAIROSEKI_NET = WyRegistry.registerEffect("Caught in Net", "caught_in_kairoseki_net", () -> new CaughtInNetEffect(NetType.KAIROSEKI));
/* 153 */   public static final RegistryObject<Effect> CARRYING = WyRegistry.registerEffect("Carrying", xyz.pixelatedw.mineminenomi.effects.CarryingEffect::new);
/* 154 */   public static final RegistryObject<Effect> WET = WyRegistry.registerEffect("Wet", xyz.pixelatedw.mineminenomi.effects.WetEffect::new);
/* 155 */   public static final RegistryObject<Effect> SWIM_SLOWDOWN = WyRegistry.registerEffect("Swim Slowdown", xyz.pixelatedw.mineminenomi.effects.SwimSlownessEffect::new);
/* 156 */   public static final RegistryObject<Effect> GRABBED = WyRegistry.registerEffect("Grabbed", xyz.pixelatedw.mineminenomi.effects.GrabbedEffect::new);
/* 157 */   public static final RegistryObject<Effect> ANTI_KNOCKBACK = WyRegistry.registerEffect("Anti Knockback", xyz.pixelatedw.mineminenomi.effects.AntiKnockbackEffect::new);
/* 158 */   public static final RegistryObject<Effect> LAUNCHED = WyRegistry.registerEffect("Launched", xyz.pixelatedw.mineminenomi.effects.LaunchedEffect::new);
/* 159 */   public static final RegistryObject<Effect> FRAGILE = WyRegistry.registerEffect("Fragile", xyz.pixelatedw.mineminenomi.effects.FragileEffect::new);
/* 160 */   public static final RegistryObject<Effect> IMPACT_FRAME = WyRegistry.registerEffect("Impact Frame", xyz.pixelatedw.mineminenomi.effects.ImpactFrameEffect::new);
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 163 */     WyRegistry.EFFECTS.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */