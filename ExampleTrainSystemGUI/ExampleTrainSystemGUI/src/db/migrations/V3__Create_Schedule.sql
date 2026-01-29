CREATE TABLE Schedule(
    ScheduleID int PRIMARY KEY,
    StartDateTime TIMESTAMP NOT NULL,
    EndDateTime TIMESTAMP NOT NULL,
    StartLocation VARCHAR(30) NOT NULL,
    Desitination VARCHAR(30) NOT NULL,
    TrainID int NOT NULL,
   FOREIGN KEY (TrainID) REFERENCES Train(TrainID)
);