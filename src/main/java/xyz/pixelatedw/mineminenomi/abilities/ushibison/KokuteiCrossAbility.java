/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushibison;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KokuteiCrossAbility extends PunchAbility2 {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kokutei_cross", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("The transformed user crosses their hooves to hit the opponent.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 140;
/* 33 */   public static final AbilityCore<KokuteiCrossAbility> INSTANCE = (new AbilityCore.Builder("Kokutei Cross", AbilityCategory.DEVIL_FRUITS, KokuteiCrossAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 36 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 37 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 38 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 39 */       }).build();
/*    */   
/* 41 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.BISON_HEAVY.get(), new MorphInfo[0]);
/*    */   
/*    */   public KokuteiCrossAbility(AbilityCore<KokuteiCrossAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 51 */     Vector3d speed = WyHelper.propulsion(entity, 1.5D, 1.5D);
/*    */     
/* 53 */     AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, entity.func_213322_ci().func_82617_b(), speed.field_72449_c);
/*    */     
/* 55 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KOKUTEI_CROSS.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
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
/*    */   public float getPunchDamage() {
/* 67 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 72 */     return 140.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 77 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushibison\KokuteiCrossAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */