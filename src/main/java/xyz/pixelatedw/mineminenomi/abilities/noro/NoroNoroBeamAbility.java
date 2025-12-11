/*    */ package xyz.pixelatedw.mineminenomi.abilities.noro;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.noro.NoroNoroBeamProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class NoroNoroBeamAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "noro_noro_beam", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Shoots a beam of photons at the opponent, completely slowing them down (multiple hits stack the Slowness effect)", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 60.0F;
/*    */   private static final int ANIMATION_TICK = 10;
/* 29 */   public static final AbilityCore<NoroNoroBeamAbility> INSTANCE = (new AbilityCore.Builder("Noro Noro Beam", AbilityCategory.DEVIL_FRUITS, NoroNoroBeamAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .build();
/*    */   
/* 35 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 36 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public NoroNoroBeamAbility(AbilityCore<NoroNoroBeamAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     this.isNew = true;
/* 42 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 44 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 48 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER, 10);
/* 49 */     this.projectileComponent.shoot(entity, 4.0F, 1.0F);
/* 50 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.NORO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 51 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*    */   }
/*    */   
/*    */   private NoroNoroBeamProjectile createProjectile(LivingEntity entity) {
/* 55 */     NoroNoroBeamProjectile proj = new NoroNoroBeamProjectile(entity.field_70170_p, entity, this);
/* 56 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\noro\NoroNoroBeamAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */