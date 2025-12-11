/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaBlockBypassingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SunaTravelAbility extends LogiaBlockBypassingAbility {
/* 16 */   public static final AbilityCore<SunaTravelAbility> INSTANCE = (new AbilityCore.Builder("Suna Block Bypassing", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, SunaTravelAbility::new))
/* 17 */     .addDescriptionLine(DESCRIPTION)
/* 18 */     .setIcon(new ResourceLocation("minecraft", "textures/block/sand.png"))
/* 19 */     .build();
/*    */   
/*    */   public SunaTravelAbility(AbilityCore<SunaTravelAbility> core) {
/* 22 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawnParticles(LivingEntity entity) {
/* 27 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SAND_BLADE_IDLE.get(), (Entity)entity, entity.func_226277_ct_(), entity.field_70170_p.func_201676_a(Heightmap.Type.OCEAN_FLOOR, (int)entity.func_226277_ct_(), (int)entity.func_226281_cx_()), entity.func_226281_cx_());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canGoThrough(BlockState state) {
/* 32 */     return state.func_235714_a_((ITag)ModTags.Blocks.LOGIA_BLOCK_PASS_SUNA);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SunaTravelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */