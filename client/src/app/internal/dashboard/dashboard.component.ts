import {Component} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {PostDto, PostMetaDto, UserDto} from "../../dto/dto";
import {Observable} from "rxjs";
import {take} from "rxjs/operators";
import {UsersService} from "../../services/users.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  public drafts$: Observable<PostMetaDto[]>;

  public posts$: Observable<PostMetaDto[]>;

  public users$: Observable<UserDto[]>;

  constructor(private postsService: PostsService, private usersService: UsersService) {
    this.drafts$ = this.postsService.loadDraftsMeta();
    this.posts$ = this.postsService.loadPostsMeta();
    this.users$ = this.usersService.loadUsers();
  }

  public onPostPublish(post: PostDto): void {
    this.postsService.publishPost(post.id)
      .pipe(take(1))
      .subscribe(_post => {
        this.drafts$ = this.postsService.loadDraftsMeta();
        this.posts$ = this.postsService.loadPostsMeta();
      });
  }

  public onPostDelete(post: PostDto): void
  {
      this.postsService.deletePost(post.id)
        .pipe(take(1))
        .subscribe(_response => {
        this.drafts$ = this.postsService.loadDraftsMeta();
        this.posts$ = this.postsService.loadPostsMeta();
      });
  }

}
