export class BuyTickets {
    userId: string;
    courseId: string;
    placesIds: string[];
    startStopId: string;
    endStopId: string;
    price: number;

    constructor(userId: string, courseId: string, placesIds: string[] , startStopId: string, endStopId: string, price: number) {
        this.userId = userId;
        this.courseId = courseId;
        this.placesIds = placesIds;
        this.startStopId = startStopId;
        this.endStopId = endStopId;
        this.price = price
    }
}