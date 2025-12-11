/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ 
/*    */ public class KageKakumeiAbility
/*    */   extends PunchAbility2
/*    */ {
/*    */   private static final int SHADOWS_REQUIRED = 20;
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kage_kakumei", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("By hitting another entity the user impales §220§r shadows at once into them boosting their physical abilities", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/* 31 */   public static final AbilityCore<KageKakumeiAbility> INSTANCE = (new AbilityCore.Builder("Kage Kakumei", AbilityCategory.DEVIL_FRUITS, KageKakumeiAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip()
/* 34 */       }).build();
/*    */   
/*    */   public KageKakumeiAbility(AbilityCore<KageKakumeiAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/* 40 */     this.hitTriggerComponent.addTryHitEvent(80, this::hitEvent);
/*    */   }
/*    */   
/*    */   private HitTriggerComponent.HitResult hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 44 */     if (canActivate().test(entity)) {
/* 45 */       target.func_195064_c(new EffectInstance(Effects.field_76424_c, 400, 0));
/* 46 */       target.func_195064_c(new EffectInstance(Effects.field_76429_m, 400, 0));
/* 47 */       target.func_195064_c(new EffectInstance(Effects.field_76420_g, 400, 0));
/* 48 */       target.func_195064_c(new EffectInstance(Effects.field_180152_w, 400, 0));
/* 49 */       target.func_195064_c(new EffectInstance(Effects.field_76428_l, 400, 0));
/*    */       
/* 51 */       KageHelper.removeShadows(entity, 20);
/*    */       
/* 53 */       return HitTriggerComponent.HitResult.FAIL;
/*    */     } 
/*    */     
/* 56 */     return HitTriggerComponent.HitResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 66 */     return 200.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 71 */     return entity -> (this.continuousComponent.isContinuous() && KageHelper.hasEnoughShadows(entity, (IAbility)this, 20).isSuccess());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 76 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KageKakumeiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */