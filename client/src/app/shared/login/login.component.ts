import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public nick: string;

  constructor() {}

  ngOnInit(): void {}

  login(nick: string): void {
    if (!nick || nick.trim() === '') {
      return;
    }

    this.nick = nick.trim();
  }

}
