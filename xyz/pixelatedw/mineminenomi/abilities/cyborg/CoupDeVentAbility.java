/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.CoupDeVentProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CoupDeVentAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "coup_de_vent", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Launches a powerful blast of compressed air that blows the opponent away.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 240.0F;
/*    */   private static final int COLA_REQUIRED = 30;
/* 32 */   public static final AbilityCore<CoupDeVentAbility> INSTANCE = (new AbilityCore.Builder("Coup de Vent", AbilityCategory.RACIAL, CoupDeVentAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), CyborgHelper.getColaTooltip(30.0F)
/* 35 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 36 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 37 */     .setSourceElement(SourceElement.SHOCKWAVE)
/* 38 */     .setUnlockCheck(CoupDeVentAbility::canUnlock)
/* 39 */     .build();
/*    */   
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public CoupDeVentAbility(AbilityCore<CoupDeVentAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 49 */     addCanUseCheck(CyborgHelper.hasEnoughCola(30));
/* 50 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 54 */     IEntityStats props = EntityStatsCapability.get(entity);
/*    */     
/* 56 */     this.projectileComponent.shoot(entity, 3.0F, 1.0F);
/*    */     
/* 58 */     props.alterCola(-30);
/* 59 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 60 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*    */     }
/*    */     
/* 63 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private CoupDeVentProjectile createProjectile(LivingEntity entity) {
/* 67 */     CoupDeVentProjectile proj = new CoupDeVentProjectile(entity.field_70170_p, entity, this);
/* 68 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 72 */     IEntityStats props = EntityStatsCapability.get(user);
/* 73 */     return props.isCyborg();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\CoupDeVentAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */