import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostDto} from "../dto/dto";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  // TODO: Pagination

  public posts: PostDto[];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<PostDto[]>('api/v1/news/feed').subscribe(data => this.posts = data);
  }

}
