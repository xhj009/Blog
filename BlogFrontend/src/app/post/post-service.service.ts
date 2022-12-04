import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from './post';
import { PostSave } from './post-save';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(private http:HttpClient) { }

  url = environment.url + 'api/posts/';


  getAll(page:number,size:number,order:string,asc:boolean):Observable<any>{
    return this.http.get<any>(this.url + `?page=${page}&size=${size}&order=${order}&asc=${asc}`);
  }

  get(id:number):Observable<any>{
    return this.http.get<any>(this.url + id);
  }

  create(post:PostSave):Observable<PostSave>{
    return this.http.post<PostSave>(this.url, post)
  }

  update(id:number,post:PostSave):Observable<PostSave>{
    return this.http.put<PostSave>(this.url + id , post);
  }

  delete(id:number):Observable<any>{
    return this.http.delete<any>(this.url + id);
  }
}
