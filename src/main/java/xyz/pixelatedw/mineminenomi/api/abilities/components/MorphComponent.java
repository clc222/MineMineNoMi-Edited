/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SSetPassengersPacket;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SChangeMorphPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class MorphComponent extends AbilityComponent<IAbility> {
/*     */   private MorphInfo morphInfo;
/*     */   
/*     */   public MorphComponent(IAbility ability) {
/*  24 */     super(ModAbilityKeys.MORPH, ability);
/*     */   }
/*     */   private boolean isMorphed;
/*     */   public void startMorph(LivingEntity entity, MorphInfo info) {
/*  28 */     ensureIsRegistered();
/*     */     
/*  30 */     this.morphInfo = info;
/*     */     
/*  32 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*     */     
/*  34 */     props.addMorph(info);
/*  35 */     this.isMorphed = true;
/*     */     
/*  37 */     updateMorphSize(entity);
/*     */   }
/*     */   
/*     */   public void updateMorphSize(LivingEntity entity) {
/*  41 */     if (this.morphInfo == null) {
/*     */       return;
/*     */     }
/*     */     
/*  45 */     this.morphInfo.updateMorphSize(entity);
/*     */     
/*  47 */     if (!entity.field_70170_p.field_72995_K) {
/*  48 */       WyNetwork.sendToAllTrackingAndSelf(new SChangeMorphPacket(entity, getAbility(), this.morphInfo, this.isMorphed ? this.morphInfo.getForm() : ""), (Entity)entity);
/*  49 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SSetPassengersPacket((Entity)entity));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stopMorph(LivingEntity entity) {
/*  54 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*     */     
/*  56 */     if (getMorphInfo() == null) {
/*     */       return;
/*     */     }
/*     */     
/*  60 */     props.removeMorph(this.morphInfo);
/*  61 */     this.isMorphed = false;
/*     */     
/*  63 */     updateMorphSize(entity);
/*  64 */     this.morphInfo = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MorphInfo getMorphInfo() {
/*  72 */     return this.morphInfo;
/*     */   }
/*     */   
/*     */   public boolean isMorphed() {
/*  76 */     return this.isMorphed;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/*  82 */     CompoundNBT nbt = super.save();
/*  83 */     if (this.morphInfo != null) {
/*  84 */       ResourceLocation morphId = this.morphInfo.getRegistryName();
/*  85 */       if (morphId != null) {
/*  86 */         nbt.func_74778_a("morph", morphId.toString());
/*     */       }
/*     */     } 
/*  89 */     nbt.func_74757_a("isMorphed", this.isMorphed);
/*  90 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/*  95 */     super.load(nbt);
/*  96 */     if (nbt.func_74764_b("morph")) {
/*  97 */       ResourceLocation morphId = new ResourceLocation(nbt.func_74779_i("morph"));
/*  98 */       this.morphInfo = (MorphInfo)ModRegistries.MORPHS.getValue(morphId);
/*     */     } 
/* 100 */     this.isMorphed = nbt.func_74767_n("isMorphed");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\MorphComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */