/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SChangeAnimationStatePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class AnimationComponent
/*     */   extends AbilityComponent<IAbility>
/*     */ {
/*     */   private Function<LivingEntity, Boolean> stopCondition;
/*     */   private AnimationId<?> animationId;
/*     */   private Animation animation;
/*     */   private long startTime;
/*     */   private long animTick;
/*     */   private int animDuration;
/*  28 */   private State state = State.STOP;
/*     */   
/*     */   public AnimationComponent(IAbility ability) {
/*  31 */     super(ModAbilityKeys.ANIMATION, ability);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends Animation> T getAnimation() {
/*  40 */     return (T)this.animation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <A extends Animation<?, ?>> void start(LivingEntity entity, AnimationId<A> animationId) {
/*  50 */     start(entity, animationId, -1, (Function<LivingEntity, Boolean>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <A extends Animation<?, ?>> void start(LivingEntity entity, AnimationId<A> animationId, int animationDuration) {
/*  61 */     start(entity, animationId, animationDuration, (Function<LivingEntity, Boolean>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <A extends Animation<?, ?>> void start(LivingEntity entity, AnimationId<A> animationId, int animationDuration, @Nullable Function<LivingEntity, Boolean> stopCondition) {
/*  72 */     ensureIsRegistered();
/*     */     
/*  74 */     if (isPlaying()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.animationId = animationId;
/*     */     
/*  87 */     if (entity.field_70170_p.field_72995_K) {
/*  88 */       this.animation = AnimationId.getRegisteredAnimation(animationId);
/*  89 */       if (this.animation == null) {
/*  90 */         ModMain.LOGGER.warn("No animation with id " + animationId.getId().toString() + " found.");
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  95 */     this.stopCondition = stopCondition;
/*     */     
/*  97 */     if (this.stopCondition != null && ((Boolean)this.stopCondition.apply(entity)).booleanValue()) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     this.startTime = entity.field_70170_p.func_82737_E();
/* 102 */     this.animTick = 0L;
/* 103 */     this.animDuration = animationDuration;
/* 104 */     this.state = State.PLAY;
/*     */     
/* 106 */     if (!entity.field_70170_p.field_72995_K) {
/* 107 */       WyNetwork.sendToAllTrackingAndSelf(new SChangeAnimationStatePacket(entity, getAbility(), animationId, this.state, this.animDuration), (Entity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop(LivingEntity entity) {
/* 115 */     if (isStopped()) {
/*     */       return;
/*     */     }
/* 118 */     this.animTick = 0L;
/* 119 */     this.state = State.STOP;
/*     */     
/* 121 */     if (!entity.field_70170_p.field_72995_K) {
/* 122 */       WyNetwork.sendToAllTrackingAndSelf(new SChangeAnimationStatePacket(entity, getAbility(), null, this.state, 0), (Entity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void doTick(LivingEntity entity) {
/* 128 */     if (!entity.field_70170_p.field_72995_K && 
/* 129 */       isPlaying()) {
/* 130 */       if (this.stopCondition != null && ((Boolean)this.stopCondition.apply(entity)).booleanValue()) {
/* 131 */         stop(entity);
/*     */         
/*     */         return;
/*     */       } 
/* 135 */       if (this.animDuration != -1 && this.animDuration <= 0) {
/* 136 */         stop(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     if (isPlaying()) {
/* 143 */       this.animTick++;
/* 144 */       if (this.animDuration > -1) {
/* 145 */         this.animDuration--;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 153 */     CompoundNBT nbt = super.save();
/* 154 */     if (this.animationId != null) {
/* 155 */       nbt.func_74778_a("animationId", this.animationId.getId().toString());
/*     */     }
/* 157 */     nbt.func_74768_a("animDuration", this.animDuration);
/* 158 */     nbt.func_74772_a("animTick", this.animTick);
/* 159 */     nbt.func_74772_a("startTime", this.startTime);
/* 160 */     nbt.func_74768_a("state", this.state.ordinal());
/* 161 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 166 */     super.load(nbt);
/* 167 */     if (nbt.func_74764_b("animationId")) {
/* 168 */       this.animationId = AnimationId.getRegisteredId(new ResourceLocation(nbt.func_74779_i("animationId")));
/* 169 */       if (FMLEnvironment.dist.isClient()) {
/* 170 */         this.animation = AnimationId.getRegisteredAnimation(this.animationId);
/*     */       }
/*     */     } 
/* 173 */     this.animDuration = nbt.func_74762_e("animDuration");
/* 174 */     this.animTick = nbt.func_74763_f("animTick");
/* 175 */     this.startTime = nbt.func_74763_f("startTime");
/* 176 */     this.state = State.values()[nbt.func_74762_e("state")];
/*     */   }
/*     */   
/*     */   public long getAnimationTick() {
/* 180 */     return this.animTick;
/*     */   }
/*     */   
/*     */   public boolean isPlaying() {
/* 184 */     return (this.state == State.PLAY);
/*     */   }
/*     */   
/*     */   public boolean isStopped() {
/* 188 */     return (this.state == State.STOP);
/*     */   }
/*     */   
/*     */   public boolean isPaused() {
/* 192 */     return (this.state == State.PAUSE);
/*     */   }
/*     */   
/*     */   public enum State {
/* 196 */     PLAY,
/* 197 */     STOP,
/* 198 */     PAUSE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\AnimationComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */