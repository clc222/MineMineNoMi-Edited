/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import xyz.pixelatedw.mineminenomi.world.features.configs.SizedBlockStateFeatureConfig;
/*    */ 
/*    */ public class BoulderFeature extends Feature<SizedBlockStateFeatureConfig> {
/*    */   public BoulderFeature() {
/* 13 */     super(SizedBlockStateFeatureConfig.CODEC);
/*    */   }
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random random, BlockPos pos, SizedBlockStateFeatureConfig config) {
/*    */     // Byte code:
/*    */     //   0: aload #4
/*    */     //   2: invokevirtual func_177956_o : ()I
/*    */     //   5: iconst_3
/*    */     //   6: if_icmple -> 61
/*    */     //   9: aload_1
/*    */     //   10: aload #4
/*    */     //   12: invokevirtual func_177977_b : ()Lnet/minecraft/util/math/BlockPos;
/*    */     //   15: invokeinterface func_175623_d : (Lnet/minecraft/util/math/BlockPos;)Z
/*    */     //   20: ifeq -> 26
/*    */     //   23: goto -> 258
/*    */     //   26: aload_1
/*    */     //   27: aload #4
/*    */     //   29: invokevirtual func_177977_b : ()Lnet/minecraft/util/math/BlockPos;
/*    */     //   32: invokeinterface func_180495_p : (Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;
/*    */     //   37: invokevirtual func_177230_c : ()Lnet/minecraft/block/Block;
/*    */     //   40: astore #6
/*    */     //   42: aload #6
/*    */     //   44: invokestatic func_227250_b_ : (Lnet/minecraft/block/Block;)Z
/*    */     //   47: ifne -> 61
/*    */     //   50: aload #6
/*    */     //   52: invokestatic func_227249_a_ : (Lnet/minecraft/block/Block;)Z
/*    */     //   55: ifne -> 61
/*    */     //   58: goto -> 258
/*    */     //   61: aload #4
/*    */     //   63: invokevirtual func_177956_o : ()I
/*    */     //   66: iconst_3
/*    */     //   67: if_icmpgt -> 72
/*    */     //   70: iconst_0
/*    */     //   71: ireturn
/*    */     //   72: iconst_0
/*    */     //   73: istore #6
/*    */     //   75: iload #6
/*    */     //   77: iconst_3
/*    */     //   78: if_icmpge -> 256
/*    */     //   81: aload_3
/*    */     //   82: aload #5
/*    */     //   84: getfield size : I
/*    */     //   87: invokevirtual nextInt : (I)I
/*    */     //   90: istore #7
/*    */     //   92: aload_3
/*    */     //   93: aload #5
/*    */     //   95: getfield size : I
/*    */     //   98: invokevirtual nextInt : (I)I
/*    */     //   101: istore #8
/*    */     //   103: aload_3
/*    */     //   104: aload #5
/*    */     //   106: getfield size : I
/*    */     //   109: invokevirtual nextInt : (I)I
/*    */     //   112: istore #9
/*    */     //   114: iload #7
/*    */     //   116: iload #8
/*    */     //   118: iadd
/*    */     //   119: iload #9
/*    */     //   121: iadd
/*    */     //   122: i2f
/*    */     //   123: ldc 0.333
/*    */     //   125: fmul
/*    */     //   126: ldc 0.5
/*    */     //   128: fadd
/*    */     //   129: fstore #10
/*    */     //   131: aload #4
/*    */     //   133: iload #7
/*    */     //   135: ineg
/*    */     //   136: iload #8
/*    */     //   138: ineg
/*    */     //   139: iload #9
/*    */     //   141: ineg
/*    */     //   142: invokevirtual func_177982_a : (III)Lnet/minecraft/util/math/BlockPos;
/*    */     //   145: aload #4
/*    */     //   147: iload #7
/*    */     //   149: iload #8
/*    */     //   151: iload #9
/*    */     //   153: invokevirtual func_177982_a : (III)Lnet/minecraft/util/math/BlockPos;
/*    */     //   156: invokestatic func_218278_a : (Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Ljava/lang/Iterable;
/*    */     //   159: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   164: astore #11
/*    */     //   166: aload #11
/*    */     //   168: invokeinterface hasNext : ()Z
/*    */     //   173: ifeq -> 223
/*    */     //   176: aload #11
/*    */     //   178: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   183: checkcast net/minecraft/util/math/BlockPos
/*    */     //   186: astore #12
/*    */     //   188: aload #12
/*    */     //   190: aload #4
/*    */     //   192: invokevirtual func_177951_i : (Lnet/minecraft/util/math/vector/Vector3i;)D
/*    */     //   195: fload #10
/*    */     //   197: fload #10
/*    */     //   199: fmul
/*    */     //   200: f2d
/*    */     //   201: dcmpg
/*    */     //   202: ifgt -> 220
/*    */     //   205: aload_1
/*    */     //   206: aload #12
/*    */     //   208: aload #5
/*    */     //   210: getfield state : Lnet/minecraft/block/BlockState;
/*    */     //   213: iconst_4
/*    */     //   214: invokeinterface func_180501_a : (Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z
/*    */     //   219: pop
/*    */     //   220: goto -> 166
/*    */     //   223: aload #4
/*    */     //   225: iconst_m1
/*    */     //   226: aload_3
/*    */     //   227: iconst_2
/*    */     //   228: invokevirtual nextInt : (I)I
/*    */     //   231: iadd
/*    */     //   232: aload_3
/*    */     //   233: iconst_2
/*    */     //   234: invokevirtual nextInt : (I)I
/*    */     //   237: ineg
/*    */     //   238: iconst_m1
/*    */     //   239: aload_3
/*    */     //   240: iconst_2
/*    */     //   241: invokevirtual nextInt : (I)I
/*    */     //   244: iadd
/*    */     //   245: invokevirtual func_177982_a : (III)Lnet/minecraft/util/math/BlockPos;
/*    */     //   248: astore #4
/*    */     //   250: iinc #6, 1
/*    */     //   253: goto -> 75
/*    */     //   256: iconst_1
/*    */     //   257: ireturn
/*    */     //   258: aload #4
/*    */     //   260: invokevirtual func_177977_b : ()Lnet/minecraft/util/math/BlockPos;
/*    */     //   263: astore #4
/*    */     //   265: goto -> 0
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #22	-> 0
/*    */     //   #23	-> 9
/*    */     //   #24	-> 23
/*    */     //   #27	-> 26
/*    */     //   #28	-> 42
/*    */     //   #29	-> 58
/*    */     //   #33	-> 61
/*    */     //   #34	-> 70
/*    */     //   #37	-> 72
/*    */     //   #38	-> 81
/*    */     //   #39	-> 92
/*    */     //   #40	-> 103
/*    */     //   #41	-> 114
/*    */     //   #43	-> 131
/*    */     //   #44	-> 188
/*    */     //   #45	-> 205
/*    */     //   #47	-> 220
/*    */     //   #49	-> 223
/*    */     //   #37	-> 250
/*    */     //   #52	-> 256
/*    */     //   #55	-> 258
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   42	19	6	block	Lnet/minecraft/block/Block;
/*    */     //   188	32	12	blockpos	Lnet/minecraft/util/math/BlockPos;
/*    */     //   92	158	7	i	I
/*    */     //   103	147	8	j	I
/*    */     //   114	136	9	k	I
/*    */     //   131	119	10	f	F
/*    */     //   75	181	6	l	I
/*    */     //   0	268	0	this	Lxyz/pixelatedw/mineminenomi/world/features/BoulderFeature;
/*    */     //   0	268	1	world	Lnet/minecraft/world/ISeedReader;
/*    */     //   0	268	2	chunkGen	Lnet/minecraft/world/gen/ChunkGenerator;
/*    */     //   0	268	3	random	Ljava/util/Random;
/*    */     //   0	268	4	pos	Lnet/minecraft/util/math/BlockPos;
/*    */     //   0	268	5	config	Lxyz/pixelatedw/mineminenomi/world/features/configs/SizedBlockStateFeatureConfig;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\BoulderFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */