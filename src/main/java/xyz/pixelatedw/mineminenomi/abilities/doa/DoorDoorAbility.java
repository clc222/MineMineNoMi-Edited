/*    */ package xyz.pixelatedw.mineminenomi.abilities.doa;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DoorDoorAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "door_door", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("By making a door, the user transports to the other side of any surface.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 31 */   public static final AbilityCore<DoorDoorAbility> INSTANCE = (new AbilityCore.Builder("Door Door", AbilityCategory.DEVIL_FRUITS, DoorDoorAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 34 */       }).build();
/*    */   
/*    */   public DoorDoorAbility(AbilityCore<DoorDoorAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/*    */     
/* 41 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 45 */     BlockRayTraceResult hitBlock = WyHelper.rayTraceBlocks((Entity)entity, 16.0D);
/*    */     
/* 47 */     if (Math.sqrt(entity.func_195048_a(hitBlock.func_216347_e())) > 2.5D) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     Vector3d look = entity.func_70040_Z().func_72432_b();
/* 52 */     Vector3d vec = entity.func_213303_ch();
/* 53 */     BlockPos.Mutable pos = new BlockPos.Mutable();
/*    */     
/* 55 */     pos.func_189532_c(vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
/*    */     
/* 57 */     boolean firstSolid = false;
/* 58 */     int airBlocks = 0;
/*    */     
/* 60 */     for (int i = 0; i < 40; i++) {
/* 61 */       BlockState state = entity.field_70170_p.func_180495_p((BlockPos)pos);
/*    */       
/* 63 */       if (state.func_177230_c() == Blocks.field_150350_a && (firstSolid || airBlocks > 1)) {
/* 64 */         entity.func_233576_c_(vec.func_72441_c(0.0D, 1.0D, 0.0D));
/*    */         
/*    */         break;
/*    */       } 
/*    */       
/* 69 */       vec = vec.func_72441_c(look.field_72450_a, look.field_72448_b, look.field_72449_c);
/* 70 */       pos.func_181079_c(MathHelper.func_76128_c(vec.field_72450_a), MathHelper.func_76143_f(vec.field_72448_b), MathHelper.func_76128_c(vec.field_72449_c));
/*    */       
/* 72 */       state = entity.field_70170_p.func_180495_p((BlockPos)pos);
/*    */       
/* 74 */       if (state.func_185904_a().func_76220_a()) {
/* 75 */         firstSolid = true;
/*    */       }
/*    */       
/* 78 */       if (state.func_177230_c() == Blocks.field_150350_a) {
/* 79 */         airBlocks++;
/*    */       }
/*    */     } 
/*    */     
/* 83 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DOA_IN_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 85 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doa\DoorDoorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */