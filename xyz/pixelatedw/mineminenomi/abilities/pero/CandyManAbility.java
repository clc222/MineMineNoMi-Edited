/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class CandyManAbility
/*    */   extends PunchAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candy_man", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Traps the target inside hardened candy, immobilizing and suffocating them.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 600;
/* 30 */   public static final AbilityCore<CandyManAbility> INSTANCE = (new AbilityCore.Builder("Candy Man", AbilityCategory.DEVIL_FRUITS, CandyManAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 33 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 34 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 35 */       }).build();
/*    */   
/*    */   public CandyManAbility(AbilityCore<CandyManAbility> core) {
/* 38 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 43 */     return 5.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 48 */     if (canActivate().test(entity)) {
/* 49 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.CANDY_STUCK.get(), 400, 1, false, false));
/*    */     }
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 57 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 62 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 67 */     return 600.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyManAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */