/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaBlockBypassingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ 
/*    */ public class HieTravelAbility extends LogiaBlockBypassingAbility {
/* 13 */   public static final AbilityCore<HieTravelAbility> INSTANCE = (new AbilityCore.Builder("Hie Block Bypassing", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, HieTravelAbility::new))
/* 14 */     .addDescriptionLine(DESCRIPTION)
/* 15 */     .setIcon(new ResourceLocation("minecraft", "textures/block/ice.png"))
/* 16 */     .build();
/*    */   
/*    */   public HieTravelAbility(AbilityCore<HieTravelAbility> core) {
/* 19 */     super(core);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawnParticles(LivingEntity entity) {}
/*    */ 
/*    */   
/*    */   public boolean canGoThrough(BlockState state) {
/* 28 */     return state.func_235714_a_((ITag)ModTags.Blocks.LOGIA_BLOCK_PASS_HIE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\HieTravelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */