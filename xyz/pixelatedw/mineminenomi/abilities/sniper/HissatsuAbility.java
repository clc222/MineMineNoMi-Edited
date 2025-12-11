/*    */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class HissatsuAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hissatsu", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Allows the next sniper ability to instantly hit the target", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 300.0F;
/* 33 */   public static final AbilityCore<HissatsuAbility> INSTANCE = (new AbilityCore.Builder("Hissatsu", AbilityCategory.STYLE, HissatsuAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip()
/* 36 */       }).setUnlockCheck(HissatsuAbility::canUnlock)
/* 37 */     .build();
/*    */   
/* 39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(this::onEndContinuity);
/*    */   
/*    */   public HissatsuAbility(AbilityCore<HissatsuAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 47 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onEndContinuity(LivingEntity entity, IAbility ability) {
/* 55 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */   
/*    */   public static boolean checkHitScan(LivingEntity entity) {
/* 59 */     Optional<HissatsuAbility> hissatsuAbility = AbilityDataCapability.getLazy(entity).resolve().map(props -> (HissatsuAbility)props.getEquippedAbility(INSTANCE));
/* 60 */     boolean isHitScan = false;
/* 61 */     if (hissatsuAbility.isPresent()) {
/* 62 */       isHitScan = ((HissatsuAbility)hissatsuAbility.get()).isContinuous();
/* 63 */       if (isHitScan) {
/* 64 */         ((HissatsuAbility)hissatsuAbility.get()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity(entity));
/* 65 */         entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 3.0F);
/* 66 */         return true;
/*    */       } 
/*    */     } 
/* 69 */     return false;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 73 */     if (!(entity instanceof PlayerEntity)) {
/* 74 */       return false;
/*    */     }
/*    */     
/* 77 */     PlayerEntity player = (PlayerEntity)entity;
/* 78 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 79 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 81 */     return (props.isSniper() && questProps.hasFinishedQuest(ModQuests.SNIPER_TRIAL_06));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\HissatsuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */