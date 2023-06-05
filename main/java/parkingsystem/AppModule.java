package parkingsystem;

import com.google.inject.AbstractModule;
import charge.decorator.factory.FactoryInterface;
import charge.strategy.factory.StrategyFactoryProximity;


public class AppModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(FactoryInterface.class ).to(StrategyFactoryProximity.class);
    }

}
