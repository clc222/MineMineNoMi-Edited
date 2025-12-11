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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class FreshFireAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "fresh_fire", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("The user heats up and breathes fire like a flamethrower at the opponenty.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 100.0F;
/*    */   private static final int COLA_REQUIRED = 1;
/*    */   private static final int MAX_FIRE_BURSTS = 10;
/* 35 */   public static final AbilityCore<FreshFireAbility> INSTANCE = (new AbilityCore.Builder("Fresh Fire", AbilityCategory.RACIAL, FreshFireAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), CyborgHelper.getColaTooltip(10.0F)
/* 38 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 39 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 40 */     .setSourceElement(SourceElement.FIRE)
/* 41 */     .setUnlockCheck(FreshFireAbility::canUnlock)
/* 42 */     .build();
/*    */   
/* 44 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent);
/* 45 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 46 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::triggerRepeaterEvent).addStopEvent(100, this::stopRepeaterEvent);
/*    */   
/*    */   public FreshFireAbility(AbilityCore<FreshFireAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.repeaterComponent });
/*    */     
/* 54 */     addCanUseCheck(CyborgHelper.hasEnoughCola(1));
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     if (this.continuousComponent.isContinuous()) {
/* 60 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/* 64 */     this.continuousComponent.startContinuity(entity, -1.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 68 */     this.repeaterComponent.start(entity, 10, 3);
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 72 */     IEntityStats props = EntityStatsCapability.get(entity);
/*    */     
/* 74 */     this.projectileComponent.shoot(entity, 2.0F, 5.0F);
/*    */     
/* 76 */     props.alterCola(-1);
/* 77 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 78 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 83 */     this.continuousComponent.stopContinuity(entity);
/* 84 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private FreshFireProjectile createProjectile(LivingEntity entity) {
/* 88 */     FreshFireProjectile proj = new FreshFireProjectile(entity.field_70170_p, entity, this);
/* 89 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 93 */     IEntityStats props = EntityStatsCapability.get(user);
/* 94 */     return props.isCyborg();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\FreshFireAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */