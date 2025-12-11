/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class GammaKnifeAbility extends PunchAbility2 {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gamma_knife", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Creates a blade of gamma radiation which massively damages the opponent's organs", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 500.0F;
/*  37 */   public static final AbilityCore<GammaKnifeAbility> INSTANCE = (new AbilityCore.Builder("Gamma Knife", AbilityCategory.DEVIL_FRUITS, GammaKnifeAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(500.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/*  40 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  41 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  42 */       }).build();
/*     */   
/*  44 */   private final ItemSpawnComponent itemSpawnComponent = new ItemSpawnComponent((IAbility)this);
/*     */   
/*  46 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("171d7b97-319e-4f2f-84c3-0882d82444ae"), INSTANCE, "Gamma Knife Strength Modifier", 70.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   public GammaKnifeAbility(AbilityCore<GammaKnifeAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     this.continuousComponent.addStartEvent(100, this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(100, this::onContinuityEnd);
/*     */     
/*  53 */     this.statsComponent.addAttributeModifier(Attributes.field_233823_f_, (AttributeModifier)STRENGTH_MODIFIER, canActivate());
/*     */     
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.itemSpawnComponent });
/*     */     
/*  57 */     addCanUseCheck(OpeHelper::hasRoomActive);
/*  58 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  62 */     this.itemSpawnComponent.spawnItem(entity, new ItemStack((IItemProvider)ModWeapons.GAMMA_KNIFE.get()), EquipmentSlotType.MAINHAND, true);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  66 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     if (OpeHelper.hasRoomActive(entity, (IAbility)this).isFail()) {
/*  71 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  76 */     this.itemSpawnComponent.despawnItems(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  81 */     source.bypassLogia().setPiercing(1.0F);
/*     */     
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/*  88 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_77973_b().equals(ModWeapons.GAMMA_KNIFE.get()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/*  93 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 103 */     return 500.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\GammaKnifeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */