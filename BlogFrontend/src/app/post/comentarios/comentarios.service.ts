import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comentario } from './comentario';

@Injectable({
  providedIn: 'root'
})
export class ComentariosService {

  constructor(private http:HttpClient) { }



  getAll(id:number):Observable<Comentario[]>{
    return this.http.get<Comentario[]>(environment.url + 'api/posts/' + id + '/comentarios') ;
  }

  get(id:number,comentarioId:number):Observable<Comentario>{
    return this.http.get<Comentario>(environment.url + 'api/posts/' + id + '/comentarios/' + comentarioId );
  }

  create(id:number,comentario:Comentario):Observable<any>{
    return this.http.post<any>(environment.url + 'api/posts/' + id + '/comentarios',comentario );
  }

  update(id:number,comentarioId:number,comentario:Comentario):Observable<any>{
    return this.http.put<any>(environment.url + 'api/posts/' + id + '/comentarios/' + comentarioId , comentario);
  }

  delete(id:number,comentarioId:number):Observable<Comentario[]>{
    return this.http.delete<Comentario[]>(environment.url +  'api/posts/' + id + '/comentarios/' + comentarioId);
  }

}
