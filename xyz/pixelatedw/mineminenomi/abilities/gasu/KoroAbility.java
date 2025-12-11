/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.gasu.KoroParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KoroAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "koro", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Removes all poison from self and nearby allies.", null), 
/* 32 */         (Pair)ImmutablePair.of("If used while %s is active, it'll remove all harmful effects for self and nearby allies.", new Object[] { ShinokuniAbility.INSTANCE })
/*    */       });
/* 34 */   private static final TargetsPredicate TARGETS_PREDICATE = (new TargetsPredicate()).testFriendlyFaction();
/*    */   
/*    */   private static final int COOLDOWN = 400;
/*    */   
/*    */   private static final float RANGE = 30.0F;
/* 39 */   public static final AbilityCore<KoroAbility> INSTANCE = (new AbilityCore.Builder("Koro", AbilityCategory.DEVIL_FRUITS, KoroAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), RangeComponent.getTooltip(30.0F, RangeComponent.RangeType.AOE)
/* 42 */       }).build();
/*    */   
/* 44 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/* 46 */   private KoroParticleEffect.Details particleDetails = new KoroParticleEffect.Details();
/*    */   
/*    */   public KoroAbility(AbilityCore<KoroAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 54 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   public void useEvent(LivingEntity player, IAbility ability) {
/* 58 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(player, 30.0F, TARGETS_PREDICATE);
/* 59 */     targets.add(player);
/* 60 */     ShinokuniAbility abl = (ShinokuniAbility)AbilityDataCapability.get(player).getEquippedAbility(ShinokuniAbility.INSTANCE);
/* 61 */     boolean hasShinokuniActive = (abl != null && abl.isContinuous());
/* 62 */     for (LivingEntity target : targets) {
/* 63 */       if (hasShinokuniActive) {
/* 64 */         for (EffectInstance inst : target.func_70651_bq()) {
/* 65 */           if (inst.func_188419_a() instanceof ModEffect && !((ModEffect)inst.func_188419_a()).isRemoveable()) {
/*    */             continue;
/*    */           }
/*    */           
/* 69 */           if (inst.func_188419_a().func_220303_e() == EffectType.HARMFUL && target.func_195063_d(inst.func_188419_a().getEffect())) {
/* 70 */             this.particleDetails.setEffect(inst);
/* 71 */             WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KORO.get(), (Entity)player, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), (ParticleEffect.Details)this.particleDetails);
/*    */           } 
/*    */         } 
/*    */         continue;
/*    */       } 
/* 76 */       if (target.func_70644_a(Effects.field_76436_u) && target.func_195063_d(Effects.field_76436_u)) {
/* 77 */         this.particleDetails.setEffect(target.func_70660_b(Effects.field_76436_u));
/* 78 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KORO.get(), (Entity)player, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), (ParticleEffect.Details)this.particleDetails);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 83 */     this.cooldownComponent.startCooldown(player, 400.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\KoroAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */