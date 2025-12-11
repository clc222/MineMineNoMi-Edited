/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
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
/*    */ public class YukiTravelAbility extends LogiaBlockBypassingAbility {
/* 13 */   public static final AbilityCore<YukiTravelAbility> INSTANCE = (new AbilityCore.Builder("Yuki Block Bypassing", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, YukiTravelAbility::new))
/* 14 */     .addDescriptionLine(DESCRIPTION)
/* 15 */     .setIcon(new ResourceLocation("minecraft", "textures/block/snow.png"))
/* 16 */     .build();
/*    */   
/*    */   public YukiTravelAbility(AbilityCore<YukiTravelAbility> core) {
/* 19 */     super(core);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawnParticles(LivingEntity entity) {}
/*    */ 
/*    */   
/*    */   public boolean canGoThrough(BlockState state) {
/* 28 */     return state.func_235714_a_((ITag)ModTags.Blocks.LOGIA_BLOCK_PASS_YUKI);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\YukiTravelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */