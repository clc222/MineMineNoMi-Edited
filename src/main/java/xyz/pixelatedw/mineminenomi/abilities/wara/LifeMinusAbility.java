/*     */ package xyz.pixelatedw.mineminenomi.abilities.wara;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class LifeMinusAbility extends PassiveAbility2 {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "life_minus", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Transfers the damage taken to a Straw Doll in your inventory.", null)
/*     */       });
/*  35 */   public static final AbilityCore<LifeMinusAbility> INSTANCE = (new AbilityCore.Builder("Life Minus", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, LifeMinusAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .build();
/*     */   
/*  39 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  41 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*     */   
/*     */   public LifeMinusAbility(AbilityCore<LifeMinusAbility> ability) {
/*  44 */     super(ability);
/*     */     
/*  46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.damageTakenComponent });
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  50 */     if (canUse(entity).isFail()) {
/*  51 */       return damage;
/*     */     }
/*     */     
/*  54 */     if (!(entity instanceof PlayerEntity)) {
/*  55 */       return damage;
/*     */     }
/*     */     
/*  58 */     boolean canUseDoll = (!entity.func_70644_a((Effect)ModEffects.ABILITY_OFF.get()) && !AbilityHelper.isWeakenedByKairosekiOrWater(entity));
/*     */     
/*  60 */     if (!canUseDoll) {
/*  61 */       return damage;
/*     */     }
/*     */     
/*  64 */     PlayerEntity attacked = (PlayerEntity)entity;
/*     */     
/*  66 */     for (int i = 0; i < attacked.field_71071_by.field_70462_a.size(); i++) {
/*  67 */       ItemStack stack = attacked.field_71071_by.func_70301_a(i);
/*     */       
/*  69 */       if (stack.func_77973_b() == ModItems.STRAW_DOLL.get()) {
/*  70 */         Pair<UUID, LivingEntity> strawDollOwner = SoulboundItemHelper.getOwner(attacked.field_70170_p, stack);
/*     */         
/*  72 */         StrawDollItem.alterStrawDollHealth(stack, -damage);
/*     */         
/*  74 */         if (attacked == strawDollOwner.getValue()) {
/*  75 */           return damage;
/*     */         }
/*     */         
/*  78 */         if (strawDollOwner.getValue() != null) {
/*     */ 
/*     */ 
/*     */           
/*  82 */           ModDamageSource modSource = (ModDamageSource)ModDamageSource.causeAbilityDamage(entity, getCore()).setInternal().setBypassFriendlyDamage().func_151518_m();
/*     */           
/*  84 */           boolean attack = this.dealDamageComponent.hurtTarget(entity, (LivingEntity)strawDollOwner.getValue(), damage, (DamageSource)modSource);
/*     */           
/*  86 */           if ((attack && ((LivingEntity)strawDollOwner.getValue()).func_110143_aJ() <= 0.0F) || StrawDollItem.getStrawDollHealth(stack) <= 0.0F) {
/*  87 */             spawnParticles((ServerWorld)attacked.field_70170_p, attacked.func_226277_ct_(), attacked.func_226278_cu_(), attacked.func_226281_cx_());
/*  88 */             spawnParticles((ServerWorld)((LivingEntity)strawDollOwner.getValue()).field_70170_p, ((LivingEntity)strawDollOwner.getValue()).func_226277_ct_(), ((LivingEntity)strawDollOwner.getValue()).func_226278_cu_(), ((LivingEntity)strawDollOwner.getValue()).func_226281_cx_());
/*     */             
/*  90 */             IEntityStats ownerProps = EntityStatsCapability.get((LivingEntity)strawDollOwner.getValue());
/*     */             
/*  92 */             ownerProps.setStrawDoll(true);
/*     */             
/*  94 */             attacked.field_71071_by.func_184437_d(stack);
/*     */           } 
/*     */           
/*  97 */           return 0.0F;
/*     */         } 
/*     */       } 
/*     */     } 
/* 101 */     return damage;
/*     */   }
/*     */   
/*     */   private void spawnParticles(ServerWorld world, double posX, double posY, double posZ) {
/* 105 */     for (int i = 0; i < 5; i++) {
/* 106 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 107 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 108 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/* 109 */       world.func_195598_a((IParticleData)ParticleTypes.field_197616_i, posX + offsetX, posY + offsetY, posZ + offsetZ, 25, 0.0D, 0.0D, 0.0D, 0.1D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\wara\LifeMinusAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */