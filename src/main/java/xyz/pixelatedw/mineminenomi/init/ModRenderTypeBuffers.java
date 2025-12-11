/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.shader.Framebuffer;
/*    */ import net.minecraft.client.shader.ShaderGroup;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.client.IWorldRendererMixin;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.buffers.HakiAuraBuffer;
/*    */ 
/*    */ public class ModRenderTypeBuffers
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private static ModRenderTypeBuffers INSTANCE;
/*    */   
/*    */   private Minecraft minecraft;
/*    */   
/*    */   private ShaderGroup hakiAuraShader;
/*    */   
/*    */   private HakiAuraBuffer hakiAuraBufferSource;
/*    */   
/*    */   private Framebuffer hakiAuraFramebuffer;
/*    */   
/*    */   public static void init() {
/* 28 */     ModRenderTypeBuffers buffers = new ModRenderTypeBuffers();
/* 29 */     buffers.minecraft = Minecraft.func_71410_x();
/* 30 */     buffers.hakiAuraBufferSource = new HakiAuraBuffer(Minecraft.func_71410_x().func_228019_au_().func_228487_b_());
/* 31 */     INSTANCE = buffers;
/*    */   }
/*    */   
/*    */   public void initHakiAuraShader() {
/* 35 */     if (this.hakiAuraShader != null) {
/* 36 */       this.hakiAuraShader.close();
/*    */     }
/*    */     
/* 39 */     ResourceLocation resourcelocation = new ResourceLocation("mineminenomi", "shaders/post/haki_aura.json");
/*    */     
/*    */     try {
/* 42 */       this.hakiAuraShader = new ShaderGroup(this.minecraft.func_110434_K(), this.minecraft.func_195551_G(), this.minecraft.func_147110_a(), resourcelocation);
/* 43 */       this.hakiAuraShader.func_148026_a(this.minecraft.func_228018_at_().func_198109_k(), this.minecraft.func_228018_at_().func_198091_l());
/* 44 */       this.hakiAuraFramebuffer = this.hakiAuraShader.func_177066_a("final");
/* 45 */       ((IWorldRendererMixin)this.minecraft.field_71438_f).setEntityTarget(this.hakiAuraFramebuffer);
/*    */     }
/* 47 */     catch (IOException ioexception) {
/* 48 */       LOGGER.warn("Failed to load haki aura shader: {}", resourcelocation, ioexception);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static ModRenderTypeBuffers getInstance() {
/* 53 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public HakiAuraBuffer getHakiAuraBuffer() {
/* 57 */     return this.hakiAuraBufferSource;
/*    */   }
/*    */   
/*    */   public ShaderGroup getHakiAuraShader() {
/* 61 */     return this.hakiAuraShader;
/*    */   }
/*    */   
/*    */   public Framebuffer getHakiAuraFramebuffer() {
/* 65 */     return this.hakiAuraFramebuffer;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModRenderTypeBuffers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */