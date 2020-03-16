import { Injectable } from '@angular/core';
import {PostDto} from "../dto/dto";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {flatMap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) {}

  public loadPosts(): Observable<PostDto[]> {
    return this.http.get<PostDto[]>('api/v1/feed');
  }

  public deletePost(id: number): Observable<PostDto[]> {
    return this.http.delete<void>('api/v1/post/' + id)
      .pipe(
        flatMap(_response => this.loadPosts())
      );
  }

}
