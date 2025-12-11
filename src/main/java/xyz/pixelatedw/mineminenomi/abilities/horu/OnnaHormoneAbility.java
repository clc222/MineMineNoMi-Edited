/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class OnnaHormoneAbility
/*    */   extends PunchAbility2
/*    */ {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "onna_hormone", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("By injecting an enemy with special hormones, the user can inflict moderate debuffs on their enemies", null), 
/* 25 */         (Pair)ImmutablePair.of("§aSHIFT-USE§r: User injects themselves", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 400;
/* 29 */   public static final AbilityCore<OnnaHormoneAbility> INSTANCE = (new AbilityCore.Builder("Onna Hormone", AbilityCategory.DEVIL_FRUITS, OnnaHormoneAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ContinuousComponent.getTooltip()
/* 32 */       }).build();
/*    */   
/*    */   public OnnaHormoneAbility(AbilityCore<OnnaHormoneAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*    */     
/* 39 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 43 */     if (entity.func_213453_ef()) {
/* 44 */       entity.func_195064_c(new EffectInstance(Effects.field_76431_k, 300, 2));
/* 45 */       entity.func_195064_c(new EffectInstance(Effects.field_76437_t, 300, 1));
/* 46 */       entity.func_195064_c(new EffectInstance(Effects.field_76424_c, 300, 0));
/* 47 */       this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 53 */     target.func_195064_c(new EffectInstance(Effects.field_76431_k, 300, 2));
/* 54 */     target.func_195064_c(new EffectInstance(Effects.field_76437_t, 300, 1));
/* 55 */     target.func_195064_c(new EffectInstance(Effects.field_76424_c, 300, 0));
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
/*    */   public int getUseLimit() {
/* 67 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 72 */     return 400.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\OnnaHormoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */