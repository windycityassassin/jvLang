package charge.strategy.factory;

import charge.decorator.factory.FactoryInterface;
import com.google.inject.Inject;

public class StrategyFactoryProximity implements FactoryInterface {

    private final FactoryInterface factory;

    @Inject
    public StrategyFactoryProximity(FactoryInterface factory) {
        this.factory = factory;
    }

    public StrategyProximity getStrategy() {
        StrategyProximity proximity = new StrategyProximity();
        return proximity;
    }

    public void printTest(){
        System.out.println("Success");
    }



}
