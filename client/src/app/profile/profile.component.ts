import { Component } from '@angular/core';
import {AuthenticationService} from "../shared/authentication/authentication.service";
import {Observable} from "rxjs";
import {UserDto} from "../dto/dto";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent {

  public user$: Observable<UserDto | undefined>;

  constructor(private auth: AuthenticationService) {
    this.user$ = this.auth.user$;
  }

}
