/*    */ package xyz.pixelatedw.mineminenomi.abilities.zou;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class GreatStompAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "great_stomp", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("By stomping the ground as a full-form elephant, the user creates a shockwave.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/*    */   private static final int RANGE = 10;
/*    */   private static final float DAMAGE = 15.0F;
/* 34 */   public static final AbilityCore<GreatStompAbility> INSTANCE = (new AbilityCore.Builder("Great Stomp", AbilityCategory.DEVIL_FRUITS, GreatStompAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 37 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 38 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 39 */     .build();
/*    */   
/* 41 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 42 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 43 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.ZOU_GUARD.get(), new MorphInfo[0]);
/*    */   
/*    */   public GreatStompAbility(AbilityCore<GreatStompAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 55 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/*    */     
/* 57 */     ModDamageSource source = ((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).markIndirectDamage();
/* 58 */     for (LivingEntity target : targets) {
/* 59 */       if (this.dealDamageComponent.hurtTarget(entity, target, 15.0F, (DamageSource)source)) {
/* 60 */         target.func_70107_b(target.func_226277_ct_(), target.func_226278_cu_() + 3.0D, target.func_226281_cx_());
/*    */       }
/*    */     } 
/*    */     
/* 64 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GREAT_STOMP.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 66 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zou\GreatStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */