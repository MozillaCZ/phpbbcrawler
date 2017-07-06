package cz.mozilla.java.phpbbcrawler.helpers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class UniqueQueue<E> {

    private final LinkedHashSet<E> linkedSet;

    public UniqueQueue() {
        this.linkedSet = new LinkedHashSet<>();
    }

    public UniqueQueue(Collection<? extends E> c) {
        this.linkedSet = new LinkedHashSet<>(c);
    }

    public boolean offer(E e) {
        return linkedSet.add(e);
    }

    public E poll() {
        Iterator<E> iterator = linkedSet.iterator();
        E next = null;
        if (iterator.hasNext()) {
            next = iterator.next();
            iterator.remove();
        }
        return next;
    }

    public int size() {
        return linkedSet.size();
    }
}
