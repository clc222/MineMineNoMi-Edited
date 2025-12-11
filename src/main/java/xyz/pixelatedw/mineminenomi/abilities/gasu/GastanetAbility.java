/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GastanetAbility extends Ability {
/* 34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gastanet", new Pair[] {
/* 35 */         (Pair)ImmutablePair.of("The user creates an explosion by detonating gas with their hands poisoning every enemy around the user.", null), 
/* 36 */         (Pair)ImmutablePair.of("If %s is active the nearby entities will instead get affected by whatever effect the user has at the moment. Allies will receive benefic effects while enemies will receive negative effects.", new Object[] { ShinokuniAbility.INSTANCE })
/*    */       });
/* 38 */   private static final UUID SHINOKUNI_RANGE_BONUS = UUID.fromString("05f3fc04-68e5-4f7b-a513-7237ad6fd643");
/* 39 */   private static final TargetsPredicate ALL_TARGETS = new TargetsPredicate();
/*    */   
/*    */   private static final int COOLDOWN = 160;
/*    */   
/*    */   private static final int RANGE = 8;
/* 44 */   public static final AbilityCore<GastanetAbility> INSTANCE = (new AbilityCore.Builder("Gastanet", AbilityCategory.DEVIL_FRUITS, GastanetAbility::new))
/* 45 */     .addDescriptionLine(DESCRIPTION)
/* 46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), RangeComponent.getTooltip(8.0F, RangeComponent.RangeType.AOE)
/* 47 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 48 */     .setSourceElement(SourceElement.EXPLOSION)
/* 49 */     .build();
/*    */   
/* 51 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public GastanetAbility(AbilityCore<GastanetAbility> core) {
/* 54 */     super(core);
/*    */     
/* 56 */     this.isNew = true;
/* 57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 59 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 63 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 64.0D);
/* 64 */     ExplosionAbility explosion = AbilityHelper.newExplosion(null, entity.field_70170_p, 
/* 65 */         (mop.func_216347_e()).field_72450_a, 
/* 66 */         (mop.func_216347_e()).field_72448_b, 
/* 67 */         (mop.func_216347_e()).field_72449_c, 5.0F);
/* 68 */     explosion.setStaticDamage(40.0F);
/* 69 */     explosion.setDestroyBlocks(false);
/* 70 */     explosion.doExplosion();
/*    */     
/* 72 */     this.rangeComponent.getBonusManager().removeBonus(SHINOKUNI_RANGE_BONUS);
/* 73 */     if (((MorphInfo)ModMorphs.SHINOKUNI.get()).isActive(entity)) {
/* 74 */       this.rangeComponent.getBonusManager().addBonus(SHINOKUNI_RANGE_BONUS, "Shinokuni Range Bonus", BonusOperation.ADD, 4.0F);
/*    */     }
/*    */     
/* 77 */     if (((MorphInfo)ModMorphs.SHINOKUNI.get()).isActive(entity)) {
/* 78 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 8.0F, ALL_TARGETS);
/* 79 */       targets.forEach(target -> ((ShinokuniAbility)AbilityDataCapability.get(entity).getEquippedAbility(ShinokuniAbility.INSTANCE)).applyEffects(entity, target));
/*    */     } else {
/*    */       
/* 82 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 8.0F);
/* 83 */       targets.forEach(target -> target.func_195064_c(new EffectInstance(Effects.field_76436_u, 200, 5)));
/*    */     } 
/*    */     
/* 86 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GASTANET.get(), (Entity)entity, (mop.func_216347_e()).field_72450_a, (mop.func_216347_e()).field_72448_b, (mop.func_216347_e()).field_72449_c);
/* 87 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GastanetAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */