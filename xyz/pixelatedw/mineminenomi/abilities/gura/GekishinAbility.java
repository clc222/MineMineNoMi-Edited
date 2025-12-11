/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gura.GekishinProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GekishinAbility extends Ability {
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gekishin", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("The user 'cracks' the air, launching a single concentrated shockwave forward which send blocks flying and deals damage to all enemies in its path", null), 
/*  43 */         (Pair)ImmutablePair.of("The user 'cracks' the air on their sides, launching two smaller shockwaves which send blocks flying and deals damage to all enemies in their path", null)
/*     */       });
/*  45 */   private static final TranslationTextComponent GEKISHIN_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gekishin", "Gekishin"));
/*  46 */   private static final TranslationTextComponent KAISHIN_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.kaishin", "Kaishin"));
/*     */   
/*  48 */   private static final ResourceLocation GEKISHIN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gekishin.png");
/*  49 */   private static final ResourceLocation KAISHIN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/kaishin.png");
/*     */   
/*  51 */   private static final AirCrackParticleEffect PARTICLES = new AirCrackParticleEffect();
/*     */   
/*     */   private static final float GEKISHIN_COOLDOWN = 240.0F;
/*     */   
/*     */   private static final float GEKISHIN_CHARGE_TIME = 20.0F;
/*     */   private static final float KAISHIN_COOLDOWN = 480.0F;
/*     */   private static final float KAISHIN_CHARGE_TIME = 40.0F;
/*  58 */   public static final AbilityCore<GekishinAbility> INSTANCE = (new AbilityCore.Builder("Gekishin", AbilityCategory.DEVIL_FRUITS, GekishinAbility::new))
/*  59 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/*  62 */         (e, a) -> GEKISHIN_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[0], CooldownComponent.getTooltip(240.0F), 
/*  63 */         ChargeComponent.getTooltip(20.0F)
/*  64 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/*  67 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> KAISHIN_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[1], CooldownComponent.getTooltip(480.0F), 
/*  68 */         ChargeComponent.getTooltip(40.0F)
/*  69 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/*  70 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  71 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/*  72 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/*  73 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  74 */     .build();
/*     */   
/*  76 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::onChargeStart).addEndEvent(this::onChargeEnd);
/*  77 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.GEKISHIN)).addChangeModeEvent(this::onAltModeChange);
/*  78 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  79 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   public GekishinAbility(AbilityCore<GekishinAbility> core) {
/*  82 */     super(core);
/*     */     
/*  84 */     this.isNew = true;
/*     */     
/*  86 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  88 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  92 */     if (this.altModeComponent.getCurrentMode() == Mode.KAISHIN) {
/*  93 */       this.chargeComponent.startCharging(entity, 40.0F);
/*  94 */     } else if (this.altModeComponent.getCurrentMode() == Mode.GEKISHIN) {
/*  95 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 100 */     this.animationComponent.start(entity, ModAnimations.KAISHIN);
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 104 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 108 */     if (this.altModeComponent.getCurrentMode() == Mode.GEKISHIN) {
/* 109 */       EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, 1.5D);
/*     */       
/* 111 */       PARTICLES.spawn(entity.field_70170_p, trace.func_216347_e().func_82615_a(), entity.func_226278_cu_() + 0.5D, trace.func_216347_e().func_82616_c(), 0.0D, 0.0D, 0.0D);
/*     */       
/* 113 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GURA_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */       
/* 115 */       this.projectileComponent.shoot(entity, 3.0F, 1.0F);
/* 116 */     } else if (this.altModeComponent.getCurrentMode() == Mode.KAISHIN) {
/* 117 */       Vector3d v1 = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D).func_178787_e(entity.func_70040_Z().func_186678_a(2.5D).func_178785_b(180.0F));
/* 118 */       Vector3d v2 = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D).func_178787_e(entity.func_70040_Z().func_186678_a(2.5D).func_178785_b(-180.0F));
/*     */       
/* 120 */       PARTICLES.spawn(entity.field_70170_p, v1.func_82615_a(), entity.func_226278_cu_() + 0.5D, v1.func_82616_c(), 0.0D, 0.0D, 0.0D);
/* 121 */       PARTICLES.spawn(entity.field_70170_p, v2.func_82615_a(), entity.func_226278_cu_() + 0.5D, v2.func_82616_c(), 0.0D, 0.0D, 0.0D);
/*     */       
/* 123 */       double range = 8.0D;
/*     */       
/* 125 */       List<Entity> list = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, range, ModEntityPredicates.getEnemyFactions(entity), new Class[] { Entity.class });
/*     */       
/* 127 */       for (Entity target : list) {
/* 128 */         if (target instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)target).getDamage() < 20.0F && ((AbilityProjectileEntity)target).isPhysical()) {
/* 129 */           target.func_70106_y();
/*     */         }
/*     */         
/* 132 */         if (target instanceof LivingEntity) {
/* 133 */           Vector3d speed = target.func_70040_Z().func_216372_d(3.0D, 2.0D, 3.0D);
/* 134 */           AbilityHelper.setDeltaMovement(target, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/* 135 */           target.field_70143_R = 0.0F;
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GURA_SFX.get(), SoundCategory.PLAYERS, 0.5F, 1.0F);
/*     */       
/* 141 */       GekishinProjectile p1 = (GekishinProjectile)this.projectileComponent.getNewProjectile(entity);
/*     */       
/* 143 */       p1.setMaxLife((int)(p1.getMaxLife() * 0.5F));
/*     */       
/* 145 */       entity.field_70170_p.func_217376_c((Entity)p1);
/*     */       
/* 147 */       p1.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z + 90.0F, 0.0F, 1.75F, 1.0F);
/*     */       
/* 149 */       GekishinProjectile p2 = (GekishinProjectile)this.projectileComponent.getNewProjectile(entity);
/*     */       
/* 151 */       p2.setMaxLife((int)(p2.getMaxLife() * 0.5F));
/*     */       
/* 153 */       entity.field_70170_p.func_217376_c((Entity)p2);
/*     */       
/* 155 */       p2.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z - 90.0F, 0.0F, 1.75F, 1.0F);
/*     */     } 
/*     */     
/* 158 */     this.animationComponent.stop(entity);
/* 159 */     if (this.altModeComponent.getCurrentMode() == Mode.KAISHIN) {
/* 160 */       this.cooldownComponent.startCooldown(entity, 480.0F);
/* 161 */     } else if (this.altModeComponent.getCurrentMode() == Mode.GEKISHIN) {
/* 162 */       this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 167 */     if (mode == Mode.KAISHIN) {
/* 168 */       setDisplayName((ITextComponent)KAISHIN_NAME);
/* 169 */       setDisplayIcon(KAISHIN_ICON);
/* 170 */     } else if (mode == Mode.GEKISHIN) {
/* 171 */       setDisplayName((ITextComponent)GEKISHIN_NAME);
/* 172 */       setDisplayIcon(GEKISHIN_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private GekishinProjectile createProjectile(LivingEntity entity) {
/* 177 */     GekishinProjectile proj = new GekishinProjectile(entity.field_70170_p, entity, this);
/* 178 */     return proj;
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 182 */     GEKISHIN, KAISHIN;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\GekishinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */