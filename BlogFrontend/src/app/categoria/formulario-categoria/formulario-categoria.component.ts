import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Categoria } from '../categoria';
import { CategoriaServiceService } from '../categoria-service.service';

@Component({
  selector: 'app-formulario-categoria',
  templateUrl: './formulario-categoria.component.html',
  styleUrls: ['./formulario-categoria.component.css']
})
export class FormularioCategoriaComponent implements OnInit {

  constructor(private categoriaService:CategoriaServiceService,private router:Router,private activatedRoute:ActivatedRoute,private toastr: ToastrService) { }

  categoria:Categoria = new Categoria();

  ngOnInit(): void {
    this.cargarDatos();
  }


  cargarDatos():void{
    const id = this.activatedRoute.snapshot.params['id'];
      if(id){
        this.categoriaService.get(id).subscribe(
          e => this.categoria = e
        );
      }
  }


  create():void{
    this.categoriaService.create(this.categoria).subscribe(
      data => {
        this.toastr.success('Categoria creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['categorias'])
      },err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    )
  }

  update():void{
    this.categoriaService.update(this.categoria.id,this.categoria)
    .subscribe(
      data => {
        this.toastr.success('Categoria actualizada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['categorias'])
      },err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );

  }


}
