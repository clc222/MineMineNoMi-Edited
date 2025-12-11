/*    */ package xyz.pixelatedw.mineminenomi.particles.rendertypes;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.IParticleRenderType;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SimpleParticleRenderType implements IParticleRenderType {
/*    */   private ResourceLocation texture;
/*    */   
/*    */   public SimpleParticleRenderType(ResourceLocation texture) {
/* 18 */     this.texture = texture;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_217600_a(BufferBuilder buffer, TextureManager textureManager) {
/* 24 */     RenderSystem.depthMask(true);
/* 25 */     if (WyHelper.isInChallengeDimension((World)(Minecraft.func_71410_x()).field_71441_e)) {
/* 26 */       RenderSystem.depthMask(true);
/*    */     }
/* 28 */     RenderSystem.enableBlend();
/* 29 */     RenderSystem.defaultBlendFunc();
/* 30 */     RenderSystem.disableCull();
/* 31 */     textureManager.func_110577_a(this.texture);
/* 32 */     buffer.func_181668_a(7, ModRenderTypes.PARTICLE_POSITION_TEX_COLOR_LMAP);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_217599_a(Tessellator tess) {
/* 37 */     tess.func_78381_a();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\rendertypes\SimpleParticleRenderType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */