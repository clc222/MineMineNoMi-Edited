/*     */ package xyz.pixelatedw.mineminenomi.particles.effects.haki;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HaoshokuHakiParticleEffect
/*     */   extends ParticleEffect<HaoshokuHakiParticleEffect.Details> {
/*     */   public HaoshokuHakiParticleEffect() {
/*  24 */     super(Details::new);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/*  30 */     int size = details.size;
/*  31 */     int color = details.color;
/*  32 */     if (color == 0)
/*  33 */       color = 16711680; 
/*  34 */     BlockPos ogPos = new BlockPos(posX, posY, posZ); double x;
/*  35 */     for (x = -size; x < size; x++) {
/*     */       double z;
/*  37 */       for (z = -size; z < size; z++) {
/*     */         
/*  39 */         BlockPos pos = new BlockPos(posX + x, posY, posZ + z);
/*     */         
/*  41 */         if (ogPos.func_218141_a((Vector3i)pos, size))
/*     */         {
/*     */           
/*  44 */           if (ogPos.hashCode() % 20 >= 5) {
/*     */ 
/*     */             
/*  47 */             BlockState blockState = world.func_180495_p(pos.func_177977_b());
/*     */             
/*  49 */             if (blockState.func_185904_a() != Material.field_151579_a)
/*     */             {
/*     */               
/*  52 */               for (int i = 0; i < 10; i++)
/*     */               {
/*  54 */                 world.func_195590_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState), true, posX + 
/*  55 */                     WyHelper.randomWithRange(-3, 3) + x, posY, posZ + 
/*     */                     
/*  57 */                     WyHelper.randomWithRange(-3, 3) + z, 0.0D, 0.0D, 0.0D); } 
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*  63 */     Vector3d playerPos = new Vector3d(posX, posY, posZ);
/*     */     
/*  65 */     double r = 2.0D; double phi;
/*  66 */     for (phi = 0.0D; phi <= 6.283185307179586D; phi += 0.04908738521234052D) {
/*     */       
/*  68 */       double d1 = r * Math.cos(phi) + WyHelper.randomDouble() / 5.0D;
/*  69 */       double z = r * Math.sin(phi) + WyHelper.randomDouble() / 5.0D;
/*     */       
/*  71 */       Vector3d pos = playerPos.func_178787_e(new Vector3d(d1, posY, z));
/*  72 */       Vector3d dirVec = playerPos.func_178788_d(pos).func_72432_b().func_216372_d(3.5D, 1.0D, 3.5D);
/*     */       
/*  74 */       Color clientRGB = new Color(color);
/*     */       
/*  76 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/*  77 */       data.setLife(15);
/*  78 */       data.setSize(3.0F);
/*  79 */       data.setMotion(-dirVec.field_72450_a, 0.0D, -dirVec.field_72449_c);
/*  80 */       data.setColor(clientRGB.getRed() / 255.0F, clientRGB.getGreen() / 255.0F, clientRGB.getBlue() / 255.0F, 0.4F);
/*  81 */       world.func_195590_a((IParticleData)data, true, posX + d1, posY + 0.3D, posZ + z, 0.0D, 0.0D, 0.0D);
/*     */       
/*  83 */       data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/*  84 */       data.setLife(15);
/*  85 */       data.setSize(2.0F);
/*  86 */       data.setMotion(-dirVec.field_72450_a, 0.0D, -dirVec.field_72449_c);
/*  87 */       data.setColor(0.0F, 0.0F, 0.0F, 0.6F);
/*  88 */       world.func_195590_a((IParticleData)data, true, posX + d1, posY + 0.3D, posZ + z, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Details
/*     */     extends ParticleEffect.Details
/*     */   {
/*     */     private int color;
/*     */     private int size;
/*     */     
/*     */     public void save(CompoundNBT nbt) {
/* 100 */       nbt.func_74768_a("color", this.color);
/* 101 */       nbt.func_74768_a("size", this.size);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void load(CompoundNBT nbt) {
/* 107 */       this.color = nbt.func_74762_e("color");
/* 108 */       this.size = nbt.func_74762_e("size");
/*     */     }
/*     */ 
/*     */     
/*     */     public void setColor(int color) {
/* 113 */       this.color = color;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getColor() {
/* 118 */       return this.color;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSize(int size) {
/* 123 */       this.size = size;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSize() {
/* 128 */       return this.size;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\haki\HaoshokuHakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */