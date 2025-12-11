/*     */ package xyz.pixelatedw.mineminenomi.particles;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.particle.IParticleFactory;
/*     */ import net.minecraft.client.particle.IParticleRenderType;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.TexturedParticle;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.world.ClientWorld;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingDirection;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunction;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.rendertypes.SimpleParticleRenderType;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class SimpleParticle
/*     */   extends TexturedParticle
/*     */ {
/*     */   private boolean hasMotionDecay = true;
/*     */   private boolean hasScaleDecay = true;
/*     */   private Vector3f rotationVector;
/*     */   private float rotationSpeed;
/*     */   protected IParticleRenderType type;
/*     */   protected final float initialSize;
/*     */   private boolean faceCamera;
/*     */   private float yaw;
/*     */   private float pitch;
/*  37 */   private int animIdx = 0;
/*  38 */   private int animSpeed = 2;
/*  39 */   private int maxFrames = 8;
/*     */   
/*     */   private EasingFunction function;
/*     */   private EasingDirection easeDir;
/*  43 */   private float easeStrength = 1.0F;
/*     */ 
/*     */   
/*     */   public SimpleParticle(SimpleParticleData data, IParticleRenderType type, ClientWorld world, double x, double y, double z, double xd, double yd, double zd) {
/*  47 */     super(world, x, y, z, 0.0D, 0.0D, 0.0D);
/*  48 */     this.field_70547_e = 30 + this.field_187136_p.nextInt(10);
/*  49 */     this.field_70546_d = 0;
/*  50 */     this.field_70544_f = 0.2F;
/*  51 */     this.field_70545_g = 0.0F;
/*  52 */     func_70538_b(1.0F, 1.0F, 1.0F);
/*  53 */     this.field_190017_n = false;
/*     */     
/*  55 */     this.type = type;
/*     */     
/*  57 */     this.field_187129_i = xd;
/*  58 */     this.field_187130_j = yd;
/*  59 */     this.field_187131_k = zd;
/*     */     
/*  61 */     func_70538_b(data.getRed(), data.getGreen(), data.getBlue());
/*  62 */     setRotation(new Vector3f(data.getRotX(), data.getRotY(), data.getRotZ()));
/*  63 */     func_82338_g(data.getAlpha());
/*  64 */     setParticleSize(data.getSize() / 10.0F);
/*  65 */     setParticleRotation(data.getRotationSpeed());
/*  66 */     setParticleAge(data.getLife());
/*  67 */     setHasMotionDecay(data.hasMotionDecay());
/*  68 */     setHasScaleDecay(data.hasScaleDecay());
/*     */     
/*  70 */     this.faceCamera = data.faceCamera();
/*  71 */     this.yaw = data.getYaw();
/*  72 */     this.pitch = data.getPitch();
/*     */     
/*  74 */     this.animSpeed = data.getAnimationSpeed();
/*  75 */     this.function = data.getFunction();
/*  76 */     this.easeDir = data.getEaseDirecetion();
/*  77 */     this.easeStrength = data.getEaseStrength();
/*     */     
/*  79 */     this.initialSize = this.field_70544_f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/*  85 */     this.field_187123_c = this.field_187126_f;
/*  86 */     this.field_187124_d = this.field_187127_g;
/*  87 */     this.field_187125_e = this.field_187128_h;
/*  88 */     if (this.field_70545_g != 0.0F) {
/*  89 */       this.field_187130_j = -0.04D * this.field_70545_g;
/*     */     }
/*  91 */     if (this.field_70546_d % this.animSpeed == 0) {
/*  92 */       this.animIdx++;
/*     */     }
/*     */     
/*  95 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*  96 */     if (this.hasMotionDecay) {
/*     */       
/*  98 */       this.field_187129_i *= 0.99D;
/*  99 */       this.field_187130_j *= 0.99D;
/* 100 */       this.field_187131_k *= 0.99D;
/*     */     } 
/*     */     
/* 103 */     if (this.hasScaleDecay) {
/*     */       
/* 105 */       if (this.field_70546_d >= this.field_70547_e / 2)
/*     */       {
/* 107 */         if (this.field_70544_f > 0.0F) {
/* 108 */           this.field_70544_f /= 1.1F;
/*     */         }
/*     */       }
/* 111 */       if (this.field_70546_d + 5 >= this.field_70547_e)
/*     */       {
/* 113 */         if (this.field_82339_as > 0.0F) {
/* 114 */           this.field_82339_as = (float)(this.field_82339_as / 1.15D);
/*     */         }
/*     */       }
/*     */     } else {
/* 118 */       if (this.function != null) {
/* 119 */         float speedModifier = 0.0F;
/* 120 */         float progress = Math.min(this.field_70546_d / this.field_70547_e * (1.0F - speedModifier), 1.0F);
/* 121 */         float result = this.function.apply(progress) * this.easeStrength;
/* 122 */         float quadSize = this.initialSize;
/* 123 */         switch (this.easeDir) {
/*     */           case POSITIVE:
/* 125 */             quadSize = this.initialSize + this.initialSize * result;
/*     */             break;
/*     */           case NEGATIVE:
/* 128 */             quadSize = this.initialSize - this.initialSize * result;
/*     */             break;
/*     */           case HALF_HALF:
/* 131 */             quadSize = (progress < 0.5D) ? (this.initialSize + this.initialSize * result) : (this.initialSize - this.initialSize * result);
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 136 */         this.field_70544_f = quadSize;
/*     */       } 
/*     */       
/* 139 */       if (this.field_70546_d + 5 >= this.field_70547_e && 
/* 140 */         this.field_82339_as > 0.0F) {
/* 141 */         this.field_82339_as = (float)(this.field_82339_as / 1.15D);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 146 */     if (this.rotationSpeed != 0.0F) {
/* 147 */       this.field_190014_F -= this.rotationSpeed;
/*     */     }
/* 149 */     if (this.field_70546_d++ >= this.field_70547_e || this.field_187132_l) {
/* 150 */       func_187112_i();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225606_a_(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
/* 156 */     Vector3d Vector3d = renderInfo.func_216785_c();
/* 157 */     float f = (float)(MathHelper.func_219803_d(partialTicks, this.field_187123_c, this.field_187126_f) - Vector3d.func_82615_a());
/* 158 */     float f1 = (float)(MathHelper.func_219803_d(partialTicks, this.field_187124_d, this.field_187127_g) - Vector3d.func_82617_b());
/* 159 */     float f2 = (float)(MathHelper.func_219803_d(partialTicks, this.field_187125_e, this.field_187128_h) - Vector3d.func_82616_c());
/* 160 */     Quaternion quaternion = new Quaternion(0.0F, 0.0F, 0.0F, 1.0F);
/*     */     
/* 162 */     if (this.faceCamera) {
/* 163 */       Quaternion quatX = new Quaternion(this.yaw, 0.0F, 0.0F, true);
/* 164 */       Quaternion quatY = new Quaternion(0.0F, -this.pitch, 0.0F, true);
/* 165 */       quaternion.func_195890_a(quatY);
/* 166 */       quaternion.func_195890_a(quatX);
/*     */     
/*     */     }
/* 169 */     else if (this.field_190014_F == 0.0F) {
/* 170 */       quaternion = renderInfo.func_227995_f_();
/*     */     } else {
/*     */       
/* 173 */       quaternion = new Quaternion(renderInfo.func_227995_f_());
/* 174 */       quaternion.func_195890_a(this.rotationVector.func_229193_c_(this.field_190014_F));
/*     */     } 
/*     */ 
/*     */     
/* 178 */     Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
/* 179 */     vector3f1.func_214905_a(quaternion);
/* 180 */     Vector3f[] avector3f = { new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F) };
/* 181 */     float scale = func_217561_b(partialTicks);
/*     */     
/* 183 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 185 */       Vector3f vector3f = avector3f[i];
/* 186 */       vector3f.func_214905_a(quaternion);
/* 187 */       vector3f.func_195898_a(scale);
/* 188 */       vector3f.func_195904_b(f, f1, f2);
/*     */     } 
/*     */     
/* 191 */     float minU = func_217563_c();
/* 192 */     float maxU = func_217564_d();
/* 193 */     float minV = func_217562_e();
/* 194 */     float maxV = func_217560_f();
/* 195 */     int brightness = func_189214_a(partialTicks);
/* 196 */     buffer.func_225582_a_(avector3f[0].func_195899_a(), avector3f[0].func_195900_b(), avector3f[0].func_195902_c()).func_225583_a_(maxU, maxV).func_227885_a_(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_227886_a_(brightness).func_181675_d();
/* 197 */     buffer.func_225582_a_(avector3f[1].func_195899_a(), avector3f[1].func_195900_b(), avector3f[1].func_195902_c()).func_225583_a_(maxU, minV).func_227885_a_(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_227886_a_(brightness).func_181675_d();
/* 198 */     buffer.func_225582_a_(avector3f[2].func_195899_a(), avector3f[2].func_195900_b(), avector3f[2].func_195902_c()).func_225583_a_(minU, minV).func_227885_a_(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_227886_a_(brightness).func_181675_d();
/* 199 */     buffer.func_225582_a_(avector3f[3].func_195899_a(), avector3f[3].func_195900_b(), avector3f[3].func_195902_c()).func_225583_a_(minU, maxV).func_227885_a_(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_227886_a_(brightness).func_181675_d();
/*     */   }
/*     */   
/*     */   public void setMaxFrames(int frames) {
/* 203 */     this.maxFrames = frames;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setAlphaF(float f) {
/* 208 */     func_82338_g(f);
/* 209 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleSize(float f) {
/* 214 */     this.field_70544_f = f;
/* 215 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleRotation(float f) {
/* 220 */     this.rotationSpeed = f;
/* 221 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleGravity(float f) {
/* 226 */     this.field_70545_g = f;
/* 227 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleAge(int i) {
/* 232 */     this.field_70547_e = i;
/* 233 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setHasMotionDecay(boolean flag) {
/* 238 */     this.hasMotionDecay = flag;
/* 239 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector3f getRotationVec() {
/* 244 */     return this.rotationVector;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setRotation(Vector3f vec) {
/* 249 */     this.rotationVector = vec;
/* 250 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setHasScaleDecay(boolean flag) {
/* 255 */     this.hasScaleDecay = flag;
/* 256 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPos() {
/* 261 */     return new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IParticleRenderType func_217558_b() {
/* 267 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_217563_c() {
/* 273 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_217562_e() {
/* 279 */     return this.animIdx / this.maxFrames;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_217564_d() {
/* 285 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_217560_f() {
/* 291 */     return (this.animIdx + 1) / this.maxFrames;
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IParticleFactory<SimpleParticleData> {
/*     */     private IParticleRenderType type;
/* 297 */     private int maxFrames = 0;
/*     */ 
/*     */     
/*     */     public Factory(ResourceLocation res) {
/* 301 */       this(res, 1);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(ResourceLocation res, int maxFrames) {
/* 306 */       this.type = (IParticleRenderType)new SimpleParticleRenderType(res);
/* 307 */       this.maxFrames = maxFrames;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Particle createParticle(SimpleParticleData data, ClientWorld world, double x, double y, double z, double velX, double velY, double velZ) {
/* 313 */       SimpleParticle part = new SimpleParticle(data, this.type, world, x, y, z, data.getDeltaMovementX(), data.getDeltaMovementY(), data.getDeltaMovementZ());
/* 314 */       part.setMaxFrames(this.maxFrames);
/* 315 */       return (Particle)part;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\SimpleParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */