/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem;
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
/*     */ public class GomuPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityHurt(WyLivingHurtEvent event) {
/*  42 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  45 */     DamageSource source = event.getSource();
/*  46 */     Entity instantSource = source.func_76364_f();
/*  47 */     Entity trueSource = source.func_76346_g();
/*  48 */     PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
/*  49 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)attacked);
/*     */     
/*  51 */     if (!props.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI) || source.func_82725_o()) {
/*     */       return;
/*     */     }
/*  54 */     float reduction = 0.0F;
/*  55 */     ArrayList<String> instantSources = new ArrayList<>(Arrays.asList(new String[] { "mob", "player" }));
/*     */     
/*  57 */     boolean a = false;
/*  58 */     if (instantSource instanceof LivingEntity) {
/*     */       
/*  60 */       ItemStack mainhandGear = ((LivingEntity)instantSource).func_184582_a(EquipmentSlotType.MAINHAND);
/*  61 */       a = (trueSource instanceof LivingEntity && !HakiHelper.hasHardeningActive((LivingEntity)instantSource) && instantSources.contains(source.func_76355_l()) && !source.func_76352_a() && getGomuDamagingItems(mainhandGear.func_77973_b()) && !ItemsHelper.isKairosekiWeapon(mainhandGear));
/*     */     } 
/*     */     
/*  64 */     boolean b = (source.func_76352_a() && instantSource instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)instantSource).isPhysical() && !((AbilityProjectileEntity)instantSource).isAffectedByHaki());
/*     */     
/*  66 */     if ((a || b) && !source.func_94541_c()) {
/*  67 */       reduction = 0.75F;
/*     */     }
/*  69 */     if (source.func_76355_l().equals(DamageSource.field_180137_b.func_76355_l())) {
/*  70 */       reduction = 1.0F;
/*     */     }
/*  72 */     if (source instanceof ModDamageSource && ((ModDamageSource)source).getElement() == SourceElement.LIGHTNING) {
/*  73 */       reduction = 1.0F;
/*     */     }
/*     */     
/*  76 */     event.setAmount(event.getAmount() * (1.0F - reduction));
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(WyLivingAttackEvent event) {
/*  82 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  85 */     PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
/*  86 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacked);
/*     */     
/*  88 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI)) {
/*     */       return;
/*     */     }
/*  91 */     DamageSource source = event.getSource();
/*  92 */     Entity instantSource = source.func_76364_f();
/*     */     
/*  94 */     if (!(instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile) && !(instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile) && !(instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile)) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     AbilityProjectileEntity ablProj = (AbilityProjectileEntity)instantSource;
/*     */     
/* 100 */     if (ablProj.getThrower() != null && ablProj.isAffectedByHaki()) {
/* 101 */       LivingEntity thrower = ablProj.getThrower();
/*     */       
/* 103 */       boolean isImbued = (ablProj.isAffectedByImbuing() && HakiHelper.hasImbuingActive(thrower));
/*     */       
/* 105 */       if (isImbued) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 110 */     event.setCanceled(true);
/*     */     
/* 112 */     ((AbilityProjectileEntity)instantSource).setThrower((LivingEntity)attacked);
/* 113 */     ((AbilityProjectileEntity)instantSource).func_70186_c(-(instantSource.func_213322_ci()).field_72450_a, -(instantSource.func_213322_ci()).field_72448_b, -(instantSource.func_213322_ci()).field_72449_c, 0.8F, 20.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean getGomuDamagingItems(Item item) {
/* 118 */     if ((item instanceof net.minecraft.item.SwordItem && !(item instanceof ModSwordItem)) || item instanceof net.minecraft.item.PickaxeItem || item instanceof net.minecraft.item.AxeItem || item instanceof net.minecraft.item.TridentItem || item instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
/* 119 */       return false;
/*     */     }
/* 121 */     if (item instanceof ModSwordItem) {
/* 122 */       return ((ModSwordItem)item).isBlunt();
/*     */     }
/* 124 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\GomuPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */