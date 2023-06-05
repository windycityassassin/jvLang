package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import charge.strategy.factory.StrategyFactoryProximity;
import parkingsystem.AppModule;
import org.junit.jupiter.api.Test;

public class GuiceTest {

    @Test
    public void test() {

        Injector guice = Guice.createInjector(new AppModule());
        StrategyFactoryProximity strategy = guice.getInstance(StrategyFactoryProximity.class);

        strategy.getStrategy();
    }

}
