import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {

  public status: boolean;

  constructor() {}

  ngOnInit(): void {
    this.status = true; // TODO
  }

}
