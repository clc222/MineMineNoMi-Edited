/*     */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.StringPillarProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FullbrightAbility extends Ability {
/*  25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "fullbright", new Pair[] {
/*  26 */         (Pair)ImmutablePair.of("Throws five strings to impale a target from above.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int CHARGE_TIME = 20;
/*  31 */   public static final AbilityCore<FullbrightAbility> INSTANCE = (new AbilityCore.Builder("Fullbright", AbilityCategory.DEVIL_FRUITS, FullbrightAbility::new))
/*  32 */     .addDescriptionLine(DESCRIPTION)
/*  33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(20.0F)
/*  34 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  35 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  36 */     .build();
/*     */   
/*  38 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::startChargeEvent).addEndEvent(100, this::endChargeEvent);
/*  39 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   private LivingEntity target;
/*     */   
/*     */   public FullbrightAbility(AbilityCore<FullbrightAbility> core) {
/*  45 */     super(core);
/*     */     
/*  47 */     this.isNew = true;
/*  48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  50 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  54 */     this.chargeComponent.startCharging(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  58 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM, 20);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  62 */     RayTraceResult mop = null;
/*  63 */     if (this.target == null) {
/*  64 */       mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 32.0D);
/*     */     }
/*     */     
/*  67 */     for (int a = 0; a < 5; a++) {
/*  68 */       double x = 0.0D;
/*  69 */       double y = 0.0D;
/*  70 */       double z = 0.0D;
/*     */       
/*  72 */       if (this.target != null) {
/*  73 */         x = this.target.func_226277_ct_();
/*  74 */         y = this.target.func_226278_cu_();
/*  75 */         z = this.target.func_226281_cx_();
/*     */       }
/*  77 */       else if (mop != null) {
/*  78 */         x = (mop.func_216347_e()).field_72450_a;
/*  79 */         y = (mop.func_216347_e()).field_72448_b;
/*  80 */         z = (mop.func_216347_e()).field_72449_c;
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/*  86 */       double i = x + WyHelper.randomDouble() * 2.0D;
/*  87 */       double j = y;
/*  88 */       double k = z + WyHelper.randomDouble() * 2.0D;
/*     */       
/*  90 */       StringPillarProjectile pillar = (StringPillarProjectile)this.projectileComponent.getNewProjectile(entity);
/*  91 */       pillar.func_70107_b(i, j + 24.0D, k);
/*  92 */       AbilityHelper.setDeltaMovement((Entity)pillar, 0.0D, -1.75D, 0.0D);
/*  93 */       entity.field_70170_p.func_217376_c((Entity)pillar);
/*     */     } 
/*     */     
/*  96 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private StringPillarProjectile createProjectile(LivingEntity entity) {
/* 100 */     StringPillarProjectile pillar = new StringPillarProjectile(entity.field_70170_p, entity, this);
/* 101 */     pillar.field_70125_A = 90.0F;
/* 102 */     return pillar;
/*     */   }
/*     */   
/*     */   public void setTarget(LivingEntity target) {
/* 106 */     this.target = target;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\FullbrightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */