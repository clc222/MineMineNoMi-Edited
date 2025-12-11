/*     */ package xyz.pixelatedw.mineminenomi.data.entity.animation;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*     */ 
/*     */ 
/*     */ public class AnimationDataBase
/*     */   implements IAnimationData
/*     */ {
/*     */   private LivingEntity owner;
/*     */   private Animation<?, ?> currentAnimation;
/*  17 */   private LinkedList<Animation<?, ?>> animations = new LinkedList<>();
/*     */ 
/*     */   
/*     */   public IAnimationData setOwner(LivingEntity entity) {
/*  21 */     this.owner = entity;
/*  22 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startAnimation(AnimationId<?> animationId, int duration, boolean force) {
/*  27 */     if (this.currentAnimation != null && !force) {
/*     */       return;
/*     */     }
/*     */     
/*  31 */     if (this.currentAnimation != null) {
/*  32 */       boolean alreadyHasAnim = this.animations.stream().anyMatch(a -> a.getId().equals(animationId));
/*  33 */       if (!alreadyHasAnim) {
/*  34 */         this.animations.push(this.currentAnimation);
/*     */       }
/*     */     } 
/*     */     
/*  38 */     this.currentAnimation = AnimationId.getRegisteredAnimation(animationId);
/*  39 */     if (this.currentAnimation != null) {
/*  40 */       this.currentAnimation.start(this.owner, duration);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopAnimation(AnimationId<?> animationId) {
/*  46 */     if (this.currentAnimation != null && this.currentAnimation.getId().equals(animationId)) {
/*  47 */       this.currentAnimation.stop(this.owner);
/*  48 */       this.currentAnimation = null;
/*     */     } else {
/*     */       
/*  51 */       Optional<Animation<?, ?>> opt = this.animations.stream().filter(a -> a.getId().equals(animationId)).findFirst();
/*  52 */       if (opt.isPresent()) {
/*  53 */         ((Animation)opt.get()).stop(this.owner);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAnimationPlaying(AnimationId<?> animationId) {
/*  60 */     if (this.currentAnimation != null && this.currentAnimation.getId().equals(animationId) && this.currentAnimation.isPlaying()) {
/*  61 */       return true;
/*     */     }
/*     */     
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Animation<?, ?> getAnimation() {
/*  70 */     if (this.currentAnimation != null && this.currentAnimation.isStopped()) {
/*  71 */       return null;
/*     */     }
/*  73 */     return this.currentAnimation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAnimations() {
/*  78 */     if (this.owner == null) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     if (this.currentAnimation != null) {
/*  83 */       this.currentAnimation.tick(this.owner);
/*  84 */       if (this.currentAnimation.isStopped()) {
/*  85 */         this.currentAnimation = null;
/*     */       }
/*     */     } 
/*     */     
/*  89 */     Iterator<Animation<?, ?>> iter = this.animations.iterator();
/*  90 */     while (iter.hasNext()) {
/*  91 */       Animation<?, ?> anim = iter.next();
/*     */       
/*  93 */       anim.tick(this.owner);
/*  94 */       if (anim.isStopped()) {
/*  95 */         iter.remove();
/*     */       }
/*     */     } 
/*     */     
/*  99 */     if (this.animations.size() > 0 && (
/* 100 */       this.currentAnimation == null || this.currentAnimation.isStopped())) {
/* 101 */       Animation<?, ?> last = this.animations.pollFirst();
/* 102 */       if (last != null) {
/* 103 */         this.currentAnimation = last;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LinkedList<Animation<?, ?>> getAnimations() {
/* 111 */     return new LinkedList<>(this.animations);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\animation\AnimationDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */