/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.tags.ITag;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaBlockBypassingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PikaTravelAbility extends LogiaBlockBypassingAbility {
/* 15 */   public static final AbilityCore<PikaTravelAbility> INSTANCE = (new AbilityCore.Builder("Pika Block Bypassing", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, PikaTravelAbility::new))
/* 16 */     .addDescriptionLine(DESCRIPTION)
/* 17 */     .setIcon(new ResourceLocation("minecraft", "textures/block/glass.png"))
/* 18 */     .build();
/*    */   
/*    */   public PikaTravelAbility(AbilityCore<PikaTravelAbility> core) {
/* 21 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawnParticles(LivingEntity entity) {
/* 26 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PIKA_LOGIA.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canGoThrough(BlockState state) {
/* 31 */     return state.func_235714_a_((ITag)ModTags.Blocks.LOGIA_BLOCK_PASS_PIKA);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\PikaTravelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */