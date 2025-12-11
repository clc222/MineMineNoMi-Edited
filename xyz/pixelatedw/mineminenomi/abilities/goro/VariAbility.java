/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class VariAbility extends Ability {
/*  42 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/vari.png");
/*  43 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/vari.png");
/*     */   
/*  45 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "vari", new Pair[] {
/*  46 */         (Pair)ImmutablePair.of("A basic move where the user discharges variable amounts of electricity", null)
/*     */       });
/*  48 */   private static final ITextComponent ONE_MILLION_V_VARI_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.1_million_vari", "1 Million Vari"));
/*  49 */   private static final ITextComponent TWENTY_MILLION_V_VARI_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.20_million_vari", "20 Million Vari"));
/*  50 */   private static final ITextComponent ONE_HUNDRED_MILLION_V_VARI_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.100_million_vari", "100 Million Vari"));
/*  51 */   private static final ITextComponent TWO_HUNDRED_MILLION_V_VARI_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.200_million_vari", "200 Million Vari"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int CHARGE_TIME = 20;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final AbilityCore<VariAbility> INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  77 */     INSTANCE = (new AbilityCore.Builder("Vari", AbilityCategory.DEVIL_FRUITS, VariAbility::new)).addDescriptionLine(DESCRIPTION).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, (e, a) -> ONE_MILLION_V_VARI_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> CooldownComponent.getTooltip(Mode.ONE_MILLION_V.cooldown).expand(e, a), ChargeComponent.getTooltip(20.0F) }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, (e, a) -> TWENTY_MILLION_V_VARI_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> CooldownComponent.getTooltip(Mode.TWENTY_MILLION_V.cooldown).expand(e, a), ChargeComponent.getTooltip(20.0F) }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, (e, a) -> ONE_HUNDRED_MILLION_V_VARI_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> CooldownComponent.getTooltip(Mode.ONE_HUNDRED_MILLION_V.cooldown).expand(e, a), ChargeComponent.getTooltip(20.0F) }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, (e, a) -> TWO_HUNDRED_MILLION_V_VARI_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> CooldownComponent.getTooltip(Mode.TWO_HUNDRED_MILLION_V.cooldown).expand(e, a), ChargeComponent.getTooltip(20.0F) }).setSourceHakiNature(SourceHakiNature.SPECIAL).setSourceElement(SourceElement.LIGHTNING).setIcon(DEFAULT_ICON).build();
/*     */   }
/*  79 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this, true)).addStartEvent(100, this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  80 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.ONE_MILLION_V)).addChangeModeEvent(this::onAltModeChange);
/*  81 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  82 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  84 */   private final Interval dischargeInterval = new Interval(5);
/*     */   
/*     */   public VariAbility(AbilityCore<VariAbility> core) {
/*  87 */     super(core);
/*     */     
/*  89 */     setDisplayIcon(DEFAULT_ICON);
/*  90 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  91 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */     
/*  94 */     this.isNew = true;
/*     */     
/*  96 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  98 */     addUseEvent(this::onUseEvent);
/*  99 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/* 103 */     setDisplayIcon(DEFAULT_ICON);
/* 104 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/* 105 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 110 */     this.chargeComponent.startCharging(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 114 */     this.dischargeInterval.restartIntervalToZero();
/* 115 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 119 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 123 */     if (this.dischargeInterval.canTick()) {
/* 124 */       Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, 1.15D, 0.8D);
/*     */       
/* 126 */       float multi = 1.0F;
/*     */       
/* 128 */       if (((MorphInfo)ModMorphs.VOLT_AMARU.get()).isActive(entity)) {
/* 129 */         multi += 0.25F;
/*     */       }
/*     */       
/* 132 */       LightningDischargeEntity discharge = new LightningDischargeEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A);
/*     */       
/* 134 */       discharge.setAliveTicks(5);
/* 135 */       discharge.setLightningLength(0.2F * multi);
/* 136 */       discharge.setColor(Color.WHITE);
/* 137 */       discharge.setOutlineColor(ClientConfig.INSTANCE.isGoroBlue() ? ElThorAbility.BLUE_THUNDER : ElThorAbility.YELLOW_THUNDER);
/* 138 */       discharge.setRenderTransparent();
/* 139 */       discharge.setDetails(20);
/* 140 */       discharge.setDensity(40);
/* 141 */       discharge.setSize(0.2F * multi);
/* 142 */       discharge.setSkipSegments(1);
/*     */       
/* 144 */       entity.field_70170_p.func_217376_c((Entity)discharge);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 149 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 153 */     float multi = 1.0F;
/*     */     
/* 155 */     if (((MorphInfo)ModMorphs.VOLT_AMARU.get()).isActive(entity)) {
/* 156 */       multi += 0.25F;
/*     */     }
/*     */     
/* 159 */     ModDamageSource ablSource = AbilityDamageSource.causeAbilityDamage(entity, getCore()).setPiercing(0.75F).setUnavoidable();
/*     */     
/* 161 */     float radius = 12.0F * this.chargeComponent.getChargePercentage();
/*     */     
/* 163 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, 1.15D, 0.8D);
/*     */     
/* 165 */     LightningDischargeEntity discharge = new LightningDischargeEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A);
/*     */     
/* 167 */     discharge.setAliveTicks(15);
/* 168 */     discharge.setLightningLength(radius / 2.0F * multi);
/* 169 */     discharge.setColor(Color.WHITE);
/* 170 */     discharge.setOutlineColor(ClientConfig.INSTANCE.isGoroBlue() ? ElThorAbility.BLUE_THUNDER : ElThorAbility.YELLOW_THUNDER);
/* 171 */     discharge.setRenderTransparent();
/* 172 */     discharge.setDetails(20);
/* 173 */     discharge.setDensity(40);
/* 174 */     discharge.setSize(radius / 2.0F * multi);
/* 175 */     discharge.setSkipSegments(1);
/*     */     
/* 177 */     entity.field_70170_p.func_217376_c((Entity)discharge);
/*     */     
/* 179 */     List<LivingEntity> targets = WyHelper.getNearbyEntities(pos, (IWorld)entity.field_70170_p, radius, ModEntityPredicates.getEnemyFactions(entity), new Class[] { LivingEntity.class });
/*     */     
/* 181 */     targets.remove(entity);
/*     */     
/* 183 */     Mode currentMode = (Mode)this.altModeComponent.getCurrentMode();
/*     */     
/* 185 */     for (LivingEntity target : targets) {
/* 186 */       float damage = (float)((currentMode.getDamage() * this.chargeComponent.getChargePercentage()) * (1.0D - pos.func_72438_d(target.func_213303_ch()) / (radius * 3.0F)));
/*     */       
/* 188 */       if (this.dealDamageComponent.hurtTarget(entity, target, damage * multi, (DamageSource)ablSource)) {
/* 189 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 10, 0, false, false, true));
/*     */       }
/*     */     } 
/*     */     
/* 193 */     this.animationComponent.stop(entity);
/*     */     
/* 195 */     this.cooldownComponent.startCooldown(entity, currentMode.getCooldown());
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 199 */     setDisplayName(mode.getDisplayName());
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 203 */     ONE_MILLION_V((String)VariAbility.ONE_MILLION_V_VARI_NAME, 10.0F, 200.0F),
/* 204 */     TWENTY_MILLION_V((String)VariAbility.TWENTY_MILLION_V_VARI_NAME, 35.0F, 300.0F),
/* 205 */     ONE_HUNDRED_MILLION_V((String)VariAbility.ONE_HUNDRED_MILLION_V_VARI_NAME, 50.0F, 500.0F),
/* 206 */     TWO_HUNDRED_MILLION_V((String)VariAbility.TWO_HUNDRED_MILLION_V_VARI_NAME, 65.0F, 600.0F);
/*     */     
/*     */     private ITextComponent displayName;
/*     */     
/*     */     private float damage;
/*     */     
/*     */     private float cooldown;
/*     */     
/*     */     Mode(ITextComponent displayName, float damage, float cooldown) {
/* 215 */       this.displayName = displayName;
/* 216 */       this.damage = damage;
/* 217 */       this.cooldown = cooldown;
/*     */     }
/*     */     
/*     */     public ITextComponent getDisplayName() {
/* 221 */       return this.displayName;
/*     */     }
/*     */     
/*     */     public float getDamage() {
/* 225 */       return this.damage;
/*     */     }
/*     */     
/*     */     public float getCooldown() {
/* 229 */       return this.cooldown;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\VariAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */