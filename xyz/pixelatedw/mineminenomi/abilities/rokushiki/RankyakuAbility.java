/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RankyakuProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public class RankyakuAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "rankyaku", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("By kicking at a very high speed, the user launches an air blade at the opponent", null)
/*    */       });
/*    */   
/*    */   public static final float COOLDOWN = 240.0F;
/* 31 */   public static final AbilityCore<RankyakuAbility> INSTANCE = (new AbilityCore.Builder("Rankyaku", AbilityCategory.RACIAL, RankyakuAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 34 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 35 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 36 */     .setSourceElement(SourceElement.AIR)
/* 37 */     .setSourceType(new SourceType[] { SourceType.SLASH, SourceType.FIST, SourceType.INDIRECT
/* 38 */       }).setUnlockCheck(RankyakuAbility::canUnlock)
/* 39 */     .build();
/*    */   
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public RankyakuAbility(AbilityCore<RankyakuAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 49 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 53 */     this.projectileComponent.shoot(entity, 3.25F, 1.0F);
/* 54 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private RankyakuProjectile createProjectile(LivingEntity entity) {
/* 58 */     RankyakuProjectile proj = new RankyakuProjectile(entity.field_70170_p, entity);
/* 59 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 63 */     IEntityStats props = EntityStatsCapability.get(user);
/* 64 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 65 */     return (raceCheck && props.getDoriki() >= 5000.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\RankyakuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */