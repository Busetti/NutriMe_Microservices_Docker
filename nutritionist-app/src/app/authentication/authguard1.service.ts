import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { UserService } from '../user/Service/user-service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardServiceNew implements CanActivate {

  constructor(private router: Router,
    private authService: UserService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.authService.isUserLoggedIn())
      return true;

    this.router.navigate(['home']);
    return false;

  }

}
