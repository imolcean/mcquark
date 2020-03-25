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

  public post: PostDto | undefined;

  public error: boolean;

  public savingInProgress: boolean;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private postsService: PostsService) {
    this.error = false;
    this.savingInProgress = false;
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(
        flatMap((params: ParamMap) => {
          const id: string | null = params.get('postId');

          if (!id) {
            const newPost: PostDto = {
              id: -1,
              title: '',
              preview: ''
            };

            return of(newPost);
          }

          return this.postsService.loadPost(+id);
        })
      )
      .subscribe(post => this.post = post);
  }

  public submit(event: Event): void {
    event.preventDefault();

    if (!this.post) {
      return;
    }

    if (this.isEmpty(this.post.title) || this.isEmpty(this.post.preview)) {
      return;
    }

    console.debug(this.post);

    this.savingInProgress = true;

    if (this.post.id < 0) {
      this.postsService.createPost(this.post)
        .subscribe(
          () => this.router.navigateByUrl('/internal'),
          () => {
            this.savingInProgress = false;
            this.error = true;
          });
    }
    else {
      this.postsService.updatePost(this.post)
        .subscribe(
          () => this.router.navigateByUrl('/internal'),
          () => {
            this.savingInProgress = false;
            this.error = true;
          });
    }
  }

  private isEmpty(str: string): boolean {
    return str === null || str === undefined || str === '';
  }

}
