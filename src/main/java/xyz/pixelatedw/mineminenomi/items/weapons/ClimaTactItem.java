/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClimaTactItem
/*     */   extends Item
/*     */ {
/*  31 */   private int damage = 1;
/*  32 */   private int level = 1;
/*     */   private Ingredient repairIngredient;
/*     */   
/*     */   public ClimaTactItem(int damage, int level, int maxDamage) {
/*  36 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.WEAPONS).func_200917_a(1).func_200918_c(maxDamage));
/*  37 */     this.damage = damage;
/*  38 */     this.level = level;
/*     */   }
/*     */   
/*     */   public WeatherBallKind[] checkCharge(ItemStack itemStack) {
/*  42 */     byte[] charge = itemStack.func_196082_o().func_74770_j("charge");
/*  43 */     WeatherBallKind[] tempo = new WeatherBallKind[charge.length];
/*  44 */     for (int i = 0; i < charge.length; i++) {
/*  45 */       byte b = charge[i];
/*  46 */       if (b == 0) {
/*  47 */         return new WeatherBallKind[0];
/*     */       }
/*  49 */       tempo[i] = WeatherBallKind.from(b);
/*     */     } 
/*  51 */     return tempo;
/*     */   }
/*     */   
/*     */   public void chargeWeatherBall(ItemStack itemStack, WeatherBallKind ball) {
/*  55 */     CompoundNBT nbt = itemStack.func_196082_o();
/*  56 */     if (!nbt.func_74764_b("charge")) {
/*  57 */       nbt.func_74773_a("charge", new byte[3]);
/*     */     }
/*  59 */     byte[] charge = nbt.func_74770_j("charge");
/*     */     
/*  61 */     for (int i = 0; i < 3; i++) {
/*  62 */       if (charge[i] == 0) {
/*  63 */         charge[i] = ball.getKind();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  68 */     nbt.func_74773_a("charge", charge);
/*     */   }
/*     */   
/*     */   public void chargeWeatherBall(ItemStack itemStack, String ball) {
/*  72 */     if (Strings.isNullOrEmpty(itemStack.func_196082_o().func_74779_i("firstSlot"))) {
/*  73 */       itemStack.func_196082_o().func_74778_a("firstSlot", ball);
/*  74 */     } else if (Strings.isNullOrEmpty(itemStack.func_196082_o().func_74779_i("secondSlot"))) {
/*  75 */       itemStack.func_196082_o().func_74778_a("secondSlot", ball);
/*  76 */     } else if (Strings.isNullOrEmpty(itemStack.func_196082_o().func_74779_i("thirdSlot"))) {
/*  77 */       itemStack.func_196082_o().func_74778_a("thirdSlot", ball);
/*     */     } 
/*     */   }
/*     */   public void emptyCharge(ItemStack itemStack) {
/*  81 */     CompoundNBT nbt = itemStack.func_196082_o();
/*  82 */     nbt.func_74773_a("charge", new byte[3]);
/*  83 */     itemStack.func_196082_o().func_74778_a("firstSlot", "");
/*  84 */     itemStack.func_196082_o().func_74778_a("secondSlot", "");
/*  85 */     itemStack.func_196082_o().func_74778_a("thirdSlot", "");
/*     */   }
/*     */   
/*     */   public static void setDamageModifier(ItemStack stack, double multiplier) {
/*  89 */     stack.func_196082_o().func_74780_a("multiplier", multiplier);
/*     */   }
/*     */   
/*     */   public void setCharged(ItemStack stack, boolean flag) {
/*  93 */     stack.func_196082_o().func_74757_a("isCharged", flag);
/*     */   }
/*     */   
/*     */   public boolean isCharged(ItemStack stack) {
/*  97 */     return stack.func_196082_o().func_74767_n("isCharged");
/*     */   }
/*     */   
/*     */   public int getLevel() {
/* 101 */     return this.level;
/*     */   }
/*     */   
/*     */   public <T extends ClimaTactItem> T setRepairIngredient(Ingredient ingredient) {
/* 105 */     this.repairIngredient = ingredient;
/* 106 */     return (T)this;
/*     */   }
/*     */   
/*     */   public Ingredient getRepairIngredient() {
/* 110 */     return this.repairIngredient;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World level, Entity entity, int itemSlot, boolean isSelected) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
/* 122 */     HashMultimap hashMultimap = HashMultimap.create();
/*     */     
/* 124 */     if (equipmentSlot == EquipmentSlotType.MAINHAND) {
/* 125 */       double multiplier = stack.func_196082_o().func_74769_h("multiplier");
/* 126 */       if (multiplier <= 0.0D) {
/* 127 */         multiplier = 1.0D;
/*     */       }
/* 129 */       hashMultimap.put(Attributes.field_233823_f_, new AttributeModifier(field_111210_e, "Weapon modifier", this.damage * multiplier, AttributeModifier.Operation.ADDITION));
/* 130 */       hashMultimap.put(Attributes.field_233825_h_, new AttributeModifier(field_185050_h, "Speed modifier", -2.8D, AttributeModifier.Operation.ADDITION));
/*     */     } 
/*     */     
/* 133 */     return (Multimap<Attribute, AttributeModifier>)hashMultimap;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82789_a(ItemStack toRepair, ItemStack repair) {
/* 138 */     return (this.repairIngredient.test(repair) || super.func_82789_a(toRepair, repair));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_77624_a(ItemStack itemStack, @Nullable World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
/* 144 */     CompoundNBT nbt = itemStack.func_196082_o();
/* 145 */     byte[] charge = nbt.func_74764_b("charge") ? itemStack.func_196082_o().func_74770_j("charge") : new byte[3];
/* 146 */     String[] tempoColors = new String[charge.length];
/*     */     
/* 148 */     for (int i = 0; i < charge.length; i++) {
/* 149 */       byte b = charge[i];
/* 150 */       if (WeatherBallKind.from(b) == null) {
/* 151 */         tempoColors[i] = "§r";
/*     */       }
/*     */       else {
/*     */         
/* 155 */         switch (WeatherBallKind.from(b)) {
/*     */           case COOL:
/* 157 */             tempoColors[i] = "§b";
/*     */             break;
/*     */           case HEAT:
/* 160 */             tempoColors[i] = "§4";
/*     */             break;
/*     */           case THUNDER:
/* 163 */             tempoColors[i] = "§e";
/*     */             break;
/*     */           default:
/* 166 */             tempoColors[i] = "§r";
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/*     */     } 
/* 172 */     StringBuilder sb = new StringBuilder();
/* 173 */     int maxCharges = charge.length;
/* 174 */     for (int j = 0; j < maxCharges; j++) {
/* 175 */       sb.append(String.format("%s█§r", new Object[] { tempoColors[j] }));
/* 176 */       if (j < maxCharges - 1) {
/* 177 */         sb.append("━");
/*     */       }
/*     */     } 
/* 180 */     tooltip.add(new StringTextComponent(sb.toString()));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ClimaTactItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */