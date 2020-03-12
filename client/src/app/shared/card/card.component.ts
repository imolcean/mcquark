import {Component, Input} from '@angular/core';
import {PostDto} from "../../dto/dto";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {

  @Input()
  public post: PostDto;

  constructor() {
    this.post = {
      id: -1,
      title: '',
      content: ''
    };
  }

}
