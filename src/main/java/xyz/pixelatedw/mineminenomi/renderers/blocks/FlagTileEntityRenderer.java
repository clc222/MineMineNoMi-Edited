/*     */ package xyz.pixelatedw.mineminenomi.renderers.blocks;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.model.ModelBakery;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.FlagBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.models.blocks.FlagModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FlagTileEntityRenderer extends TileEntityRenderer<FlagTileEntity> {
/*  33 */   public static final ResourceLocation ON_FIRE_TEXTURE = new ResourceLocation("mineminenomi", "textures/misc/flagflame.png");
/*     */   
/*  35 */   private static final float[] SIZE_SCALE = new float[] { 1.0F, 2.0F, 4.0F, 6.0F };
/*  36 */   private static final float[] OFFSET_X = new float[] { -0.5F, -0.75F, -0.87F, -0.916F };
/*  37 */   private static final float[] OFFSET_Y = new float[] { 0.0F, -0.5F, -0.75F, -0.833F };
/*     */   
/*     */   private FlagModel flagModel;
/*     */   
/*     */   public FlagTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
/*  42 */     super(dispatcher);
/*  43 */     this.flagModel = new FlagModel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(FlagTileEntity blockEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
/*  48 */     if (blockEntity.isSub()) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     World level = blockEntity.func_145831_w();
/*  53 */     UUID id = blockEntity.getOwnerUUID();
/*  54 */     PlayerEntity owner = level.func_217371_b(id);
/*     */     
/*  56 */     BlockState blockstate = blockEntity.func_195044_w();
/*     */     
/*  58 */     int idx = ((CanvasSize)blockstate.func_177229_b((Property)FlagBlock.SIZE)).ordinal();
/*  59 */     float scale = SIZE_SCALE[idx];
/*     */     
/*  61 */     Direction dir = ((Direction)blockstate.func_177229_b((Property)FlagBlock.FACING)).func_176734_d();
/*  62 */     BlockPos supportPos = blockEntity.func_174877_v().func_177982_a(dir.func_82601_c(), dir.func_96559_d(), dir.func_82599_e());
/*  63 */     BlockState supportBlock = level.func_180495_p(supportPos);
/*  64 */     double supportOffset = 1.0D - supportBlock.func_196954_c((IBlockReader)level, supportPos).func_197758_c(dir.func_176740_k());
/*     */     
/*  66 */     matrixStack.func_227861_a_(0.5D, 0.0D, 0.5D);
/*  67 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, -((Direction)blockstate.func_177229_b((Property)FlagBlock.FACING)).func_185119_l() + 90.0F, true));
/*  68 */     matrixStack.func_227861_a_(supportOffset, 0.0D, 0.0D);
/*  69 */     matrixStack.func_227862_a_(scale, scale, 1.0F);
/*  70 */     matrixStack.func_227861_a_(OFFSET_X[idx], OFFSET_Y[idx], 0.5D);
/*     */     
/*  72 */     long flutterTick = level.func_82737_E() + (blockEntity.hashCode() / 100000);
/*     */     
/*  74 */     matrixStack.func_227860_a_();
/*  75 */     float flutterAnim = (float)(Math.cos(((float)flutterTick * 0.1F) + Math.PI) * 0.014999999664723873D);
/*  76 */     matrixStack.func_227860_a_();
/*  77 */     matrixStack.func_227861_a_(0.75D, 0.96D - flutterAnim, -0.55D);
/*  78 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/*  79 */     matrixStack.func_227862_a_(0.9F, 0.9F, 1.0F);
/*  80 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229193_c_(MathHelper.func_76134_b((float)flutterTick * 0.12F + 3.1415927F) * 0.023F));
/*  81 */     RendererHelper.drawPlayerFactionIcon(blockEntity.getFaction(), blockEntity.getCrew(), (LivingEntity)owner, matrixStack, buffer, combinedLight);
/*  82 */     matrixStack.func_227865_b_();
/*     */     
/*  84 */     matrixStack.func_227860_a_();
/*  85 */     matrixStack.func_227861_a_(0.75D, 0.96D - flutterAnim, -0.47D);
/*  86 */     matrixStack.func_227862_a_(1.0F, -1.0F, 1.0F);
/*  87 */     matrixStack.func_227862_a_(0.9F, 0.9F, 1.0F);
/*  88 */     matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229193_c_(MathHelper.func_76134_b((float)flutterTick * 0.12F + 3.1415927F) * 0.02F));
/*  89 */     matrixStack.func_227861_a_(-1.0D, 0.0D, 0.0D);
/*  90 */     RendererHelper.drawPlayerFactionIcon(blockEntity.getFaction(), blockEntity.getCrew(), (LivingEntity)owner, matrixStack, buffer, combinedLight);
/*  91 */     matrixStack.func_227865_b_();
/*  92 */     matrixStack.func_227865_b_();
/*     */     
/*  94 */     RenderType renderType = ModRenderTypes.FLAG;
/*     */     
/*  96 */     Color backgroundColor = Color.WHITE;
/*  97 */     if (ModValues.PIRATE.equals(blockEntity.getFaction())) {
/*  98 */       backgroundColor = Color.BLACK;
/*     */     }
/* 100 */     else if (ModValues.MARINE.equals(blockEntity.getFaction()) || ModValues.WORLD_GOVT.equals(blockEntity.getFaction())) {
/* 101 */       backgroundColor = WyHelper.hexToRGB("#EAF5EC");
/*     */     }
/* 103 */     else if (ModValues.REVOLUTIONARY.equals(blockEntity.getFaction())) {
/* 104 */       backgroundColor = WyHelper.hexToRGB("#AE0000");
/*     */     } 
/*     */     
/* 107 */     if (blockEntity.isOnFire()) {
/* 108 */       renderType = ModRenderTypes.FLAG_ON_FIRE;
/* 109 */       backgroundColor = Color.WHITE;
/* 110 */       if (ModValues.PIRATE.equals(blockEntity.getFaction())) {
/* 111 */         backgroundColor = Color.GRAY;
/*     */       }
/*     */       
/* 114 */       matrixStack.func_227860_a_();
/* 115 */       matrixStack.func_227861_a_(0.25D, 0.0D, -0.6D);
/* 116 */       renderFlame(matrixStack, buffer, 1.0F, 0.55F);
/* 117 */       matrixStack.func_227862_a_(-1.0F, 1.0F, 1.0F);
/* 118 */       matrixStack.func_227861_a_(0.0D, 0.0D, 0.15D);
/* 119 */       renderFlame(matrixStack, buffer, 1.0F, 0.55F);
/* 120 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */     
/* 123 */     matrixStack.func_227860_a_();
/* 124 */     matrixStack.func_227861_a_(0.5D, 1.5D, -0.51D);
/* 125 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 126 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 127 */     this.flagModel.flagAnimation(level, flutterTick);
/* 128 */     this.flagModel.func_225598_a_(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, backgroundColor.getRed() / 255.0F, backgroundColor.getGreen() / 255.0F, backgroundColor.getBlue() / 255.0F, 1.0F);
/* 129 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   public void renderFlame(MatrixStack matrixStack, IRenderTypeBuffer buffer, float width, float height) {
/* 133 */     TextureAtlasSprite fire0Atlas = ModelBakery.field_207763_a.func_229314_c_();
/* 134 */     TextureAtlasSprite fire1Atlas = ModelBakery.field_207764_b.func_229314_c_();
/* 135 */     matrixStack.func_227860_a_();
/* 136 */     float f = width * 1.4F;
/* 137 */     matrixStack.func_227862_a_(f, f, f);
/* 138 */     float f1 = 0.5F;
/* 139 */     float f3 = height / f;
/* 140 */     float f4 = 0.0F;
/* 141 */     float f5 = 0.0F;
/* 142 */     int i = 0;
/* 143 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(Atlases.func_228783_h_());
/*     */     
/* 145 */     for (MatrixStack.Entry matrixEntry = matrixStack.func_227866_c_(); f3 > 0.0F; i++) {
/* 146 */       TextureAtlasSprite textureAtlas = (i % 2 == 0) ? fire0Atlas : fire1Atlas;
/* 147 */       float f6 = textureAtlas.func_94209_e();
/* 148 */       float f7 = textureAtlas.func_94206_g();
/* 149 */       float f8 = textureAtlas.func_94212_f();
/* 150 */       float f9 = textureAtlas.func_94210_h();
/* 151 */       if (i / 2 % 2 == 0) {
/* 152 */         float f10 = f8;
/* 153 */         f8 = f6;
/* 154 */         f6 = f10;
/*     */       } 
/*     */       
/* 157 */       fireVertex(matrixEntry, ivertexbuilder, f1 - 0.0F, 0.0F - f4, f5, f8, f9);
/* 158 */       fireVertex(matrixEntry, ivertexbuilder, -f1 - 0.0F, 0.0F - f4, f5, f6, f9);
/* 159 */       fireVertex(matrixEntry, ivertexbuilder, -f1 - 0.0F, 1.4F - f4, f5, f6, f7);
/* 160 */       fireVertex(matrixEntry, ivertexbuilder, f1 - 0.0F, 1.4F - f4, f5, f8, f7);
/* 161 */       f3 -= 0.45F;
/* 162 */       f4 -= 0.45F;
/* 163 */       f1 *= 0.9F;
/* 164 */       f5 += 0.03F;
/*     */     } 
/*     */     
/* 167 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   private static void fireVertex(MatrixStack.Entry matrixEntry, IVertexBuilder buffer, float x, float y, float z, float texU, float texV) {
/* 171 */     buffer.func_227888_a_(matrixEntry.func_227870_a_(), x, y, z).func_225586_a_(255, 255, 255, 255).func_225583_a_(texU, texV).func_225585_a_(0, 10).func_227886_a_(240).func_227887_a_(matrixEntry.func_227872_b_(), 0.0F, 1.0F, 0.0F).func_181675_d();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\blocks\FlagTileEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */