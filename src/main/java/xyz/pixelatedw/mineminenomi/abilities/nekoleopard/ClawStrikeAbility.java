/*    */ package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class ClawStrikeAbility extends PunchAbility2 {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "claw_strike", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Hits using the user's sharp claws.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 100.0F;
/* 32 */   public static final AbilityCore<ClawStrikeAbility> INSTANCE = (new AbilityCore.Builder("Claw Strike", AbilityCategory.DEVIL_FRUITS, ClawStrikeAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 35 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 36 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 37 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 38 */       }).build();
/*    */   
/* 40 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.LEOPARD_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.LEOPARD_WALK.get() });
/*    */   
/*    */   public ClawStrikeAbility(AbilityCore<ClawStrikeAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 50 */     entity.field_70170_p.func_184133_a(null, target.func_233580_cy_(), (SoundEvent)ModSounds.SHIGAN_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
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
/*    */   public boolean isParallel() {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 72 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 77 */     return 100.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nekoleopard\ClawStrikeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */