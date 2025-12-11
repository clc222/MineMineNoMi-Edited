/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public class PunkPistolsAbility extends RepeaterAbility2 {
/*     */   private static final int VALUE_PER_SPEAR = 5;
/*     */   private static final int SPEARS = 6;
/*     */   private static final int INTERVAL = 4;
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "punk_pistols", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Uses %s magnetic objects from the user's inventory to form %s spears and shoots them at enemies.", new Object[] { AbilityHelper.mentionText(Integer.valueOf(30)), AbilityHelper.mentionText(Integer.valueOf(6)) })
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*  38 */   public static final AbilityCore<PunkPistolsAbility> INSTANCE = (new AbilityCore.Builder("Punk Pistols", AbilityCategory.DEVIL_FRUITS, PunkPistolsAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/*  41 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  42 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  43 */     .setSourceElement(SourceElement.METAL)
/*  44 */     .build();
/*     */   
/*  46 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public PunkPistolsAbility(AbilityCore<PunkPistolsAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*     */     
/*  53 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*  54 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */     
/*  56 */     this.repeaterComponent.addTriggerEvent(this::triggerRepeaterEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  60 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.animationComponent.stop(entity);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  68 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*     */     
/*  70 */     if (!JikiHelper.hasEnoughIron(inventory, 5.0F)) {
/*  71 */       entity.func_145747_a((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, Util.field_240973_b_);
/*  72 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  76 */     JikiHelper.spawnAttractEffect((Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxTriggers() {
/*  81 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTriggerInterval() {
/*  86 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRepeaterCooldown() {
/*  91 */     return 240.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public PunkPistolProjectile getProjectileFactory(LivingEntity entity) {
/*  96 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*  97 */     List<ItemStack> components = JikiHelper.getMagneticItemsNeeded(inventory, 5.0F);
/*     */     
/*  99 */     PunkPistolProjectile proj = new PunkPistolProjectile(entity.field_70170_p, entity, components);
/*     */     
/* 101 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkPistolsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */