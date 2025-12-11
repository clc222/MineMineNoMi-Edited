/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class TwoFishEngineAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "two_fish_engine", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Increases the user's swimming speed", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 200.0F;
/*    */   private static final float MIN_COOLDOWN = 100.0F;
/*    */   private static final float MAX_COOLDOWN = 300.0F;
/* 33 */   public static final AbilityCore<TwoFishEngineAbility> INSTANCE = (new AbilityCore.Builder("Two Fish Engine", AbilityCategory.RACIAL, TwoFishEngineAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 300.0F), ContinuousComponent.getTooltip(200.0F), ChangeStatsComponent.getTooltip()
/* 36 */       }).setUnlockCheck(TwoFishEngineAbility::canUnlock)
/* 37 */     .build();
/*    */   
/* 39 */   private static final AbilityAttributeModifier SWIN_SPEED = new AbilityAttributeModifier(UUID.fromString("c6ad4347-b287-4bd5-b6c9-1533543fd15c"), INSTANCE, "Fishman Speed Modifier", 1.75D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   
/* 41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(100, this::onEndContinuousEvent);
/* 42 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/*    */   
/*    */   public TwoFishEngineAbility(AbilityCore<TwoFishEngineAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent });
/*    */     
/* 50 */     this.statsComponent.addAttributeModifier((Attribute)ForgeMod.SWIM_SPEED.get(), (AttributeModifier)SWIN_SPEED, entity -> this.continuousComponent.isContinuous());
/*    */     
/* 52 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 56 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void onEndContinuousEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.cooldownComponent.startCooldown(entity, 100.0F + this.continuousComponent.getContinueTime());
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 64 */     IEntityStats props = EntityStatsCapability.get(user);
/* 65 */     return (props.isFishman() && props.getDoriki() >= 2000.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\TwoFishEngineAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */