package ioc.class003.human;

import ioc.class003.car.Car;

public class LiSi extends HumanWithCar {
    public LiSi(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
         car.start();
         car.stop();
    }
}
