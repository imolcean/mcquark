import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";

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
export class AuthenticationService {

  private userSubject$: BehaviorSubject<string>;

  public user$: Observable<string>;

  constructor(private http: HttpClient, private router: Router) {
    this.userSubject$ = new BehaviorSubject<string>('');
    this.user$ = this.userSubject$.asObservable();
  }

  // TODO: Request UserDto instead of boolean
  public authenticate(credentials: Credentials): void {
    let headers: HttpHeaders;

    if (credentials) {
      headers = new HttpHeaders({
        authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
      });
    }

    this.http.post('user', null, { headers: headers })
      .subscribe(response => {
        if (response['name']) {
          this.userSubject$.next(response['name']);
        }
        else {
          this.userSubject$.next(undefined);
        }
      });
  }

  public logout(): void {
    this.http.post('logout', null)
      .subscribe(_response => {
        this.userSubject$.next(undefined);
        this.router.navigateByUrl('/');
      });
  }
}
