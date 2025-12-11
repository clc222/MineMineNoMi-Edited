/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SmokeLaunchAbility
/*    */   extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "smmoke_launch", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Launches all nearby entities surrounded by smoke in the sky.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/*    */   private static final int RANGE = 100;
/* 31 */   public static final AbilityCore<SmokeLaunchAbility> INSTANCE = (new AbilityCore.Builder("Smoke Launch", AbilityCategory.DEVIL_FRUITS, SmokeLaunchAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), RangeComponent.getTooltip(100.0F, RangeComponent.RangeType.AOE)
/* 34 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 35 */     .build();
/*    */   
/* 37 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public SmokeLaunchAbility(AbilityCore<SmokeLaunchAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 45 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   public void useEvent(LivingEntity entity, IAbility ability) {
/* 49 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 100.0F);
/* 50 */     for (LivingEntity target : targets) {
/* 51 */       if (target.func_70644_a((Effect)ModEffects.SMOKE.get())) {
/* 52 */         target.func_195063_d((Effect)ModEffects.SMOKE.get());
/* 53 */         target.func_195064_c(new EffectInstance(Effects.field_188424_y, 200, 1));
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\SmokeLaunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */