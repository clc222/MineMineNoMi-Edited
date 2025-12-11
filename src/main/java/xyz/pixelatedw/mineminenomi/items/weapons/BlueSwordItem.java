/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.BlueSwordAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlueSwordItem extends ModSwordItem {
/*    */   public BlueSwordItem() {
/* 24 */     super(6, 5000);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 30 */     if (!AbilityDataCapability.get((LivingEntity)player).hasUnlockedAbility(BlueSwordAbility.INSTANCE)) {
/* 31 */       return ActionResult.func_226251_d_(player.func_184586_b(hand));
/*    */     }
/* 33 */     List<BlockPos> list = WyHelper.getNearbyBlocks((Entity)player, 10);
/*    */     
/* 35 */     for (BlockPos pos : list) {
/*    */       
/* 37 */       if (world.func_180495_p(pos.func_177984_a()).func_196958_f())
/*    */       {
/* 39 */         if (pos.hashCode() % (world.field_73012_v.nextInt(35) + 1) == 0) {
/* 40 */           world.func_180501_a(pos.func_177984_a(), Blocks.field_150480_ab.func_176223_P(), 3);
/*    */         }
/*    */       }
/*    */     } 
/* 44 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(player.func_213303_ch(), (IWorld)player.field_70170_p, 10.0D, ModEntityPredicates.getEnemyFactions((LivingEntity)player));
/*    */     
/* 46 */     for (LivingEntity target : targets)
/*    */     {
/* 48 */       AbilityHelper.setSecondsOnFireBy((Entity)target, 20, (LivingEntity)player);
/*    */     }
/*    */     
/* 51 */     player.func_184811_cZ().func_185145_a((Item)this, 600);
/*    */     
/* 53 */     return new ActionResult(ActionResultType.PASS, player.func_184586_b(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77644_a(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/* 59 */     if (!AbilityDataCapability.get(attacker).hasUnlockedAbility(BlueSwordAbility.INSTANCE)) {
/* 60 */       return false;
/*    */     }
/* 62 */     AbilityHelper.setSecondsOnFireBy((Entity)target, 20, attacker);
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\BlueSwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */