import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from 'src/app/auth/token.service';
import { Post } from '../post';
import { PostServiceService } from '../post-service.service';
import { Comentario } from './comentario';
import { ComentariosService } from './comentarios.service';

@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})
export class ComentariosComponent implements OnInit {


  posts: Array<Post> = [];
  comentarios:Comentario[] = [];
  nombreUsuario: string = '';


  constructor(private comentarioService:ComentariosService,private postService:PostServiceService, private tokenService:TokenService ,private router:Router, private activedRouter:ActivatedRoute,private toastr:ToastrService) { }

  ngOnInit(): void {
    //this.cargarDatos();
    this.cargarPosts();
    this.cargarComentarios();
    this.nombreUsuario = this.tokenService.getUserName();
  }


/*
  cargarPosts(){
    this.postService.getAll().subscribe(
      data => {
        this.posts = data
        console.log(data)
      }
    )
  } */


  cargarPosts(){
    const id = this.activedRouter.snapshot.params['id'];
    this.postService.get(id).subscribe(
          e => {
            this.posts.push(e)
          }
        )
  }


  cargarComentarios(){
    const id = this.activedRouter.snapshot.params['id'];
      if(id){
        this.comentarioService.getAll(id).subscribe(
          e => this.comentarios = e
         );
      }
  }


  /*
  delete(comentario:Comentario){
    this.activedRouter.params.subscribe(
      data => {
         let id = data['id'];
         this.comentarioService.delete(id,comentario.id).subscribe(
          e => this.comentarioService.getAll(id).subscribe(
            res => this.comentarios = res
          )
         )
      }
    );
  } */


  delete(comentario:Comentario){
    const id = this.activedRouter.snapshot.params['id'];

    this.comentarioService.delete(id,comentario.id).subscribe(
      data => {
        console.log(data);
        this.toastr.success('Comentario eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarComentarios();
      }, err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }




}
