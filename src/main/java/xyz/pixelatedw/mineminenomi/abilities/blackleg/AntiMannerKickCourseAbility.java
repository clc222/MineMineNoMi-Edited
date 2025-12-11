/*    */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class AntiMannerKickCourseAbility extends PunchAbility2 {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "antimanner_kick_course", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Kicks an enemy and launches them vertically", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 240.0F;
/* 37 */   public static final AbilityCore<AntiMannerKickCourseAbility> INSTANCE = (new AbilityCore.Builder("Anti-Manner Kick Course", AbilityCategory.STYLE, AntiMannerKickCourseAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 40 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 41 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 42 */       }).setUnlockCheck(AntiMannerKickCourseAbility::canUnlock)
/* 43 */     .build();
/*    */   
/*    */   public AntiMannerKickCourseAbility(AbilityCore<AntiMannerKickCourseAbility> core) {
/* 46 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 51 */     return 15.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 56 */     AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(0.0D, 1.2000000000000002D, 0.0D));
/*    */     
/* 58 */     target.func_195064_c(new EffectInstance(Effects.field_76431_k, 40, 0, false, false));
/* 59 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0, false, false));
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 66 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 71 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 76 */     return 240.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 81 */     return true;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 85 */     if (!(entity instanceof PlayerEntity)) {
/* 86 */       return false;
/*    */     }
/*    */     
/* 89 */     PlayerEntity player = (PlayerEntity)entity;
/* 90 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 91 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 93 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_03));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\AntiMannerKickCourseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */