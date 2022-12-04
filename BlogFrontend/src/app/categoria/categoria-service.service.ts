import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Categoria } from './categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaServiceService {

  constructor(private http:HttpClient) { }

  url = 'api/categorias/';


  getAll(page:number,size:number,order:string,asc:boolean):Observable<any>{
    return this.http.get<any>(environment.url + this.url + `?page=${page}&size=${size}&order=${order}&asc=${asc}`);
  }

  get(id:number):Observable<Categoria>{
    return this.http.get<Categoria>(environment.url + this.url + id);
  }

  create(categoria:Categoria):Observable<Categoria>{
    return this.http.post<Categoria>(environment.url + this.url , categoria)
  }

  update(id:number,categoria:Categoria):Observable<Categoria>{
    return this.http.put<Categoria>(environment.url + this.url + id , categoria);
  }

  delete(id:number):Observable<any>{
    return this.http.delete<any>(environment.url + this.url + id)
  }
}
