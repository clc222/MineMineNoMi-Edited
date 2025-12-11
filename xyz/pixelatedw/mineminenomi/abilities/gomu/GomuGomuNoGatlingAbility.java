/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKingKongGunProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GomuGomuNoGatlingAbility extends RepeaterAbility2 {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gomu_gomu_no_gatling", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Rapidly punches enemies in front of the user.", null)
/*     */       });
/*  41 */   private static final TranslationTextComponent GOMU_GOMU_NO_GATLING_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_gatling", "Gomu Gomu no Gatling"));
/*  42 */   private static final TranslationTextComponent GOMU_GOMU_NO_JET_GATLING_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_gatling", "Gomu Gomu no Jet Gatling"));
/*  43 */   private static final TranslationTextComponent GOMU_GOMU_NO_ELEPHANT_GATLING_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_elephant_gatling", "Gomu Gomu no Elephant Gatling"));
/*  44 */   private static final TranslationTextComponent GOMU_GOMU_NO_KONG_GATLING_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_kong_gatling", "Gomu Gomu no Kong Gatling"));
/*     */   
/*  46 */   private static final ResourceLocation GOMU_GOMU_NO_GATLING_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_gatling.png");
/*  47 */   private static final ResourceLocation GOMU_GOMU_NO_JET_GATLING_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_jet_gatling.png");
/*  48 */   private static final ResourceLocation GOMU_GOMU_NO_ELEPHANT_GATLING_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_elephant_gatling.png");
/*  49 */   private static final ResourceLocation GOMU_GOMU_NO_KONG_GATLING_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_kong_gatling.png");
/*     */   
/*     */   private static final int NO_GEAR_COOLDOWN = 140;
/*     */   
/*     */   private static final int NO_GEAR_TRIGGERS = 20;
/*     */   
/*     */   private static final int NO_GEAR_INTERVAL = 3;
/*     */   
/*     */   private static final int SECOND_GEAR_COOLDOWN = 100;
/*     */   
/*     */   private static final int SECOND_GEAR_TRIGGERS = 35;
/*     */   private static final int SECOND_GEAR_INTERVAL = 2;
/*     */   private static final int THIRD_GEAR_COOLDOWN = 250;
/*     */   private static final int THIRD_GEAR_TRIGGERS = 10;
/*     */   private static final int THIRD_GEAR_INTERVAL = 5;
/*     */   private static final int FOURTH_GEAR_COOLDOWN = 200;
/*     */   private static final int FOURTH_GEAR_TRIGGERS = 8;
/*     */   private static final int FOURTH_GEAR_INTERVAL = 5;
/*  67 */   private static final AbilityDescriptionLine.IDescriptionLine NO_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_GATLING_NAME));
/*  68 */   private static final AbilityDescriptionLine.IDescriptionLine SECOND_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_JET_GATLING_NAME));
/*  69 */   private static final AbilityDescriptionLine.IDescriptionLine THIRD_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_ELEPHANT_GATLING_NAME));
/*  70 */   private static final AbilityDescriptionLine.IDescriptionLine FOURTH_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_KONG_GATLING_NAME));
/*     */   
/*  72 */   public static final AbilityCore<GomuGomuNoGatlingAbility> INSTANCE = (new AbilityCore.Builder("Gomu Gomu no Gatling", AbilityCategory.DEVIL_FRUITS, GomuGomuNoGatlingAbility::new))
/*  73 */     .addDescriptionLine(DESCRIPTION)
/*  74 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, NO_GEAR_NAME_DESC, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F)
/*  75 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, SECOND_GEAR_NAME_DESC, GomuHelper.SECOND_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/*  76 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, THIRD_GEAR_NAME_DESC, GomuHelper.THIRD_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(250.0F)
/*  77 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, FOURTH_GEAR_NAME_DESC, GomuHelper.FOURTH_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/*  78 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  79 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  80 */       }).build();
/*     */   
/*  82 */   private final AltModeComponent<GomuHelper.Gears> altModeComponent = (new AltModeComponent((IAbility)this, GomuHelper.Gears.class, GomuHelper.Gears.NO_GEAR, true)).addChangeModeEvent(this::altModeChangeEvent);
/*  83 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  85 */   private float projectileSpeed = 3.0F;
/*  86 */   private int projectileSpread = 2;
/*  87 */   private float cooldown = 140.0F;
/*  88 */   private int triggers = 20;
/*  89 */   private int interval = 3;
/*     */   
/*     */   public GomuGomuNoGatlingAbility(AbilityCore<GomuGomuNoGatlingAbility> core) {
/*  92 */     super(core);
/*     */     
/*  94 */     this.isNew = true;
/*  95 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  97 */     setCustomShootLogic(living -> {
/*     */           for (int i = 0; i < 5; i++) {
/*     */             AbilityProjectileEntity projectile = getProjectileFactory(living);
/*     */             
/*     */             this.projectileComponent.shootWithSpread(projectile, living, this.projectileSpeed, 3.0F, this.projectileSpread);
/*     */           } 
/*     */         });
/* 104 */     this.repeaterComponent.addTriggerEvent(100, this::triggerRepeaterEvent);
/* 105 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/* 106 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/* 107 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 111 */     this.animationComponent.start(entity, ModAnimations.PUNCH_RUSH);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 115 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 116 */     if (GomuHelper.hasGearFourthActive(props) && entity instanceof PlayerEntity && ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/*     */       return;
/*     */     }
/* 119 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 123 */     this.animationComponent.stop(entity);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 127 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/* 128 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GOMU_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.6F + this.random.nextFloat() / 2.0F);
/*     */   }
/*     */   
/*     */   private void altModeChangeEvent(LivingEntity entity, IAbility ability, GomuHelper.Gears mode) {
/* 132 */     switch (mode) {
/*     */       
/*     */       case GEAR_2:
/* 135 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_JET_GATLING_NAME);
/* 136 */         this.cooldown = 100.0F;
/* 137 */         this.triggers = 35;
/* 138 */         this.interval = 2;
/*     */ 
/*     */       
/*     */       case GEAR_3:
/* 142 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_ELEPHANT_GATLING_NAME);
/* 143 */         this.cooldown = 250.0F;
/* 144 */         this.triggers = 10;
/* 145 */         this.interval = 5;
/*     */ 
/*     */       
/*     */       case GEAR_4:
/* 149 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_KONG_GATLING_NAME);
/* 150 */         this.cooldown = 200.0F;
/* 151 */         this.triggers = 8;
/* 152 */         this.interval = 5;
/*     */       
/*     */       case GEAR_5:
/*     */         return;
/*     */     } 
/*     */     
/* 158 */     setDisplayIcon(GOMU_GOMU_NO_GATLING_ICON);
/* 159 */     setDisplayName((ITextComponent)GOMU_GOMU_NO_GATLING_NAME);
/* 160 */     this.cooldown = 140.0F;
/* 161 */     this.triggers = 20;
/* 162 */     this.interval = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchNoGear(LivingEntity entity) {
/* 168 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.NO_GEAR);
/*     */   }
/*     */   
/*     */   public void switchSecondGear(LivingEntity entity) {
/* 172 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_2);
/*     */   }
/*     */   
/*     */   public void switchThirdGear(LivingEntity entity) {
/* 176 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_3);
/*     */   }
/*     */   
/*     */   public void switchFourthGear(LivingEntity entity) {
/* 180 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_4);
/*     */   }
/*     */   
/*     */   public void switchFifthGear(LivingEntity entity) {
/* 184 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_5);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxTriggers() {
/* 189 */     return this.triggers;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTriggerInterval() {
/* 194 */     return this.interval;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRepeaterCooldown() {
/* 199 */     return this.cooldown;
/*     */   }
/*     */   
/*     */   public AbilityProjectileEntity getProjectileFactory(LivingEntity entity) {
/*     */     GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile;
/* 204 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 206 */     AbilityProjectileEntity projectile = null;
/*     */     
/* 208 */     this.projectileSpeed = 3.0F;
/* 209 */     float projDmageReduction = 0.8F;
/* 210 */     this.projectileSpread = 2;
/*     */     
/* 212 */     if (GomuHelper.hasGearFourthActive(props)) {
/* 213 */       GomuGomuNoKingKongGunProjectile gomuGomuNoKingKongGunProjectile = new GomuGomuNoKingKongGunProjectile(entity.field_70170_p, entity, (Ability)this);
/* 214 */       gomuGomuNoKingKongGunProjectile.setEntityCollisionSize(2.5D);
/* 215 */       this.projectileSpeed = 2.2F;
/* 216 */       this.projectileSpread = 6;
/* 217 */       projDmageReduction = 0.6F;
/*     */     
/*     */     }
/* 220 */     else if (GomuHelper.hasGearThirdActive(props)) {
/* 221 */       GomuGomuNoElephantGunProjectile gomuGomuNoElephantGunProjectile = new GomuGomuNoElephantGunProjectile(entity.field_70170_p, entity, (Ability)this);
/* 222 */       gomuGomuNoElephantGunProjectile.setEntityCollisionSize(2.5D);
/* 223 */       this.projectileSpeed = 2.4F;
/* 224 */       this.projectileSpread = 9;
/* 225 */       projDmageReduction = 0.6F;
/*     */     }
/* 227 */     else if (GomuHelper.hasGearSecondActive(props)) {
/* 228 */       GomuGomuNoJetPistolProjectile gomuGomuNoJetPistolProjectile = new GomuGomuNoJetPistolProjectile(entity.field_70170_p, entity, (Ability)this);
/* 229 */       this.projectileSpeed = 3.6F;
/*     */     } else {
/*     */       
/* 232 */       gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(entity.field_70170_p, entity);
/*     */     } 
/*     */     
/* 235 */     gomuGomuNoPistolProjectile.setDamage(gomuGomuNoPistolProjectile.getDamage() * (1.0F - projDmageReduction));
/* 236 */     gomuGomuNoPistolProjectile.setMaxLife((int)(gomuGomuNoPistolProjectile.getMaxLife() * 0.75D));
/*     */     
/* 238 */     return (AbilityProjectileEntity)gomuGomuNoPistolProjectile;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoGatlingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */