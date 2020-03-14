import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    console.log(route.data["authenticated"]);
    console.log(this.authService.currentUser);

    const authenticated: boolean = this.authService.currentUser !== undefined;

    if (route.data["authenticated"] === authenticated) {
      return true;
    }
    else {
      return this.router.parseUrl('/');
    }
  }

}
