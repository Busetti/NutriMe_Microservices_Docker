export class User {

    constructor(
        public firstName?: string,
        public lastName?: string,
        public emailId?: string,
        public password?: string,
        public height?: number,
        public weight?: number,
        public age?: number,
        public token?: string,
        public confirmpassword?: string,
    ){
    }
}