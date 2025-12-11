/*     */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public class AbareHimatsuriAbility extends Ability {
/*  24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "abare_himatsuri", new Pair[] {
/*  25 */         (Pair)ImmutablePair.of("Makes the user fly using gravity on the ground below you.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int HOLD_TIME = 1200;
/*  30 */   public static final AbilityCore<AbareHimatsuriAbility> INSTANCE = (new AbilityCore.Builder("Abare Himatsuri", AbilityCategory.DEVIL_FRUITS, AbareHimatsuriAbility::new))
/*  31 */     .addDescriptionLine(DESCRIPTION)
/*  32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(1200.0F)
/*  33 */       }).build();
/*     */   
/*  35 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*     */   
/*     */   private boolean stateChanged = false;
/*     */ 
/*     */   
/*     */   public AbareHimatsuriAbility(AbilityCore<AbareHimatsuriAbility> core) {
/*  41 */     super(core);
/*     */     
/*  43 */     this.isNew = true;
/*  44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  46 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  47 */     addCanUseCheck((entity, ability) -> AbilityHelper.isInCreativeOrSpectator(entity) ? AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY) : AbilityUseResult.success());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  57 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  61 */     if (entity instanceof PlayerEntity) {
/*  62 */       PlayerEntity player = (PlayerEntity)entity;
/*  63 */       player.field_71075_bZ.field_75101_c = true;
/*  64 */       if (!entity.field_70170_p.field_72995_K) {
/*  65 */         ((ServerPlayerEntity)entity).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  71 */     if (entity instanceof PlayerEntity) {
/*  72 */       PlayerEntity player = (PlayerEntity)entity;
/*  73 */       player.field_70143_R = 0.0F;
/*  74 */       if (!AbilityHelper.canUseMomentumAbilities((LivingEntity)player)) {
/*  75 */         if (player.field_71075_bZ.field_75101_c) {
/*  76 */           player.field_71075_bZ.field_75101_c = false;
/*  77 */           player.field_71075_bZ.field_75100_b = false;
/*  78 */           this.stateChanged = true;
/*     */         }
/*     */       
/*     */       }
/*  82 */       else if (!player.field_71075_bZ.field_75101_c) {
/*  83 */         player.field_71075_bZ.field_75101_c = true;
/*  84 */         this.stateChanged = true;
/*     */       } 
/*     */ 
/*     */       
/*  88 */       if (!entity.field_70170_p.field_72995_K) {
/*  89 */         ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */       }
/*     */       
/*  92 */       boolean canFly = DevilFruitHelper.flyingAtMaxHeight((LivingEntity)player, 48.0D);
/*  93 */       if (player.field_71075_bZ.field_75100_b) {
/*  94 */         DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.func_226278_cu_() - 1));
/*     */       }
/*  96 */       if (player.func_70051_ag()) {
/*  97 */         AbilityHelper.setDeltaMovement((Entity)player, player.func_213322_ci().func_216372_d(0.69D, 1.0D, 0.69D));
/*  98 */         player.func_70031_b(false);
/*  99 */         this.stateChanged = true;
/*     */       } 
/*     */       
/* 102 */       if (this.stateChanged) {
/* 103 */         this.stateChanged = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 109 */     if (entity instanceof PlayerEntity) {
/* 110 */       PlayerEntity player = (PlayerEntity)entity;
/* 111 */       player.field_71075_bZ.field_75101_c = false;
/* 112 */       player.field_71075_bZ.field_75100_b = false;
/* 113 */       if (!entity.field_70170_p.field_72995_K) {
/* 114 */         ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */       }
/*     */     } 
/* 117 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\AbareHimatsuriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */