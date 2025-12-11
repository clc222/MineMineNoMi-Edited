/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.SoulboundItemHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class HeartItem extends Item {
/*     */   public HeartItem() {
/*  31 */     super((new Item.Properties()).func_200917_a(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  36 */     ItemStack itemStack = player.func_184586_b(hand);
/*     */     
/*  38 */     if (world.field_72995_K) {
/*  39 */       return ActionResult.func_226248_a_(itemStack);
/*     */     }
/*     */     
/*  42 */     if (itemStack.func_77978_p() == null) {
/*  43 */       player.field_71071_by.func_184437_d(itemStack);
/*  44 */       return ActionResult.func_226248_a_(itemStack);
/*     */     } 
/*     */     
/*  47 */     LivingEntity owner = (LivingEntity)SoulboundItemHelper.getOwner(world, itemStack).getValue();
/*     */     
/*  49 */     if (owner == null) {
/*  50 */       return ActionResult.func_226248_a_(itemStack);
/*     */     }
/*     */     
/*  53 */     IEntityStats props = EntityStatsCapability.get(owner);
/*     */     
/*  55 */     if (props.hasHeart()) {
/*  56 */       player.field_71071_by.func_184437_d(itemStack);
/*  57 */       return ActionResult.func_226248_a_(itemStack);
/*     */     } 
/*     */     
/*  60 */     if (itemStack.func_77978_p() != null) {
/*  61 */       if (owner == player) {
/*  62 */         props.setHeart(true);
/*  63 */         WyNetwork.sendToServer(new SSyncEntityStatsPacket(player.func_145782_y(), props));
/*  64 */         player.field_71071_by.func_184437_d(itemStack);
/*     */       } else {
/*     */         
/*  67 */         owner.func_70097_a(getDamageSource((LivingEntity)player), 5.0F);
/*  68 */         owner.func_195064_c(new EffectInstance(Effects.field_76421_d, 40, 1));
/*  69 */         owner.func_195064_c(new EffectInstance(Effects.field_76431_k, 40, 1));
/*  70 */         if (owner.func_110143_aJ() <= 0.0F) {
/*  71 */           player.field_71071_by.func_184437_d(itemStack);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  76 */     return ActionResult.func_226248_a_(itemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
/*  81 */     if (entityItem.field_70170_p.field_72995_K) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     if (itemStack.func_77978_p() != null) {
/*  86 */       Pair<UUID, LivingEntity> target = SoulboundItemHelper.getOwner(entityItem.field_70170_p, itemStack);
/*  87 */       boolean isBurning = entityItem.func_70027_ad();
/*  88 */       boolean isInVoid = (entityItem.func_233580_cy_().func_177956_o() < -1);
/*     */       
/*  90 */       if (isBurning || isInVoid) {
/*  91 */         if (target.getValue() != null) {
/*  92 */           Iterator<ItemStack> iter = ((LivingEntity)target.getValue()).func_184214_aD().iterator();
/*  93 */           while (iter.hasNext()) {
/*  94 */             ItemStack stack = iter.next();
/*  95 */             if (stack.func_77973_b() == Items.field_190929_cY) {
/*  96 */               stack.func_190918_g(stack.func_190916_E());
/*     */             }
/*     */           } 
/*  99 */           ((LivingEntity)target.getValue()).func_70097_a(getDamageSource(null), Float.MAX_VALUE);
/*     */         }
/*     */         else {
/*     */           
/* 103 */           ExtendedWorldData.get().markDead((UUID)target.getKey());
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   private DamageSource getDamageSource(@Nullable LivingEntity entity) {
/* 112 */     if (entity == null) {
/* 113 */       return (new ModDamageSource("heart_damage")).setInternal().func_82726_p().func_76359_i().func_151518_m();
/*     */     }
/* 115 */     return (new ModEntityDamageSource("heart_damage", (Entity)entity)).setInternal().func_82726_p().func_76359_i().func_151518_m();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\HeartItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */