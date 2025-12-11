/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.vector.Vector3d;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class TailSpinAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tail_spin", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Spins around hitting all nearby enemies with the user's tail.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final float RANGE = 5.5F;
/*    */   private static final float DAMAGE = 10.0F;
/* 36 */   public static final AbilityCore<TailSpinAbility> INSTANCE = (new AbilityCore.Builder("Tail Spin", AbilityCategory.RACIAL, TailSpinAbility::new))
/* 37 */     .addDescriptionLine(DESCRIPTION)
/* 38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(5.5F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(10.0F)
/* 39 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 40 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 41 */       }).build();
/*    */   
/* 43 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 44 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 45 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*    */   
/*    */   public TailSpinAbility(AbilityCore<TailSpinAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*    */     
/* 53 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 57 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, entity.func_233580_cy_(), 5.5F);
/*    */     
/* 59 */     this.animationComponent.start(entity, ModAnimations.YAW_SPIN, 10);
/*    */     
/* 61 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.SPIN.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*    */     
/* 63 */     for (LivingEntity target : list) {
/* 64 */       if (this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/* 65 */         Vector3d dist = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D);
/* 66 */         double speedReduction = 2.0D;
/* 67 */         double xSpeed = -dist.field_72450_a / speedReduction;
/* 68 */         double zSpeed = -dist.field_72449_c / speedReduction;
/* 69 */         AbilityHelper.setDeltaMovement((Entity)target, -xSpeed, 0.10000000149011612D, -zSpeed);
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\TailSpinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */