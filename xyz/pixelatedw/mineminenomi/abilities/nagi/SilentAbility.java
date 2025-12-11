/*     */ package xyz.pixelatedw.mineminenomi.abilities.nagi;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.sound.PlaySoundEvent;
/*     */ import net.minecraftforge.event.ServerChatEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class SilentAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "silent", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Cancels all noises caused by or around the user.", null)
/*     */       });
/*  38 */   private static final TargetsPredicate TARGET_CHECK = new TargetsPredicate();
/*     */   
/*     */   private static final int RANGE = 30;
/*     */   
/*  42 */   public static final AbilityCore<SilentAbility> INSTANCE = (new AbilityCore.Builder("Silent", AbilityCategory.DEVIL_FRUITS, SilentAbility::new))
/*  43 */     .addDescriptionLine(DESCRIPTION)
/*  44 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RangeComponent.getTooltip(30.0F, RangeComponent.RangeType.AOE)
/*  45 */       }).build();
/*     */   
/*  47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::duringContinuityEvent);
/*  48 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   public SilentAbility(AbilityCore<SilentAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  56 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  60 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  64 */     if (this.continuousComponent.getContinueTime() % 15.0F == 0.0F) {
/*  65 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 30.0F, TARGET_CHECK);
/*     */       
/*  67 */       for (LivingEntity target : targets) {
/*  68 */         EffectInstance inst = new EffectInstance((Effect)ModEffects.SILENT.get(), 20, 0, false, false);
/*  69 */         target.func_195064_c(inst);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class SilentAbilityServerEvents
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onServerMessage(ServerChatEvent event) {
/*  82 */       ServerPlayerEntity serverPlayerEntity = event.getPlayer();
/*  83 */       IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*  84 */       SilentAbility ability = (SilentAbility)abilityData.getEquippedAbility(SilentAbility.INSTANCE);
/*     */       
/*  86 */       boolean isNagiUser = (ability != null && ability.isContinuous());
/*     */       
/*  88 */       if (serverPlayerEntity.func_70644_a((Effect)ModEffects.SILENT.get()) && !isNagiUser) {
/*  89 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class SilentAbilityClientEvents {
/*     */     @SubscribeEvent
/*     */     public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  99 */       LivingEntity entity = event.getEntityLiving();
/* 100 */       if (entity.func_70644_a((Effect)ModEffects.SILENT.get()) && entity.field_70170_p.field_72995_K && entity.field_70173_aa % 20 == 0) {
/* 101 */         Minecraft.func_71410_x().func_147118_V().func_147690_c();
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onSoundPlayed(PlaySoundEvent event) {
/* 107 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 109 */       if (mc.field_71441_e == null) {
/*     */         return;
/*     */       }
/*     */       
/* 113 */       BlockPos soundPos = new BlockPos(event.getSound().func_147649_g(), event.getSound().func_147654_h(), event.getSound().func_147651_i());
/*     */       
/* 115 */       if (mc.field_71439_g.func_70644_a((Effect)ModEffects.SILENT.get()) && soundPos.func_218141_a((Vector3i)mc.field_71439_g.func_233580_cy_(), 30.0D))
/* 116 */         event.setResultSound(null); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nagi\SilentAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */