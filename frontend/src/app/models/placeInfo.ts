export class PlaceInfo{
    placeId: string;
    available: boolean;
    isSelected: boolean = false;

    constructor(placeId: string, available: boolean) {
        this.placeId = placeId;
        this.available = available;
    }
}