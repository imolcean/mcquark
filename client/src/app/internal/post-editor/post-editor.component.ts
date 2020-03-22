import {Component, OnInit} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {PostDto} from "../../dto/dto";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {flatMap} from "rxjs/operators";
import {of} from "rxjs";

@Component({
  selector: 'app-post-editor',
  templateUrl: './post-editor.component.html',
  styleUrls: ['./post-editor.component.scss']
})
export class PostEditorComponent implements OnInit {

  public title: string;

  public content: string;

  public error: boolean;

  private updateId: number | undefined;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private postsService: PostsService) {
    this.title = '';
    this.content = '';
    this.error = false;
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(
        flatMap((params: ParamMap) => {
          const id: string | null = params.get('postId');

          if (!id) {
            return of(undefined);
          }

          return this.postsService.loadPost(+id);
        })
      )
      .subscribe(post => {
        this.updateId = post ? post.id : undefined;
        this.title = post ? post.title : '';
        this.content = post ? post.content! : '';
      });
  }

  public submit(event: Event): void {
    event.preventDefault();

    if (this.title === '' || this.content === '') {
      return;
    }

    console.log(this.content);

    const post: PostDto = {
      id: this.updateId ? this.updateId : -1,
      title: this.title,
      content: this.content
    };

    if (this.updateId) {
      this.postsService.updatePost(post)
        .subscribe(() => this.router.navigateByUrl('/internal'),() => this.error = true);
    }
    else {
      this.postsService.createPost(post)
        .subscribe(() => this.router.navigateByUrl('/internal'),() => this.error = true);
    }
  }

}
