package in.jord.deltoid.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.jord.deltoid.utils.UnionUtilities;
import in.jord.deltoid.vector.Vec2;

import java.util.List;

public class CircleRegion implements Region<CircleRegion, Vec2> {
    /**
     * A {@link CircleRegion} with coordinates <b>[0, 0]</b> and radius <b>r = 0.0</b>.
     */
    public static final CircleRegion ORIGIN = new CircleRegion(Vec2.ORIGIN, 0);

    private static double HALF_TAU = Math.PI;

    /**
     * The location of the centre of this {@link CircleRegion}.
     *
     * @serial
     */
    @JsonProperty("centre")
    public final Vec2 centre;

    /**
     * The radius of this {@link CircleRegion}.
     *
     * @serial
     */
    @JsonProperty("radius")
    public final double radius;

    /**
     * The total surface area of this {@link CircleRegion}.
     *
     * @serial
     */
    @JsonProperty("area")
    private double surfaceArea;

    public CircleRegion(Vec2 centre, double radius) {
        this.centre = centre;
        this.radius = radius;

        this.surfaceArea = HALF_TAU * radius * radius;
    }

    /**
     * Returns the total volume enclosed in this {@link CircleRegion}.
     *
     * @return <b>0.0</b>, Circles do not have a volume!
     */
    @Override
    public double volume() {
        return 0.0;
    }

    /**
     * Returns the total area of this {@link CircleRegion}.
     *
     * @return the total area of this {@link CircleRegion}
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the {@link CircleRegion} has
     * a non-zero area, {@code false} otherwise.
     *
     * @return {@code true} if the {@link CircleRegion} exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.radius != 0;
    }

    /**
     * Returns {@code true} if the {@link CircleRegion}
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the {@link Vec2} to consider.
     * @return {@code true} if the {@link CircleRegion} contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec2 location) {
        return this.centre.subtract(location.add(Vec2.CENTRE)).length() <= this.radius;
    }

    /**
     * Returns a {@link List} of all of the {@link Vec2}s enclosed in this {@link CircleRegion}.
     *
     * @return the {@link List} of the {@link Vec2}s
     */
    @Override
    public List<Vec2> enclosedPoints() {
        Vec2 bounds = new Vec2(this.radius, this.radius);
        return UnionUtilities.overlap(new RectangleRegion(this.centre.add(bounds), this.centre.subtract(bounds)), this);
    }

    /**
     * Creates the smallest possible {@link CircleRegion}
     * that fully encloses this {@link CircleRegion} and <b>region</b>.
     *
     * @param region the other {@link CircleRegion} to be considered
     * @return the {@link CircleRegion}
     */
    @Override
    public CircleRegion union(CircleRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new {@link CircleRegion} translated by <b>offset</b>.
     *
     * @return the new {@link CircleRegion}
     */
    @Override
    public CircleRegion offset(Vec2 offset) {
        return new CircleRegion(this.centre.add(offset), this.radius);
    }
}
