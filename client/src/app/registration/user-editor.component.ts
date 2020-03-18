import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserDto} from "../dto/dto";
import {UsersService} from "../services/users.service";

@Component({
  selector: 'app-user-editor',
  templateUrl: './user-editor.component.html',
  styleUrls: ['./user-editor.component.scss']
})
export class UserEditorComponent implements OnInit {

  public user: UserDto;

  public passwordConfirm: string;

  constructor(private usersService: UsersService, private router: Router) {
    this.user = {
      id: -1,
      username: '',
      roles: []
    };

    this.passwordConfirm = '';
  }

  ngOnInit(): void {}

  public submit(event: Event): void {
    event.preventDefault();

    if (!this.checkPasswordConfirmMatch()) {
      return;
    }

    this.usersService.createUser(this.user).subscribe(_response => {
      this.router.navigateByUrl('/');
    });
  }

  private checkPasswordConfirmMatch(): boolean {
    return this.user.password === this.passwordConfirm;
  }

}
