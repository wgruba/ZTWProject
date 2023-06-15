export class PlaceInfo{
    placeId: string;
    nr: number;
    available: boolean;
    isSelected: boolean = false;

    constructor(placeId: string, nr: number, available: boolean) {
        this.placeId = placeId;
        this.nr = nr;
        this.available = available;
    }
}

export class PlaceInfoWithCourseStops {
    places: PlaceInfo[];
    courseId: string;
    cityFromId: string;
    cityToId: string;

    constructor(places: PlaceInfo[], courseId: string, cityFromId: string, cityToId: string) {
        this.places = places;
        this.courseId = courseId;
        this.cityFromId = cityFromId;
        this.cityToId = cityToId;
    }
}