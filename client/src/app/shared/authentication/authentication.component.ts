import { Component, OnInit } from '@angular/core';
import {Credentials, AuthenticationService} from "./authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  // TODO: Login error message

  constructor(public auth: AuthenticationService) {
    this.auth.authenticate(undefined);
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
