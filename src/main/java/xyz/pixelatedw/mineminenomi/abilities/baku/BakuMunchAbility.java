/*    */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BakuMunchAbility extends Ability {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "baku_munch", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Allows the user to eat a big chunk of blocks in front of him, obtaining all of them as blocks in their inventory", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 60.0F;
/* 37 */   public static final AbilityCore<BakuMunchAbility> INSTANCE = (new AbilityCore.Builder("Baku Munch", AbilityCategory.DEVIL_FRUITS, BakuMunchAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F)
/* 40 */       }).build();
/*    */   
/* 42 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(100);
/*    */   
/*    */   public BakuMunchAbility(AbilityCore<BakuMunchAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/*    */     
/* 49 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 53 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 16.0D);
/* 54 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 55 */     if (MathHelper.func_76133_a(entity.func_70092_e((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c)) < 5.0F) {
/* 56 */       List<BlockPos> positions = new ArrayList<>();
/* 57 */       for (int x = -2; x < 2; x++) {
/* 58 */         for (int y = 0; y < 3; y++) {
/* 59 */           for (int z = -2; z < 2; z++) {
/* 60 */             int posX = (int)(blockRayTraceResult.func_216347_e()).field_72450_a + x;
/* 61 */             int posY = (int)(blockRayTraceResult.func_216347_e()).field_72448_b - y;
/* 62 */             int posZ = (int)(blockRayTraceResult.func_216347_e()).field_72449_c + z;
/* 63 */             mutpos.func_181079_c(posX, posY, posZ);
/* 64 */             Block tempBlock = entity.field_70170_p.func_180495_p((BlockPos)mutpos).func_177230_c();
/* 65 */             if (!tempBlock.func_203417_a((ITag)ModTags.Blocks.KAIROSEKI) && AbilityHelper.placeBlockIfAllowed(entity, (BlockPos)mutpos, Blocks.field_150350_a.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 66 */               if (entity instanceof PlayerEntity) {
/* 67 */                 ((PlayerEntity)entity).field_71071_by.func_70441_a(new ItemStack((IItemProvider)tempBlock));
/*    */               }
/* 69 */               positions.add(mutpos.func_185334_h());
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 75 */       if (positions.size() > 0) {
/* 76 */         this.details.setPositions(positions);
/* 77 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BakuMunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */