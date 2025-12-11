/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.CooldownTracker;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NetType;
/*     */ import xyz.pixelatedw.mineminenomi.effects.CaughtInNetEffect;
/*     */ import xyz.pixelatedw.mineminenomi.entities.NetEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class NetItem
/*     */   extends Item {
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int USE_TIME = 20;
/*     */   private Supplier<CaughtInNetEffect> netEffect;
/*     */   
/*     */   public NetItem(Supplier<CaughtInNetEffect> handcuffed) {
/*  31 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*  32 */     this.netEffect = handcuffed;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  37 */     ItemStack itemstack = player.func_184586_b(hand);
/*     */     
/*  39 */     if (!world.field_72995_K) {
/*  40 */       WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation((LivingEntity)player, ModAnimations.CHARGE_NET_THROW, func_77626_a(itemstack), true), (Entity)player);
/*     */     }
/*  42 */     player.func_184598_c(hand);
/*     */     
/*  44 */     return ActionResult.func_226249_b_(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, LivingEntity entity, int timeLeft) {
/*  49 */     if (entity instanceof PlayerEntity) {
/*  50 */       PlayerEntity player = (PlayerEntity)entity;
/*     */       
/*  52 */       if (!world.field_72995_K) {
/*  53 */         WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation((LivingEntity)player, ModAnimations.CHARGE_NET_THROW), (Entity)player);
/*  54 */         int useTime = func_77626_a(stack) - timeLeft;
/*  55 */         if (useTime < 20) {
/*     */           return;
/*     */         }
/*     */         
/*  59 */         throwNet((LivingEntity)player, world, stack);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_219972_a(World world, LivingEntity entity, ItemStack stack, int count) {
/*  66 */     if (world.field_72995_K) {
/*  67 */       int useTime = func_77626_a(stack) - entity.func_184605_cv();
/*  68 */       if (useTime >= 20) {
/*  69 */         (Minecraft.func_71410_x()).field_71460_t.field_78516_c.func_187460_a(Hand.MAIN_HAND);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void throwNet(LivingEntity entity, World world, ItemStack stack) {
/*  75 */     NetEntity netEntity = NetEntity.createFromItem(world, entity, this);
/*  76 */     netEntity.func_70107_b(entity.func_226277_ct_(), entity.func_226280_cw_() + 1.0D, entity.func_226281_cx_());
/*  77 */     netEntity.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 1.25F, 0.0F);
/*  78 */     world.func_217376_c((Entity)netEntity);
/*     */     
/*  80 */     boolean removeItem = true;
/*  81 */     if (entity instanceof PlayerEntity) {
/*  82 */       startCooldowns(((PlayerEntity)entity).func_184811_cZ());
/*  83 */       removeItem = !((PlayerEntity)entity).field_71075_bZ.field_75098_d;
/*     */     } 
/*     */     
/*  86 */     if (removeItem) {
/*  87 */       stack.func_190918_g(1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void startCooldowns(CooldownTracker cooldown) {
/*  92 */     cooldown.func_185145_a((Item)ModItems.ROPE_NET.get(), 200);
/*  93 */     cooldown.func_185145_a((Item)ModItems.KAIROSEKI_NET.get(), 200);
/*     */   }
/*     */   
/*     */   public CaughtInNetEffect getEffect() {
/*  97 */     return this.netEffect.get();
/*     */   }
/*     */   
/*     */   public NetType getNetType() {
/* 101 */     return getEffect().getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack pStack) {
/* 106 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public UseAction func_77661_b(ItemStack pStack) {
/* 111 */     return UseAction.NONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\NetItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */