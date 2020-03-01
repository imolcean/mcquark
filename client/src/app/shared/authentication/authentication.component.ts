import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {Credentials} from "./credentials";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {UserDto} from "../../dto/dto";

@Component({
  selector: 'app-login',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  public loginFailed$: Observable<boolean>;

  public username$: Observable<string>;

  constructor(public auth: AuthenticationService) {
    this.loginFailed$ = this.auth.lastAuthFailed$;
    this.username$ = this.auth.user$.pipe(
      map((user: UserDto | undefined) => user ? user.username : '')
    );

    this.auth.authenticate();
  }

  ngOnInit(): void {}

  public login(event: Event, username: string, password: string): void {
    event.preventDefault();

    if (!username || !password) {
      return;
    }

    this.auth.authenticate(new Credentials(username, password));
  }

  public logout(): void {
    this.auth.logout();
  }

}
