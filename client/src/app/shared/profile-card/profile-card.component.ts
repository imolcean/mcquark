import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.scss']
})
export class ProfileCardComponent {

  @Input()
  public username: string;

  @Input()
  public email: string;

  // TODO: Roles

  constructor() {
    this.username = '';
    this.email = '';
  }

}
