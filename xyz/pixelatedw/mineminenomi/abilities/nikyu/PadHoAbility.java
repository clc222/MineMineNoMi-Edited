/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class PadHoAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "pad_ho", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Launches a paw-shaped shockwave at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 30;
/* 30 */   public static final AbilityCore<PadHoAbility> INSTANCE = (new AbilityCore.Builder("Pad Ho", AbilityCategory.DEVIL_FRUITS, PadHoAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(30.0F)
/* 33 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 34 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 35 */     .setSourceElement(SourceElement.SHOCKWAVE)
/* 36 */     .build();
/*    */   
/* 38 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 39 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public PadHoAbility(AbilityCore<PadHoAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 47 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER, 7);
/* 52 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PAD_HO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 53 */     this.projectileComponent.shoot(entity, 3.5F, 1.0F);
/* 54 */     this.cooldownComponent.startCooldown(entity, 30.0F);
/*    */   }
/*    */   
/*    */   private PadHoProjectile createProjectile(LivingEntity entity) {
/* 58 */     PadHoProjectile proj = new PadHoProjectile(entity.field_70170_p, entity, this);
/* 59 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\PadHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */