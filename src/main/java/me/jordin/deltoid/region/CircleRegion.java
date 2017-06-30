package me.jordin.deltoid.region;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.utils.UnionUtilities;
import me.jordin.deltoid.vector.Vec2;

import java.util.List;

/**
 * Created by Jordin on 6/29/2017.
 * Jordin is still best hacker.
 */
public class CircleRegion implements Region<CircleRegion, Vec2> {
    /**
     * A <b>CircleRegion</b> with coordinates <b>[0, 0]</b> and radius <b>r = 0.0</b>.
     */
    public static final CircleRegion ORIGIN = new CircleRegion(Vec2.ORIGIN, 0);

    private static Vec2 CENTRE = new Vec2(0.5, 0.5);

    private static double HALF_TAU = Math.PI;

    /**
     * The location of the centre of this <b>CircleRegion</b>.
     *
     * @serial
     */
    @SerializedName("centre")
    public final Vec2 centre;

    /**
     * The radius of this <b>CircleRegion</b>.
     *
     * @serial
     */
    @SerializedName("radius")
    public final double radius;

    /**
     * The total surface area of this <b>CircleRegion</b>.
     *
     * @serial
     */
    @SerializedName("area")
    private double surfaceArea;

    public CircleRegion(Vec2 centre, double radius) {
        this.centre = centre;
        this.radius = radius;

        this.surfaceArea = HALF_TAU * radius * radius;
    }

    /**
     * Returns the total volume enclosed in this <b>CircleRegion</b>.
     *
     * @return <b>0.0</b>, Circles do not have a volume!
     */
    @Override
    public double volume() {
        return 0.0;
    }

    /**
     * Returns the total area of this <b>CircleRegion</b>.
     *
     * @return the total area of this <b>CircleRegion</b>
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the <b>CircleRegion</b> has
     * a non-zero area, {@code false} otherwise.
     *
     * @return {@code true} if the <b>CircleRegion</b> exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.radius != 0;
    }

    /**
     * Returns {@code true} if the <b>CircleRegion</b>
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the <b>Vec2</b> to consider.
     * @return {@code true} if the <b>CircleRegion</b> contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec2 location) {
        return this.centre.subtract(location.add(CENTRE)).length() <= this.radius;
    }

    /**
     * Returns a <b>List</b> of all of the <b>Vec2</b>s enclosed in this <b>CircleRegion</b>.
     *
     * @return the <b>List</b> of the <b>Vec2</b>s
     */
    @Override
    public List<Vec2> enclosedPoints() {
        Vec2 bounds = new Vec2(radius, radius);
        return UnionUtilities.overlap(new RectangleRegion(this.centre.add(bounds), this.centre.subtract(bounds)), this);
    }

    /**
     * Creates the smallest possible <b>CircleRegion</b>
     * that fully encloses this <b>CircleRegion</b> and <b>region</b>.
     *
     * @param region the other <b>CircleRegion</b> to be considered
     * @return the <b>CircleRegion</b>
     */
    @Override
    public CircleRegion union(CircleRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new <b>CircleRegion</b> translated by <b>offset</b>.
     *
     * @return the new <b>CircleRegion</b>
     */
    @Override
    public CircleRegion offset(Vec2 offset) {
        return new CircleRegion(centre.add(offset), radius);
    }
}
