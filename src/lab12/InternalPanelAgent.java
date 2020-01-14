package lab12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {
    static class InternalCall{
        private final int toFloor;
        InternalCall(int toFloor){
            this.toFloor = toFloor;
        }
    }

    InternalPanelAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    ElevatorCar elevatorCar;

    public void run(){
        for(;;){
            InternalCall ic = null;
            try{
                ic = input.take();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (ic.toFloor == elevatorCar.floor) continue;
            if (ic.toFloor > elevatorCar.floor) ElevatorStops.get().setLiftStopUp(ic.toFloor);
            if (ic.toFloor < elevatorCar.floor) ElevatorStops.get().setLiftStopDown(ic.toFloor);
        }
    }

}