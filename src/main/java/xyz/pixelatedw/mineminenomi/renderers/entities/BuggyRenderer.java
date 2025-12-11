/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.BigNoseLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.BuggyHairLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.BaraCarLayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BuggyRenderer
/*    */   extends HumanoidRenderer<BuggyEntity, HumanoidModel<BuggyEntity>>
/*    */ {
/* 27 */   private ItemStack headStack = ItemStack.field_190927_a;
/* 28 */   private ItemStack chestStack = ItemStack.field_190927_a;
/*    */   
/*    */   public BuggyRenderer(EntityRendererManager manager) {
/* 31 */     super(manager, new HumanoidModel(), "buggy");
/* 32 */     this.bullshitFlag = true;
/*    */     
/* 34 */     func_177094_a((LayerRenderer)new BigNoseLayer((IEntityRenderer)this));
/* 35 */     func_177094_a((LayerRenderer)new BuggyHairLayer((IEntityRenderer)this));
/* 36 */     func_177094_a((LayerRenderer)new BaraCarLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(BuggyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 41 */     if (entity.clientState == 1) {
/* 42 */       ((HumanoidModel)this.field_77045_g).field_178723_h.field_78806_j = false;
/*    */     }
/* 44 */     else if (entity.clientState == 2) {
/* 45 */       ((HumanoidModel)this.field_77045_g).field_178722_k.field_78806_j = false;
/* 46 */       ((HumanoidModel)this.field_77045_g).field_178721_j.field_78806_j = false;
/* 47 */       entity.field_70177_z += 10.0F;
/* 48 */       entity.field_70126_B = entity.field_70177_z;
/*    */     }
/* 50 */     else if (entity.clientState == 3) {
/* 51 */       ((HumanoidModel)this.field_77045_g).field_178724_i.field_78806_j = false;
/* 52 */       ((HumanoidModel)this.field_77045_g).field_178723_h.field_78806_j = false;
/* 53 */       ((HumanoidModel)this.field_77045_g).field_178722_k.field_78806_j = false;
/* 54 */       ((HumanoidModel)this.field_77045_g).field_178721_j.field_78806_j = false;
/*    */     } else {
/*    */       
/* 57 */       ((HumanoidModel)this.field_77045_g).func_178719_a(true);
/*    */     } 
/*    */     
/* 60 */     if (entity.clientCarState == 1) {
/* 61 */       if (this.headStack.func_190926_b()) {
/* 62 */         this.headStack = entity.func_184582_a(EquipmentSlotType.HEAD);
/*    */       }
/*    */       
/* 65 */       if (this.chestStack.func_190926_b()) {
/* 66 */         this.chestStack = entity.func_184582_a(EquipmentSlotType.CHEST);
/*    */       }
/*    */       
/* 69 */       entity.func_184201_a(EquipmentSlotType.HEAD, ItemStack.field_190927_a);
/* 70 */       entity.func_184201_a(EquipmentSlotType.CHEST, ItemStack.field_190927_a);
/* 71 */       ((HumanoidModel)this.field_77045_g).func_178719_a(false);
/*    */     }
/* 73 */     else if (entity.clientCarState == -1) {
/* 74 */       if (!this.headStack.func_190926_b()) {
/* 75 */         entity.func_184201_a(EquipmentSlotType.HEAD, this.headStack);
/*    */       }
/*    */       
/* 78 */       if (!this.chestStack.func_190926_b()) {
/* 79 */         entity.func_184201_a(EquipmentSlotType.CHEST, this.chestStack);
/*    */       }
/*    */       
/* 82 */       this.headStack = ItemStack.field_190927_a;
/* 83 */       this.chestStack = ItemStack.field_190927_a;
/*    */     } else {
/*    */       
/* 86 */       ((HumanoidModel)this.field_77045_g).func_178719_a(true);
/*    */     } 
/*    */     
/* 89 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 95 */       return (EntityRenderer)new BuggyRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BuggyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */