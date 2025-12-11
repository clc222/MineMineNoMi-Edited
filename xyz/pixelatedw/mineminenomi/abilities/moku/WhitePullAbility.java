/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class WhitePullAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "white_pull", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Pulls all nearby entities surrounded by smoke towards the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final int RANGE = 100;
/* 31 */   public static final AbilityCore<WhitePullAbility> INSTANCE = (new AbilityCore.Builder("White Pull", AbilityCategory.DEVIL_FRUITS, WhitePullAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(100.0F, RangeComponent.RangeType.AOE)
/* 34 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 35 */     .build();
/*    */   
/* 37 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*    */   
/* 40 */   private ArrayList<LivingEntity> targetsToRemove = new ArrayList<>();
/*    */   
/*    */   public WhitePullAbility(AbilityCore<WhitePullAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     this.isNew = true;
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.continuousComponent });
/*    */     
/* 48 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   public void useEvent(LivingEntity entity, IAbility ability) {
/* 52 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*    */   }
/*    */   
/*    */   public void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 56 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 100.0F);
/*    */     
/* 58 */     for (LivingEntity target : targets) {
/* 59 */       if (target.func_70644_a((Effect)ModEffects.SMOKE.get())) {
/* 60 */         AbilityHelper.setDeltaMovement((Entity)target, entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_186678_a(0.5D));
/*    */         
/* 62 */         if (!this.targetsToRemove.contains(target)) {
/* 63 */           this.targetsToRemove.add(target);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 70 */     for (LivingEntity target : this.targetsToRemove) {
/* 71 */       target.func_195063_d((Effect)ModEffects.SMOKE.get());
/*    */     }
/*    */     
/* 74 */     this.targetsToRemove.clear();
/*    */     
/* 76 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhitePullAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */