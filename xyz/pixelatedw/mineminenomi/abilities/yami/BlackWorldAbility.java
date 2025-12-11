/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.IWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackWorldAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "black_world", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("The user spreads their power to the surroundings, blinding enemies and creating pillars of Darkness.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/* 34 */   public static final AbilityCore<BlackWorldAbility> INSTANCE = (new AbilityCore.Builder("Black World", AbilityCategory.DEVIL_FRUITS, BlackWorldAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/* 37 */       }).build();
/*    */   
/* 39 */   private static final BlockProtectionRule GRIEF_RULE = AirBlockProtectionRule.INSTANCE;
/*    */   
/*    */   public BlackWorldAbility(AbilityCore<BlackWorldAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent[0]);
/*    */     
/* 47 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 51 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 55 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLACK_WORLD.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 57 */     BlockState defaultDarknessState = ((Block)ModBlocks.DARKNESS.get()).func_176223_P();
/*    */     
/* 59 */     for (int i = 0; i < 12; i++) {
/* 60 */       Vector3d vec = Vector3d.func_189986_a(0.0F, entity.func_70681_au().nextInt(360)).func_72432_b().func_186678_a((5 + entity.func_70681_au().nextInt(10)));
/*    */       
/* 62 */       for (int j = -5; j < 5; j++) {
/* 63 */         AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177963_a(vec.field_72450_a, j, vec.field_72449_c), defaultDarknessState, GRIEF_RULE);
/* 64 */         AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177963_a(vec.field_72450_a + 1.0D, j, vec.field_72449_c), defaultDarknessState, GRIEF_RULE);
/* 65 */         AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177963_a(vec.field_72450_a, j, vec.field_72449_c + 1.0D), defaultDarknessState, GRIEF_RULE);
/* 66 */         AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177963_a(vec.field_72450_a + 1.0D, j, vec.field_72449_c + 1.0D), defaultDarknessState, GRIEF_RULE);
/*    */       } 
/*    */     } 
/*    */     
/* 70 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 20.0D, ModEntityPredicates.getEnemyFactions(entity));
/*    */     
/* 72 */     for (LivingEntity target : targets) {
/* 73 */       target.func_195064_c(new EffectInstance(Effects.field_76440_q, 200, 1, false, false));
/* 74 */       target.func_195064_c(new EffectInstance(Effects.field_76421_d, 200, 1, false, false));
/*    */     } 
/*    */     
/* 77 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\BlackWorldAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */