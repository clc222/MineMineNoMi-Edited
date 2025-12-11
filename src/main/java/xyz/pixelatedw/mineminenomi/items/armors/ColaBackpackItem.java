/*     */ package xyz.pixelatedw.mineminenomi.items.armors;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ArmorItem;
/*     */ import net.minecraft.item.IArmorMaterial;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.models.armors.ColaBackpackModel;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class ColaBackpackItem
/*     */   extends ArmorItem
/*     */ {
/*     */   private static final int COLA_REFILL_TICKS = 20;
/*     */   
/*     */   public ColaBackpackItem() {
/*  32 */     super((IArmorMaterial)ModArmors.COLA_BACKPACK_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @Nullable
/*     */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/*  39 */     return (A)new ColaBackpackModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/*  47 */     return String.format("%s:textures/models/armor/cola_backpack.png", new Object[] { "mineminenomi" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
/*  52 */     if (world.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  56 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/*  58 */     if (props.isCyborg() && 
/*  59 */       player.field_70173_aa % 20 == 0) {
/*  60 */       int colaSlot = getColaSlot(player);
/*  61 */       int ultraColaSlot = getUltraColaSlot(player);
/*  62 */       if (colaSlot != -1 && props.getCola() + 25 <= props.getMaxCola()) {
/*  63 */         props.alterCola(25);
/*  64 */         player.field_71071_by.func_70298_a(colaSlot, 1);
/*  65 */         player.func_191521_c(new ItemStack((IItemProvider)ModItems.EMPTY_COLA.get()));
/*     */       }
/*  67 */       else if (ultraColaSlot != -1 && props.getCola() + 100 <= props.getMaxCola()) {
/*  68 */         props.alterCola(100);
/*  69 */         player.field_71071_by.func_70298_a(ultraColaSlot, 1);
/*  70 */         player.func_191521_c(new ItemStack((IItemProvider)ModItems.EMPTY_ULTRA_COLA.get()));
/*     */       } 
/*  72 */       WyNetwork.sendTo(new SUpdateColaAmountPacket((LivingEntity)player), player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColaSlot(PlayerEntity player) {
/*  78 */     if (!player.field_71071_by.func_70431_c(new ItemStack((IItemProvider)ModItems.COLA.get()))) {
/*  79 */       return -1;
/*     */     }
/*     */     
/*  82 */     for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*  83 */       ItemStack stack = player.field_71071_by.func_70301_a(i);
/*  84 */       if (!stack.func_190926_b() && stack.func_77973_b() == ModItems.COLA.get()) {
/*  85 */         return i;
/*     */       }
/*     */     } 
/*  88 */     return -1;
/*     */   }
/*     */   
/*     */   public int getUltraColaSlot(PlayerEntity player) {
/*  92 */     if (!player.field_71071_by.func_70431_c(new ItemStack((IItemProvider)ModItems.ULTRA_COLA.get()))) {
/*  93 */       return -1;
/*     */     }
/*     */     
/*  96 */     for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*  97 */       ItemStack stack = player.field_71071_by.func_70301_a(i);
/*  98 */       if (!stack.func_190926_b() && stack.func_77973_b() == ModItems.ULTRA_COLA.get()) {
/*  99 */         return i;
/*     */       }
/*     */     } 
/* 102 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\ColaBackpackItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */