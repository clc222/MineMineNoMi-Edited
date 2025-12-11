/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.pathfinding.PathNavigator;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AvoidBlockTagGoal
/*     */   extends Goal {
/*     */   private CreatureEntity entity;
/*  23 */   private Set<BlockPos> cachedPositions = new HashSet<>(); private Vector3d toAvoid; private final ITag.INamedTag<Block> tagToAvoid;
/*  24 */   private int radius = 15;
/*     */   private long lastCheck;
/*     */   private Path path;
/*     */   private final PathNavigator pathNav;
/*  28 */   private double speed = 2.0D;
/*  29 */   private double sprintSpeed = 4.0D;
/*     */   
/*     */   public AvoidBlockTagGoal(CreatureEntity entity, ITag.INamedTag<Block> toAvoid) {
/*  32 */     this.entity = entity;
/*  33 */     this.tagToAvoid = toAvoid;
/*  34 */     this.pathNav = entity.func_70661_as();
/*     */   }
/*     */   
/*     */   public void setSpeed(double speed) {
/*  38 */     this.speed = speed;
/*     */   }
/*     */   
/*     */   public void setSprintSpeed(double speed) {
/*  42 */     this.sprintSpeed = speed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  47 */     if (this.entity == null || !this.entity.func_70089_S()) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     long now = System.currentTimeMillis();
/*  52 */     if (now - this.lastCheck < 1000L) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     if (this.cachedPositions.size() > 0) {
/*  57 */       Iterator<BlockPos> positions = this.cachedPositions.iterator();
/*  58 */       while (positions.hasNext()) {
/*  59 */         BlockPos pos = positions.next();
/*     */         
/*  61 */         boolean isSameBlock = this.entity.field_70170_p.func_180495_p(pos).func_235714_a_((ITag)this.tagToAvoid);
/*  62 */         if (isSameBlock && this.entity.func_70092_e(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()) < (this.radius * this.radius)) {
/*  63 */           this.toAvoid = RandomPositionGenerator.func_75461_b(this.entity, 128, 64, new Vector3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()));
/*  64 */           if (this.toAvoid == null) {
/*  65 */             return false;
/*     */           }
/*     */           
/*  68 */           this.path = this.pathNav.func_225466_a(this.toAvoid.field_72450_a, this.toAvoid.field_72448_b, this.toAvoid.field_72449_c, 0);
/*  69 */           return (this.path != null);
/*     */         } 
/*     */         
/*  72 */         if (!isSameBlock) {
/*  73 */           positions.remove();
/*     */         }
/*     */       } 
/*  76 */       return false;
/*     */     } 
/*     */     
/*  79 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks(this.entity.func_233580_cy_(), (IWorld)this.entity.field_70170_p, this.radius, this.radius, this.radius, b -> b.func_235714_a_((ITag)this.tagToAvoid));
/*  80 */     this.lastCheck = System.currentTimeMillis();
/*  81 */     this.cachedPositions.clear();
/*  82 */     this.cachedPositions.addAll(blocks);
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  88 */     return !this.pathNav.func_75500_f();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  93 */     this.pathNav.func_75484_a(this.path, this.speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  98 */     this.toAvoid = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 103 */     if (this.entity.func_195048_a(this.toAvoid) < 49.0D) {
/* 104 */       this.entity.func_70661_as().func_75489_a(this.sprintSpeed);
/*     */     } else {
/*     */       
/* 107 */       this.entity.func_70661_as().func_75489_a(this.speed);
/*     */     } 
/* 109 */     this.pathNav.func_75484_a(this.path, this.speed);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\AvoidBlockTagGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */