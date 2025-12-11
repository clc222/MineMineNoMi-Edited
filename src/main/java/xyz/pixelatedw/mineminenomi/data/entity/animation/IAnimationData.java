package xyz.pixelatedw.mineminenomi.data.entity.animation;

import java.util.LinkedList;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.api.animations.Animation;
import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;

public interface IAnimationData {
  IAnimationData setOwner(LivingEntity paramLivingEntity);
  
  void startAnimation(AnimationId<?> paramAnimationId, int paramInt, boolean paramBoolean);
  
  void stopAnimation(AnimationId<?> paramAnimationId);
  
  boolean isAnimationPlaying(AnimationId<?> paramAnimationId);
  
  @Nullable
  <E extends LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel> Animation<E, M> getAnimation();
  
  void tickAnimations();
  
  LinkedList<Animation<?, ?>> getAnimations();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\animation\IAnimationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */