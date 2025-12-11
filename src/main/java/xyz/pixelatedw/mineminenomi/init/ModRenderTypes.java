/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderState;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.blocks.FlagTileEntityRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModRenderTypes
/*     */   extends RenderType
/*     */ {
/*     */   public ModRenderTypes(String name, VertexFormat format, int drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTask, Runnable clearTask) {
/*  19 */     super(name, format, drawMode, bufferSize, useDelegate, needsSorting, setupTask, clearTask);
/*     */   }
/*     */   
/*  22 */   public static final RenderState.TargetState AURA_TARGET = new RenderState.TargetState("mineminenomi:aura_target", () -> ModRenderTypeBuffers.getInstance().getHakiAuraFramebuffer().func_147610_a(false), () -> Minecraft.func_71410_x().func_147110_a().func_147610_a(false));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static final RenderType ENERGY = (RenderType)func_228633_a_("mineminenomi:energy", DefaultVertexFormats.field_227850_m_, 7, 256, false, true, 
/*  29 */       RenderType.State.func_228694_a_()
/*  30 */       .func_228724_a_(RenderState.field_228523_o_)
/*  31 */       .func_228714_a_(RenderState.field_228534_z_)
/*  32 */       .func_228713_a_(RenderState.field_228517_i_)
/*  33 */       .func_228726_a_(RenderState.field_228512_d_)
/*  34 */       .func_228728_a_(true));
/*     */   
/*  36 */   public static final RenderType ENERGY_NO_CULL = (RenderType)func_228633_a_("mineminenomi:energy_no_cull", DefaultVertexFormats.field_227850_m_, 7, 256, false, true, 
/*  37 */       RenderType.State.func_228694_a_()
/*  38 */       .func_228724_a_(RenderState.field_228523_o_)
/*  39 */       .func_228714_a_(RenderState.field_228491_A_)
/*  40 */       .func_228713_a_(RenderState.field_228517_i_)
/*  41 */       .func_228726_a_(RenderState.field_228512_d_)
/*  42 */       .func_228728_a_(true));
/*     */   
/*  44 */   public static final RenderType SOLID = (RenderType)func_228633_a_("mineminenomi:solid", DefaultVertexFormats.field_227850_m_, 7, 256, false, true, 
/*  45 */       RenderType.State.func_228694_a_()
/*  46 */       .func_228724_a_(RenderState.field_228523_o_)
/*  47 */       .func_228714_a_(RenderState.field_228534_z_)
/*  48 */       .func_228728_a_(true));
/*     */   
/*  50 */   public static final RenderType TRANSPARENT_COLOR2 = (RenderType)func_228633_a_("mineminenomi:translucent_color_notexture2", DefaultVertexFormats.field_227850_m_, 7, 256, false, true, 
/*  51 */       RenderType.State.func_228694_a_()
/*  52 */       .func_228726_a_(field_228515_g_)
/*  53 */       .func_228724_a_(field_228523_o_)
/*  54 */       .func_228714_a_(RenderState.field_228491_A_)
/*  55 */       .func_228719_a_(field_228528_t_)
/*  56 */       .func_228716_a_(field_228532_x_)
/*  57 */       .func_228728_a_(true));
/*     */   
/*  59 */   public static final RenderType TRANSPARENT_COLOR = (RenderType)func_228633_a_("mineminenomi:translucent_color_notexture", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, 
/*  60 */       RenderType.State.func_228694_a_()
/*  61 */       .func_228726_a_(field_228515_g_)
/*  62 */       .func_228724_a_(field_228523_o_)
/*  63 */       .func_228714_a_(RenderState.field_228491_A_)
/*  64 */       .func_228719_a_(field_228528_t_)
/*  65 */       .func_228716_a_(field_228532_x_)
/*  66 */       .func_228728_a_(true));
/*     */   
/*  68 */   public static final RenderType FLAG = (RenderType)func_228633_a_("mineminenomi:flag", DefaultVertexFormats.field_227850_m_, 7, 256, false, true, 
/*  69 */       RenderType.State.func_228694_a_()
/*  70 */       .func_228726_a_(field_228515_g_)
/*  71 */       .func_228724_a_(field_228523_o_)
/*  72 */       .func_228714_a_(RenderState.field_228534_z_)
/*  73 */       .func_228719_a_(field_228528_t_)
/*  74 */       .func_228716_a_(field_228533_y_)
/*  75 */       .func_228728_a_(true));
/*     */   
/*  77 */   public static final RenderType FLAG_ON_FIRE = (RenderType)func_228633_a_("mineminenomi:flag_on_fire", DefaultVertexFormats.field_227849_i_, 7, 256, false, true, 
/*  78 */       RenderType.State.func_228694_a_()
/*  79 */       .func_228726_a_(field_228515_g_)
/*  80 */       .func_228724_a_(new RenderState.TextureState(FlagTileEntityRenderer.ON_FIRE_TEXTURE, false, false))
/*  81 */       .func_228714_a_(RenderState.field_228534_z_)
/*  82 */       .func_228719_a_(field_228528_t_)
/*  83 */       .func_228716_a_(field_228533_y_)
/*  84 */       .func_228725_a_(field_228527_s_)
/*  85 */       .func_228718_a_(field_239235_M_)
/*  86 */       .func_228728_a_(true));
/*     */   
/*     */   public static RenderType getNewAuraRenderType() {
/*  89 */     return (RenderType)func_228633_a_("mineminenomi:aura", DefaultVertexFormats.field_227851_o_, 7, 256, true, true, 
/*  90 */         RenderType.State.func_228694_a_()
/*  91 */         .func_228714_a_(field_228491_A_)
/*  92 */         .func_228713_a_(field_228517_i_)
/*  93 */         .func_228725_a_(field_228525_q_)
/*  94 */         .func_228717_a_(field_228501_K_)
/*  95 */         .func_228721_a_(AURA_TARGET)
/*  96 */         .func_228728_a_(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderType getEnergyRenderType() {
/* 108 */     RenderType.State state = RenderType.State.func_228694_a_().func_228724_a_(RenderState.field_228523_o_).func_228714_a_(RenderState.field_228534_z_).func_228713_a_(RenderState.field_228517_i_).func_228716_a_(RenderState.field_228532_x_).func_228726_a_(RenderState.field_228512_d_).func_228728_a_(true);
/*     */     
/* 110 */     return (RenderType)func_228633_a_("mineminenomi:energy", DefaultVertexFormats.field_227850_m_, 7, 256, false, true, state);
/*     */   }
/*     */   
/*     */   public static RenderType getArenaFogRenderType(ResourceLocation texture) {
/* 114 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     RenderType.State state = RenderType.State.func_228694_a_().func_228724_a_(textureState).func_228714_a_(field_228534_z_).func_228728_a_(true);
/*     */     
/* 123 */     return (RenderType)func_228633_a_("mineminenomi:arena_fog", DefaultVertexFormats.field_227852_q_, 4, 256, false, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAuraRenderType(ResourceLocation texture) {
/* 128 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     RenderType.State state = RenderType.State.func_228694_a_().func_228724_a_(textureState).func_228726_a_(field_228515_g_).func_228716_a_(field_228532_x_).func_228713_a_(field_228517_i_).func_228719_a_(field_228528_t_).func_228722_a_(field_228530_v_).func_228728_a_(true);
/*     */     
/* 138 */     return (RenderType)func_228633_a_("mineminenomi:aura_color_notexture", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getYomiRenderType(ResourceLocation texture) {
/* 143 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(RenderState.TransparencyState.field_228515_g_).func_228713_a_(RenderState.AlphaState.field_228517_i_).func_228719_a_(RenderState.LightmapState.field_228528_t_).func_228716_a_(RenderState.DiffuseLightingState.field_228532_x_).func_228714_a_(RenderState.CullState.field_228491_A_).func_228724_a_(textureState).func_228728_a_(true);
/* 152 */     return (RenderType)func_228633_a_("mineminenomi:yomi", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getZoanRenderType(ResourceLocation texture) {
/* 157 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(RenderState.TransparencyState.field_228515_g_).func_228713_a_(RenderState.AlphaState.field_228517_i_).func_228719_a_(RenderState.LightmapState.field_228528_t_).func_228722_a_(field_228530_v_).func_228716_a_(RenderState.DiffuseLightingState.field_228532_x_).func_228714_a_(RenderState.CullState.field_228491_A_).func_228724_a_(textureState).func_228728_a_(true);
/* 167 */     return (RenderType)func_228633_a_("mineminenomi:zoan", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static RenderType getZoanWithCullingRenderType(ResourceLocation texture) {
/* 173 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(RenderState.TransparencyState.field_228515_g_).func_228713_a_(RenderState.AlphaState.field_228517_i_).func_228719_a_(RenderState.LightmapState.field_228528_t_).func_228722_a_(field_228530_v_).func_228716_a_(RenderState.DiffuseLightingState.field_228532_x_).func_228724_a_(textureState).func_228728_a_(true);
/* 182 */     return (RenderType)func_228633_a_("mineminenomi:zoan", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAbilityBody(ResourceLocation texture) {
/* 187 */     return getAbilityBody(texture, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAbilityBody(ResourceLocation texture, boolean equalDepthTest, boolean culling) {
/* 192 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(RenderState.TransparencyState.field_228515_g_).func_228713_a_(RenderState.AlphaState.field_228517_i_).func_228724_a_(textureState).func_228714_a_(culling ? RenderState.CullState.field_228534_z_ : RenderState.CullState.field_228491_A_).func_228715_a_(equalDepthTest ? RenderState.DepthTestState.field_228493_C_ : RenderState.DepthTestState.field_228494_D_).func_228719_a_(RenderState.LightmapState.field_228528_t_).func_228728_a_(true);
/* 201 */     return (RenderType)func_228633_a_("mineminenomi:ability_body", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAbilityHand(ResourceLocation texture) {
/* 206 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(RenderState.TransparencyState.field_228515_g_).func_228713_a_(RenderState.AlphaState.field_228517_i_).func_228724_a_(textureState).func_228714_a_(RenderState.CullState.field_228491_A_).func_228719_a_(RenderState.LightmapState.field_228528_t_).func_228728_a_(true);
/* 214 */     return (RenderType)func_228633_a_("mineminenomi:ability_hand", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getWantedPoster(ResourceLocation texture) {
/* 219 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(field_228515_g_).func_228718_a_(field_239235_M_).func_228713_a_(field_228517_i_).func_228724_a_(textureState).func_228714_a_(field_228491_A_).func_228719_a_(field_228528_t_).func_228728_a_(true);
/*     */     
/* 229 */     return (RenderType)func_228633_a_("mineminenomi:wanted_poster", DefaultVertexFormats.field_176600_a, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getWantedPosterExpiration(ResourceLocation texture) {
/* 234 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(field_228515_g_).func_228713_a_(field_228517_i_).func_228724_a_(textureState).func_228714_a_(RenderState.CullState.field_228534_z_).func_228719_a_(field_228528_t_).func_228728_a_(true);
/* 242 */     return (RenderType)func_228633_a_("mineminenomi:wanted_poster_expiration", DefaultVertexFormats.field_176600_a, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getEyesLayerRenderType(ResourceLocation texture) {
/* 247 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     RenderType.State state = RenderType.State.func_228694_a_().func_228726_a_(RenderState.TransparencyState.field_228515_g_).func_228713_a_(RenderState.AlphaState.field_228517_i_).func_228719_a_(RenderState.LightmapState.field_228528_t_).func_228716_a_(RenderState.DiffuseLightingState.field_228532_x_).func_228724_a_(textureState).func_228728_a_(true);
/* 255 */     return (RenderType)func_228633_a_("mineminenomi:eyes_layer", DefaultVertexFormats.field_227849_i_, 7, 256, true, true, state);
/*     */   }
/*     */   
/* 258 */   public static final VertexFormat PARTICLE_POSITION_TEX_COLOR_LMAP = new VertexFormat(ImmutableList.builder()
/* 259 */       .add(DefaultVertexFormats.field_181713_m)
/* 260 */       .add(DefaultVertexFormats.field_181715_o)
/* 261 */       .add(DefaultVertexFormats.field_181714_n)
/* 262 */       .add(DefaultVertexFormats.field_227848_e_)
/* 263 */       .build());
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModRenderTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */