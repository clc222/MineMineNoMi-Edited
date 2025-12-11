/*    */ package xyz.pixelatedw.mineminenomi.abilities.kachi;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class VulcanizationAbility
/*    */   extends PunchAbility2
/*    */ {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "vulcanization", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Coats the user into a thick stone-like layer that increases the armor of its user.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 200;
/*    */   private static final int COOLDOWN = 300;
/* 38 */   public static final AbilityCore<VulcanizationAbility> INSTANCE = (new AbilityCore.Builder("Vulcanization", AbilityCategory.DEVIL_FRUITS, VulcanizationAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(200.0F), ChangeStatsComponent.getTooltip()
/* 41 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 42 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 43 */       }).build();
/*    */   
/* 45 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.BODY).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFFFFFAA").build();
/* 46 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(UUID.fromString("b0e012c8-1479-414d-8015-9044225572a7"), INSTANCE, "Vulcanization Modifier", 7.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 48 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public VulcanizationAbility(AbilityCore<VulcanizationAbility> core) {
/* 51 */     super(core);
/*    */     
/* 53 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/*    */     
/* 55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 57 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/* 58 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 66 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 71 */     return 4.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 81 */     return entity -> this.continuousComponent.isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 86 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchHoldTime() {
/* 91 */     return 200.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 96 */     return 300.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kachi\VulcanizationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */