import {Component, OnInit} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {PostDto} from "../../dto/dto";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.scss']
})
export class EditorComponent implements OnInit {

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
        map(() => window.history.state)
      )
      .subscribe(state => {
        const post: PostDto | undefined = state['post'];

        if (!post) {
          return;
        }

        this.updateId = post.id;
        this.title = post.title;
        this.content = post.content;
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
