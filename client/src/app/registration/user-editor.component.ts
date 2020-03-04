import { Component, OnInit } from '@angular/core';
import {UserSaveRequest} from "../dto/dto";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-editor',
  templateUrl: './user-editor.component.html',
  styleUrls: ['./user-editor.component.scss']
})
export class UserEditorComponent implements OnInit {

  public request: UserSaveRequest;

  constructor(private http: HttpClient, private router: Router) {
    this.request = {
      name: '',
      password: '',
      email: '',
      roles: []
    };
  }

  ngOnInit(): void {}

  public submit(event: Event): void {
    event.preventDefault();

    this.http.post<void>('api/v1/users', this.request).subscribe(_response => {
      this.router.navigateByUrl('/');
    });
  }

}
