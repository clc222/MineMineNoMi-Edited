/*     */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.BlockItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
/*     */ 
/*     */ public class BakuTsuihoAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "baku_tsuiho", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Allows the user to charge multiple blocks from their inventory in their mouth and shoot them all at the same time.", null)
/*     */       });
/*     */   
/*     */   private static final float CHARGE_TIME = 60.0F;
/*     */   private static final float COOLDOWN = 160.0F;
/*  37 */   public static final AbilityCore<BakuTsuihoAbility> INSTANCE = (new AbilityCore.Builder("Baku Tsuiho", AbilityCategory.DEVIL_FRUITS, BakuTsuihoAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ChargeComponent.getTooltip(60.0F)
/*  40 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  41 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  42 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  43 */       }).build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent);
/*  46 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addEndEvent(100, this::endChargeEvent);
/*  47 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  48 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::triggerRepeaterEvent).addStopEvent(100, this::stopRepeaterEvent);
/*     */   
/*     */   private int ammo;
/*     */   
/*     */   public BakuTsuihoAbility(AbilityCore<BakuTsuihoAbility> core) {
/*  53 */     super(core);
/*     */     
/*  55 */     this.isNew = true;
/*  56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.repeaterComponent });
/*     */     
/*  58 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  62 */     if (this.continuousComponent.isContinuous()) {
/*  63 */       this.repeaterComponent.stop(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.ammo = 0;
/*  72 */     List<ItemStack> availableBlocks = getBlocksInInventory(entity);
/*     */     
/*  74 */     int idx = 0;
/*  75 */     while (this.ammo < 20 && idx < availableBlocks.size()) {
/*  76 */       ItemStack stack = availableBlocks.get(idx);
/*  77 */       ItemStack copy = stack.func_77946_l();
/*  78 */       this.ammo += copy.func_190916_E();
/*  79 */       stack.func_190918_g(this.ammo);
/*  80 */       idx++;
/*     */     } 
/*     */     
/*  83 */     this.continuousComponent.startContinuity(entity, -1.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.repeaterComponent.start(entity, this.ammo, 3);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  91 */     this.projectileComponent.shootWithSpread(entity, 3.0F, 4.0F, 1);
/*     */   }
/*     */   
/*     */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  95 */     this.continuousComponent.stopContinuity(entity);
/*  96 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*     */   }
/*     */   
/*     */   private BeroCannonProjectile createProjectile(LivingEntity entity) {
/* 100 */     BeroCannonProjectile proj = new BeroCannonProjectile(entity.field_70170_p, entity, this);
/* 101 */     return proj;
/*     */   }
/*     */   
/*     */   private List<ItemStack> getBlocksInInventory(LivingEntity entity) {
/* 105 */     List<ItemStack> projectiles = new ArrayList<>();
/* 106 */     for (ItemStack item : ItemsHelper.getInventoryItems(entity)) {
/* 107 */       if (item != null && item.func_77973_b() instanceof BlockItem && !DefaultProtectionRules.CORE_FOLIAGE_ORE.getApprovedBlocks().stream().anyMatch(p -> (p == ((BlockItem)item.func_77973_b()).func_179223_d().getRegistryName()))) {
/* 108 */         projectiles.add(item);
/*     */       }
/*     */     } 
/* 111 */     return projectiles;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BakuTsuihoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */