/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TestAbility extends Ability {
/*  25 */   public static final AbilityCore<TestAbility> INSTANCE = (new AbilityCore.Builder("Test", AbilityCategory.DEVIL_FRUITS, TestAbility::new))
/*  26 */     .build();
/*     */   
/*  28 */   protected final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/*     */   
/*  30 */   private static final BlockPos POS = new BlockPos(-655, 90, -95);
/*     */   
/*  32 */   private Set<Long> blocks = new HashSet<>();
/*  33 */   private BlockPlacingHelper placeHelper = new BlockPlacingHelper();
/*     */   private long startTime;
/*     */   private int total;
/*     */   private int spawned;
/*     */   private Iterator<Long> iter;
/*     */   
/*     */   public TestAbility(AbilityCore<TestAbility> core) {
/*  40 */     super(core);
/*     */     
/*  42 */     this.isNew = true;
/*     */     
/*  44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  46 */     this.continuousComponent.addTickEvent(this::tickCotinuityEvent);
/*  47 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */     
/*  49 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  53 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
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
/*  78 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void tickCotinuityEvent(LivingEntity entity, IAbility ability) {
/*  82 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  87 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PIKA_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
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
/*     */ 
/*     */ 
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 115 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
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
/*     */   public static Set<Long> createCenteredFilledCube(World world, BlockPos origin, int sizeX, int sizeY, int sizeZ, BlockState state, int flag, @Nullable BlockProtectionRule rule) {
/* 130 */     int x0 = origin.func_177958_n();
/* 131 */     int y0 = origin.func_177956_o();
/* 132 */     int z0 = origin.func_177952_p();
/*     */     
/* 134 */     boolean isNegative = (sizeY < 0);
/* 135 */     sizeY = Math.abs(sizeY);
/*     */     
/* 137 */     int actualXSize = (int)Math.ceil(sizeX / 2.0D);
/* 138 */     int actualZSize = (int)Math.ceil(sizeZ / 2.0D);
/*     */     
/* 140 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 141 */     Set<Long> blocks = new HashSet<>();
/* 142 */     for (int x = -actualXSize; x < actualXSize; x++) {
/* 143 */       for (int y = 0; y < sizeY; y++) {
/* 144 */         for (int z = -actualZSize; z < actualZSize; z++) {
/* 145 */           mutpos.func_181079_c(x0 + x, isNegative ? (y0 - y) : (y0 + y), z0 + z);
/* 146 */           long l = mutpos.func_218275_a();
/* 147 */           blocks.add(Long.valueOf(l));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     return blocks;
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 156 */     FIRST, SECOND, THIRD;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\TestAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */