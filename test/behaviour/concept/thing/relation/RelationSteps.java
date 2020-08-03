/*
 * Copyright (C) 2020 Grakn Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package grakn.client.test.behaviour.concept.thing.relation;

import grakn.client.concept.ValueType;
import grakn.client.concept.thing.Attribute;
import grakn.client.concept.thing.Relation;
import grakn.client.concept.thing.Thing;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static grakn.client.test.behaviour.concept.thing.ThingSteps.get;
import static grakn.client.test.behaviour.concept.thing.ThingSteps.put;
import static grakn.client.test.behaviour.connection.ConnectionSteps.tx;
import static grakn.client.test.behaviour.util.Util.assertThrows;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class RelationSteps {

    @When("{var} = relation\\( ?{type_label} ?) create new instance")
    public void relation_type_create_new_instance(String var, String typeLabel) {
        put(var, tx().getRelationType(typeLabel).create());
    }

    @Then("relation\\( ?{type_label} ?) create new instance; throws exception")
    public void relation_type_create_new_instance_throws_exception(String typeLabel) {
        assertThrows(() -> tx().getRelationType(typeLabel).create());
    }

    @When("{var} = relation\\( ?{type_label} ?) create new instance with key\\( ?{type_label} ?): {bool}")
    public void relation_type_create_new_instance_with_key(String var, String type, String keyType, boolean keyValue) {
        Attribute.Remote<Boolean> key = tx().getAttributeType(keyType).asAttributeType(ValueType.BOOLEAN).put(keyValue);
        put(var, tx().getRelationType(type).create().has(key));
    }

    @When("{var} = relation\\( ?{type_label} ?) create new instance with key\\( ?{type_label} ?): {int}")
    public void relation_type_create_new_instance_with_key(String var, String type, String keyType, int keyValue) {
        Attribute.Remote<Long> key = tx().getAttributeType(keyType).asAttributeType(ValueType.LONG).put((long) keyValue);
        put(var, tx().getRelationType(type).create().has(key));
    }

    @When("{var} = relation\\( ?{type_label} ?) create new instance with key\\( ?{type_label} ?): {double}")
    public void relation_type_create_new_instance_with_key(String var, String type, String keyType, double keyValue) {
        Attribute.Remote<Double> key = tx().getAttributeType(keyType).asAttributeType(ValueType.DOUBLE).put(keyValue);
        put(var, tx().getRelationType(type).create().has(key));
    }

    @When("{var} = relation\\( ?{type_label} ?) create new instance with key\\( ?{type_label} ?): {word}")
    public void relation_type_create_new_instance_with_key(String var, String type, String keyType, String keyValue) {
        Attribute.Remote<String> key = tx().getAttributeType(keyType).asAttributeType(ValueType.STRING).put(keyValue);
        put(var, tx().getRelationType(type).create().has(key));
    }

    @When("{var} = relation\\( ?{type_label} ?) create new instance with key\\( ?{type_label} ?): {datetime}")
    public void relation_type_create_new_instance_with_key(String var, String type, String keyType, LocalDateTime keyValue) {
        Attribute.Remote<LocalDateTime> key = tx().getAttributeType(keyType).asAttributeType(ValueType.DATETIME).put(keyValue);
        put(var, tx().getRelationType(type).create().has(key));
    }

    @When("{var} = relation\\( ?{type_label} ?) get instance with key\\( ?{type_label} ?): {bool}")
    public void relation_type_get_instance_with_key(String var1, String type, String keyType, boolean keyValue) {
        put(var1, tx().getAttributeType(keyType).asAttributeType(ValueType.BOOLEAN).get(keyValue).owners()
                .filter(owner -> owner.type().equals(tx().getRelationType(type)))
                .findFirst().orElse(null));
    }

    @When("{var} = relation\\( ?{type_label} ?) get instance with key\\( ?{type_label} ?): {long}")
    public void relation_type_get_instance_with_key(String var1, String type, String keyType, long keyValue) {
        put(var1, tx().getAttributeType(keyType).asAttributeType(ValueType.LONG).get(keyValue).owners()
                .filter(owner -> owner.type().equals(tx().getRelationType(type)))
                .findFirst().orElse(null));
    }

    @When("{var} = relation\\( ?{type_label} ?) get instance with key\\( ?{type_label} ?): {double}")
    public void relation_type_get_instance_with_key(String var1, String type, String keyType, double keyValue) {
        put(var1, tx().getAttributeType(keyType).asAttributeType(ValueType.DOUBLE).get(keyValue).owners()
                .filter(owner -> owner.type().equals(tx().getRelationType(type)))
                .findFirst().orElse(null));
    }


    @When("{var} = relation\\( ?{type_label} ?) get instance with key\\( ?{type_label} ?): {word}")
    public void relation_type_get_instance_with_key(String var1, String type, String keyType, String keyValue) {
        put(var1, tx().getAttributeType(keyType).asAttributeType(ValueType.STRING).get(keyValue).owners()
                .filter(owner -> owner.type().equals(tx().getRelationType(type)))
                .findFirst().orElse(null));
    }

    @When("{var} = relation\\( ?{type_label} ?) get instance with key\\( ?{type_label} ?): {datetime}")
    public void relation_type_get_instance_with_key(String var1, String type, String keyType, LocalDateTime keyValue) {
        put(var1, tx().getAttributeType(keyType).asAttributeType(ValueType.DATETIME).get(keyValue).owners()
                .filter(owner -> owner.type().equals(tx().getRelationType(type)))
                .findFirst().orElse(null));
    }

    @Then("relation\\( ?{type_label} ?) get instances contain: {var}")
    public void relation_type_get_instances_contain(String typeLabel, String var) {
        assertTrue(tx().getRelationType(typeLabel).instances().anyMatch(i -> i.equals(get(var))));
    }

    @Then("relation\\( ?{type_label} ?) get instances do not contain: {var}")
    public void relation_type_get_instances_do_not_contain(String typeLabel, String var) {
        assertTrue(tx().getRelationType(typeLabel).instances().noneMatch(i -> i.equals(get(var))));
    }

    @Then("relation\\( ?{type_label} ?) get instances is empty")
    public void relation_type_get_instances_is_empty(String typeLabel) {
        assertEquals(0, tx().getRelationType(typeLabel).instances().count());
    }

    @When("relation {var} set player for role\\( ?{type_label} ?): {var}")
    public void relation_set_player_for_role(String var1, String roleTypeLabel, String var2) {
        get(var1).asRelation().relate(get(var1).asRelation().type().role(roleTypeLabel), get(var2));
    }

    @When("relation {var} remove player for role\\( ?{type_label} ?): {var}")
    public void relation_remove_player_for_role(String var1, String roleTypeLabel, String var2) {
        get(var1).asRelation().unrelate(get(var1).asRelation().type().role(roleTypeLabel), get(var2));
    }

    @Then("relation {var} get players contain:")
    public void relation_get_players_contain(String var, Map<String, String> players) {
        Relation.Remote relation = get(var).asRelation();
        players.forEach((rt, var2) -> assertTrue(relation.playersMap().get(relation.type().role(rt)).contains(get(var2.substring(1)))));
    }

    @Then("relation {var} get players do not contain:")
    public void relation_get_players_do_not_contain(String var, Map<String, String> players) {
        Relation.Remote relation = get(var).asRelation();
        players.forEach((rt, var2) -> {
            List<? extends Thing.Remote<?, ?>> p;
            if ((p = relation.playersMap().get(relation.type().role(rt))) != null) {
                assertFalse(p.contains(get(var2.substring(1))));
            }
        });
    }

    @Then("relation {var} get players contain: {var}")
    public void relation_get_players_contain(String var1, String var2) {
        assertTrue(get(var1).asRelation().players().anyMatch(p -> p.equals(get(var2))));
    }

    @Then("relation {var} get players do not contain: {var}")
    public void relation_get_players_do_not_contain(String var1, String var2) {
        assertTrue(get(var1).asRelation().players().noneMatch(p -> p.equals(get(var2))));
    }

    @Then("relation {var} get players for role\\( ?{type_label} ?) contain: {var}")
    public void relation_get_player_for_role_contain(String var1, String roleTypeLabel, String var2) {
        assertTrue(get(var1).asRelation().players(get(var1).asRelation().type().role(roleTypeLabel)).anyMatch(p -> p.equals(get(var2))));
    }

    @Then("relation {var} get players for role\\( ?{type_label} ?) do not contain: {var}")
    public void relation_get_player_for_role_do_not_contain(String var1, String roleTypeLabel, String var2) {
        assertTrue(get(var1).asRelation().players(get(var1).asRelation().type().role(roleTypeLabel)).noneMatch(p -> p.equals(get(var2))));
    }
}