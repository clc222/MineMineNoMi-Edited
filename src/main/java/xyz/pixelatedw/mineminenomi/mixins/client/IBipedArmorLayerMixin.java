package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({BipedArmorLayer.class})
public interface IBipedArmorLayerMixin<T extends net.minecraft.entity.LivingEntity, M extends net.minecraft.client.renderer.entity.model.BipedModel<T>, A extends net.minecraft.client.renderer.entity.model.BipedModel<T>> {
  @Accessor("innerModel")
  A getLegsModel();
  
  @Accessor("outerModel")
  A getBodyModel();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\IBipedArmorLayerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */