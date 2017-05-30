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
package org.cactoos.list;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test case for {@link FilteredIterable}.
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FilteredIterableTest {

    /**
     * FilteredIterable can filter a list.
     */
    @Test
    public void filtersList() {
        MatcherAssert.assertThat(
            new LengthOfIterable(
                new FilteredIterable<>(
                    new ArrayAsIterable<>(
                        "hello", "world", "друг"
                    ),
                    // @checkstyle MagicNumber (1 line)
                    input -> input.length() > 4
                )
            ),
            new ScalarHasValue<>(2)
        );
    }

    /**
     * FilteredIterable can filter an empty list.
     */
    @Test
    public void filtersEmptyList() {
        MatcherAssert.assertThat(
            new LengthOfIterable(
                new FilteredIterable<>(
                    new ArrayAsIterable<String>(),
                    input -> input.length() > 1
                )
            ),
            new ScalarHasValue<>(0)
        );
    }

}
