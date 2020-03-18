import { Injectable } from '@angular/core';
import {ChangePasswordRequest, UserDto} from "../dto/dto";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) {}

  public createUser(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>('api/v1/users', user);
  }

  public updateCurrentUser(user: UserDto): Observable<UserDto> {
    return this.http.put<UserDto>('api/v1/user/me', user);
  }

  public changePasswordOfCurrentUser(request: ChangePasswordRequest): Observable<UserDto> {
    return this.http.put<UserDto>('api/v1/user/me/password', request);
  }

}
