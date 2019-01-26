package in.jord.deltoid.utils;

import in.jord.deltoid.region.*;
import in.jord.deltoid.vector.Vec2;
import in.jord.deltoid.vector.Vec3;

import java.util.ArrayList;
import java.util.List;

public class UnionUtilities {

    public static <R1, R2, V> List<V> overlap(Region<R1, V> region1, Region<R2, V> region2) {
        List<V> locations = new ArrayList<>();

        region1.enclosedPoints().stream()
                .filter(region2::contains)
                .forEach(locations::add);

        return locations;
    }

    /**
     * Creates the smallest possible {@link RectangleRegion}
     * that fully encloses <b>rect1</b> and <b>rect2</b>.
     *
     * @param rect1 the first {@link RectangleRegion} to be considered
     * @param rect2 the second {@link RectangleRegion} to be considered
     * @return the {@link RectangleRegion}
     */
    public static RectangleRegion union(RectangleRegion rect1, RectangleRegion rect2) {
        double minX = Math.min(rect1.min.x, rect2.min.x);
        double minY = Math.min(rect1.min.y, rect2.min.y);

        double maxX = Math.min(rect1.max.x, rect2.max.x);
        double maxY = Math.min(rect1.max.y, rect2.max.y);

        return new RectangleRegion(new Vec2(minX, minY), new Vec2(maxX, maxY));
    }

    /**
     * Creates the smallest possible {@link CircleRegion}
     * that fully encloses <b>circle1</b> and <b>circle2</b>.
     *
     * @param circle1 the first {@link SphereRegion} to be considered
     * @param circle2 the second {@link SphereRegion} to be considered
     * @return the {@link SphereRegion}
     */
    public static CircleRegion union(CircleRegion circle1, CircleRegion circle2) {
        Vec2 deltaCentre = circle2.centre.subtract(circle1.centre);

        double radius = (deltaCentre.length() + circle1.radius + circle2.radius) / 2.0;

        Vec2 centre = circle1.centre.add(deltaCentre.normalize(radius - circle2.radius));

        return new CircleRegion(centre, radius);
    }

    /**
     * Creates the smallest possible {@link CuboidRegion}
     * that fully encloses <b>cube1</b> and <b>cube2</b>.
     *
     * @param cube1 the first {@link CuboidRegion} to be considered
     * @param cube2 the second {@link CuboidRegion} to be considered
     * @return the {@link CuboidRegion}
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
     * Creates the smallest possible {@link SphereRegion}
     * that fully encloses <b>sphere1</b> and <b>sphere2</b>.
     *
     * @param sphere1 the first {@link SphereRegion} to be considered
     * @param sphere2 the second {@link SphereRegion} to be considered
     * @return the {@link SphereRegion}
     */
    public static SphereRegion union(SphereRegion sphere1, SphereRegion sphere2) {
        Vec3 deltaCentre = sphere2.centre.subtract(sphere1.centre);

        double radius = (deltaCentre.length() + sphere1.radius + sphere2.radius) / 2.0;

        Vec3 centre = sphere1.centre.add(deltaCentre.normalize(radius - sphere1.radius));

        return new SphereRegion(centre, radius);
    }
}
