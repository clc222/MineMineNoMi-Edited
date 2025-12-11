/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class KachiageHaisokuAbility
/*    */   extends PunchAbility2
/*    */ {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kachiage_haisoku", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("The user delivers a powerful kick to the opponent's chin, which is stronger inside water", null)
/*    */       });
/*    */ 
/*    */ 
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/*    */ 
/*    */ 
/*    */   
/*    */   private static final float DAMAGE = 20.0F;
/*    */ 
/*    */ 
/*    */   
/*    */   private static final float WATER_DAMAGE_MUL = 2.0F;
/*    */ 
/*    */ 
/*    */   
/* 43 */   public static final AbilityCore<KachiageHaisokuAbility> INSTANCE = (new AbilityCore.Builder("Kachiage Haisoku", AbilityCategory.RACIAL, KachiageHaisokuAbility::new))
/* 44 */     .addDescriptionLine(DESCRIPTION)
/* 45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ContinuousComponent.getTooltip(), FishmanKarateHelper.getWaterBuffedDamageStat(20.0F, 2.0F)
/* 46 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 47 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 48 */       }).setUnlockCheck(KachiageHaisokuAbility::canUnlock)
/* 49 */     .build();
/*    */   
/* 51 */   private float damage = 20.0F;
/*    */   
/*    */   public KachiageHaisokuAbility(AbilityCore<KachiageHaisokuAbility> core) {
/* 54 */     super(core);
/*    */     
/* 56 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 60 */     if (FishmanKarateHelper.isInWater(entity)) {
/* 61 */       this.damage = 40.0F;
/*    */     }
/* 63 */     this.damage = 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 68 */     if (!isContinuous()) {
/* 69 */       return 20.0F;
/*    */     }
/* 71 */     return this.damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 81 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 86 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 91 */     return 160.0F;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 95 */     IEntityStats props = EntityStatsCapability.get(user);
/* 96 */     return (props.isFishman() && props.getDoriki() >= 1500.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\KachiageHaisokuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */