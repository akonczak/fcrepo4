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
/**
 *
 */

package org.fcrepo.kernel.impl.observer.eventmappings;

import java.util.function.Function;
import java.util.stream.Stream;

import javax.jcr.observation.Event;

import org.fcrepo.kernel.observer.FedoraEvent;
import org.fcrepo.kernel.observer.eventmappings.InternalExternalEventMapper;


/**
 * Maps each JCR {@link Event} to a single {@link FedoraEvent}
 *
 * @author ajs6f
 * @since Feb 27, 2014
 */
public class OneToOne implements InternalExternalEventMapper {

    private static final Function<Event, FedoraEvent> TO_FEDORA_EVENT = e -> new FedoraEvent(e);

    @Override
    public Stream<FedoraEvent> apply(final Stream<Event> jcrEvents) {
        return jcrEvents.map(TO_FEDORA_EVENT);
    }
}