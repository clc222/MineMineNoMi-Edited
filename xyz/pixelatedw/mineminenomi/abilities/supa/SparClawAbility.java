/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.function.Predicate;
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
/*    */ 
/*    */ public class SparClawAbility
/*    */   extends PunchAbility2
/*    */ {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spar_claw", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Turns the undersides of the user's fingers into blades to slash opponents with", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 1000.0F;
/*    */   private static final float MIN_COOLDOWN = 20.0F;
/*    */   private static final float MAX_COOLDOWN = 1020.0F;
/* 35 */   public static final AbilityCore<SparClawAbility> INSTANCE = (new AbilityCore.Builder("Spar Claw", AbilityCategory.DEVIL_FRUITS, SparClawAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 1020.0F), ContinuousComponent.getTooltip(1000.0F), ChangeStatsComponent.getTooltip()
/* 38 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 39 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 40 */       }).build();
/*    */   
/* 42 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setColor(new Color(113, 121, 126)).build();
/*    */   
/* 44 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public SparClawAbility(AbilityCore<SparClawAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 51 */     this.continuousComponent.addStartEvent(100, this::continuousStartEvent);
/* 52 */     this.continuousComponent.addEndEvent(100, this::continuousStopEvent);
/*    */   }
/*    */   
/*    */   private void continuousStartEvent(LivingEntity entity, IAbility ability) {
/* 56 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void continuousStopEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 70 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 75 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchHoldTime() {
/* 85 */     return 1000.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 90 */     return 15.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 95 */     return 20.0F + this.continuousComponent.getContinueTime();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SparClawAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */