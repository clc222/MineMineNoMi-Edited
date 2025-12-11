/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class DamnedPunkModel
/*     */   extends EntityModel<Entity>
/*     */ {
/*     */   public final ModelRenderer rightArm;
/*     */   private final ModelRenderer cube_r1;
/*     */   private final ModelRenderer cube_r2;
/*     */   private final ModelRenderer cube_r3;
/*     */   private final ModelRenderer cube_r4;
/*     */   private final ModelRenderer cube_r5;
/*     */   private final ModelRenderer cube_r6;
/*     */   private final ModelRenderer cube_r7;
/*     */   private final ModelRenderer cube_r8;
/*     */   private final ModelRenderer cube_r9;
/*     */   private final ModelRenderer cube_r10;
/*     */   private final ModelRenderer cube_r11;
/*     */   private final ModelRenderer cube_r12;
/*     */   private final ModelRenderer cube_r13;
/*     */   private final ModelRenderer cube_r14;
/*     */   private final ModelRenderer cube_r15;
/*     */   private final ModelRenderer cube_r16;
/*     */   private final ModelRenderer cube_r17;
/*     */   private final ModelRenderer cube_r18;
/*     */   private final ModelRenderer cube_r19;
/*     */   private final ModelRenderer RightArm_r1;
/*     */   private final ModelRenderer RightArm_r2;
/*     */   private final ModelRenderer RightArm_r3;
/*     */   private final ModelRenderer octagon;
/*     */   private final ModelRenderer octagon_r1;
/*     */   private final ModelRenderer octagon3;
/*     */   private final ModelRenderer octagon_r2;
/*     */   private final ModelRenderer octagon2;
/*     */   private final ModelRenderer octagon_r3;
/*     */   
/*     */   public DamnedPunkModel() {
/*  44 */     this.field_78090_t = 128;
/*  45 */     this.field_78089_u = 128;
/*     */     
/*  47 */     this.rightArm = new ModelRenderer((Model)this);
/*  48 */     this.rightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  49 */     this.rightArm.func_78784_a(0, 16).func_228303_a_(-9.0F, -4.0F, -4.0F, 5.0F, 5.0F, 8.0F, 0.002F, false);
/*  50 */     this.rightArm.func_78784_a(24, 47).func_228303_a_(-9.0F, 1.0F, -4.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
/*  51 */     this.rightArm.func_78784_a(14, 73).func_228303_a_(-9.0F, 2.0F, -3.0F, 3.0F, 1.0F, 6.0F, 0.0F, false);
/*  52 */     this.rightArm.func_78784_a(84, 49).func_228303_a_(-10.0F, -3.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
/*  53 */     this.rightArm.func_78784_a(75, 14).func_228303_a_(-10.0F, -1.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  54 */     this.rightArm.func_78784_a(56, 51).func_228303_a_(-10.0F, 3.0F, 0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  55 */     this.rightArm.func_78784_a(56, 51).func_228303_a_(-10.0F, 3.0F, -1.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  56 */     this.rightArm.func_78784_a(56, 51).func_228303_a_(-10.0F, 3.0F, -2.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  57 */     this.rightArm.func_78784_a(56, 51).func_228303_a_(-10.0F, 3.0F, 1.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  58 */     this.rightArm.func_78784_a(75, 14).func_228303_a_(-10.0F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  59 */     this.rightArm.func_78784_a(75, 14).func_228303_a_(-10.0F, -1.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  60 */     this.rightArm.func_78784_a(84, 41).func_228303_a_(-10.0F, 1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
/*  61 */     this.rightArm.func_78784_a(57, 0).func_228303_a_(-11.0F, -5.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
/*  62 */     this.rightArm.func_78784_a(85, 11).func_228303_a_(-9.0F, 3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*  63 */     this.rightArm.func_78784_a(0, 0).func_228303_a_(-13.0F, -8.0F, -39.0F, 14.0F, 18.0F, 29.0F, 0.0F, false);
/*  64 */     this.rightArm.func_78784_a(87, 23).func_228303_a_(-4.0F, -5.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  65 */     this.rightArm.func_78784_a(87, 23).func_228303_a_(-4.0F, 1.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  66 */     this.rightArm.func_78784_a(87, 23).func_228303_a_(-10.0F, 1.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  67 */     this.rightArm.func_78784_a(87, 23).func_228303_a_(-10.0F, -5.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  68 */     this.rightArm.func_78784_a(86, 29).func_228303_a_(-5.0F, -13.0F, -34.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*  69 */     this.rightArm.func_78784_a(30, 67).func_228303_a_(-10.0F, -9.0F, -24.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*  70 */     this.rightArm.func_78784_a(0, 47).func_228303_a_(-16.0F, -3.0F, -34.0F, 1.0F, 6.0F, 6.0F, -0.001F, false);
/*  71 */     this.rightArm.func_78784_a(64, 47).func_228303_a_(-8.0F, -10.0F, -22.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
/*  72 */     this.rightArm.func_78784_a(0, 47).func_228303_a_(2.5F, -4.0F, -20.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*  73 */     this.rightArm.func_78784_a(0, 47).func_228303_a_(-16.5F, -1.0F, -32.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*  74 */     this.rightArm.func_78784_a(0, 47).func_228303_a_(2.5F, 4.0F, -30.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  76 */     this.cube_r1 = new ModelRenderer((Model)this);
/*  77 */     this.cube_r1.func_78793_a(-7.0F, -5.0F, 0.0F);
/*  78 */     this.rightArm.func_78792_a(this.cube_r1);
/*  79 */     setRotationAngle(this.cube_r1, -0.7854F, 0.0F, 0.0F);
/*  80 */     this.cube_r1.func_78784_a(83, 0).func_228303_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/*  82 */     this.cube_r2 = new ModelRenderer((Model)this);
/*  83 */     this.cube_r2.func_78793_a(-7.0F, -5.0F, 0.0F);
/*  84 */     this.rightArm.func_78792_a(this.cube_r2);
/*  85 */     setRotationAngle(this.cube_r2, 0.1968F, 0.3527F, 0.5763F);
/*  86 */     this.cube_r2.func_78784_a(83, 0).func_228303_a_(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/*  88 */     this.cube_r3 = new ModelRenderer((Model)this);
/*  89 */     this.cube_r3.func_78793_a(-15.5F, 0.0F, -31.0F);
/*  90 */     this.rightArm.func_78792_a(this.cube_r3);
/*  91 */     setRotationAngle(this.cube_r3, 0.7854F, 0.0F, 0.0F);
/*  92 */     this.cube_r3.func_78784_a(0, 47).func_228303_a_(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  94 */     this.cube_r4 = new ModelRenderer((Model)this);
/*  95 */     this.cube_r4.func_78793_a(-16.9153F, 6.0F, -11.7922F);
/*  96 */     this.rightArm.func_78792_a(this.cube_r4);
/*  97 */     setRotationAngle(this.cube_r4, 2.5986F, 0.8367F, 2.459F);
/*  98 */     this.cube_r4.func_78784_a(24, 56).func_228303_a_(-5.9F, -1.5F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 100 */     this.cube_r5 = new ModelRenderer((Model)this);
/* 101 */     this.cube_r5.func_78793_a(-10.9153F, 3.0F, -6.8922F);
/* 102 */     this.rightArm.func_78792_a(this.cube_r5);
/* 103 */     setRotationAngle(this.cube_r5, 0.0F, -2.7053F, 0.0F);
/* 104 */     this.cube_r5.func_78784_a(25, 56).func_228303_a_(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 106 */     this.cube_r6 = new ModelRenderer((Model)this);
/* 107 */     this.cube_r6.func_78793_a(-13.9323F, 3.0F, -6.7628F);
/* 108 */     this.rightArm.func_78792_a(this.cube_r6);
/* 109 */     setRotationAngle(this.cube_r6, 0.0F, 3.0543F, 0.0F);
/* 110 */     this.cube_r6.func_78784_a(27, 56).func_228303_a_(-1.5F, -1.0F, -0.7255F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 112 */     this.cube_r7 = new ModelRenderer((Model)this);
/* 113 */     this.cube_r7.func_78793_a(-16.9153F, 6.0F, -19.7922F);
/* 114 */     this.rightArm.func_78792_a(this.cube_r7);
/* 115 */     setRotationAngle(this.cube_r7, 0.0F, 1.5708F, 0.0F);
/* 116 */     this.cube_r7.func_78784_a(24, 56).func_228303_a_(-8.4F, -1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 118 */     this.cube_r8 = new ModelRenderer((Model)this);
/* 119 */     this.cube_r8.func_78793_a(-16.3634F, 6.0F, -21.6518F);
/* 120 */     this.rightArm.func_78792_a(this.cube_r8);
/* 121 */     setRotationAngle(this.cube_r8, 0.0F, 1.0908F, 0.0F);
/* 122 */     this.cube_r8.func_78784_a(25, 56).func_228303_a_(-3.9043F, -1.0F, -0.6308F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 124 */     this.cube_r9 = new ModelRenderer((Model)this);
/* 125 */     this.cube_r9.func_78793_a(-14.0F, 6.0F, -23.0F);
/* 126 */     this.rightArm.func_78792_a(this.cube_r9);
/* 127 */     setRotationAngle(this.cube_r9, 0.0F, 0.6545F, 0.0F);
/* 128 */     this.cube_r9.func_78784_a(25, 56).func_228303_a_(-3.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 130 */     this.cube_r10 = new ModelRenderer((Model)this);
/* 131 */     this.cube_r10.func_78793_a(-8.0F, 11.7425F, -9.2669F);
/* 132 */     this.rightArm.func_78792_a(this.cube_r10);
/* 133 */     setRotationAngle(this.cube_r10, -2.3998F, 0.0F, 0.0F);
/* 134 */     this.cube_r10.func_78784_a(57, 2).func_228303_a_(-1.0F, -1.6462F, -1.2493F, 2.0F, 5.0F, 2.0F, 0.11F, false);
/*     */     
/* 136 */     this.cube_r11 = new ModelRenderer((Model)this);
/* 137 */     this.cube_r11.func_78793_a(-8.0F, 9.7425F, -4.2669F);
/* 138 */     this.rightArm.func_78792_a(this.cube_r11);
/* 139 */     setRotationAngle(this.cube_r11, -1.1781F, 0.0F, 0.0F);
/* 140 */     this.cube_r11.func_78784_a(57, 2).func_228303_a_(-1.0F, 0.7386F, -0.3149F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 142 */     this.cube_r12 = new ModelRenderer((Model)this);
/* 143 */     this.cube_r12.func_78793_a(-8.0F, 7.0F, -1.0F);
/* 144 */     this.rightArm.func_78792_a(this.cube_r12);
/* 145 */     setRotationAngle(this.cube_r12, -0.829F, 0.0F, 0.0F);
/* 146 */     this.cube_r12.func_78784_a(57, 0).func_228303_a_(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.001F, false);
/*     */     
/* 148 */     this.cube_r13 = new ModelRenderer((Model)this);
/* 149 */     this.cube_r13.func_78793_a(-8.5F, -7.0F, -2.0F);
/* 150 */     this.rightArm.func_78792_a(this.cube_r13);
/* 151 */     setRotationAngle(this.cube_r13, 0.7854F, 0.0F, -0.6981F);
/* 152 */     this.cube_r13.func_78784_a(25, 15).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/* 153 */     this.cube_r13.func_78784_a(25, 15).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 155 */     this.cube_r14 = new ModelRenderer((Model)this);
/* 156 */     this.cube_r14.func_78793_a(-7.8572F, -6.234F, 2.0F);
/* 157 */     this.rightArm.func_78792_a(this.cube_r14);
/* 158 */     setRotationAngle(this.cube_r14, -0.6545F, 0.3054F, -0.6981F);
/* 159 */     this.cube_r14.func_78784_a(25, 15).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 161 */     this.cube_r15 = new ModelRenderer((Model)this);
/* 162 */     this.cube_r15.func_78793_a(-9.3893F, -4.9484F, 0.0F);
/* 163 */     this.rightArm.func_78792_a(this.cube_r15);
/* 164 */     setRotationAngle(this.cube_r15, -0.1745F, 0.0F, -0.6981F);
/* 165 */     this.cube_r15.func_78784_a(25, 16).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 167 */     this.cube_r16 = new ModelRenderer((Model)this);
/* 168 */     this.cube_r16.func_78793_a(-5.6823F, -6.7535F, 2.0F);
/* 169 */     this.rightArm.func_78792_a(this.cube_r16);
/* 170 */     setRotationAngle(this.cube_r16, -0.8727F, 0.0F, 0.6109F);
/* 171 */     this.cube_r16.func_78784_a(25, 15).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 173 */     this.cube_r17 = new ModelRenderer((Model)this);
/* 174 */     this.cube_r17.func_78793_a(-5.6823F, -6.7535F, 0.0F);
/* 175 */     this.rightArm.func_78792_a(this.cube_r17);
/* 176 */     setRotationAngle(this.cube_r17, 0.0F, 0.0F, 0.6109F);
/* 177 */     this.cube_r17.func_78784_a(25, 15).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/* 178 */     this.cube_r17.func_78784_a(25, 15).func_228303_a_(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 180 */     this.cube_r18 = new ModelRenderer((Model)this);
/* 181 */     this.cube_r18.func_78793_a(-8.5F, -7.0F, 0.0F);
/* 182 */     this.rightArm.func_78792_a(this.cube_r18);
/* 183 */     setRotationAngle(this.cube_r18, 0.0F, 0.0F, -0.6981F);
/* 184 */     this.cube_r18.func_78784_a(25, 15).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 186 */     this.cube_r19 = new ModelRenderer((Model)this);
/* 187 */     this.cube_r19.func_78793_a(-6.0747F, -6.6512F, -1.5F);
/* 188 */     this.rightArm.func_78792_a(this.cube_r19);
/* 189 */     setRotationAngle(this.cube_r19, 0.4363F, 0.0F, 0.48F);
/* 190 */     this.cube_r19.func_78784_a(25, 15).func_228303_a_(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 192 */     this.RightArm_r1 = new ModelRenderer((Model)this);
/* 193 */     this.RightArm_r1.func_78793_a(-1.0F, 1.0F, 0.0F);
/* 194 */     this.rightArm.func_78792_a(this.RightArm_r1);
/* 195 */     setRotationAngle(this.RightArm_r1, -0.7854F, 0.0F, 0.0F);
/* 196 */     this.RightArm_r1.func_78784_a(0, 0).func_228303_a_(-3.0F, -4.5F, -4.5F, 5.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/* 198 */     this.RightArm_r2 = new ModelRenderer((Model)this);
/* 199 */     this.RightArm_r2.func_78793_a(-7.0F, 0.0F, -9.5263F);
/* 200 */     this.rightArm.func_78792_a(this.RightArm_r2);
/* 201 */     setRotationAngle(this.RightArm_r2, 0.0F, -1.5708F, -1.5708F);
/* 202 */     this.RightArm_r2.func_78784_a(36, 78).func_228303_a_(-36.5F, 4.0F, -4.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
/* 203 */     this.RightArm_r2.func_78784_a(82, 67).func_228303_a_(-36.5F, -4.0F, -4.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
/* 204 */     this.RightArm_r2.func_78784_a(24, 47).func_228303_a_(-36.5F, -2.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/* 205 */     this.RightArm_r2.func_78784_a(57, 11).func_228303_a_(-36.5F, -2.0F, -6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/* 206 */     this.RightArm_r2.func_78784_a(0, 47).func_228303_a_(-34.5F, -5.0F, -7.0F, 5.0F, 12.0F, 14.0F, 0.0F, false);
/* 207 */     this.RightArm_r2.func_78784_a(0, 73).func_228303_a_(-0.5F, -3.0F, -5.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
/* 208 */     this.RightArm_r2.func_78784_a(38, 47).func_228303_a_(2.5F, -1.0F, -3.0F, 3.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 210 */     this.RightArm_r3 = new ModelRenderer((Model)this);
/* 211 */     this.RightArm_r3.func_78793_a(-1.0F, 1.0F, 0.0F);
/* 212 */     this.rightArm.func_78792_a(this.RightArm_r3);
/* 213 */     setRotationAngle(this.RightArm_r3, -1.5708F, 0.0F, 0.0F);
/* 214 */     this.RightArm_r3.func_78784_a(0, 0).func_228303_a_(-3.0F, -4.0F, -5.0F, 5.0F, 8.0F, 8.0F, -0.001F, false);
/*     */     
/* 216 */     this.octagon = new ModelRenderer((Model)this);
/* 217 */     this.octagon.func_78793_a(5.0F, 5.0F, -27.0F);
/* 218 */     this.rightArm.func_78792_a(this.octagon);
/* 219 */     this.octagon.func_78784_a(74, 55).func_228303_a_(-4.0F, -9.2426F, 5.0F, 2.0F, 2.0F, 6.0F, -0.003F, false);
/* 220 */     this.octagon.func_78784_a(0, 0).func_228303_a_(-4.0F, -11.0F, 6.7574F, 2.0F, 6.0F, 2.0F, 0.001F, false);
/*     */     
/* 222 */     this.octagon_r1 = new ModelRenderer((Model)this);
/* 223 */     this.octagon_r1.func_78793_a(-8.0F, -8.0F, 8.0F);
/* 224 */     this.octagon.func_78792_a(this.octagon_r1);
/* 225 */     setRotationAngle(this.octagon_r1, -0.7854F, 0.0F, 0.0F);
/* 226 */     this.octagon_r1.func_78784_a(0, 0).func_228303_a_(4.0F, -3.0F, -1.2426F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/* 227 */     this.octagon_r1.func_78784_a(74, 55).func_228303_a_(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, -0.002F, false);
/*     */     
/* 229 */     this.octagon3 = new ModelRenderer((Model)this);
/* 230 */     this.octagon3.func_78793_a(1.0F, 8.0F, -26.0F);
/* 231 */     this.rightArm.func_78792_a(this.octagon3);
/* 232 */     this.octagon3.func_78784_a(38, 47).func_228303_a_(-16.0F, -11.0F, -12.2426F, 2.0F, 6.0F, 14.0F, 0.002F, false);
/* 233 */     this.octagon3.func_78784_a(56, 73).func_228303_a_(-16.0F, -15.2426F, -8.0F, 2.0F, 14.0F, 6.0F, -0.001F, false);
/*     */     
/* 235 */     this.octagon_r2 = new ModelRenderer((Model)this);
/* 236 */     this.octagon_r2.func_78793_a(-8.0F, -8.0F, -5.0F);
/* 237 */     this.octagon3.func_78792_a(this.octagon_r2);
/* 238 */     setRotationAngle(this.octagon_r2, -0.7854F, 0.0F, 0.0F);
/* 239 */     this.octagon_r2.func_78784_a(56, 73).func_228303_a_(-8.0F, -7.2426F, -3.0F, 2.0F, 14.0F, 6.0F, -0.002F, false);
/* 240 */     this.octagon_r2.func_78784_a(38, 47).func_228303_a_(-8.0F, -3.0F, -7.2426F, 2.0F, 6.0F, 14.0F, -0.004F, false);
/*     */     
/* 242 */     this.octagon2 = new ModelRenderer((Model)this);
/* 243 */     this.octagon2.func_78793_a(5.0F, 13.0F, -37.0F);
/* 244 */     this.rightArm.func_78792_a(this.octagon2);
/* 245 */     this.octagon2.func_78784_a(74, 55).func_228303_a_(-4.0F, -9.2426F, 5.0F, 2.0F, 2.0F, 6.0F, 0.002F, false);
/* 246 */     this.octagon2.func_78784_a(0, 0).func_228303_a_(-4.0F, -11.0F, 6.7574F, 2.0F, 6.0F, 2.0F, -0.001F, false);
/*     */     
/* 248 */     this.octagon_r3 = new ModelRenderer((Model)this);
/* 249 */     this.octagon_r3.func_78793_a(-8.0F, -8.0F, 8.0F);
/* 250 */     this.octagon2.func_78792_a(this.octagon_r3);
/* 251 */     setRotationAngle(this.octagon_r3, -0.7854F, 0.0F, 0.0F);
/* 252 */     this.octagon_r3.func_78784_a(0, 0).func_228303_a_(4.0F, -3.0F, -1.2426F, 2.0F, 6.0F, 2.0F, -0.004F, false);
/* 253 */     this.octagon_r3.func_78784_a(74, 55).func_228303_a_(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
/* 254 */     this.octagon_r3.func_78784_a(74, 55).func_228303_a_(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, -0.002F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225597_a_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 265 */     this.rightArm.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 270 */     modelRenderer.field_78795_f = x;
/* 271 */     modelRenderer.field_78796_g = y;
/* 272 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\abilities\DamnedPunkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */