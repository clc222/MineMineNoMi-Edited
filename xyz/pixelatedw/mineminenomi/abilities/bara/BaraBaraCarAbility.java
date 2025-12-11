/*    */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class BaraBaraCarAbility extends MorphAbility2 {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bara_bara_car", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Turns the user's body into a car.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 1200;
/*    */   
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final int MAX_COOLDOWN = 1200;
/*    */   private static final float MAX_SPEED = 1.0F;
/*    */   private static final float SLIDE_POWER = 1.3F;
/* 38 */   public static final AbilityCore<BaraBaraCarAbility> INSTANCE = (new AbilityCore.Builder("Bara Bara Car", AbilityCategory.DEVIL_FRUITS, BaraBaraCarAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 1200.0F), ContinuousComponent.getTooltip(1200.0F)
/* 41 */       }).build();
/*    */   
/* 43 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Bara Bara Car Speed Modifier", 1.2D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 44 */   private static final AbilityAttributeModifier STEP_HEIGHT = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Bara Bara Car Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 46 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.BARA_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*    */   
/*    */   public BaraBaraCarAbility(AbilityCore<BaraBaraCarAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent });
/*    */     
/* 53 */     this.continuousComponent.addStartEvent(200, this::startContinuityEvent);
/* 54 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*    */     
/* 56 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 57 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/*    */     
/* 59 */     addCanUseCheck(BaraHelper::hasLimbs);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 64 */     return (MorphInfo)ModMorphs.BARA_CAR.get();
/*    */   }
/*    */ 
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {}
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 72 */     if (entity.func_233570_aj_() && entity.func_70051_ag() && (
/* 73 */       Math.abs(entity.func_213322_ci().func_82615_a()) < 0.2D || Math.abs(entity.func_213322_ci().func_82616_c()) < 0.2D)) {
/* 74 */       double x = MathHelper.func_151237_a(entity.func_213322_ci().func_82615_a() * 1.2999999523162842D, -1.0D, 1.0D);
/* 75 */       double z = MathHelper.func_151237_a(entity.func_213322_ci().func_82616_c() * 1.2999999523162842D, -1.0D, 1.0D);
/*    */       
/* 77 */       AbilityHelper.setDeltaMovement((Entity)entity, x, entity.func_213322_ci().func_82617_b(), z);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getContinuityHoldTime() {
/* 84 */     return WyHelper.secondsToTicks(60.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCooldownTicks() {
/* 89 */     return Math.max(100.0F, this.continuousComponent.getContinueTime());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraBaraCarAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */