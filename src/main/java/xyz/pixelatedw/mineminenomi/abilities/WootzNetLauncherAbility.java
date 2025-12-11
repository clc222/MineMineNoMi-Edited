/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.NetEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.items.NetItem;
/*     */ 
/*     */ public class WootzNetLauncherAbility
/*     */   extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "wootz_net_launcher", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Shoots a net from one of the many guns hidden within the armor.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 600;
/*  35 */   public static final AbilityCore<WootzNetLauncherAbility> INSTANCE = (new AbilityCore.Builder("Wootz Net Launcher", AbilityCategory.EQUIPMENT, WootzNetLauncherAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F)
/*  38 */       }).setUnlockCheck(WootzNetLauncherAbility::canUnlock)
/*  39 */     .build();
/*     */   
/*  41 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  43 */   private ItemStack netStack = ItemStack.field_190927_a;
/*     */   
/*     */   public WootzNetLauncherAbility(AbilityCore<WootzNetLauncherAbility> core) {
/*  46 */     super(core);
/*     */     
/*  48 */     this.isNew = true;
/*  49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*     */     
/*  51 */     addCanUseCheck(this::canUse);
/*  52 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  56 */     if (!(this.netStack.func_77973_b() instanceof NetItem)) {
/*     */       return;
/*     */     }
/*     */     
/*  60 */     NetItem netItem = (NetItem)this.netStack.func_77973_b();
/*     */     
/*  62 */     this.animationComponent.start(entity, ModAnimations.MH5_CHARGING, 7);
/*     */     
/*  64 */     Vector3d look = entity.func_70040_Z();
/*     */     
/*  66 */     NetEntity netEntity = NetEntity.createFromItem(entity.field_70170_p, entity, netItem);
/*  67 */     netEntity.func_70107_b(entity.func_226277_ct_() + look.field_72450_a, entity.func_226280_cw_() + look.field_72448_b, entity.func_226281_cx_() + look.field_72449_c);
/*  68 */     netEntity.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 1.25F, 0.0F);
/*  69 */     entity.field_70170_p.func_217376_c((Entity)netEntity);
/*  70 */     this.netStack.func_190918_g(1);
/*     */     
/*  72 */     this.cooldownComponent.startCooldown(entity, 600.0F);
/*     */   }
/*     */   
/*     */   private void findNewStack(LivingEntity entity) {
/*  76 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*  77 */     for (int i = 0; i < inventory.size(); i++) {
/*  78 */       ItemStack stack = inventory.get(i);
/*     */       
/*  80 */       if (stack != null && !stack.func_190926_b() && stack.func_77973_b() instanceof NetItem) {
/*  81 */         this.netStack = stack;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/*  88 */     if (!hasArmorSetEquipped(entity)) {
/*  89 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/*  92 */     findNewStack(entity);
/*  93 */     if (this.netStack.func_190926_b()) {
/*  94 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/*  97 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 101 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     return hasArmorSetEquipped(entity);
/*     */   }
/*     */   
/*     */   private static boolean hasArmorSetEquipped(LivingEntity entity) {
/* 109 */     ItemStack chestStack = entity.func_184582_a(EquipmentSlotType.CHEST);
/* 110 */     if (chestStack.func_190926_b() || chestStack.func_77973_b() != ModArmors.WOOTZ_STEEL_ARMOR.get()) {
/* 111 */       return false;
/*     */     }
/*     */     
/* 114 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\WootzNetLauncherAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */