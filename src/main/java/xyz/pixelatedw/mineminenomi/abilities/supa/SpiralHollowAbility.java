/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.supa.SpiralHollowProjectile;
/*    */ 
/*    */ public class SpiralHollowAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spiral_hollow", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Creates circular blades along the user's forearms slicing enemies in a close line", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 180.0F;
/* 29 */   public static final AbilityCore<SpiralHollowAbility> INSTANCE = (new AbilityCore.Builder("Spiral Hollow", AbilityCategory.DEVIL_FRUITS, SpiralHollowAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 34 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 35 */       }).build();
/*    */   
/* 37 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 38 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   private boolean isDouble = false;
/*    */   
/*    */   public SpiralHollowAbility(AbilityCore<SpiralHollowAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     this.isNew = true;
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 48 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 52 */     this.isDouble = true;
/* 53 */     if (this.isDouble) {
/* 54 */       Vector3d dirVec = Vector3d.field_186680_a;
/* 55 */       Direction dir = Direction.func_176733_a(entity.field_70177_z);
/* 56 */       dirVec = dirVec.func_72441_c(Math.abs(dir.func_176730_m().func_177958_n()), Math.abs(dir.func_176730_m().func_177956_o()), Math.abs(dir.func_176730_m().func_177952_p())).func_216372_d(1.2D, 1.0D, 1.2D);
/*    */       
/* 58 */       double yPos = entity.func_226278_cu_() + entity.func_70047_e() - 0.5D;
/*    */       
/* 60 */       SpiralHollowProjectile leftProj = (SpiralHollowProjectile)this.projectileComponent.getNewProjectile(entity);
/* 61 */       leftProj.func_70012_b(entity.func_226277_ct_() + dirVec.field_72449_c, yPos, entity.func_226281_cx_() + dirVec.field_72450_a, 0.0F, 0.0F);
/* 62 */       entity.field_70170_p.func_217376_c((Entity)leftProj);
/* 63 */       leftProj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/*    */       
/* 65 */       SpiralHollowProjectile rightProj = (SpiralHollowProjectile)this.projectileComponent.getNewProjectile(entity);
/* 66 */       rightProj.func_70012_b(entity.func_226277_ct_() - dirVec.field_72449_c, yPos, entity.func_226281_cx_() - dirVec.field_72450_a, 0.0F, 0.0F);
/* 67 */       entity.field_70170_p.func_217376_c((Entity)rightProj);
/* 68 */       rightProj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/*    */     } else {
/*    */       
/* 71 */       this.projectileComponent.shoot(entity);
/*    */     } 
/*    */     
/* 74 */     entity.func_226292_a_(entity.func_184600_cs(), true);
/*    */     
/* 76 */     this.cooldownComponent.startCooldown(entity, 180.0F);
/*    */   }
/*    */   
/*    */   public void setDouble() {
/* 80 */     this.isDouble = true;
/*    */   }
/*    */   
/*    */   private SpiralHollowProjectile createProjectile(LivingEntity entity) {
/* 84 */     SpiralHollowProjectile proj = new SpiralHollowProjectile(entity.field_70170_p, entity);
/* 85 */     proj.setPassThroughEntities();
/* 86 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SpiralHollowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */