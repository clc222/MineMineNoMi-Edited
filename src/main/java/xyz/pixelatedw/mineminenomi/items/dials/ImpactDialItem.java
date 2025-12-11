/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class ImpactDialItem extends BlockItem {
/*    */   public ImpactDialItem(Block block) {
/* 21 */     super(block, (new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 27 */     ItemStack itemstack = player.func_184586_b(hand);
/*    */     
/* 29 */     if (!world.field_72995_K) {
/*    */       
/* 31 */       player.func_195064_c(new EffectInstance(Effects.field_76429_m, 2, 4));
/* 32 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, world, player.func_233580_cy_().func_177958_n(), player.func_233580_cy_().func_177956_o(), player.func_233580_cy_().func_177952_p(), 3.0F);
/* 33 */       explosion.setStaticDamage(5.0F);
/* 34 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 35 */       explosion.doExplosion();
/*    */       
/* 37 */       player.func_184811_cZ().func_185145_a(getItem(), 10);
/* 38 */       itemstack.func_190918_g(1);
/*    */     } 
/*    */     
/* 41 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\dials\ImpactDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */