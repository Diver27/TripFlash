package com.example.l.TripFlash.model;

public class RoutineModel implements RoutineInterface{
    public RoutineModel(){}

    public class DestSpot{
        private String name;
        private String location;

        public DestSpot(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
