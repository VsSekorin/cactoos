/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.iterator;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Synchronized {@link Iterator} implementation.
 * @author Sven Diedrichsen (sven.diedrichsen@gmail.com)
 * @version $Id$
 * @param <T> The type of the iterator.
 * @since 1.0
 */
public class SyncIterator<T> implements Iterator<T> {
    /**
     * The original iterator.
     */
    private final Iterator<T> iterator;
    /**
     * The lock to use.
     */
    private final ReentrantReadWriteLock lock;

    /**
     * Ctor.
     * @param iterator The iterator to synchronize access to.
     */
    public SyncIterator(final Iterator<T> iterator) {
        this(iterator, new ReentrantReadWriteLock());
    }

    /**
     * Ctor.
     * @param iterator The iterator to synchronize access to.
     * @param lock The lock to use for synchronization.
     */
    public SyncIterator(
        final Iterator<T> iterator,
        final ReentrantReadWriteLock lock) {
        this.iterator = iterator;
        this.lock = lock;
    }

    @Override
    public final boolean hasNext() {
        this.lock.readLock().lock();
        try {
            return this.iterator.hasNext();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public final T next() {
        this.lock.writeLock().lock();
        try {
            return this.iterator.next();
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
