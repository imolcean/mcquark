import { Component } from '@angular/core';
import {UsersService} from "../../services/users.service";
import {Router} from "@angular/router";
import {ChangePasswordRequest} from "../../dto/dto";

@Component({
  selector: 'app-password-editor',
  templateUrl: './password-editor.component.html',
  styleUrls: ['./password-editor.component.scss']
})
export class PasswordEditorComponent {

  public oldPassword: string;

  public newPassword: string;

  public newPasswordConfirm: string;

  public error: boolean;

  constructor(private router: Router, private usersService: UsersService) {
    this.oldPassword = '';
    this.newPassword = '';
    this.newPasswordConfirm = '';
    this.error = false;
  }

  public submit(event: Event): void {
    event.preventDefault();

    if (!this.checkPasswordConfirmMatch()) {
      return;
    }

    const request: ChangePasswordRequest = {
      oldPassword: this.oldPassword,
      newPassword: this.newPassword
    };

    this.usersService.changePasswordOfCurrentUser(request)
      .subscribe(_response => this.router.navigateByUrl('/profile'), () => this.error = true);
  }

  private checkPasswordConfirmMatch(): boolean {
    return this.newPassword === this.newPasswordConfirm;
  }

}
