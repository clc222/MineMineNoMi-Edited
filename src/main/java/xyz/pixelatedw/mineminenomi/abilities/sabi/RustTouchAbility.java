/*     */ package xyz.pixelatedw.mineminenomi.abilities.sabi;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class RustTouchAbility extends PunchAbility2 {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "rust_touch", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Rusts the enemy and the items equipped on them", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 300.0F;
/*  40 */   public static final AbilityCore<RustTouchAbility> INSTANCE = (new AbilityCore.Builder("Rust Touch", AbilityCategory.DEVIL_FRUITS, RustTouchAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/*  43 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  44 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  45 */       }).build();
/*     */   
/*  47 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.RUST_TOUCH_ARM).build();
/*     */   
/*  49 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   public RustTouchAbility(AbilityCore<RustTouchAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  56 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  57 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  61 */     this.skinOverlayComponent.showAll(entity);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  65 */     this.skinOverlayComponent.hideAll(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  70 */     target.func_195064_c(new EffectInstance(Effects.field_82731_v, 160, 2));
/*  71 */     target.func_195064_c(new EffectInstance(Effects.field_76419_f, 160, 1));
/*  72 */     target.func_195064_c(new EffectInstance(Effects.field_76421_d, 160, 1));
/*     */     
/*  74 */     Iterable<ItemStack> iter = ItemsHelper.getAllInventoryItems(target);
/*     */     
/*  76 */     for (ItemStack stack : iter) {
/*  77 */       if (!stack.func_190926_b() && stack.func_77973_b().func_206844_a((ITag)ModTags.Items.RUSTY)) {
/*  78 */         if (stack.func_77984_f()) {
/*  79 */           stack.func_222118_a(stack.func_77958_k() / 3 + 1 + 1, target, e -> e.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */           continue;
/*     */         } 
/*  82 */         stack.func_190918_g(1 + this.random.nextInt(4));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  87 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.RUST_TOUCH.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/*  94 */     return entity -> this.continuousComponent.isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/*  99 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchDamage() {
/* 104 */     return 10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 109 */     return 300.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sabi\RustTouchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */