/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreCriteria;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DFWeaknessesEvents {
/*     */   static {
/*  40 */     WATER_DISABLED_ABILITIES_PREDICATE = (abl -> 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  46 */       !(abl.getCore().getType() == AbilityType.PASSIVE && abl.getCore().getCategory() != AbilityCategory.DEVIL_FRUITS));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Predicate<IAbility> WATER_DISABLED_ABILITIES_PREDICATE;
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/*  56 */     PlayerEntity player = event.getPlayer();
/*  57 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  59 */     if (props.hasAnyDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/*  60 */       event.setNewSpeed(event.getOriginalSpeed() / 15.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
/*  66 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */       return;
/*     */     }
/*  69 */     PlayerEntity player = event.getPlayer();
/*  70 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  72 */     if (props.hasAnyDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/*  73 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickEmpty(PlayerInteractEvent.RightClickItem event) {
/*  79 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled() || event.getItemStack().func_77973_b().equals(ModItems.BUBBLY_CORAL.get())) {
/*     */       return;
/*     */     }
/*  82 */     PlayerEntity player = event.getPlayer();
/*  83 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  85 */     if (props.hasAnyDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/*  86 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
/*  92 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */       return;
/*     */     }
/*  95 */     PlayerEntity player = event.getPlayer();
/*  96 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  98 */     if (props.hasAnyDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/*  99 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
/* 105 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */       return;
/*     */     }
/* 108 */     PlayerEntity player = event.getPlayer();
/* 109 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 111 */     if (props.hasAnyDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/* 112 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
/* 118 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled() || !(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 121 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/* 122 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 124 */     if (props.hasAnyDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get()))
/* 125 */       event.setCanceled(true); 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 130 */     if (event.getEntityLiving() != null) {
/* 131 */       LivingEntity entity = event.getEntityLiving();
/*     */       
/* 133 */       IDevilFruit props = DevilFruitCapability.get(entity);
/* 134 */       IAbilityData abilityData = AbilityDataCapability.get(entity);
/*     */       
/* 136 */       boolean hasAnyFruit = props.hasAnyDevilFruit();
/* 137 */       boolean hasAnyFruitAbility = (abilityData.getUnlockedAbilities(a -> (a.getCategory() == AbilityCategory.DEVIL_FRUITS)).size() > 0);
/*     */       
/* 139 */       if (!hasAnyFruit && !hasAnyFruitAbility) {
/*     */         return;
/*     */       }
/*     */       
/* 143 */       boolean hasStrongWaterWeakness = props.hasStrongWaterWeakness();
/* 144 */       boolean hasKairosekiWeakness = props.hasKairosekiWeakness();
/* 145 */       boolean hasWeakWaterWeakness = props.hasWeakWaterWeakness();
/*     */       
/* 147 */       if (entity.field_70170_p.func_82737_E() % 30L == 0L) {
/* 148 */         hasStrongWaterWeakness = AbilityHelper.isAffectedByWater(entity);
/* 149 */         hasKairosekiWeakness = AbilityHelper.isKairosekiNearby(entity);
/* 150 */         hasWeakWaterWeakness = AbilityHelper.isAffectedByWater(entity, 0.25F);
/*     */         
/* 152 */         props.setStrongWaterWeakness(hasStrongWaterWeakness);
/* 153 */         props.setKairosekiWeakness(hasKairosekiWeakness);
/* 154 */         props.setWeakWaterWeakness(hasWeakWaterWeakness);
/*     */       } 
/*     */ 
/*     */       
/* 158 */       if (props.hasAnyDevilFruit() && hasStrongWaterWeakness && !entity.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/* 159 */         boolean isCreative = (entity instanceof PlayerEntity && ((PlayerEntity)entity).func_184812_l_());
/* 160 */         if (!isCreative) {
/* 161 */           if (entity.func_213314_bj()) {
/* 162 */             AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b - 0.15D, (entity.func_213322_ci()).field_72449_c);
/*     */           } else {
/*     */             
/* 165 */             AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b - 0.1D, (entity.func_213322_ci()).field_72449_c);
/*     */           } 
/*     */           
/* 168 */           if (entity instanceof PlayerEntity && CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/* 169 */             ((PlayerEntity)entity).func_71020_j(0.015F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 175 */       if (entity instanceof PlayerEntity && 
/* 176 */         props.hasAnyDevilFruit() && !entity.field_70170_p.field_72995_K && CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/* 177 */         boolean hasExtraWaterTime = scoreCheck((PlayerEntity)entity, hasStrongWaterWeakness);
/*     */         
/* 179 */         if (hasStrongWaterWeakness && hasExtraWaterTime) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 186 */       if (!entity.field_70170_p.field_72995_K) {
/* 187 */         Effect curseType = null;
/*     */         
/* 189 */         if (hasKairosekiWeakness) {
/* 190 */           curseType = (Effect)ModEffects.KAIROSEKI_WEAKNESS.get();
/*     */         }
/* 192 */         else if (hasStrongWaterWeakness) {
/* 193 */           curseType = (Effect)ModEffects.WATER_WEAKNESS.get();
/*     */         } 
/*     */         
/* 196 */         if (hasStrongWaterWeakness || hasKairosekiWeakness) {
/* 197 */           if (curseType != null) {
/* 198 */             entity.func_195064_c(new EffectInstance(curseType, 40, 0));
/*     */           }
/*     */           
/* 201 */           if (entity instanceof PlayerEntity && CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/* 202 */             ((PlayerEntity)entity).func_71020_j(0.015F);
/*     */           }
/* 204 */           AbilityHelper.disableAbilities(entity, 20, WATER_DISABLED_ABILITIES_PREDICATE);
/*     */         }
/* 206 */         else if (hasWeakWaterWeakness) {
/* 207 */           entity.func_195064_c(new EffectInstance((Effect)ModEffects.SWIM_SLOWDOWN.get(), 20, 2, false, false, true));
/* 208 */           entity.func_195064_c(new EffectInstance(Effects.field_76437_t, 20, 1, false, false, true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean scoreCheck(PlayerEntity player, boolean value) {
/* 216 */     Scoreboard scoreboard = player.field_70170_p.func_96441_U();
/* 217 */     if (!scoreboard.func_197897_d().contains("watertime_mmnm"))
/* 218 */       scoreboard.func_199868_a("watertime_mmnm", ScoreCriteria.field_96641_b, (ITextComponent)new StringTextComponent("waterTime"), ScoreCriteria.RenderType.INTEGER); 
/* 219 */     Score score = scoreboard.func_96529_a(player.func_110124_au().toString(), Objects.<ScoreObjective>requireNonNull(scoreboard.func_96518_b("watertime_mmnm")));
/*     */     
/* 221 */     if (value) {
/*     */       
/* 223 */       List<BlockPos> blockPosList = WyHelper.getNearbyBlocks((Entity)player, 1);
/* 224 */       float size = (float)blockPosList.stream().map(pos -> player.field_70170_p.func_180495_p(pos).func_177230_c()).filter(block -> block.equals(Blocks.field_150355_j)).count() / 4.0F;
/* 225 */       if (0.0F >= size)
/* 226 */         size = 1.0F; 
/* 227 */       score.func_96649_a(Math.round(size));
/* 228 */       return (score.func_96652_c() <= 60);
/*     */     } 
/*     */     
/* 231 */     score.func_197891_c();
/* 232 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\DFWeaknessesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */