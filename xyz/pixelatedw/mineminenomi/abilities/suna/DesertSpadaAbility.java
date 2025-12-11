/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertSpadaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class DesertSpadaAbility extends Ability {
/*    */   private static final float COOLDOWN_BONUS = 0.8F;
/*    */   private static final float DAMAGE_BONUS = 1.5F;
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "desert_spada", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("The user forms their hand into a blade and stabs it into the ground, creating a sand blade that destroys anything on its path.", null), 
/* 29 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s and the damage is increased by %s.", new Object[] {
/* 30 */             "§a" + Math.round(19.999998F) + "%§r", "§a" + 
/* 31 */             Math.round(Math.abs(-0.5F) * 100.0F) + "%§r"
/*    */           })
/*    */       });
/*    */   private static final int COOLDOWN = 160;
/* 35 */   public static final AbilityCore<DesertSpadaAbility> INSTANCE = (new AbilityCore.Builder("Desert Spada", AbilityCategory.DEVIL_FRUITS, DesertSpadaAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 38 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 39 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 40 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 41 */       }).build();
/*    */   
/* 43 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public DesertSpadaAbility(AbilityCore<DesertSpadaAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/*    */     
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 52 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/* 53 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*    */     
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DESERT_SPADA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 61 */     boolean fruitBoosted = SunaHelper.isFruitBoosted(entity);
/* 62 */     this.projectileComponent.shoot(entity, 3.0F, 1.0F);
/*    */     
/* 64 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 65 */     if (fruitBoosted) {
/* 66 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*    */     }
/*    */     
/* 69 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */   
/*    */   private DesertSpadaProjectile createProjectile(LivingEntity entity) {
/* 73 */     boolean fruitBoosted = SunaHelper.isFruitBoosted(entity);
/*    */     
/* 75 */     this.projectileComponent.getDamageBonusManager().removeBonus(SunaHelper.DESERT_DAMAGE_BONUS);
/* 76 */     if (fruitBoosted) {
/* 77 */       this.projectileComponent.getDamageBonusManager().addBonus(SunaHelper.DESERT_DAMAGE_BONUS, "Desert Damage Bonus", BonusOperation.MUL, 1.5F);
/*    */     }
/*    */     
/* 80 */     DesertSpadaProjectile proj = new DesertSpadaProjectile(entity.field_70170_p, entity);
/* 81 */     proj.setMaxLife(fruitBoosted ? 30 : 20);
/* 82 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertSpadaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */