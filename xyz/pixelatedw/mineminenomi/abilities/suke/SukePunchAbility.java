/*    */ package xyz.pixelatedw.mineminenomi.abilities.suke;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SukePunchAbility
/*    */   extends PunchAbility2 {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "suke_punch", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Turns an entity's entire body invisible after hitting them.", null)
/*    */       });
/* 26 */   public static final AbilityCore<SukePunchAbility> INSTANCE = (new AbilityCore.Builder("Suke Punch", AbilityCategory.DEVIL_FRUITS, SukePunchAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, ContinuousComponent.getTooltip()
/* 29 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 30 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 31 */       }).build();
/*    */   
/*    */   public SukePunchAbility(AbilityCore<SukePunchAbility> core) {
/* 34 */     super(core);
/*    */     
/* 36 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 41 */     if (target.func_70644_a((Effect)ModEffects.SUKE_INVISIBILITY.get())) {
/* 42 */       target.func_195063_d((Effect)ModEffects.SUKE_INVISIBILITY.get());
/*    */     } else {
/* 44 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.SUKE_INVISIBILITY.get(), 2400, 0, false, false, true));
/*    */     } 
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 52 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 57 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 62 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suke\SukePunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */