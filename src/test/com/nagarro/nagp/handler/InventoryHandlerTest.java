package com.nagarro.nagp.handler;

import static org.junit.Assert.assertEquals;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.repository.DurableInventoryRepository;
import com.nagarro.nagp.repository.FragileInventoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InventoryHandlerTest {

    @InjectMocks
    private InventoryHandler handler;

    @Mock
    private DurableInventoryRepository durableInventoryRepository;

    @Mock
    private FragileInventoryRepository fragileInventoryRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldReturnExceptionOnNullCategory() {
        final Inventory inventory = new Inventory(null);
        handler.createInventory(inventory);
    }

    @Test
    public void shouldReturnFragileCategory() {
        final Inventory expected = new Inventory(Category.FRAGILE);
        final Inventory actual = handler.createInventory(expected);
        assertEquals(expected.getCategory(), actual.getCategory());
    }

    @Test
    public void shouldReturnDurableCategory() {
        final Inventory inventory = new Inventory(Category.DURABLE);
        final Inventory actual = handler.createInventory(inventory);
        assertEquals(Category.FRAGILE, actual.getCategory());
    }
}
