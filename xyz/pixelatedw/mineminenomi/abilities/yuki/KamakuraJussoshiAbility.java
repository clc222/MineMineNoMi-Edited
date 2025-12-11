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
/*    */ public class KamakuraJussoshiAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kamakura_jussoshi", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Like 'Kamakura', but creates a multi-layered snow barrier.", null), 
/* 30 */         (Pair)ImmutablePair.of("If used while crouching it'll create the igloo around the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/* 34 */   public static final AbilityCore<KamakuraJussoshiAbility> INSTANCE = (new AbilityCore.Builder("Kamakura Jussoshi", AbilityCategory.DEVIL_FRUITS, KamakuraJussoshiAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 37 */       }).build();
/*    */   
/* 39 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.field_150433_aE }).build();
/* 40 */   private static final KamakuraParticleEffect.Details DETAILS = new KamakuraParticleEffect.Details(8);
/*    */   
/*    */   public KamakuraJussoshiAbility(AbilityCore<KamakuraJussoshiAbility> core) {
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
/* 55 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KAMAKURA.get(), (Entity)entity, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, (ParticleEffect.Details)DETAILS);
/*    */     
/* 57 */     AbilityHelper.createEmptySphere(world, (int)vec3d.field_72450_a, (int)vec3d.field_72448_b, (int)vec3d.field_72449_c, 4, (Block)ModBlocks.HARDENED_SNOW.get(), GRIEF_RULE);
/* 58 */     AbilityHelper.createEmptySphere(world, (int)vec3d.field_72450_a, (int)vec3d.field_72448_b, (int)vec3d.field_72449_c, 6, (Block)ModBlocks.HARDENED_SNOW.get(), GRIEF_RULE);
/* 59 */     AbilityHelper.createEmptySphere(world, (int)vec3d.field_72450_a, (int)vec3d.field_72448_b, (int)vec3d.field_72449_c, 8, (Block)ModBlocks.HARDENED_SNOW.get(), GRIEF_RULE);
/*    */     
/* 61 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\KamakuraJussoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */