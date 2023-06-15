export class CheckAvailability {
    courseId: string;
    cityFromId: string;
    cityToId: string;

    constructor(courseId: string, cityFromId: string, cityToId: string) {
        this.cityFromId = cityFromId;
        this.cityToId = cityToId;
        this.courseId = courseId;
    }
}