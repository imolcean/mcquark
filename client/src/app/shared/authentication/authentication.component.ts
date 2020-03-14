import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {Credentials} from "./credentials";
import {UserDto} from "../../dto/dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  public user: UserDto | undefined;

  public lastAuthFailed: boolean;

  constructor(private auth: AuthenticationService, private router: Router) {
    this.lastAuthFailed = false;
  }

  ngOnInit(): void {
    // this.auth.loadCurrentUser()
    //   .subscribe(user => this.user = user);

    this.user = this.auth.currentUser;
  }

  public login(event: Event, username: string, password: string): void {
    event.preventDefault();
    this.auth.authenticate(new Credentials(username, password))
      .subscribe(user => {
        this.user = user;
        this.lastAuthFailed = user === undefined;
      });
  }

  public logout(): void {
    this.auth.logout()
      .subscribe(user => {
        this.user = user;
        this.router.navigateByUrl('/');
      });
  }

}
