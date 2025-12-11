/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoBazookaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoGrizzlyMagnumProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetBazookaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoLeoBazookaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GomuGomuNoBazookaAbility extends Ability {
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gomu_gomu_no_bazooka", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("Hits the enemy with both hands to launch them away.", null)
/*     */       });
/*  45 */   private static final TranslationTextComponent GOMU_GOMU_NO_BAZOOKA_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_bazooka", "Gomu Gomu no Bazooka"));
/*  46 */   private static final TranslationTextComponent GOMU_GOMU_NO_JET_BAZOOKA_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_bazooka", "Gomu Gomu no Jet Bazooka"));
/*  47 */   private static final TranslationTextComponent GOMU_GOMU_NO_GRIZZLY_MAGNUM_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_grizzly_magnum", "Gomu Gomu no Grizzly Magnum"));
/*  48 */   private static final TranslationTextComponent GOMU_GOMU_NO_LEO_BAZOOKA_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_leo_bazooka", "Gomu Gomu no Leo Bazooka"));
/*     */   
/*  50 */   private static final ResourceLocation GOMU_GOMU_NO_BAZOOKA_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_bazooka.png");
/*  51 */   private static final ResourceLocation GOMU_GOMU_NO_JET_BAZOOKA_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_jet_bazooka.png");
/*  52 */   private static final ResourceLocation GOMU_GOMU_NO_GRIZZLY_MAGNUM_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_grizzly_magnum.png");
/*  53 */   private static final ResourceLocation GOMU_GOMU_NO_LEO_BAZOOKA_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_leo_bazooka.png");
/*     */   
/*     */   private static final int NO_GEAR_COOLDOWN = 200;
/*     */   
/*     */   private static final int NO_GEAR_CHARGE_TIME = 40;
/*     */   
/*     */   private static final int SECOND_GEAR_COOLDOWN = 140;
/*     */   
/*     */   private static final int SECOND_GEAR_CHARGE_TIME = 30;
/*     */   
/*     */   private static final int THIRD_GEAR_COOLDOWN = 300;
/*     */   private static final int THIRD_GEAR_CHARGE_TIME = 60;
/*     */   private static final int FOURTH_GEAR_COOLDOWN = 240;
/*     */   private static final int FOURTH_GEAR_CHARGE_TIME = 40;
/*  67 */   private static final AbilityDescriptionLine.IDescriptionLine NO_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_BAZOOKA_NAME));
/*  68 */   private static final AbilityDescriptionLine.IDescriptionLine SECOND_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_JET_BAZOOKA_NAME));
/*  69 */   private static final AbilityDescriptionLine.IDescriptionLine THIRD_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_GRIZZLY_MAGNUM_NAME));
/*  70 */   private static final AbilityDescriptionLine.IDescriptionLine FOURTH_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_LEO_BAZOOKA_NAME));
/*     */   
/*  72 */   public static final AbilityCore<GomuGomuNoBazookaAbility> INSTANCE = (new AbilityCore.Builder("Gomu Gomu no Bazooka", AbilityCategory.DEVIL_FRUITS, GomuGomuNoBazookaAbility::new))
/*  73 */     .addDescriptionLine(DESCRIPTION)
/*  74 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, NO_GEAR_NAME_DESC, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(40.0F)
/*  75 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, SECOND_GEAR_NAME_DESC, GomuHelper.SECOND_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ChargeComponent.getTooltip(30.0F)
/*  76 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, THIRD_GEAR_NAME_DESC, GomuHelper.THIRD_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ChargeComponent.getTooltip(60.0F)
/*  77 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, FOURTH_GEAR_NAME_DESC, GomuHelper.FOURTH_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(40.0F)
/*  78 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  79 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  80 */       }).build();
/*     */   
/*  82 */   private final AltModeComponent<GomuHelper.Gears> altModeComponent = (new AltModeComponent((IAbility)this, GomuHelper.Gears.class, GomuHelper.Gears.NO_GEAR, true)).addChangeModeEvent(this::altModeChangeEvent);
/*  83 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::endChargeEvent);
/*  84 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  85 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  87 */   private float cooldown = 200.0F;
/*  88 */   private int chargeTime = 40;
/*  89 */   private float projectileSpeed = 2.0F;
/*  90 */   private float projectileSpread = 1.0F;
/*     */   
/*     */   public GomuGomuNoBazookaAbility(AbilityCore<GomuGomuNoBazookaAbility> core) {
/*  93 */     super(core);
/*     */     
/*  95 */     this.isNew = true;
/*  96 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  98 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 102 */     this.chargeComponent.startCharging(entity, this.chargeTime);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 106 */     this.animationComponent.start(entity, ModAnimations.GOMU_BAZOOKA);
/* 107 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 108 */     if (GomuHelper.hasGearFourthActive(props) && entity instanceof PlayerEntity && ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/*     */       return;
/*     */     }
/* 111 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), this.chargeTime, 0));
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 115 */     this.animationComponent.stop(entity);
/*     */     
/* 117 */     AbilityProjectileEntity projectile1 = this.projectileComponent.getNewProjectile(entity);
/* 118 */     AbilityProjectileEntity projectile2 = this.projectileComponent.getNewProjectile(entity);
/*     */     
/* 120 */     Vector3d dirVec = Vector3d.field_186680_a;
/* 121 */     Direction dir = Direction.func_176733_a(entity.field_70177_z);
/* 122 */     dirVec = dirVec.func_72441_c(Math.abs(dir.func_176730_m().func_177958_n()), Math.abs(dir.func_176730_m().func_177956_o()), Math.abs(dir.func_176730_m().func_177952_p()));
/* 123 */     dirVec = dirVec.func_216372_d(this.projectileSpread, 1.0D, this.projectileSpread);
/*     */     
/* 125 */     projectile1.func_70012_b(entity.func_226277_ct_() + dirVec.field_72449_c, entity.func_226280_cw_(), entity.func_226281_cx_() + dirVec.field_72450_a, 0.0F, 0.0F);
/* 126 */     projectile2.func_70012_b(entity.func_226277_ct_() - dirVec.field_72449_c, entity.func_226280_cw_(), entity.func_226281_cx_() - dirVec.field_72450_a, 0.0F, 0.0F);
/*     */     
/* 128 */     this.projectileComponent.shoot(projectile1, entity, this.projectileSpeed, 0.0F);
/* 129 */     this.projectileComponent.shoot(projectile2, entity, this.projectileSpeed, 0.0F);
/*     */     
/* 131 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/* 132 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GOMU_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.75F);
/*     */     
/* 134 */     this.cooldownComponent.startCooldown(entity, this.cooldown);
/*     */   }
/*     */   
/*     */   private void altModeChangeEvent(LivingEntity entity, IAbility ability, GomuHelper.Gears mode) {
/* 138 */     switch (mode) {
/*     */       
/*     */       case GEAR_2:
/* 141 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_JET_BAZOOKA_NAME);
/* 142 */         this.cooldown = 140.0F;
/* 143 */         this.chargeTime = 30;
/*     */ 
/*     */       
/*     */       case GEAR_3:
/* 147 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_GRIZZLY_MAGNUM_NAME);
/* 148 */         this.cooldown = 300.0F;
/* 149 */         this.chargeTime = 60;
/*     */ 
/*     */       
/*     */       case GEAR_4:
/* 153 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_LEO_BAZOOKA_NAME);
/* 154 */         this.cooldown = 240.0F;
/* 155 */         this.chargeTime = 40;
/*     */       
/*     */       case GEAR_5:
/*     */         return;
/*     */     } 
/*     */     
/* 161 */     setDisplayIcon(GOMU_GOMU_NO_BAZOOKA_ICON);
/* 162 */     setDisplayName((ITextComponent)GOMU_GOMU_NO_BAZOOKA_NAME);
/* 163 */     this.cooldown = 200.0F;
/* 164 */     this.chargeTime = 40;
/*     */   }
/*     */ 
/*     */   
/*     */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/*     */     GomuGomuNoBazookaProjectile gomuGomuNoBazookaProjectile;
/* 170 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 172 */     AbilityProjectileEntity projectile = null;
/*     */     
/* 174 */     this.projectileSpeed = 2.0F;
/* 175 */     this.projectileSpread = 1.0F;
/*     */     
/* 177 */     if (GomuHelper.hasGearFourthActive(props)) {
/* 178 */       GomuGomuNoLeoBazookaProjectile gomuGomuNoLeoBazookaProjectile = new GomuGomuNoLeoBazookaProjectile(entity.field_70170_p, entity, this);
/* 179 */       this.projectileSpeed = 3.0F;
/* 180 */       this.projectileSpread = 2.5F;
/*     */     }
/* 182 */     else if (GomuHelper.hasGearThirdActive(props)) {
/* 183 */       GomuGomuNoGrizzlyMagnumProjectile gomuGomuNoGrizzlyMagnumProjectile = new GomuGomuNoGrizzlyMagnumProjectile(entity.field_70170_p, entity, this);
/* 184 */       this.projectileSpeed = 1.8F;
/* 185 */       this.projectileSpread = 2.5F;
/*     */     }
/* 187 */     else if (GomuHelper.hasGearSecondActive(props)) {
/* 188 */       GomuGomuNoJetBazookaProjectile gomuGomuNoJetBazookaProjectile = new GomuGomuNoJetBazookaProjectile(entity.field_70170_p, entity, this);
/* 189 */       this.projectileSpeed = 3.0F;
/*     */     } else {
/*     */       
/* 192 */       gomuGomuNoBazookaProjectile = new GomuGomuNoBazookaProjectile(entity.field_70170_p, entity, this);
/*     */     } 
/*     */     
/* 195 */     return (AbilityProjectileEntity)gomuGomuNoBazookaProjectile;
/*     */   }
/*     */   
/*     */   public void switchNoGear(LivingEntity entity) {
/* 199 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.NO_GEAR);
/*     */   }
/*     */   
/*     */   public void switchSecondGear(LivingEntity entity) {
/* 203 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_2);
/*     */   }
/*     */   
/*     */   public void switchThirdGear(LivingEntity entity) {
/* 207 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_3);
/*     */   }
/*     */   
/*     */   public void switchFourthGear(LivingEntity entity) {
/* 211 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_4);
/*     */   }
/*     */   
/*     */   public void switchFifthGear(LivingEntity entity) {
/* 215 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_5);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoBazookaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */