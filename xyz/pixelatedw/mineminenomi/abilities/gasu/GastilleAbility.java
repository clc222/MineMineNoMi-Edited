/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.BigGastilleProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GastilleProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class GastilleAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gastille", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Shoots a beam of lit gas from the users mouth, that explodes on impact", null), 
/* 31 */         (Pair)ImmutablePair.of("If %s is active a bigger and more destructive laser will be shot.", new Object[] { ShinokuniAbility.INSTANCE })
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 140;
/* 35 */   public static final AbilityCore<GastilleAbility> INSTANCE = (new AbilityCore.Builder("Gastille", AbilityCategory.DEVIL_FRUITS, GastilleAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F)
/* 38 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 39 */     .setSourceElement(SourceElement.EXPLOSION)
/* 40 */     .build();
/*    */   
/* 42 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public GastilleAbility(AbilityCore<GastilleAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 50 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 54 */     float projSpeed = 5.5F;
/* 55 */     if (((MorphInfo)ModMorphs.SHINOKUNI.get()).isActive(entity)) {
/* 56 */       projSpeed = 8.0F;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 61 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 64.0D);
/*    */     
/* 63 */     double beamDistance = Math.sqrt(entity.func_70092_e((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c));
/*    */     
/* 65 */     float damage = 50.0F;
/* 66 */     float size = 0.25F;
/* 67 */     float length = 50.0F;
/*    */     
/* 69 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, 1.15D, 0.8D);
/*    */     
/* 71 */     LightningEntity bolt = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A, length + (float)beamDistance, projSpeed, getCore());
/*    */     
/* 73 */     bolt.setBlocksAffectedLimit(1508);
/* 74 */     bolt.setMaxLife(40);
/* 75 */     bolt.setDamage(damage);
/*    */     
/* 77 */     bolt.setExplosion(5, true, 0.3F);
/*    */     
/* 79 */     bolt.setSize(size);
/* 80 */     bolt.setBoxSizeDivision(1.0D);
/* 81 */     bolt.setColor(new Color(13397929));
/* 82 */     bolt.setAngle(100);
/* 83 */     bolt.setTargetTimeToReset(6000);
/* 84 */     bolt.disableExplosionKnockback();
/* 85 */     bolt.setBranches(1);
/* 86 */     bolt.setSegments(1);
/*    */     
/* 88 */     entity.field_70170_p.func_217376_c((Entity)bolt);
/*    */     
/* 90 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*    */   }
/*    */   
/*    */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/* 94 */     if (((MorphInfo)ModMorphs.SHINOKUNI.get()).isActive(entity)) {
/* 95 */       return (AbilityProjectileEntity)new BigGastilleProjectile(entity.field_70170_p, entity);
/*    */     }
/* 97 */     return (AbilityProjectileEntity)new GastilleProjectile(entity.field_70170_p, entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GastilleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */