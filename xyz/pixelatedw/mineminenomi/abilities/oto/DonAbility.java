/*    */ package xyz.pixelatedw.mineminenomi.abilities.oto;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class DonAbility extends Ability {
/* 33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "don", new Pair[] {
/* 34 */         (Pair)ImmutablePair.of("The user plays the drum, creating a explosion inside all who hear it", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 60.0F;
/*    */   private static final float VOLUME = 3.0F;
/*    */   private static final int DISTANCE = 24;
/*    */   private static final float WIDTH = 3.0F;
/*    */   private static final float DAMAGE = 25.0F;
/*    */   private static final int ANIMATION_TICKS = 20;
/* 43 */   public static final AbilityCore<DonAbility> INSTANCE = (new AbilityCore.Builder("Don", AbilityCategory.DEVIL_FRUITS, DonAbility::new))
/* 44 */     .addDescriptionLine(DESCRIPTION)
/* 45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F), DealDamageComponent.getTooltip(25.0F), RangeComponent.getTooltip(24.0F, RangeComponent.RangeType.AOE)
/* 46 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 47 */     .setSourceType(new SourceType[] { SourceType.INTERNAL
/* 48 */       }).setSourceElement(SourceElement.EXPLOSION)
/* 49 */     .build();
/*    */   
/* 51 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 52 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*    */   
/*    */   public DonAbility(AbilityCore<DonAbility> core) {
/* 56 */     super(core);
/*    */     
/* 58 */     this.isNew = true;
/*    */     
/* 60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*    */     
/* 62 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 66 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DON.get(), SoundCategory.PLAYERS, 3.0F, 0.2F + entity.func_70681_au().nextFloat());
/*    */     
/* 68 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 24.0F, 3.0F);
/*    */     
/* 70 */     this.animationComponent.start(entity, ModAnimations.DON, 20);
/*    */     
/* 72 */     for (LivingEntity target : targets) {
/* 73 */       if (target.func_70644_a((Effect)ModEffects.SILENT.get())) {
/*    */         continue;
/*    */       }
/*    */       
/* 77 */       AbilityDamageSource source = (AbilityDamageSource)this.dealDamageComponent.getDamageSource(entity);
/*    */       
/* 79 */       source.setInternal();
/* 80 */       source.markIndirectDamage();
/*    */       
/* 82 */       if (this.dealDamageComponent.hurtTarget(entity, target, 25.0F, (DamageSource)source)) {
/* 83 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)target, target.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 1.0F);
/*    */         
/* 85 */         explosion.setStaticDamage(25.0F);
/* 86 */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 87 */         explosion.doExplosion();
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/*    */     
/* 93 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\oto\DonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */