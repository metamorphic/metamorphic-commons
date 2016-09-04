package io.metamorphic.commons;

/**
 * Created by markmo on 4/07/2015.
 */
public class Pair<L, R> {

    public final L l;
    public final R r;

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public Object[] asArray() {
        return new Object[] {l, r};
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", l, r);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (l != null ? !l.equals(pair.l) : pair.l != null) return false;
        return !(r != null ? !r.equals(pair.r) : pair.r != null);

    }

    @Override
    public int hashCode() {
        int result = l != null ? l.hashCode() : 0;
        result = 31 * result + (r != null ? r.hashCode() : 0);
        return result;
    }
}
