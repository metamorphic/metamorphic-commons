package io.metamorphic.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markmo on 4/07/2015.
 */
public class PairListBuilder<L, R> {

    List<Pair<L, R>> pairs = new ArrayList<>();

    public PairListBuilder<L, R> add(L l, R r) {
        pairs.add(new Pair<>(l, r));
        return this;
    }

    public List<Pair<L, R>> asList() {
        return pairs;
    }
}
