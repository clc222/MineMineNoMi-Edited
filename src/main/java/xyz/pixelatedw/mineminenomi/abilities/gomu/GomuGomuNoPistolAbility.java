/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import net.minecraft.entity.LivingEntity;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoBajrangGunProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKingKongGunProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GomuGomuNoPistolAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gomu_gomu_no_pistol", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("The user stretches their arm to punch the opponent.", null)
/*     */       });
/*  38 */   private static final TranslationTextComponent GOMU_GOMU_NO_PISTOL_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_pistol", "Gomu Gomu no Pistol"));
/*  39 */   private static final TranslationTextComponent GOMU_GOMU_NO_JET_PISTOL_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_pistol", "Gomu Gomu no Jet Pistol"));
/*  40 */   private static final TranslationTextComponent GOMU_GOMU_NO_ELEPHANT_GUN_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_elephant_gun", "Gomu Gomu no Elephant Gun"));
/*  41 */   private static final TranslationTextComponent GOMU_GOMU_NO_KING_KONG_GUN_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_king_kong_gun", "Gomu Gomu no King Kong Gun"));
/*  42 */   private static final TranslationTextComponent GOMU_GOMU_NO_BAJRANG_GUN_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_bajrang_gun", "Gomu Gomu no Bajrang Gun"));
/*     */   
/*  44 */   private static final ResourceLocation GOMU_GOMU_NO_PISTOL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_pistol.png");
/*  45 */   private static final ResourceLocation GOMU_GOMU_NO_JET_PISTOL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_jet_pistol.png");
/*  46 */   private static final ResourceLocation GOMU_GOMU_NO_ELEPHANT_GUN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_elephant_gun.png");
/*  47 */   private static final ResourceLocation GOMU_GOMU_NO_KING_KONG_GUN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_king_kong_gun.png");
/*  48 */   private static final ResourceLocation GOMU_GOMU_NO_BAJRANG_GUN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_bajrang_gun.png");
/*     */   
/*     */   private static final int NO_GEAR_COOLDOWN = 30;
/*     */   
/*     */   private static final int SECOND_GEAR_COOLDOWN = 20;
/*     */   private static final int THIRD_GEAR_COOLDOWN = 120;
/*     */   private static final int FOURTH_GEAR_COOLDOWN = 80;
/*     */   private static final int FIFTH_GEAR_COOLDOWN = 100;
/*  56 */   private static final AbilityDescriptionLine.IDescriptionLine NO_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_PISTOL_NAME));
/*  57 */   private static final AbilityDescriptionLine.IDescriptionLine SECOND_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_JET_PISTOL_NAME));
/*  58 */   private static final AbilityDescriptionLine.IDescriptionLine THIRD_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_ELEPHANT_GUN_NAME));
/*  59 */   private static final AbilityDescriptionLine.IDescriptionLine FOURTH_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_KING_KONG_GUN_NAME));
/*  60 */   private static final AbilityDescriptionLine.IDescriptionLine FIFTH_GEAR_NAME_DESC = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(GOMU_GOMU_NO_BAJRANG_GUN_NAME));
/*     */   
/*  62 */   public static final AbilityCore<GomuGomuNoPistolAbility> INSTANCE = (new AbilityCore.Builder("Gomu Gomu no Pistol", AbilityCategory.DEVIL_FRUITS, GomuGomuNoPistolAbility::new))
/*  63 */     .addDescriptionLine(DESCRIPTION)
/*  64 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, NO_GEAR_NAME_DESC, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(30.0F)
/*  65 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, SECOND_GEAR_NAME_DESC, GomuHelper.SECOND_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F)
/*  66 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, THIRD_GEAR_NAME_DESC, GomuHelper.THIRD_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F)
/*  67 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, FOURTH_GEAR_NAME_DESC, GomuHelper.FOURTH_GEAR_REQ, AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F)
/*     */       
/*  69 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  70 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  71 */       }).build();
/*     */   
/*  73 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  74 */   private final AltModeComponent<GomuHelper.Gears> altModeComponent = (new AltModeComponent((IAbility)this, GomuHelper.Gears.class, GomuHelper.Gears.NO_GEAR, true)).addChangeModeEvent(this::altModeChangeEvent);
/*     */   
/*  76 */   private float speed = 2.0F;
/*  77 */   private float cooldown = 30.0F;
/*     */   
/*     */   public GomuGomuNoPistolAbility(AbilityCore<GomuGomuNoPistolAbility> core) {
/*  80 */     super(core);
/*     */     
/*  82 */     this.isNew = true;
/*  83 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.altModeComponent });
/*     */     
/*  85 */     addUseEvent(this::useEvent);
/*     */   }
/*     */ 
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  90 */     AbilityProjectileEntity projectile = createProjectile(entity);
/*  91 */     this.projectileComponent.shoot(projectile, entity, this.speed, 0.0F);
/*     */     
/*  93 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*  94 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GOMU_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     
/*  96 */     this.cooldownComponent.startCooldown(entity, this.cooldown);
/*     */   }
/*     */   
/*     */   private void altModeChangeEvent(LivingEntity entity, IAbility ability, GomuHelper.Gears mode) {
/* 100 */     switch (mode) {
/*     */       
/*     */       case GEAR_2:
/* 103 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_JET_PISTOL_NAME);
/* 104 */         this.cooldown = 20.0F;
/*     */         return;
/*     */       
/*     */       case GEAR_3:
/* 108 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_ELEPHANT_GUN_NAME);
/* 109 */         this.cooldown = 120.0F;
/*     */         return;
/*     */       case GEAR_4:
/* 112 */         setDisplayIcon(GOMU_GOMU_NO_KING_KONG_GUN_ICON);
/* 113 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_KING_KONG_GUN_NAME);
/* 114 */         this.cooldown = 80.0F;
/*     */         return;
/*     */       
/*     */       case GEAR_5:
/* 118 */         setDisplayName((ITextComponent)GOMU_GOMU_NO_BAJRANG_GUN_NAME);
/* 119 */         this.cooldown = 100.0F;
/*     */         return;
/*     */     } 
/*     */     
/* 123 */     setDisplayIcon(GOMU_GOMU_NO_PISTOL_ICON);
/* 124 */     setDisplayName((ITextComponent)GOMU_GOMU_NO_PISTOL_NAME);
/* 125 */     this.cooldown = 30.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/*     */     GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile;
/* 131 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 133 */     AbilityProjectileEntity projectile = null;
/* 134 */     this.speed = 2.0F;
/*     */     
/* 136 */     if (GomuHelper.hasGearFifthActive(props)) {
/* 137 */       GomuGomuNoBajrangGunProjectile gomuGomuNoBajrangGunProjectile = new GomuGomuNoBajrangGunProjectile(entity.field_70170_p, entity, this);
/* 138 */       this.speed = 1.9F;
/*     */     }
/* 140 */     else if (GomuHelper.hasGearFourthActive(props)) {
/* 141 */       GomuGomuNoKingKongGunProjectile gomuGomuNoKingKongGunProjectile = new GomuGomuNoKingKongGunProjectile(entity.field_70170_p, entity, this);
/* 142 */       this.speed = 1.8F;
/*     */     }
/* 144 */     else if (GomuHelper.hasGearThirdActive(props)) {
/* 145 */       GomuGomuNoElephantGunProjectile gomuGomuNoElephantGunProjectile = new GomuGomuNoElephantGunProjectile(entity.field_70170_p, entity, this);
/* 146 */       this.speed = 1.8F;
/*     */     }
/* 148 */     else if (GomuHelper.hasGearSecondActive(props)) {
/* 149 */       GomuGomuNoJetPistolProjectile gomuGomuNoJetPistolProjectile = new GomuGomuNoJetPistolProjectile(entity.field_70170_p, entity, this);
/* 150 */       this.speed = 2.5F;
/*     */     } else {
/*     */       
/* 153 */       gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(entity.field_70170_p, entity);
/*     */     } 
/*     */     
/* 156 */     return (AbilityProjectileEntity)gomuGomuNoPistolProjectile;
/*     */   }
/*     */   
/*     */   public void switchNoGear(LivingEntity entity) {
/* 160 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.NO_GEAR);
/*     */   }
/*     */   
/*     */   public void switchSecondGear(LivingEntity entity) {
/* 164 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_2);
/*     */   }
/*     */   
/*     */   public void switchThirdGear(LivingEntity entity) {
/* 168 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_3);
/*     */   }
/*     */   
/*     */   public void switchFourthGear(LivingEntity entity) {
/* 172 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_4);
/*     */   }
/*     */   
/*     */   public void switchFifthGear(LivingEntity entity) {
/* 176 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_5);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoPistolAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */