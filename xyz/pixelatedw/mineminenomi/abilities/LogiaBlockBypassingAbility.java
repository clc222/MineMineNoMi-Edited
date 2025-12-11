/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerAbilities;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public abstract class LogiaBlockBypassingAbility extends PassiveAbility2 {
/* 16 */   public static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "logia_block_bypassing", new Pair[] {
/* 17 */         (Pair)ImmutablePair.of("Allows the user to move through specific blocks based on their element", null)
/*    */       });
/*    */   private boolean isInBlock = false;
/*    */   
/*    */   public LogiaBlockBypassingAbility(AbilityCore<? extends LogiaBlockBypassingAbility> core) {
/* 22 */     super(core);
/*    */     
/* 24 */     addDuringPassiveEvent(this::duringPassive);
/* 25 */     this.pauseTickComponent.addPauseEvent(110, this::onPauseEvent);
/*    */   }
/*    */   
/*    */   private void duringPassive(LivingEntity entity) {
/* 29 */     if (entity instanceof PlayerEntity && canGoThrough(entity.field_70170_p.func_180495_p(entity.func_233580_cy_()))) {
/* 30 */       PlayerAbilities abilities = ((PlayerEntity)entity).field_71075_bZ;
/* 31 */       if (!abilities.field_75100_b) {
/* 32 */         abilities.field_75100_b = true;
/* 33 */         ((PlayerEntity)entity).func_71016_p();
/*    */       } 
/* 35 */       this.isInBlock = true;
/* 36 */       spawnParticles(entity);
/* 37 */     } else if (entity instanceof PlayerEntity && this.isInBlock) {
/* 38 */       PlayerAbilities abilities = ((PlayerEntity)entity).field_71075_bZ;
/* 39 */       abilities.field_75100_b = false;
/* 40 */       ((PlayerEntity)entity).func_71016_p();
/* 41 */       this.isInBlock = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onPauseEvent(LivingEntity entity, IAbility ability) {
/* 46 */     if (this.isInBlock) {
/* 47 */       PlayerAbilities abilities = ((PlayerEntity)entity).field_71075_bZ;
/* 48 */       abilities.field_75100_b = false;
/* 49 */       ((PlayerEntity)entity).func_71016_p();
/* 50 */       this.isInBlock = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract void spawnParticles(LivingEntity paramLivingEntity);
/*    */   
/*    */   public abstract boolean canGoThrough(BlockState paramBlockState);
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\LogiaBlockBypassingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */