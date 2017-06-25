package me.jordin.deltoid.utils;

import me.jordin.deltoid.region.CuboidRegion;
import me.jordin.deltoid.region.Region;
import me.jordin.deltoid.region.SphereRegion;
import me.jordin.deltoid.vector.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jordin on 6/21/2017.
 * Jordin is still best hacker.
 */
public class UnionUtilities {

    public static <R1, R2, V> List<V> overlap(Region<R1, V> region1, Region<R2, V> region2) {
        // TODO: comment about collect
        List<V> locations = new ArrayList<>();

        region1.enclosedPoints().stream()
                .filter(region2::contains)
                .forEach(locations::add);

        return locations;
    }

    public static <R1, R2, V> List<V> overlapCollect(Region<R1, V> region1, Region<R2, V> region2) {
        return region1.enclosedPoints().stream()
                .filter(region2::contains)
                .collect(Collectors.toList());
    }

    /**
     * Creates the smallest possible <b>CuboidRegion</b>
     * that fully encloses <b>cube1</b> and <b>cube2</b>.
     *
     * @param cube1 the first <b>CuboidRegion</b> to be considered
     * @param cube2 the second <b>CuboidRegion</b> to be considered
     * @return the <b>CuboidRegion</b>
     */
    public static CuboidRegion union(CuboidRegion cube1, CuboidRegion cube2) {
        double minX = Math.min(cube1.min.x, cube2.min.x);
        double minY = Math.min(cube1.min.y, cube2.min.y);
        double minZ = Math.min(cube1.min.z, cube2.min.z);

        double maxX = Math.min(cube1.max.x, cube2.max.x);
        double maxY = Math.min(cube1.max.y, cube2.max.y);
        double maxZ = Math.min(cube1.max.z, cube2.max.z);

        return new CuboidRegion(new Vec3(minX, minY, minZ), new Vec3(maxX, maxY, maxZ));
    }

    /**
     * Creates the smallest possible <b>SphereRegion</b>
     * that fully encloses <b>sphere1</b> and <b>sphere2</b>.
     *
     * @param sphere1 the first <b>SphereRegion</b> to be considered
     * @param sphere2 the second <b>SphereRegion</b> to be considered
     * @return the <b>SphereRegion</b>
     */
    public static SphereRegion union(SphereRegion sphere1, SphereRegion sphere2) {
        Vec3 deltaCentre = sphere2.centre.subtract(sphere1.centre);

        double radius = (deltaCentre.length() + sphere1.radius + sphere2.radius) / 2.0;

        Vec3 centre = sphere1.centre.add(deltaCentre.normalize(radius - sphere1.radius));

        return new SphereRegion(centre, radius);
    }
}
