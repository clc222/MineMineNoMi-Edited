/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimeScreamComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BowTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ConsumptionComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HealComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PauseTickComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireAbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SlotDecorationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwitchModeComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModAbilityKeys
/*    */ {
/* 41 */   public static final AbilityComponentKey<CooldownComponent> COOLDOWN = register("cooldown");
/* 42 */   public static final AbilityComponentKey<DisableComponent> DISABLE = register("disable");
/* 43 */   public static final AbilityComponentKey<ContinuousComponent> CONTINUOUS = register("continuous");
/* 44 */   public static final AbilityComponentKey<ChargeComponent> CHARGE = register("charge");
/* 45 */   public static final AbilityComponentKey<RepeaterComponent> REPEATER = register("repeater");
/* 46 */   public static final AbilityComponentKey<AnimationComponent> ANIMATION = register("animation");
/* 47 */   public static final AbilityComponentKey<HitTrackerComponent> HIT_TRACKER = register("hit_tracker");
/* 48 */   public static final AbilityComponentKey<GaugeComponent> GAUGE = register("gauge");
/* 49 */   public static final AbilityComponentKey<SkinOverlayComponent> SKIN_OVERLAY = register("skin_overlay");
/* 50 */   public static final AbilityComponentKey<ChangeStatsComponent> CHANGE_STATS = register("change_stats");
/* 51 */   public static final AbilityComponentKey<SlotDecorationComponent> SLOT_DECORATION = register("slot_decoration");
/* 52 */   public static final AbilityComponentKey<PoolComponent> POOL = register("pool_component");
/* 53 */   public static final AbilityComponentKey<BowTriggerComponent> BOW_TRIGGER = register("bow_trigger");
/* 54 */   public static final AbilityComponentKey<DamageTakenComponent> DAMAGE_TAKEN = register("damage_taken");
/* 55 */   public static final AbilityComponentKey<ItemSpawnComponent> ITEM_SPAWN = register("item_spawn");
/* 56 */   public static final AbilityComponentKey<StackComponent> STACK = register("stack");
/* 57 */   public static final AbilityComponentKey<AltModeComponent<?>> ALT_MODE = register("alt_mode");
/* 58 */   public static final AbilityComponentKey<DealDamageComponent> DAMAGE = register("damage");
/* 59 */   public static final AbilityComponentKey<PauseTickComponent> PAUSE_TICK = register("pause_tick");
/* 60 */   public static final AbilityComponentKey<HitTriggerComponent> HIT_TRIGGER = register("hit_trigger");
/* 61 */   public static final AbilityComponentKey<AnimeScreamComponent> ANIME_SCREAM = register("anime_scream");
/* 62 */   public static final AbilityComponentKey<SwitchModeComponent> SWITCH_MODE = register("switch_mode");
/* 63 */   public static final AbilityComponentKey<SwingTriggerComponent> SWING_TRIGGER = register("swing_trigger");
/* 64 */   public static final AbilityComponentKey<RangeComponent> RANGE = register("range");
/* 65 */   public static final AbilityComponentKey<MorphComponent> MORPH = register("morph");
/* 66 */   public static final AbilityComponentKey<GrabEntityComponent> GRAB = register("grab");
/* 67 */   public static final AbilityComponentKey<ProjectileComponent> PROJECTILE = register("projectile");
/* 68 */   public static final AbilityComponentKey<HealComponent> HEAL = register("heal");
/* 69 */   public static final AbilityComponentKey<RequireMorphComponent> REQUIRE_MORPH = register("require_morph");
/* 70 */   public static final AbilityComponentKey<ConsumptionComponent> CONSUMPTION = register("consumption");
/* 71 */   public static final AbilityComponentKey<RequireAbilityComponent> REQUIRE_ABILITY = register("require_ability");
/*    */   
/*    */   private static <C extends xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent<?>> AbilityComponentKey<C> register(String name) {
/* 74 */     return AbilityComponentKey.key(new ResourceLocation("mineminenomi", name));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModAbilityKeys.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */