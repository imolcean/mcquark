import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserDto} from "../dto/dto";

@Component({
  selector: 'app-user-editor',
  templateUrl: './user-editor.component.html',
  styleUrls: ['./user-editor.component.scss']
})
export class UserEditorComponent implements OnInit {

  public user: UserDto;

  public passwordConfirm: string;

  constructor(private http: HttpClient, private router: Router) {
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

    this.http.post<void>('api/v1/users', this.user).subscribe(_response => {
      this.router.navigateByUrl('/');
    });
  }

  private checkPasswordConfirmMatch(): boolean {
    return this.user.password === this.passwordConfirm;
  }

}
