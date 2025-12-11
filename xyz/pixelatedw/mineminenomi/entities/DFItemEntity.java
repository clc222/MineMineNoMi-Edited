/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class DFItemEntity extends ItemEntity {
/* 16 */   private int playerCounter = 0;
/*    */   
/*    */   public DFItemEntity(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DFItemEntity(World world) {
/* 23 */     super((EntityType)ModEntities.DEVIL_FRUIT_ITEM.get(), world);
/*    */   }
/*    */   
/*    */   public DFItemEntity(World world, double x, double y, double z, ItemStack stack) {
/* 27 */     this(world);
/* 28 */     func_92058_a(stack);
/* 29 */     this.lifespan = (stack.func_77973_b() == null) ? 6000 : stack.getEntityLifespan(world);
/* 30 */     func_70107_b(x, y, z);
/* 31 */     this.field_70125_A = this.field_70146_Z.nextFloat() * 360.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70106_y() {
/* 36 */     if (func_92059_d().func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem && !func_92059_d().func_190926_b() && !this.field_70170_p.field_72995_K) {
/* 37 */       OFPWWorldData.get().lostOneFruit(func_92059_d().func_77973_b().getRegistryName(), null, "Dropped item got destroyed");
/*    */     }
/* 39 */     super.func_70106_y();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70100_b_(PlayerEntity player) {
/* 44 */     super.func_70100_b_(player);
/* 45 */     if (!player.field_70170_p.field_72995_K && 
/* 46 */       !func_174874_s()) {
/* 47 */       this.playerCounter = -1;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_213284_aV() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_184178_b(ServerPlayerEntity player) {
/* 62 */     this.playerCounter++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_184203_c(ServerPlayerEntity player) {
/* 67 */     if (this.playerCounter > 0 && 
/* 68 */       this.playerCounter - 1 <= 0) {
/* 69 */       func_70106_y();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> func_213297_N() {
/* 76 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\DFItemEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */