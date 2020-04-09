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

package grakn.client.concept.remote;

import grakn.client.GraknClient;
import grakn.client.concept.Concept;
import grakn.client.concept.ConceptId;
import grakn.client.concept.Role;
import grakn.client.concept.thing.Relation;
import grakn.client.concept.thing.Thing;
import grakn.client.concept.type.RelationType;
import grakn.client.rpc.RequestBuilder;
import grakn.protocol.session.ConceptProto;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Client implementation of Relation
 */
public class RemoteRelationImpl extends RemoteThingImpl<Relation.Remote, RelationType.Remote> implements Relation.Remote {

    public RemoteRelationImpl(GraknClient.Transaction tx, ConceptId id) {
        super(tx, id);
    }

    @Override
    public final Map<Role.Remote, Set<Thing.Remote<?, ?>>> rolePlayersMap() {
        ConceptProto.Method.Iter.Req method = ConceptProto.Method.Iter.Req.newBuilder()
                .setRelationRolePlayersMapIterReq(ConceptProto.Relation.RolePlayersMap.Iter.Req.getDefaultInstance()).build();

        Stream<ConceptProto.Relation.RolePlayersMap.Iter.Res> stream = tx().iterateConceptMethod(id(), method, ConceptProto.Method.Iter.Res::getRelationRolePlayersMapIterRes);

        Map<Role.Remote, Set<Thing.Remote<?, ?>>> rolePlayerMap = new HashMap<>();
        stream.forEach(rolePlayer -> {
            Role.Remote role = Concept.Remote.of(rolePlayer.getRole(), tx()).asRole();
            Thing.Remote<?, ?> player = Concept.Remote.of(rolePlayer.getPlayer(), tx()).asThing();
            if (rolePlayerMap.containsKey(role)) {
                rolePlayerMap.get(role).add(player);
            } else {
                rolePlayerMap.put(role, new HashSet<>(Collections.singletonList(player)));
            }
        });

        return rolePlayerMap;
    }

    @Override
    public final Stream<Thing.Remote<?, ?>> rolePlayers(Role<?>... roles) {
        ConceptProto.Method.Iter.Req method = ConceptProto.Method.Iter.Req.newBuilder()
                .setRelationRolePlayersIterReq(ConceptProto.Relation.RolePlayers.Iter.Req.newBuilder()
                        .addAllRoles(RequestBuilder.ConceptMessage.concepts(Arrays.asList(roles)))).build();

        return conceptStream(method, res -> res.getRelationRolePlayersIterRes().getThing()).map(Concept.Remote::asThing);
    }

    @Override
    public final Relation.Remote assign(Role<?> role, Thing<?, ?> player) {
        ConceptProto.Method.Req method = ConceptProto.Method.Req.newBuilder()
                .setRelationAssignReq(ConceptProto.Relation.Assign.Req.newBuilder()
                        .setRole(RequestBuilder.ConceptMessage.from(role))
                        .setPlayer(RequestBuilder.ConceptMessage.from(player))).build();

        runMethod(method);
        return asCurrentBaseType(this);
    }

    @Override
    public final void unassign(Role<?> role, Thing<?, ?> player) {
        ConceptProto.Method.Req method = ConceptProto.Method.Req.newBuilder()
                .setRelationUnassignReq(ConceptProto.Relation.Unassign.Req.newBuilder()
                        .setRole(RequestBuilder.ConceptMessage.from(role))
                        .setPlayer(RequestBuilder.ConceptMessage.from(player))).build();

        runMethod(method);
    }

    @Override
    final RelationType.Remote asCurrentType(Concept.Remote<?> concept) {
        return concept.asRelationType();
    }

    @Override
    final Relation.Remote asCurrentBaseType(Concept.Remote<?> other) {
        return other.asRelation();
    }

}
