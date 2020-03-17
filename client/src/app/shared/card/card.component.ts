import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {PostDto} from "../../dto/dto";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnChanges {

  @Input()
  public post: PostDto;

  public content: SafeHtml;

  constructor(private sanitizer: DomSanitizer) {
    this.post = {
      id: -1,
      title: '',
      content: ''
    };

    this.content = {};
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.content = this.sanitizer.bypassSecurityTrustHtml(this.post.content);
  }

}
