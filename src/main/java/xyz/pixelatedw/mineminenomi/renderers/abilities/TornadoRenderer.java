/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.TornadoEntity;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.TornadoModel;
/*     */ 
/*     */ public class TornadoRenderer<T extends TornadoEntity, M extends EntityModel<T>>
/*     */   extends EntityRenderer<T>
/*     */ {
/*  26 */   private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/models/projectiles/tornado1.png"), new ResourceLocation("mineminenomi", "textures/models/projectiles/tornado2.png"), new ResourceLocation("mineminenomi", "textures/models/projectiles/tornado3.png") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private TornadoModel model = new TornadoModel();
/*     */ 
/*     */   
/*     */   public TornadoRenderer(EntityRendererManager manager) {
/*  36 */     super(manager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  42 */     float ageInTicks = ((TornadoEntity)entity).field_70173_aa + partialTicks;
/*  43 */     float scale = entity.getSize();
/*     */     
/*  45 */     matrixStack.func_227860_a_();
/*     */     
/*  47 */     matrixStack.func_227860_a_();
/*     */     
/*  49 */     matrixStack.func_227862_a_(scale, scale, scale);
/*  50 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(180.0F));
/*  51 */     matrixStack.func_227861_a_(0.0D, -1.4D, 0.0D);
/*  52 */     IVertexBuilder ivb = buffer.getBuffer(RenderType.func_228644_e_(getTextureLocation(entity)));
/*  53 */     this.model.setupAnim((TornadoEntity)entity, 0.0F, 0.0F, ageInTicks, 0.0F, 0.0F);
/*  54 */     this.model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.4F);
/*     */ 
/*     */     
/*  57 */     matrixStack.func_227860_a_();
/*     */     
/*  59 */     matrixStack.func_227862_a_(0.85F, 0.85F, 0.85F);
/*  60 */     matrixStack.func_227861_a_(0.0D, 0.05D, 0.0D);
/*  61 */     this.model.setupAnim((TornadoEntity)entity, 0.0F, 0.0F, -ageInTicks / 2.0F, 0.0F, 0.0F);
/*  62 */     this.model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.6F);
/*     */     
/*  64 */     matrixStack.func_227865_b_();
/*     */     
/*  66 */     matrixStack.func_227865_b_();
/*     */     
/*  68 */     float spread = scale / 3.0F;
/*  69 */     int amount = 8;
/*     */     
/*  71 */     matrixStack.func_227861_a_(0.0D, 5.0D, 0.0D);
/*     */ 
/*     */     
/*  74 */     matrixStack.func_227860_a_();
/*     */     
/*  76 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(180.0F));
/*  77 */     float rotAmount = 45.0F;
/*  78 */     matrixStack.func_227861_a_(spread, -spread, spread);
/*  79 */     for (int i = 0; i < 8; i++) {
/*  80 */       float rot1 = i * 45.0F; float j;
/*  81 */       for (j = 0.0F; j < 8.0F; j++) {
/*  82 */         float rot2 = j * 45.0F;
/*  83 */         matrixStack.func_227860_a_();
/*     */ 
/*     */         
/*  86 */         matrixStack.func_227861_a_(-spread, -spread, -spread);
/*  87 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(rot1 + ((TornadoEntity)entity).field_70173_aa * entity.getSpeed()));
/*  88 */         matrixStack.func_227863_a_(Vector3f.field_229182_e_.func_229187_a_(rot2 - ((TornadoEntity)entity).field_70173_aa * entity.getSpeed() / 2.0F));
/*     */         
/*  90 */         matrixStack.func_227861_a_(spread, spread, spread);
/*     */         
/*  92 */         ItemStack stack = new ItemStack((IItemProvider)Blocks.field_150354_m);
/*  93 */         (Minecraft.func_71410_x().func_175597_ag()).field_178112_h.func_229109_a_(null, stack, ItemCameraTransforms.TransformType.NONE, false, matrixStack, buffer, ((TornadoEntity)entity).field_70170_p, packedLight, OverlayTexture.field_229196_a_);
/*  94 */         matrixStack.func_227865_b_();
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     matrixStack.func_227865_b_();
/*     */     
/* 100 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(T entity) {
/* 106 */     int i = Math.min(30, ((TornadoEntity)entity).field_70173_aa % 30);
/* 107 */     return TEXTURES[i / 10];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory
/*     */   {
/*     */     public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {
/* 119 */       return new TornadoRenderer<>(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\TornadoRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */