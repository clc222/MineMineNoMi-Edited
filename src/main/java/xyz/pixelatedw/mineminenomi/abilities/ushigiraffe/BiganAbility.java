/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ public class BiganAbility extends PunchAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bigan", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Hits using the hardened giraffe nose.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 30 */   public static final AbilityCore<BiganAbility> INSTANCE = (new AbilityCore.Builder("Bigan", AbilityCategory.DEVIL_FRUITS, BiganAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 33 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 34 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 35 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 36 */       }).build();
/*    */   
/* 38 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.GIRAFFE_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.GIRAFFE_WALK.get() });
/*    */   
/*    */   public BiganAbility(AbilityCore<BiganAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 48 */     return 25.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 53 */     return 100.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 63 */     return entity -> this.continuousComponent.isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 68 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushigiraffe\BiganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */