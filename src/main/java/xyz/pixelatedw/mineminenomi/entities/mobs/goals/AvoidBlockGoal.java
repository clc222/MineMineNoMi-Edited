/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.pathfinding.PathNavigator;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AvoidBlockGoal
/*     */   extends Goal {
/*     */   private CreatureEntity entity;
/*  21 */   private Set<BlockPos> cachedPositions = new HashSet<>(); private Vector3d toAvoid; private final Set<Block> blocksToAvoid;
/*  22 */   private int radius = 15;
/*     */   private long lastCheck;
/*     */   private Path path;
/*     */   private final PathNavigator pathNav;
/*  26 */   private double speed = 2.0D;
/*  27 */   private double sprintSpeed = 4.0D;
/*     */   
/*     */   public AvoidBlockGoal(CreatureEntity entity, Set<Block> toAvoid) {
/*  30 */     this.entity = entity;
/*  31 */     this.blocksToAvoid = toAvoid;
/*  32 */     this.pathNav = entity.func_70661_as();
/*     */   }
/*     */   
/*     */   public void setSpeed(double speed) {
/*  36 */     this.speed = speed;
/*     */   }
/*     */   
/*     */   public void setSprintSpeed(double speed) {
/*  40 */     this.sprintSpeed = speed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  45 */     if (this.entity == null || !this.entity.func_70089_S()) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     if (this.entity.func_70638_az() == null) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     long now = System.currentTimeMillis();
/*  54 */     if (now - this.lastCheck < 1000L) {
/*  55 */       return false;
/*     */     }
/*     */     
/*  58 */     if (this.cachedPositions.size() > 0) {
/*  59 */       for (BlockPos pos : this.cachedPositions) {
/*  60 */         boolean isSameBlock = this.blocksToAvoid.contains(this.entity.field_70170_p.func_180495_p(pos).func_177230_c());
/*  61 */         if (isSameBlock && this.entity.func_70092_e(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()) < (this.radius * this.radius)) {
/*  62 */           this.toAvoid = RandomPositionGenerator.func_75461_b(this.entity, 128, 64, new Vector3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()));
/*  63 */           if (this.toAvoid == null) {
/*  64 */             return false;
/*     */           }
/*     */           
/*  67 */           this.path = this.pathNav.func_225466_a(this.toAvoid.field_72450_a, this.toAvoid.field_72448_b, this.toAvoid.field_72449_c, 0);
/*  68 */           return (this.path != null);
/*     */         } 
/*     */         
/*  71 */         if (!isSameBlock) {
/*  72 */           this.cachedPositions.remove(pos);
/*     */         }
/*     */       } 
/*  75 */       return false;
/*     */     } 
/*     */     
/*  78 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks(this.entity.func_233580_cy_(), (IWorld)this.entity.field_70170_p, this.radius, this.radius, this.radius, b -> this.blocksToAvoid.contains(b.func_177230_c()));
/*  79 */     this.lastCheck = System.currentTimeMillis();
/*  80 */     this.cachedPositions.clear();
/*  81 */     this.cachedPositions.addAll(blocks);
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  87 */     return !this.pathNav.func_75500_f();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  92 */     this.pathNav.func_75484_a(this.path, this.speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  97 */     this.toAvoid = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 102 */     if (this.entity.func_195048_a(this.toAvoid) < 49.0D) {
/* 103 */       this.entity.func_70661_as().func_75489_a(this.sprintSpeed);
/*     */     } else {
/*     */       
/* 106 */       this.entity.func_70661_as().func_75489_a(this.speed);
/*     */     } 
/* 108 */     this.pathNav.func_75484_a(this.path, this.speed);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\AvoidBlockGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */