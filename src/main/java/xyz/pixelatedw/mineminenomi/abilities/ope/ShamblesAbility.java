/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShamblesAbility extends Ability {
/*  40 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shambles", new Pair[] {
/*  41 */         (Pair)ImmutablePair.of("The user swaps place with the closest entity or block within the ROOM. Alt mode allows it to switch multiple entities within the ROOM.", null)
/*     */       });
/*  43 */   private static final ResourceLocation SHAMBLES_SINGLE_ICON = new ResourceLocation("mineminenomi", "textures/abilities/shambles.png");
/*  44 */   private static final ResourceLocation SHAMBLES_GROUP_ICON = new ResourceLocation("mineminenomi", "textures/abilities/shambles_group.png");
/*     */   
/*     */   private static final float COOLDOWN = 40.0F;
/*     */   
/*  48 */   public static final AbilityCore<ShamblesAbility> INSTANCE = (new AbilityCore.Builder("Shambles", AbilityCategory.DEVIL_FRUITS, ShamblesAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F)
/*  51 */       }).build();
/*     */   
/*  53 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.SINGLE)).addChangeModeEvent(this::onAltModeChange);
/*     */   static {
/*  55 */     SHAMBLES_LIST = (target -> (target instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity) ? false : ((target instanceof xyz.pixelatedw.mineminenomi.entities.SphereEntity) ? false : (
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  64 */       !(target.getEntity() instanceof AbilityProjectileEntity && !((AbilityProjectileEntity)target.getEntity()).isPhysical()))));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final Predicate<Entity> SHAMBLES_LIST;
/*     */ 
/*     */   
/*     */   public ShamblesAbility(AbilityCore<ShamblesAbility> core) {
/*  72 */     super(core);
/*     */     
/*  74 */     this.isNew = true;
/*     */     
/*  76 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent });
/*     */     
/*  78 */     addCanUseCheck(OpeHelper::hasRoomActive);
/*     */     
/*  80 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  84 */     RoomAbility roomAbility = (RoomAbility)AbilityDataCapability.get(entity).getEquippedAbility(RoomAbility.INSTANCE);
/*     */     
/*  86 */     boolean hadTarget = false;
/*     */     
/*  88 */     if (this.altModeComponent.getCurrentMode() == Mode.SINGLE) {
/*  89 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 64.0D);
/*     */       
/*  91 */       if (mop instanceof EntityRayTraceResult) {
/*  92 */         EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
/*  93 */         Entity target = entityRayTraceResult.func_216348_a();
/*     */         
/*  95 */         if (!roomAbility.isEntityInRoom(target)) {
/*     */           return;
/*     */         }
/*     */         
/*  99 */         if (!SHAMBLES_LIST.test(target)) {
/*     */           return;
/*     */         }
/*     */         
/* 103 */         float[] beforeCoords = { (float)entity.func_226277_ct_(), (float)entity.func_226278_cu_(), (float)entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A };
/*     */         
/* 105 */         entity.func_70012_b(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), target.field_70177_z, target.field_70125_A);
/* 106 */         entity.func_225653_b_(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */         
/* 108 */         target.func_70012_b(beforeCoords[0], beforeCoords[1], beforeCoords[2], beforeCoords[3], beforeCoords[4]);
/*     */         
/* 110 */         entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 111 */         entity.field_70170_p.func_184133_a(null, target.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */         
/* 113 */         hadTarget = true;
/* 114 */       } else if (mop instanceof BlockRayTraceResult) {
/* 115 */         BlockRayTraceResult result = (BlockRayTraceResult)mop;
/* 116 */         BlockPos pos = result.func_216350_a();
/* 117 */         BlockState state = entity.field_70170_p.func_180495_p(pos);
/* 118 */         BlockPos entityPos = entity.func_233580_cy_();
/* 119 */         BlockState entityPosState = entity.field_70170_p.func_180495_p(entityPos);
/*     */         
/* 121 */         boolean isInsideRoom = roomAbility.isPositionInRoom(pos);
/* 122 */         boolean isDestinationBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(state);
/* 123 */         boolean isOriginBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(entityPosState);
/*     */         
/* 125 */         if (isInsideRoom && !isDestinationBanned && !isOriginBanned) {
/* 126 */           BlockPos beforePos = entity.func_233580_cy_();
/*     */           
/* 128 */           ProtectedAreasData protectedAreaData = ProtectedAreasData.get(entity.field_70170_p);
/*     */           
/* 130 */           boolean a1 = protectedAreaData.isInsideRestrictedArea(beforePos.func_177958_n(), beforePos.func_177956_o(), beforePos.func_177952_p());
/* 131 */           boolean a2 = protectedAreaData.isInsideRestrictedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */           
/* 133 */           if (a1 != a2) {
/*     */             return;
/*     */           }
/*     */           
/* 137 */           entity.func_70012_b(pos.func_177958_n(), (pos.func_177956_o() + 1), pos.func_177952_p(), entity.field_70177_z, entity.field_70125_A);
/*     */           
/* 139 */           boolean b1 = AbilityHelper.placeBlockIfAllowed(entity, beforePos, state, 3, DefaultProtectionRules.AIR_CORE_FOLIAGE_ORE);
/* 140 */           boolean b2 = AbilityHelper.placeBlockIfAllowed(entity, pos, Blocks.field_150350_a.func_176223_P(), 3, DefaultProtectionRules.AIR_CORE_FOLIAGE_ORE);
/*     */           
/* 142 */           if (b1 && b2) {
/* 143 */             entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 0.5F, 1.0F);
/*     */             
/* 145 */             hadTarget = true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 149 */     } else if (this.altModeComponent.getCurrentMode() == Mode.GROUP) {
/* 150 */       BlockPos centerPos = roomAbility.getCenterBlock();
/*     */       
/* 152 */       Vector3d centerVec = new Vector3d(centerPos.func_177958_n(), centerPos.func_177956_o(), centerPos.func_177952_p());
/*     */       
/* 154 */       Predicate<Entity> groupCheck = ModEntityPredicates.getEnemyFactions(entity).and(SHAMBLES_LIST);
/*     */       
/* 156 */       List<Entity> targets = WyHelper.getNearbyEntities(centerVec, (IWorld)entity.field_70170_p, roomAbility.getROOMSize(), groupCheck, new Class[] { Entity.class });
/*     */       
/* 158 */       Collections.shuffle(targets);
/*     */       
/* 160 */       for (int i = 0; i < targets.size() && 
/* 161 */         i < targets.size() && i + 1 < targets.size(); i += 2) {
/*     */ 
/*     */ 
/*     */         
/* 165 */         Entity target1 = targets.get(i);
/* 166 */         Entity target2 = targets.get(i + 1);
/*     */         
/* 168 */         if (roomAbility.isPositionInRoom(target1.func_233580_cy_()) && roomAbility.isPositionInRoom(target2.func_233580_cy_())) {
/*     */ 
/*     */ 
/*     */           
/* 172 */           float[] beforeCoords = { (float)target2.func_226277_ct_(), (float)target2.func_226278_cu_(), (float)target2.func_226281_cx_(), target2.field_70177_z, target2.field_70125_A };
/*     */           
/* 174 */           target2.func_70012_b(target1.func_226277_ct_(), target1.func_226278_cu_(), target1.func_226281_cx_(), target1.field_70177_z, target1.field_70125_A);
/* 175 */           target2.func_225653_b_(target1.func_226277_ct_(), target1.func_226278_cu_(), target1.func_226281_cx_());
/* 176 */           target1.func_70012_b(beforeCoords[0], beforeCoords[1], beforeCoords[2], beforeCoords[3], beforeCoords[4]);
/*     */           
/* 178 */           entity.field_70170_p.func_184133_a(null, target2.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 0.5F, 1.0F);
/* 179 */           entity.field_70170_p.func_184133_a(null, target1.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 0.5F, 1.0F);
/*     */         } 
/*     */       } 
/* 182 */       if (targets.size() >= 2) {
/* 183 */         hadTarget = true;
/*     */       }
/*     */     } 
/*     */     
/* 187 */     if (hadTarget) {
/* 188 */       this.cooldownComponent.startCooldown(entity, 40.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 193 */     if (mode == Mode.SINGLE) {
/* 194 */       setDisplayIcon(SHAMBLES_SINGLE_ICON);
/* 195 */     } else if (mode == Mode.GROUP) {
/* 196 */       setDisplayIcon(SHAMBLES_GROUP_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 201 */     SINGLE, GROUP;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\ShamblesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */