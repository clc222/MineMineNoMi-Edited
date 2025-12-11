/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.TsunoTokagePillarEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TsunoTokageAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tsuno_tokage", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("The user creates a lizard-like shadow under his opponent, which pierces them from below", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/* 27 */   public static final AbilityCore<TsunoTokageAbility> INSTANCE = (new AbilityCore.Builder("Tsuno Tokage", AbilityCategory.DEVIL_FRUITS, TsunoTokageAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/* 30 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 31 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 32 */     .build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public TsunoTokageAbility(AbilityCore<TsunoTokageAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 42 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 46 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 64.0D);
/*    */     
/* 48 */     if (mop.func_216346_c() == RayTraceResult.Type.MISS) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     double i = (mop.func_216347_e()).field_72450_a;
/* 53 */     double j = (mop.func_216347_e()).field_72448_b;
/* 54 */     double k = (mop.func_216347_e()).field_72449_c;
/*    */     
/* 56 */     TsunoTokagePillarEntity pillar = (TsunoTokagePillarEntity)this.projectileComponent.getNewProjectile(entity);
/* 57 */     pillar.func_70012_b(i, j - 6.0D, k, 0.0F, 0.0F);
/* 58 */     pillar.func_70186_c(0.0D, 0.7D, 0.0D, 1.4F, 0.0F);
/* 59 */     entity.field_70170_p.func_217376_c((Entity)pillar);
/*    */     
/* 61 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private TsunoTokagePillarEntity createProjectile(LivingEntity entity) {
/* 65 */     TsunoTokagePillarEntity pillar = new TsunoTokagePillarEntity(entity.field_70170_p, entity);
/* 66 */     return pillar;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\TsunoTokageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */