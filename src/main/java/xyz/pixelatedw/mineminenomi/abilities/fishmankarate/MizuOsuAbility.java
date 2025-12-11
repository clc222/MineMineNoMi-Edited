/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MizuOsuAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mizu_osu", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Spits water with enough force to push away any enemy in front of the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 160;
/*    */   private static final float RANGE = 2.0F;
/* 32 */   public static final AbilityCore<MizuOsuAbility> INSTANCE = (new AbilityCore.Builder("Mizu Osu", AbilityCategory.RACIAL, MizuOsuAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), RangeComponent.getTooltip(2.0F, RangeComponent.RangeType.LINE)
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/* 39 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public MizuOsuAbility(AbilityCore<MizuOsuAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 47 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 51 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.5F, 2.0F);
/*    */     
/* 53 */     for (LivingEntity target : targets) {
/* 54 */       Vector3d diff = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_186678_a(4.0D);
/* 55 */       AbilityHelper.setDeltaMovement((Entity)target, diff.field_72450_a, 0.2D, diff.field_72449_c);
/*    */     } 
/*    */     
/* 58 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.MIZU_OSU.get(), (Entity)entity, entity.func_226277_ct_(), 1.75D + entity.func_226278_cu_(), entity.func_226281_cx_());
/* 59 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\MizuOsuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */