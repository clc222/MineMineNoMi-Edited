/*    */ package xyz.pixelatedw.mineminenomi.abilities.kilo;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
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
/*    */ 
/*    */ public class KiloPunch5000Ability
/*    */   extends PunchAbility2
/*    */ {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "5000_kilo_press", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Delivers a 5000 kilo punch, the user is slowed down due to the extra kilos", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 140.0F;
/* 32 */   public static final AbilityCore<KiloPunch5000Ability> INSTANCE = (new AbilityCore.Builder("5000 Kilo Punch", AbilityCategory.DEVIL_FRUITS, KiloPunch5000Ability::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/* 39 */   private static final AbilityAttributeModifier MOVEMENT_MODIFIER = new AbilityAttributeModifier(UUID.fromString("969f4880-faf9-41e9-bdae-26a57422254a"), INSTANCE, "Kilo Punch 5000 Movement Modifier", -0.01D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public KiloPunch5000Ability(AbilityCore<KiloPunch5000Ability> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)MOVEMENT_MODIFIER, canActivate());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 54 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 59 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 69 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 74 */     return 140.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kilo\KiloPunch5000Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */