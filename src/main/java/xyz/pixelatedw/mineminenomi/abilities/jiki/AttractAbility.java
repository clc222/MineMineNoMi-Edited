/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import java.util.List;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.PlayerInventory;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AttractAbility extends Ability {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "attract", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Attracts all nearby magnetic objects from the ground or enemy inventories.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 60;
/*     */   private static final int HOLD_TIME = 100;
/*     */   private static final int RADIUS = 40;
/*  45 */   public static final AbilityCore<AttractAbility> INSTANCE = (new AbilityCore.Builder("Attract", AbilityCategory.DEVIL_FRUITS, AttractAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F), ContinuousComponent.getTooltip(100.0F), RangeComponent.getTooltip(40.0F, RangeComponent.RangeType.AOE)
/*  48 */       }).build();
/*     */   
/*  50 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*     */   
/*     */   public AttractAbility(AbilityCore<AttractAbility> core) {
/*  53 */     super(core);
/*     */     
/*  55 */     this.isNew = true;
/*  56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  58 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  62 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  66 */     List<Entity> targets = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 40.0D, ModEntityPredicates.getEnemyFactions(entity), new Class[] { MobEntity.class, PlayerEntity.class });
/*  67 */     List<Entity> items = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 40.0D, null, new Class[] { ItemEntity.class });
/*  68 */     targets.addAll(items);
/*  69 */     for (Entity target : targets) {
/*  70 */       if (target instanceof ItemEntity) {
/*  71 */         ItemEntity item = (ItemEntity)target;
/*  72 */         if (item.func_92059_d().func_190926_b() || !item.func_92059_d().func_77973_b().func_206844_a((ITag)ModTags.Items.MAGNETIC)) {
/*     */           continue;
/*     */         }
/*  75 */         if (EnchantmentHelper.func_82781_a(item.func_92059_d()).containsKey(ModEnchantments.KAIROSEKI.get())) {
/*     */           continue;
/*     */         }
/*  78 */         if (item.func_92059_d().func_77942_o() && item.func_92059_d().func_77978_p().func_74764_b("imbuingHakiActive")) {
/*     */           continue;
/*     */         }
/*  81 */         Vector3d vec = item.func_213303_ch().func_178788_d(entity.func_213303_ch().func_72441_c(0.0D, 1.5D, 0.0D)).func_216372_d(1.2D, 1.2D, 1.2D);
/*  82 */         double speedReduction = 8.0D;
/*  83 */         double xSpeed = -vec.field_72450_a / speedReduction;
/*  84 */         double ySpeed = -vec.field_72448_b / speedReduction;
/*  85 */         double zSpeed = -vec.field_72449_c / speedReduction;
/*  86 */         AbilityHelper.setDeltaMovement((Entity)item, xSpeed, ySpeed, zSpeed);
/*  87 */         double dist = Math.sqrt(entity.func_233580_cy_().func_177951_i((Vector3i)item.func_233580_cy_()));
/*  88 */         if (dist > 3.0D) {
/*  89 */           int delay = (int)Math.max(5.0D, dist / 3.0D);
/*  90 */           item.func_174867_a(delay);
/*     */         } 
/*  92 */         if (this.continuousComponent.getContinueTime() % 40.0F == 0.0F) {
/*  93 */           JikiHelper.spawnAttractEffect((Entity)item);
/*     */         }
/*     */         continue;
/*     */       } 
/*  97 */       Iterable<ItemStack> iter = target.func_184209_aF();
/*  98 */       if (target instanceof PlayerEntity) {
/*  99 */         PlayerInventory playerInv = ((PlayerEntity)target).field_71071_by;
/* 100 */         iter = Iterables.concat((Iterable)playerInv.field_70462_a, (Iterable)playerInv.field_70460_b, (Iterable)playerInv.field_184439_c);
/*     */       } 
/* 102 */       for (ItemStack stack : iter) {
/* 103 */         if (stack.func_190926_b() || !stack.func_77973_b().func_206844_a((ITag)ModTags.Items.MAGNETIC)) {
/*     */           continue;
/*     */         }
/* 106 */         if (EnchantmentHelper.func_82781_a(stack).containsKey(ModEnchantments.KAIROSEKI.get())) {
/*     */           continue;
/*     */         }
/* 109 */         if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("imbuingHakiActive")) {
/*     */           continue;
/*     */         }
/*     */         
/* 113 */         if (target instanceof MobEntity) {
/* 114 */           for (EquipmentSlotType slotType : EquipmentSlotType.values()) {
/* 115 */             if (((MobEntity)target).func_184582_a(slotType).equals(stack)) {
/* 116 */               ((MobEntity)target).func_184201_a(slotType, ItemStack.field_190927_a);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 121 */         if (HakiHelper.hasImbuingActive((LivingEntity)target, false, true)) {
/*     */           continue;
/*     */         }
/*     */         
/* 125 */         ItemEntity item = target.func_70099_a(stack.func_77946_l(), 1.0F);
/* 126 */         if (item != null) {
/* 127 */           item.func_174867_a(30);
/*     */         }
/*     */         
/* 130 */         stack.func_190918_g(stack.func_190916_E());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 137 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\AttractAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */