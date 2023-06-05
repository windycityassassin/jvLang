package serialization;

import org.junit.Test;

public class ClientHandlerRunnableTest {

    @Test
    public void testRun() {
        ClientHandlerRunnable clientHandler = new ClientHandlerRunnable();

        // Call the run method
        clientHandler.run();

        // No assertion is needed for this test, as it is primarily focused on checking if the method runs without errors
    }
}
