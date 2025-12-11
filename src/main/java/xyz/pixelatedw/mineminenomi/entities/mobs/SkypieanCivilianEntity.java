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
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ 
/*    */ public class SkypieanCivilianEntity extends OPEntity {
/*    */   public SkypieanCivilianEntity(EntityType type, World world) {
/* 21 */     super(type, world, MobsHelper.SKYPEAN_TEXTURES);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_184651_r() {
/* 26 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 27 */     props.setFaction(ModValues.CIVILIAN);
/*    */     
/* 29 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/* 30 */     this.field_70714_bg.func_75776_a(2, (Goal)new OpenDoorGoal((MobEntity)this, false));
/* 31 */     this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 32 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 33 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*    */     
/* 35 */     this.field_70715_bh.func_75776_a(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*    */   }
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 39 */     return OPEntity.createAttributes()
/* 40 */       .func_233815_a_(Attributes.field_233819_b_, 10.0D)
/* 41 */       .func_233815_a_(Attributes.field_233821_d_, 0.23D)
/* 42 */       .func_233815_a_(Attributes.field_233823_f_, 1.0D)
/* 43 */       .func_233815_a_(Attributes.field_233818_a_, 20.0D)
/* 44 */       .func_233815_a_(Attributes.field_233826_i_, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\SkypieanCivilianEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */