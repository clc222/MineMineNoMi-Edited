/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AncientBiteAbility extends PunchAbility2 {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ancient_bite", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Bites the enemy dealing moderate damage.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 140;
/* 37 */   public static final AbilityCore<AncientBiteAbility> INSTANCE = (new AbilityCore.Builder("Ancient Bite", AbilityCategory.DEVIL_FRUITS, AncientBiteAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 40 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 41 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 42 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 43 */       }).build();
/*    */   
/* 45 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.ALLOSAURUS_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.ALLOSAURUS_WALK.get() });
/*    */   
/*    */   public AncientBiteAbility(AbilityCore<AncientBiteAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 55 */     double x = WyHelper.randomDouble() * 2.0D;
/* 56 */     double y = WyHelper.randomDouble() * 0.3D;
/* 57 */     double z = WyHelper.randomDouble() * 2.0D;
/*    */     
/* 59 */     entity.field_70170_p.func_195594_a((IParticleData)ParticleTypes.field_197614_g, target.func_226277_ct_(), target.func_226278_cu_() + 1.0D, target.func_226281_cx_(), x, y, z);
/* 60 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.BLEEDING.get(), 20, 0));
/*    */     
/* 62 */     for (int i = 0; i < 50; i++) {
/* 63 */       Vector3d vec3d = new Vector3d((entity.func_70681_au().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 64 */       vec3d = vec3d.func_178789_a(-entity.field_70125_A * 0.017453292F);
/* 65 */       vec3d = vec3d.func_178785_b(-entity.field_70177_z * 0.017453292F);
/* 66 */       ((ServerWorld)entity.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197614_g, target.func_226277_ct_(), target.func_226278_cu_() + 1.5D, target
/* 67 */           .func_226281_cx_(), 1, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 0.8D);
/*    */     } 
/*    */     
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 75 */     return entity -> this.continuousComponent.isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 80 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 85 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 90 */     return 140.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryuallosaurus\AncientBiteAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */