import { Component, OnInit } from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {PostDto} from "../../dto/dto";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  // TODO: Column width
  // TODO: Show tabs based on user's roles

  public posts: PostDto[];

  constructor(private postsService: PostsService) {
    this.posts = [];
  }

  ngOnInit(): void {
    this.posts = this.postsService.getDummyPosts(500);
  }

  public onPostCreate(): void
  {
    // TODO
    console.log("onPostCreate()");
  }

  public onPostEdit(post: PostDto): void
  {
    // TODO
    console.log(post);
  }

  public onPostDelete(post: PostDto): void
  {
    // TODO
    console.log(post);
  }

}
