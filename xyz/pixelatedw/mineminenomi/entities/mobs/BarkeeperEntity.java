/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*    */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*    */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*    */ import net.minecraft.entity.ai.goal.SwimGoal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RandomWalkingAroundHomeGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenDialogueScreenPacket;
/*    */ 
/*    */ public class BarkeeperEntity extends OPEntity {
/*    */   public BarkeeperEntity(EntityType type, World world) {
/* 26 */     super(type, world, MobsHelper.BARKEEPER_TEXTURES);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_184651_r() {
/* 31 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 32 */     props.setFaction(ModValues.CIVILIAN);
/*    */     
/* 34 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/* 35 */     this.field_70714_bg.func_75776_a(2, (Goal)new OpenDoorGoal((MobEntity)this, false));
/* 36 */     this.field_70714_bg.func_75776_a(3, (Goal)(new RandomWalkingAroundHomeGoal(this, 0.5D)).setWalkDistance(2).setWalkOffset(2));
/* 37 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 38 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*    */     
/* 40 */     this.field_70715_bh.func_75776_a(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*    */   }
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 44 */     return OPEntity.createAttributes()
/* 45 */       .func_233815_a_(Attributes.field_233819_b_, 10.0D)
/* 46 */       .func_233815_a_(Attributes.field_233821_d_, 0.23D)
/* 47 */       .func_233815_a_(Attributes.field_233823_f_, 1.0D)
/* 48 */       .func_233815_a_(Attributes.field_233818_a_, 20.0D)
/* 49 */       .func_233815_a_(Attributes.field_233826_i_, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 54 */     if (hand != Hand.MAIN_HAND) {
/* 55 */       return ActionResultType.FAIL;
/*    */     }
/*    */     
/* 58 */     if (!player.field_70170_p.field_72995_K) {
/* 59 */       WyNetwork.sendTo(new SOpenDialogueScreenPacket((LivingEntity)this, (Interaction)ModInteractions.BARKEEPER.get()), player);
/* 60 */       return ActionResultType.PASS;
/*    */     } 
/*    */     
/* 63 */     return ActionResultType.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\BarkeeperEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */