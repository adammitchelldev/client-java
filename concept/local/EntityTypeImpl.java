/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package grakn.client.concept.local;

import grakn.client.concept.Concept;
import grakn.client.concept.thing.Entity;
import grakn.client.concept.type.EntityType;
import grakn.protocol.session.ConceptProto;

/**
 * Client implementation of a MetaType, a special type of Type
 * TODO: This class is not defined in Concept API, and at server side implementation.
 * TODO: we should remove this class, or implement properly on server side.
 */
public class EntityTypeImpl extends TypeImpl<EntityType.Local, Entity.Local> implements EntityType.Local {

    public EntityTypeImpl(ConceptProto.Concept concept) {
        super(concept);
    }

    @Override
    final Local asCurrentBaseType(Concept<?> other) {
        return (Local) other.asEntityType();
    }

    @Override
    final boolean equalsCurrentBaseType(Concept<?> other) {
        return other.isEntityType();
    }

    @Override
    protected final Entity.Local asInstance(Concept<?> concept) {
        return (Entity.Local) concept.asEntity();
    }

}
