export class Connection {
    id: string;
    departureDate: Date;
    stops: StopInConnection[];
    routeName: string;

    constructor(id: string, departureDate: Date, stops: StopInConnection[],routeName: string) {
        this.id = id;
        this.departureDate = departureDate;
        this.stops = stops;
        this.routeName = routeName;
    }
}

export class StopInConnection{
    id: string;
    arrivalDate: Date;
    cityName: string;

    constructor(id: string, arrivalDate: Date, cityName: string) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.cityName = cityName;
    }
}

export class ConenctionWithCityId {
    connections: Connection[];
    cityFrom: string;
    cityTo: string;

    constructor (connections: Connection[], cityFrom: string, cityTo: string) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.connections = connections;
    }
}