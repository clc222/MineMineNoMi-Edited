/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HikenProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class HikenAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hiken", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Turns the user's fist into flames and launches it towards the target", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 360;
/* 28 */   public static final AbilityCore<HikenAbility> INSTANCE = (new AbilityCore.Builder("Hiken", AbilityCategory.DEVIL_FRUITS, HikenAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(360.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 33 */     .setSourceElement(SourceElement.FIRE)
/* 34 */     .build();
/*    */   
/* 36 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public HikenAbility(AbilityCore<HikenAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     this.isNew = true;
/*    */     
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 45 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*    */     
/* 47 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/*    */     
/* 53 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MERA_SFX.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*    */     
/* 55 */     this.cooldownComponent.startCooldown(entity, 360.0F);
/*    */   }
/*    */   
/*    */   private HikenProjectile createProjectile(LivingEntity entity) {
/* 59 */     HikenProjectile proj = new HikenProjectile(entity.field_70170_p, entity);
/* 60 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HikenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */