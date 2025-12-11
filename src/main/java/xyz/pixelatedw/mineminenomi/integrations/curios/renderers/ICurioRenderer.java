package xyz.pixelatedw.mineminenomi.integrations.curios.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface ICurioRenderer {
  void render(String paramString, int paramInt1, MatrixStack paramMatrixStack, IRenderTypeBuffer paramIRenderTypeBuffer, int paramInt2, LivingEntity paramLivingEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, ItemStack paramItemStack);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\curios\renderers\ICurioRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */