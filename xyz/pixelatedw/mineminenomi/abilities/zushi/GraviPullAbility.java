/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GraviPullAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gravi_pull", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Pulls all enemies in a radius towards the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 340;
/*    */   private static final int CHARGE_TIME = 60;
/*    */   private static final int RANGE = 16;
/* 31 */   public static final AbilityCore<GraviPullAbility> INSTANCE = (new AbilityCore.Builder("Gravi Pull", AbilityCategory.DEVIL_FRUITS, GraviPullAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(340.0F), ChargeComponent.getTooltip(60.0F), RangeComponent.getTooltip(16.0F, RangeComponent.RangeType.AOE)
/* 34 */       }).build();
/*    */   
/* 36 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::endChargeEvent);
/* 37 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */ 
/*    */   
/*    */   public GraviPullAbility(AbilityCore<GraviPullAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 46 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.chargeComponent.startCharging(entity, 60.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 54 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GRAVI_PULL_1.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 58 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GRAVI_PULL_2.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 60 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 16.0F);
/* 61 */     for (LivingEntity target : targets) {
/* 62 */       double offsetX = entity.func_226277_ct_() - target.func_226277_ct_();
/* 63 */       double offsetZ = entity.func_226281_cx_() - target.func_226281_cx_();
/* 64 */       AbilityHelper.setDeltaMovement((Entity)target, offsetX / 2.0D, (entity.func_226278_cu_() - target.func_226278_cu_()) / 4.0D, offsetZ / 2.0D);
/*    */     } 
/*    */     
/* 67 */     this.cooldownComponent.startCooldown(entity, 340.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\GraviPullAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */