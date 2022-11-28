package edebe.more_lens.common.helper;

import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import static net.minecraft.block.Block.makeCuboidShape;

public interface VoxelShapeHelper {
    static VoxelShape shape(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4, double z1, double z2, double z3, double z4,IBooleanFunction function) {
        return VoxelShapes.combineAndSimplify(makeCuboidShape(x1,y1,z1,x2,y2,z2),makeCuboidShape(x3,y3,z3,x4,y4,z4),function);
    }
}
