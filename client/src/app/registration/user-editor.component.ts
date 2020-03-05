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

  constructor(private http: HttpClient, private router: Router) {
    this.user = {
      id: -1,
      username: '',
      roles: []
    };
  }

  ngOnInit(): void {}

  public submit(event: Event): void {
    event.preventDefault();

    this.http.post<void>('api/v1/users', this.user).subscribe(_response => {
      this.router.navigateByUrl('/');
    });
  }

}
