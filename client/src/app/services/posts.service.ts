import { Injectable } from '@angular/core';
import {PostDto} from "../dto/dto";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) {}

  public loadPosts(): Observable<PostDto[]> {
    return this.http.get<PostDto[]>('api/v1/feed');
  }

  public getDummyPosts(amount: number): PostDto[] {
    const posts: PostDto[] = [];

    for (let i = 0; i < amount; i++) {
      const post: PostDto = {
        id: i,
        title: "Post " + i,
        content: "<h1>Post</h1><p>Lorem ipsum...</p>",
        created: new Date(),
        modified: new Date(),
        authorUsername: "Gory26"
      };

      posts.push(post);
    }

    return posts;
  }

}
