import { HttpClient } from "@angular/common/http";
import { EventEmitter, Injectable, Output } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from 'rxjs/internal/Observable';
import { environment } from "src/environments/environment";
import { Login } from "../Model/login";
import { User } from "../Model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public API = `${environment.API_URL}`;
  public SAVE_USER = this.API + '/app/v1/user/signup';
  public SIGN_IN = this.API + '/app/v1/user/authenticate';
  public SEND_OTP = this.API + '/app/v1/user/sendotp/';
  public VALIDATE_OTP = this.API + '/app/v1/user/validateotp';
  public UPDATE_PASSWORD = this.API + '/app/v1/user/updateuserpassword';
  public USER_INFO = this.API + '/app/v1/user/userinfo/';
  public UPDATE_USERINFO = this.API + '/app/v1/user/updateuserinfo';

  constructor(private http: HttpClient, private router: Router) { }

  saveUser(user: User): Observable<User> {
    return this.http.post(`${this.SAVE_USER}`, user);
  }

  signin(user: Login): Observable<User> {
    return this.http.post(`${this.SIGN_IN}`, user);
  }

  sendOtp(emailId: string): Observable<string> {
    return this.http.get(`${this.SEND_OTP}/${emailId}`, { responseType: 'text' });
  }

  validateOtp(emailId: string, otp: string): Observable<string> {
    return this.http.get(`${this.VALIDATE_OTP}`+"/?email="+`${emailId}`+"&otp="+`${otp}`, { responseType: 'text' });
  }

  changePassword(user: User): Observable<User> {
    return this.http.post(`${this.UPDATE_PASSWORD}`, user);
  }

  userInfo(emailId: string): Observable<User> {
    return this.http.get(`${this.USER_INFO}`+`${emailId}`);
  }

  updateUserInfo(user: User): Observable<User> {
    return this.http.post(`${this.UPDATE_USERINFO}`, user);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('user')
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token')
    this.router.navigate(['signin']);
  }

  getUsername(): String {
    if(this.isUserLoggedIn)
      return sessionStorage.getItem('user');
  }
}