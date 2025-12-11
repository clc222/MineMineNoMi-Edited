/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class ChiyuHormoneAbility
/*    */   extends PunchAbility2 {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "chiyu_hormone", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user injects the target with special hormones to stimulate the body's immune system which will nullify poisons at the cost of the user's lifespan.", null), 
/* 25 */         (Pair)ImmutablePair.of("§aSHIFT-USE§r: User injects themselves", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 400;
/* 29 */   public static final AbilityCore<ChiyuHormoneAbility> INSTANCE = (new AbilityCore.Builder("Chiyu Hormone", AbilityCategory.DEVIL_FRUITS, ChiyuHormoneAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ContinuousComponent.getTooltip()
/* 32 */       }).build();
/*    */   
/*    */   public ChiyuHormoneAbility(AbilityCore<ChiyuHormoneAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*    */     
/* 39 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 43 */     if (entity.func_213453_ef()) {
/* 44 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.CHIYU_HORMONE.get(), 300, 0));
/* 45 */       this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 51 */     source.setBypassFriendlyDamage();
/* 52 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.CHIYU_HORMONE.get(), 300, 0));
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
/*    */   public float getPunchCooldown() {
/* 69 */     return 400.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\ChiyuHormoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */