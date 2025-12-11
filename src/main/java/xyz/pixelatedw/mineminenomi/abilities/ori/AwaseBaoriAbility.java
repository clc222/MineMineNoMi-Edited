/*    */ package xyz.pixelatedw.mineminenomi.abilities.ori;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ori.AwaseBaoriProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class AwaseBaoriAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "awase_baori", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Launches a short range projectile that creates a small cage around the hit target.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final int HOLD_TIME = 100;
/* 33 */   public static final AbilityCore<AwaseBaoriAbility> INSTANCE = (new AbilityCore.Builder("Awase Baori", AbilityCategory.DEVIL_FRUITS, AwaseBaoriAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(100.0F)
/* 36 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 37 */     .build();
/*    */   
/* 39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   private AwaseBaoriProjectile proj;
/* 43 */   private List<BlockPos> placedBlocks = new ArrayList<>();
/*    */   
/*    */   public AwaseBaoriAbility(AbilityCore<AwaseBaoriAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.proj = (AwaseBaoriProjectile)this.projectileComponent.getNewProjectile(entity);
/* 60 */     entity.field_70170_p.func_217376_c((Entity)this.proj);
/* 61 */     this.proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 65 */     if (this.placedBlocks.isEmpty()) {
/* 66 */       if (this.proj != null && this.proj.getBlocks() != null) {
/* 67 */         this.placedBlocks = this.proj.getBlocks();
/*    */         
/*    */         return;
/*    */       } 
/* 71 */       if (this.proj == null || !this.proj.func_70089_S()) {
/* 72 */         this.continuousComponent.stopContinuity(entity);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 79 */     this.proj.func_70106_y();
/* 80 */     for (BlockPos pos : this.placedBlocks) {
/* 81 */       if (entity.field_70170_p.func_180495_p(pos).func_177230_c() == ModBlocks.ORI_BARS.get()) {
/* 82 */         entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*    */       }
/*    */     } 
/* 85 */     this.placedBlocks.clear();
/*    */     
/* 87 */     this.cooldownComponent.stopCooldown(entity);
/*    */   }
/*    */   
/*    */   private AwaseBaoriProjectile createProjectile(LivingEntity entity) {
/* 91 */     AwaseBaoriProjectile proj = new AwaseBaoriProjectile(entity.field_70170_p, entity);
/* 92 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ori\AwaseBaoriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */