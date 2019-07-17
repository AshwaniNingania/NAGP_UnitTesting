package com.nagarro.nagp.handler;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
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

    @Test
    public void shouldCreateInverntorySuccessfully() {
        final Inventory expected = Mockito.mock(Inventory.class);
        handler.createInventory(expected);
        Mockito.verify(durableInventoryRepository).save(expected);
    }
}
