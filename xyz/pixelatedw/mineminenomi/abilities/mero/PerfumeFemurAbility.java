/*    */ package xyz.pixelatedw.mineminenomi.abilities.mero;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PerfumeFemurAbility extends PunchAbility2 {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "perfume_femur", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Turns enemies hit by the user into stone.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 280;
/* 32 */   public static final AbilityCore<PerfumeFemurAbility> INSTANCE = (new AbilityCore.Builder("Perfume Femur", AbilityCategory.DEVIL_FRUITS, PerfumeFemurAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(280.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/*    */   public PerfumeFemurAbility(AbilityCore<PerfumeFemurAbility> core) {
/* 40 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 45 */     return 10.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 50 */     if (canActivate().test(entity)) {
/* 51 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.LOVESTRUCK.get(), 200, 1));
/* 52 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PERFUME_FEMUR.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*    */       
/* 54 */       this.continuousComponent.stopContinuity(entity);
/*    */     } 
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 62 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 67 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 72 */     return 280.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\PerfumeFemurAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */