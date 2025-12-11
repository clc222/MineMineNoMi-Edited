/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RokuoganProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class RokuoganAbility extends PunchAbility2 {
/* 33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "rokuogan", new Pair[] {
/* 34 */         (Pair)ImmutablePair.of("The user places both their fists right in front of the target to focus their physical strength to launch a devastating shockwave forward", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 700.0F;
/* 38 */   public static final AbilityCore<RokuoganAbility> INSTANCE = (new AbilityCore.Builder("Rokuogan", AbilityCategory.RACIAL, RokuoganAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(700.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 41 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 42 */     .setSourceElement(SourceElement.SHOCKWAVE)
/* 43 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 44 */       }).setUnlockCheck(RokuoganAbility::canUnlock)
/* 45 */     .build();
/*    */   
/* 47 */   private ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public RokuoganAbility(AbilityCore<RokuoganAbility> core) {
/* 50 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 55 */     RokuoganProjectile proj = (RokuoganProjectile)this.projectileComponent.getNewProjectile(entity);
/* 56 */     entity.field_70170_p.func_217376_c((Entity)proj);
/* 57 */     proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/* 58 */     Vector3d lookVec = entity.func_70040_Z().func_216372_d(2.0D, 2.0D, 2.0D);
/* 59 */     proj.func_70107_b(target.func_226277_ct_() + lookVec.field_72450_a, target.func_226278_cu_() + lookVec.field_72448_b, target.func_226281_cx_() + lookVec.field_72449_c);
/*    */     
/* 61 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.ROKUOGAN.get(), SoundCategory.PLAYERS, 2.5F, 0.2F + entity.func_70681_au().nextFloat());
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 68 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 73 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 78 */     return 60.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 83 */     return 700.0F;
/*    */   }
/*    */   
/*    */   private RokuoganProjectile createProjectile(LivingEntity entity) {
/* 87 */     RokuoganProjectile proj = new RokuoganProjectile(entity.field_70170_p, entity, (Ability)this);
/* 88 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 92 */     IEntityStats props = EntityStatsCapability.get(user);
/* 93 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 94 */     return (raceCheck && props.getDoriki() >= 9000.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\RokuoganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */