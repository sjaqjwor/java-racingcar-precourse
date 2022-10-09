package racingcar.round;

import racingcar.car.Car;
import racingcar.car.CarName;
import racingcar.car.CarPosition;
import racingcar.game.dto.RacingResult;
import racingcar.move.Movement;

import java.util.LinkedList;
import java.util.List;

public class RacingRound {

    private static final int MIN_ROUND_NUMBER = 0;

    private int round;

    public RacingRound(String roundNumber) {
        try {
            int roundNum = Integer.parseInt(roundNumber);
            if (roundNum < MIN_ROUND_NUMBER) {
                throw new IllegalArgumentException(RoundError.NEGATIVE_NUMBER.printMessage());
            }
            this.round = roundNum;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(RoundError.NOT_NUMBER.printMessage());
        }
    }

    public List<RacingResult> result(List<Car> cars, Movement movement) {
        List<RacingResult> result = new LinkedList<>();
        for (Car car : cars) {
            car.move(movement);
            CarName name = car.getCarName();
            CarPosition position = car.getPosition();
            result.add(new RacingResult(name.getName(), position.getPosition()));
        }
        return result;
    }

    public boolean possibleRoundStart() {
        return MIN_ROUND_NUMBER <= this.round--;
    }
}
