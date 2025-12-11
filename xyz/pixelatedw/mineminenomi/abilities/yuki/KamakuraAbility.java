/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yuki.KamakuraParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KamakuraAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kamakura", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Creates an igloo-like snow barrier where the user's pointing.", null), 
/* 30 */         (Pair)ImmutablePair.of("If used while crouching it'll create the igloo around the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 160;
/* 34 */   public static final AbilityCore<KamakuraAbility> INSTANCE = (new AbilityCore.Builder("Kamakura", AbilityCategory.DEVIL_FRUITS, KamakuraAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 37 */       }).build();
/*    */   
/* 39 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.field_150433_aE }).build();
/* 40 */   private static final KamakuraParticleEffect.Details DETAILS = new KamakuraParticleEffect.Details(4);
/*    */   
/*    */   public KamakuraAbility(AbilityCore<KamakuraAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     this.isNew = true;
/*    */     
/* 47 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     World world = entity.field_70170_p;
/*    */     
/* 53 */     Vector3d vec3d = entity.func_213453_ef() ? entity.func_213303_ch() : WyHelper.rayTraceBlocksAndEntities((Entity)entity, 64.0D).func_216347_e();
/*    */     
/* 55 */     AbilityHelper.createEmptySphere(world, (int)vec3d.field_72450_a, (int)vec3d.field_72448_b, (int)vec3d.field_72449_c, 4, (Block)ModBlocks.HARDENED_SNOW.get(), GRIEF_RULE);
/*    */     
/* 57 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KAMAKURA.get(), (Entity)entity, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, (ParticleEffect.Details)DETAILS);
/*    */     
/* 59 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\KamakuraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */