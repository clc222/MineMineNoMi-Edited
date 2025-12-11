package xyz.pixelatedw.mineminenomi.mixins.client;

import java.util.List;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LivingRenderer.class})
public interface ILivingRendererMixin<T extends net.minecraft.entity.LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel<T>> {
  @Accessor("layers")
  List<LayerRenderer<T, M>> getLayers();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ILivingRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */