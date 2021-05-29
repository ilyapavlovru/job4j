package ru.job4j.hibernate.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void clear() throws SQLException {
        pool.getConnection().prepareStatement("drop table orders").executeUpdate();
    }

        @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveTwoOrdersAndFindAllTwoRowsWithNameAndDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));
        List<Order> all = (List<Order>) store.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("name1"));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
        assertThat(all.get(1).getName(), is("name2"));
        assertThat(all.get(1).getDescription(), is("description2"));
        assertThat(all.get(1).getId(), is(2));
    }

    @Test
    public void whenUpdateOrderAndFindAllOneRowWithNameAndDescription() {
        OrdersStore store = new OrdersStore(pool);
        Order order = Order.of("name1", "description1");
        store.save(order);
        order.setName("name2");
        order.setDescription("description2");
        store.update(order);
        List<Order> all = (List<Order>) store.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getName(), is("name2"));
        assertThat(all.get(0).getDescription(), is("description2"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveTwoOrdersAndFindByNameOneRowWithNameAndDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));
        Order order = store.findByName("name2");
        assertThat(order.getName(), is("name2"));
        assertThat(order.getDescription(), is("description2"));
        assertThat(order.getId(), is(2));
    }

    @Test
    public void whenSaveTwoOrdersAndFindByIdOneRowWithNameAndDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));
        Order order = store.findById(2);
        assertThat(order.getId(), is(2));
        assertThat(order.getName(), is("name2"));
        assertThat(order.getDescription(), is("description2"));
    }
}
