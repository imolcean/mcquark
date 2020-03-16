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

  public createPost(post: PostDto): Observable<PostDto> {
    return this.http.post<PostDto>('api/v1/feed', post);
  }

  public updatePost(post: PostDto): Observable<PostDto> {
    return this.http.put<PostDto>('api/v1/post/' + post.id, post);
  }

  public deletePost(id: number): Observable<void> {
    return this.http.delete<void>('api/v1/post/' + id);
  }

}
