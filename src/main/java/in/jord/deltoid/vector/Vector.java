package in.jord.deltoid.vector;

public interface Vector<V> {
    /**
     * Returns the magnitude of the {@link Vector}.
     *
     * @return the magnitude of the {@link Vector}
     */
    double length();

    /**
     * Returns the manhattan (taxicab) length of the {@link Vector}.
     *
     * @return the manhattan length of the {@link Vector}
     */
    double manhattan();

    /**
     * Breaks this {@link Vector} into its underlying components.
     *
     * @return the components of this {@link Vector}
     */
    double[] components();

    /**
     * Returns the unit {@link Vector} parallel to this {@link Vector}.
     *
     * @return the {@link Vector}
     */
    V normalize();

    /**
     * Returns a scalar multiple of the unit {@link Vector} parallel to this {@link Vector}.
     *
     * @param length the desired length of the {@link Vector}.
     * @return the {@link Vector}
     */
    V normalize(double length);

    /**
     * Returns a scalar multiple of this {@link Vector}.
     *
     * @param scaleFactor the desired scale factor for the {@link Vector}.
     * @return the {@link Vector}
     */
    V scale(double scaleFactor);

    /**
     * Returns the {@link Vector} that is the sum of this {@link Vector} and {@code addend}.
     *
     * @param addend the {@link Vector} to be added to this {@link Vector}.
     * @return the {@link Vector}
     */
    V add(V addend);

    /**
     * Returns the {@link Vector} that is the difference of this {@link Vector} and {@code subtrahend}.
     *
     * @param subtrahend the {@link Vector} to be subtracted from this {@link Vector}.
     * @return the {@link Vector}
     */
    V subtract(V subtrahend);

    /**
     * Returns the {@link Vector} that is this {@link Vector} with floored coordinates.
     *
     * @return the {@link Vector}
     */
    V floor();

    /**
     * Returns the {@link Vector} that is this {@link Vector} with ceilinged coordinates.
     *
     * @return the {@link Vector}
     */
    V ceil();

    /**
     * Returns the {@link Vector} that is anti-parallel to this {@link Vector}.
     *
     * @return the {@link Vector}
     */
    V reverse();

    /**
     * Returns {@code true} IFF this {@link Vector} is
     * considered to be valid, with each {@code component ∈ ℝ}.
     *
     * @return {@code true} if the {@link Vector} is valid, {@code false} otherwise
     */
    boolean isValid();
}
