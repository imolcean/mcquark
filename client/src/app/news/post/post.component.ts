import {Component, OnInit} from '@angular/core';
import {PostDto} from "../../dto/dto";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";
import {PostsService} from "../../services/posts.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {flatMap} from "rxjs/operators";
import {of} from "rxjs";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  public post: PostDto | undefined;

  public preview: SafeHtml;
  public content: SafeHtml;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private sanitizer: DomSanitizer,
              private postsService: PostsService) {
    this.preview = {};
    this.content = {};
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(
        flatMap((params: ParamMap) => {
          const id: string | null = params.get('postId');

          if(!id) {
            return of(undefined);
          }

          return this.postsService.loadPost(+id);
        })
      )
      .subscribe(
        post => {
          if (!post) {
            this.router.navigateByUrl("/");
            return;
          }

          this.post = post;
          this.preview = this.sanitizer.bypassSecurityTrustHtml(this.post.preview);
          this.content = this.sanitizer.bypassSecurityTrustHtml(this.post.content ? this.post.content : '');
        },
        () => this.router.navigateByUrl("/"));
  }

}
