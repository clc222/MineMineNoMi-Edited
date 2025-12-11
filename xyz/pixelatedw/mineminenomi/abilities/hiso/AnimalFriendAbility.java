/*     */ package xyz.pixelatedw.mineminenomi.abilities.hiso;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.passive.OcelotEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.passive.horse.AbstractHorseEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.particles.BasicParticleType;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ 
/*     */ public class AnimalFriendAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "animal_friend", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("Allows the user to tame all nearby tameable entities, even if they're already tamed by somebody else.", null) });
/*     */   static {
/*  39 */     TARGETS_PREDICATE = (new TargetsPredicate()).selector(entity -> ModEntityPredicates.canBeTamed().test(entity));
/*     */   }
/*     */   private static final TargetsPredicate TARGETS_PREDICATE;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int RANGE = 10;
/*  44 */   public static final AbilityCore<AnimalFriendAbility> INSTANCE = (new AbilityCore.Builder("Animal Friend", AbilityCategory.DEVIL_FRUITS, AnimalFriendAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/*  47 */       }).build();
/*     */   
/*  49 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   public AnimalFriendAbility(AbilityCore<AnimalFriendAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     this.isNew = true;
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*     */     
/*  57 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  61 */     if (!(entity instanceof PlayerEntity)) {
/*  62 */       ModMain.LOGGER.debug("Ability only usable by Players");
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     PlayerEntity player = (PlayerEntity)entity;
/*  67 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea((LivingEntity)player, 10.0F, TARGETS_PREDICATE);
/*  68 */     for (LivingEntity target : targets) {
/*  69 */       int i; boolean spawnParticles = false;
/*     */       
/*  71 */       if (target instanceof TameableEntity) {
/*  72 */         ((TameableEntity)target).func_193101_c(player);
/*  73 */         i = spawnParticles | true;
/*     */       } 
/*     */ 
/*     */       
/*  77 */       if (target instanceof AbstractHorseEntity) {
/*  78 */         ((AbstractHorseEntity)target).func_110263_g(player);
/*  79 */         i |= 0x1;
/*     */       } 
/*     */ 
/*     */       
/*  83 */       if (target instanceof OcelotEntity) {
/*  84 */         Method method = ObfuscationReflectionHelper.findMethod(OcelotEntity.class, "func_213528_r", new Class[] { boolean.class });
/*     */         try {
/*  86 */           method.setAccessible(true);
/*  87 */           method.invoke(target, new Object[] { Boolean.valueOf(true) });
/*     */         }
/*  89 */         catch (Exception e) {
/*  90 */           e.printStackTrace();
/*     */         } 
/*  92 */         i |= 0x1;
/*     */       } 
/*     */ 
/*     */       
/*  96 */       if (target instanceof KungFuDugongEntity) {
/*  97 */         ((KungFuDugongEntity)target).func_184754_b(player.func_110124_au());
/*  98 */         i |= 0x1;
/*     */       } 
/*     */ 
/*     */       
/* 102 */       if (target instanceof YagaraBullEntity) {
/* 103 */         ((YagaraBullEntity)target).func_184754_b(player.func_110124_au());
/* 104 */         i |= 0x1;
/*     */       } 
/*     */       
/* 107 */       if (i != 0) {
/* 108 */         spawnHearts(player, (Entity)target);
/*     */       }
/*     */     } 
/*     */     
/* 112 */     this.cooldownComponent.startCooldown((LivingEntity)player, 200.0F);
/*     */   }
/*     */   
/*     */   private void spawnHearts(PlayerEntity player, Entity target) {
/* 116 */     if (player instanceof ServerPlayerEntity) {
/* 117 */       BasicParticleType basicParticleType = ParticleTypes.field_197633_z;
/* 118 */       ((ServerWorld)target.field_70170_p).func_195600_a((ServerPlayerEntity)player, (IParticleData)basicParticleType, true, target.func_226277_ct_(), target.func_226278_cu_() + 0.75D, target.func_226281_cx_(), 7, 0.5D, 0.2D, 0.5D, 0.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hiso\AnimalFriendAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */