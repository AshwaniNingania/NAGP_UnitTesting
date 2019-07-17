package com.nagarro.nagp;

import static org.junit.Assert.assertEquals;

import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InventoryResourceTest {

    @InjectMocks
    private InventoryResource resource;

    @Mock
    private InventoryHandler handler;

    @Test
    public void shouldCreateInventoryOfCategoryDURABLESuccessfully() {
        final Inventory expected = new Inventory(Category.FRAGILE);
        Mockito.when(handler.createInventory(Mockito.any(Inventory.class))).thenReturn(expected);

        Inventory actual = resource.createInventory(expected);
        assertEquals(expected.getCategory(), actual.getCategory());
        Mockito.verify(handler, Mockito.times(1)).createInventory(Mockito.any(Inventory.class));
    }

    @Test
    public void shouldCreateInventoryOfCategoryFRAGILESuccessfully() {
        final Inventory expected = new Inventory(Category.FRAGILE);
        Mockito.when(handler.createInventory(Mockito.any(Inventory.class))).thenReturn(expected);
        Inventory actual = resource.createInventory(expected);
        assertEquals(expected.getCategory(), actual.getCategory());
        Mockito.verify(handler, Mockito.times(1)).createInventory(Mockito.any(Inventory.class));
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionOnNullCategory() {
        resource.createInventory(null);
        Mockito.verify(handler, Mockito.times(0)).createInventory(Mockito.any(Inventory.class));
    }
}
