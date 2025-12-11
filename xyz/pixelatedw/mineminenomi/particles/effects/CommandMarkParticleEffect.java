/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CommandMarkParticleEffect extends ParticleEffect<CommandMarkParticleEffect.Details> {
/*    */   public CommandMarkParticleEffect() {
/* 15 */     super(Details::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 20 */     Color color = details.getColor();
/* 21 */     SimpleParticleData particle = new SimpleParticleData((ParticleType)ModParticleTypes.COMMAND_MARK.get());
/* 22 */     particle.setLife(5);
/* 23 */     particle.setSize(details.isMainMark() ? 5.0F : 4.0F);
/* 24 */     particle.setColor(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/* 25 */     particle.setFunction(EasingFunction.BOUNCE_IN);
/* 26 */     particle.setMotion(0.0D, 0.01D, 0.0D);
/* 27 */     world.func_195590_a((IParticleData)particle, true, posX, posY + 0.75D, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   public static class Details
/*    */     extends ParticleEffect.Details {
/*    */     private boolean isMain;
/*    */     private int rgb;
/*    */     
/*    */     public Details() {}
/*    */     
/*    */     public Details(int rgb, boolean isMain) {
/* 38 */       this.rgb = rgb;
/* 39 */       this.isMain = true;
/*    */     }
/*    */     
/*    */     public Details(String hex, boolean isMain) {
/* 43 */       this(WyHelper.hexToRGB(hex).getRGB(), isMain);
/*    */     }
/*    */ 
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 48 */       nbt.func_74768_a("rgb", this.rgb);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 53 */       this.rgb = nbt.func_74762_e("rgb");
/*    */     }
/*    */     
/*    */     public int getRGB() {
/* 57 */       return this.rgb;
/*    */     }
/*    */     
/*    */     public Color getColor() {
/* 61 */       return new Color(this.rgb);
/*    */     }
/*    */     
/*    */     public void setRGB(int rgb) {
/* 65 */       this.rgb = rgb;
/*    */     }
/*    */     
/*    */     public boolean isMainMark() {
/* 69 */       return this.isMain;
/*    */     }
/*    */     
/*    */     public void setMainMark() {
/* 73 */       this.isMain = true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\CommandMarkParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */