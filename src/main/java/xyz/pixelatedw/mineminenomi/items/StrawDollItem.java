/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.IDyeableArmorItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.SoulboundMark;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.SoulboundItemHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class StrawDollItem extends Item implements IDyeableArmorItem {
/*     */   public StrawDollItem() {
/*  29 */     super((new Item.Properties()).func_200917_a(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  34 */     ItemStack itemStack = player.func_184586_b(hand);
/*     */     
/*  36 */     if (world.field_72995_K) {
/*  37 */       return ActionResult.func_226248_a_(itemStack);
/*     */     }
/*     */     
/*  40 */     if (itemStack.func_77978_p() == null) {
/*  41 */       player.field_71071_by.func_184437_d(itemStack);
/*  42 */       return ActionResult.func_226248_a_(itemStack);
/*     */     } 
/*     */     
/*  45 */     LivingEntity owner = (LivingEntity)SoulboundItemHelper.getOwner(world, itemStack).getValue();
/*     */     
/*  47 */     if (owner == null) {
/*  48 */       return ActionResult.func_226248_a_(itemStack);
/*     */     }
/*     */     
/*  51 */     IEntityStats props = EntityStatsCapability.get(owner);
/*     */     
/*  53 */     if (props.hasStrawDoll()) {
/*  54 */       player.field_71071_by.func_184437_d(itemStack);
/*  55 */       return ActionResult.func_226248_a_(itemStack);
/*     */     } 
/*     */     
/*  58 */     if (itemStack.func_77978_p() != null && 
/*  59 */       owner == player) {
/*  60 */       props.setStrawDoll(true);
/*  61 */       WyNetwork.sendToServer(new SSyncEntityStatsPacket(player.func_145782_y(), props));
/*  62 */       player.field_71071_by.func_184437_d(itemStack);
/*     */     } 
/*     */ 
/*     */     
/*  66 */     return ActionResult.func_226248_a_(itemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
/*  71 */     if (entityItem.field_70170_p.field_72995_K) {
/*  72 */       return false;
/*     */     }
/*     */     
/*  75 */     if (itemStack.func_77978_p() != null) {
/*  76 */       Pair<UUID, LivingEntity> target = SoulboundItemHelper.getOwner(entityItem.field_70170_p, itemStack);
/*  77 */       boolean isBurning = entityItem.func_70027_ad();
/*  78 */       boolean isInVoid = (entityItem.func_233580_cy_().func_177956_o() < -1);
/*     */       
/*  80 */       if (isBurning || isInVoid) {
/*  81 */         if (target.getValue() != null) {
/*  82 */           Iterator<ItemStack> iter = ((LivingEntity)target.getValue()).func_184214_aD().iterator();
/*  83 */           while (iter.hasNext()) {
/*  84 */             ItemStack stack = iter.next();
/*  85 */             if (stack.func_77973_b() == Items.field_190929_cY) {
/*  86 */               stack.func_190918_g(stack.func_190916_E());
/*     */             }
/*     */           } 
/*  89 */           EntityStatsCapability.get((LivingEntity)target.getValue()).setStrawDoll(true);
/*     */         }
/*     */         else {
/*     */           
/*  93 */           ExtendedWorldData.get().markRestore((UUID)target.getKey(), SoulboundMark.RESTORE_DOLL);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public static void setStrawDollOwner(ItemStack itemStack, LivingEntity entity) {
/* 102 */     SoulboundItemHelper.setOwner(itemStack, entity);
/*     */     
/* 104 */     float red = entity.func_70681_au().nextFloat();
/* 105 */     float green = entity.func_70681_au().nextFloat();
/* 106 */     float blue = entity.func_70681_au().nextFloat();
/*     */     
/* 108 */     itemStack.func_190925_c("display").func_74768_a("color", (new Color(red, green, blue)).getRGB());
/*     */   }
/*     */   
/*     */   public static float getStrawDollHealth(ItemStack itemStack) {
/* 112 */     return itemStack.func_196082_o().func_74760_g("health");
/*     */   }
/*     */   
/*     */   public static void setStrawDollHealth(ItemStack itemStack, float health) {
/* 116 */     float maxHealth = (CommonConfig.INSTANCE.getDorikiLimit() / CommonConfig.INSTANCE.getHealthGainFrequency());
/*     */     
/* 118 */     itemStack.func_196082_o().func_74776_a("health", MathHelper.func_76131_a(health, 0.0F, maxHealth));
/*     */   }
/*     */   
/*     */   public static void alterStrawDollHealth(ItemStack itemStack, float health) {
/* 122 */     float maxHealth = (CommonConfig.INSTANCE.getDorikiLimit() / CommonConfig.INSTANCE.getHealthGainFrequency());
/*     */     
/* 124 */     float currentHealth = itemStack.func_196082_o().func_74760_g("health");
/*     */     
/* 126 */     itemStack.func_196082_o().func_74776_a("health", MathHelper.func_76131_a(currentHealth + health, 0.0F, maxHealth));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\StrawDollItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */