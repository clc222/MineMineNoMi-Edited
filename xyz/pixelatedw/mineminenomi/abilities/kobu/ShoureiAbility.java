/*    */ package xyz.pixelatedw.mineminenomi.abilities.kobu;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ShoureiAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shourei", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Increases other people's fighting spirit and physical strength by simply encouraging them with words.", null)
/*    */       });
/* 29 */   private static final TargetsPredicate TARGETS_CHECK = (new TargetsPredicate()).testFriendlyFaction();
/*    */   
/*    */   private static final int COOLDOWN = 1200;
/*    */   
/*    */   private static final int RANGE = 20;
/* 34 */   public static final AbilityCore<ShoureiAbility> INSTANCE = (new AbilityCore.Builder("Shourei", AbilityCategory.DEVIL_FRUITS, ShoureiAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1200.0F), RangeComponent.getTooltip(20.0F, RangeComponent.RangeType.AOE)
/* 37 */       }).build();
/*    */   
/* 39 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public ShoureiAbility(AbilityCore<ShoureiAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 47 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 51 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 20.0F, TARGETS_CHECK);
/*    */     
/* 53 */     for (LivingEntity target : targets) {
/* 54 */       target.func_195064_c(new EffectInstance(Effects.field_76420_g, 600, 1, true, false, true));
/* 55 */       target.func_195064_c(new EffectInstance(Effects.field_76424_c, 600, 1, true, false, true));
/* 56 */       target.func_195064_c(new EffectInstance(Effects.field_76429_m, 600, 1, true, false, true));
/* 57 */       target.func_195064_c(new EffectInstance(Effects.field_76428_l, 600, 0, true, false, true));
/*    */       
/* 59 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SHOUREI.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*    */     } 
/*    */     
/* 62 */     this.cooldownComponent.startCooldown(entity, 1200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kobu\ShoureiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */