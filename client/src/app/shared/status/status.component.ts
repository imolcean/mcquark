import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {

  public status: boolean | undefined;

  public showMsgCopied: boolean;

  constructor() {
    this.status = false;
    this.showMsgCopied = false;
  }

  ngOnInit(): void {
    this.status = true; // TODO
  }

  public copyLink(field: HTMLInputElement): void {
    field.select();
    document.execCommand('copy');
    field.setSelectionRange(0 ,0);

    this.showMsgCopied = true;
    setTimeout(() => this.showMsgCopied = false, 3000);
  }

}
