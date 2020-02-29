import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

export class Credentials {
  username: string;
  password: string;

  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _authenticated: boolean;

  public nick: string;

  public get authenticated(): boolean {
    return this._authenticated;
  }

  constructor(private http: HttpClient, private router: Router) {}

  // TODO: Request UserDto instead of boolean
  public authenticate(credentials: Credentials): void {
    let headers: HttpHeaders;

    if (credentials) {
      headers = new HttpHeaders({
        authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
      });
    }

    this.http.get('user', { headers: headers })
      .subscribe(response => {
        if (response['name']) {
          this._authenticated = true;
          this.nick = response['name'];
        }
        else {
          this._authenticated = false;
          this.nick = undefined;
        }
      });
  }

  public logout(): void {
    this.http.post('logout', null)
      .subscribe(response => {
        if (!response) {
          return;
        }

        this._authenticated = false;
        this.router.navigateByUrl('/');
      });
  }
}
