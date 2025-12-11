/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AxolotlHealAbility extends Ability {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "axolotl_heal", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Regenerates all friendlies nearby based on how many other Axolotls are around.", null) });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/*    */   
/*    */   static {
/* 37 */     TARGET_PREDICATE = (new TargetsPredicate()).testFriendlyFaction().selector(target -> (target instanceof net.minecraft.entity.player.PlayerEntity && DevilFruitCapability.get(target).hasDevilFruit(ModAbilities.SARA_SARA_NO_MI_AXOLOTL)));
/*    */   }
/*    */   private static final float RANGE = 15.0F; private static final TargetsPredicate TARGET_PREDICATE;
/* 40 */   public static final AbilityCore<AxolotlHealAbility> INSTANCE = (new AbilityCore.Builder("Axolotl Heal", AbilityCategory.DEVIL_FRUITS, AxolotlHealAbility::new))
/* 41 */     .addDescriptionLine(DESCRIPTION)
/* 42 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 43 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), RangeComponent.getTooltip(15.0F, RangeComponent.RangeType.AOE)
/* 44 */       }).build();
/*    */   
/* 46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 47 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.AXOLOTL_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.AXOLOTL_WALK.get() });
/*    */   
/*    */   public AxolotlHealAbility(AbilityCore<AxolotlHealAbility> core) {
/* 50 */     super(core);
/*    */     
/* 52 */     this.isNew = true;
/* 53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 15.0F, TARGET_PREDICATE);
/*    */ 
/*    */     
/* 62 */     int axolots = targets.size();
/* 63 */     axolots = Math.min(axolots, 3);
/* 64 */     int time = 100 + Math.min(axolots * 100, 300);
/*    */     
/* 66 */     for (LivingEntity target : targets) {
/* 67 */       target.func_195064_c(new EffectInstance(Effects.field_76428_l, time, axolots));
/*    */     }
/*    */     
/* 70 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHIYUPOPO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 72 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\AxolotlHealAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */