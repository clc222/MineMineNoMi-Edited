/*     */ package xyz.pixelatedw.mineminenomi.api.math;
/*     */ 
/*     */ public class EasingFunctionHelper
/*     */ {
/*     */   public static float easeInOutQuad(Float x) {
/*   6 */     return easeInQuad(x) * (1.0F - x.floatValue()) + easeOutQuad(x) * x.floatValue();
/*     */   }
/*     */   
/*     */   public static float easeOutQuad(Float x) {
/*  10 */     return (float)(1.0D - Math.pow((1.0F - x.floatValue()), 2.0D));
/*     */   }
/*     */   
/*     */   public static float easeInQuad(Float x) {
/*  14 */     return x.floatValue() * x.floatValue();
/*     */   }
/*     */   
/*     */   public static float easeOutCubic(Float x) {
/*  18 */     return (float)(1.0D - Math.pow((1.0F - x.floatValue()), 3.0D));
/*     */   }
/*     */   
/*     */   public static float easeInCubic(Float x) {
/*  22 */     return x.floatValue() * x.floatValue() * x.floatValue();
/*     */   }
/*     */   
/*     */   public static float easeInOutCubic(Float x) {
/*  26 */     return (float)((x.floatValue() < 0.5D) ? (4.0F * x.floatValue() * x.floatValue() * x.floatValue()) : (1.0D - Math.pow((-2.0F * x.floatValue() + 2.0F), 3.0D) / 2.0D));
/*     */   }
/*     */   
/*     */   public static float easeOutSine(Float x) {
/*  30 */     return (float)Math.sin(x.floatValue() * Math.PI / 2.0D);
/*     */   }
/*     */   
/*     */   public static float easeInSine(Float x) {
/*  34 */     return (float)(1.0D - Math.cos(x.floatValue() * Math.PI / 2.0D));
/*     */   }
/*     */   
/*     */   public static float easeInOutSine(Float x) {
/*  38 */     return (float)(-(Math.cos(Math.PI * x.floatValue()) - 1.0D) / 2.0D);
/*     */   }
/*     */   
/*     */   public static float easeInBack(Float x) {
/*  42 */     float y1 = 1.70158F;
/*  43 */     float y2 = y1 + 1.0F;
/*  44 */     return y2 * x.floatValue() * x.floatValue() * x.floatValue() - y1 * x.floatValue() * x.floatValue();
/*     */   }
/*     */   
/*     */   public static float easeOutBack(Float x) {
/*  48 */     float y1 = 1.70158F;
/*  49 */     float y2 = y1 + 1.0F;
/*  50 */     return (float)(1.0D + y2 * Math.pow((x.floatValue() - 1.0F), 3.0D) + y1 * Math.pow((x.floatValue() - 1.0F), 2.0D));
/*     */   }
/*     */   
/*     */   public static float easeInOutBack(Float x) {
/*  54 */     float y1 = 1.70158F;
/*  55 */     float y2 = y1 + 1.525F;
/*  56 */     return (float)((x.floatValue() < 0.5D) ? (Math.pow((2.0F * x.floatValue()), 2.0D) * ((y2 + 1.0F) * 2.0F * x.floatValue() - y2) / 2.0D) : ((Math.pow((2.0F * x.floatValue() - 2.0F), 2.0D) * ((y2 + 1.0F) * (x.floatValue() * 2.0F - 2.0F) + y2) + 2.0D) / 2.0D));
/*     */   }
/*     */   
/*     */   public static float easeInElastic(Float x) {
/*  60 */     float y = 2.0943952F;
/*  61 */     return (float)((x.floatValue() == 0.0F) ? 0.0D : ((x.floatValue() == 1.0F) ? 1.0D : (-Math.pow(2.0D, (10.0F * x.floatValue() - 10.0F)) * Math.sin(((x.floatValue() * 10.0F) - 10.75D) * y))));
/*     */   }
/*     */   
/*     */   public static float easeOutElastic(Float x) {
/*  65 */     float y = 2.0943952F;
/*  66 */     return (float)((x.floatValue() == 0.0F) ? 0.0D : ((x.floatValue() == 1.0F) ? 1.0D : (Math.pow(2.0D, (-10.0F * x.floatValue())) * Math.sin(((x.floatValue() * 10.0F) - 0.75D) * y) + 1.0D)));
/*     */   }
/*     */   
/*     */   public static float easeInOutElastic(Float x) {
/*  70 */     float y = 1.3962634F;
/*  71 */     return 
/*     */ 
/*     */       
/*  74 */       (float)((x.floatValue() == 0.0F) ? 0.0D : ((x.floatValue() == 1.0F) ? 1.0D : ((x.floatValue() < 0.5D) ? (-(Math.pow(2.0D, (20.0F * x.floatValue() - 10.0F)) * Math.sin(((20.0F * x.floatValue()) - 11.125D) * y)) / 2.0D) : (Math.pow(2.0D, (-20.0F * x.floatValue() + 10.0F)) * Math.sin(((20.0F * x.floatValue()) - 11.125D) * y) / 2.0D + 1.0D))));
/*     */   }
/*     */   
/*     */   public static float easeInBounce(Float x) {
/*  78 */     return easeOutBounce(Float.valueOf(1.0F - x.floatValue()));
/*     */   }
/*     */   
/*     */   public static float easeOutBounce(Float x) {
/*  82 */     float n = 7.5625F;
/*  83 */     float d = 2.75F;
/*     */     
/*  85 */     if (x.floatValue() < 1.0F / d) {
/*  86 */       return n * x.floatValue() * x.floatValue();
/*     */     }
/*  88 */     if (x.floatValue() < 2.0F / d) {
/*  89 */       return (float)((n * (x = Float.valueOf(x.floatValue() - 1.5F / d)).floatValue() * x.floatValue()) + 0.75D);
/*     */     }
/*  91 */     if (x.floatValue() < 2.5D / d) {
/*  92 */       return (float)((n * (x = Float.valueOf(x.floatValue() - 2.25F / d)).floatValue() * x.floatValue()) + 0.9375D);
/*     */     }
/*     */     
/*  95 */     return (float)((n * (x = Float.valueOf(x.floatValue() - 2.625F / d)).floatValue() * x.floatValue()) + 0.984375D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float easeInOutBounce(Float x) {
/* 100 */     return (x.floatValue() < 0.5D) ? ((1.0F - easeOutBounce(Float.valueOf(1.0F - 2.0F * x.floatValue()))) / 2.0F) : ((1.0F + easeOutBounce(Float.valueOf(2.0F * x.floatValue() - 1.0F))) / 2.0F);
/*     */   }
/*     */   
/*     */   public static float easeAbsoluteSine(Float x) {
/* 104 */     return (float)Math.abs(Math.sin(x.floatValue()));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\math\EasingFunctionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */