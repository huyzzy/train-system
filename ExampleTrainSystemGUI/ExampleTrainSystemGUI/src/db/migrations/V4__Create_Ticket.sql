CREATE TABLE Ticket(
    TicketID int PRIMARY KEY,
    PassengerID VARCHAR(30) NOT NULL,
    PurchaseDateTime VARCHAR(30) NOT NULL,
    TicketCost int NOT NULL,
    ScheduleID int NOT NULL,
   FOREIGN KEY (ScheduleID) REFERENCES Schedule(ScheduleID)
);