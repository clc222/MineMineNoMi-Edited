/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.wara.StrawProjectile;
/*    */ 
/*    */ public class WarabideSwordItem extends AbilitySwordItem {
/*    */   public WarabideSwordItem(AbilityCore instance, int damage) {
/* 15 */     super(instance, damage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 21 */     ActionResult<ItemStack> result = super.func_77659_a(world, player, hand);
/*    */     
/* 23 */     if (!world.field_72995_K) {
/*    */       
/* 25 */       StrawProjectile projectile = new StrawProjectile(player.field_70170_p, (LivingEntity)player);
/* 26 */       player.field_70170_p.func_217376_c((Entity)projectile);
/* 27 */       projectile.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 1.0F, 1.5F);
/* 28 */       player.func_184811_cZ().func_185145_a((Item)this, 25);
/*    */     } 
/*    */     
/* 31 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\WarabideSwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */