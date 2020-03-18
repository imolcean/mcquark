import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {UserDto} from "../../dto/dto";
import {UsersService} from "../../services/users.service";

@Component({
  selector: 'app-email-editor',
  templateUrl: './email-editor.component.html',
  styleUrls: ['./email-editor.component.scss']
})
export class EmailEditorComponent implements OnInit {

  public user: UserDto | undefined;

  public error: boolean;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private usersService: UsersService) {
    this.error = false;
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(
        map(() => window.history.state)
      )
      .subscribe(state => {
        const user: UserDto | undefined = state['user'];

        if (!user) {
          this.router.navigateByUrl('/profile');
        }

        this.user = user;
      });
  }

  public submit(event: Event): void {
    event.preventDefault();

    this.usersService.updateCurrentUser(this.user!)
      .subscribe(_response => this.router.navigateByUrl('/profile'), () => this.error = true);
  }

}
