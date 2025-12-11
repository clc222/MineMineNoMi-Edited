/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FlashAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "flash", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("The user creates a bright flash of light, blinding their opponents. Longer charges means a longer distance.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final int CHARGE_TIME = 40;
/*    */   private static final int MIN_RANGE = 2;
/*    */   private static final int MAX_RANGE = 16;
/* 35 */   public static final AbilityCore<FlashAbility> INSTANCE = (new AbilityCore.Builder("Flash", AbilityCategory.DEVIL_FRUITS, FlashAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(40.0F), RangeComponent.getTooltip(2.0F, 16.0F, RangeComponent.RangeType.AOE)
/* 38 */       }).setSourceElement(SourceElement.LIGHT)
/* 39 */     .build();
/*    */   
/*    */   private final ChargeComponent chargeComponent;
/*    */   private final AnimationComponent animationComponent;
/*    */   private final RangeComponent rangeComponent;
/*    */   
/*    */   public FlashAbility(AbilityCore<FlashAbility> core) {
/* 46 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() > 0.2D))).addTickEvent(this::duringChargeEvent).addEndEvent(this::stopChargeEvent); this.animationComponent = new AnimationComponent((IAbility)this);
/*    */     this.rangeComponent = new RangeComponent((IAbility)this);
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity player, IAbility ability) {
/* 55 */     this.chargeComponent.startCharging(player, 40.0F);
/*    */   }
/*    */   
/*    */   private void duringChargeEvent(LivingEntity player, IAbility ability) {
/* 59 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PIKA_CHARGING.get(), (Entity)player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_());
/*    */   }
/*    */   
/*    */   private void stopChargeEvent(LivingEntity player, IAbility ability) {
/* 63 */     AbilityHelper.reduceEffect(player.func_70660_b((Effect)ModEffects.FROZEN.get()), 10.0D);
/* 64 */     AbilityHelper.reduceEffect(player.func_70660_b((Effect)ModEffects.FROSTBITE.get()), 10.0D);
/* 65 */     AbilityHelper.reduceEffect(player.func_70660_b((Effect)ModEffects.CANDY_STUCK.get()), 10.0D);
/* 66 */     AbilityHelper.reduceEffect(player.func_70660_b((Effect)ModEffects.CANDLE_LOCK.get()), 10.0D);
/*    */     
/* 68 */     float radius = this.chargeComponent.getChargePercentage() * 16.0F;
/* 69 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(player, radius);
/*    */     
/* 71 */     for (LivingEntity target : targets) {
/* 72 */       target.func_195064_c(new EffectInstance(Effects.field_76440_q, 140, 3));
/*    */       
/* 74 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FLASH.get(), (Entity)player, target.func_226277_ct_(), target.func_226278_cu_() + target.func_70047_e(), target.func_226281_cx_());
/*    */     } 
/*    */     
/* 77 */     this.cooldownComponent.startCooldown(player, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\FlashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */