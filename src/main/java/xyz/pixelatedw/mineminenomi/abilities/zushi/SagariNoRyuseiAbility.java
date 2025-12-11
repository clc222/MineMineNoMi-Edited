/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.SagariNoRyuseiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SagariNoRyuseiAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sagari_no_ryusei", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Using gravity, the user pulls one (or rarely two) meteorites down on their enemies.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 900;
/* 29 */   public static final AbilityCore<SagariNoRyuseiAbility> INSTANCE = (new AbilityCore.Builder("Sagari no Ryusei", AbilityCategory.DEVIL_FRUITS, SagariNoRyuseiAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(900.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .build();
/*    */   
/* 35 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 36 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 37 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   private int count;
/*    */   
/*    */   public SagariNoRyuseiAbility(AbilityCore<SagariNoRyuseiAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 47 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.count = 0;
/*    */     
/* 53 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM, 7);
/*    */     
/* 55 */     for (int i = 2; i < 16; i += 2) {
/* 56 */       GraviZoneAbility.gravityRing(entity, 4, i, false);
/*    */     }
/*    */     
/* 59 */     boolean has2nd = (entity.func_70681_au().nextInt(3) == 0);
/*    */     
/* 61 */     SagariNoRyuseiProjectile proj1 = (SagariNoRyuseiProjectile)this.projectileComponent.getNewProjectile(entity);
/* 62 */     entity.field_70170_p.func_217376_c((Entity)proj1);
/*    */     
/* 64 */     if (has2nd) {
/* 65 */       this.count++;
/* 66 */       SagariNoRyuseiProjectile proj2 = (SagariNoRyuseiProjectile)this.projectileComponent.getNewProjectile(entity);
/* 67 */       entity.field_70170_p.func_217376_c((Entity)proj2);
/*    */     } 
/*    */     
/* 70 */     this.cooldownComponent.startCooldown(entity, 900.0F);
/*    */   }
/*    */   
/*    */   private SagariNoRyuseiProjectile createProjectile(LivingEntity entity) {
/* 74 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity);
/*    */     
/* 76 */     double x = (mop.func_216347_e()).field_72450_a;
/* 77 */     double y = (mop.func_216347_e()).field_72448_b;
/* 78 */     double z = (mop.func_216347_e()).field_72449_c;
/*    */     
/* 80 */     float size = (this.count == 0) ? (float)WyHelper.randomWithRange(24, 30) : (float)WyHelper.randomWithRange(8, 10);
/*    */     
/* 82 */     SagariNoRyuseiProjectile proj = new SagariNoRyuseiProjectile(entity.field_70170_p, entity);
/* 83 */     proj.setSize(size);
/* 84 */     proj.func_70107_b(x, y + 90.0D, z);
/* 85 */     proj.field_70125_A = 0.0F;
/* 86 */     proj.field_70177_z = 0.0F;
/* 87 */     AbilityHelper.setDeltaMovement((Entity)proj, 0.0D, -1.85D, 0.0D);
/*    */     
/* 89 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\SagariNoRyuseiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */