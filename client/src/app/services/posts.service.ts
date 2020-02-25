import { Injectable } from '@angular/core';
import {PostDto} from "../dto/dto";

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor() {}

  public getDummyPosts(amount: number): PostDto[] {
    const posts: PostDto[] = [];

    for (let i = 0; i < amount; i++) {
      const post: PostDto = {
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
