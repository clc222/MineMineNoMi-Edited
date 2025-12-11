/*    */ package xyz.pixelatedw.mineminenomi.abilities.kira;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CabochonKnuckleAbility
/*    */   extends PunchAbility2 {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "cabochon_knuckle", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Covers the user's punch in a diamond coating, dealing damage and slight knockback.", null)
/*    */       });
/* 33 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.DIAMOND_BODY).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).build();
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/*    */   
/* 37 */   public static final AbilityCore<CabochonKnuckleAbility> INSTANCE = (new AbilityCore.Builder("Cabochon Knuckle", AbilityCategory.DEVIL_FRUITS, CabochonKnuckleAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 40 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 41 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 42 */       }).build();
/*    */   
/* 44 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public CabochonKnuckleAbility(AbilityCore<CabochonKnuckleAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 51 */     this.continuousComponent.addStartEvent(100, this::onContinuityStart);
/* 52 */     this.continuousComponent.addEndEvent(100, this::onContinuityEnd);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 56 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 60 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 65 */     Vector3d speed = WyHelper.propulsion(entity, 3.0D, 3.0D);
/* 66 */     AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(speed.field_72450_a, 0.5D, speed.field_72449_c));
/* 67 */     target.func_230245_c_(false);
/*    */     
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 74 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 84 */     return 15.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 89 */     return 160.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 94 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kira\CabochonKnuckleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */