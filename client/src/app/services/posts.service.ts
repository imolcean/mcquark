import { Injectable } from '@angular/core';
import {Post} from "../dto/post";

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor() {}

  public getDummyPosts(amount: number): Post[] {
    const posts: Post[] = [];

    for (let i = 0; i < amount; i++) {
      const post: Post = {
        id: i,
        title: "Post " + i,
        content: "<h1>Post</h1><p>Lorem ipsum...</p>",
        created: new Date(),
        modified: new Date(),
        authorNick: "Gory26"
      };

      posts.push(post);
    }

    return posts;
  }

}
