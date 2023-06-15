export class BuyTickets {
    username: string;
    courseId: string;
    placesIds: string[];
    startStopId: string;
    endStopId: string;
    price: number;

    constructor(username: string, courseId: string, placesIds: string[] , startStopId: string, endStopId: string, price: number) {
        this.username = username;
        this.courseId = courseId;
        this.placesIds = placesIds;
        this.startStopId = startStopId;
        this.endStopId = endStopId;
        this.price = price
    }
}