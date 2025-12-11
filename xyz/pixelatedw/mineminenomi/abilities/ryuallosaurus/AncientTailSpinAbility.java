/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class AncientTailSpinAbility extends Ability {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ancient_tail_spin", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Spins around hitting all nearby enemies with the user's tail.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final float RANGE = 5.5F;
/*    */   private static final float DAMAGE = 15.0F;
/* 38 */   public static final AbilityCore<AncientTailSpinAbility> INSTANCE = (new AbilityCore.Builder("Ancient Tail Spin", AbilityCategory.DEVIL_FRUITS, AncientTailSpinAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 41 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(5.5F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(15.0F)
/* 42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 43 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 44 */       }).build();
/*    */   
/* 46 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 47 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 48 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 49 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.ALLOSAURUS_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.ALLOSAURUS_WALK.get() });
/*    */   
/*    */   public AncientTailSpinAbility(AbilityCore<AncientTailSpinAbility> core) {
/* 52 */     super(core);
/*    */     
/* 54 */     this.isNew = true;
/* 55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 57 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 61 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, entity.func_233580_cy_(), 5.5F);
/*    */     
/* 63 */     this.animationComponent.start(entity, ModAnimations.YAW_SPIN, 10);
/*    */     
/* 65 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.SPIN.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*    */     
/* 67 */     for (LivingEntity target : list) {
/* 68 */       if (this.dealDamageComponent.hurtTarget(entity, target, 15.0F)) {
/* 69 */         Vector3d dist = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D);
/* 70 */         double speedReduction = 2.0D;
/* 71 */         double xSpeed = -dist.field_72450_a / speedReduction;
/* 72 */         double zSpeed = -dist.field_72449_c / speedReduction;
/* 73 */         AbilityHelper.setDeltaMovement((Entity)target, -xSpeed, 0.10000000149011612D, -zSpeed);
/*    */       } 
/*    */     } 
/*    */     
/* 77 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryuallosaurus\AncientTailSpinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */