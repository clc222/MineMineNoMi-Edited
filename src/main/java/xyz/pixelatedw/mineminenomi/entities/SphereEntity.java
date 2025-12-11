/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.ArenaSkybox;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SphereEntity
/*     */   extends Entity
/*     */   implements IEntityAdditionalSpawnData {
/*  29 */   private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("mineminenomi", "textures/skyboxes/default.png");
/*     */   
/*  31 */   private static final DataParameter<Float> RADIUS = EntityDataManager.func_187226_a(SphereEntity.class, DataSerializers.field_187193_c);
/*     */   
/*     */   @Nullable
/*     */   private LivingEntity spawner;
/*     */   
/*     */   private Vector3d oldPos;
/*     */   
/*  38 */   private ResourceLocation[] textures = new ResourceLocation[] { DEFAULT_TEXTURE };
/*     */   
/*  40 */   private Color color = Color.WHITE;
/*     */   
/*  42 */   private int animationSpeed = 1;
/*  43 */   private int detailLevel = 8;
/*     */   
/*     */   private boolean hasSpawner;
/*     */   
/*     */   private boolean isFullWrapping;
/*     */   
/*     */   private boolean move = false;
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private ArenaSkybox cachedSkybox;
/*  52 */   private final PriorityEventPool<ITickEvent> tickEvents = new PriorityEventPool();
/*     */   
/*     */   public SphereEntity(EntityType<? extends Entity> type, World world) {
/*  55 */     super(type, world);
/*     */   }
/*     */   
/*     */   public SphereEntity(World world) {
/*  59 */     this((EntityType<? extends Entity>)ModEntities.SPHERE.get(), world);
/*     */   }
/*     */   
/*     */   public SphereEntity(World world, LivingEntity spawner) {
/*  63 */     this((EntityType<? extends Entity>)ModEntities.SPHERE.get(), world);
/*  64 */     this.spawner = spawner;
/*  65 */     this.hasSpawner = true;
/*  66 */     func_70107_b(spawner.func_226277_ct_(), spawner.func_226278_cu_(), spawner.func_226281_cx_());
/*     */   }
/*     */   
/*     */   public void setTexture(boolean isFullWrapping, ResourceLocation... textures) {
/*  70 */     this.textures = textures;
/*  71 */     this.isFullWrapping = isFullWrapping;
/*     */   }
/*     */   
/*     */   public ResourceLocation[] getTexture() {
/*  75 */     return this.textures;
/*     */   }
/*     */   
/*     */   public boolean isFullWrapping() {
/*  79 */     return this.isFullWrapping;
/*     */   }
/*     */   
/*     */   public void setAnimationSpeed(int animationSpeed) {
/*  83 */     this.animationSpeed = animationSpeed;
/*     */   }
/*     */   
/*     */   public int getAnimationSpeed() {
/*  87 */     return this.animationSpeed;
/*     */   }
/*     */   
/*     */   public void setColor(Color color) {
/*  91 */     this.color = color;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/*  95 */     return this.color;
/*     */   }
/*     */   
/*     */   public void setRadius(float radius) {
/*  99 */     func_184212_Q().func_187227_b(RADIUS, Float.valueOf(radius));
/*     */   }
/*     */   
/*     */   public float getRadius() {
/* 103 */     return ((Float)func_184212_Q().func_187225_a(RADIUS)).floatValue();
/*     */   }
/*     */   
/*     */   public void setDetailLevel(int detail) {
/* 107 */     this.detailLevel = detail;
/*     */   }
/*     */   
/*     */   public int getDetailLevel() {
/* 111 */     return this.detailLevel;
/*     */   }
/*     */   
/*     */   public void addTickEvent(ITickEvent event) {
/* 115 */     this.tickEvents.addEvent(event);
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ArenaSkybox getSkybox() {
/* 120 */     if (this.cachedSkybox == null) {
/* 121 */       this.cachedSkybox = new ArenaSkybox();
/* 122 */       this.cachedSkybox.setTexture(this.isFullWrapping, this.textures);
/* 123 */       this.cachedSkybox.setAnimationSpeed(this.animationSpeed);
/* 124 */       this.cachedSkybox.setColor(this.color);
/* 125 */       this.cachedSkybox.setDetailLevel(this.detailLevel);
/*     */     } 
/*     */     
/* 128 */     this.cachedSkybox.setRadius(getRadius());
/*     */     
/* 130 */     return this.cachedSkybox;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 135 */     func_184212_Q().func_187214_a(RADIUS, Float.valueOf(1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 140 */     super.func_70071_h_();
/*     */     
/* 142 */     if (this.oldPos == null) {
/* 143 */       this.oldPos = func_213303_ch();
/*     */     }
/*     */     
/* 146 */     if (!this.move) {
/* 147 */       if (!func_213303_ch().equals(this.oldPos)) {
/* 148 */         func_70107_b(this.oldPos.field_72450_a, this.oldPos.field_72448_b, this.oldPos.field_72449_c);
/*     */       }
/*     */     } else {
/* 151 */       this.oldPos = func_213303_ch();
/* 152 */       this.move = false;
/*     */     } 
/*     */     
/* 155 */     if (this.field_70173_aa % 20 == 0) {
/* 156 */       if (this.hasSpawner && (this.spawner == null || !this.spawner.func_70089_S())) {
/* 157 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/* 161 */       this.tickEvents.dispatch(event -> event.tick(this));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_145770_h(double pX, double pY, double pZ) {
/* 168 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT tag) {
/* 173 */     tag.func_74757_a("hasSpawner", this.hasSpawner);
/* 174 */     tag.func_74768_a("textures", this.textures.length);
/* 175 */     for (int i = 0; i < this.textures.length; i++) {
/* 176 */       tag.func_74778_a("texture" + i, this.textures[i].toString());
/*     */     }
/* 178 */     tag.func_74757_a("isFullWrapping", this.isFullWrapping);
/* 179 */     tag.func_74768_a("animationSpeed", this.animationSpeed);
/* 180 */     tag.func_74768_a("rgb", this.color.getRGB());
/* 181 */     tag.func_74768_a("alpha", this.color.getAlpha());
/* 182 */     tag.func_74776_a("radius", ((Float)func_184212_Q().func_187225_a(RADIUS)).floatValue());
/* 183 */     tag.func_74768_a("detailLevel", this.detailLevel);
/* 184 */     tag.func_74757_a("move", this.move);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT tag) {
/* 189 */     this.hasSpawner = tag.func_74767_n("hasSpawner");
/* 190 */     int texs = tag.func_74762_e("textures");
/* 191 */     this.textures = new ResourceLocation[texs];
/* 192 */     for (int i = 0; i < texs; i++) {
/* 193 */       ResourceLocation tex = ResourceLocation.func_208304_a(tag.func_74779_i("texture" + i));
/* 194 */       this.textures[i] = (tex != null) ? tex : DEFAULT_TEXTURE;
/*     */     } 
/* 196 */     this.isFullWrapping = tag.func_74767_n("isFullWrapping");
/* 197 */     this.animationSpeed = tag.func_74762_e("animationSpeed");
/* 198 */     this.color = WyHelper.intToRGB(tag.func_74762_e("rgb"), tag.func_74762_e("alpha"));
/* 199 */     func_184212_Q().func_187227_b(RADIUS, Float.valueOf(tag.func_74760_g("radius")));
/* 200 */     this.detailLevel = tag.func_74762_e("detailLevel");
/* 201 */     this.move = tag.func_74767_n("move");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 206 */     buffer.writeInt(this.textures.length);
/* 207 */     for (int i = 0; i < this.textures.length; i++) {
/* 208 */       buffer.func_192572_a(this.textures[i]);
/*     */     }
/* 210 */     buffer.writeBoolean(this.isFullWrapping);
/* 211 */     buffer.writeInt(this.animationSpeed);
/* 212 */     buffer.writeInt(this.color.getRGB());
/* 213 */     buffer.writeInt(this.color.getAlpha());
/* 214 */     buffer.writeInt(this.detailLevel);
/* 215 */     buffer.writeBoolean(this.move);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 220 */     int textures = buffer.readInt();
/* 221 */     this.textures = new ResourceLocation[textures];
/* 222 */     for (int i = 0; i < textures; i++) {
/* 223 */       this.textures[i] = buffer.func_192575_l();
/*     */     }
/* 225 */     this.isFullWrapping = buffer.readBoolean();
/* 226 */     this.animationSpeed = buffer.readInt();
/* 227 */     int rgb = buffer.readInt();
/* 228 */     int alpha = buffer.readInt();
/* 229 */     this.color = WyHelper.intToRGB(rgb, alpha);
/* 230 */     this.detailLevel = buffer.readInt();
/* 231 */     this.move = buffer.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 236 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ITickEvent {
/*     */     void tick(SphereEntity param1SphereEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\SphereEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */