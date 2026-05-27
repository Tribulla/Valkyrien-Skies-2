package org.valkyrienskies.mod.common.mob_spawning;

import net.minecraft.core.BlockPos;
import org.joml.Vector3d;
import org.valkyrienskies.core.api.ships.Ship;

public final class ShipFinalizeBiome {

    private ShipFinalizeBiome() {
    }

    private static final ThreadLocal<Vector3d> TL_SCRATCH = ThreadLocal.withInitial(Vector3d::new);

    public static BlockPos project(final BlockPos pos) {
        if (!ShipSpawnFinalizeContext.hasAnyActive()) {
            return pos;
        }
        final Ship ship = ShipSpawnFinalizeContext.current();
        if (ship == null) {
            return pos;
        }
        final Vector3d world = ship.getTransform().getShipToWorld().transformPosition(
            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, TL_SCRATCH.get()
        );
        return BlockPos.containing(world.x, world.y, world.z);
    }
}
