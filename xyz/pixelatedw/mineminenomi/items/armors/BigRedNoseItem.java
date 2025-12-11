/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.BigNoseModel;
/*    */ 
/*    */ public class BigRedNoseItem
/*    */   extends ArmorItem {
/* 25 */   private static final StringTextComponent DESCRIPTION = createDescription();
/* 26 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/armor/big_nose.png");
/*    */   
/*    */   public BigRedNoseItem() {
/* 29 */     super((IArmorMaterial)ModArmors.BANDANA_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties());
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 36 */     BigNoseModel<LivingEntity> model = new BigNoseModel();
/* 37 */     return (A)model;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 43 */     return TEXTURE.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, @Nullable World level, List<ITextComponent> list, ITooltipFlag flag) {
/* 49 */     list.add(DESCRIPTION);
/*    */   }
/*    */   
/*    */   private static StringTextComponent createDescription() {
/* 53 */     StringBuilder sb = new StringBuilder();
/* 54 */     sb.append("§k");
/* 55 */     Random rand = new Random();
/* 56 */     int max = 50 + rand.nextInt(100);
/* 57 */     for (int i = 0; i < max; i++) {
/* 58 */       sb.append("?");
/* 59 */       boolean split = (rand.nextInt(100) == 0);
/* 60 */       if (split) {
/* 61 */         sb.append("\n");
/*    */       }
/*    */     } 
/* 64 */     sb.append("§r");
/* 65 */     return new StringTextComponent(sb.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\BigRedNoseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */