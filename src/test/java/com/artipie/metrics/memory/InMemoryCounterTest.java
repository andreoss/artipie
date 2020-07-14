/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 artipie.com
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
package com.artipie.metrics.memory;

import java.util.stream.IntStream;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link InMemoryCounter}.
 *
 * @since 0.8
 */
class InMemoryCounterTest {

    @Test
    void shouldBeInitializedWithZero() {
        MatcherAssert.assertThat(new InMemoryCounter().value(), new IsEqual<>(0L));
    }

    @Test
    void shouldAddValue() {
        final InMemoryCounter counter = new InMemoryCounter();
        final long value = 123L;
        counter.add(value);
        MatcherAssert.assertThat(counter.value(), new IsEqual<>(value));
    }

    @Test
    void shouldFailAddNegativeValue() {
        final InMemoryCounter counter = new InMemoryCounter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> counter.add(-1));
    }

    @Test
    void shouldIncrement() {
        final InMemoryCounter counter = new InMemoryCounter();
        final int count = 5;
        IntStream.rangeClosed(1, count).forEach(ignored -> counter.inc());
        MatcherAssert.assertThat(counter.value(), new IsEqual<>((long) count));
    }
}