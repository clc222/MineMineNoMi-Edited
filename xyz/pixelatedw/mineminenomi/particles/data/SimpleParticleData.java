/*     */ package xyz.pixelatedw.mineminenomi.particles.data;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.serialization.Codec;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingDirection;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunction;
/*     */ 
/*     */ public class SimpleParticleData
/*     */   extends ParticleType<SimpleParticleData>
/*     */   implements IParticleData {
/*  16 */   public static final IParticleData.IDeserializer<SimpleParticleData> DESERIALIZER = new IParticleData.IDeserializer<SimpleParticleData>()
/*     */     {
/*     */       public SimpleParticleData fromCommand(ParticleType<SimpleParticleData> particleType, StringReader stringReader) throws CommandSyntaxException {
/*  19 */         SimpleParticleData part = (SimpleParticleData)particleType;
/*  20 */         part.setSize(2.0F);
/*  21 */         return part;
/*     */       }
/*     */ 
/*     */       
/*     */       public SimpleParticleData fromNetwork(ParticleType<SimpleParticleData> particleType, PacketBuffer packetBuffer) {
/*  26 */         float red = packetBuffer.readFloat();
/*  27 */         float green = packetBuffer.readFloat();
/*  28 */         float blue = packetBuffer.readFloat();
/*  29 */         float alpha = packetBuffer.readFloat();
/*  30 */         float size = packetBuffer.readFloat();
/*  31 */         float rot = packetBuffer.readFloat();
/*  32 */         int life = packetBuffer.readInt();
/*  33 */         float yaw = packetBuffer.readFloat();
/*  34 */         float pitch = packetBuffer.readFloat();
/*     */         
/*  36 */         boolean hasMotionDecay = packetBuffer.readBoolean();
/*  37 */         boolean hasScaleDecay = packetBuffer.readBoolean();
/*  38 */         boolean faceCamera = packetBuffer.readBoolean();
/*     */         
/*  40 */         double motionX = packetBuffer.readDouble();
/*  41 */         double motionY = packetBuffer.readDouble();
/*  42 */         double motionZ = packetBuffer.readDouble();
/*     */         
/*  44 */         float rotX = packetBuffer.readFloat();
/*  45 */         float rotY = packetBuffer.readFloat();
/*  46 */         float rotZ = packetBuffer.readFloat();
/*     */         
/*  48 */         int animSpeed = packetBuffer.readInt();
/*  49 */         int easeFunc = packetBuffer.readInt();
/*  50 */         int easeDir = packetBuffer.readInt();
/*  51 */         float easeStr = packetBuffer.readFloat();
/*     */         
/*  53 */         SimpleParticleData data = new SimpleParticleData(particleType);
/*  54 */         data.setColor(red, green, blue, alpha);
/*  55 */         data.setMotion(motionX, motionY, motionZ);
/*  56 */         data.setRotation(rotX, rotY, rotZ);
/*  57 */         data.setSize(size);
/*  58 */         data.setRotationSpeed(rot);
/*  59 */         data.setLife(life);
/*  60 */         data.setLookVec(yaw, pitch);
/*     */         
/*  62 */         data.setHasMotionDecay(hasMotionDecay);
/*  63 */         data.setHasScaleDecay(hasScaleDecay);
/*     */         
/*  65 */         data.setAnimationSpeed(animSpeed);
/*  66 */         data.setFunction(easeFunc);
/*  67 */         data.setEaseDirection(easeDir);
/*  68 */         data.setEaseStrength(easeStr);
/*     */         
/*  70 */         return data;
/*     */       }
/*     */     };
/*     */   
/*  74 */   private final Codec<SimpleParticleData> codec = Codec.unit(this);
/*     */   
/*  76 */   private float red = 1.0F; private float green = 1.0F; private float blue = 1.0F; private double motionX;
/*     */   private double motionY;
/*     */   private double motionZ;
/*  79 */   private float alpha = 1.0F; private float rotX; private float rotY; private float rotZ;
/*  80 */   private float size = 1.0F;
/*  81 */   private float rotSpeed = 0.0F;
/*  82 */   private int life = 10;
/*     */   
/*     */   private boolean hasMotionDecay = true;
/*     */   private boolean hasScaleDecay = true;
/*     */   private boolean faceCamera = false;
/*     */   private float yaw;
/*     */   private float pitch;
/*  89 */   private int animSpeed = 2;
/*  90 */   private int function = -1;
/*     */   private int easeDirection;
/*  92 */   private float easeStrength = 1.0F;
/*     */   
/*     */   protected ParticleType type;
/*     */   
/*     */   public SimpleParticleData() {
/*  97 */     super(true, DESERIALIZER);
/*  98 */     this.type = this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData(ParticleType type) {
/* 102 */     super(true, DESERIALIZER);
/* 103 */     this.type = type;
/*     */   }
/*     */   
/*     */   public SimpleParticleData(ParticleType type, IParticleData.IDeserializer des) {
/* 107 */     super(true, des);
/* 108 */     this.type = type;
/*     */   }
/*     */   
/*     */   public SimpleParticleData(boolean limiter, IParticleData.IDeserializer des) {
/* 112 */     super(limiter, des);
/* 113 */     this.type = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_197553_a(PacketBuffer buffer) {
/* 118 */     buffer.writeFloat(this.red);
/* 119 */     buffer.writeFloat(this.green);
/* 120 */     buffer.writeFloat(this.blue);
/* 121 */     buffer.writeFloat(this.alpha);
/* 122 */     buffer.writeFloat(this.size);
/* 123 */     buffer.writeFloat(this.rotSpeed);
/* 124 */     buffer.writeInt(this.life);
/* 125 */     buffer.writeFloat(this.yaw);
/* 126 */     buffer.writeFloat(this.pitch);
/*     */     
/* 128 */     buffer.writeBoolean(this.hasMotionDecay);
/* 129 */     buffer.writeBoolean(this.hasScaleDecay);
/* 130 */     buffer.writeBoolean(this.faceCamera);
/*     */     
/* 132 */     buffer.writeDouble(this.motionX);
/* 133 */     buffer.writeDouble(this.motionY);
/* 134 */     buffer.writeDouble(this.motionZ);
/*     */     
/* 136 */     buffer.writeFloat(this.rotX);
/* 137 */     buffer.writeFloat(this.rotY);
/* 138 */     buffer.writeFloat(this.rotZ);
/*     */     
/* 140 */     buffer.writeInt(this.animSpeed);
/* 141 */     buffer.writeInt(this.function);
/* 142 */     buffer.writeInt(this.easeDirection);
/* 143 */     buffer.writeFloat(this.easeStrength);
/*     */   }
/*     */   
/*     */   public void setLookVec(float yaw, float pitch) {
/* 147 */     this.yaw = yaw;
/* 148 */     this.pitch = pitch;
/* 149 */     this.faceCamera = true;
/*     */   }
/*     */   
/*     */   public boolean faceCamera() {
/* 153 */     return this.faceCamera;
/*     */   }
/*     */   
/*     */   public float getYaw() {
/* 157 */     return this.yaw;
/*     */   }
/*     */   
/*     */   public float getPitch() {
/* 161 */     return this.pitch;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setMotion(double motionX, double motionY, double motionZ) {
/* 165 */     this.motionX = motionX;
/* 166 */     this.motionY = motionY;
/* 167 */     this.motionZ = motionZ;
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setRotation(float rotX, float rotY, float rotZ) {
/* 172 */     this.rotX = rotX;
/* 173 */     this.rotY = rotY;
/* 174 */     this.rotZ = rotZ;
/* 175 */     this.rotSpeed = 0.3F;
/* 176 */     return this;
/*     */   }
/*     */   
/*     */   public void setAnimationSpeed(int speed) {
/* 180 */     this.animSpeed = speed;
/*     */   }
/*     */   
/*     */   public int getAnimationSpeed() {
/* 184 */     return this.animSpeed;
/*     */   }
/*     */   
/*     */   public void setEaseStrength(float val) {
/* 188 */     this.easeStrength = val;
/*     */   }
/*     */   
/*     */   public float getEaseStrength() {
/* 192 */     return this.easeStrength;
/*     */   }
/*     */   
/*     */   public void setEaseDirection(int id) {
/* 196 */     this.easeDirection = id;
/*     */   }
/*     */   
/*     */   public void setEaseDirection(EasingDirection dir) {
/* 200 */     this.easeDirection = dir.ordinal();
/*     */   }
/*     */   
/*     */   public EasingDirection getEaseDirecetion() {
/* 204 */     return EasingDirection.values()[this.easeDirection];
/*     */   }
/*     */   
/*     */   public void setFunction(int id) {
/* 208 */     this.function = id;
/* 209 */     setHasScaleDecay(false);
/*     */   }
/*     */   
/*     */   public void setFunction(EasingFunction func) {
/* 213 */     setFunction(func.ordinal());
/*     */   }
/*     */   
/*     */   public EasingFunction getFunction() {
/* 217 */     if (this.function < 0) {
/* 218 */       return null;
/*     */     }
/* 220 */     return EasingFunction.values()[this.function];
/*     */   }
/*     */   
/*     */   public SimpleParticleData setRotation(Vector3f vec) {
/* 224 */     return setRotation(vec.func_195899_a(), vec.func_195900_b(), vec.func_195902_c());
/*     */   }
/*     */   
/*     */   public SimpleParticleData setColor(float red, float green, float blue) {
/* 228 */     return setColor(red, green, blue, 1.0F);
/*     */   }
/*     */   
/*     */   public SimpleParticleData setColor(float red, float green, float blue, float alpha) {
/* 232 */     this.red = red;
/* 233 */     this.green = green;
/* 234 */     this.blue = blue;
/* 235 */     this.alpha = alpha;
/* 236 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setRotationSpeed(float rot) {
/* 240 */     this.rotSpeed = rot;
/* 241 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setSize(float size) {
/* 245 */     this.size = size;
/* 246 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setLife(int life) {
/* 250 */     this.life = life;
/* 251 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setHasMotionDecay(boolean flag) {
/* 255 */     this.hasMotionDecay = flag;
/* 256 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleParticleData setHasScaleDecay(boolean flag) {
/* 260 */     this.hasScaleDecay = flag;
/* 261 */     return this;
/*     */   }
/*     */   
/*     */   public float getRed() {
/* 265 */     return this.red;
/*     */   }
/*     */   
/*     */   public float getGreen() {
/* 269 */     return this.green;
/*     */   }
/*     */   
/*     */   public float getBlue() {
/* 273 */     return this.blue;
/*     */   }
/*     */   
/*     */   public float getAlpha() {
/* 277 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 281 */     return this.size;
/*     */   }
/*     */   
/*     */   public float getRotationSpeed() {
/* 285 */     return this.rotSpeed;
/*     */   }
/*     */   
/*     */   public int getLife() {
/* 289 */     return this.life;
/*     */   }
/*     */   
/*     */   public double getDeltaMovementX() {
/* 293 */     return this.motionX;
/*     */   }
/*     */   
/*     */   public double getDeltaMovementY() {
/* 297 */     return this.motionY;
/*     */   }
/*     */   
/*     */   public double getDeltaMovementZ() {
/* 301 */     return this.motionZ;
/*     */   }
/*     */   
/*     */   public float getRotX() {
/* 305 */     return this.rotX;
/*     */   }
/*     */   
/*     */   public float getRotY() {
/* 309 */     return this.rotY;
/*     */   }
/*     */   
/*     */   public float getRotZ() {
/* 313 */     return this.rotZ;
/*     */   }
/*     */   
/*     */   public boolean hasMotionDecay() {
/* 317 */     return this.hasMotionDecay;
/*     */   }
/*     */   
/*     */   public boolean hasScaleDecay() {
/* 321 */     return this.hasScaleDecay;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_197555_a() {
/* 326 */     return func_197554_b().getRegistryName().toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public ParticleType func_197554_b() {
/* 331 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public Codec<SimpleParticleData> func_230522_e_() {
/* 336 */     return this.codec;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\data\SimpleParticleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */