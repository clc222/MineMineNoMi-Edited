/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.TomoeDrumsModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TomoeDrumsItem
/*    */   extends ArmorItem
/*    */ {
/*    */   public TomoeDrumsItem() {
/* 25 */     super((IArmorMaterial)ModArmors.TOMOE_DRUMS_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT).func_200915_b(1000));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 33 */     return (A)new TomoeDrumsModel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/* 41 */     if (getDamage(stack) + amount > getMaxDamage(stack))
/* 42 */       return 0; 
/* 43 */     return super.damageItem(stack, amount, (LivingEntity)entity, onBroken);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 50 */     return String.format("%s:textures/models/armor/tomoe_drums.png", new Object[] { "mineminenomi" });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\TomoeDrumsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */