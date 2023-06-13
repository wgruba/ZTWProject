export class TicketInfo {
    routeName: string;
    startCityName: string;
    endCityName: string;
    price: number;
    departureTime: Date;
    qrCode: string;
    generatedQRCode: string;


    constructor(routeName: string, startCityName: string, endCityName: string, price: number, departureTime: Date, qrCode: string) {
        this.routeName = routeName;
        this.startCityName = startCityName;
        this.endCityName = endCityName;
        this.price = price;
        this.departureTime = departureTime;
        this.qrCode = qrCode;
        this.generatedQRCode = '';
      }
}