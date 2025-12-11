/*    */ package xyz.pixelatedw.mineminenomi.abilities.sabi;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.IWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RustBreakAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "rust_break", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Rusts iron blocks in front of the user", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 400.0F;
/* 34 */   public static final AbilityCore<RustBreakAbility> INSTANCE = (new AbilityCore.Builder("Rust Break", AbilityCategory.DEVIL_FRUITS, RustBreakAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/* 37 */       }).build();
/*    */   
/*    */   public RustBreakAbility(AbilityCore<RustBreakAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/*    */     
/* 44 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 48 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity);
/*    */     
/* 50 */     if (entity.func_195048_a(mop.func_216347_e()) > 50.0D) {
/*    */       return;
/*    */     }
/*    */     
/* 54 */     BlockPos tracePos = new BlockPos(mop.func_216347_e());
/* 55 */     List<BlockPos> rustyBlocks = WyHelper.getNearbyBlocks(tracePos, (IWorld)entity.field_70170_p, 1, p -> entity.field_70170_p.func_180495_p(p).func_177230_c().func_203417_a((ITag)ModTags.Blocks.RUSTY), (List)ImmutableList.of(Blocks.field_150350_a));
/*    */     
/* 57 */     for (BlockPos pos : rustyBlocks) {
/* 58 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.RUST_BREAK.get(), (Entity)entity, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 59 */       boolean isIngot = this.random.nextBoolean();
/* 60 */       for (int i = 0; i < this.random.nextInt(3); i++) {
/* 61 */         ItemStack stack = isIngot ? new ItemStack((IItemProvider)Items.field_151042_j) : new ItemStack((IItemProvider)Items.field_191525_da);
/* 62 */         ItemEntity item = new ItemEntity(entity.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), stack);
/* 63 */         entity.field_70170_p.func_217376_c((Entity)item);
/*    */       } 
/*    */       
/* 66 */       entity.field_70170_p.func_217377_a(pos, false);
/*    */     } 
/*    */     
/* 69 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sabi\RustBreakAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */