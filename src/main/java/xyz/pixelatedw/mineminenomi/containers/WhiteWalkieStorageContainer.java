/*     */ package xyz.pixelatedw.mineminenomi.containers;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.PlayerInventory;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Inventory;
/*     */ import net.minecraft.inventory.container.Container;
/*     */ import net.minecraft.inventory.container.ContainerType;
/*     */ import net.minecraft.inventory.container.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModContainers;
/*     */ 
/*     */ public class WhiteWalkieStorageContainer
/*     */   extends Container {
/*     */   private PlayerEntity player;
/*     */   
/*     */   public WhiteWalkieStorageContainer(int containerId, PlayerInventory playerInventory, PacketBuffer buf) {
/*  25 */     super((ContainerType)ModContainers.WHITE_WALKIE_STORAGE.get(), containerId);
/*  26 */     this.player = playerInventory.field_70458_d;
/*     */     
/*  28 */     CompoundNBT nbt = buf.func_150793_b();
/*  29 */     int entityId = nbt.func_74762_e("entity");
/*  30 */     this.whiteWalkie = (WhiteWalkieEntity)this.player.field_70170_p.func_73045_a(entityId);
/*  31 */     int inventorySize = nbt.func_74762_e("inventorySize");
/*  32 */     int pageSize = nbt.func_74762_e("pageSize");
/*  33 */     this.storage = (IInventory)new Inventory(inventorySize);
/*     */     
/*  35 */     func_75146_a(new Slot(this.storage, 0, 8, 18)
/*     */         {
/*     */           public boolean func_75214_a(ItemStack pStack) {
/*  38 */             return (pStack.func_77973_b() == Items.field_151141_av && !func_75216_d() && WhiteWalkieStorageContainer.this.whiteWalkie.func_70909_n());
/*     */           }
/*     */ 
/*     */           
/*     */           @OnlyIn(Dist.CLIENT)
/*     */           public boolean func_111238_b() {
/*  44 */             return WhiteWalkieStorageContainer.this.whiteWalkie.func_70909_n();
/*     */           }
/*     */         });
/*     */     
/*  48 */     if (this.whiteWalkie.hasChest()) {
/*  49 */       for (int k = 0; k < 3; k++) {
/*  50 */         for (int l = 0; l < this.whiteWalkie.getInventoryColumns(); l++) {
/*  51 */           func_75146_a(new Slot(this.storage, 3 + l + k * this.whiteWalkie.getInventoryColumns() + this.whiteWalkie.getInventoryPage() * pageSize, 80 + l * 18, 18 + k * 18));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  56 */     for (int i1 = 0; i1 < 3; i1++) {
/*  57 */       for (int k1 = 0; k1 < 9; k1++) {
/*  58 */         func_75146_a(new Slot((IInventory)playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
/*     */       }
/*     */     } 
/*     */     
/*  62 */     for (int j1 = 0; j1 < 9; j1++)
/*  63 */       func_75146_a(new Slot((IInventory)playerInventory, j1, 8 + j1 * 18, 142)); 
/*     */   }
/*     */   private IInventory storage; private WhiteWalkieEntity whiteWalkie;
/*     */   
/*     */   public WhiteWalkieStorageContainer(int containerId, PlayerInventory playerInventory, IInventory storage, final WhiteWalkieEntity whiteWalkie) {
/*  68 */     super((ContainerType)ModContainers.WHITE_WALKIE_STORAGE.get(), containerId);
/*  69 */     this.storage = storage;
/*  70 */     this.whiteWalkie = whiteWalkie;
/*  71 */     storage.func_174889_b(playerInventory.field_70458_d);
/*  72 */     int pageSize = this.whiteWalkie.getInventoryPageSize();
/*     */     
/*  74 */     func_75146_a(new Slot(this.storage, 0, 8, 18)
/*     */         {
/*     */           public boolean func_75214_a(ItemStack pStack) {
/*  77 */             return (pStack.func_77973_b() == Items.field_151141_av && !func_75216_d() && whiteWalkie.func_70909_n());
/*     */           }
/*     */ 
/*     */           
/*     */           @OnlyIn(Dist.CLIENT)
/*     */           public boolean func_111238_b() {
/*  83 */             return whiteWalkie.func_70909_n();
/*     */           }
/*     */         });
/*     */     
/*  87 */     if (this.whiteWalkie.hasChest()) {
/*  88 */       for (int k = 0; k < 3; k++) {
/*  89 */         for (int l = 0; l < this.whiteWalkie.getInventoryColumns(); l++) {
/*  90 */           func_75146_a(new Slot(this.storage, 3 + l + k * this.whiteWalkie.getInventoryColumns() + this.whiteWalkie.getInventoryPage() * pageSize, 80 + l * 18, 18 + k * 18));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  95 */     for (int i1 = 0; i1 < 3; i1++) {
/*  96 */       for (int k1 = 0; k1 < 9; k1++) {
/*  97 */         func_75146_a(new Slot((IInventory)playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
/*     */       }
/*     */     } 
/*     */     
/* 101 */     for (int j1 = 0; j1 < 9; j1++) {
/* 102 */       func_75146_a(new Slot((IInventory)playerInventory, j1, 8 + j1 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(PlayerEntity pPlayer) {
/* 108 */     return (this.storage.func_70300_a(pPlayer) && this.whiteWalkie.func_70089_S() && this.whiteWalkie.func_70032_d((Entity)pPlayer) < 8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(PlayerEntity pPlayer, int pIndex) {
/* 113 */     ItemStack itemstack = ItemStack.field_190927_a;
/* 114 */     Slot slot = this.field_75151_b.get(pIndex);
/* 115 */     if (slot != null && slot.func_75216_d()) {
/* 116 */       ItemStack itemstack1 = slot.func_75211_c();
/* 117 */       itemstack = itemstack1.func_77946_l();
/* 118 */       int i = this.storage.func_70302_i_();
/* 119 */       if (pIndex < i) {
/* 120 */         if (!func_75135_a(itemstack1, i, this.field_75151_b.size(), true)) {
/* 121 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/* 124 */       else if (func_75139_a(1).func_75214_a(itemstack1) && !func_75139_a(1).func_75216_d()) {
/* 125 */         if (!func_75135_a(itemstack1, 1, 2, false)) {
/* 126 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/* 129 */       else if (func_75139_a(0).func_75214_a(itemstack1)) {
/* 130 */         if (!func_75135_a(itemstack1, 0, 1, false)) {
/* 131 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/* 134 */       else if (i <= 2 || !func_75135_a(itemstack1, 2, i, false)) {
/* 135 */         int j = i + 27;
/* 136 */         int k = j + 9;
/* 137 */         if (pIndex >= j && pIndex < k) {
/* 138 */           if (!func_75135_a(itemstack1, i, j, false)) {
/* 139 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/* 142 */         else if (pIndex >= i && pIndex < j) {
/* 143 */           if (!func_75135_a(itemstack1, j, k, false)) {
/* 144 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/* 147 */         else if (!func_75135_a(itemstack1, j, j, false)) {
/* 148 */           return ItemStack.field_190927_a;
/*     */         } 
/*     */         
/* 151 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 154 */       if (itemstack1.func_190926_b()) {
/* 155 */         slot.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/*     */         
/* 158 */         slot.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 162 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75134_a(PlayerEntity player) {
/* 167 */     super.func_75134_a(player);
/* 168 */     this.storage.func_174886_c(player);
/*     */   }
/*     */   
/*     */   public WhiteWalkieEntity getWhiteWalkie() {
/* 172 */     return this.whiteWalkie;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\containers\WhiteWalkieStorageContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */