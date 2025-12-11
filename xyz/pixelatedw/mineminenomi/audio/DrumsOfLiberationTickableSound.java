/*    */ package xyz.pixelatedw.mineminenomi.audio;
/*    */ 
/*    */ import net.minecraft.client.audio.TickableSound;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ 
/*    */ 
/*    */ public class DrumsOfLiberationTickableSound
/*    */   extends TickableSound
/*    */ {
/*    */   private static final int INTRO_DURATION = 72;
/*    */   private static final int FADE_IN_DURATION = 60;
/*    */   private final int stage;
/*    */   private final Entity source;
/* 17 */   private float playTime = 0.0F;
/*    */   
/*    */   public DrumsOfLiberationTickableSound(SoundEvent sound, int stage, Entity source) {
/* 20 */     super(sound, SoundCategory.PLAYERS);
/* 21 */     this.stage = stage;
/* 22 */     this.source = source;
/* 23 */     this.field_147659_g = (this.stage == 1);
/* 24 */     this.field_147665_h = 0;
/* 25 */     this.field_147662_b = 0.0F;
/* 26 */     this.field_217862_m = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_230510_t_() {
/* 31 */     return (this.source != null && this.source.func_70089_S());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_211503_n() {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 41 */     if (this.source.func_70089_S()) {
/* 42 */       if (this.stage == 0) {
/* 43 */         if (this.playTime > 72.0F) {
/* 44 */           func_239509_o_();
/*    */         } else {
/*    */           
/* 47 */           this.field_147662_b = MathHelper.func_76131_a(this.playTime / 120.0F, 0.0F, 0.5F);
/*    */         }
/*    */       
/* 50 */       } else if (this.stage == 1) {
/* 51 */         if (this.playTime > 1200.0F) {
/* 52 */           func_239509_o_();
/*    */         }
/* 54 */         else if (this.playTime > 60.0F) {
/* 55 */           this.field_147662_b = 0.5F;
/*    */         } 
/*    */       } 
/*    */       
/* 59 */       this.playTime++;
/*    */     } else {
/*    */       
/* 62 */       func_239509_o_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\audio\DrumsOfLiberationTickableSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */