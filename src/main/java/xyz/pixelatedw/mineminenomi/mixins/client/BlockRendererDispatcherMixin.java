package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.FluidBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({BlockRendererDispatcher.class})
public interface BlockRendererDispatcherMixin {
  @Accessor("liquidBlockRenderer")
  void setfluidRenderer(FluidBlockRenderer paramFluidBlockRenderer);
  
  @Accessor("liquidBlockRenderer")
  FluidBlockRenderer getfluidRenderer();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\BlockRendererDispatcherMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */