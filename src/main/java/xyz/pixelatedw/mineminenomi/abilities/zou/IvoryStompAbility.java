/*    */ package xyz.pixelatedw.mineminenomi.abilities.zou;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IvoryStompAbility extends PunchAbility2 {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ivory_stomp", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("A strong punch from a hybrid elephant form, which launches the enemy.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 160;
/* 32 */   public static final AbilityCore<IvoryStompAbility> INSTANCE = (new AbilityCore.Builder("Ivory Stomp", AbilityCategory.DEVIL_FRUITS, IvoryStompAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 35 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 36 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 37 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 38 */       }).build();
/*    */   
/* 40 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.ZOU_HEAVY.get(), new MorphInfo[0]);
/*    */   
/*    */   public IvoryStompAbility(AbilityCore<IvoryStompAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 50 */     Vector3d speed = WyHelper.propulsion(entity, 2.5D, 2.5D);
/* 51 */     AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, entity.func_213322_ci().func_82617_b() + 0.1D, speed.field_72449_c);
/*    */     
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 58 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 63 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 68 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 73 */     return 160.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zou\IvoryStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */