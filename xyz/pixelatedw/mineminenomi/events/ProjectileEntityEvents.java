/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiInfusionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ProjectileShootEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.IProjectileExtras;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncProjectileExtrasPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ProjectileEntityEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(PlayerEvent.StartTracking event) {
/*  36 */     if (event.getTarget() instanceof ProjectileEntity && !(event.getTarget() instanceof AbilityProjectileEntity)) {
/*  37 */       ProjectileEntity proj = (ProjectileEntity)event.getTarget();
/*     */       
/*  39 */       setProjectileHakiData(proj, proj.func_234616_v_());
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onProjectileShoot(ProjectileShootEvent event) {
/*  45 */     AbilityProjectileEntity proj = event.getProjectile();
/*     */     
/*  47 */     if (proj == null || proj.getThrower() == null) {
/*     */       return;
/*     */     }
/*     */     
/*  51 */     IProjectileExtras props = ProjectileExtrasCapability.get((Entity)proj);
/*     */     
/*  53 */     if (props == null) {
/*     */       return;
/*     */     }
/*     */     
/*  57 */     LivingEntity thrower = proj.getThrower();
/*     */     
/*  59 */     setProjectileHakiData((ProjectileEntity)proj, (Entity)thrower);
/*     */     
/*  61 */     props.setWeaponUsed(thrower.func_184614_ca());
/*     */   }
/*     */   
/*     */   private static void setProjectileHakiData(ProjectileEntity proj, Entity owner) {
/*  65 */     if (owner == null || !(owner instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/*     */     
/*  69 */     LivingEntity thrower = (LivingEntity)owner;
/*     */     
/*  71 */     IProjectileExtras projectileExtrasProps = ProjectileExtrasCapability.get((Entity)proj);
/*  72 */     IAbilityData abilityDataProps = AbilityDataCapability.get(thrower);
/*     */     
/*  74 */     if (projectileExtrasProps == null || abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/*  78 */     HaoshokuHakiInfusionAbility haoshokuHakiInfusion = (HaoshokuHakiInfusionAbility)abilityDataProps.getEquippedAbility(HaoshokuHakiInfusionAbility.INSTANCE);
/*     */     
/*  80 */     boolean hasBusoshokuImbuingActive = HakiHelper.hasImbuingActive(thrower, false, false);
/*  81 */     boolean hasBusoshokuShroudingActive = HakiHelper.hasAdvancedBusoActive(thrower);
/*  82 */     boolean hasHaoshokuInfusionActive = (haoshokuHakiInfusion != null && haoshokuHakiInfusion.isContinuous());
/*     */     
/*  84 */     projectileExtrasProps.setProjectileBusoshokuImbued(hasBusoshokuImbuingActive);
/*  85 */     projectileExtrasProps.setProjectileBusoshokuShrouded(hasBusoshokuShroudingActive);
/*  86 */     projectileExtrasProps.setProjectileHaoshokuInfused(hasHaoshokuInfusionActive);
/*     */     
/*  88 */     float piercing = 0.0F;
/*     */     
/*  90 */     if (proj instanceof AbilityProjectileEntity) {
/*  91 */       piercing = ((AbilityProjectileEntity)proj).getArmorPiercing();
/*     */     }
/*     */     
/*  94 */     piercing = HakiHelper.hasBusoInternalDestructionActive(thrower) ? 1.0F : piercing;
/*     */     
/*  96 */     if (proj instanceof AbilityProjectileEntity) {
/*  97 */       ((AbilityProjectileEntity)proj).setArmorPiercing(piercing);
/*     */     }
/*     */     
/* 100 */     projectileExtrasProps.setPiercing(piercing);
/*     */     
/* 102 */     if (!proj.field_70170_p.field_72995_K && owner instanceof net.minecraft.entity.player.PlayerEntity)
/* 103 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncProjectileExtrasPacket(proj), (Entity)thrower); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\ProjectileEntityEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */