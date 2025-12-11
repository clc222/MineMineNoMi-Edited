/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.text.IFormattableTextComponent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.Style;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.nikyu.PainRepelAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PainEntity;
/*    */ 
/*    */ public class PainItem extends Item {
/*    */   public PainItem() {
/* 24 */     super((new Item.Properties()).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 29 */     ItemStack stack = player.func_184586_b(hand);
/*    */     
/* 31 */     if (!AbilityDataCapability.get((LivingEntity)player).hasUnlockedAbility(PainRepelAbility.INSTANCE)) {
/* 32 */       stack.func_190918_g(1);
/*    */       
/* 34 */       return ActionResult.func_226251_d_(ItemStack.field_190927_a);
/*    */     } 
/*    */     
/* 37 */     float pain = stack.func_196082_o().func_74760_g("pain");
/*    */     
/* 39 */     PainEntity proj = new PainEntity(player.field_70170_p, (LivingEntity)player);
/*    */     
/* 41 */     proj.setDamage(pain);
/* 42 */     proj.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 2.0F, 1.0F);
/*    */     
/* 44 */     player.field_70170_p.func_217376_c((Entity)proj);
/* 45 */     player.field_71071_by.func_184437_d(stack);
/*    */     
/* 47 */     return ActionResult.func_226250_c_(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 52 */     float pain = itemStack.func_196082_o().func_74760_g("pain");
/*    */     
/* 54 */     IFormattableTextComponent iFormattableTextComponent = (new StringTextComponent("Pain: " + String.format("%.2f", new Object[] { Float.valueOf(pain) }))).func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.RED));
/*    */     
/* 56 */     list.add(iFormattableTextComponent);
/*    */   }
/*    */   
/*    */   public void setPainLevel(ItemStack itemStack, float pain) {
/* 60 */     itemStack.func_196082_o().func_74776_a("pain", pain);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\PainItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */