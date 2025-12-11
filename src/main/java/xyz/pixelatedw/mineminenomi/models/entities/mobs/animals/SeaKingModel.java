/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ public class SeaKingModel<T extends MobEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer TailFins;
/*     */   private final ModelRenderer bone;
/*     */   private final ModelRenderer bone2;
/*     */   private final ModelRenderer bone3;
/*     */   private final ModelRenderer bone4;
/*     */   private final ModelRenderer bone5;
/*     */   private final ModelRenderer bone6;
/*     */   private final ModelRenderer bone7;
/*     */   private final ModelRenderer bone8;
/*     */   private final ModelRenderer bone9;
/*     */   private final ModelRenderer bone10;
/*     */   private final ModelRenderer bone11;
/*     */   private final ModelRenderer bone12;
/*     */   private final ModelRenderer bone13;
/*     */   private final ModelRenderer bone14;
/*     */   private final ModelRenderer bone15;
/*     */   private final ModelRenderer bone16;
/*     */   private final ModelRenderer bone17;
/*     */   private final ModelRenderer bone18;
/*     */   private final ModelRenderer headbone;
/*     */   private final ModelRenderer bb_main;
/*     */   
/*     */   public SeaKingModel() {
/*  38 */     super(1.0F);
/*  39 */     this.field_78090_t = 16;
/*  40 */     this.field_78089_u = 16;
/*     */     
/*  42 */     this.TailFins = new ModelRenderer((Model)this);
/*  43 */     this.TailFins.func_78793_a(0.0F, 24.0F, 0.0F);
/*  44 */     this.TailFins.func_78784_a(0, 0).func_228303_a_(-1.1857F, -70.4619F, 944.9143F, 2.0F, 54.0F, 17.0F, 0.0F, false);
/*  45 */     this.TailFins.func_78784_a(0, 0).func_228303_a_(-1.1857F, -65.4619F, 961.9143F, 2.0F, 47.0F, 21.0F, 0.0F, false);
/*  46 */     this.TailFins.func_78784_a(0, 0).func_228303_a_(-1.1857F, -57.4619F, 981.9143F, 2.0F, 31.0F, 21.0F, 0.0F, false);
/*  47 */     this.TailFins.func_78784_a(0, 0).func_228303_a_(-1.1857F, -49.4619F, 1002.9143F, 2.0F, 19.0F, 21.0F, 0.0F, false);
/*     */     
/*  49 */     this.bone = new ModelRenderer((Model)this);
/*  50 */     this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
/*  51 */     this.bone.func_78784_a(0, 0).func_228303_a_(-10.1857F, -48.4619F, 900.9143F, 20.0F, 16.0F, 44.0F, 0.0F, false);
/*  52 */     this.bone.func_78784_a(0, 0).func_228303_a_(-1.1857F, -32.4619F, 900.9143F, 2.0F, 18.0F, 44.0F, 0.0F, false);
/*  53 */     this.bone.func_78784_a(0, 0).func_228303_a_(-1.1857F, -66.4619F, 900.9143F, 2.0F, 18.0F, 44.0F, 0.0F, false);
/*     */     
/*  55 */     this.bone2 = new ModelRenderer((Model)this);
/*  56 */     this.bone2.func_78793_a(0.0F, 24.0F, 0.0F);
/*  57 */     this.bone2.func_78784_a(0, 0).func_228303_a_(-1.1857F, -31.4619F, 856.9143F, 2.0F, 13.0F, 44.0F, 0.0F, false);
/*  58 */     this.bone2.func_78784_a(0, 0).func_228303_a_(-11.1857F, -49.4619F, 856.9143F, 22.0F, 18.0F, 44.0F, 0.0F, false);
/*  59 */     this.bone2.func_78784_a(0, 0).func_228303_a_(-1.1857F, -62.4619F, 856.9143F, 2.0F, 13.0F, 44.0F, 0.0F, false);
/*     */     
/*  61 */     this.bone3 = new ModelRenderer((Model)this);
/*  62 */     this.bone3.func_78793_a(0.0F, 24.0F, 0.0F);
/*  63 */     this.bone3.func_78784_a(0, 0).func_228303_a_(-1.1857F, -30.4619F, 812.9143F, 2.0F, 14.0F, 44.0F, 0.0F, false);
/*  64 */     this.bone3.func_78784_a(0, 0).func_228303_a_(-12.1857F, -50.4619F, 812.9143F, 24.0F, 20.0F, 44.0F, 0.0F, false);
/*  65 */     this.bone3.func_78784_a(0, 0).func_228303_a_(-1.1857F, -64.4619F, 812.9143F, 2.0F, 14.0F, 44.0F, 0.0F, false);
/*     */     
/*  67 */     this.bone4 = new ModelRenderer((Model)this);
/*  68 */     this.bone4.func_78793_a(0.0F, 24.0F, 0.0F);
/*  69 */     this.bone4.func_78784_a(0, 0).func_228303_a_(-1.1857F, -29.4619F, 773.9143F, 2.0F, 16.0F, 39.0F, 0.0F, false);
/*  70 */     this.bone4.func_78784_a(0, 0).func_228303_a_(-13.1857F, -51.4619F, 773.9143F, 26.0F, 22.0F, 39.0F, 0.0F, false);
/*  71 */     this.bone4.func_78784_a(0, 0).func_228303_a_(-1.1857F, -67.4619F, 773.9143F, 2.0F, 16.0F, 39.0F, 0.0F, false);
/*     */     
/*  73 */     this.bone5 = new ModelRenderer((Model)this);
/*  74 */     this.bone5.func_78793_a(0.0F, 24.0F, 0.0F);
/*  75 */     this.bone5.func_78784_a(0, 0).func_228303_a_(-1.1857F, -28.4619F, 726.9143F, 2.0F, 16.0F, 47.0F, 0.0F, false);
/*  76 */     this.bone5.func_78784_a(0, 0).func_228303_a_(-14.1857F, -52.4619F, 726.9143F, 28.0F, 24.0F, 47.0F, 0.0F, false);
/*  77 */     this.bone5.func_78784_a(0, 0).func_228303_a_(-1.1857F, -68.4619F, 726.9143F, 2.0F, 16.0F, 47.0F, 0.0F, false);
/*     */     
/*  79 */     this.bone6 = new ModelRenderer((Model)this);
/*  80 */     this.bone6.func_78793_a(0.0F, 24.0F, 0.0F);
/*  81 */     this.bone6.func_78784_a(0, 0).func_228303_a_(-1.1857F, -27.4619F, 671.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*  82 */     this.bone6.func_78784_a(0, 0).func_228303_a_(-15.1857F, -53.4619F, 671.9143F, 30.0F, 26.0F, 55.0F, 0.0F, false);
/*  83 */     this.bone6.func_78784_a(0, 0).func_228303_a_(-1.1857F, -69.4619F, 671.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*     */     
/*  85 */     this.bone7 = new ModelRenderer((Model)this);
/*  86 */     this.bone7.func_78793_a(0.0F, 24.0F, 0.0F);
/*  87 */     this.bone7.func_78784_a(0, 0).func_228303_a_(-1.1857F, -26.4619F, 609.9143F, 2.0F, 16.0F, 62.0F, 0.0F, false);
/*  88 */     this.bone7.func_78784_a(0, 0).func_228303_a_(-16.1857F, -54.4619F, 609.9143F, 32.0F, 28.0F, 62.0F, 0.0F, false);
/*  89 */     this.bone7.func_78784_a(0, 0).func_228303_a_(-1.1857F, -70.4619F, 609.9143F, 2.0F, 16.0F, 62.0F, 0.0F, false);
/*     */     
/*  91 */     this.bone8 = new ModelRenderer((Model)this);
/*  92 */     this.bone8.func_78793_a(0.0F, 24.0F, 0.0F);
/*  93 */     this.bone8.func_78784_a(0, 0).func_228303_a_(-1.1857F, -25.4619F, 573.9143F, 2.0F, 16.0F, 36.0F, 0.0F, false);
/*  94 */     this.bone8.func_78784_a(0, 0).func_228303_a_(-17.1857F, -55.4619F, 573.9143F, 34.0F, 30.0F, 36.0F, 0.0F, false);
/*  95 */     this.bone8.func_78784_a(0, 0).func_228303_a_(-1.1857F, -71.4619F, 573.9143F, 2.0F, 16.0F, 36.0F, 0.0F, false);
/*     */     
/*  97 */     this.bone9 = new ModelRenderer((Model)this);
/*  98 */     this.bone9.func_78793_a(0.0F, 24.0F, 0.0F);
/*  99 */     this.bone9.func_78784_a(0, 0).func_228303_a_(-1.1857F, -24.4619F, 533.9143F, 2.0F, 16.0F, 40.0F, 0.0F, false);
/* 100 */     this.bone9.func_78784_a(0, 0).func_228303_a_(-18.1857F, -56.4619F, 532.9143F, 36.0F, 32.0F, 41.0F, 0.0F, false);
/* 101 */     this.bone9.func_78784_a(0, 0).func_228303_a_(-1.1857F, -72.4619F, 533.9143F, 2.0F, 16.0F, 40.0F, 0.0F, false);
/*     */     
/* 103 */     this.bone10 = new ModelRenderer((Model)this);
/* 104 */     this.bone10.func_78793_a(0.0F, 24.0F, 0.0F);
/* 105 */     this.bone10.func_78784_a(0, 0).func_228303_a_(-1.1857F, -23.4619F, 485.9143F, 2.0F, 16.0F, 48.0F, 0.0F, false);
/* 106 */     this.bone10.func_78784_a(0, 0).func_228303_a_(-19.1857F, -57.4619F, 485.9143F, 38.0F, 34.0F, 48.0F, 0.0F, false);
/* 107 */     this.bone10.func_78784_a(0, 0).func_228303_a_(-1.1857F, -73.4619F, 485.9143F, 2.0F, 16.0F, 48.0F, 0.0F, false);
/*     */     
/* 109 */     this.bone11 = new ModelRenderer((Model)this);
/* 110 */     this.bone11.func_78793_a(0.0F, 24.0F, 0.0F);
/* 111 */     this.bone11.func_78784_a(0, 0).func_228303_a_(-1.1857F, -22.4619F, 428.9143F, 2.0F, 16.0F, 57.0F, 0.0F, false);
/* 112 */     this.bone11.func_78784_a(0, 0).func_228303_a_(-20.1857F, -58.4619F, 428.9143F, 40.0F, 36.0F, 57.0F, 0.0F, false);
/* 113 */     this.bone11.func_78784_a(0, 0).func_228303_a_(-1.1857F, -74.4619F, 428.9143F, 2.0F, 16.0F, 57.0F, 0.0F, false);
/*     */     
/* 115 */     this.bone12 = new ModelRenderer((Model)this);
/* 116 */     this.bone12.func_78793_a(0.0F, 24.0F, 0.0F);
/* 117 */     this.bone12.func_78784_a(0, 0).func_228303_a_(-1.1857F, -21.4619F, 373.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/* 118 */     this.bone12.func_78784_a(0, 0).func_228303_a_(-21.1857F, -59.4619F, 373.9143F, 42.0F, 38.0F, 55.0F, 0.0F, false);
/* 119 */     this.bone12.func_78784_a(0, 0).func_228303_a_(-1.1857F, -75.4619F, 373.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*     */     
/* 121 */     this.bone13 = new ModelRenderer((Model)this);
/* 122 */     this.bone13.func_78793_a(0.0F, 24.0F, 0.0F);
/* 123 */     this.bone13.func_78784_a(0, 0).func_228303_a_(-1.1857F, -22.4619F, 318.9143F, 2.0F, 19.0F, 55.0F, 0.0F, false);
/* 124 */     this.bone13.func_78784_a(0, 0).func_228303_a_(-19.1857F, -58.4619F, 318.9143F, 39.0F, 36.0F, 55.0F, 0.0F, false);
/* 125 */     this.bone13.func_78784_a(0, 0).func_228303_a_(-1.1857F, -74.4619F, 318.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*     */     
/* 127 */     this.bone14 = new ModelRenderer((Model)this);
/* 128 */     this.bone14.func_78793_a(0.0F, 24.0F, 0.0F);
/* 129 */     this.bone14.func_78784_a(0, 0).func_228303_a_(-1.1857F, -21.4619F, 263.9143F, 2.0F, 20.0F, 55.0F, 0.0F, false);
/* 130 */     this.bone14.func_78784_a(0, 0).func_228303_a_(-21.1857F, -59.4619F, 263.9143F, 42.0F, 38.0F, 55.0F, 0.0F, false);
/* 131 */     this.bone14.func_78784_a(0, 0).func_228303_a_(-1.1857F, -75.4619F, 263.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*     */     
/* 133 */     this.bone15 = new ModelRenderer((Model)this);
/* 134 */     this.bone15.func_78793_a(0.0F, 24.0F, 0.0F);
/* 135 */     this.bone15.func_78784_a(0, 0).func_228303_a_(-1.1857F, -22.4619F, 208.9143F, 2.0F, 23.0F, 55.0F, 0.0F, false);
/* 136 */     this.bone15.func_78784_a(0, 0).func_228303_a_(-19.1857F, -58.4619F, 208.9143F, 39.0F, 36.0F, 55.0F, 0.0F, false);
/* 137 */     this.bone15.func_78784_a(0, 0).func_228303_a_(-1.1857F, -74.4619F, 208.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*     */     
/* 139 */     this.bone16 = new ModelRenderer((Model)this);
/* 140 */     this.bone16.func_78793_a(0.0F, 24.0F, 0.0F);
/* 141 */     this.bone16.func_78784_a(0, 0).func_228303_a_(-1.1857F, -21.4619F, 153.9143F, 2.0F, 25.0F, 55.0F, 0.0F, false);
/* 142 */     this.bone16.func_78784_a(0, 0).func_228303_a_(-21.1857F, -59.4619F, 153.9143F, 42.0F, 38.0F, 55.0F, 0.0F, false);
/* 143 */     this.bone16.func_78784_a(0, 0).func_228303_a_(-1.1857F, -75.4619F, 153.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
/*     */     
/* 145 */     this.bone17 = new ModelRenderer((Model)this);
/* 146 */     this.bone17.func_78793_a(0.0F, 24.0F, 0.0F);
/* 147 */     this.bone17.func_78784_a(0, 0).func_228303_a_(-1.1857F, -19.4619F, 94.9143F, 2.0F, 27.0F, 59.0F, 0.0F, false);
/* 148 */     this.bone17.func_78784_a(0, 0).func_228303_a_(-25.1857F, -62.4619F, 94.9143F, 50.0F, 44.0F, 59.0F, 0.0F, false);
/* 149 */     this.bone17.func_78784_a(0, 0).func_228303_a_(-1.1857F, -78.4619F, 94.9143F, 2.0F, 16.0F, 59.0F, 0.0F, false);
/*     */     
/* 151 */     this.bone18 = new ModelRenderer((Model)this);
/* 152 */     this.bone18.func_78793_a(0.0F, 24.0F, 0.0F);
/* 153 */     this.bone18.func_78784_a(0, 0).func_228303_a_(-1.1857F, -15.4619F, 35.9143F, 2.0F, 27.0F, 59.0F, 0.0F, false);
/* 154 */     this.bone18.func_78784_a(0, 0).func_228303_a_(-28.1857F, -65.4619F, 35.9143F, 56.0F, 50.0F, 59.0F, 0.0F, false);
/* 155 */     this.bone18.func_78784_a(0, 0).func_228303_a_(-1.1857F, -81.4619F, 63.9143F, 2.0F, 16.0F, 31.0F, 0.0F, false);
/*     */     
/* 157 */     this.headbone = new ModelRenderer((Model)this);
/* 158 */     this.headbone.func_78793_a(0.0F, 24.0F, 0.0F);
/* 159 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-31.1857F, -72.4619F, -7.0857F, 62.0F, 37.0F, 43.0F, 0.0F, false);
/* 160 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-1.1857F, -82.4619F, 5.9143F, 2.0F, 10.0F, 30.0F, 0.0F, false);
/* 161 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-31.1857F, -35.4619F, -7.0857F, 62.0F, 23.0F, 43.0F, 0.0F, false);
/* 162 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-32.1857F, -29.4619F, -50.0857F, 64.0F, 15.0F, 43.0F, 0.0F, false);
/* 163 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-29.1857F, -71.4619F, -24.0857F, 58.0F, 42.0F, 17.0F, 0.0F, false);
/* 164 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-24.1857F, -64.4619F, -45.0857F, 49.0F, 35.0F, 21.0F, 0.0F, false);
/* 165 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-23.1857F, -60.4619F, -66.0857F, 47.0F, 31.0F, 21.0F, 0.0F, false);
/* 166 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-22.1857F, -51.4619F, -85.0857F, 45.0F, 22.0F, 19.0F, 0.0F, false);
/* 167 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-0.1857F, -56.4619F, -72.0857F, 18.0F, 5.0F, 7.0F, 0.0F, false);
/* 168 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-9.1857F, -58.4619F, -70.0857F, 18.0F, 2.0F, 5.0F, 0.0F, false);
/* 169 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-19.1857F, -48.4619F, -120.0857F, 39.0F, 19.0F, 35.0F, 0.0F, false);
/* 170 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-17.1857F, -46.4619F, -155.0857F, 35.0F, 17.0F, 35.0F, 0.0F, false);
/* 171 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-12.1857F, -49.4619F, -153.0857F, 25.0F, 3.0F, 16.0F, 0.0F, false);
/* 172 */     this.headbone.func_78784_a(0, 0).func_228303_a_(17.8143F, -35.4619F, -142.0857F, 2.0F, 7.0F, 5.0F, 0.0F, false);
/* 173 */     this.headbone.func_78784_a(0, 0).func_228303_a_(17.8143F, -33.4619F, -149.0857F, 2.0F, 4.0F, 5.0F, 0.0F, false);
/* 174 */     this.headbone.func_78784_a(0, 0).func_228303_a_(17.8143F, -38.4619F, -148.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
/* 175 */     this.headbone.func_78784_a(0, 0).func_228303_a_(17.8143F, -40.4619F, -141.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
/* 176 */     this.headbone.func_78784_a(0, 0).func_228303_a_(19.8143F, -33.4619F, -109.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
/* 177 */     this.headbone.func_78784_a(0, 0).func_228303_a_(19.8143F, -33.4619F, -100.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
/* 178 */     this.headbone.func_78784_a(0, 0).func_228303_a_(19.8143F, -36.4619F, -99.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 179 */     this.headbone.func_78784_a(0, 0).func_228303_a_(19.8143F, -36.4619F, -108.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 180 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-24.1857F, -29.4619F, -120.0857F, 49.0F, 13.0F, 35.0F, 0.0F, false);
/* 181 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-25.1857F, -29.4619F, -85.0857F, 51.0F, 14.0F, 35.0F, 0.0F, false);
/* 182 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-21.1857F, -29.4619F, -156.0857F, 43.0F, 13.0F, 36.0F, 0.0F, false);
/* 183 */     this.headbone.func_78784_a(0, 0).func_228303_a_(24.8143F, -35.4619F, -41.0857F, 2.0F, 6.0F, 6.0F, 0.0F, false);
/* 184 */     this.headbone.func_78784_a(0, 0).func_228303_a_(24.8143F, -36.4619F, -33.0857F, 2.0F, 7.0F, 6.0F, 0.0F, false);
/* 185 */     this.headbone.func_78784_a(0, 0).func_228303_a_(24.8143F, -43.4619F, -32.0857F, 2.0F, 7.0F, 4.0F, 0.0F, false);
/* 186 */     this.headbone.func_78784_a(0, 0).func_228303_a_(24.8143F, -46.4619F, -31.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 187 */     this.headbone.func_78784_a(0, 0).func_228303_a_(24.8143F, -41.4619F, -40.0857F, 2.0F, 6.0F, 4.0F, 0.0F, false);
/* 188 */     this.headbone.func_78784_a(0, 0).func_228303_a_(24.8143F, -44.4619F, -39.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 189 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-19.1857F, -33.4619F, -149.0857F, 2.0F, 4.0F, 5.0F, 0.0F, false);
/* 190 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-19.1857F, -38.4619F, -148.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
/* 191 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-19.1857F, -40.4619F, -141.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
/* 192 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-19.1857F, -35.4619F, -142.0857F, 2.0F, 7.0F, 5.0F, 0.0F, false);
/* 193 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-21.1857F, -33.4619F, -109.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
/* 194 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-21.1857F, -36.4619F, -108.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 195 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-21.1857F, -33.4619F, -100.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
/* 196 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-21.1857F, -36.4619F, -99.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 197 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-26.1857F, -35.4619F, -41.0857F, 2.0F, 6.0F, 6.0F, 0.0F, false);
/* 198 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-26.1857F, -41.4619F, -40.0857F, 2.0F, 6.0F, 4.0F, 0.0F, false);
/* 199 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-26.1857F, -44.4619F, -39.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 200 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-26.1857F, -46.4619F, -31.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 201 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-26.1857F, -43.4619F, -32.0857F, 2.0F, 7.0F, 4.0F, 0.0F, false);
/* 202 */     this.headbone.func_78784_a(0, 0).func_228303_a_(-26.1857F, -36.4619F, -33.0857F, 2.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/* 204 */     this.bb_main = new ModelRenderer((Model)this);
/* 205 */     this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
/* 206 */     this.bb_main.func_78784_a(0, 0).func_228303_a_(-28.1857F, -70.4619F, 35.9143F, 56.0F, 5.0F, 28.0F, 0.0F, false);
/* 207 */     this.bb_main.func_78784_a(0, 0).func_228303_a_(-18.1857F, -56.4619F, -72.0857F, 18.0F, 5.0F, 7.0F, 0.0F, false);
/* 208 */     this.bb_main.func_78784_a(0, 0).func_228303_a_(-1.1857F, -86.4619F, 35.9143F, 2.0F, 16.0F, 28.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 215 */     this.headbone.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F) * 1.0F * limbSwingAmount;
/* 216 */     this.bone18.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.1F) * 1.0F * limbSwingAmount;
/* 217 */     this.bone17.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.2F) * 1.0F * limbSwingAmount;
/* 218 */     this.bone16.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.3F) * 1.0F * limbSwingAmount;
/* 219 */     this.bone15.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.4F) * 1.0F * limbSwingAmount;
/* 220 */     this.bone14.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.5F) * 1.0F * limbSwingAmount;
/* 221 */     this.bone13.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.6F) * 1.0F * limbSwingAmount;
/* 222 */     this.bone12.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.7F) * 1.0F * limbSwingAmount;
/* 223 */     this.bone11.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.8F) * 1.0F * limbSwingAmount;
/* 224 */     this.bone10.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 0.9F) * 1.0F * limbSwingAmount;
/* 225 */     this.bone9.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.0F) * 1.0F * limbSwingAmount;
/* 226 */     this.bone8.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.1F) * 1.0F * limbSwingAmount;
/* 227 */     this.bone7.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.2F) * 1.0F * limbSwingAmount;
/* 228 */     this.bone6.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.3F) * 1.0F * limbSwingAmount;
/* 229 */     this.bone5.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.4F) * 1.0F * limbSwingAmount;
/* 230 */     this.bone4.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.5F) * 1.0F * limbSwingAmount;
/* 231 */     this.bone3.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.6F) * 1.0F * limbSwingAmount;
/* 232 */     this.bone2.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.7F) * 1.0F * limbSwingAmount;
/* 233 */     this.bone.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.8F) * 1.0F * limbSwingAmount;
/* 234 */     this.TailFins.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.3662F + 1.9F) * 1.0F * limbSwingAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 241 */     this.TailFins.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 242 */     this.bone.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 243 */     this.bone2.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 244 */     this.bone3.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 245 */     this.bone4.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 246 */     this.bone5.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 247 */     this.bone6.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 248 */     this.bone7.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 249 */     this.bone8.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 250 */     this.bone9.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 251 */     this.bone10.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 252 */     this.bone11.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 253 */     this.bone12.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 254 */     this.bone13.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 255 */     this.bone14.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 256 */     this.bone15.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 257 */     this.bone16.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 258 */     this.bone17.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 259 */     this.bone18.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 260 */     this.headbone.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/* 261 */     this.bb_main.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 265 */     modelRenderer.field_78795_f = x;
/* 266 */     modelRenderer.field_78796_g = y;
/* 267 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void renderSaddle(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int noOverlay, int i, int i1, int i2, int i3) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\SeaKingModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */