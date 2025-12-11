/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.CrewEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class SakeCupItem extends Item {
/*     */   public SakeCupItem() {
/*  39 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1).func_221540_a(ModFoods.ALCOHOL));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  45 */     ItemStack itemstack = player.func_184586_b(hand);
/*  46 */     if (getLeader(player.func_184614_ca(), world) != null) {
/*     */       
/*  48 */       player.func_184598_c(hand);
/*     */     }
/*     */     else {
/*     */       
/*  52 */       int slot = WyHelper.getIndexOfItemStack((Item)ModItems.SAKE_BOTTLE.get(), (IInventory)player.field_71071_by);
/*  53 */       if (slot >= 0) {
/*     */         
/*  55 */         ItemStack stack = player.field_71071_by.func_70301_a(slot);
/*  56 */         stack.func_222118_a(1, (LivingEntity)player, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */ 
/*     */ 
/*     */         
/*  60 */         setLeader(player.func_184614_ca(), player);
/*  61 */         return ActionResult.func_226248_a_(itemstack);
/*     */       } 
/*     */     } 
/*  64 */     return ActionResult.func_226251_d_(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/*  70 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/*     */       
/*  72 */       PlayerEntity player = (PlayerEntity)entity;
/*  73 */       PlayerEntity leader = getLeader(itemStack, player.field_70170_p);
/*     */       
/*  75 */       if (leader != null) {
/*     */         
/*  77 */         ExtendedWorldData worldProps = ExtendedWorldData.get();
/*  78 */         Crew crew = worldProps.getCrewWithCaptain(leader.func_110124_au());
/*  79 */         if (crew == null) {
/*  80 */           WyDebug.debug("Cannot find a crew for captain " + leader.func_200200_C_().getString());
/*     */         } else {
/*     */           
/*  83 */           CrewEvent.Join event = new CrewEvent.Join(player, crew);
/*  84 */           if (!MinecraftForge.EVENT_BUS.post((Event)event))
/*     */           {
/*  86 */             if (!crew.hasMember(player.func_110124_au())) {
/*     */               
/*  88 */               worldProps.addCrewMember(crew, (LivingEntity)player);
/*  89 */               FactionHelper.sendMessageToCrew(world, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_JOIN, new Object[] { player.func_200200_C_().getString() }));
/*  90 */               IEntityStats props = EntityStatsCapability.get(entity);
/*  91 */               props.setFaction(ModValues.PIRATE);
/*  92 */               WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.func_145782_y(), props), (Entity)player);
/*  93 */               itemStack.func_196082_o().func_74768_a("leader", 0);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  99 */       player.field_71071_by.func_70441_a(new ItemStack((IItemProvider)ModItems.SAKE_CUP.get()));
/* 100 */       itemStack.func_190918_g(1);
/*     */     } 
/*     */     
/* 103 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeader(ItemStack itemStack, PlayerEntity leader) {
/* 108 */     itemStack.func_77982_d(new CompoundNBT());
/* 109 */     itemStack.func_77978_p().func_74778_a("leader", leader.func_110124_au().toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getLeader(ItemStack itemStack, World world) {
/* 114 */     if (!itemStack.func_77942_o())
/* 115 */       itemStack.func_77982_d(new CompoundNBT()); 
/* 116 */     String leaderUUID = itemStack.func_77978_p().func_74779_i("leader");
/* 117 */     if (!Strings.isNullOrEmpty(leaderUUID))
/* 118 */       return world.func_217371_b(UUID.fromString(leaderUUID)); 
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAction func_77661_b(ItemStack stack) {
/* 125 */     return UseAction.DRINK;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\SakeCupItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */