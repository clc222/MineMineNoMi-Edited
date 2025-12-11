/*    */ package xyz.pixelatedw.mineminenomi.abilities.doa;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
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
/*    */ public class KaitenDoorAbility
/*    */   extends PunchAbility2 {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kaiten_door", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Turn the head of your opponent into a rotating door to confuse and disorient them.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/* 31 */   public static final AbilityCore<KaitenDoorAbility> INSTANCE = (new AbilityCore.Builder("Kaiten Door", AbilityCategory.DEVIL_FRUITS, KaitenDoorAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 34 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 35 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 36 */       }).build();
/*    */   
/*    */   public KaitenDoorAbility(AbilityCore<KaitenDoorAbility> core) {
/* 39 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 44 */     return 4.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 49 */     if (canActivate().test(entity)) {
/* 50 */       target.func_195064_c(new EffectInstance(Effects.field_76431_k, 100, 1, false, false));
/* 51 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.DOOR_HEAD.get(), 100, 0, false, false));
/*    */       
/* 53 */       this.continuousComponent.stopContinuity(entity);
/*    */     } 
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 61 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 66 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 71 */     return 200.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doa\KaitenDoorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */