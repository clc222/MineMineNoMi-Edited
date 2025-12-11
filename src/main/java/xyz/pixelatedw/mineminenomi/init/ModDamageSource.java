/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ 
/*     */ 
/*     */ public class ModDamageSource
/*     */   extends DamageSource
/*     */ {
/*  26 */   public static final ModDamageSource SPECIAL = new ModDamageSource("special", false, false, false);
/*     */   
/*  28 */   public static final ModDamageSource DEVILS_CURSE = (ModDamageSource)(new ModDamageSource("devils_curse")).setUnavoidable().bypassLogia().func_151518_m().func_76348_h().func_76359_i();
/*     */   
/*  30 */   public static final DamageSource SOULBOUND_DAMAGE = (new ModDamageSource("soulbound_damage")).setUnavoidable().bypassLogia().func_151518_m().func_76348_h().func_76359_i();
/*     */   
/*  32 */   public static final DamageSource OUT_OF_BODY = (new DamageSource("out_of_body")).func_76348_h().func_82726_p();
/*     */   
/*  34 */   public static final DamageSource POISON = (new ModDamageSource("poison")).setSourceElement(SourceElement.POISON).setPiercing(1.0F).setUnavoidable().setInternal().func_82726_p();
/*     */   
/*  36 */   public static final ModDamageSource GENOCIDE_RAID = (new ModDamageSource("genocide_raid")).setPiercing(0.25F).setSourceElement(SourceElement.METAL);
/*     */   
/*  38 */   public static final ModDamageSource TOO_MUCH_GRAVITY = (new ModDamageSource("too_much_gravity")).setPiercing(1.0F).setUnavoidable().setSourceElement(SourceElement.GRAVITY);
/*     */   
/*  40 */   public static final ModDamageSource SUN_INCINERATION = (ModDamageSource)(new ModDamageSource("sun_incineration")).setPiercing(1.0F).setUnavoidable().setSourceElement(SourceElement.FIRE).bypassLogia().func_76361_j().func_151518_m();
/*     */   
/*  42 */   public static final ModDamageSource STORED_DAMAGE = (ModDamageSource)(new ModDamageSource("stored_damage")).setUnavoidable().bypassLogia().func_151518_m().func_76348_h().func_76359_i();
/*     */   
/*     */   private boolean isIndirectDamage = false;
/*     */   
/*     */   private boolean canBypassFriendlyDamage = false;
/*  47 */   private SourceElement element = SourceElement.NONE;
/*  48 */   private ArrayList<SourceType> sourceTypes = new ArrayList<>();
/*  49 */   private SourceHakiNature hakiNature = SourceHakiNature.UNKNOWN;
/*     */   
/*     */   private boolean isAoE;
/*     */   
/*     */   private boolean bypassLogia;
/*     */   
/*     */   private boolean unavoidable;
/*     */   private float piercing;
/*     */   private boolean ignore;
/*     */   private boolean isBlocked;
/*     */   
/*     */   public ModDamageSource(DamageSource source) {
/*  61 */     super(source.field_76373_n);
/*     */     
/*  63 */     if (source.func_76363_c())
/*  64 */       func_76348_h(); 
/*  65 */     if (source.func_76357_e())
/*  66 */       func_76359_i(); 
/*  67 */     if (source.func_151517_h()) {
/*  68 */       func_151518_m();
/*     */     }
/*  70 */     if (source instanceof ModDamageSource) {
/*     */       
/*  72 */       ModDamageSource msource = (ModDamageSource)source;
/*  73 */       msource.setSourceElement(this.element);
/*  74 */       msource.setSourceTypes(this.sourceTypes);
/*  75 */       msource.setHakiNature(this.hakiNature);
/*  76 */       if (msource.isAreaOfEffect())
/*  77 */         msource.setAreaOfEffect(); 
/*  78 */       if (msource.func_76352_a())
/*  79 */         msource.setProjectile(); 
/*  80 */       if (msource.isBypassingLogia())
/*  81 */         msource.bypassLogia(); 
/*  82 */       msource.setPiercing(this.piercing);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource(String damageTypeIn, boolean bypassArmor, boolean isFireDamage, boolean isExplosive) {
/*  88 */     super(damageTypeIn);
/*     */     
/*  90 */     if (bypassArmor)
/*  91 */       func_76348_h(); 
/*  92 */     if (isFireDamage)
/*  93 */       func_76361_j(); 
/*  94 */     if (isExplosive) {
/*  95 */       func_94540_d();
/*     */     }
/*     */   }
/*     */   
/*     */   public ModDamageSource(String damageType) {
/* 100 */     this(damageType, false, false, false);
/*     */   }
/*     */   
/*     */   public ModDamageSource setBypassGroupProtection() {
/* 104 */     return setBypassFriendlyDamage();
/*     */   }
/*     */   
/*     */   public boolean bypassesSameGroupProtection() {
/* 108 */     return this.canBypassFriendlyDamage;
/*     */   }
/*     */   
/*     */   public ModDamageSource setBypassFriendlyDamage() {
/* 112 */     this.canBypassFriendlyDamage = true;
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   public boolean canBypassFriendlyDamage() {
/* 117 */     return this.canBypassFriendlyDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource markIndirectDamage() {
/* 122 */     this.isIndirectDamage = true;
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIndirectDamage() {
/* 128 */     return this.isIndirectDamage;
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
/*     */ 
/*     */   
/*     */   public ModDamageSource ignore() {
/* 142 */     this.ignore = true;
/* 143 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isIgnored() {
/* 147 */     return this.ignore;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource bypassLogia() {
/* 152 */     this.bypassLogia = true;
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBypassingLogia() {
/* 158 */     return this.bypassLogia;
/*     */   }
/*     */   
/*     */   public ModDamageSource setPiercing(float pierce) {
/* 162 */     this.piercing = MathHelper.func_76131_a(pierce, 0.0F, 1.0F);
/*     */     
/* 164 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPierce() {
/* 169 */     return this.piercing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSlash() {
/* 176 */     return hasType(SourceType.SLASH);
/*     */   }
/*     */   
/*     */   public <T extends ModDamageSource> T setSlash() {
/* 180 */     if (!this.sourceTypes.contains(SourceType.SLASH)) {
/* 181 */       this.sourceTypes.add(SourceType.SLASH);
/*     */     }
/*     */     
/* 184 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlunt() {
/* 189 */     return hasType(SourceType.BLUNT);
/*     */   }
/*     */   
/*     */   public <T extends ModDamageSource> T setBlunt() {
/* 193 */     if (!this.sourceTypes.contains(SourceType.BLUNT)) {
/* 194 */       this.sourceTypes.add(SourceType.BLUNT);
/*     */     }
/*     */     
/* 197 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInternal() {
/* 202 */     return hasType(SourceType.INTERNAL);
/*     */   }
/*     */   
/*     */   public ModDamageSource setInternal() {
/* 206 */     if (!this.sourceTypes.contains(SourceType.INTERNAL)) {
/* 207 */       this.sourceTypes.add(SourceType.INTERNAL);
/*     */     }
/*     */     
/* 210 */     func_76348_h();
/*     */     
/* 212 */     bypassLogia();
/* 213 */     setPiercing(1.0F);
/*     */     
/* 215 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isUnavoidable() {
/* 219 */     return (this.unavoidable || func_94541_c());
/*     */   }
/*     */   
/*     */   public ModDamageSource setUnavoidable() {
/* 223 */     this.unavoidable = true;
/*     */     
/* 225 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFistDamage() {
/* 230 */     return hasType(SourceType.FIST);
/*     */   }
/*     */   
/*     */   public ModDamageSource setFistDamage() {
/* 234 */     if (!this.sourceTypes.contains(SourceType.FIST)) {
/* 235 */       this.sourceTypes.add(SourceType.FIST);
/*     */     }
/*     */     
/* 238 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 243 */     return (hasType(SourceType.PHYSICAL) || isFistDamage());
/*     */   }
/*     */   
/*     */   public ModDamageSource setPhysical() {
/* 247 */     if (!this.sourceTypes.contains(SourceType.PHYSICAL)) {
/* 248 */       this.sourceTypes.add(SourceType.PHYSICAL);
/*     */     }
/* 250 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAreaOfEffect() {
/* 255 */     return this.isAoE;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setAreaOfEffect() {
/* 260 */     this.isAoE = true;
/* 261 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SourceElement getElement() {
/* 266 */     return this.element;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setSourceElement(SourceElement element) {
/* 271 */     this.element = element;
/* 272 */     return this;
/*     */   }
/*     */   
/*     */   public ArrayList<SourceType> getSourceTypes() {
/* 276 */     if (this.sourceTypes.isEmpty()) {
/* 277 */       return AbilityHelper.NO_SOURCE_TYPE;
/*     */     }
/*     */     
/* 280 */     return new ArrayList<>(this.sourceTypes);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setSourceTypes(ArrayList<SourceType> sourceTypes) {
/* 285 */     this.sourceTypes = sourceTypes;
/*     */     
/* 287 */     return this;
/*     */   }
/*     */   
/*     */   public boolean hasType(SourceType type) {
/* 291 */     boolean flag = false;
/* 292 */     for (SourceType t : this.sourceTypes) {
/* 293 */       if (t.equals(type)) {
/* 294 */         flag = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 298 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public SourceHakiNature getHakiNature() {
/* 303 */     return this.hakiNature;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setHakiNature(SourceHakiNature nature) {
/* 308 */     this.hakiNature = nature;
/* 309 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ModDamageSource getSource() {
/* 315 */     return setSourceProprieties(new ModDamageSource(func_76355_l()));
/*     */   }
/*     */ 
/*     */   
/*     */   private ModDamageSource setSourceProprieties(ModDamageSource s) {
/* 320 */     if (func_76347_k())
/* 321 */       s.func_76361_j(); 
/* 322 */     if (func_94541_c())
/* 323 */       s.func_94540_d(); 
/* 324 */     if (func_76363_c())
/* 325 */       s.func_76348_h(); 
/* 326 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ModDamageSource setProjectile() {
/* 332 */     return setSourceProprieties((ModDamageSource)super.func_76349_b());
/*     */   }
/*     */   
/*     */   public ModDamageSource setBlocked() {
/* 336 */     this.isBlocked = true;
/* 337 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isBlocked() {
/* 341 */     return this.isBlocked;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vector3d func_188404_v() {
/* 347 */     if (func_76364_f() != null)
/* 348 */       return func_76364_f().func_213303_ch(); 
/* 349 */     if (func_76346_g() != null) {
/* 350 */       return func_76346_g().func_213303_ch();
/*     */     }
/* 352 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ITextComponent func_151519_b(LivingEntity entityLivingBaseIn) {
/* 358 */     if (this instanceof AbilityDamageSource) {
/* 359 */       return ((AbilityDamageSource)this).func_151519_b(entityLivingBaseIn);
/*     */     }
/* 361 */     return super.func_151519_b(entityLivingBaseIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource causeIndirectDamageFromSource(ThrowableEntity entity) {
/* 366 */     ModIndirectEntityDamageSource modIndirectEntityDamageSource = new ModIndirectEntityDamageSource(func_76355_l(), (Entity)entity, entity.func_234616_v_());
/* 367 */     if (func_76347_k())
/* 368 */       modIndirectEntityDamageSource.func_76361_j(); 
/* 369 */     if (func_94541_c())
/* 370 */       modIndirectEntityDamageSource.func_94540_d(); 
/* 371 */     if (func_76363_c()) {
/* 372 */       modIndirectEntityDamageSource.func_76348_h();
/*     */     }
/* 374 */     return (ModDamageSource)modIndirectEntityDamageSource;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource causeEntityDamageFromSource(Entity entity) {
/* 379 */     ModEntityDamageSource modEntityDamageSource = new ModEntityDamageSource(func_76355_l(), entity);
/* 380 */     return setSourceProprieties((ModDamageSource)modEntityDamageSource);
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbilityDamageSource causeAbilityDamage(LivingEntity player, AbilityCore ability) {
/* 385 */     return new AbilityDamageSource("ability", (Entity)player, ability);
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbilityDamageSource causeAbilityDamage(LivingEntity player, IAbility ability) {
/* 390 */     return new AbilityDamageSource("ability", (Entity)player, ability.getCore());
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbilityDamageSource causeAbilityDamage(LivingEntity player, IAbility ability, String damageType) {
/* 395 */     return new AbilityDamageSource(damageType, (Entity)player, ability.getCore());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */