import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../shared/authentication/authentication.service";
import {UserDto} from "../dto/dto";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public user: UserDto | undefined;

  constructor(private auth: AuthenticationService) {}

  ngOnInit(): void {
    this.auth.loadCurrentUser()
      .subscribe(user => this.user = user);
  }

}
