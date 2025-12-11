/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.StrongRightProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class StrongRightAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "strong_right", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("The user punches the opponent with an extensible short range metal fist.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 100.0F;
/* 31 */   public static final AbilityCore<StrongRightAbility> INSTANCE = (new AbilityCore.Builder("Strong Right", AbilityCategory.RACIAL, StrongRightAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 34 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 35 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).setUnlockCheck(StrongRightAbility::canUnlock)
/* 38 */     .build();
/*    */   
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   private boolean isShot;
/*    */   private AbilityProjectileEntity projectile;
/*    */   
/*    */   public StrongRightAbility(AbilityCore<StrongRightAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 51 */     addCanUseCheck(BaraHelper::hasLimbs);
/*    */     
/* 53 */     addUseEvent(this::useEvent);
/* 54 */     addTickEvent(this::tickEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.projectileComponent.shoot(entity);
/* 59 */     this.projectile = this.projectileComponent.getShotProjectile();
/* 60 */     this.isShot = true;
/* 61 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.NO_HANDS.get(), 200, 0));
/* 62 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private void tickEvent(LivingEntity entity, IAbility ability) {
/* 66 */     if (this.isShot) {
/* 67 */       if (this.projectile != null && !this.projectile.func_70089_S()) {
/* 68 */         this.projectile.func_70106_y();
/* 69 */         this.projectile = null;
/*    */       } 
/* 71 */       entity.func_195063_d((Effect)ModEffects.NO_HANDS.get());
/* 72 */       this.isShot = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   private StrongRightProjectile createProjectile(LivingEntity entity) {
/* 77 */     StrongRightProjectile proj = new StrongRightProjectile(entity.field_70170_p, entity, this);
/* 78 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 82 */     IEntityStats props = EntityStatsCapability.get(user);
/* 83 */     return props.isCyborg();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\StrongRightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */