package xyz.pixelatedw.mineminenomi.api.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;

@Deprecated
public interface IAnimation<E extends net.minecraft.entity.LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel> {
  void setupAnimation(E paramE, MatrixStack paramMatrixStack, IRenderTypeBuffer paramIRenderTypeBuffer, int paramInt, float paramFloat1, float paramFloat2);
  
  default void setupAnimation(E entity, M entityModel, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
  
  void setAnimationAngles(E paramE, M paramM, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\animations\IAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */