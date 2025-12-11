/*    */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraBaraHoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class BaraBaraHoAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bara_bara_ho", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Launches the user's fist towards the enemy, if the user holds a weapon in hand this will increase the fist's damage as well.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 80;
/* 32 */   public static final AbilityCore<BaraBaraHoAbility> INSTANCE = (new AbilityCore.Builder("Bara Bara Ho", AbilityCategory.DEVIL_FRUITS, BaraBaraHoAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F)
/* 35 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 36 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 37 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 38 */       }).build();
/*    */   
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 41 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*    */   
/*    */   private int morphTick;
/*    */   
/*    */   public BaraBaraHoAbility(AbilityCore<BaraBaraHoAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.morphComponent });
/*    */     
/* 51 */     addCanUseCheck(BaraHelper::hasLimbs);
/*    */     
/* 53 */     addTickEvent(this::tickEvent);
/* 54 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void tickEvent(LivingEntity entity, IAbility ability) {
/* 58 */     if (!entity.field_70170_p.field_72995_K && this.morphComponent.isMorphed()) {
/* 59 */       if (this.morphTick > 0) {
/* 60 */         this.morphTick--;
/*    */       } else {
/*    */         
/* 63 */         this.morphComponent.stopMorph(entity);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 69 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/*    */     
/* 71 */     int projectileLife = this.projectileComponent.getShotProjectile().getLife();
/*    */     
/* 73 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.BARA_HO.get());
/* 74 */     this.morphTick = projectileLife;
/*    */     
/* 76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.NO_HANDS.get(), projectileLife, 0));
/*    */     
/* 78 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/*    */   }
/*    */   
/*    */   private BaraBaraHoProjectile createProjectile(LivingEntity entity) {
/* 82 */     BaraBaraHoProjectile proj = new BaraBaraHoProjectile(entity.field_70170_p, entity);
/*    */     
/* 84 */     ItemStack stack = entity.func_184614_ca();
/*    */     
/* 86 */     float extraDamage = 0.0F;
/* 87 */     if (ItemsHelper.isSword(stack)) {
/* 88 */       extraDamage = ItemsHelper.getItemDamage(stack);
/*    */     }
/*    */     
/* 91 */     proj.setDamage(proj.getDamage() + extraDamage);
/* 92 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraBaraHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */