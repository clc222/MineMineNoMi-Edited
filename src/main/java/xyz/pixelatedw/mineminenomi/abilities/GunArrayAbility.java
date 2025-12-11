/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModGunItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GunArrayAbility
/*     */   extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gun_array", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("While active it automatically fires bullets from the inventory.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 40;
/*  37 */   public static final AbilityCore<GunArrayAbility> INSTANCE = (new AbilityCore.Builder("Gun Array", AbilityCategory.EQUIPMENT, GunArrayAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  40 */     .setUnlockCheck(GunArrayAbility::canUnlock)
/*  41 */     .build();
/*     */   
/*  43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  44 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::stopRepeaterEvent);
/*  45 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*  47 */   private ItemStack bulletStack = ItemStack.field_190927_a;
/*     */   
/*     */   public GunArrayAbility(AbilityCore<GunArrayAbility> core) {
/*  50 */     super(core);
/*     */     
/*  52 */     this.isNew = true;
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  55 */     addCanUseCheck(this::canUse);
/*  56 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  60 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.repeaterComponent.start(entity, 20, 20);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  68 */     if (this.bulletStack.func_190926_b()) {
/*  69 */       findNewStack(entity);
/*     */       
/*  71 */       if (this.bulletStack.func_190926_b()) {
/*  72 */         this.continuousComponent.stopContinuity(entity);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  78 */     this.repeaterComponent.stop(entity);
/*  79 */     this.cooldownComponent.startCooldown(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  83 */     float innacuracy = 1.0F;
/*     */     
/*  85 */     if (entity.func_70051_ag()) {
/*  86 */       innacuracy = 3.0F;
/*     */     }
/*     */     
/*  89 */     if (entity.func_213453_ef()) {
/*  90 */       innacuracy = 0.0F;
/*     */     }
/*     */     
/*  93 */     this.projectileComponent.shoot(entity, 3.0F, innacuracy);
/*  94 */     this.bulletStack.func_190918_g(1);
/*     */   }
/*     */   
/*     */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  98 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/*     */     NormalBulletProjectile normalBulletProjectile;
/* 102 */     AbilityProjectileEntity proj = null;
/*     */     
/* 104 */     if (this.bulletStack.func_77973_b() == ModItems.KAIROSEKI_BULLET.get()) {
/* 105 */       KairosekiBulletProjectile kairosekiBulletProjectile = new KairosekiBulletProjectile(entity.field_70170_p, entity);
/*     */     } else {
/*     */       
/* 108 */       normalBulletProjectile = new NormalBulletProjectile(entity.field_70170_p, entity);
/*     */     } 
/*     */     
/* 111 */     double x = entity.func_226277_ct_() + WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 112 */     double y = entity.func_226280_cw_() + WyHelper.randomDouble() / 2.0D;
/* 113 */     double z = entity.func_226281_cx_() + WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*     */     
/* 115 */     normalBulletProjectile.func_70012_b(x, y, z, 0.0F, 0.0F);
/*     */     
/* 117 */     return (AbilityProjectileEntity)normalBulletProjectile;
/*     */   }
/*     */   
/*     */   private void findNewStack(LivingEntity entity) {
/* 121 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/* 122 */     for (int i = 0; i < inventory.size(); i++) {
/* 123 */       ItemStack stack = inventory.get(i);
/*     */       
/* 125 */       if (stack != null && !stack.func_190926_b() && ModGunItem.GUN_AMMO.test(stack) && stack.func_190916_E() >= 4) {
/* 126 */         this.bulletStack = stack;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 133 */     if (!hasArmorSetEquipped(entity)) {
/* 134 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 137 */     findNewStack(entity);
/* 138 */     if (this.bulletStack.func_190926_b()) {
/* 139 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 142 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 146 */     return hasArmorSetEquipped(entity);
/*     */   }
/*     */   
/*     */   private static boolean hasArmorSetEquipped(LivingEntity entity) {
/* 150 */     ItemStack chestStack = entity.func_184582_a(EquipmentSlotType.CHEST);
/* 151 */     if (chestStack.func_190926_b() || chestStack.func_77973_b() != ModArmors.WOOTZ_STEEL_ARMOR.get()) {
/* 152 */       return false;
/*     */     }
/*     */     
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\GunArrayAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */