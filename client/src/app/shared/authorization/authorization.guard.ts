import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {AuthenticationService} from "../authentication/authentication.service";
import {UserDto} from "../../dto/dto";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    const allowedRoles: string[] = route.data["roles"];
    const user: UserDto | undefined = this.authService.currentUser;

    if (!!user && user.roles.filter(role => allowedRoles.includes(role)).length > 0) {
      return true;
    }
    else {
      return this.router.parseUrl('/');
    }
  }

}
