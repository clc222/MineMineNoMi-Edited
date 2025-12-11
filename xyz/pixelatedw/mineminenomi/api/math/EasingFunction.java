/*    */ package xyz.pixelatedw.mineminenomi.api.math;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public enum EasingFunction
/*    */ {
/*  8 */   SINE_IN(EasingFunctionHelper::easeInSine),
/*  9 */   QUAD_IN(EasingFunctionHelper::easeInQuad),
/* 10 */   CUBIC_IN(EasingFunctionHelper::easeInCubic),
/* 11 */   ELASTIC_IN(EasingFunctionHelper::easeInElastic),
/* 12 */   BACK_IN(EasingFunctionHelper::easeInBack),
/* 13 */   BOUNCE_IN(EasingFunctionHelper::easeInBounce),
/*    */ 
/*    */   
/* 16 */   SINE_OUT(EasingFunctionHelper::easeOutSine),
/* 17 */   QUAD_OUT(EasingFunctionHelper::easeOutQuad),
/* 18 */   CUBIC_OUT(EasingFunctionHelper::easeOutCubic),
/* 19 */   ELASTIC_OUT(EasingFunctionHelper::easeOutElastic),
/* 20 */   BACK_OUT(EasingFunctionHelper::easeOutBack),
/* 21 */   BOUNCE_OUT(EasingFunctionHelper::easeOutBounce),
/*    */ 
/*    */   
/* 24 */   SINE_IN_OUT(EasingFunctionHelper::easeInOutSine),
/* 25 */   QUAD_IN_OUT(EasingFunctionHelper::easeInOutQuad),
/* 26 */   CUBIC_IN_OUT(EasingFunctionHelper::easeInOutCubic),
/* 27 */   ELASTIC_IN_OUT(EasingFunctionHelper::easeInOutElastic),
/* 28 */   BACK_IN_OUT(EasingFunctionHelper::easeInOutBack),
/* 29 */   BOUNCE_IN_OUT(EasingFunctionHelper::easeInOutBounce);
/*    */   
/*    */   private Function<Float, Float> func;
/*    */ 
/*    */   
/*    */   EasingFunction(Function<Float, Float> func) {
/* 35 */     this.func = func;
/*    */   }
/*    */   
/*    */   public float apply(float x) {
/* 39 */     return ((Float)this.func.apply(Float.valueOf(x))).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\math\EasingFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */