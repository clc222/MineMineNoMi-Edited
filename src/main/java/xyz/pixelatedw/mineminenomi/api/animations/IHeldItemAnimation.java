package xyz.pixelatedw.mineminenomi.api.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;

@Deprecated
public interface IHeldItemAnimation<E extends net.minecraft.entity.LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel> extends IAnimation<E, M> {
  void setupHeldItem(E paramE, ItemStack paramItemStack, ItemCameraTransforms.TransformType paramTransformType, HandSide paramHandSide, MatrixStack paramMatrixStack, IRenderTypeBuffer paramIRenderTypeBuffer, int paramInt);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\animations\IHeldItemAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */