/*    */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
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
/*    */ public class AncientTrunkShotAbility extends PunchAbility2 {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ancient_trunk_shot", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Hit using the massive trunk.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 31 */   public static final AbilityCore<AncientTrunkShotAbility> INSTANCE = (new AbilityCore.Builder("Ancient Trunk Shot", AbilityCategory.DEVIL_FRUITS, AncientTrunkShotAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 34 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/* 39 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.MAMMOTH_GUARD.get(), new MorphInfo[] { (MorphInfo)ModMorphs.MAMMOTH_HEAVY.get() });
/*    */   
/*    */   public AncientTrunkShotAbility(AbilityCore<AncientTrunkShotAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 49 */     Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(5.0D, 1.0D, 5.0D);
/*    */     
/* 51 */     AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(-dirVec.field_72450_a, 0.65D, -dirVec.field_72449_c));
/* 52 */     target.func_230245_c_(false);
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 59 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 64 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 69 */     return 15.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 74 */     return 100.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\AncientTrunkShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */