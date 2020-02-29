import { Component, OnInit } from '@angular/core';
import {Credentials, AuthenticationService} from "./authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  constructor(public auth: AuthenticationService) {
    this.auth.authenticate(undefined);
  }

  ngOnInit(): void {}

  public login(event: Event, username: string, password: string): void {
    this.auth.authenticate(new Credentials(username, password));
    event.preventDefault();
  }

  public logout(): void {
    this.auth.logout();
  }

}
