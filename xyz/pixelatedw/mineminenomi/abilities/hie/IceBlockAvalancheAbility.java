/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockAvalancheProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IceBlockAvalancheAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ice_block_avalanche", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Creates a sharp blade made of compressed ice", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 360;
/*    */   private static final int CHARGE_TIME = 100;
/* 31 */   public static final AbilityCore<IceBlockAvalancheAbility> INSTANCE = (new AbilityCore.Builder("Ice Block: Avalanche", AbilityCategory.DEVIL_FRUITS, IceBlockAvalancheAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(360.0F), ChargeComponent.getTooltip(100.0F)
/* 34 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 35 */     .setSourceElement(SourceElement.ICE)
/* 36 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 37 */       }).build();
/*    */   
/* 39 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::startChargeEvent).addTickEvent(100, this::tickChargeEvent).addEndEvent(100, this::stopChargeEvent);
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   private IceBlockAvalancheProjectile proj;
/*    */   
/*    */   public IceBlockAvalancheAbility(AbilityCore<IceBlockAvalancheAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 50 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.chargeComponent.startCharging(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity player, IAbility ability) {
/* 58 */     RayTraceResult ray = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
/* 59 */     removeDuplicate();
/* 60 */     this.proj = (IceBlockAvalancheProjectile)this.projectileComponent.getNewProjectile(player);
/* 61 */     this.proj.func_70107_b(ray.func_216347_e().func_82615_a(), ray.func_216347_e().func_82617_b() + 20.0D, ray.func_216347_e().func_82616_c());
/* 62 */     AbilityHelper.setDeltaMovement((Entity)this.proj, 0.0D, 0.0D, 0.0D);
/* 63 */     player.field_70170_p.func_217376_c((Entity)this.proj);
/*    */   }
/*    */   
/*    */   private void tickChargeEvent(LivingEntity entity, IAbility ability) {
/* 67 */     if (entity != null && this.proj != null && this.chargeComponent.getChargeTime() % 2.0F == 0.0F) {
/* 68 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ICE_BLOCK_AVALANCHE.get(), (Entity)entity, this.proj.func_226277_ct_(), this.proj.func_226278_cu_(), this.proj.func_226281_cx_());
/*    */     }
/*    */   }
/*    */   
/*    */   private void stopChargeEvent(LivingEntity entity, IAbility ability) {
/* 73 */     if (this.proj == null) {
/*    */       return;
/*    */     }
/*    */     
/* 77 */     this.proj.finalized = true;
/* 78 */     AbilityHelper.setDeltaMovement((Entity)this.proj, 0.0D, -2.0D, 0.0D);
/* 79 */     this.cooldownComponent.startCooldown(entity, 360.0F);
/*    */   }
/*    */   
/*    */   private void removeDuplicate() {
/* 83 */     if (this.proj != null && 
/* 84 */       this.proj.isAddedToWorld()) {
/* 85 */       this.proj.func_70106_y();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private IceBlockAvalancheProjectile createProjectile(LivingEntity entity) {
/* 91 */     IceBlockAvalancheProjectile proj = new IceBlockAvalancheProjectile(entity.field_70170_p, entity);
/* 92 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceBlockAvalancheAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */