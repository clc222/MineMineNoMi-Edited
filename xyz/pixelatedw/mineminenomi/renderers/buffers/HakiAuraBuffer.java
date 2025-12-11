/*     */ package xyz.pixelatedw.mineminenomi.renderers.buffers;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.DefaultColorVertexBuilder;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import com.mojang.blaze3d.vertex.VertexBuilderUtils;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ 
/*     */ public class HakiAuraBuffer implements IRenderTypeBuffer {
/*     */   private final IRenderTypeBuffer.Impl bufferSource;
/*  12 */   private final IRenderTypeBuffer.Impl testSource = IRenderTypeBuffer.func_228455_a_(new BufferBuilder(256));
/*  13 */   private int red = 255;
/*  14 */   private int green = 255;
/*  15 */   private int blue = 255;
/*  16 */   private int alpha = 255;
/*     */   
/*     */   public HakiAuraBuffer(IRenderTypeBuffer.Impl buffer) {
/*  19 */     this.bufferSource = buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public IVertexBuilder getBuffer(RenderType type) {
/*  24 */     IVertexBuilder ivertexbuilder = this.bufferSource.getBuffer(type);
/*  25 */     IVertexBuilder ivertexbuilder2 = this.testSource.getBuffer(type);
/*  26 */     ColorVertexBuilder testBuffer = new ColorVertexBuilder(ivertexbuilder2, this.red, this.green, this.blue, this.alpha);
/*  27 */     return VertexBuilderUtils.func_227915_a_((IVertexBuilder)testBuffer, ivertexbuilder);
/*     */   }
/*     */   
/*     */   public void setColor(int red, int green, int blue, int alpha) {
/*  31 */     this.red = red;
/*  32 */     this.green = green;
/*  33 */     this.blue = blue;
/*  34 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */   public void endBatch() {
/*  38 */     this.testSource.func_228461_a_();
/*     */   }
/*     */   
/*     */   static class ColorVertexBuilder extends DefaultColorVertexBuilder {
/*     */     private final IVertexBuilder delegate;
/*     */     private double x;
/*     */     private double y;
/*     */     private double z;
/*     */     private float u;
/*     */     private float v;
/*     */     
/*     */     private ColorVertexBuilder(IVertexBuilder delegate, int red, int green, int blue, int alpha) {
/*  50 */       this.delegate = delegate;
/*  51 */       super.func_225611_b_(red, green, blue, alpha);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_225611_b_(int red, int green, int blue, int alpha) {}
/*     */ 
/*     */     
/*     */     public IVertexBuilder func_225582_a_(double pX, double pY, double pZ) {
/*  59 */       this.x = pX;
/*  60 */       this.y = pY;
/*  61 */       this.z = pZ;
/*  62 */       return (IVertexBuilder)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IVertexBuilder func_225586_a_(int red, int green, int blue, int alpha) {
/*  67 */       return (IVertexBuilder)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IVertexBuilder func_225583_a_(float u, float v) {
/*  72 */       this.u = u;
/*  73 */       this.v = v;
/*  74 */       return (IVertexBuilder)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IVertexBuilder func_225585_a_(int u, int v) {
/*  79 */       return (IVertexBuilder)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IVertexBuilder func_225587_b_(int u, int v) {
/*  84 */       return (IVertexBuilder)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IVertexBuilder func_225584_a_(float x, float y, float z) {
/*  89 */       return (IVertexBuilder)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_225588_a_(float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
/*  94 */       this.delegate.func_225582_a_(x, y, z)
/*  95 */         .func_225586_a_(this.field_227855_b_, this.field_227856_c_, this.field_227857_d_, this.field_227858_e_)
/*  96 */         .func_225583_a_(texU, texV)
/*  97 */         .func_227891_b_(overlayUV)
/*  98 */         .func_227886_a_(lightmapUV)
/*  99 */         .func_225584_a_(normalX, normalY, normalZ)
/* 100 */         .func_181675_d();
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_181675_d() {
/* 105 */       this.delegate.func_225582_a_(this.x, this.y, this.z).func_225586_a_(this.field_227855_b_, this.field_227856_c_, this.field_227857_d_, this.field_227858_e_).func_225583_a_(this.u, this.v).func_181675_d();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\buffers\HakiAuraBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */