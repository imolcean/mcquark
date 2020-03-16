import { Component } from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {PostDto} from "../../dto/dto";
import {Observable} from "rxjs";
import {flatMap} from "rxjs/operators";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  // TODO: Column width
  // TODO: Show tabs based on user's roles

  public posts$: Observable<PostDto[]>;

  constructor(private postsService: PostsService) {
    this.posts$ = this.postsService.loadPosts();
  }

  public onPostDelete(post: PostDto): void
  {
    this.posts$ = this.postsService.deletePost(post.id)
      .pipe(
        flatMap(_response => this.postsService.loadPosts())
      );
  }

}
