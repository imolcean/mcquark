import { Component, OnInit } from '@angular/core';
import {Post} from "../../dto/post";
import {PostsService} from "../../services/posts.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  // TODO: Column width
  // TODO: Show tabs based on user's roles

  public posts: Post[];

  constructor(private postsService: PostsService) {}

  ngOnInit(): void {
    this.posts = this.postsService.getDummyPosts(500);
  }

  public onPostCreate(): void
  {
    // TODO
    console.log("onPostCreate()");
  }

  public onPostEdit(post: Post): void
  {
    // TODO
    console.log(post);
  }

  public onPostDelete(post: Post): void
  {
    // TODO
    console.log(post);
  }

}
