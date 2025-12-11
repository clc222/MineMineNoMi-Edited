/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SSetPassengersPacket;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSyncAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class MorphAbility
/*     */   extends StatsChangeAbility
/*     */   implements IParallelContinuousAbility, IMorphAbility
/*     */ {
/*     */   public MorphAbility(AbilityCore core) {
/*  35 */     super(core);
/*     */     
/*  37 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  38 */     this.beforeContinuityStopEvent = this::beforeContinuityStopEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkZoanTransformation(PlayerEntity player) {
/*  43 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/*  45 */       double transformationHeight = 0.0D;
/*  46 */       double transformationWidth = 0.0D;
/*  47 */       boolean hasEmptySpace = true;
/*     */       
/*  49 */       if (getTransformation() instanceof xyz.pixelatedw.mineminenomi.api.morph.MorphInfo && getTransformation().getSizes() != null) {
/*     */         
/*  51 */         EntitySize size = (EntitySize)getTransformation().getSizes().get(Pose.STANDING);
/*  52 */         transformationHeight = size.field_220316_b;
/*  53 */         transformationWidth = size.field_220315_a;
/*     */       } 
/*     */       
/*  56 */       if (transformationHeight > 0.0D)
/*     */       {
/*  58 */         for (int i = 0; i < transformationHeight; i++) {
/*     */           
/*  60 */           BlockPos pos = player.func_233580_cy_().func_177982_a(0, i, 0);
/*  61 */           if (player.field_70170_p.func_217400_a(pos, (Entity)player)) {
/*     */             
/*  63 */             hasEmptySpace = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*  69 */       if (hasEmptySpace && transformationWidth > 0.0D)
/*     */       {
/*  71 */         for (int i = 0; i < transformationWidth; i++) {
/*     */           
/*  73 */           BlockPos pos1 = player.func_233580_cy_().func_177982_a(i, 0, i);
/*  74 */           BlockPos pos2 = player.func_233580_cy_().func_177982_a(-i, 0, -i);
/*  75 */           if (player.field_70170_p.func_180495_p(pos1).func_185904_a().func_76220_a() && player.field_70170_p.func_180495_p(pos2).func_185904_a().func_76220_a()) {
/*     */             
/*  77 */             hasEmptySpace = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*  83 */       if (!hasEmptySpace) {
/*     */         
/*  85 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_SPACE, new Object[] { getName() }), Util.field_240973_b_);
/*  86 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  95 */     if (!checkZoanTransformation(player)) {
/*  96 */       return false;
/*     */     }
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startContinuity(PlayerEntity player) {
/* 105 */     super.startContinuity(player);
/*     */     
/* 107 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 109 */     if (props.getZoanPoint().isEmpty()) {
/* 110 */       props.setZoanPoint("");
/*     */     }
/*     */     
/* 113 */     props.setZoanPoint(getTransformation().getForm());
/* 114 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.func_145782_y(), props));
/* 115 */     WyNetwork.sendToAll(new SSyncAbilityPacket(player.func_145782_y(), this));
/*     */ 
/*     */     
/* 118 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.Size((Entity)player, player.func_213283_Z(), player.func_213305_a(player.func_213283_Z()), player.func_213302_cg()));
/* 119 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.func_145782_y()), (Entity)player);
/* 120 */     player.func_213323_x_();
/*     */     
/* 122 */     player.func_184226_ay();
/* 123 */     ((ServerWorld)player.field_70170_p).func_72863_F().func_217216_a((Entity)player, (IPacket)new SSetPassengersPacket((Entity)player));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean beforeContinuityStopEvent(PlayerEntity player) {
/* 128 */     if (!checkZoanTransformation(player)) {
/* 129 */       return false;
/*     */     }
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopContinuity(PlayerEntity player) {
/* 137 */     super.stopContinuity(player);
/*     */     
/* 139 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 140 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 142 */     props.setZoanPoint("");
/*     */     
/* 144 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.func_145782_y(), props));
/* 145 */     WyNetwork.sendToAll(new SSyncAbilityPacket(player.func_145782_y(), this));
/*     */ 
/*     */     
/* 148 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.Size((Entity)player, player.func_213283_Z(), player.func_213305_a(player.func_213283_Z()), player.func_213302_cg()));
/* 149 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.func_145782_y()), (Entity)player);
/* 150 */     player.func_213323_x_();
/*     */     
/* 152 */     player.func_184226_ay();
/* 153 */     ((ServerWorld)player.field_70170_p).func_72863_F().func_217216_a((Entity)player, (IPacket)new SSetPassengersPacket((Entity)player));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 159 */     return isContinuous();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\MorphAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */