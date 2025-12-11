/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
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
/*    */ 
/*    */ public class GearThirdAbility extends Ability {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gear_third", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("By blowing air and inflating their body, the user's attacks get bigger and gain incredible strength.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 400;
/*    */   private static final int MIN_COOLDOWN = 60;
/*    */   private static final float MAX_COOLDOWN = 400.0F;
/* 27 */   public static final AbilityCore<GearThirdAbility> INSTANCE = (new AbilityCore.Builder("Gear Third", AbilityCategory.DEVIL_FRUITS, GearThirdAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F, 400.0F), ContinuousComponent.getTooltip(400.0F)
/* 30 */       }).build();
/*    */   
/* 32 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/*    */   public GearThirdAbility(AbilityCore<GearThirdAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.isNew = true;
/* 38 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 40 */     addCanUseCheck(GomuHelper.canUseGearCheck(INSTANCE));
/* 41 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 45 */     this.continuousComponent.triggerContinuity(entity, 400.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 49 */     IAbilityData props = AbilityDataCapability.get(entity);
/*    */     
/* 51 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/* 52 */     if (pistol != null) {
/* 53 */       pistol.switchThirdGear(entity);
/*    */     }
/*    */     
/* 56 */     GomuGomuNoGatlingAbility gatling = (GomuGomuNoGatlingAbility)props.getEquippedAbility(GomuGomuNoGatlingAbility.INSTANCE);
/* 57 */     if (gatling != null) {
/* 58 */       gatling.switchThirdGear(entity);
/*    */     }
/*    */     
/* 61 */     GomuGomuNoBazookaAbility bazooka = (GomuGomuNoBazookaAbility)props.getEquippedAbility(GomuGomuNoBazookaAbility.INSTANCE);
/* 62 */     if (bazooka != null) {
/* 63 */       bazooka.switchThirdGear(entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 68 */     IAbilityData props = AbilityDataCapability.get(entity);
/*    */     
/* 70 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/* 71 */     if (pistol != null) {
/* 72 */       pistol.switchNoGear(entity);
/*    */     }
/*    */     
/* 75 */     GomuGomuNoGatlingAbility gatling = (GomuGomuNoGatlingAbility)props.getEquippedAbility(GomuGomuNoGatlingAbility.INSTANCE);
/* 76 */     if (gatling != null) {
/* 77 */       gatling.switchNoGear(entity);
/*    */     }
/*    */     
/* 80 */     GomuGomuNoBazookaAbility bazooka = (GomuGomuNoBazookaAbility)props.getEquippedAbility(GomuGomuNoBazookaAbility.INSTANCE);
/* 81 */     if (bazooka != null) {
/* 82 */       bazooka.switchNoGear(entity);
/*    */     }
/*    */     
/* 85 */     float cooldown = Math.max(60.0F, this.continuousComponent.getContinueTime());
/* 86 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearThirdAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */