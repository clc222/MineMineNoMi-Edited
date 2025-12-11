/*     */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.dispenser.IPosition;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.VenomRoadProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class VenomRoadAbility extends Ability {
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "venom_road", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Fires a Hydra at the target location that stays there for a few seconds during which time the user can use them to move along their path.", null)
/*     */       });
/*  36 */   private static final ITextComponent NORMAL_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.venom_road", "Venom Road"));
/*  37 */   private static final ITextComponent VENOM_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.venom_road_venom", "Demon Road"));
/*     */   
/*  39 */   private static final ResourceLocation NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/venom_road.png");
/*  40 */   private static final ResourceLocation VENOM_ICON = new ResourceLocation("mineminenomi", "textures/abilities/venom_road_venom.png");
/*     */   
/*     */   public static final int COOLDOWN = 80;
/*     */   
/*  44 */   public static final AbilityCore<VenomRoadAbility> INSTANCE = (new AbilityCore.Builder("Venom Road", AbilityCategory.DEVIL_FRUITS, VenomRoadAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F)
/*  47 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  48 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  49 */     .setSourceElement(SourceElement.POISON)
/*  50 */     .build();
/*     */   
/*  52 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  53 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   private boolean isMovingOwner;
/*     */   private int firstTick;
/*     */   private int ticks;
/*  59 */   private List<Pair<Vector3d, VenomRoadProjectile>> projectiles = new ArrayList<>();
/*     */   private VenomRoadProjectile projectileUsed;
/*     */   
/*     */   public VenomRoadAbility(AbilityCore<VenomRoadAbility> core) {
/*  63 */     super(core);
/*     */     
/*  65 */     this.isNew = true;
/*  66 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  68 */     addUseEvent(this::useEvent);
/*  69 */     addTickEvent(this::tickEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  73 */     if (this.isMovingOwner) {
/*     */       return;
/*     */     }
/*     */     
/*  77 */     boolean isNearStartPoint = false;
/*  78 */     for (Pair<Vector3d, VenomRoadProjectile> pair : this.projectiles) {
/*  79 */       if (pair.getValue() == null || !((VenomRoadProjectile)pair.getValue()).func_70089_S()) {
/*     */         continue;
/*     */       }
/*  82 */       if (entity.func_213303_ch().func_237488_a_((IPosition)pair.getKey(), 10.0D)) {
/*  83 */         isNearStartPoint = true;
/*  84 */         this.projectileUsed = (VenomRoadProjectile)pair.getValue();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  89 */     if (isNearStartPoint && AbilityHelper.canUseMomentumAbilities(entity)) {
/*  90 */       entity.func_233576_c_(this.projectileUsed.getStartPos().func_72441_c(0.0D, 1.0D, 0.0D));
/*  91 */       this.firstTick = this.ticks;
/*  92 */       this.isMovingOwner = true;
/*  93 */       this.animationComponent.start(entity, ModAnimations.SHOOT_SELF_FORWARD, 30);
/*     */     } else {
/*     */       
/*  96 */       this.projectiles.removeIf(pair -> (pair.getValue() == null || !((VenomRoadProjectile)pair.getValue()).func_70089_S()));
/*  97 */       if (this.projectiles.size() > 2) {
/*  98 */         Pair<Vector3d, VenomRoadProjectile> firstPair = this.projectiles.stream().findFirst().orElse(null);
/*  99 */         if (firstPair == null) {
/*     */           return;
/*     */         }
/*     */         
/* 103 */         ((VenomRoadProjectile)firstPair.getValue()).func_70106_y();
/* 104 */         this.projectiles.remove(firstPair);
/*     */       } 
/*     */ 
/*     */       
/* 108 */       this.isMovingOwner = false;
/* 109 */       VenomRoadProjectile projectile = (VenomRoadProjectile)this.projectileComponent.getNewProjectile(entity);
/* 110 */       entity.field_70170_p.func_217376_c((Entity)projectile);
/* 111 */       projectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 3.0F, 1.0F);
/* 112 */       ImmutablePair immutablePair = ImmutablePair.of(entity.func_213303_ch(), projectile);
/* 113 */       this.projectiles.add(immutablePair);
/* 114 */       this.cooldownComponent.startCooldown(entity, 80.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private VenomRoadProjectile createProjectile(LivingEntity entity) {
/* 119 */     boolean isDemonForm = ((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(entity);
/* 120 */     VenomRoadProjectile projectile = new VenomRoadProjectile(entity.field_70170_p, entity, this, isDemonForm);
/* 121 */     return projectile;
/*     */   }
/*     */   
/*     */   public void tickEvent(LivingEntity entity, IAbility ability) {
/* 125 */     this.ticks++;
/*     */     
/* 127 */     if (!entity.field_70170_p.field_72995_K && this.isMovingOwner) {
/* 128 */       boolean hasArrived = entity.func_213303_ch().func_237488_a_((IPosition)this.projectileUsed.func_213303_ch(), 10.0D);
/* 129 */       boolean pushedForTooLong = (this.ticks - this.firstTick > 40);
/*     */       
/* 131 */       if (hasArrived || pushedForTooLong) {
/* 132 */         this.isMovingOwner = false;
/* 133 */         this.cooldownComponent.startCooldown(entity, 80.0F);
/* 134 */         entity.field_70143_R = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 139 */       if (AbilityHelper.canUseMomentumAbilities(entity)) {
/* 140 */         Vector3d vec = this.projectileUsed.func_213303_ch().func_72441_c(0.0D, 1.0D, 0.0D).func_178788_d(this.projectileUsed.getStartPos()).func_72432_b().func_186678_a(3.0D);
/* 141 */         AbilityHelper.setDeltaMovement((Entity)entity, vec);
/* 142 */         entity.field_70143_R = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNormalMode(LivingEntity entity) {
/* 148 */     this.altModeComponent.setMode(entity, Mode.NORMAL);
/*     */   }
/*     */   
/*     */   public void setVenomMode(LivingEntity entity) {
/* 152 */     this.altModeComponent.setMode(entity, Mode.VENOM);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 156 */     if (mode == Mode.VENOM) {
/* 157 */       setDisplayName(VENOM_NAME);
/* 158 */       setDisplayIcon(VENOM_ICON);
/*     */     }
/* 160 */     else if (mode == Mode.NORMAL) {
/* 161 */       setDisplayName(NORMAL_NAME);
/* 162 */       setDisplayIcon(NORMAL_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private enum Mode {
/* 167 */     NORMAL, VENOM;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\VenomRoadAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */