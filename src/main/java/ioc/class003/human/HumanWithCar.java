package ioc.class003.human;

import ioc.class003.car.Car;

public abstract class HumanWithCar implements Human{
    protected Car car;

    public HumanWithCar(Car car) {
        this.car = car;
    }

    @Override
    public abstract void goHome();
}
