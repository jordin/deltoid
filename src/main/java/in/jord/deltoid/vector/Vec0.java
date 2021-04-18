package in.jord.deltoid.vector;

public class Vec0 implements Vector<Vec0> {
    private static final double[] COMPONENTS = new double[0];
    public static final Vec0 ZERO = new Vec0();

    @Override
    public double length() {
        return 0;
    }

    @Override
    public double manhattan() {
        return 0;
    }

    @Override
    public double[] components() {
        return COMPONENTS;
    }

    @Override
    public Vec0 normalize() {
        return ZERO;
    }

    @Override
    public Vec0 normalize(double length) {
        return ZERO;
    }

    @Override
    public Vec0 scale(double scaleFactor) {
        return ZERO;
    }

    @Override
    public Vec0 add(Vec0 addend) {
        return ZERO;
    }

    @Override
    public Vec0 subtract(Vec0 subtrahend) {
        return ZERO;
    }

    @Override
    public Vec0 floor() {
        return ZERO;
    }

    @Override
    public Vec0 ceil() {
        return ZERO;
    }

    @Override
    public Vec0 reverse() {
        return ZERO;
    }
}
