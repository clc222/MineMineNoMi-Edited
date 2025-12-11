/*    */ package xyz.pixelatedw.mineminenomi.api.math;
/*    */ 
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ 
/*    */ public class VectorHelper {
/*    */   public static Vector3d rotateAroundX(Vector3d vector, double angle) {
/*  8 */     double y = Math.cos(angle) * vector.field_72448_b - Math.sin(angle) * vector.field_72449_c;
/*  9 */     double z = Math.sin(angle) * vector.field_72448_b + Math.cos(angle) * vector.field_72449_c;
/* 10 */     vector = new Vector3d(vector.field_72450_a, y, z);
/* 11 */     return vector;
/*    */   }
/*    */   
/*    */   public static Vector3d rotateAroundY(Vector3d vector, double angle) {
/* 15 */     double x = Math.cos(angle) * vector.field_72450_a + Math.sin(angle) * vector.field_72449_c;
/* 16 */     double z = -Math.sin(angle) * vector.field_72450_a + Math.cos(angle) * vector.field_72449_c;
/* 17 */     vector = new Vector3d(x, vector.field_72448_b, z);
/* 18 */     return vector;
/*    */   }
/*    */   
/*    */   public static Vector3d rotateAroundZ(Vector3d vector, double angle) {
/* 22 */     double x = Math.cos(angle) * vector.field_72450_a - Math.sin(angle) * vector.field_72448_b;
/* 23 */     double y = Math.sin(angle) * vector.field_72450_a + Math.cos(angle) * vector.field_72448_b;
/* 24 */     vector = new Vector3d(x, y, vector.field_72449_c);
/* 25 */     return vector;
/*    */   }
/*    */   
/*    */   public static Vector3d calculateViewVectorFromBodyRot(float xRot, float yRot) {
/* 29 */     float f = xRot * 0.017453292F;
/* 30 */     float f1 = -yRot * 0.017453292F;
/* 31 */     float f2 = MathHelper.func_76134_b(f1);
/* 32 */     float f3 = MathHelper.func_76126_a(f1);
/* 33 */     float f4 = MathHelper.func_76134_b(f);
/* 34 */     float f5 = MathHelper.func_76126_a(f);
/* 35 */     return new Vector3d((f3 * f4), -f5, (f2 * f4));
/*    */   }
/*    */   
/*    */   public static Vector3d calculateRotationBasedOffsetPosition(Vector3d position, double angdeg, double offsetX, double offsetY, double offsetZ) {
/* 39 */     double yRotInRadians = Math.toRadians(angdeg);
/*    */     
/* 41 */     double xOffset = Math.sin(yRotInRadians) * offsetX;
/* 42 */     double zOffset = Math.cos(yRotInRadians) * offsetX;
/*    */     
/* 44 */     double xPos = position.func_82615_a() - zOffset - Math.cos(yRotInRadians - 1.5707963267948966D) * offsetZ;
/* 45 */     double yPos = position.func_82617_b() + offsetY;
/* 46 */     double zPos = position.func_82616_c() - xOffset - Math.sin(yRotInRadians - 1.5707963267948966D) * offsetZ;
/*    */     
/* 48 */     return new Vector3d(xPos, yPos, zPos);
/*    */   }
/*    */   
/*    */   public static Vector3d calculateViewVectorFromYaw(float yRot) {
/* 52 */     double f = -yRot * Math.PI / 180.0D;
/* 53 */     double f1 = Math.sin(f);
/* 54 */     double f2 = Math.cos(f);
/*    */     
/* 56 */     return new Vector3d(f1, 0.0D, f2);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\math\VectorHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */