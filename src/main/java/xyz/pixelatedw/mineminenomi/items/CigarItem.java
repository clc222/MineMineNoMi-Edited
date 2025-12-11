/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class CigarItem extends Item {
/* 27 */   private int smokeFreqency = 1;
/*    */   
/*    */   public CigarItem(int smokeFreqency) {
/* 30 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200918_c(2045));
/* 31 */     this.smokeFreqency = smokeFreqency;
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 36 */     if (player.func_184582_a(EquipmentSlotType.HEAD).func_190926_b()) {
/* 37 */       ItemStack stack = new ItemStack((IItemProvider)player.func_184586_b(hand).func_77973_b(), 1);
/* 38 */       player.func_184201_a(EquipmentSlotType.HEAD, stack);
/* 39 */       player.func_184185_a(SoundEvents.field_187649_bu, 1.0F, 1.0F);
/* 40 */       player.func_184586_b(hand).func_190918_g(1);
/*    */     } 
/*    */     
/* 43 */     return new ActionResult(ActionResultType.SUCCESS, player.func_184586_b(hand));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
/* 48 */     return armorType.equals(EquipmentSlotType.HEAD);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
/* 53 */     if (player.field_70173_aa % this.smokeFreqency == 0) {
/* 54 */       if (!player.func_204231_K()) {
/* 55 */         Vector3d vec = player.func_70040_Z().func_186678_a(0.5D + (player.func_213311_cf() / 2.0F)).func_178785_b((float)Math.toRadians(-20.0D));
/* 56 */         world.func_195594_a((IParticleData)ParticleTypes.field_218417_ae, player.func_226277_ct_() + vec.field_72450_a, vec.field_72448_b + player.func_226280_cw_(), player.func_226281_cx_() + vec.field_72449_c, 0.0D, 0.04D, 0.0D);
/*    */       } 
/*    */       
/* 59 */       if (!world.field_72995_K) {
/* 60 */         IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 61 */         if (props.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI) || props.hasDevilFruit(ModAbilities.GASU_GASU_NO_MI)) {
/*    */           return;
/*    */         }
/* 64 */         stack.func_222118_a(1, (LivingEntity)player, user -> user.func_213361_c(EquipmentSlotType.HEAD));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> a, ITooltipFlag p_77624_4_) {
/* 73 */     super.func_77624_a(p_77624_1_, p_77624_2_, a, p_77624_4_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ITextComponent func_200295_i(ItemStack stack) {
/* 78 */     if (WyHelper.isAprilFirst()) {
/* 79 */       return (ITextComponent)new StringTextComponent("Lollipop");
/*    */     }
/*    */     
/* 82 */     return super.func_200295_i(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\CigarItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */