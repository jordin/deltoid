package me.jordin.deltoid.region;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.utils.UnionUtilities;
import me.jordin.deltoid.vector.Vec3;

import java.util.List;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public class SphereRegion implements Region<SphereRegion, Vec3> {
    /**
     * A <b>SphereRegion</b> with coordinates <b>[0, 0, 0]</b> and radius <b>r = 0.0</b>.
     */
    public static final SphereRegion ORIGIN = new SphereRegion(Vec3.ORIGIN, 0);

    private static Vec3 CENTRE = new Vec3(0.5, 0.5, 0.5);

    private static double TWO_TAU = 4.0 * Math.PI;
    private static double TWO_OVER_THREE_TIMES_TAU = 4.0 / 3.0 * Math.PI;

    /**
     * The location of the centre of this <b>SphereRegion</b>.
     *
     * @serial
     */
    @SerializedName("centre")
    public final Vec3 centre;

    /**
     * The radius of this <b>SphereRegion</b>.
     *
     * @serial
     */
    @SerializedName("radius")
    public final double radius;

    /**
     * The total volume enclosed in this <b>SphereRegion</b>.
     *
     * @serial
     */
    @SerializedName("volume")
    private double volume;

    /**
     * The total surface area of this <b>SphereRegion</b>.
     *
     * @serial
     */
    @SerializedName("surface_area")
    private double surfaceArea;

    public SphereRegion(Vec3 centre, double radius) {
        this.centre = centre;
        this.radius = radius;

        this.volume = TWO_OVER_THREE_TIMES_TAU * radius * radius * radius;
        this.surfaceArea = TWO_TAU * radius * radius;
    }

    /**
     * Returns the total volume enclosed in this <b>SphereRegion</b>.
     *
     * @return the total volume of this <b>SphereRegion</b>
     */
    @Override
    public double volume() {
        return this.volume;
    }

    /**
     * Returns the total surface area of this <b>SphereRegion</b>.
     *
     * @return the total surface area of this <b>SphereRegion</b>
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the <b>SphereRegion</b> has
     * a non-zero volume, {@code false} otherwise.
     *
     * @return {@code true} if the <b>SphereRegion</b> exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.radius != 0;
    }

    /**
     * Returns {@code true} if the <b>SphereRegion</b>
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the <b>Vec3</b> to consider.
     * @return {@code true} if the <b>SphereRegion</b> contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec3 location) {
        return this.centre.subtract(location.add(CENTRE)).length() <= this.radius;
    }

    /**
     * Returns a <b>List</b> of all of the <b>Vec3</b>s enclosed in this <b>SphereRegion</b>.
     *
     * @return the <b>List</b> of the <b>Vec3</b>s
     */
    @Override
    public List<Vec3> enclosedPoints() {
        Vec3 bounds = new Vec3(radius, radius, radius);
        return UnionUtilities.overlap(new CuboidRegion(this.centre.add(bounds), this.centre.subtract(bounds)), this);
    }

    /**
     * Creates the smallest possible <b>SphereRegion</b>
     * that fully encloses this <b>SphereRegion</b> and <b>region</b>.
     *
     * @param region the other <b>SphereRegion</b> to be considered
     * @return the <b>SphereRegion</b>
     */
    @Override
    public SphereRegion union(SphereRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new <b>SphereRegion</b> translated by <b>offset</b>.
     *
     * @return the new <b>SphereRegion</b>
     */
    @Override
    public SphereRegion offset(Vec3 offset) {
        return new SphereRegion(centre.add(offset), radius);
    }
}
