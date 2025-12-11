/*     */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class SpringHopperAbility extends Ability {
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spring_hopper", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("By turning the entity's legs into springs, they can jump around with great ease bouncing around surfaces", null)
/*     */       });
/*  36 */   private static final UUID SPRING_POWER_UUID = UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b");
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   
/*  40 */   public static final AbilityCore<SpringHopperAbility> INSTANCE = (new AbilityCore.Builder("Spring Hopper", AbilityCategory.DEVIL_FRUITS, SpringHopperAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip()
/*  43 */       }).build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true))
/*  46 */     .addStartEvent(this::onContinuityStart)
/*  47 */     .addTickEvent(this::onContinuityTick)
/*  48 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*  50 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*     */   
/*  52 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*  54 */   private int jumpPower = 0;
/*     */   
/*     */   private boolean canIncreaseJumpPower = false;
/*     */   
/*     */   private boolean startedFalling = false;
/*     */   
/*     */   public SpringHopperAbility(AbilityCore<SpringHopperAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*     */     
/*  65 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.morphComponent, (AbilityComponent)this.changeStatsComponent });
/*     */     
/*  67 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  69 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  73 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   public void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  77 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.SPRING_LEGS.get());
/*     */   }
/*     */   
/*     */   public void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  81 */     if (canUse(entity).isFail()) {
/*  82 */       this.jumpPower = 0;
/*     */       
/*  84 */       this.continuousComponent.stopContinuity(entity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (entity.field_70123_F && this.jumpPower > 2) {
/* 102 */       Vector3d speed = entity.func_70040_Z().func_216372_d(-2.0D, -2.0D, -2.0D);
/*     */       
/* 104 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/*     */       
/* 106 */       if (this.jumpPower < 9) {
/* 107 */         this.jumpPower++;
/*     */       }
/*     */       
/* 110 */       if (entity instanceof PlayerEntity) {
/* 111 */         entity.field_70170_p.func_184133_a((PlayerEntity)entity, entity.func_233580_cy_(), (SoundEvent)ModSounds.SPRING_SFX.get(), SoundCategory.PLAYERS, 1.0F, (float)MathHelper.func_151237_a(entity.func_70681_au().nextDouble() + 0.30000001192092896D, 0.5D, 1.5D));
/*     */       }
/*     */     } 
/*     */     
/* 115 */     if (entity.func_233570_aj_()) {
/* 116 */       this.startedFalling = true;
/*     */       
/* 118 */       if (AbilityHelper.isJumping(entity)) {
/* 119 */         if (this.jumpPower > 3) {
/* 120 */           Vector3d speed = WyHelper.propulsion(entity, 0.25D + this.jumpPower * 0.25D, 0.25D + this.jumpPower * 0.25D);
/*     */           
/* 122 */           AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, (entity.func_213322_ci()).field_72448_b, speed.field_72449_c);
/*     */         } 
/*     */         
/* 125 */         if (this.jumpPower < 9 && this.canIncreaseJumpPower) {
/* 126 */           this.jumpPower++;
/*     */         }
/*     */         
/* 129 */         if (entity.func_110148_a((Attribute)ModAttributes.JUMP_HEIGHT.get()).func_111126_e() != this.jumpPower * 1.5D) {
/* 130 */           this.changeStatsComponent.removeModifiers(entity);
/*     */           
/* 132 */           this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, getJumpHeight(this.jumpPower), e -> this.continuousComponent.isContinuous());
/*     */         } 
/*     */         
/* 135 */         if (entity instanceof PlayerEntity) {
/* 136 */           entity.field_70170_p.func_184133_a((PlayerEntity)entity, entity.func_233580_cy_(), (SoundEvent)ModSounds.SPRING_SFX.get(), SoundCategory.PLAYERS, 0.3F, (float)MathHelper.func_151237_a(entity.func_70681_au().nextDouble() + 0.30000001192092896D, 0.800000011920929D, 1.5D));
/*     */         }
/*     */         
/* 139 */         this.canIncreaseJumpPower = true;
/*     */       } else {
/* 141 */         this.jumpPower = 0;
/*     */       } 
/*     */     } else {
/* 144 */       if (entity.field_70124_G) {
/* 145 */         this.jumpPower--;
/*     */       }
/*     */       
/* 148 */       if (0.0D > (entity.func_213322_ci()).field_72448_b) {
/* 149 */         if (this.startedFalling) {
/* 150 */           this.canIncreaseJumpPower = (DevilFruitHelper.getDifferenceToFloor((Entity)entity) > this.jumpPower);
/*     */           
/* 152 */           this.startedFalling = false;
/*     */         } 
/*     */         
/* 155 */         if (this.jumpPower > 3) {
/* 156 */           AbilityHelper.setDeltaMovement((Entity)entity, entity.func_213322_ci().func_186678_a(1.15D));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 163 */     this.morphComponent.stopMorph(entity);
/*     */     
/* 165 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public AttributeModifier getJumpHeight(int jumpPower) {
/* 169 */     return (AttributeModifier)new AbilityAttributeModifier(SPRING_POWER_UUID, INSTANCE, "Spring Movement Modifier", jumpPower * 1.5D, AttributeModifier.Operation.MULTIPLY_BASE);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringHopperAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */