/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ 
/*    */ import java.awt.Color;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class MagmaCoatingAbility
/*    */   extends PunchAbility2 {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "magma_coating", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("The user coats their arm with magma", null)
/*    */       });
/*    */   
/*    */   private static final float THRESHOLD = 600.0F;
/*    */   private static final float COOLDOWN = 200.0F;
/* 37 */   public static final AbilityCore<MagmaCoatingAbility> INSTANCE = (new AbilityCore.Builder("Magma Coating", AbilityCategory.DEVIL_FRUITS, MagmaCoatingAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 40 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(600.0F), ChangeStatsComponent.getTooltip()
/* 41 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 42 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 43 */       }).setSourceElement(SourceElement.MAGMA)
/* 44 */     .build();
/*    */   
/* 46 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/* 48 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setTexture(ModResources.DOKU_COATING).setColor(new Color(160, 0, 0)).build();
/*    */   
/*    */   public MagmaCoatingAbility(AbilityCore<MagmaCoatingAbility> core) {
/* 51 */     super(core);
/*    */     
/* 53 */     this.isNew = true;
/*    */     
/* 55 */     this.continuousComponent.addStartEvent(this::onContinuityStart).addEndEvent(this::onContinuityEnd);
/*    */     
/* 57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 61 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 65 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 70 */     return 25.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 75 */     return 200.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 80 */     AbilityHelper.setSecondsOnFireBy((Entity)target, 5, entity);
/*    */     
/* 82 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchHoldTime() {
/* 87 */     return 600.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 92 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 97 */     return -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\MagmaCoatingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */