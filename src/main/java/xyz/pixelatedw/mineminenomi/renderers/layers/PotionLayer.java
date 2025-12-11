/*     */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IBlockOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.TornadoModel;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PotionLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/*  34 */   private final TornadoModel tornadoModel = new TornadoModel();
/*     */   
/*     */   public PotionLayer(IEntityRenderer renderer) {
/*  37 */     super(renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/*  44 */     for (EffectInstance instance : entity.func_70651_bq()) {
/*  45 */       Effect effect = instance.func_188419_a();
/*     */       
/*  47 */       if (entity.func_70644_a(effect) && entity.func_70660_b(effect).func_76459_b() <= 0) {
/*  48 */         entity.func_195063_d(effect);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  53 */     for (EffectInstance instance : entity.func_70651_bq()) {
/*  54 */       Effect effect = instance.func_188419_a();
/*     */ 
/*     */       
/*  57 */       if (effect == ModEffects.PUNK_CROSS.get()) {
/*  58 */         matrixStack.func_227860_a_();
/*     */         
/*  60 */         float blocksWidth = (float)(Math.ceil(entity.func_213311_cf()) + 1.0D);
/*  61 */         float blocksHeight = (float)(Math.ceil(entity.func_213302_cg()) + 1.0D);
/*  62 */         matrixStack.func_227861_a_((0.4F - blocksWidth / 2.0F), (1.4F - entity.func_213302_cg() / 2.0F - blocksHeight / 2.0F), (0.4F - blocksWidth / 2.0F));
/*  63 */         matrixStack.func_227861_a_(0.0D, -2.0D, 0.0D);
/*  64 */         Random rand = new Random(); int x;
/*  65 */         for (x = 0; x < blocksWidth; x++) {
/*     */           
/*  67 */           for (int y = 0; y < blocksHeight + 2.0F; y++) {
/*     */             
/*  69 */             for (int z = 0; z < blocksWidth; z++) {
/*     */               
/*  71 */               int seed = 1 + x + y + z;
/*  72 */               rand.setSeed(seed);
/*  73 */               int w = rand.nextInt(180);
/*  74 */               matrixStack.func_227860_a_();
/*  75 */               matrixStack.func_227861_a_(x, y, z);
/*  76 */               matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(w));
/*  77 */               matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-w));
/*  78 */               Minecraft.func_71410_x().func_175599_af().func_229110_a_(new ItemStack((IItemProvider)Blocks.field_150339_S), ItemCameraTransforms.TransformType.HEAD, packedLight, OverlayTexture.field_229196_a_, matrixStack, buffer);
/*  79 */               matrixStack.func_227865_b_();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  84 */         matrixStack.func_227861_a_(-1.5D, 1.0D, 0.5D);
/*  85 */         for (x = 0; x < blocksHeight + 2.0F; x++) {
/*     */           
/*  87 */           for (int y = 0; y < blocksWidth; y++) {
/*     */             
/*  89 */             int seed = 1 + x + y;
/*  90 */             rand.setSeed(seed);
/*  91 */             int w = rand.nextInt(180);
/*  92 */             matrixStack.func_227860_a_();
/*  93 */             matrixStack.func_227861_a_(x, y, 0.0D);
/*  94 */             matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(w));
/*  95 */             matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-w));
/*  96 */             Minecraft.func_71410_x().func_175599_af().func_229110_a_(new ItemStack((IItemProvider)Blocks.field_150339_S), ItemCameraTransforms.TransformType.HEAD, packedLight, OverlayTexture.field_229196_a_, matrixStack, buffer);
/*  97 */             matrixStack.func_227865_b_();
/*     */           } 
/*     */         } 
/*     */         
/* 101 */         matrixStack.func_227865_b_(); continue;
/*     */       } 
/* 103 */       if (effect == ModEffects.CYCLONE_TEMPO.get()) {
/* 104 */         matrixStack.func_227860_a_();
/*     */         
/* 106 */         float width = entity.func_213311_cf() * 6.0F;
/* 107 */         float height = entity.func_213302_cg() * 1.15F;
/*     */         
/* 109 */         matrixStack.func_227862_a_(width, height, width);
/* 110 */         matrixStack.func_227861_a_(0.0D, -0.30000001192092896D, 0.0D);
/* 111 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(((LivingEntity)entity).field_70173_aa * 3.1415927F * 1.5F));
/*     */         
/* 113 */         IVertexBuilder vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/* 114 */         this.tornadoModel.func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 0.5F, 0.8F, 1.0F, 0.4F);
/*     */         
/* 116 */         float anim = (float)Math.sin((instance.func_76459_b() % 20.0F / 20.0F * 3.0F));
/* 117 */         matrixStack.func_227862_a_(anim, anim, anim);
/*     */         
/* 119 */         matrixStack.func_227861_a_(0.0D, -0.10000000149011612D, 0.0D);
/* 120 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(((LivingEntity)entity).field_70173_aa * 3.1415927F * 2.5F));
/*     */         
/* 122 */         this.tornadoModel.func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, 0.5F, 0.8F, 1.0F, 0.4F);
/*     */         
/* 124 */         matrixStack.func_227865_b_(); continue;
/*     */       } 
/* 126 */       if (effect instanceof IBlockOverlayEffect) {
/* 127 */         Block overlay = ((IBlockOverlayEffect)effect).getBlockOverlay(instance.func_76459_b(), instance.func_76458_c());
/* 128 */         if (overlay != null) {
/* 129 */           matrixStack.func_227860_a_();
/*     */           
/* 131 */           float blocksWidth = (float)(Math.ceil(entity.func_213311_cf()) + 1.0D);
/* 132 */           float blocksHeight = (float)(Math.ceil(entity.func_213302_cg()) + 1.0D);
/* 133 */           matrixStack.func_227861_a_((0.4F - blocksWidth / 2.0F), (1.4F - entity.func_213302_cg() / 2.0F - blocksHeight / 2.0F), (0.4F - blocksWidth / 2.0F));
/*     */           
/* 135 */           for (int x = 0; x < blocksWidth; x++) {
/* 136 */             for (int y = 0; y < blocksHeight; y++) {
/* 137 */               for (int z = 0; z < blocksWidth; z++) {
/* 138 */                 matrixStack.func_227860_a_();
/* 139 */                 matrixStack.func_227861_a_(x, y, z);
/* 140 */                 Minecraft.func_71410_x().func_175599_af().func_229110_a_(new ItemStack((IItemProvider)overlay), ItemCameraTransforms.TransformType.HEAD, packedLight, OverlayTexture.field_229196_a_, matrixStack, buffer);
/* 141 */                 matrixStack.func_227865_b_();
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 146 */           matrixStack.func_227865_b_();
/*     */         }  continue;
/*     */       } 
/* 149 */       if (effect instanceof IColorOverlayEffect) {
/* 150 */         float[] colors = ((IColorOverlayEffect)effect).getBodyOverlayColor(instance.func_76459_b(), instance.func_76458_c());
/* 151 */         if (colors != null) {
/* 152 */           matrixStack.func_227860_a_();
/* 153 */           IVertexBuilder vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/* 154 */           func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, colors[0], colors[1], colors[2], colors[3]);
/* 155 */           matrixStack.func_227865_b_();
/*     */         }  continue;
/*     */       } 
/* 158 */       if (effect instanceof ITextureOverlayEffect) {
/* 159 */         ResourceLocation res = ((ITextureOverlayEffect)effect).getBodyTexture(instance.func_76459_b(), instance.func_76458_c());
/* 160 */         if (res != null) {
/* 161 */           matrixStack.func_227860_a_();
/* 162 */           IVertexBuilder vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(res));
/* 163 */           float[] colors = ((ITextureOverlayEffect)effect).getOverlayColor();
/* 164 */           func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, colors[0], colors[1], colors[2], colors[3]);
/* 165 */           matrixStack.func_227865_b_();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\PotionLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */