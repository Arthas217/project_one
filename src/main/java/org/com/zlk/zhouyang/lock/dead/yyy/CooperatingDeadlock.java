package org.com.zlk.zhouyang.lock.dead.yyy;

import javax.annotation.concurrent.GuardedBy;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 协作对象之间发生死锁,隐式获取两个锁(对象之间协作)..没懂
 * @Date 2021/1/13 17:02
 */
public class CooperatingDeadlock {

    class Image {
        public void drawMarker(Point p) {
        }
    }

    class Taxi {
        @GuardedBy("this")
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        // setLocation 需要Taxi内置锁 ***************
        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination)){
                // 调用notifyAvailable()需要Dispatcher内置锁
                dispatcher.notifyAvailable(this);
            }
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    class Dispatcher {
        @GuardedBy("this")
        private final Set<Taxi> taxis;
        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        // 调用getImage()需要Dispatcher内置锁   ************
        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis) {
                // 调用getLocation()需要Taxi内置锁
                image.drawMarker(t.getLocation());
            }
            return image;
        }
    }

    // 开放调用(针对对象之间协作造成的死锁)
}
