package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.renderer.RenderTypeBuffers;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.shader.Framebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({WorldRenderer.class})
public interface IWorldRendererMixin {
  @Accessor("entityTarget")
  void setEntityTarget(Framebuffer paramFramebuffer);
  
  @Accessor("viewArea")
  void setViewFrustum(ViewFrustum paramViewFrustum);
  
  @Accessor("viewArea")
  ViewFrustum getViewFrustum();
  
  @Accessor("renderBuffers")
  RenderTypeBuffers getRenderTypeTextures();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\IWorldRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */