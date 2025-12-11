/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class WeaponSpinAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "weapon_spin", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Spins around hitting all nearby enemies with the held weapon.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 80;
/*    */   private static final float COOLDOWN = 240.0F;
/*    */   private static final float DAMAGE = 15.0F;
/*    */   private static final float RANGE = 4.5F;
/* 37 */   public static final AbilityCore<WeaponSpinAbility> INSTANCE = (new AbilityCore.Builder("Weapon Spin", AbilityCategory.STYLE, WeaponSpinAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(80.0F), DealDamageComponent.getTooltip(15.0F), RangeComponent.getTooltip(4.5F, RangeComponent.RangeType.AOE)
/* 40 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 41 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 42 */       }).build();
/*    */   
/* 44 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::onStartContinuousEvent).addTickEvent(100, this::onTickContinuousEvent).addEndEvent(100, this::onEndContinuousEvent);
/* 45 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 46 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 47 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public WeaponSpinAbility(AbilityCore<WeaponSpinAbility> core) {
/* 50 */     super(core);
/*    */     
/* 52 */     this.isNew = true;
/* 53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 55 */     addCanUseCheck(AbilityHelper::requiresBluntWeapon);
/* 56 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.continuousComponent.triggerContinuity(entity, 80.0F);
/*    */   }
/*    */   
/*    */   private void onStartContinuousEvent(LivingEntity entity, IAbility ability) {
/* 64 */     ItemStack stack = entity.func_184614_ca();
/* 65 */     stack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*    */     
/* 67 */     this.animationComponent.start(entity, ModAnimations.SPIN_TO_WIN);
/*    */   }
/*    */   
/*    */   private void onEndContinuousEvent(LivingEntity entity, IAbility ability) {
/* 71 */     this.animationComponent.stop(entity);
/* 72 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private void onTickContinuousEvent(LivingEntity entity, IAbility ability) {
/* 76 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, 4.5F);
/*    */     
/* 78 */     for (LivingEntity target : list) {
/* 79 */       this.dealDamageComponent.hurtTarget(entity, target, 15.0F);
/*    */     }
/*    */     
/* 82 */     entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 5, 2, false, false));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\WeaponSpinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */