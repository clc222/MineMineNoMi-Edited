package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({FlowingFluid.class})
public class FlowingFluidMixin {
  @Inject(method = {"spreadTo"}, at = {@At("HEAD")})
  public void spreadTo(IWorld level, BlockPos pos, BlockState state, Direction dir, FluidState fluidState, CallbackInfo ci) {}
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\FlowingFluidMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */