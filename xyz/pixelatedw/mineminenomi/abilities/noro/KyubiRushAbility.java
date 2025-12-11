/*    */ package xyz.pixelatedw.mineminenomi.abilities.noro;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KyubiRushAbility extends PunchAbility2 {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kyubi_rush", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("While the opponent is slowed, the user delivers a series of punches, which hits the opponent all at once (a stronger slowness effect causes more damage)", null) }); private static final float COOLDOWN = 100.0F;
/*    */   private static final float MIN_DAMAGE = 1.0F;
/*    */   private static final float MAX_DAMAGE = 100.0F;
/*    */   private static final AbilityDescriptionLine.IDescriptionLine STATS_TOOLTIP;
/*    */   
/*    */   static {
/* 38 */     STATS_TOOLTIP = ((entity, ability) -> {
/*    */         TranslationTextComponent translationTextComponent = new TranslationTextComponent(Attributes.field_233823_f_.func_233754_c_());
/*    */         ITextComponent attackStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent, 1.0F, 100.0F)).build().getStatDescription(2);
/*    */         StringBuilder sb = new StringBuilder();
/*    */         sb.append("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_STATS.getString() + "§r\n");
/*    */         sb.append(attackStatText.getString());
/*    */         return (ITextComponent)new StringTextComponent(sb.toString());
/*    */       });
/*    */   }
/*    */ 
/*    */   
/* 49 */   public static final AbilityCore<KyubiRushAbility> INSTANCE = (new AbilityCore.Builder("Kyubi Rush", AbilityCategory.DEVIL_FRUITS, KyubiRushAbility::new))
/* 50 */     .addDescriptionLine(DESCRIPTION)
/* 51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), STATS_TOOLTIP
/* 52 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 53 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 54 */       }).build();
/*    */   
/* 56 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*    */   
/*    */   public KyubiRushAbility(AbilityCore<KyubiRushAbility> core) {
/* 59 */     super(core);
/*    */     
/* 61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 66 */     float damageFromSlowness = 0.0F;
/*    */     
/* 68 */     if (target.func_70644_a((Effect)ModEffects.NORO_SLOWNESS.get())) {
/* 69 */       damageFromSlowness = Math.min(target.func_70660_b((Effect)ModEffects.NORO_SLOWNESS.get()).func_76459_b() / 15.0F, 100.0F);
/* 70 */       int newTime = target.func_70660_b((Effect)ModEffects.NORO_SLOWNESS.get()).func_76459_b() / 2;
/* 71 */       int newAmplifier = Math.min(target.func_70660_b((Effect)ModEffects.NORO_SLOWNESS.get()).func_76458_c() - 2, 0);
/* 72 */       target.func_195063_d((Effect)ModEffects.NORO_SLOWNESS.get());
/* 73 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.NORO_SLOWNESS.get(), newTime, newAmplifier));
/*    */     } else {
/*    */       
/* 76 */       damageFromSlowness = 1.0F;
/*    */     } 
/*    */     
/* 79 */     WyHelper.spawnDamageIndicatorParticles(entity.field_70170_p, target, Math.round(damageFromSlowness));
/* 80 */     this.dealDamageComponent.hurtTarget(entity, target, damageFromSlowness, (DamageSource)source.ignore());
/*    */     
/* 82 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 87 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 92 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 97 */     return 100.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\noro\KyubiRushAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */