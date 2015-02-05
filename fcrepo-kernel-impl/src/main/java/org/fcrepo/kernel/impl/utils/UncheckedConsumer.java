/**
 * Copyright 2015 DuraSpace, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fcrepo.kernel.impl.utils;

import java.util.function.Consumer;

import javax.jcr.RepositoryException;

import org.fcrepo.kernel.exception.RepositoryRuntimeException;

public interface UncheckedConsumer<T> extends Consumer<T> {

    @Override
    default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    void acceptThrows(T elem) throws RepositoryException;

    static <T> UncheckedConsumer<T> uncheck(final ThrowingConsumer<T> c) {
        return new UncheckedConsumer<T>() {

            @Override
            public void acceptThrows(final T elem) throws RepositoryException {
                c.accept(elem);
            }
        };

    }

    @FunctionalInterface
    public static interface ThrowingConsumer<T> {
        void accept(T element) throws RepositoryException;
    }
}