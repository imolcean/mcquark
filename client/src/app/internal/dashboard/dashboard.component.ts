import { Component } from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {PostDto, PostMetaDto, UserDto} from "../../dto/dto";
import {Observable} from "rxjs";
import {flatMap} from "rxjs/operators";
import {UsersService} from "../../services/users.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  public posts$: Observable<PostMetaDto[]>;

  public users$: Observable<UserDto[]>;

  constructor(private postsService: PostsService, private usersService: UsersService) {
    this.posts$ = this.postsService.loadPostsMeta();
    this.users$ = this.usersService.loadUsers();
  }

  public onPostDelete(post: PostDto): void
  {
    this.posts$ = this.postsService.deletePost(post.id)
      .pipe(
        flatMap(_response => this.postsService.loadPostsMeta())
      );
  }

}
