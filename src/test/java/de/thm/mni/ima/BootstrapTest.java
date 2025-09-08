package de.thm.mni.ima;

import de.thm.mni.ima.user.store.EmployeeStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapTest {

  @Test
  void initialization() {
    try {
      Bootstrap.initializeDataStorage();

      var store = EmployeeStore.getInstance();

      // Check if the store is initialized
      assertNotNull(store);
      assertFalse(store.getAll().isEmpty());
    } catch (Exception e) {
      fail("Initialization failed: " + e.getMessage());
    }
  }

}
