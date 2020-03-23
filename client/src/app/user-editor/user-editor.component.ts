import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {UserDto} from "../dto/dto";
import {UsersService} from "../services/users.service";
import {flatMap} from "rxjs/operators";
import {of} from "rxjs";

@Component({
  selector: 'app-user-editor',
  templateUrl: './user-editor.component.html',
  styleUrls: ['./user-editor.component.scss']
})
export class UserEditorComponent implements OnInit {

  public user: UserDto;

  public passwordConfirm: string;

  public error: boolean;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private usersService: UsersService) {
    this.user = {
      id: -1,
      username: '',
      roles: []
    };

    this.passwordConfirm = '';
    this.error = false;
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(
        flatMap((params: ParamMap) => {
          const id: string | null = params.get('userId');

          if (!id) {
            return of(undefined);
          }

          return this.usersService.loadUser(+id);
        })
      )
      .subscribe(user => {
        if (!user) {
          return;
        }

        this.user = user;
      },
        () => this.router.navigateByUrl('/internal'));
  }

  public submit(event: Event): void {
    event.preventDefault();

    if (this.user.id < 0) {
      if (!this.checkPasswordConfirmMatch()) {
        return;
      }

      this.usersService.createUser(this.user)
        .subscribe(_response => this.router.navigateByUrl('/'), () => this.error = true);
    }
    else {
      this.usersService.updateUser(this.user)
        .subscribe(_response => this.router.navigateByUrl('/internal'), () => this.error = true);
    }
  }

  private checkPasswordConfirmMatch(): boolean {
    return this.user.password === this.passwordConfirm;
  }

}
