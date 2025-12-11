/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.ThrowingWeaponEntity;
/*    */ 
/*    */ public class WeaponThrowAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "weapon_throw", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Throws the held weapon dealing damage based on the weapon's damage.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/* 29 */   public static final AbilityCore<WeaponThrowAbility> INSTANCE = (new AbilityCore.Builder("Weapon Throw", AbilityCategory.STYLE, WeaponThrowAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 34 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 35 */       }).build();
/*    */   
/* 37 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public WeaponThrowAbility(AbilityCore<WeaponThrowAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 45 */     addCanUseCheck(AbilityHelper::canUseWeaponAbilities);
/* 46 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 50 */     if (!entity.field_70170_p.field_72995_K) {
/* 51 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*    */     }
/* 53 */     this.projectileComponent.shoot(entity);
/* 54 */     ItemsHelper.removeItemStackFromInventory(entity, entity.func_184614_ca());
/* 55 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private ThrowingWeaponEntity createProjectile(LivingEntity entity) {
/* 59 */     ThrowingWeaponEntity proj = new ThrowingWeaponEntity(entity.field_70170_p, entity);
/* 60 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\WeaponThrowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */