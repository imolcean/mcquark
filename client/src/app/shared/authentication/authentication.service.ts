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

  constructor(private http: HttpClient) {}

  public get currentUser(): UserDto | undefined {
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

  public logout(): Observable<UserDto | undefined> {
    return this.http.post<void>('api/v1/auth/logout', null, {observe: "response"}).pipe(
      tap(response => this._user = response.ok ? undefined : this._user),
      map(_response => this._user)
    );
  }
}
