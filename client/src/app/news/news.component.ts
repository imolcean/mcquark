import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostDto} from "../dto/dto";
import {Observable} from "rxjs";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent {

  // TODO: Pagination

  public posts: Observable<PostDto[]>;

  constructor(private http: HttpClient) {
    this.posts = this.http.get<PostDto[]>('api/v1/feed');
  }

}
