import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Post } from '../../post';
import { PostServiceService } from '../../post-service.service';
import { Comentario } from '../comentario';
import { ComentariosService } from '../comentarios.service';

@Component({
  selector: 'app-formulario-comentario',
  templateUrl: './formulario-comentario.component.html',
  styleUrls: ['./formulario-comentario.component.css']
})
export class FormularioComentarioComponent implements OnInit {

  id:number = 0;
  comentarioId :number = 0;

  comentario:Comentario = new Comentario();

  constructor(private postService:PostServiceService,private comentarioService:ComentariosService,private router:Router, private activedRouter:ActivatedRoute,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.getComentario();
  }

  getComentario(){
    let id = this.activedRouter.snapshot.params['id'];
    let comentarioId = this.activedRouter.snapshot.params['comentarioId'];

    if(id && comentarioId){
    this.comentarioService.get(id,comentarioId).subscribe(
      res => this.comentario = res
   )
    }
  }

  create():void{
    const id = this.activedRouter.snapshot.params['id'];

    this.comentarioService.create(id,this.comentario).subscribe(
      e => {
        this.toastr.success('Comentario creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['posts/comentarios/',id])
      },err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    )
    }


  update():void{
    const id = this.activedRouter.snapshot.params['id'];

    this.comentarioService.update(id,this.comentario.id,this.comentario).subscribe(
        e => {
          this.toastr.success('Comentario actualizado', 'OK', {
            timeOut: 3000, positionClass: 'toast-top-center'
          });
          this.router.navigate(['/posts/comentarios/' + id])
        },err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000, positionClass: 'toast-top-center',
          });
        }
      );
  }





}
