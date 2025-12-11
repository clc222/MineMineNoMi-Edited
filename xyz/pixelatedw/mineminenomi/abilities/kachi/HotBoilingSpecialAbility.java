/*    */ package xyz.pixelatedw.mineminenomi.abilities.kachi;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
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
/*    */ public class HotBoilingSpecialAbility
/*    */   extends PunchAbility2 {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hot_boiling_special", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Hardens and heats up the fist of the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 33 */   public static final AbilityCore<HotBoilingSpecialAbility> INSTANCE = (new AbilityCore.Builder("Hot Boiling Special", AbilityCategory.DEVIL_FRUITS, HotBoilingSpecialAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 36 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 37 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 38 */       }).build();
/*    */   
/* 40 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFFFFFAA").build();
/*    */   
/* 42 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public HotBoilingSpecialAbility(AbilityCore<HotBoilingSpecialAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 49 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/* 50 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 63 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 68 */     if (canActivate().test(entity)) {
/* 69 */       AbilityHelper.setSecondsOnFireBy((Entity)target, 20, entity);
/*    */     }
/*    */     
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 77 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 82 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 87 */     return 100.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kachi\HotBoilingSpecialAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */