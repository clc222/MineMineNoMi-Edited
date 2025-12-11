/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.play.server.SPlayerPositionLookPacket;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.TaktBlockEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TaktAbility extends Ability {
/*  46 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "takt", new Pair[] {
/*  47 */         (Pair)ImmutablePair.of("The user lifts entities its looking at, preventing them from moving freely.", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float HOLD_TIME = 60.0F;
/*  52 */   public static final AbilityCore<TaktAbility> INSTANCE = (new AbilityCore.Builder("Takt", AbilityCategory.DEVIL_FRUITS, TaktAbility::new))
/*  53 */     .addDescriptionLine(DESCRIPTION)
/*  54 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(60.0F)
/*  55 */       }).build();
/*     */   
/*  57 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*     */   
/*  59 */   private List<Entity> grabbedEntities = new ArrayList<>();
/*     */   
/*     */   public TaktAbility(AbilityCore<TaktAbility> core) {
/*  62 */     super(core);
/*     */     
/*  64 */     this.isNew = true;
/*     */     
/*  66 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  68 */     addCanUseCheck(OpeHelper::hasRoomActive);
/*     */     
/*  70 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  74 */     RoomAbility abl = (RoomAbility)AbilityDataCapability.get(entity).getEquippedAbility(RoomAbility.INSTANCE);
/*     */     
/*  76 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, abl.getROOMSize());
/*     */     
/*  78 */     BlockPos blockPos = new BlockPos(mop.func_216347_e());
/*     */     
/*  80 */     if (mop.func_216346_c() == RayTraceResult.Type.BLOCK) {
/*  81 */       blockPos = new BlockPos((Vector3i)((BlockRayTraceResult)mop).func_216350_a());
/*  82 */     } else if (mop.func_216346_c() == RayTraceResult.Type.ENTITY) {
/*  83 */       blockPos = new BlockPos((Vector3i)((EntityRayTraceResult)mop).func_216348_a().func_233580_cy_());
/*     */     } 
/*     */     
/*  86 */     Function<BlockPos, TaktBlockEntity> mapper = pos -> {
/*     */         BlockState state = entity.field_70170_p.func_180495_p(pos);
/*     */         
/*     */         TaktBlockEntity fallingBlock = new TaktBlockEntity(entity.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), state);
/*     */         
/*     */         AbilityHelper.setDeltaMovement((Entity)fallingBlock, 0.0D, 0.0D, 0.0D);
/*     */         
/*     */         fallingBlock.time = 5;
/*     */         
/*     */         fallingBlock.func_189654_d(true);
/*     */         
/*     */         fallingBlock.dropItem = false;
/*     */         entity.field_70170_p.func_217376_c((Entity)fallingBlock);
/*     */         entity.field_70170_p.func_217377_a(pos, true);
/*     */         return fallingBlock;
/*     */       };
/* 102 */     WyHelper.getNearbyBlocks(blockPos, (IWorld)entity.field_70170_p, 2, (Predicate)isPositionGriefable(entity), (List)ImmutableList.of(Blocks.field_150350_a)).stream()
/* 103 */       .<TaktBlockEntity>map((Function<? super BlockPos, ? extends TaktBlockEntity>)mapper)
/* 104 */       .forEach(this.grabbedEntities::add);
/*     */     
/* 106 */     WyHelper.getNearbyLiving(mop.func_216347_e(), (IWorld)entity.field_70170_p, 2.0D, ModEntityPredicates.getEnemyFactions(entity)).stream()
/* 107 */       .filter(ModEntityPredicates.IS_ALIVE_AND_SURVIVAL)
/* 108 */       .filter(living -> abl.isPositionInRoom(living.func_233580_cy_()))
/* 109 */       .forEach(this.grabbedEntities::add);
/*     */     
/* 111 */     if (!this.grabbedEntities.isEmpty()) {
/* 112 */       this.continuousComponent.triggerContinuity(entity, 60.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 117 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 121 */     if (canUse(entity).isFail() || this.grabbedEntities.isEmpty()) {
/* 122 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 127 */     RoomAbility abl = (RoomAbility)AbilityDataCapability.get(entity).getEquippedAbility(RoomAbility.INSTANCE);
/*     */     
/* 129 */     this.grabbedEntities.stream().forEach(target -> {
/*     */           target.field_70125_A = target.field_70127_C;
/*     */           target.field_70177_z = target.field_70126_B;
/*     */           Random rand = new Random(target.hashCode());
/*     */           double offsetX = WyHelper.randomWithRange(rand, -2, 2);
/*     */           double offsetY = WyHelper.randomWithRange(rand, -2, 2);
/*     */           double offsetZ = WyHelper.randomWithRange(rand, -2, 2);
/*     */           double distance = 8.0D;
/*     */           Vector3d lookVec = entity.func_70040_Z();
/*     */           Vector3d pos = new Vector3d(lookVec.field_72450_a * distance + offsetX, entity.func_70047_e() / 2.0D + lookVec.field_72448_b * distance + offsetY, lookVec.field_72449_c * distance + offsetZ);
/*     */           if ((target instanceof LivingEntity && abl.isPositionInRoom(target.func_233580_cy_())) || isPositionGriefable(entity).test(target.func_233580_cy_())) {
/*     */             AbilityHelper.setDeltaMovement(target, entity.func_213303_ch().func_178787_e(pos).func_178788_d(target.func_213303_ch()));
/*     */             if (target instanceof ServerPlayerEntity) {
/*     */               Set<SPlayerPositionLookPacket.Flags> flags = EnumSet.of(SPlayerPositionLookPacket.Flags.X, SPlayerPositionLookPacket.Flags.Y, SPlayerPositionLookPacket.Flags.Z);
/*     */               ((ServerPlayerEntity)target).field_71135_a.func_175089_a(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), target.field_70126_B, target.field_70127_C, flags);
/*     */             } 
/*     */           } 
/*     */           target.field_70143_R = 0.0F;
/*     */         });
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
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 159 */     this.grabbedEntities.stream().filter(Entity::func_189652_ae).forEach(e -> e.func_189654_d(false));
/* 160 */     this.grabbedEntities.clear();
/*     */     
/* 162 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private static Predicate<BlockPos> isPositionGriefable(LivingEntity entity) {
/* 166 */     RoomAbility abl = (RoomAbility)AbilityDataCapability.get(entity).getEquippedAbility(RoomAbility.INSTANCE);
/*     */     
/* 168 */     ProtectedAreasData worldData = ProtectedAreasData.get(entity.field_70170_p);
/*     */     
/* 170 */     return pos -> {
/*     */         boolean isGriefDisabled = !CommonConfig.INSTANCE.isAbilityGriefingEnabled();
/*     */         if (isGriefDisabled)
/*     */           return false; 
/*     */         ProtectedArea area = worldData.getProtectedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */         if (area != null && !area.canDestroyBlocks())
/*     */           return false; 
/*     */         if (!abl.isPositionInRoom(pos))
/*     */           return false; 
/*     */         BlockState state = entity.field_70170_p.func_180495_p(pos);
/*     */         boolean isBlockBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(state);
/*     */         return !isBlockBanned;
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\TaktAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */