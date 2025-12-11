/*    */ package xyz.pixelatedw.mineminenomi.api.animations;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimationId<A extends Animation<?, ?>>
/*    */ {
/* 11 */   public static final HashMap<AnimationId<?>, Animation<?, ?>> REGISTERED_ANIMATIONS = new HashMap<>();
/*    */   
/*    */   private ResourceLocation id;
/*    */   
/*    */   public AnimationId(ResourceLocation id) {
/* 16 */     this.id = id;
/*    */   }
/*    */   
/*    */   public static <A extends Animation<?, ?>> void register(A anim) {
/* 20 */     REGISTERED_ANIMATIONS.put(anim.getId(), (Animation<?, ?>)anim);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static <A extends Animation<?, ?>> A getRegisteredAnimation(AnimationId<A> id) {
/* 25 */     return (A)REGISTERED_ANIMATIONS.get(id);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static <A extends Animation<?, ?>> AnimationId<A> getRegisteredId(ResourceLocation id) {
/* 30 */     return REGISTERED_ANIMATIONS.keySet().stream().filter(anim -> anim.id.equals(id)).findFirst().orElse(null);
/*    */   }
/*    */   
/*    */   public ResourceLocation getId() {
/* 34 */     return this.id;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\animations\AnimationId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */