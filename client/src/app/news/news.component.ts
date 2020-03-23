import {Component, OnInit} from '@angular/core';
import {PostDto} from "../dto/dto";
import {Observable} from "rxjs";
import {PostsService} from "../services/posts.service";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  // TODO: Pagination

  public posts$: Observable<PostDto[]> | undefined;

  constructor(private postsService: PostsService) {}

  ngOnInit(): void {
    this.posts$ = this.postsService.loadPosts();
  }

}
