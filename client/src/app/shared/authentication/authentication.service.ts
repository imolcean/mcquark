import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";
import {UserDto} from "../../dto/dto";
import {Credentials} from "./credentials";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private userSubject$: BehaviorSubject<UserDto | undefined>;
  public user$: Observable<UserDto | undefined>;

  private lastAuthFailedSubject$: BehaviorSubject<boolean>;
  public lastAuthFailed$: Observable<boolean>;

  constructor(private http: HttpClient, private router: Router) {
    this.userSubject$ = new BehaviorSubject<UserDto | undefined>(undefined);
    this.user$ = this.userSubject$.asObservable();

    this.lastAuthFailedSubject$ = new BehaviorSubject<boolean>(false);
    this.lastAuthFailed$ = this.lastAuthFailedSubject$.asObservable();
  }

  public authenticate(credentials?: Credentials): void {
    let headers: HttpHeaders | undefined;

    if (credentials) {
      headers = new HttpHeaders({
        authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
      });
    }

    this.http.post<UserDto>('api/v1/auth/user', null, { headers: headers })
      .subscribe(user => {
        this.userSubject$.next(user);
        this.lastAuthFailedSubject$.next(false);
      }, _error => {
        if (credentials) {
          this.lastAuthFailedSubject$.next(true);
        }
      });
  }

  public logout(): void {
    this.http.post<void>('api/v1/auth/logout', null).subscribe(_response => {
      this.userSubject$.next(undefined);
      this.router.navigateByUrl('/');
    });
  }
}

// TODO Forget Basic auth credentials after logout
