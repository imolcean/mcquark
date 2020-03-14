import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {UserDto} from "../../dto/dto";
import {Credentials} from "./credentials";
import {catchError, map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private _user: UserDto | undefined;

  // private userSubject$: BehaviorSubject<UserDto | undefined>;
  // public user$: Observable<UserDto | undefined>;
  //
  // private lastAuthFailedSubject$: BehaviorSubject<boolean>;
  // public lastAuthFailed$: Observable<boolean>;

  constructor(private http: HttpClient) {
    // this.userSubject$ = new BehaviorSubject<UserDto | undefined>(undefined);
    // this.user$ = this.userSubject$.asObservable();
    //
    // this.lastAuthFailedSubject$ = new BehaviorSubject<boolean>(false);
    // this.lastAuthFailed$ = this.lastAuthFailedSubject$.asObservable();
  }

  public get currentUser(): UserDto | undefined {
    // return this.userSubject$.value;

    return this._user;
  }

  public loadCurrentUser(): Observable<UserDto | undefined> {
    return this.http.post<UserDto>('api/v1/auth/user', null).pipe(
      catchError(_error => of(undefined)),
      tap(data => this._user = data)
    );
  }

  public authenticate(credentials: Credentials): Observable<UserDto | undefined> {
    let headers: HttpHeaders | undefined;

    headers = new HttpHeaders({
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    });

    return this.http.post<UserDto>('api/v1/auth/user', null, { headers: headers }).pipe(
      catchError(_error => of(undefined)),
      tap(data => this._user = data)
    );
  }

  // public authenticate(credentials: Credentials): void {
  //   let headers: HttpHeaders | undefined;
  //
  //   headers = new HttpHeaders({
  //     authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
  //   });
  //
  //   this.http.post<UserDto>('api/v1/auth/user', null, { headers: headers })
  //     .subscribe(user => {
  //       this.userSubject$.next(user);
  //       this.lastAuthFailedSubject$.next(false);
  //     }, _error => {
  //       if (credentials) {
  //         this.lastAuthFailedSubject$.next(true);
  //       }
  //     });
  // }

  public logout(): Observable<UserDto | undefined> {
    return this.http.post<void>('api/v1/auth/logout', null, {observe: "response"}).pipe(
      tap(response => this._user = response.ok ? undefined : this._user),
      map(_response => this._user)
    );


      // .subscribe(_response => {
      //   this._user = undefined;
      //   this.router.navigateByUrl('/');
      // });
  }
}
