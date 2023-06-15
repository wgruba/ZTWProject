export class User {
    sub: string;
    name: string;
    picture: string;
    email: string;


    constructor(sub: string, name: string, picture: string, email: string) {
        this.sub = sub;
        this.name = name;
        this.picture = picture;
        this.email = email;

    }
}