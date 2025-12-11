/*     */ package xyz.pixelatedw.mineminenomi.abilities.oto;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BonAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bon", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("The user plucks a string created from their arm, creating a sound wave that internally damages all who hear it", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 60.0F;
/*     */   private static final float VOLUME = 3.5F;
/*     */   private static final int DISTANCE = 24;
/*     */   private static final float WIDTH = 3.0F;
/*     */   private static final float DAMAGE = 25.0F;
/*     */   private static final int ANIMATION_TICKS = 10;
/*  45 */   public static final AbilityCore<BonAbility> INSTANCE = (new AbilityCore.Builder("Bon", AbilityCategory.DEVIL_FRUITS, BonAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F), DealDamageComponent.getTooltip(25.0F), RangeComponent.getTooltip(24.0F, RangeComponent.RangeType.AOE)
/*  48 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  49 */     .setSourceType(new SourceType[] { SourceType.INTERNAL
/*  50 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/*  51 */     .build();
/*     */   
/*  53 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  54 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  55 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public BonAbility(AbilityCore<BonAbility> core) {
/*  58 */     super(core);
/*     */     
/*  60 */     this.isNew = true;
/*     */     
/*  62 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  64 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  68 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BON.get(), SoundCategory.PLAYERS, 3.5F, 0.2F + entity.func_70681_au().nextFloat());
/*     */     
/*  70 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 24.0F, 3.0F);
/*     */     
/*  72 */     this.animationComponent.start(entity, ModAnimations.BON, 10);
/*     */     
/*  74 */     for (LivingEntity target : targets) {
/*  75 */       if (target.func_70644_a((Effect)ModEffects.SILENT.get())) {
/*     */         continue;
/*     */       }
/*     */       
/*  79 */       AbilityDamageSource source = (AbilityDamageSource)this.dealDamageComponent.getDamageSource(entity);
/*     */       
/*  81 */       source.setInternal();
/*  82 */       source.setSlash();
/*  83 */       source.markIndirectDamage();
/*     */       
/*  85 */       if (this.dealDamageComponent.hurtTarget(entity, target, 25.0F, (DamageSource)source)) {
/*  86 */         Vector3d dist = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D).func_72432_b();
/*     */         
/*  88 */         double power = 4.5D;
/*  89 */         double xSpeed = -dist.field_72450_a * power;
/*  90 */         double zSpeed = -dist.field_72449_c * power;
/*     */         
/*  92 */         AbilityHelper.setDeltaMovement((Entity)target, -xSpeed, 0.1D, -zSpeed);
/*     */         
/*  94 */         for (int i = 0; i < 5; i++) {
/*  95 */           double offsetX = target.func_70681_au().nextDouble() / 2.0D;
/*  96 */           double offsetZ = target.func_70681_au().nextDouble() / 2.0D;
/*     */           
/*  98 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)entity.field_70170_p, target.func_226277_ct_() + offsetX, target.func_226278_cu_() + target.func_70047_e() + offsetX, target.func_226281_cx_() + offsetZ);
/*     */         } 
/*     */         
/* 101 */         WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197627_t, (ServerWorld)entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_() + target.func_70047_e(), target.func_226281_cx_());
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\oto\BonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */