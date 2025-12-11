/*    */ package xyz.pixelatedw.mineminenomi.abilities.mogu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class MoguraBananaAbility extends PunchAbility2 {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mogura_banana", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Hit the enemy with big mole claws to launch them away", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 120.0F;
/* 31 */   public static final AbilityCore<MoguraBananaAbility> INSTANCE = (new AbilityCore.Builder("Mogura Banana", AbilityCategory.DEVIL_FRUITS, MoguraBananaAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 34 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/* 39 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.MOGU_HEAVY.get(), new MorphInfo[0]);
/*    */   
/*    */   public MoguraBananaAbility(AbilityCore<MoguraBananaAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 49 */     Vector3d diff = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(7.0D, 1.0D, 7.0D);
/* 50 */     AbilityHelper.setDeltaMovement((Entity)target, -diff.field_72450_a, entity.func_213322_ci().func_82617_b() + 0.1D, -diff.field_72449_c);
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
/*    */   public float getPunchDamage() {
/* 67 */     return 15.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 72 */     return 120.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mogu\MoguraBananaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */