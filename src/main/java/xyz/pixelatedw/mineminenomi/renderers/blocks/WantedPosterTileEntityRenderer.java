/*     */ package xyz.pixelatedw.mineminenomi.renderers.blocks;
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.FlagBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WantedPosterTileEntityRenderer extends TileEntityRenderer<WantedPosterTileEntity> {
/*  34 */   private static final float[] SIZE_SCALE = new float[] { 1.0F, 2.0F, 4.0F, 6.0F };
/*  35 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposter.png");
/*     */   private static final String FONT_HEX_COLOR = "#513413";
/*     */   private WantedPosterModel posterModel;
/*  38 */   private final ModelRenderer face = new ModelRenderer(64, 64, 7, 7);
/*  39 */   private final ModelRenderer faceOverlay = new ModelRenderer(64, 64, 39, 7);
/*     */   
/*     */   private ResourceLocation skin;
/*     */   
/*     */   private ResourceLocation background;
/*     */   
/*     */   public WantedPosterTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
/*  46 */     super(dispatcher);
/*  47 */     this.posterModel = new WantedPosterModel();
/*  48 */     this.face.func_228301_a_(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F);
/*  49 */     this.faceOverlay.func_228301_a_(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(WantedPosterTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
/*  55 */     BlockState blockstate = tileEntity.func_195044_w();
/*  56 */     WantedPosterData wantedPosterData = tileEntity.getWantedPosterData();
/*     */     
/*  58 */     String name = wantedPosterData.getOwnerName();
/*  59 */     UUID uuid = wantedPosterData.getOwnerId();
/*  60 */     ResourceLocation skinTexture = null;
/*     */     
/*  62 */     int idx = ((CanvasSize)blockstate.func_177229_b((Property)FlagBlock.SIZE)).ordinal();
/*  63 */     float scale = SIZE_SCALE[idx];
/*     */     
/*  65 */     matrixStack.func_227860_a_();
/*     */     
/*  67 */     matrixStack.func_227861_a_(0.5D, 0.5D, 0.5D);
/*  68 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, -((Direction)blockstate.func_177229_b((Property)WantedPosterBlock.FACING)).func_185119_l() + 180.0F, true));
/*  69 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*  70 */     matrixStack.func_227862_a_(scale, scale, 1.0F);
/*     */ 
/*     */     
/*  73 */     matrixStack.func_227860_a_();
/*     */     
/*  75 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*  76 */     matrixStack.func_227861_a_(-0.36D, 0.6D, 0.5D);
/*  77 */     matrixStack.func_227862_a_(0.6F, -0.6F, -0.01F);
/*  78 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getWantedPoster(TEXTURE));
/*  79 */     this.posterModel.func_225598_a_(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  81 */     matrixStack.func_227865_b_();
/*     */     
/*  83 */     if (Strings.isNullOrEmpty(name)) {
/*     */       
/*  85 */       matrixStack.func_227865_b_();
/*     */       
/*     */       return;
/*     */     } 
/*  89 */     String bounty = wantedPosterData.getBountyString();
/*     */ 
/*     */     
/*  92 */     matrixStack.func_227860_a_();
/*     */ 
/*     */     
/*  95 */     if (wantedPosterData.isExpired()) {
/*     */       
/*  97 */       matrixStack.func_227860_a_();
/*     */       
/*  99 */       matrixStack.func_227861_a_(-0.35D, -0.389D, 0.496D);
/* 100 */       matrixStack.func_227862_a_(0.173F, 0.132F, 0.001F);
/* 101 */       IVertexBuilder iVertexBuilder = buffer.getBuffer(ModRenderTypes.getWantedPosterExpiration(ModResources.EXPIRED));
/* 102 */       RendererHelper.drawQuad(matrixStack, iVertexBuilder, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 3.9F, 3.9F, combinedLight);
/*     */       
/* 104 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */ 
/*     */     
/* 108 */     matrixStack.func_227860_a_();
/*     */ 
/*     */     
/* 111 */     if (wantedPosterData.getOwnerProfile().isPresent()) {
/* 112 */       Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.func_71410_x().func_152342_ad().func_152788_a(wantedPosterData.getOwnerProfile().get());
/* 113 */       if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/* 114 */         skinTexture = Minecraft.func_71410_x().func_152342_ad().func_152792_a(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*     */       } else {
/* 116 */         skinTexture = DefaultPlayerSkin.func_177334_a(uuid);
/*     */       }
/*     */     
/* 119 */     } else if (wantedPosterData.getOwnerTexture().isPresent()) {
/* 120 */       skinTexture = wantedPosterData.getOwnerTexture().get();
/*     */     } else {
/* 122 */       skinTexture = DefaultPlayerSkin.func_177334_a(uuid);
/*     */     } 
/*     */ 
/*     */     
/* 126 */     matrixStack.func_227861_a_(-0.21D, -0.32D, 0.498D);
/* 127 */     matrixStack.func_227862_a_(0.8F, 0.8F, 0.001F);
/* 128 */     IVertexBuilder iVertexBuilder1 = buffer.getBuffer(ModRenderTypes.getWantedPoster(skinTexture));
/* 129 */     this.face.func_228309_a_(matrixStack, iVertexBuilder1, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 130 */     this.faceOverlay.func_228309_a_(matrixStack, iVertexBuilder1, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */     
/* 133 */     matrixStack.func_227865_b_();
/*     */ 
/*     */     
/* 136 */     matrixStack.func_227860_a_();
/*     */     
/* 138 */     if (tileEntity.isPirate()) {
/*     */       
/* 140 */       if (wantedPosterData.getOwnerCrew().isPresent()) {
/* 141 */         matrixStack.func_227861_a_(0.10999999940395355D, -0.09000000357627869D, 0.4970000088214874D);
/* 142 */         matrixStack.func_227862_a_(0.2F, 0.2F, 1.0F);
/* 143 */         RendererHelper.drawPlayerJollyRoger(((Crew)wantedPosterData.getOwnerCrew().get()).getJollyRoger(), matrixStack, buffer, combinedLight);
/*     */       }
/*     */     
/* 146 */     } else if (tileEntity.isRevolutionary()) {
/*     */       
/* 148 */       matrixStack.func_227861_a_(-0.05000000074505806D, -0.20999999344348907D, 0.4970000088214874D);
/* 149 */       iVertexBuilder1 = buffer.getBuffer(ModRenderTypes.getWantedPoster(ModResources.REVOLUTIONARY_ARMY_ICON));
/* 150 */       RendererHelper.drawQuad(matrixStack, iVertexBuilder1, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.5F, combinedLight);
/*     */     } 
/*     */     
/* 153 */     matrixStack.func_227865_b_();
/*     */ 
/*     */     
/* 156 */     matrixStack.func_227860_a_();
/*     */     
/* 158 */     matrixStack.func_227861_a_(-0.35D, -0.389D, 0.499D);
/* 159 */     matrixStack.func_227862_a_(0.173F, 0.132F, 0.001F);
/* 160 */     ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/gui/wantedposters/backgrounds/" + wantedPosterData.getBackground() + ".jpg");
/* 161 */     IVertexBuilder iVertexBuilder2 = buffer.getBuffer(ModRenderTypes.getWantedPoster(texture));
/* 162 */     RendererHelper.drawQuad(matrixStack, iVertexBuilder2, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 3.9F, 3.9F, combinedLight);
/*     */     
/* 164 */     matrixStack.func_227865_b_();
/*     */     
/* 166 */     matrixStack.func_227865_b_();
/*     */ 
/*     */     
/* 169 */     matrixStack.func_227860_a_();
/*     */     
/* 171 */     matrixStack.func_227862_a_(0.0075F, 0.008F, 0.0075F);
/* 172 */     matrixStack.func_227861_a_(-12.0D, 29.9D, 64.5D);
/*     */     
/* 174 */     if (name.length() > 13)
/* 175 */       name = name.substring(0, 10) + "..."; 
/* 176 */     this.field_228858_b_.field_147557_n.func_228079_a_(TextFormatting.BOLD + name, 9.0F - this.field_228858_b_.field_147557_n.func_78256_a(name) / 1.75F, -1.0F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.func_227866_c_().func_227870_a_(), buffer, false, 0, combinedLight);
/*     */     
/* 178 */     matrixStack.func_227862_a_(1.2F, 1.2F, 1.2F);
/*     */     
/* 180 */     boolean flag = (bounty.length() > 9);
/*     */     
/* 182 */     if (flag) {
/*     */       
/* 184 */       matrixStack.func_227860_a_();
/* 185 */       matrixStack.func_227861_a_(-5.0D, 1.5D, 0.0D);
/* 186 */       matrixStack.func_227862_a_(0.72F, 0.89F, 1.005F);
/*     */     } 
/* 188 */     this.field_228858_b_.field_147557_n.func_228079_a_(TextFormatting.BOLD + bounty, -18.0F, 9.5F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.func_227866_c_().func_227870_a_(), buffer, false, 0, combinedLight);
/* 189 */     if (flag) {
/* 190 */       matrixStack.func_227865_b_();
/*     */     }
/* 192 */     matrixStack.func_227862_a_(0.7F, 0.9F, 0.8F);
/* 193 */     this.field_228858_b_.field_147557_n.func_228079_a_(TextFormatting.BOLD + wantedPosterData.getDate(), -40.0F, 25.0F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.func_227866_c_().func_227870_a_(), buffer, false, 0, combinedLight);
/*     */     
/* 195 */     matrixStack.func_227865_b_();
/*     */     
/* 197 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\blocks\WantedPosterTileEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */