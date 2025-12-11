/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChiyupopoAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "chiyupopo", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Releases dandelions made of tears that temporarily increase the healing rate of those around the user. This can only be applied once per person.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 16800.0F;
/*    */   private static final float RANGE = 15.0F;
/* 34 */   public static final AbilityCore<ChiyupopoAbility> INSTANCE = (new AbilityCore.Builder("Chiyupopo", AbilityCategory.DEVIL_FRUITS, ChiyupopoAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(16800.0F), RangeComponent.getTooltip(15.0F, RangeComponent.RangeType.AOE)
/* 37 */       }).build();
/*    */   
/* 39 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   private TargetsPredicate predicate;
/*    */   
/*    */   public ChiyupopoAbility(AbilityCore<ChiyupopoAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 49 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity player, IAbility ability) {
/* 53 */     EntityStatsCapability.get(player).setChiyuEffect(false);
/* 54 */     if (this.predicate == null) {
/* 55 */       this.predicate = (new TargetsPredicate()).testFriendlyFaction().selector(entity -> !EntityStatsCapability.get(entity).hadChiyuEffect());
/*    */     }
/*    */     
/* 58 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(player, 15.0F, this.predicate);
/*    */     
/* 60 */     if (ModEntityPredicates.getFriendlyFactions(player).test(player) && !EntityStatsCapability.get(player).hadChiyuEffect()) {
/* 61 */       targets.add(player);
/*    */     }
/*    */     
/* 64 */     for (LivingEntity entity : targets) {
/* 65 */       if (entity.func_195064_c(new EffectInstance((Effect)ModEffects.CHIYUPOPO.get(), 4800, 5))) {
/* 66 */         EntityStatsCapability.get(entity).setChiyuEffect(true);
/*    */       }
/*    */     } 
/*    */     
/* 70 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHIYUPOPO.get(), (Entity)player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_());
/*    */     
/* 72 */     this.cooldownComponent.startCooldown(player, 16800.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\ChiyupopoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */