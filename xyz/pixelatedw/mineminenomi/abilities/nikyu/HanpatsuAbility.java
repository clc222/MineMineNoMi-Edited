/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanpatsuAbility extends PunchAbility2 {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hanpatsu", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Anyone the user punches gets sent flying far into the air", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 32 */   public static final AbilityCore<HanpatsuAbility> INSTANCE = (new AbilityCore.Builder("Hanpatsu", AbilityCategory.DEVIL_FRUITS, HanpatsuAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 35 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/*    */   public HanpatsuAbility(AbilityCore<HanpatsuAbility> core) {
/* 40 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 45 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 50 */     if (canActivate().test(entity)) {
/* 51 */       double xPower = WyHelper.randomDouble() * 400.0D;
/* 52 */       if (xPower >= 0.0D) {
/* 53 */         xPower = MathHelper.func_151237_a(xPower, 200.0D, 400.0D);
/*    */       } else {
/*    */         
/* 56 */         xPower = MathHelper.func_151237_a(xPower, -400.0D, -200.0D);
/*    */       } 
/*    */       
/* 59 */       double zPower = WyHelper.randomDouble() * 400.0D;
/* 60 */       if (zPower >= 0.0D) {
/* 61 */         zPower = MathHelper.func_151237_a(zPower, 200.0D, 400.0D);
/*    */       } else {
/*    */         
/* 64 */         zPower = MathHelper.func_151237_a(zPower, -400.0D, -200.0D);
/*    */       } 
/*    */       
/* 67 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.LAUNCHED.get(), 1200, 0));
/* 68 */       AbilityHelper.setDeltaMovement((Entity)target, xPower, 10.0D, zPower);
/* 69 */       target.field_70143_R = 0.0F;
/*    */       
/* 71 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PAD_HO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.75F);
/*    */       
/* 73 */       this.continuousComponent.stopContinuity(entity);
/*    */     } 
/*    */     
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
/* 91 */     return 100.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\HanpatsuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */