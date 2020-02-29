import { Component, OnInit } from '@angular/core';
import {Credentials, LoginService} from "./login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(public auth: LoginService) {
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
